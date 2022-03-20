package com.chx.pluginlib.plugin

import com.chx.pluginlib.constants.Constants
import com.chx.pluginlib.plugin.task.DependencyTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class PluginImpl implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("************************** PLUGIN LIB START **************************")
        println("")

        createTask(project)

        println("************************** PLUGIN LIB END **************************")
        println("")
    }

    static void createTask(Project project) {
        project.tasks.create(name: 'checkDependencies', group: Constants.DEFAULT_GROUP, type: DependencyTask)
    }
}