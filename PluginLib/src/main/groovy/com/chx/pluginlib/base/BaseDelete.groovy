package com.chx.pluginlib.base

import org.gradle.api.Task
import org.gradle.api.specs.Spec
import org.gradle.api.tasks.Delete

/**
 * @author: cangHX* on 2020/06/15  11:44
 */
class BaseDelete extends Delete {

    BaseDelete() {
        getOutputs().upToDateWhen(new Spec<Task>() {
            @Override
            boolean isSatisfiedBy(Task task) {
                return false
            }
        })
    }

}
