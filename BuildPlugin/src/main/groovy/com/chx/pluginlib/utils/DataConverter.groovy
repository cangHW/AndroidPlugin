package com.chx.pluginlib.utils

import com.chx.pluginlib.extensions.BuildJarExtension
import org.gradle.api.Project

/**
 * @author: cangHX* on 2020/06/15  18:45
 */
class DataConverter {

    static BuildJarExtension converter(BuildJarExtension extension, Object object, Project project) {

        def log = System.out

        log.println("********** START CHECK DATA *************")
        log.println("")

        String group = object.group
        if (group != null && group != "") {
            extension.group = group
        }
        log.println("       group : " + extension.group)
        log.println("")

        String[] doFirstTask = object.doFirstTask
        if (doFirstTask != null && doFirstTask.length != 0) {
            extension.doFirstTask = doFirstTask
        }
        log.println("       doFirstTask : " + extension.doFirstTask)
        log.println("")

        String[] clearPath = object.clearPath
        if (clearPath != null && clearPath.length != 0) {
            extension.clearPath = clearPath
        }
        log.println("       clearPath : " + extension.clearPath)
        log.println("")

        String[] inputJarPath = object.inputJarPath
        if (inputJarPath != null && inputJarPath.length != 0) {
            extension.inputJarPath = inputJarPath
        }
        log.println("       inputJarPath : " + extension.inputJarPath)
        log.println("")

        String[] excludesFromJar = object.excludesFromJar
        if (excludesFromJar != null && excludesFromJar.length != 0) {
            extension.excludesFromJar = excludesFromJar
        }
        log.println("       excludesFromJar : " + extension.excludesFromJar)
        log.println("")

        String outputJarPath = object.outputJarPath
        if (outputJarPath != null && outputJarPath != "") {
            extension.outputJarPath = outputJarPath
        } else {
            extension.outputJarPath = project.buildDir.path + File.separator + "jar"
        }
        log.println("       outputJarPath : " + extension.outputJarPath)
        log.println("")

        String jarName = object.jarName
        if (jarName != null && jarName != "") {
            extension.jarName = jarName
        }
        log.println("       jarName : " + extension.jarName)
        log.println("")

        String inputProGuardJarPath = object.inputProGuardJarPath
        if (inputProGuardJarPath != null && inputProGuardJarPath != "") {
            extension.inputProGuardJarPath = inputProGuardJarPath
        }
        log.println("       inputProGuardJarPath : " + extension.inputProGuardJarPath)
        log.println("")

        String outputProGuardJarPath = object.outputProGuardJarPath
        if (outputProGuardJarPath != null && outputProGuardJarPath != "") {
            extension.outputProGuardJarPath = outputProGuardJarPath
        }
        log.println("       outputProGuardJarPath : " + extension.outputProGuardJarPath)
        log.println("")

        String proGuardJarName = object.proGuardJarName
        if (proGuardJarName != null && proGuardJarName != "") {
            extension.proGuardJarName = proGuardJarName
        }
        log.println("       proGuardJarName : " + extension.proGuardJarName)
        log.println("")

        def isShrink = object.isShrink
        if (isShrink != null) {
            if (isShrink instanceof Boolean) {
                extension.isShrink = isShrink
            } else {
                System.err.println("isShrink can only use type Boolean")
            }
        }
        log.println("       isShrink : " + extension.isShrink)
        log.println("")

        String[] proGuardLibrarys = object.proGuardLibrarys
        if (proGuardLibrarys != null && proGuardLibrarys.length != 0) {
            extension.proGuardLibrarys = proGuardLibrarys
        }
        log.println("       proGuardLibrarys : " + extension.proGuardLibrarys)
        log.println("")

        String rulesPath = object.rulesPath
        if (rulesPath != null && rulesPath != "") {
            extension.rulesPath = rulesPath
        }
        log.println("       rulesPath : " + extension.rulesPath)
        log.println("")

        log.println("********** END CHECK DATA *************")

        return extension;
    }

}
