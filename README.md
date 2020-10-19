
### mee-admin

#### Preface

```
  这是一个开放的时代，我们不能总是把东西揣在口袋里面自己乐呵。
  正如名言所说的“如果你有两块面包，你当用其中一块去换一朵水仙花”
  所以，继上一次把我的两个个人项目开源之后今天我再一次把自有的后台页面也开源出来，以回馈整个开源世界。
```

#### Quick start

+ Fork [mee-admin](https://github.com//funnyzpc/mee-admin/fork) to your repository
+ git clone  `your fork project address `
+ add this to your idea _Program arguments_ `--spring.profiles.active=dev`
+ startup MEE in `MeeApplication`

#### 项目结构概述

  mee-admin是由我的个人`mee`项目开源而来,`mee-admin`项目是一个前后端一体化的项目,不过在代码上实现了页面与数据分离，是一个非常好的
  轻量级后端工程，所以在正式使用时您会发现主体业务部门均是采用json交互，前端页面使用模板工具实现数据展现及编辑
  与`jeesite`不一样，我们不使用`jsp+sitmesh+ehcache`臃肿化项目
  与`Spring-Cloud-Platform` `xboot` 不一样,这里不使用`vue` `iview` 做前后端分离，也不使用`springclooud`做集群分布式
  所以我的项目更加轻量级，不需要装`node` 不需要`npm`打包 需不要安装`nginx` 同时也不需要编写无聊的mapper接口，不需要单独写增删改....
  所以对于企业内部需求开发更是无比的急速
  同时，`mee-admin`只需具有`java`后端以及一点点`javascript`开发能力，便可急速上手。

#### 项目技术相关

+ 使用`springboot 2.3.4.RELEASE`作为基础框架
+ 使用`mybatis`作为`dao`框架
+ 使用`postgreSQL` 作为框架DB(可支持`Mysql`及`Oracle`)
+ 使用`shiro`做权限管理
+ 使用`Freemarker`做页面模板
+ 使用`jquery` 插件作`javascript`基本扩展库使用
  - 目前只是一些组件依赖用,建议大多数情况下使用`ES5`或`ES6`规范的`javascript`扩展
+ 使用`handlebars`做表单及数据模板
+ 使用`seajs` 做基础模块管理
+ 封装了序列(`ID`)生成器(支持分布式)
    - `SeqGenServiceImpl` 序列生成器(支持分布式)
    - `SeqGenUtil` 普通序列生成器
+ 封装了`Jackson`的`json`库，完全可替代`fastjson`
+ 封装了物理分页`PhysicalPageInterceptor`及逻辑分页`LogicalPageIntercepter`(两个可任选其一)，完全替代`RowBounds`及一众分页依赖
+ 封装了`Excel`及`CSV`工具
  - `ExcelReadUtil` EXCEL读工具
  - `ExcelWriteUtil` EXCEL写工具
  - `CSVUtils` CSV读工具
+ 简单封装了java8日期工具类 `DateUtil`

#### IDE start config
+ --spring.profiles.active=dev

#### packaging
+ development environment
    - `mvn clean -Dmaven.test.skip=true package -Pdev`
    
+ test environment
    - `mvn clean -Dmaven.test.skip=true package -Ptest`

+ product environment
    - `mvn clean -Dmaven.test.skip=true package -Pprod`

#### deploy script
>>> local(windows) deploy
+ ` java -jar mee.jar --server.port=8001 `

>>> test deploy
+ `echo 正在启动mee模块.....`
+ `ps -ef|grep mee.jar|grep java|awk '{print $2}'|xargs kill -9`
+ `cd /mnt/app/8001-mee && nohup /usr/local/java/jdk1.8.0_261/bin/java -jar /mnt/app/8001-mee/mee.jar --server.port=8001 --spring.profiles.active=test  1>/mnt/app/8001-mee/logs/mee_ALL.log 2>/mnt/app/8001-mee/logs/mee_ALL.log &`

>>> prod deploy[TODO need edit](#)
+ `echo 正在启动mee模块.....`
+ `ps -ef|grep mee.jar|grep java|awk '{print $2}'|xargs kill -9`
+ `cd /mnt/app/8001-mee && nohup /usr/local/java/jdk1.8.0_261/bin/java -jar /mnt/app/8001-mee/mee.jar --server.port=8001 --spring.profiles.active=test  1>/mnt/app/8001-mee/logs/mee_ALL.log 2>/mnt/app/8001-mee/logs/mee_ALL.log &`

#### 功能模块
+ 系统及全局配置
    - 日志管理(开发中)
    - 字典配置(完成)
    - 系统监控(开发中)
    - 完善shiro功能(完成)
    - 优化页面嵌套(完成)
    - 优化表结构(完成)
    — 添加DAO逻辑(完成)

+ 用户及菜单管理
    - 菜单管理
    - 用户管理[new](#)
    - 角色管理[new](#)
    - 用户角色管理(开发中)
    - 角色菜单管理(开发中)

#### 需要说明
+ 对于前端
    - 使用handlebar作为模板
    - 使用seajs作为模块管理工具
    - 基本增删改查参考tablex
    
    
+ 对于后端
    - 使用springboot作为基础框架
    — 使用jdk8作为应用运行环境
    - 使用mybatis作为DAO层(仅仅使用)

+ 开发参考
  - [完整的例子](https://github.com/zhaojiatao/springboot-zjt-chapter10-springboot-mysql-mybatis-shiro-freemarker-layui)
  - [freemarker+shiro相关](https://github.com/fuce1314/Springboot_v2)
  - [前后端结合](https://github.com/enilu/web-flash)
  - [shiro权限参考](https://www.cnblogs.com/Jimc/p/10031094.html)

#### 最新进度
 + new 2020-09-18

#### Issues and inprove
+ 输入框自动带出优化
+ bootstrap弹出框设计及构建
+ websocket消息推送功能
+ 功能开发文档编写
+ JacksonUtil序列化安全问题
+ Controller params support LocalDateTime
+ 密码安全性问题
+ 分页缓存
+ 数据库密码加密
+ 角色用户查询
+ 角色菜单查询  
