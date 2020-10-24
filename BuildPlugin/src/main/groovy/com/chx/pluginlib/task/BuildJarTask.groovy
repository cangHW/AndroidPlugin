package com.chx.pluginlib.task

import com.chx.pluginlib.base.BaseJar
import com.chx.pluginlib.extensions.BuildJarExtension
import com.chx.pluginlib.utils.JarUtils

/**
 * @author: cangHX* on 2020/06/15  10:53
 */
class BuildJarTask extends BaseJar {

    BuildJarTask() {
        BuildJarExtension buildJarExtension = project.buildJar
        archiveFileName.set(buildJarExtension.getJarName())
        destinationDirectory.set(new File(buildJarExtension.getOutputJarPath()))
    }

    @Override
    protected void copy() {
        BuildJarExtension buildJarExtension = project.buildJar
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