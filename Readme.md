
##### EasyFrame — Android快速集成开发框架(欢迎star)

## EasyFrame简介

**EasyFrame**是一个Android开发的工具集合，一句代码就能集成到你项目中，该集成了一个开发app常使用的工具，包含有
网络请求，一般常用的工具，日志输出，加载界面的状态，权限管理等，具体详情请看下面的介绍。

[详细文档请前往wiki](https://github.com/haoxiongqin/EasyFrame_master/wiki)

## 特性

**EasyFrame**主要有这些功能：

- [x] `JHttp`：Http网络请求隔离框架，解决高耦合！一行代码随意切换第三方网络请求库，可自动解析json
- [x] `JLog`：查看的日志，支持Json、Xml、Map、List等格式输出，可全局配置
- [x] `JLoadingView`：简单实用的页面状态统一管理 ，加载中、无网络、无数据、出错等状态的随意切换
- [x] `JLoadingDialog`：简单实现加载等待对话框
- [x] `JPermission`：简化Android动态权限管理的操作
- [x] `BaseQuickAdapter`：一个用于RecyclerView Adapter的开发库，包含加载现实动画，添加Header，Footer，加载更多,加载失败，加载到底和支持多种布局
- [x] `JCache`：缓存普通的字符串、Bitmap、Drawable、Serializable的java对象、byte数据
- [x] `JStatusBar`：实现沉浸式状态栏
- [x] `JToast`：简单的吐司使用
- [x] `Utils工具类集合`：内置常用工具类
- [x] `自定义View`：内置常用自定义控件
- [x] `JFrangment`： 解决与viewpager和通过FragmentTransaction的show和hide的方法来控制显示，实现懒加载。

## 使用步骤

#### Step 1.依赖JFrame
Gradle 
```groovy
dependencies{
    compile 'com.wujing.jframe:jframe:1.1.0'
}
```
或者引用本地lib
```groovy
compile project(':jframe')
```



## 更新说明

#### v1.1.1
   JFrame 优化更新
 * 增加Http网络隔离框架
 * 增加BaseQuickAdapter加载的6大动画实现,以及自己自定义动画
 * 修改优化了一些bug



#### v1.1.0
   JFrame 优化更新
 * 增加Http网络隔离框架
 * 修改优化了一些bug




## License

```  
Copyright 2017 wujing

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```