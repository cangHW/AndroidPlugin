package com.chx.pluginlib.task

import com.chx.pluginlib.base.BaseJar
import com.chx.pluginlib.extensions.BuildJarExtension
import com.chx.pluginlib.utils.JarUtils

/**
 * @author: cangHX* on 2020/06/15  10:53
 */
class BuildJarTask extends BaseJar {

    BuildJarTask(){
        BuildJarExtension buildJarExtension = project.buildJar

        archiveFileName.set(buildJarExtension.getJarName()+".jar")

        destinationDirectory.set(new File(buildJarExtension.getOutputJarPath()))
//        destinationDirectory.set(project.file(new File(buildJarExtension.outputJarPath)))
//        archiveFile.set(destinationDirectory.file(archiveFileName))

        println("setJarParams   "+"+++++++++++++++"+"  into  success ")

        println("archiveName   "+"+++++++++++++++"+"  into  success "+archiveName)
        println("archivePath   "+"+++++++++++++++"+"  into  success "+archivePath)
    }

    @Override
    protected void copy() {
        BuildJarExtension buildJarExtension = project.buildJar
        from(getFileTree(buildJarExtension.getInputJarPath()))
        println("setJarParams   "+"+++++++++++++++"+"  from  success ")
        super.copy()
    }

    List<File> getFileTree(String[] paths) {
        List<File> files = new ArrayList<>()

        List<File> jarFiles = JarUtils.getJarsFromPaths(paths, project)

        println("setJarParams   "+"+++++++++++++++"+"  getFileTree  success " +jarFiles)
        for (File jarFile : jarFiles) {
            def file = project.rootProject.zipTree(jarFile)["asFileTrees"].get(0).getDir()
            files.add(file)
        }

        return files
    }

}