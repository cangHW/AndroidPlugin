package com.chx.pluginlib.extensions

/**
 * @author: cangHX* on 2020/06/15  10:28
 */
class BuildJarExtension {

    /**
     * 任务组名称
     * */
    String group = "pluglib"

    /**
     * 执行插件之前，先执行的任务
     * */
    String[] doFirstTask = []

    /******* CLEAR 任务 **********************/

    /**
     * 准备删除文件的路径
     * */
    String[] clearPath = []

    /******* BUILD JAR 任务 **********************/

    /**
     * jar包含有文件的路径
     * */
    String[] inputJarPath = []

    /**
     * 输出jar的路径
     * */
    String outputJarPath = ""

    /**
     * 生成jar包的名字
     * */
    String jarName = "buildJar"


    /******* PROGUARD JAR 任务 **********************/

    /**
     * 准备混淆的jar文件路径
     * */
    String inputProGuardJarPath = ""

    /**
     * 混淆的jar文件导出路径
     * */
    String outputProGuardJarPath = ""

    /**
     * jar包混淆后的名字
     * */
    String proGuardJarName = ""

    /**
     * 是否删除未引用的资源。true：删除；false：不删除
     */
    boolean isShrink = true

    /**
     * 混淆所需要的依赖文件路径
     * */
    String[] proGuardLibrarys = []

    /**
     * 混淆文件的路径
     * */
    String rulesPath = ""

    String getGroup() {
        return group
    }

    String[] getDoFirstTask() {
        return doFirstTask
    }

    String[] getClearPath() {
        return clearPath
    }

    String[] getInputJarPath() {
        return inputJarPath
    }

    String getOutputJarPath() {
        return outputJarPath
    }

    String getJarName() {
        return jarName
    }

    String getInputProGuardJarPath() {
        if (inputProGuardJarPath != null && inputProGuardJarPath != "") {
            return inputProGuardJarPath
        }
        return getOutputJarPath() + getJarName()
    }

    String getOutputProGuardJarPath() {
        if (outputProGuardJarPath != null && outputProGuardJarPath != "") {
            return outputProGuardJarPath
        }
        return getOutputJarPath()
    }

    String getProGuardJarName() {
        if (proGuardJarName != null && proGuardJarName != "") {
            return proGuardJarName
        }
        return getJarName() + "-ProGuard"
    }

    boolean getIsShrink() {
        return isShrink
    }

    String[] getProGuardLibrarys() {
        return proGuardLibrarys
    }

    String getRulesPath() {
        return rulesPath
    }
}