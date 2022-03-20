package com.chx.pluginlib.plugin.task

import com.chx.pluginlib.plugin.bean.ProjectDependencyInfo
import com.chx.pluginlib.plugin.utils.DependencyUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class DependencyTask extends DefaultTask {

    ProjectDependencyInfo projectDependencyInfo

    @TaskAction
    public void checkDependencies() {
        projectDependencyInfo = DependencyUtils.parser("", project)
    }


    public ProjectDependencyInfo getProjectDependencyInfo() {
        return projectDependencyInfo
    }

}