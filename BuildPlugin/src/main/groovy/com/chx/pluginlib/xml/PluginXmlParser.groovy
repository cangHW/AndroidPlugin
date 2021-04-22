package com.chx.pluginlib.xml

import com.chx.pluginlib.extensions.BuildJarExtension
import org.gradle.api.Project
import org.gradle.internal.os.OperatingSystem
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

class PluginXmlParser {
    static void parser(Project project) {
        BuildJarExtension buildJarExtension = project.buildJarExtension

        String path = project.rootDir.path + File.separator + ".idea" + File.separator + "libraries"
        File file = new File(path)
        if (file == null || !file.exists()) {
            return
        }
        Map<String, String> map = System.getenv()
        String gPath = ""
        if (OperatingSystem.current().isWindows()) {
            gPath = map.get("USERPROFILE", "")
        } else if (OperatingSystem.current().isMacOsX()) {
            gPath = map.get("HOME", "")
        }

        if (gPath == "") {
            return
        }

        println("gradle 缓存地址 : " + gPath)

        file.traverse { it ->
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance()
                DocumentBuilder builder = factory.newDocumentBuilder()
                Document document = builder.parse(new File(it.path))
                NodeList nodeList = document.getElementsByTagName("CLASSES")

                for (int i = 0; i < nodeList.length; i++) {
                    Node node = nodeList.item(i)
                    if (node.getNodeType() != Node.ELEMENT_NODE) {
                        continue
                    }
                    Element element = node
                    NodeList roots = element.getElementsByTagName("root")

                    for (int j = 0; j < roots.length; j++) {
                        Node root = roots.item(j)
                        if (root.getNodeType() != Node.ELEMENT_NODE) {
                            continue
                        }
                        Element rootElement = root
                        String url = rootElement.getAttribute("url")
                        if (!url.startsWith("jar://")) {
                            continue
                        }
                        url = url.replace("jar://", "")
                        url = url.replace(".jar!/", ".jar")
                        if (url.startsWith("\$USER_HOME\$")) {
                            url = url.replace("\$USER_HOME\$", gPath)
                        }
                        if (url.startsWith("\$PROJECT_DIR\$")) {
                            url = url.replace("\$PROJECT_DIR\$", project.rootDir.path)
                        }

                        println("发现依赖文件地址 : " + url)
                        buildJarExtension.proGuardLibrarys.add(url)
                    }
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace()
            }
        }
    }
}