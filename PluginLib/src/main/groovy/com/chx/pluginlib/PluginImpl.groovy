package com.chx.pluginlib

import com.chx.pluginlib.extensions.BuildJarExtension
import com.chx.pluginlib.task.BuildJarTask
import com.chx.pluginlib.task.ClearJarTask
import com.chx.pluginlib.task.ProGuardJarTask
import com.chx.pluginlib.utils.DataConver
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author: cangHX* on 2020/06/15  10:23
 */
class PluginImpl implements Plugin<Project> {
    @Override
    void apply(Project project) {
        createExtensions(project)
        createTask(project)
    }

    static void createExtensions(Project project) {
        project.getExtensions().create("buildJar", BuildJarExtension)
        BuildJarExtension buildJarExtension = project.buildJar
        Object object = project.BuildJar
        DataConver.converter(buildJarExtension,object)
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