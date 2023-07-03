
## 开源后台管理系统(MEE-ADMIN)
 这是一套由本人构建的后台系统，从0开始都是我自己写的，其中v1.5是从2022年12月份开始一直开发现在(2023年7月3日)，这个项目耗费了我太多心血❤,如果您看到了觉得还行请点赞
如果能[FORK🎈](https://github.com//funnyzpc/mee/fork)那可太感谢了~😉

### 相较于1.0
+ 几乎重构了所有前端页面
+ 前端合理化布局及添加了复杂交互
+ 后端接口拆分及细致化权限管理
+ 移除了jQuery以及所有与jQuery相关的扩展插件
+ 添加了个人中心
+ 优化了字典配置及前端字段配置相关
+ 简化了表单验证
+ 添加了代码生成，简化开发难度（后续会提供）
+ 修复了分页bug
+ 等等...

### 代码生成
本项目配合`mee_generator`可大幅提高开发效率，从前端到后端可全部使用`mee_generator`生成，你可能需要做的只是测试（菜单也需要手动配置）而已~
_`mee_generator`_会在后续几天推出~


#### UI
>![...](./overview/1.png)
>![...](./overview/2.png)
>![...](./overview/3.png)
>![...](./overview/4.png)
>![...](./overview/5.png)

#### 主要技术栈
+ SpringBoot 2.6
+ MyBatis
+ Freemarker
+ Postgresql
+ Shiro
+ ShedLock
+ 等等...

#### quick start
+ Fork [MEE](https://github.com//funnyzpc/mee/fork) to your repository
+ git clone  `your fork project address `
+ add this to your idea _Program arguments_ `--spring.profiles.active=dev`
+ add this to your idea _VM options_ `-Djasypt.encryptor.password="0989GoEncc}{||>.<||}0101"`
+ startup in `MeeApplication`

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

>>> prod deploy
+ `echo 正在启动mee模块.....`
+ `ps -ef|grep mee.jar|grep java|awk '{print $2}'|xargs kill -9`
+ `cd /mnt/app/8001-mee && nohup /usr/local/java/jdk1.8.0_261/bin/java -jar /mnt/app/8001-mee/mee.jar --server.port=8001 --spring.profiles.active=test  1>/mnt/app/8001-mee/logs/mee_ALL.log 2>/mnt/app/8001-mee/logs/mee_ALL.log &`
