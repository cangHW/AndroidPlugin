<br/>

```
    Android 混淆打包插件，主要用于打 JAR、AAR、ZIP
```

<br/>

[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](https://www.apache.org/licenses/LICENSE-2.0)

<br/>

| 最新版本号 | 地址 |
| :--: | :--: |
| 1.0.0 | com.chx.plugin:buildJar:"版本号" |

# 一、提供功能

1. 清除无用文件
2. 合并并导出 JAR 包
3. 对 JAR 包混淆

# 二、添加依赖和配置

在工程根目录中的 build.gradle 中添加

    buildscript {
            repositories {
                google()
                mavenCentral()
            }
            dependencies {
                classpath 'com.android.tools.build:gradle:X.X.X'
                //添加插件依赖
                classpath 'io.github.cangHW:buildJar:X.X.X'
                .
                .
                .
        
                // NOTE: Do not place your application dependencies here; they belong
                // in the individual module build.gradle files
            }
        }
        
# 三、使用方式      

## 1、配置参数
在准备执行脚本的模块中的 build.gradle 中添加，参数可选，按需添加

    ext.CloudPluginData = [
        //任务组名称，默认为：pluglib
        group   : 'aa',
        .
        .
        .
    ]
    
| key | value | 作用 |
| :--: | :--: | :--: |
| group | 字符串："" | 任务组名称，默认为：pluglib |
| doFirstTask | 数组：[] | 前置任务，插件执行之前需要提前执行的任务。</br> 主要用于生成 JAR 包合并，在合并前生成 JAR 包 |
| clearPath | 数组：[] | 用于无用文件清理,插件执行时，会先删除这些路径下的文件 |
| excludesFromJar | 数组：[] | 用于过滤某些不想打进 JAR 包中的文件 |
| inputJarPath | 数组：[] | 需要执行导出 JAR 包任务的 JAR / AAR 位置，多个地址会触发合并操作，最终只会生成一个 JAR |
| jarName | 字符串："" | 导出 JAR 包的名字，默认为：buildJar.jar |
| outputJarPath | 字符串："" | JAR 包导出路径，默认为：**/build/jar/ |
| inputProGuardJarPath | 字符串："" | 准备混淆的 JAR 包位置，默认为 JAR 包的导出位置，即：outputJarPath + jarName |
| proGuardJarName | 字符串："" | 混淆后 JAR 包的名称，默认为：jarName + '-ProGuard.jar' |
| outputProGuardJarPath | 字符串："" | 混淆后 JAR 包的位置，默认为 JAR 包的导出位置，即：outputJarPath |
| proGuardLibrarys | 数组：[] | 所依赖三方库的本地路径，方便用于混淆时，完善混淆规则，如果混淆文件配置合理，此处可以不传 |
| isShrink | 布尔：true / false | 混淆时，是否移除无用资源，true：移除；false：不移除，默认为：true |
| rulesPath | 字符串："" | 混淆文件的名称，自动选择当前 module 下的对应名称的混淆文件，默认为：proguard-rules.pro |

## 2、引用插件
需要在配置完参数之后引用插件，插件被引用后，会自动使用配置的参数

    apply plugin: 'com.cloud.buildjar'

## 3、使用脚本
目前共有脚本为：

| 脚本名称 | 作用 |
| :--: | :--: |
| clearJarTask | 根据配置参数，清理无用文件 |
| buildJar | 根据配置参数，导出 JAR 包 |
| proGuardJarTask | 根据配置参数，对 JAR 包进行混淆 |

根据常用习惯，目前脚本已经添加的执行顺序有：
</br>

1. clearJarTask ——> buildJar ——> proGuardJarTask。

当执行某一个任务时，会根据顺序，自动执行前置任务。</br>
例如：只需要导出 JAR 包，则只需执行对应模块下面的 buildJar 任务即可，buildJar 任务执行前会自动执行 clearJarTask 任务，方便开发者删除上次生成的 jar 包。
</br>
例如：只需要对 JAR 包进行混淆，则在参数配置中，只配置混淆相关的参数即可

# 四、常见问题

##问题一

提示：Check the build log

    Could not get unknown property 'CloudPluginData' for project ':Test:BuildJar' of type org.gradle.api.Project.
    The Plugin init error, please check the CloudPluginData{}
    You need to create the CloudPluginData{} first and then rely on the remote plug-in：apply plugin: 'com.cloud.buildjar'

原因：
    
    没有配置参数，或者配置参数在引用插件之后
    
解决方案：
    
    修改代码顺序，先配置参数，后引用插件
    
    ext.CloudPluginData = [
        //任务组名称，默认为：pluglib
        group   : 'aa',
        .
        .
        .
    ]
    apply plugin: 'com.cloud.buildjar'

# 五、交流学习

    QQ   :  1163478116
    微信  :  1163478116