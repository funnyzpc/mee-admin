
### 后台管理系统(MEE)

#### 主要技术栈
+ SpringBoot 2.2.9
+ MyBatis
+ Freemarker
+ Postgresql
+ Shiro
+ 

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
+ <del>导入导出功能模块缺失(finished)</del>
+ 输入框自动带出优化
+ bootstrap弹出框设计及构建
+ websocket消息推送功能
+ 功能开发文档编写
+ <del>多数据源功能添加(finished)</del>
+ <del>日志(基础或业务)记录功能(finished)</del>
+ <del>日志记录功能完善(finished)</del>
+ JacksonUtil序列化安全问题
+ <del>mybatis逻辑分页问题(finished)</del>
+ Controller params support LocalDateTime
+ 密码安全性问题
+ <del>DefaultWebSessionManager需要用ShiroWebSessionManager重写(finished)</del>
+ <del>writeCellValueDefault switch 替代if(finished)</del>
+ <del>分页逻辑优化(finished)</del>
+ 分页缓存
+ <del>统一SQL调用方法(finished)</del>
+ 数据库密码加密
+ 角色用户查询
+ 角色菜单查询  
