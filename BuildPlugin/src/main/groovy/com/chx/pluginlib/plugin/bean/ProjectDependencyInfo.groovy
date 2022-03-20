package com.chx.pluginlib.plugin.bean

import org.gradle.api.Project

class ProjectDependencyInfo extends BaseDependencyInfo {

    Project project

    ArrayList<BaseDependencyInfo> dependencyInfos = new ArrayList<>()

    @Override
    String toString() {
        return "ProjectDependencyInfo {" +
                "configuration=" + configuration + "," +
                "project=" + project + "," +
                "}"
    }
}