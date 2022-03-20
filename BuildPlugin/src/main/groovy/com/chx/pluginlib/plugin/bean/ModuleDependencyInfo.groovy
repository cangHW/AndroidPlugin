package com.chx.pluginlib.plugin.bean

class ModuleDependencyInfo extends BaseDependencyInfo {

    String group
    String name
    String version

    @Override
    String toString() {
        return "ModuleDependencyInfo {" +
                "configuration=" + configuration + "," +
                "group=" + group + "," +
                "name=" + name + "," +
                "version=" + version + "," +
                "}"
    }
}