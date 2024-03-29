package com.chx.pluginlib.buildjar.task

import com.chx.pluginlib.base.BaseDelete
import com.chx.pluginlib.buildjar.extensions.BuildJarExtension

/**
 * @author: cangHX
 * on 2020/06/15  11:43
 */
class ClearJarTask extends BaseDelete {

    @Override
    protected void clean() {
        BuildJarExtension buildJarExtension = project.buildJarExtension
        delete(buildJarExtension.getClearPath())
        super.clean()
    }
}
