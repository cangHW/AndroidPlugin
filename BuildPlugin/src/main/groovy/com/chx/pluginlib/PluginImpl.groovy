package com.chx.pluginlib

import com.chx.pluginlib.extensions.BuildJarExtension
import com.chx.pluginlib.task.BuildJarTask
import com.chx.pluginlib.task.ClearJarTask
import com.chx.pluginlib.task.ProGuardJarTask
import com.chx.pluginlib.utils.DataConverter
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author: cangHX* on 2020/06/15  10:23
 */
class PluginImpl implements Plugin<Project> {
    @Override
    void apply(Project project) {

        System.out.println("********** PLUGIN BUILD-JAR START *************")
        System.out.println("")

        if (!createExtensions(project)) {
            System.err.println("The Plugin init error, please check the CloudPluginData{}")
            System.err.println("You need to create the CloudPluginData{} first and then rely on the remote plug-in：apply plugin: 'com.cloud.buildjar'")
            throw new IllegalAccessError("Check the build log")
        }
        createTask(project)
    }

    static boolean createExtensions(Project project) {
        project.getExtensions().create("buildJar", BuildJarExtension)
        BuildJarExtension buildJarExtension = project.buildJar
        try {
            Object object = project.CloudPluginData
            DataConverter.converter(buildJarExtension, object,project)
            return true
        } catch (Throwable throwable) {
            System.err.println(throwable.getMessage())
        }
        return false
    }

    static void createTask(Project project) {
        BuildJarExtension buildJarExtension = project.buildJar
        String group = buildJarExtension.group

        ClearJarTask clearJarTask = project.tasks.create(name: "clearJarTask", group: group, type: ClearJarTask)
        BuildJarTask buildJarTask = project.tasks.create(name: "buildJar", group: group, type: BuildJarTask)
        ProGuardJarTask proGuardJarTask = project.tasks.create(name: "proGuardJarTask", group: group, type: ProGuardJarTask)

        clearJarTask.dependsOn(buildJarExtension.doFirstTask)
        buildJarTask.dependsOn(clearJarTask)
        proGuardJarTask.dependsOn(buildJarTask)
    }
}