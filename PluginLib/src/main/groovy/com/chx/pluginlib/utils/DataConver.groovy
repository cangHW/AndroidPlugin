package com.chx.pluginlib.utils

import com.chx.pluginlib.extensions.BuildJarExtension

/**
 * @author: cangHX* on 2020/06/15  18:45
 */
class DataConver {

    static BuildJarExtension converter(BuildJarExtension extension,Object object) {

        String group = object.group
        if (group != null && group != "") {
            extension.group = group
        }

        String[] doFirstTask = object.doFirstTask
        if (doFirstTask != null && doFirstTask.length != 0) {
            extension.doFirstTask = doFirstTask
        }

        String[] clearPath = object.clearPath
        if (clearPath != null && clearPath.length != 0) {
            extension.clearPath = clearPath
        }

        String[] inputJarPath = object.inputJarPath
        if (inputJarPath != null && inputJarPath.length != 0) {
            extension.inputJarPath = inputJarPath
        }

        String outputJarPath = object.outputJarPath
        if (outputJarPath != null && outputJarPath != "") {
            extension.outputJarPath = outputJarPath
        }

        String jarName = object.jarName
        if (jarName != null && jarName != "") {
            extension.jarName = jarName
        }

        String inputProGuardJarPath = object.inputProGuardJarPath
        if (inputProGuardJarPath != null && inputProGuardJarPath != "") {
            extension.inputProGuardJarPath = inputProGuardJarPath
        }

        String outputProGuardJarPath = object.outputProGuardJarPath
        if (outputProGuardJarPath != null && outputProGuardJarPath != "") {
            extension.outputProGuardJarPath = outputProGuardJarPath
        }

        String proGuardJarName = object.proGuardJarName
        if (proGuardJarName != null && proGuardJarName != "") {
            extension.proGuardJarName = proGuardJarName
        }

        boolean isShrink = object.isShrink
        if (isShrink) {
            extension.isShrink = isShrink
        }

        String[] proGuardLibrarys = object.proGuardLibrarys
        if (proGuardLibrarys != null && proGuardLibrarys.length != 0) {
            extension.proGuardLibrarys = proGuardLibrarys
        }

        String rulesPath = object.rulesPath
        if (rulesPath != null && rulesPath != "") {
            extension.rulesPath = rulesPath
        }

        return extension;
    }

}
