package com.chx.pluginlib.plugin.bean

class SelfResolvingDependencyInfo extends BaseDependencyInfo {
    String buildDependencies
    String targetComponentId

    @Override
    String toString() {
        return "SelfResolvingDependencyInfo {" +
                "configuration=" + configuration + "," +
                "buildDependencies=" + buildDependencies + "," +
                "targetComponentId=" + targetComponentId + "," +
                "}"
    }
}