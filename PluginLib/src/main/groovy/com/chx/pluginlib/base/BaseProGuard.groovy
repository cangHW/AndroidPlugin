package com.chx.pluginlib.base

import org.gradle.api.specs.Spec
import proguard.gradle.ProGuardTask

/**
 * @author: cangHX* on 2020/06/15  10:57
 */
class BaseProGuard extends ProGuardTask {

    BaseProGuard() {
        getOutputs().upToDateWhen(new Spec() {
            @Override
            boolean isSatisfiedBy(Object o) {
                return false
            }
        })
    }

}
