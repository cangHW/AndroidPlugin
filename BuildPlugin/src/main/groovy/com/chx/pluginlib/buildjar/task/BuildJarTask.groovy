package com.chx.pluginlib.buildjar.task

import com.chx.pluginlib.base.BaseJar
import com.chx.pluginlib.buildjar.extensions.BuildJarExtension
import com.chx.pluginlib.buildjar.utils.JarUtils

/**
 * @author: cangHX* on 2020/06/15  10:53
 */
class BuildJarTask extends BaseJar {

    BuildJarTask() {
        BuildJarExtension buildJarExtension = project.buildJarExtension

        try {
            archiveFileName.set(buildJarExtension.getJarName())
        } catch (Throwable ignored) {
            setArchiveName(buildJarExtension.getJarName())
        }

        try {
            destinationDirectory.set(new File(buildJarExtension.getOutputJarPath()))
        } catch (Throwable ignored) {
            setDestinationDir(new File(buildJarExtension.getOutputJarPath()))
        }

    }

    @Override
    protected void copy() {
        BuildJarExtension buildJarExtension = project.buildJarExtension
        from(getFileTree(buildJarExtension))
        super.copy()
    }

    List<File> getFileTree(BuildJarExtension buildJarExtension) {
        List<File> files = new ArrayList<>()
        List<File> jarFiles = JarUtils.getJarsFromPaths(buildJarExtension.getInputJarPath(), project)

        println("start to create jar")
        for (File jarFile : jarFiles) {
            try {
                exclude(buildJarExtension.getExcludesFromJar())
                def file = project.rootProject.zipTree(jarFile)["asFileTrees"].get(0).getDir()
                println("add file : " + jarFile)
                files.add(file)
            } catch (Throwable throwable) {
                println(throwable)
            }
        }
        println("end to create jar")

        return files
    }

}