package com.chx.pluginlib.base

import org.gradle.api.Project
import org.gradle.api.specs.Spec
import org.gradle.api.tasks.Copy

/**
 * @author: cangHX
 * on 2020/06/15  18:24
 */
class BaseCopy extends Copy {

    BaseCopy(){
        getOutputs().upToDateWhen {new Spec<Project>(){
            @Override
            boolean isSatisfiedBy(Project project) {
                return false
            }
        }}
    }

}
