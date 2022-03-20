package com.chx.pluginlib.buildjar.task

import com.chx.pluginlib.base.BaseProGuard
import com.chx.pluginlib.buildjar.extensions.BuildJarExtension
import com.chx.pluginlib.buildjar.utils.JarUtils
import org.gradle.api.Plugin
import proguard.ParseException

/**
 * @author: cangHX* on 2020/06/15  10:57
 */
class ProGuardJarTask extends BaseProGuard {

    @Override
    void proguard() throws ParseException, IOException {
        BuildJarExtension buildJarExtension = project.buildJarExtension
        setJarParams(buildJarExtension)
        addRunTime()
        addLibrarys(buildJarExtension)
        super.proguard()
    }

    void setJarParams(BuildJarExtension buildJarExtension) {
        configuration(buildJarExtension.getRulesPath())

        String inPath = buildJarExtension.getInputProGuardJarPath()
        injars(inPath)

        String outPath = buildJarExtension.getOutputProGuardJarPath() + buildJarExtension.getProGuardJarName()
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
}
