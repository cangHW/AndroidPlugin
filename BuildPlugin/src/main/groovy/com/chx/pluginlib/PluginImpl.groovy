package com.chx.pluginlib

import com.chx.pluginlib.extensions.BuildJarExtension
import com.chx.pluginlib.task.BuildJarTask
import com.chx.pluginlib.task.ClearJarTask
import com.chx.pluginlib.task.ProGuardJarTask
import com.chx.pluginlib.utils.DataConverter
import com.chx.pluginlib.xml.PluginXmlParser
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

/**
 * @author: cangHX* on 2020/06/15  10:23
 */
class PluginImpl implements Plugin<Project> {
    @Override
    void apply(Project project) {

        println("************************** PLUGIN BUILD-JAR START **************************")
        println("")

        if (!createExtensions(project)) {
            System.err.println("The Plugin init error, please check the CloudPluginData{}")
            System.err.println("You need to create the CloudPluginData{} first and then rely on the remote plug-in：apply plugin: 'com.cloud.buildjar'")
            throw new IllegalAccessError("Check the build log")
        }

        println("")

        PluginXmlParser.parser(project)
        createTask(project)

        println("************************** PLUGIN BUILD-JAR END **************************")
        println("")
    }

    static boolean createExtensions(Project project) {
        project.getExtensions().create("buildJarExtension", BuildJarExtension)
        BuildJarExtension buildJarExtension = project.buildJarExtension
        try {
            Object object = project.CloudPluginData
            DataConverter.converter(buildJarExtension, object, project)
            return true
        } catch (Throwable throwable) {
            System.err.println(throwable.getMessage())
        }
        return false
    }

    static void createTask(Project project) {
        BuildJarExtension buildJarExtension = project.buildJarExtension
        String group = buildJarExtension.group

        def pluginTasks = []

        ClearJarTask clearJarTask = project.tasks.create(name: "clearJarTask", group: group, type: ClearJarTask)
        pluginTasks.add(clearJarTask)

        if (buildJarExtension.inputJarPath.length != 0) {
            BuildJarTask buildJarTask = project.tasks.create(name: "buildJarTask", group: group, type: BuildJarTask)
            pluginTasks.add(buildJarTask)
        } else {
            System.err.println("inputJarPath cannot be null")
            throw new IllegalAccessError("Check the inputJarPath")
        }

        ProGuardJarTask proGuardJarTask = project.tasks.create(name: "proGuardJarTask", group: group, type: ProGuardJarTask)
        pluginTasks.add(proGuardJarTask)

        Task currentTask = null
        for (int i = 0; i < pluginTasks.size(); i++) {
            Task it = (Task) pluginTasks[i]

            if (currentTask == null) {
                it.dependsOn(buildJarExtension.doFirstTask)
            } else {
                it.dependsOn(currentTask)
            }
            currentTask = it
        }
    }
}