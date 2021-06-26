package com.chx.pluginlib.utils

import com.chx.pluginlib.extensions.BuildJarExtension
import org.gradle.api.Project

/**
 * @author: cangHX* on 2020/06/15  18:45
 */
class DataConverter {

    static BuildJarExtension converter(BuildJarExtension extension, Object object, Project project) {

        println("************* START CHECK DATA *************")
        println("")

        String group = object.group
        if (group != null && group.trim() != "") {
            extension.group = group
        }
        println("       group : " + extension.group)
        println("")

        String[] doFirstTask = object.doFirstTask
        if (doFirstTask != null && doFirstTask.length != 0) {
            extension.doFirstTask = doFirstTask
        }
        println("       doFirstTask : " + extension.doFirstTask)
        println("")

        String[] clearPath = object.clearPath
        if (clearPath != null && clearPath.length != 0) {
            extension.clearPath = clearPath
        }
        println("       clearPath : " + extension.clearPath)
        println("")

        String[] excludesFromJar = object.excludesFromJar
        if (excludesFromJar != null && excludesFromJar.length != 0) {
            extension.excludesFromJar = excludesFromJar
        }
        println("       excludesFromJar : " + extension.excludesFromJar)
        println("")

        String[] inputJarPath = object.inputJarPath
        if (inputJarPath != null && inputJarPath.length != 0) {
            extension.inputJarPath = inputJarPath
        }
        println("       inputJarPath : " + extension.inputJarPath)
        println("")

        String jarName = object.jarName
        if (jarName != null && jarName.trim() != "") {
            extension.jarName = jarName
        }
        println("       jarName : " + extension.jarName)
        println("")

        String outputJarPath = object.outputJarPath
        if (outputJarPath != null && outputJarPath.trim() != "") {
            extension.outputJarPath = outputJarPath
        }
        if (extension.outputJarPath == null || extension.outputJarPath.trim() == ""){
            extension.outputJarPath = project.buildDir.path + File.separator + "jar"
        }
        println("       outputJarPath : " + extension.outputJarPath)
        println("")

        String inputProGuardJarPath = object.inputProGuardJarPath
        if (inputProGuardJarPath != null && inputProGuardJarPath.trim() != "") {
            extension.inputProGuardJarPath = inputProGuardJarPath
        }
        println("       inputProGuardJarPath : " + extension.inputProGuardJarPath)
        println("")

        String proGuardJarName = object.proGuardJarName
        if (proGuardJarName != null && proGuardJarName.trim() != "") {
            extension.proGuardJarName = proGuardJarName
        }
        println("       proGuardJarName : " + extension.proGuardJarName)
        println("")

        String outputProGuardJarPath = object.outputProGuardJarPath
        if (outputProGuardJarPath != null && outputProGuardJarPath.trim() != "") {
            extension.outputProGuardJarPath = outputProGuardJarPath
        }
        println("       outputProGuardJarPath : " + extension.outputProGuardJarPath)
        println("")

        def isShrink = object.isShrink
        if (isShrink != null) {
            if (isShrink instanceof Boolean) {
                extension.isShrink = isShrink
            } else {
                System.err.println("isShrink can only use type Boolean")
            }
        }
        println("       isShrink : " + extension.isShrink)
        println("")

        String[] proGuardLibrarys = object.proGuardLibrarys
        if (proGuardLibrarys != null && proGuardLibrarys.length != 0) {
            extension.proGuardLibrarys = proGuardLibrarys
        }
        println("       proGuardLibrarys : " + extension.proGuardLibrarys)
        println("")

        String rulesPath = object.rulesPath
        if (rulesPath != null && rulesPath.trim() != "") {
            extension.rulesPath = rulesPath
        }
        println("       rulesPath : " + extension.rulesPath)
        println("")

        println("************* END CHECK DATA *************")

        return extension
    }

}
