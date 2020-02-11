

# 多人博客BlogPlatform
[课程地址](https://coding.imooc.com/class/125.html)

## 项目介绍
基于Spring Boot技术栈的多人博客系统

## 功能模块
### 1.博文管理
可以发布博文，查看系统中的新博文，使用ueditor富文本编辑器。可以根据最新、最热分页查看博客列表
### 2.评论管理
可以对博文进行评论，也可以对评论进行评论，博文列表每篇博文的右侧都会显示评论数。
### 3.分类管理
博文的分类，发表博文时需要为其选定一个类别。
### 4.点赞管理
浏览博文时，发现最近喜欢的博文，可以对其按赞表示喜欢，博文列表每篇博文的右侧都会显示喜欢数。
### 5.标签管理
可以为博客添加标签。

## 技术架构
### 前端：
* HTML
* CSS
* Thymeleaf
* Bootstrap
* jQuery
### 后端：
* Spring
* Spring MVC
* Spring Data JPA
* Ehcache

## 系统效果图
* 登录页面，登录用户可以进行发布博文的操作
![alt 登录页面](https://raw.githubusercontent.com/captainxyw/BlogPlatform/master/img/login.png "登录页面")
* 个人页面，可以查看关注的人和粉丝等
![alt 个人页面](https://raw.githubusercontent.com/captainxyw/BlogPlatform/master/img/profile.png "个人页面")
* 别的用户的页面，可以对其进行关注
![alt 别的用户的页面](https://raw.githubusercontent.com/captainxyw/BlogPlatform/master/img/profile-other.png "别的用户的页面")
* 博文页面，分页展示系统中所有的博文，侧边展示了热门文章和最新发布的文章
![alt 博文页面](https://raw.githubusercontent.com/captainxyw/BlogPlatform/master/img/article-1.png "博文页面")
* 鸡汤页面，与博文类似，分页展示系统中所有的鸡汤，侧边展示了热门文章和最新发布的文章
![alt 鸡汤页面](https://raw.githubusercontent.com/captainxyw/BlogPlatform/master/img/article-2.png "鸡汤页面")
* 博文发布页面，写博文，为其添加分类和标签，进行发布
![alt 博文发布页面](https://raw.githubusercontent.com/captainxyw/BlogPlatform/master/img/article-release.png "博文发布页面")


