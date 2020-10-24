package com.chx.pluginlib.utils

import org.gradle.api.Project

/**
 * @author: cangHX* on 2020/06/15  13:45
 */
class JarUtils {

    static List<File> getJarsFromPaths(String[] paths, Project project) {
        List<File> files = new ArrayList<>()
        if (paths == null || paths.length == 0) {
            return files
        }
        paths.each { path ->
            File pathFile = new File(path)
            if (pathFile.exists()) {
                if (path.endsWith(File.separator)) {
                    addFromDir(pathFile, files, project)
                } else if (path.endsWith("aar")) {
                    addAAR(pathFile, files, project)
                } else if (path.endsWith("jar")) {
                    addJAR(pathFile, files)
                }
            } else {
                System.err.println("The file is empty : " + path)
            }
        }

        return files
    }

    private static void addFromDir(File dirFile, List<File> files, Project project) {
        dirFile.traverse { file ->
            String path = file.path
            if (path.endsWith("aar")) {
                addAAR(file, files, project)
            } else if (path.endsWith("jar")) {
                addJAR(file, files)
            }
        }
    }

    private static void addAAR(File aarFile, List<File> files, Project project) {
        for (File file : project.rootProject.zipTree(aarFile).getFiles()) {
            if (file.getName().endsWith(".jar")) {
                addJAR(file, files)
            }
        }
    }

    private static void addJAR(File jarFile, List<File> files) {
        files.add(jarFile)
    }

}
