package com.chx.pluginlib.base

import org.gradle.api.Task
import org.gradle.api.specs.Spec
import org.gradle.api.tasks.bundling.Jar

/**
 * @author: cangHX* on 2020/06/15  10:54
 */
class BaseJar extends Jar {

    BaseJar() {
        getOutputs().upToDateWhen(new Spec<Task>() {
            @Override
            boolean isSatisfiedBy(Task task) {
                return false
            }
        })
    }

}
