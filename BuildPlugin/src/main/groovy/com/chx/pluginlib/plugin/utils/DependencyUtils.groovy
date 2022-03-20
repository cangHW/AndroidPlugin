package com.chx.pluginlib.plugin.utils

import com.chx.pluginlib.plugin.bean.BaseDependencyInfo
import com.chx.pluginlib.plugin.bean.ModuleDependencyInfo
import com.chx.pluginlib.plugin.bean.ProjectDependencyInfo
import com.chx.pluginlib.plugin.bean.SelfResolvingDependencyInfo
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency
import org.gradle.api.internal.artifacts.dependencies.DefaultProjectDependency
import org.gradle.api.internal.artifacts.dependencies.DefaultSelfResolvingDependency

class DependencyUtils {

    static ProjectDependencyInfo parser(String configuration, Project project) {
        ProjectDependencyInfo projectDependencyInfo = new ProjectDependencyInfo()
        projectDependencyInfo.setConfiguration(configuration)
        projectDependencyInfo.setProject(project)
        parserPorject(project, projectDependencyInfo.dependencyInfos)
        return projectDependencyInfo
    }

    private static void parserPorject(Project project, ArrayList<BaseDependencyInfo> dependencyInfos) {
        project.configurations.each { config ->
            config.dependencies.each { dependency ->
                parserDependency(config.getName(), dependency, dependencyInfos)
            }
        }
    }

    private static void parserDependency(String configuration, Dependency dependency, ArrayList<BaseDependencyInfo> dependencyInfos) {
        if (dependency instanceof DefaultExternalModuleDependency) {
            ModuleDependencyInfo moduleDependencyInfo = new ModuleDependencyInfo()
            moduleDependencyInfo.setConfiguration(configuration.getName())
            moduleDependencyInfo.setGroup(dependency.getGroup())
            moduleDependencyInfo.setName(dependency.getName())
            moduleDependencyInfo.setVersion(dependency.getVersion())
            dependencyInfos.add(moduleDependencyInfo)
        } else if (dependency instanceof DefaultProjectDependency) {
            dependencyInfos.add(parser(configuration, dependency.getDependencyProject()))
        } else if (dependency instanceof DefaultSelfResolvingDependency) {
            SelfResolvingDependencyInfo selfResolvingDependencyInfo = new SelfResolvingDependencyInfo()
            selfResolvingDependencyInfo.setConfiguration(configuration.getName())
            selfResolvingDependencyInfo.setBuildDependencies(dependency.getBuildDependencies())
            selfResolvingDependencyInfo.setTargetComponentId(dependency.getTargetComponentId())
            dependencyInfos.add(selfResolvingDependencyInfo)
        }
    }

}
