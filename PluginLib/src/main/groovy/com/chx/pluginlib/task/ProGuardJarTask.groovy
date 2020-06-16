package com.chx.pluginlib.task

import com.chx.pluginlib.base.BaseProGuard
import com.chx.pluginlib.extensions.BuildJarExtension
import com.chx.pluginlib.utils.JarUtils
import org.gradle.api.Plugin
import proguard.ParseException

/**
 * @author: cangHX* on 2020/06/15  10:57
 */
class ProGuardJarTask extends BaseProGuard {

    @Override
    void proguard() throws ParseException, IOException {
        BuildJarExtension buildJarExtension = project.buildJar
        setJarParams(buildJarExtension)
        addRunTime()
        addLibrarys(buildJarExtension)
        addLibrarysDefault()
        super.proguard()
    }

    void setJarParams(BuildJarExtension buildJarExtension) {
        configuration(buildJarExtension.getRulesPath())

        String inPath = project.projectDir.absolutePath + File.separator + buildJarExtension.getInputProGuardJarPath() + ".jar"
        println("injars+++++++++++++++" + inPath)
        injars(inPath)

        String outPath = project.projectDir.absolutePath + File.separator + buildJarExtension.getOutputProGuardJarPath() + File.separator + buildJarExtension.getProGuardJarName() + ".jar"
        println("outjars+++++++++++++++" + outPath)
        outjars(outPath)

        if (!buildJarExtension.getIsShrink()) {
            dontshrink()
        }
    }

    void addRunTime() {
        String libraryId = "com.android.library"
        String appId = "com.android.application"

        Plugin plugin = project.getPlugins().hasPlugin(appId) ?
                project.getPlugins().findPlugin(appId) : project.getPlugins().findPlugin(libraryId)
        if (plugin == null) {
            return
        }
        List<String> runtimeJarList
        if (plugin.getMetaClass().getMetaMethod("getRuntimeJarList")) {
            runtimeJarList = plugin.getRuntimeJarList()
        } else if (project.android.getMetaClass().getMetaMethod("getBootClasspath")) {
            runtimeJarList = project.android.getBootClasspath()
        } else {
            runtimeJarList = plugin.getBootClasspath()
        }

        for (String runtimeJar : runtimeJarList) {
            libraryjars(runtimeJar)
        }
    }

    void addLibrarys(BuildJarExtension buildJarExtension) {
        List<File> files = JarUtils.getJarsFromPaths(buildJarExtension.getProGuardLibrarys(), project)
        files.each { file ->
            String path = file.path
            libraryjars(path)
        }
    }

    void addLibrarysDefault() {

    }
}
