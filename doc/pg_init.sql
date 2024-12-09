
DROP TABLE  IF EXISTS mee_admin.sys_menu;
CREATE TABLE mee_admin.sys_menu (
  "id" INT8 primary key,
  "pid" INT8,
  "type" INT2 NOT NULL,
  "title" VARCHAR(100) NOT NULL,
  "icon" VARCHAR(50),
  "path" VARCHAR(100) NOT NULL default '',
  "target" VARCHAR(100),
  "permission" VARCHAR(80),
  "sub_count" INT4,
  "show" INT2 NOT NULL,
  "sort" INT4 DEFAULT '1',
  "create_time" TIMESTAMP,
  "create_by" VARCHAR(64),
  "update_time" TIMESTAMP,
  UPDATE_BY VARCHAR(64)
);

COMMENT ON COLUMN mee_admin.sys_menu."id" IS '菜单';
COMMENT ON COLUMN mee_admin.sys_menu."pid" IS '父ID';
COMMENT ON COLUMN mee_admin.sys_menu."type" IS '菜单类型(1.目录 2.菜单 3.按钮)';
COMMENT ON COLUMN mee_admin.sys_menu."title" IS '菜单名称';
COMMENT ON COLUMN mee_admin.sys_menu."icon" IS '菜单图标';
COMMENT ON COLUMN mee_admin.sys_menu."path" IS '路径(不含context-path 且于/开始)';
COMMENT ON COLUMN mee_admin.sys_menu."target" IS '_blank.在新窗口中打开 _self.当前框架中打开 _parent.在父框架中打开 _top.在整个窗口中打开 _content.在右侧框架中打开';
COMMENT ON COLUMN mee_admin.sys_menu."permission" IS '权限标识(菜单或按钮，具体用于权限管控的对象)';
COMMENT ON COLUMN mee_admin.sys_menu."sub_count" IS '子级个数(含隐藏的 show=1)';
COMMENT ON COLUMN mee_admin.sys_menu."show" IS '是否显示(0:否 1:是)';
COMMENT ON COLUMN mee_admin.sys_menu."sort" IS '排序(1开始 越大越靠前)';
COMMENT ON COLUMN mee_admin.sys_menu."pid" IS '父ID';
COMMENT ON COLUMN mee_admin.sys_menu."create_time" IS '创建时间';
COMMENT ON COLUMN mee_admin.sys_menu."create_by" IS '创建人';
COMMENT ON COLUMN mee_admin.sys_menu."update_time" IS '创建时间';
COMMENT ON COLUMN mee_admin.sys_menu."update_by" IS '创建人';
COMMENT ON TABLE  mee_admin.sys_menu IS '系统::新菜单表';

INSERT INTO sys_menu (id,pid,"type",title,icon,"path",target,"permission",sub_count,"show",sort,create_time,create_by,update_time,update_by) VALUES
	 (40205,402,2,'定时任务',NULL,'/sys/sys_shedlock_app','_content','sys:sys_shedlock:list',4,1,5,'2020-10-19 05:46:22.000','1','2024-06-19 11:07:38.127','1'),
	 (2406191107381000,40205,3,'添加','','','','sys:sys_shedlock:add',0,0,4,'2024-06-19 11:07:38.127','1','2024-06-19 11:07:38.127','1'),
	 (402,4,1,'基础管理',NULL,'',NULL,NULL,5,1,2,'2020-11-27 03:09:36.788','sys','2024-06-18 16:50:50.132','1'),
	 (20701,207,2,'线下店铺',NULL,'/sv1/t_offline_store','_content','sv1:t_offline_store:list',6,1,2,'2020-11-27 03:45:34.000','admin','2024-03-12 14:20:00.917','1'),
	 (2403121419441000,20701,3,'导入','','','_self','sv1:t_offline_store:import',0,0,6,'2024-03-12 14:19:44.728','1','2024-03-12 14:20:00.917','1'),
	 (2306161645031000,20701,3,'导出','','','','sv1:t_offline_store:export',0,0,5,'2023-06-16 16:45:03.568','1','2023-06-16 16:45:03.568','1'),
	 (207,2,1,'基础信息',NULL,'',NULL,NULL,5,1,NULL,'2020-11-27 03:45:34.000','admin','2023-06-16 14:33:09.886','1'),
	 (2306161418111074,207,2,'其他','','https://cn.bing.com/','_content','',0,1,5,'2023-06-16 14:18:11.119','1','2023-06-16 14:33:09.886','1'),
	 (2306161419041075,2,1,'地区','','','','',2,1,1,'2023-06-16 14:19:04.635','1','2023-06-16 14:19:04.635','1'),
	 (2,0,1,'业务系统1',NULL,'',NULL,NULL,4,1,2,'2020-11-27 03:09:24.253','sys','2023-06-16 14:19:04.635','1');
INSERT INTO sys_menu (id,pid,"type",title,icon,"path",target,"permission",sub_count,"show",sort,create_time,create_by,update_time,update_by) VALUES
	 (205,2306161419041075,1,'店铺日报',NULL,'',NULL,NULL,3,0,4,'2020-11-27 03:44:24.000','admin','2023-06-16 14:16:04.567','1'),
	 (2306161413241003,20701,3,'删除','','','','sv1:t_offline_store:delete',0,0,4,'2023-06-16 14:13:24.965','1','2023-06-16 14:13:24.965','1'),
	 (2306161412271002,20701,3,'修改','','','','sv1:t_offline_store:update',0,0,3,'2023-06-16 14:12:27.063','1','2023-06-16 14:12:27.063','1'),
	 (2306161411461001,20701,3,'新增','','','','sv1:t_offline_store:add',0,0,2,'2023-06-16 14:11:46.224','1','2023-06-16 14:11:46.224','1'),
	 (2306161410011000,20701,3,'查询','','','','sv1:t_offline_store:list',0,0,1,'2023-06-16 14:10:01.206','1','2023-06-16 14:10:01.206','1'),
	 (2305312137051000,401,2,'用户配置','','/sys/sys_user','_content','sys:sys_user:list',9,1,1,'2023-05-31 21:37:05.980','1','2023-06-11 19:08:17.458','1'),
	 (2306111908171001,2305312137051000,3,'导出','','','','sys:sys_user:export',0,0,4,'2023-06-11 19:08:17.458','1','2023-06-11 19:08:17.458','1'),
	 (2306111900571000,0,3,'开发者','','','','dev',0,0,9,'2023-06-11 19:00:57.898','1','2023-06-11 19:00:57.898','1'),
	 (2306071126091063,40205,3,'刪除','','','','sys:sys_shedlock:delete',0,0,3,'2023-06-07 11:26:09.187','1','2023-06-07 11:26:09.187','1'),
	 (2306071125291062,40205,3,'更新','','','','sys:sys_shedlock:update',0,0,2,'2023-06-07 11:25:29.340','1','2023-06-07 11:25:29.340','1');
INSERT INTO sys_menu (id,pid,"type",title,icon,"path",target,"permission",sub_count,"show",sort,create_time,create_by,update_time,update_by) VALUES
	 (2306071125011061,40205,3,'查詢','','','','sys:sys_shedlock:list',0,0,1,'2023-06-07 11:25:01.987','1','2023-06-07 11:25:29.340','1'),
	 (2306040014281000,402,2,'系统信息','','/sys/server_info','_content','sys:server_info:list',0,1,4,'2023-06-04 00:14:28.856','1','2023-06-07 11:24:11.281','1'),
	 (40202,402,2,'日志配置',NULL,'/sys/sys_log','_content','sys:sys_log:list',3,1,3,'2020-11-27 03:09:52.519','sys','2023-06-07 11:23:17.615','1'),
	 (2306071123171060,40202,3,'導出','','','','sys:sys_log:export',0,0,3,'2023-06-07 11:23:17.615','1','2023-06-07 11:23:17.615','1'),
	 (2306071122481059,40202,3,'刪除','','','','sys:sys_log:delete',0,0,2,'2023-06-07 11:22:48.462','1','2023-06-07 11:22:48.462','1'),
	 (2306071122221058,40202,3,'查詢','','','','sys:sys_log:list',0,0,1,'2023-06-07 11:22:22.270','1','2023-06-07 11:22:22.270','1'),
	 (40204,402,2,'文件管理',NULL,'/sys/sys_file','_content','sys:sys_file:list',2,1,2,'2020-09-21 08:33:16.000','1','2023-06-07 11:20:45.390','1'),
	 (2306071120451057,40204,3,'刪除','','','','sys:sys_file:delete',0,0,2,'2023-06-07 11:20:45.390','1','2023-06-07 11:20:45.390','1'),
	 (2306071120161056,40204,3,'查詢','','','','sys:sys_file:list',0,0,1,'2023-06-07 11:20:16.655','1','2023-06-07 11:20:16.655','1'),
	 (2306071118301055,2305252217261000,3,'刪除','','','','sys:sys_dict:delete',0,0,4,'2023-06-07 11:18:30.342','1','2023-06-07 11:18:30.342','1');
INSERT INTO sys_menu (id,pid,"type",title,icon,"path",target,"permission",sub_count,"show",sort,create_time,create_by,update_time,update_by) VALUES
	 (2305252217261000,402,2,'字典配置','','/sys/sys_dict','_content','sys:sys_dict:list',4,1,1,'2023-05-25 22:17:26.898','1','2023-06-07 11:18:30.342','1'),
	 (2306071118051054,2305252217261000,3,'更新','','','','sys:sys_dict:update',0,0,3,'2023-06-07 11:18:05.249','1','2023-06-07 11:18:05.249','1'),
	 (2306071117421053,2305252217261000,3,'添加','','','','sys:sys_dict:add',0,0,2,'2023-06-07 11:17:42.354','1','2023-06-07 11:17:42.354','1'),
	 (2306071117001052,2305252217261000,3,'查詢','','','','sys:sys_dict:list',0,0,1,'2023-06-07 11:17:00.943','1','2023-06-07 11:17:00.943','1'),
	 (2306071104431051,2305312137051000,3,'角色信息','','','','sys:sys_user:get_roles',0,0,8,'2023-06-07 11:04:43.863','1','2023-06-07 11:04:43.863','1'),
	 (2306071103581050,2305312137051000,3,'重置密碼','','','','sys:sys_user:reset_pwd',0,0,7,'2023-06-07 11:03:58.029','1','2023-06-07 11:03:58.029','1'),
	 (2306071103001049,2305312137051000,3,'解鎖操作','','','','sys:sys_user:unlock',0,0,6,'2023-06-07 11:03:00.876','1','2023-06-07 11:03:00.876','1'),
	 (2306071102041048,2305312137051000,3,'狀態切換','','','','sys:sys_user:change_status',0,0,5,'2023-06-07 11:02:04.269','1','2023-06-07 11:02:04.269','1'),
	 (2306071101221047,2305312137051000,3,'刪除','','','','sys:sys_user:delete',0,0,4,'2023-06-07 11:01:22.556','1','2023-06-07 11:01:22.556','1'),
	 (2306071100531046,2305312137051000,3,'更新','','','','sys:sys_user:update',0,0,3,'2023-06-07 11:00:53.023','1','2023-06-07 11:00:53.023','1');
INSERT INTO sys_menu (id,pid,"type",title,icon,"path",target,"permission",sub_count,"show",sort,create_time,create_by,update_time,update_by) VALUES
	 (2306071059581045,2305312137051000,3,'添加','','','','sys:sys_user:add',0,0,2,'2023-06-07 10:59:58.039','1','2023-06-07 10:59:58.039','1'),
	 (2306071059211044,2305312137051000,3,'查詢','','','','sys:sys_user:list',0,0,1,'2023-06-07 10:59:21.651','1','2023-06-07 10:59:21.651','1'),
	 (2306071057061043,2305281726041000,3,'刪除','','','','sys:sys_role:delete',0,0,4,'2023-06-07 10:57:06.845','1','2023-06-07 10:57:06.845','1'),
	 (2305281726041000,401,2,'角色配置','','/sys/sys_role','_content','sys:sys_role:list',4,1,2,'2023-05-28 17:26:04.914','1','2023-06-07 10:57:06.845','1'),
	 (2306071056141042,2305281726041000,3,'修改','','','','sys:sys_role:update',0,0,3,'2023-06-07 10:56:14.608','1','2023-06-07 10:56:14.608','1'),
	 (2306071055271041,2305281726041000,3,'添加','','','','sys:sys_role:add',0,0,2,'2023-06-07 10:55:27.404','1','2023-06-07 10:55:27.404','1'),
	 (2306071054451040,2305281726041000,3,'查詢','','','','sys:sys_role:list',0,0,1,'2023-06-07 10:54:45.382','1','2023-06-07 10:54:45.382','1'),
	 (40105,401,2,'角色&菜单配置',NULL,'/sys/sys_role_menu','_content','sys:sys_role_menu:list',3,1,6,'2020-11-27 08:04:41.381','sys','2023-06-07 10:54:10.306','1'),
	 (2306071054101039,40105,3,'刪除','','','','sys:sys_role_menu:delete',0,0,3,'2023-06-07 10:54:10.306','1','2023-06-07 10:54:10.306','1'),
	 (2306071052451038,40105,3,'添加','','','','sys:sys_role_menu:add',0,0,2,'2023-06-07 10:52:45.361','1','2023-06-07 10:52:45.361','1');
INSERT INTO sys_menu (id,pid,"type",title,icon,"path",target,"permission",sub_count,"show",sort,create_time,create_by,update_time,update_by) VALUES
	 (2306071052151037,40105,3,'查詢','','','','sys:sys_role_menu:list',0,0,1,'2023-06-07 10:52:15.392','1','2023-06-07 10:52:15.392','1'),
	 (2306071051311036,40104,3,'刪除','','','','sys:sys_role_user:delete',0,0,3,'2023-06-07 10:51:31.784','1','2023-06-07 10:51:31.784','1'),
	 (40104,401,2,'角色&用户配置',NULL,'/sys/sys_role_user','_content','sys:sys_role_user:list',3,1,5,'2020-11-27 03:09:13.046','sys','2023-06-07 10:51:31.784','1'),
	 (2306071050571035,40104,3,'添加','','','','sys:sys_role_user:add',0,0,2,'2023-06-07 10:50:57.980','1','2023-06-07 10:50:57.980','1'),
	 (2306071050181034,40104,3,'查詢','','','','sys:sys_role_user:list',0,0,1,'2023-06-07 10:50:18.801','1','2023-06-07 10:50:18.801','1'),
	 (401,4,1,'系统配置',NULL,'',NULL,NULL,5,1,1,'2020-11-27 03:09:40.177','sys','2023-06-07 10:41:49.803','1'),
	 (40106,401,2,'菜单配置',NULL,'/sys/sys_menu','_content','sys:sys_menu:list',4,1,4,'2023-05-06 05:04:12.178','sys','2023-06-07 10:38:15.833','1'),
	 (2306071038151003,40106,3,'刪除','','','','sys:sys_menu:delete',0,0,4,'2023-06-07 10:38:15.833','1','2023-06-07 10:38:15.833','1'),
	 (2306071037411002,40106,3,'更新','','','','sys:sys_menu:update',0,0,3,'2023-06-07 10:37:41.660','1','2023-06-07 10:37:41.660','1'),
	 (2306071036061001,40106,3,'新增','','','','sys:sys_menu:add',0,0,2,'2023-06-07 10:36:06.411','1','2023-06-07 10:36:46.012','1');
INSERT INTO sys_menu (id,pid,"type",title,icon,"path",target,"permission",sub_count,"show",sort,create_time,create_by,update_time,update_by) VALUES
	 (2306071035211000,40106,3,'查詢','','','','sys:sys_menu:list',0,0,1,'2023-06-07 10:35:21.384','1','2023-06-07 10:36:29.262','1'),
	 (2306061317111000,0,1,'业务系统2','','https://www.csdn.net/','_content','',0,1,3,'2023-06-06 13:17:11.651','1','2023-06-06 13:29:08.544','1'),
	 (202,2306161419041075,1,'华南电商',NULL,'',NULL,NULL,9,1,2,'2020-11-27 03:28:43.524','sys','2023-05-24 17:51:42.223','1'),
	 (4,0,1,'系统管理',NULL,'',NULL,NULL,2,1,5,'2020-11-27 03:09:29.581','sys','2023-05-24 17:08:59.234','1'),
	 (1,0,1,'主页',NULL,'',NULL,NULL,0,1,1,'2020-11-27 03:09:21.714','sys','2023-05-08 09:59:43.910','SYS'),
	 (20703,207,2,'品牌',NULL,'/mallinfo/brand','_content','brand:list',0,1,1,'2020-11-27 03:45:34.000','admin',NULL,NULL),
	 (20702,207,2,'渠道',NULL,'/mallinfo/channel','_content','channel:list',0,1,3,'2020-11-27 03:45:34.000','admin',NULL,NULL),
	 (20203,202,2,'产品研发',NULL,'/huanan/developer','_content',NULL,0,1,3,'2020-10-09 11:31:11.199','1',NULL,NULL),
	 (20503,205,2,'店铺03',NULL,'','/mall/03',NULL,0,1,3,'2021-04-02 14:54:07.623','admin',NULL,NULL),
	 (20704,207,2,'商品',NULL,'/mallinfo/goods','_content','goods:list',0,1,4,'2022-04-13 13:29:21.678','admin',NULL,NULL);
INSERT INTO sys_menu (id,pid,"type",title,icon,"path",target,"permission",sub_count,"show",sort,create_time,create_by,update_time,update_by) VALUES
	 (20202,202,2,'直营渠道',NULL,'/huanan/official','_content',NULL,0,1,2,'2020-09-22 06:53:53.000','1',NULL,NULL),
	 (20501,205,2,'店铺01',NULL,'','/mall/01',NULL,0,1,1,'2020-11-27 03:44:24.000','admin',NULL,NULL),
	 (20201,202,2,'品牌一部',NULL,'/huanan/brand','_content',NULL,0,1,1,'2020-09-22 06:53:33.802','1',NULL,NULL),
	 (20502,205,2,'店铺02',NULL,'','/mall/02',NULL,0,1,2,'2020-11-27 03:44:24.000','admin',NULL,NULL);
INSERT INTO sys_menu (id,pid,"type",title,icon,"path",target,"permission",sub_count,"show",sort,create_time,create_by,update_time,update_by) VALUES
	 (2409141751571004,2409141749091000,3,'添加','','','','sys:qrtz_job:add',0,0,4,'2024-09-14 17:51:57.043','1','2024-09-14 17:51:57.043','1'),
	 (2409141749091000,402,2,'QUARTZ任务-任务&执行','','/sys/qrtz_job','_content','sys:qrtz_job:list',4,1,7,'2024-09-14 17:49:09.821','1','2024-09-14 17:51:57.043','1'),
	 (2409141103551001,2409141044001000,3,'查询','','','','sys:qrtz_app:list',0,0,1,'2024-09-14 11:03:55.218','1','2024-09-14 11:13:03.218','1'),
	 (2409141104551002,2409141044001000,3,'更新','','','','sys:qrtz_app:update',0,0,2,'2024-09-14 11:04:55.462','1','2024-09-14 11:13:22.618','1'),
	 (2409141105481003,2409141044001000,3,'删除','','','','sys:qrtz_app:delete',0,0,3,'2024-09-14 11:05:48.879','1','2024-09-14 11:13:38.601','1'),
	 (2409141106261004,2409141044001000,3,'添加','','','','sys:qrtz_app:add',0,0,4,'2024-09-14 11:06:26.046','1','2024-09-14 11:14:07.427','1'),
	 (2409141044001000,402,2,'QUARTZ任务-应用&节点','','/sys/qrtz_app','_content','sys:qrtz_app:list',4,1,6,'2024-09-14 10:44:00.422','1','2024-09-14 17:46:40.544','1'),
	 (2409141750251001,2409141749091000,3,'查询','','','','sys:qrtz_job:list',0,0,1,'2024-09-14 17:50:25.588','1','2024-09-14 17:50:25.588','1'),
	 (2409141750581002,2409141749091000,3,'更新','','','','sys:qrtz_job:update',0,0,2,'2024-09-14 17:50:58.505','1','2024-09-14 17:50:58.505','1'),
	 (2409141751291003,2409141749091000,3,'删除','','','','sys:qrtz_job:delete',0,0,3,'2024-09-14 17:51:29.459','1','2024-09-14 17:51:29.459','1');

DROP TABLE IF EXISTS "sys_log";
CREATE TABLE "sys_log" (
  "id" numeric(24,0) NOT NULL,
  "log_type" char(2) COLLATE "pg_catalog"."default" NOT NULL,
  "log_title" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "log_date" timestamp(6) NOT NULL,
  "remote_address" varchar(30) COLLATE "pg_catalog"."default",
  "log_content" varchar(1024) COLLATE "pg_catalog"."default"
);

COMMENT ON COLUMN "sys_log"."id" IS '日志表ID';
COMMENT ON COLUMN "sys_log"."log_type" IS '1.用户登录 ... 7.电八填报';
COMMENT ON COLUMN "sys_log"."log_title" IS '日志标题';
COMMENT ON COLUMN "sys_log"."log_date" IS '日志时间';
COMMENT ON COLUMN "sys_log"."remote_address" IS '远程地址IP';
COMMENT ON COLUMN "sys_log"."log_content" IS '日志内容';
COMMENT ON TABLE "sys_log" IS '系统::日志表';

INSERT INTO "sys_log" VALUES (2010201821080080100000, '1 ', 'admin', '2020-10-20 18:21:09', '10.0.132.37', 'user login record');
INSERT INTO "sys_log" VALUES (2010201824420080100000, '1 ', 'admin', '2020-10-20 18:24:43', '10.0.132.37', 'user login record');
INSERT INTO "sys_log" VALUES (2010201826330080100000, '1 ', 'admin', '2020-10-20 18:26:33', '10.0.132.37', 'user login record');
INSERT INTO "sys_log" VALUES (2010201846270080100000, '1 ', 'admin', '2020-10-20 18:46:28', '10.0.132.37', 'user login record');
INSERT INTO "sys_log" VALUES (2010210908060080100000, '1 ', 'admin', '2020-10-21 09:08:07', '127.0.0.1', 'user login record');
INSERT INTO "sys_log" VALUES (2010211146200080100000, '1 ', 'admin', '2020-10-21 11:46:21', '127.0.0.1', 'user login record');
INSERT INTO "sys_log" VALUES (2010230909400080100000, '1 ', 'admin', '2020-10-23 09:09:41', '127.0.0.1', 'user login record');
INSERT INTO "sys_log" VALUES (2010231101180080100000, '1 ', 'admin', '2020-10-23 11:01:19', '127.0.0.1', 'user login record');
INSERT INTO "sys_log" VALUES (2010231113540080100000, '1 ', 'admin', '2020-10-23 11:13:54', '127.0.0.1', 'user login record');
INSERT INTO "sys_log" VALUES (2010231121420080100000, '1 ', 'admin', '2020-10-23 11:21:43', '127.0.0.1', 'user login record');
INSERT INTO "sys_log" VALUES (2010231153350080100000, '1 ', 'admin', '2020-10-23 11:53:36', '127.0.0.1', 'user login record');
INSERT INTO "sys_log" VALUES (2010231325030080100001, '1 ', 'admin', '2020-10-23 13:25:04', '127.0.0.1', 'user login record');


-- 定时任务应用配置
DROP TABLE IF EXISTS SYS_SHEDLOCK_APP;
CREATE TABLE SYS_SHEDLOCK_APP (
  APPLICATION VARCHAR(64) NOT NULL ,
  HOST_IP VARCHAR(32) not null,
  HOST_NAME varchar(100),
  STATE CHAR(1) NOT NULL DEFAULT 1,
  LABEL VARCHAR(50),
  UPDATE_TIME TIMESTAMP(3) NOT NULL,
  PRIMARY KEY (APPLICATION,HOST_IP)
);
COMMENT ON COLUMN SYS_SHEDLOCK_APP.APPLICATION IS '当前实例应用';
COMMENT ON COLUMN SYS_SHEDLOCK_APP.HOST_IP IS '当前实例应用所属IP';
COMMENT ON COLUMN SYS_SHEDLOCK_APP.HOST_NAME IS '创建机器';
COMMENT ON COLUMN SYS_SHEDLOCK_APP.STATE IS '状态 0.关闭 1.开启';
COMMENT ON COLUMN SYS_SHEDLOCK_APP.LABEL IS '备注信息';
COMMENT ON COLUMN SYS_SHEDLOCK_APP.UPDATE_TIME IS '创建及更新时间';
COMMENT ON TABLE  SYS_SHEDLOCK_APP IS '集群分佈式鎖-应用配置';

-- 定时任务锁(new)
DROP TABLE IF EXISTS SYS_SHEDLOCK_JOB;
CREATE TABLE SYS_SHEDLOCK_JOB (
  APPLICATION VARCHAR(64) NOT NULL ,
  NAME varchar(64) not null,
  HOST_IP varchar(32) not null,
  LOCKED_AT timestamp(6) NOT NULL,
  LOCK_UNTIL timestamp(6) NOT NULL,
  LOCKED_BY varchar(100) NOT NULL,
  STATE CHAR(1) NOT NULL DEFAULT 1,
  DATA VARCHAR(256),
  LABEL VARCHAR(50),
  CALL_TYPE VARCHAR(20),
  CALL_VALUE VARCHAR(100),
  UPDATE_TIME TIMESTAMP(3) NOT NULL,
  PRIMARY KEY (APPLICATION, NAME)
);
COMMENT ON COLUMN SYS_SHEDLOCK_JOB.APPLICATION IS '应用';
COMMENT ON COLUMN SYS_SHEDLOCK_JOB.NAME IS '任务KEY';
COMMENT ON COLUMN SYS_SHEDLOCK_JOB.HOST_IP IS '当前实例应用所属IP';
COMMENT ON COLUMN SYS_SHEDLOCK_JOB.LOCKED_AT IS '任務開始鎖定';
COMMENT ON COLUMN SYS_SHEDLOCK_JOB.LOCK_UNTIL IS '任務鎖定至';
COMMENT ON COLUMN SYS_SHEDLOCK_JOB.LOCKED_BY IS '任務執行人';
COMMENT ON COLUMN SYS_SHEDLOCK_JOB.STATE IS '0.close关闭 1.open开启(默认) 局部限制标志位(只限制所有相同实例中的此任务)';
COMMENT ON COLUMN SYS_SHEDLOCK_JOB.DATA IS 'job传入数据';
COMMENT ON COLUMN SYS_SHEDLOCK_JOB.LABEL IS '任务描述';
COMMENT ON COLUMN SYS_SHEDLOCK_JOB.CALL_TYPE IS '定时任务类型';
COMMENT ON COLUMN SYS_SHEDLOCK_JOB.CALL_VALUE IS '定时任务值';
COMMENT ON COLUMN SYS_SHEDLOCK_JOB.UPDATE_TIME IS '最后更新时间(也即最近一次执行时间/获取锁时间)';
COMMENT ON TABLE  SYS_SHEDLOCK_JOB IS '集群分佈式鎖-任务配置';




-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS mee_admin."sys_file";
CREATE TABLE mee_admin."sys_file" (
  ID numeric(24,0) NOT NULL,
  ORIGINAL_NAME varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  NAME varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  FILE_PATH varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  FILE_TYPE varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  CATEGORY varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  CREATE_TIME timestamp(6) NOT NULL,
  CREATE_BY varchar(255) COLLATE "pg_catalog"."default"
);
COMMENT ON COLUMN sys_file.id IS '文件表ID';
COMMENT ON COLUMN sys_file.original_name IS '上传文件名称';
COMMENT ON COLUMN sys_file.name IS '保存文件名';
COMMENT ON COLUMN sys_file.file_path IS '文件保存路径';
COMMENT ON COLUMN sys_file.file_type IS '文件类型';
COMMENT ON COLUMN sys_file.category IS '文件分类(手动名称)';
COMMENT ON COLUMN sys_file.create_time IS '创建时间';
COMMENT ON COLUMN sys_file.create_by IS '创建人';
COMMENT ON TABLE mee_admin.sys_file IS '系统::文件表';


INSERT INTO mee_admin."sys_file" VALUES (2009221707508001100027, '14909_20190901.zip', '2009221707489000.zip', '/2020-09-22/2009221707509002.zip', '.zip', '2', '2020-09-22 09:07:51', '2');
INSERT INTO mee_admin."sys_file" VALUES (2009221730358001100028, '14909_201910全月.zip', '2009221730329003.zip', '/2020-09-22/2009221730359005.zip', '.zip', '3', '2020-09-22 09:30:35', '2');
INSERT INTO mee_admin."sys_file" VALUES (2009221748238001100007, '14909_201909全月.zip', '2009221748209000.zip', '/2020-09-22/2009221748239002.zip', '.zip', '3', '2020-09-22 09:48:24', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009221753208001100008, '14909_20190902.zip', '2009221753199003.zip', '/2020-09-22/2009221753209005.zip', '.zip', '2', '2020-09-22 09:53:20', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009221812290080100001, '14909_20200901.zip', '2009221812279000.zip', '/2020-09-22/2009221812299002.zip', '.zip', '2', '2020-09-22 10:12:29', '1');
INSERT INTO mee_admin."sys_file" VALUES (2009221821368001100002, '14909_201908全月-.zip', '2009221821339000.zip', '/2020-09-22/2009221821369002.zip', '.zip', '3', '2020-09-22 10:21:36', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009221823148001100003, '14909_201910全月.zip', '2009221823119003.zip', '/2020-09-22/2009221823149005.zip', '.zip', '3', '2020-09-22 10:23:15', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009221823268001100004, '14909_201911月全月.zip', '2009221823249006.zip', '/2020-09-22/2009221823269008.zip', '.zip', '3', '2020-09-22 10:23:27', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009221823438001100005, '14909_201912月全月.zip', '2009221823419009.zip', '/2020-09-22/2009221823439011.zip', '.zip', '3', '2020-09-22 10:23:44', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009221912000080100001, '14909_202001全月.zip', '2009221911299000.zip', '/2020-09-22/2009221912009002.zip', '.zip', '3', '2020-09-22 11:12:01', '1');
INSERT INTO mee_admin."sys_file" VALUES (2009222020218001100002, '14909_201901全月.zip', '2009222020189000.zip', '/2020-09-22/2009222020219002.zip', '.zip', '3', '2020-09-22 12:20:21', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222021018001100003, '14909_201902全月.zip', '2009222020599003.zip', '/2020-09-22/2009222021019005.zip', '.zip', '3', '2020-09-22 12:21:01', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222021138001100004, '14909_201903全月.zip', '2009222021119006.zip', '/2020-09-22/2009222021139008.zip', '.zip', '3', '2020-09-22 12:21:13', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222021348001100005, '14909_201904全月.zip', '2009222021329009.zip', '/2020-09-22/2009222021349011.zip', '.zip', '3', '2020-09-22 12:21:35', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222021448001100006, '14909_201905全月.zip', '2009222021429012.zip', '/2020-09-22/2009222021449014.zip', '.zip', '3', '2020-09-22 12:21:45', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222021548001100007, '14909_201906全月.zip', '2009222021529015.zip', '/2020-09-22/2009222021549017.zip', '.zip', '3', '2020-09-22 12:21:55', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222022058001100008, '14909_201907全月.zip', '2009222022029018.zip', '/2020-09-22/2009222022059020.zip', '.zip', '3', '2020-09-22 12:22:05', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222025338001100009, '14909_202001全月.zip', '2009222025319021.zip', '/2020-09-22/2009222025339023.zip', '.zip', '3', '2020-09-22 12:25:34', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222025430080100001, '14909_202004全月.zip', '2009222024599000.zip', '/2020-09-22/2009222025439002.zip', '.zip', '3', '2020-09-22 12:25:44', '1');
INSERT INTO mee_admin."sys_file" VALUES (2009222025548001100010, '14909_202002全月.zip', '2009222025529024.zip', '/2020-09-22/2009222025549026.zip', '.zip', '3', '2020-09-22 12:25:55', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222026358001100011, '14909_202003全月.zip', '2009222026329027.zip', '/2020-09-22/2009222026359029.zip', '.zip', '3', '2020-09-22 12:26:35', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222039568001100012, '14909_202005全月.zip', '2009222039539030.zip', '/2020-09-22/2009222039569032.zip', '.zip', '3', '2020-09-22 12:39:57', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222040118001100013, '14909_202006全月.zip', '2009222040089033.zip', '/2020-09-22/2009222040119035.zip', '.zip', '3', '2020-09-22 12:40:11', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222040288001100014, '14909_202007全月.zip', '2009222040259036.zip', '/2020-09-22/2009222040289038.zip', '.zip', '3', '2020-09-22 12:40:28', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222040538001100015, '14909_202008全月.zip', '2009222040499039.zip', '/2020-09-22/2009222040539041.zip', '.zip', '3', '2020-09-22 12:40:53', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222044218001100016, '14909_20190903.zip', '2009222044209042.zip', '/2020-09-22/2009222044219044.zip', '.zip', '2', '2020-09-22 12:44:21', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222044488001100017, '14909_20200901.zip', '2009222044479045.zip', '/2020-09-22/2009222044489047.zip', '.zip', '2', '2020-09-22 12:44:48', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222045038001100018, '14909_20200902.zip', '2009222045039048.zip', '/2020-09-22/2009222045039050.zip', '.zip', '2', '2020-09-22 12:45:04', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009222045188001100019, '14909_20200903.zip', '2009222045179051.zip', '/2020-09-22/2009222045189053.zip', '.zip', '2', '2020-09-22 12:45:18', '3');
INSERT INTO mee_admin."sys_file" VALUES (2009240917378001100030, '14909_20190904-.zip', '2009240917369054.zip', '/2020-09-24/2009240917379056.zip', '.zip', '2', '2020-09-24 01:17:37', '3');

DROP TABLE IF EXISTS mee_admin."sys_dict";
CREATE TABLE mee_admin.sys_dict (
	ID INT8 PRIMARY KEY,
	"name" varchar(127) NOT NULL,
	description varchar(255) NULL,
	create_by int8 NULL,
	update_by int8 NULL,
	create_time timestamp(6) NOT NULL,
	update_time timestamp(6) NULL
);
COMMENT ON COLUMN mee_admin."sys_dict"."id" IS '主键(字典ID)';
COMMENT ON COLUMN mee_admin."sys_dict"."name" IS '字典名称';
COMMENT ON COLUMN mee_admin."sys_dict"."description" IS '描述';
COMMENT ON COLUMN mee_admin."sys_dict"."create_by" IS '创建者';
COMMENT ON COLUMN mee_admin."sys_dict"."update_by" IS '更新者';
COMMENT ON COLUMN mee_admin."sys_dict"."create_time" IS '创建日期';
COMMENT ON COLUMN mee_admin."sys_dict"."update_time" IS '更新时间';
COMMENT ON TABLE mee_admin."sys_dict" IS '数据字典';
INSERT INTO mee_admin.sys_dict (id,"name",description,create_by,update_by,create_time,update_time) VALUES
	 (12,'sys_notice_type','通知类型',1,1,'2022-07-15 18:05:17.118','2022-07-15 18:05:17.118'),
	 (13,'sys_notice_status','通知状态',1,1,'2022-07-15 18:05:17.118','2022-07-15 18:05:17.118'),
	 (14,'sys_oper_type','操作类型',1,1,'2022-07-15 18:05:17.118','2022-07-15 18:05:17.118'),
	 (15,'sys_common_status','系统状态',1,1,'2022-07-15 18:05:17.118','2022-07-15 18:05:17.118'),
	 (4,'dept_status','部门状态',1,1,'2019-10-27 20:31:36.000',NULL),
	 (10,'sys_job_group','定时任务分组',1,1,'2022-07-15 18:05:17.118','2022-09-26 17:53:11.995'),
	 (2209291138521000,'online_status','在线状态',2,2,'2022-09-29 11:38:52.297','2022-09-29 11:38:52.297'),
	 (2209291139241001,'login_status','登录状态',2,2,'2022-09-29 11:39:24.550','2022-09-29 11:39:24.550'),
	 (2210091418521000,'scope_type','数据权限类型',1,1,'2022-10-09 14:18:52.027','2022-10-09 14:18:52.027'),
	 (6,'sys_user_sex','用户性别',2209221730491000,2209221730491000,'2022-07-15 18:05:17.118','2022-07-15 18:05:17.118');
INSERT INTO mee_admin.sys_dict (id,"name",description,create_by,update_by,create_time,update_time) VALUES
	 (7,'sys_show_hide','菜单状态',2,2,'2022-07-15 18:05:17.118','2022-07-15 18:05:17.118'),
	 (8,'sys_normal_disable','系统开关',2110201751031000,2110201751031000,'2022-07-15 18:05:17.118','2022-07-15 18:05:17.118'),
	 (9,'sys_job_status','任务状态',1,1,'2022-07-15 18:05:17.118','2022-07-15 18:05:17.118'),
	 (11,'sys_yes_no','系统是否',2210091451531000,2210091451531000,'2022-07-15 18:05:17.118','2022-07-15 18:05:17.118'),
	 (5,'job_status','岗位状态',2209221730491000,2209221730491000,'2019-10-27 20:31:36.000',NULL),
	 (1,'sys_user_status','用户状态',2,2,'2019-10-27 20:31:36.000',NULL),
	 (2210131610061000,'shedlock_status','定时任务状态',2,2,'2022-10-13 16:10:06.416','2022-10-13 16:10:06.416'),
	 (2210141744331000,'user_enabled','用户状态',2,2,'2022-10-14 17:44:33.310','2022-10-14 17:44:33.310'),
	 (16,'sys_menu_show','菜单是否显示',1,1,'2023-05-15 14:01:56.769','2023-05-15 14:01:56.769'),
	 (18,'sys_menu_type','菜单类型',1,1,'2023-05-24 14:53:09.776','2023-05-24 14:53:09.776');
INSERT INTO mee_admin.sys_dict (id,"name",description,create_by,update_by,create_time,update_time) VALUES
	 (17,'sys_menu_target','打开目标',1,1,'2023-05-24 14:48:24.801','2023-05-24 14:48:24.801'),
	 (2305261513591000,'test001_dict','测试字典001',1,1,'2023-05-26 15:13:59.886','2023-05-26 15:13:59.886'),
	 (2305301426501000,'sys_role_status','角色状态',1,1,'2023-05-30 14:26:50.674','2023-05-30 14:26:50.674'),
	 (2305311325251001,'sys_user_gender','用户性别',1,1,'2023-05-31 13:25:25.110','2023-05-31 13:25:25.110'),
	 (2305311326281004,'del_flag','删除标记',1,1,'2023-05-31 13:26:28.749','2023-05-31 13:26:28.749'),
	 (2306161442571000,'t_offline_store_brand','测试品牌',1,1,'2023-06-16 14:42:57.169','2023-06-16 14:42:57.169'),
	 (2306161451241004,'common_status','通用状态',1,1,'2023-06-16 14:51:24.190','2023-06-16 14:51:24.190');
INSERT INTO sys_dict (id,"name",description,create_by,update_by,create_time,update_time) VALUES
	 (2406181655071000,'shedlock_state','定时任务状态',1,1,'2024-06-18 16:55:07.974','2024-06-18 16:55:07.974');

-- sys_dict_detail
DROP TABLE IF EXISTS mee_admin."sys_dict_detail";
CREATE TABLE MEE_ADMIN.SYS_DICT_DETAIL (
	ID INT8 PRIMARY KEY,
	dict_id int8 NOT NULL,
	"label" varchar(128) NOT NULL,
	value varchar(127) NOT NULL,
	"sort" int4 NOT NULL default '1',
	create_by int8 NULL,
	update_by int8 NULL,
	create_time timestamp(6) NOT NULL,
	update_time timestamp(6) NULL
);
COMMENT ON COLUMN mee_admin."sys_dict_detail"."id" IS '主键(字典项id)';
COMMENT ON COLUMN mee_admin."sys_dict_detail"."dict_id" IS '字典id';
COMMENT ON COLUMN mee_admin."sys_dict_detail"."label" IS '字典标签';
COMMENT ON COLUMN mee_admin."sys_dict_detail"."value" IS '字典值';
COMMENT ON COLUMN mee_admin."sys_dict_detail"."sort" IS '排序';
COMMENT ON COLUMN mee_admin."sys_dict_detail"."create_by" IS '创建者';
COMMENT ON COLUMN mee_admin."sys_dict_detail"."update_by" IS '更新者';
COMMENT ON COLUMN mee_admin."sys_dict_detail"."create_time" IS '创建日期';
COMMENT ON COLUMN mee_admin."sys_dict_detail"."update_time" IS '更新时间';
COMMENT ON TABLE  mee_admin."sys_dict_detail" IS '数据字典详情';
INSERT INTO mee_admin.sys_dict_detail (id,dict_id,"label",value,sort,create_by,update_by,create_time,update_time) VALUES
	 (16,8,'正常','0',7,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (17,8,'停用','1',8,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (18,13,'正常','0',9,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (19,13,'关闭','1',10,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (20,12,'通知','1',11,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (21,12,'公告','2',12,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (31,7,'显示','0',22,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (32,7,'隐藏','1',23,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (33,6,'男','0',24,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (34,6,'女','1',25,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000');
INSERT INTO mee_admin.sys_dict_detail (id,dict_id,"label",value,sort,create_by,update_by,create_time,update_time) VALUES
	 (35,6,'未知','2',26,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (36,11,'是','Y',27,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (37,11,'否','N',28,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (10,15,'成功','0',1,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (11,15,'失败','1',2,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (12,10,'默认','DEFAULT',3,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (13,10,'系统','SYSTEM',4,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (1,1,'激活(有效)','1',1,1,NULL,'2019-10-27 20:31:36.000',NULL),
	 (6,5,'停用','0',2,1,NULL,'2019-10-27 20:31:36.000',NULL),
	 (4,4,'停用','0',2,1,NULL,'2019-10-27 20:31:36.000',NULL);
INSERT INTO mee_admin.sys_dict_detail (id,dict_id,"label",value,sort,create_by,update_by,create_time,update_time) VALUES
	 (5,5,'启用','1',1,1,NULL,'2022-09-23 17:57:46.831','2022-09-23 17:57:46.831'),
	 (3,4,'启用','1',1,1,NULL,'2022-09-23 17:57:46.831','2022-09-23 17:57:46.831'),
	 (2,1,'禁用(无效)','0',2,1,NULL,'2022-09-23 17:57:46.831','2022-09-23 17:57:46.831'),
	 (2209291142151000,2209291139241001,'登录失败','0',1,2,2,'2022-09-29 11:42:15.962','2022-09-29 11:42:15.962'),
	 (2209291142371001,2209291139241001,'登录成功','1',2,2,2,'2022-09-29 11:42:37.027','2022-09-29 11:42:37.027'),
	 (2209291143111002,2209291139241001,'ignore','2',3,2,2,'2022-09-29 11:43:11.756','2022-09-29 11:43:11.756'),
	 (2209291146371003,2209291138521000,'在线','1',1,2,1,'2022-09-29 11:46:37.062','2022-09-29 14:19:01.286'),
	 (2209291146571004,2209291138521000,'离线','0',2,2,1,'2022-09-29 11:46:57.793','2022-09-29 14:19:06.205'),
	 (2210091419301001,2210091418521000,'全部','1',1,1,1,'2022-10-09 14:19:30.040','2022-10-09 14:19:30.040'),
	 (2210091419411002,2210091418521000,'仅所在部门','2',2,1,1,'2022-10-09 14:19:41.370','2022-10-09 14:19:41.370');
INSERT INTO mee_admin.sys_dict_detail (id,dict_id,"label",value,sort,create_by,update_by,create_time,update_time) VALUES
	 (2210091420051003,2210091418521000,'所在部门及所有子级','3',3,1,1,'2022-10-09 14:20:05.508','2022-10-09 14:20:05.508'),
	 (2210091420151004,2210091418521000,'本人数据','4',4,1,1,'2022-10-09 14:20:15.559','2022-10-09 14:20:15.559'),
	 (2210091420241005,2210091418521000,'自定义','5',5,1,1,'2022-10-09 14:20:24.318','2022-10-09 14:20:24.318'),
	 (2210131610431002,2210131610061000,'关闭','0',1,2,2,'2022-10-13 16:10:43.034','2022-10-13 16:13:09.086'),
	 (2210131610291001,2210131610061000,'开启','1',2,2,2,'2022-10-13 16:10:29.573','2022-10-13 16:13:16.536'),
	 (2210141745051001,2210141744331000,'禁用','0',1,2,2,'2022-10-14 17:45:05.468','2022-10-14 17:45:05.468'),
	 (2210141745161002,2210141744331000,'启用','1',2,2,2,'2022-10-14 17:45:16.992','2022-10-14 17:46:19.874'),
	 (14,9,'正常','0',1,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (15,9,'暂停','1',2,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (22,14,'新增','1',1,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000');
INSERT INTO mee_admin.sys_dict_detail (id,dict_id,"label",value,sort,create_by,update_by,create_time,update_time) VALUES
	 (23,14,'修改','2',2,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (24,14,'删除','3',3,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (25,14,'授权','4',4,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (26,14,'导出','5',5,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (27,14,'导入','6',6,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (28,14,'强退','7',7,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (29,14,'生成代码','8',8,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (30,14,'清空数据','9',9,1,NULL,'2022-07-15 18:11:00.000','2022-07-15 18:11:00.000'),
	 (38,17,'在新窗口中打开','_blank',5,1,NULL,'2023-05-24 14:48:16.214','2023-05-24 14:48:16.214'),
	 (39,17,'当前框架中打开','_self',4,1,NULL,'2023-05-24 14:48:16.214','2023-05-24 14:48:16.214');
INSERT INTO mee_admin.sys_dict_detail (id,dict_id,"label",value,sort,create_by,update_by,create_time,update_time) VALUES
	 (40,17,'在父框架中打开','_parent',3,1,NULL,'2023-05-24 14:48:16.214','2023-05-24 14:48:16.214'),
	 (41,17,'在整个窗口中打开','_top',2,1,NULL,'2023-05-24 14:48:16.214','2023-05-24 14:48:16.214'),
	 (42,17,'在右侧框架中打开','_content',1,1,NULL,'2023-05-24 14:48:16.214','2023-05-24 14:48:16.214'),
	 (43,18,'目录','1',1,1,NULL,'2023-05-24 14:53:09.778','2023-05-24 14:53:09.778'),
	 (44,18,'菜单','2',2,1,NULL,'2023-05-24 14:53:09.778','2023-05-24 14:53:09.778'),
	 (45,18,'按钮','3',3,1,NULL,'2023-05-24 14:53:09.778','2023-05-24 14:53:09.778'),
	 (46,16,'否','0',1,1,NULL,'2023-05-24 15:00:45.854','2023-05-24 15:00:45.854'),
	 (47,16,'是','1',1,1,NULL,'2023-05-24 15:00:45.854','2023-05-24 15:00:45.854'),
	 (2305301427041001,2305301426501000,'开启','1',2,1,1,'2023-05-30 14:27:04.716','2023-05-30 14:27:04.716'),
	 (2305301427141002,2305301426501000,'关闭','0',1,1,1,'2023-05-30 14:27:14.254','2023-05-30 14:27:14.254');
INSERT INTO mee_admin.sys_dict_detail (id,dict_id,"label",value,sort,create_by,update_by,create_time,update_time) VALUES
	 (2305311325501003,2305311325251001,'男','M',2,1,1,'2023-05-31 13:25:50.158','2023-05-31 13:25:50.158'),
	 (2305311325351002,2305311325251001,'女','F',1,1,1,'2023-05-31 13:25:35.846','2023-05-31 13:26:01.837'),
	 (2305311327011006,2305311326281004,'正常','1',2,1,1,'2023-05-31 13:27:01.580','2023-05-31 13:27:01.580'),
	 (2305311326421005,2305311326281004,'已删除','0',1,1,1,'2023-05-31 13:26:42.693','2023-05-31 13:27:09.413'),
	 (2306161453401006,2306161451241004,'禁用','0',2,1,1,'2023-06-16 14:53:40.879','2023-06-16 14:53:40.879'),
	 (2306161443211001,2306161442571000,'品牌1','1',1,1,1,'2023-06-16 14:43:21.047','2023-06-16 15:01:45.303'),
	 (2306161443381002,2306161442571000,'品牌2','2',2,1,1,'2023-06-16 14:43:38.238','2023-06-16 15:01:49.620'),
	 (2306161444081003,2306161442571000,'品牌3','3',3,1,1,'2023-06-16 14:44:08.533','2023-06-16 15:01:53.205'),
	 (2306161451401005,2306161451241004,'启用','1',1,1,1,'2023-06-16 14:51:40.402','2023-06-16 16:05:12.194');
INSERT INTO sys_dict_detail (id,dict_id,"label",value,sort,create_by,update_by,create_time,update_time) VALUES
	 (2406181700341001,2406181655071000,'关闭','0',2,1,1,'2024-06-18 17:00:34.902','2024-06-18 17:00:34.902'),
	 (2406181700171000,2406181655071000,'开启','1',1,1,1,'2024-06-18 17:00:17.200','2024-06-18 17:00:17.200');


-- 角色相关
CREATE TABLE "mee_admin"."sys_role" (
  "id" int8 primary key,
  "name" varchar(80)  NOT NULL,
  "description" varchar(128),
  "status" int4 NOT NULL,
  "create_by" int8,
  "create_time" timestamp,
  "update_by" int8,
  "update_time" timestamp
);

COMMENT ON COLUMN "mee_admin"."sys_role"."id" IS '角色id';
COMMENT ON COLUMN "mee_admin"."sys_role"."name" IS '角色名称(en)';
COMMENT ON COLUMN "mee_admin"."sys_role"."description" IS '角色描述';
COMMENT ON COLUMN "mee_admin"."sys_role"."status" IS '角色状态 0.关闭 1.开启';
COMMENT ON COLUMN "mee_admin"."sys_role"."create_by" IS '创建人';
COMMENT ON COLUMN "mee_admin"."sys_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "mee_admin"."sys_role"."update_by" IS '更新人';
COMMENT ON COLUMN "mee_admin"."sys_role"."update_time" IS '更新时间';
COMMENT ON TABLE "mee_admin"."sys_role" IS '系统::角色表';

INSERT INTO mee_admin.sys_role (id,"name",description,status,create_by,create_time,update_by,update_time) VALUES
	 (2201181119100002,'ROLE_test','测试用户组',1,1,'2023-05-28 01:03:04.277',1,'2023-05-28 01:03:04.277'),
	 (2205101711100002,'ROLE_admin','管理员组',1,1,'2023-05-28 01:03:04.277',1,'2023-05-28 01:03:04.277'),
	 (2305301619401000,'ROLE_demo1','demo角色',0,1,'2023-05-30 16:19:40.568',1,'2023-05-30 16:19:40.568'),
	 (2101071443100107,'ROLE_brand2_report','品牌二日报组',1,1,'2023-05-28 01:03:04.277',1,'2023-06-07 15:07:50.100'),
	 (2203251821100455,'ROLE_it','技术支持部',1,1,'2023-05-28 01:03:04.277',1,'2023-06-07 15:09:47.381'),
	 (2211081625100056,'ROLE_leader','主管组',1,1,'2023-05-28 01:03:04.277',1,'2023-05-28 01:03:04.277'),
	 (2107191139100327,'ROLE_bi','BI报表组',1,1,'2023-05-28 01:03:04.277',1,'2023-05-28 01:03:04.277'),
	 (2203251821100307,'ROLE_dev','开发组',1,1,'2023-05-28 01:03:04.277',1,'2023-05-28 01:03:04.277'),
	 (2104080940104802,'ROLE_jf_center','运营组',1,1,'2023-05-28 01:03:04.277',1,'2023-06-07 15:06:01.517'),
	 (2105121352100002,'ROLE_brand1','华南一部角色',1,1,'2023-05-28 01:03:04.277',1,'2023-06-07 15:07:15.404');
INSERT INTO mee_admin.sys_role (id,"name",description,status,create_by,create_time,update_by,update_time) VALUES
	 (2104131519103477,'ROLE_brand3','江南三日报',1,1,'2023-05-28 01:03:04.277',1,'2023-06-07 15:08:14.543'),
	 (2101130936104294,'ROLE_brand2_tk','抖二音组',1,1,'2023-05-28 01:03:04.277',1,'2023-06-07 15:09:07.733');

CREATE TABLE "mee_admin"."sys_role_menu" (
  "id" int8 primary key,
  "menu_id" int8 NOT NULL,
  "role_id" int8 NOT NULL,
  "create_by" int8,
  "create_time" timestamp
);

COMMENT ON COLUMN "mee_admin"."sys_role_menu"."id" IS '主键';
COMMENT ON COLUMN "mee_admin"."sys_role_menu"."menu_id" IS '菜单ID';
COMMENT ON COLUMN "mee_admin"."sys_role_menu"."role_id" IS '角色ID';
COMMENT ON COLUMN "mee_admin"."sys_role_menu"."create_by" IS '创建人';
COMMENT ON COLUMN "mee_admin"."sys_role_menu"."create_time" IS '创建时间';
COMMENT ON TABLE "mee_admin"."sys_role_menu" IS '角色菜单关联';

INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (201127152080100002,402,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100003,40202,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100004,20105,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100005,20104,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100006,20103,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100007,20102,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100008,20101,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100009,201,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100010,2,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100011,1,2010261453100047,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (201127152080100012,3,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100013,40204,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100014,202,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100015,20201,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100016,20202,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100017,203,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100018,20301,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100019,20302,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100020,301,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100021,30101,2010261453100047,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (201127152080100022,30102,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100023,30103,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100024,302,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100025,303,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100026,4,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100027,20203,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100028,40101,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100029,401,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100030,40102,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100031,40201,2010261453100047,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (201127152080100032,20106,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100033,20107,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100034,20108,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100035,40205,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (220325182180100310,20703,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100311,20702,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (201127152080100039,20109,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100040,20110,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100041,20111,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100042,6,2010261453100047,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (201127152080100047,20208,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100048,20209,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100049,20303,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100051,20501,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100053,20503,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100054,205,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100055,20502,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (220325182180100312,20701,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100313,207,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (201127152080100058,207,2010261453100047,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (201127152080100059,20702,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100060,20703,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100061,20701,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100064,601,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100065,602,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (201127152080100066,603,2010261453100047,1,'2023-05-28 01:21:48.143'),
	 (220325182180100314,20505,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (201127152980100002,1,2011271522100068,1,'2023-05-28 01:21:48.143'),
	 (201127152980100003,2,2011271522100068,1,'2023-05-28 01:21:48.143'),
	 (201127152980100004,201,2011271522100068,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220325182180100315,20504,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100316,20503,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100317,20502,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100318,20501,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100319,205,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100320,20307,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100321,20305,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100322,20304,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100323,20303,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100324,20302,2203251821100307,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220325182180100325,20301,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100326,203,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100327,20306,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100328,20204,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100329,20111,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100330,20110,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100331,20109,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100332,20108,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100333,20107,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100334,20106,2203251821100307,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220325182180100335,20105,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100336,20104,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100337,20103,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100338,20102,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100339,20101,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100340,201,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100341,40104,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100342,40103,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100344,1,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100345,301,2203251821100307,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220325182180100346,3,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100347,30101,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100348,30102,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100349,302,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100350,303,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100351,3010101,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100352,3010102,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100353,3010104,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100354,3010103,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100355,3010105,2203251821100307,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220325182180100356,3010201,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100357,3010202,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100358,3010204,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100359,3010205,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100360,3010203,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100361,3010206,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100362,3010208,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100363,3010207,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100364,3010209,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (210107144301100109,203,2101071443100107,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210107144301100110,20301,2101071443100107,1,'2023-05-28 01:21:48.143'),
	 (210107144301100111,20302,2101071443100107,1,'2023-05-28 01:21:48.143'),
	 (210107144301100112,2,2101071443100107,1,'2023-05-28 01:21:48.143'),
	 (210107144301100113,205,2101071443100107,1,'2023-05-28 01:21:48.143'),
	 (210107144301100114,20501,2101071443100107,1,'2023-05-28 01:21:48.143'),
	 (210107144301100115,20502,2101071443100107,1,'2023-05-28 01:21:48.143'),
	 (210107144301100116,20503,2101071443100107,1,'2023-05-28 01:21:48.143'),
	 (210107144301100117,30104,2101071443100107,1,'2023-05-28 01:21:48.143'),
	 (210107144301100118,3010401,2101071443100107,1,'2023-05-28 01:21:48.143'),
	 (210107144301100119,3010402,2101071443100107,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210107144301100120,3010404,2101071443100107,1,'2023-05-28 01:21:48.143'),
	 (210107144301100121,301,2101071443100107,1,'2023-05-28 01:21:48.143'),
	 (210107144301100122,3,2101071443100107,1,'2023-05-28 01:21:48.143'),
	 (210113093601104296,2,2101130936104294,1,'2023-05-28 01:21:48.143'),
	 (210113093601104297,201,2101130936104294,1,'2023-05-28 01:21:48.143'),
	 (210113093601104298,20101,2101130936104294,1,'2023-05-28 01:21:48.143'),
	 (210113093601104299,20102,2101130936104294,1,'2023-05-28 01:21:48.143'),
	 (210113093601104300,20103,2101130936104294,1,'2023-05-28 01:21:48.143'),
	 (210113093601104301,20104,2101130936104294,1,'2023-05-28 01:21:48.143'),
	 (210113093601104302,20105,2101130936104294,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210113093601104303,20106,2101130936104294,1,'2023-05-28 01:21:48.143'),
	 (210113093601104304,20107,2101130936104294,1,'2023-05-28 01:21:48.143'),
	 (210113093601104305,20108,2101130936104294,1,'2023-05-28 01:21:48.143'),
	 (210113093601104306,20109,2101130936104294,1,'2023-05-28 01:21:48.143'),
	 (210113093601104307,20110,2101130936104294,1,'2023-05-28 01:21:48.143'),
	 (210113093601104308,20111,2101130936104294,1,'2023-05-28 01:21:48.143'),
	 (210113093601104309,3,2101130936104294,1,'2023-05-28 01:21:48.143'),
	 (210113093601104310,301,2101130936104294,1,'2023-05-28 01:21:48.143'),
	 (210113093601104311,3010403,2101130936104294,1,'2023-05-28 01:21:48.143'),
	 (210113093601104312,3010404,2101130936104294,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210113093601104313,30104,2101130936104294,1,'2023-05-28 01:21:48.143'),
	 (210512135280100004,1,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100005,2,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100006,201,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100007,20101,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100008,20102,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100009,20103,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210719113980100333,30201,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100334,30202,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100335,3010213,2107191139100327,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210719113980100336,604,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100337,60401,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100338,60402,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100339,60403,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100340,60405,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100341,60404,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100342,60101,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100343,213,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100344,21301,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100345,2130101,2107191139100327,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210719113980100346,2130102,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100347,2130103,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100348,2130104,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100349,2130105,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100350,2130106,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100351,2130107,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100352,2130108,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100353,210,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100354,21001,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (221108162501100062,302,2211081625100056,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (221108162501100063,303,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100064,4,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100065,20203,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100066,401,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100067,40201,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100068,20106,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100069,20107,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100070,20108,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100071,40205,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100072,20109,2211081625100056,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (221108162501100073,20110,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (210719113980100355,21002,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100356,1,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100357,2,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100358,201,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100359,20101,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100360,20102,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100361,20104,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100362,20103,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100363,20105,2107191139100327,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210719113980100364,20106,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210512135280100010,20104,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210719113980100365,20107,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100366,20108,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100367,20109,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100368,20111,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100369,20110,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100370,202,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100371,20203,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100372,20201,2107191139100327,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210719113980100373,20202,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100374,20208,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100375,20209,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100376,203,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100377,20301,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100378,20302,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100379,20303,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100380,205,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100381,20501,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100382,20502,2107191139100327,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210719113980100383,20503,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100384,207,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100385,20702,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100386,20701,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100387,20703,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100388,3,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100389,30101,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100390,301,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100391,30102,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100392,30103,2107191139100327,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210719113980100393,302,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100394,303,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100395,401,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100396,4,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100397,402,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100398,40202,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (220325182180100365,3010210,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100366,3010212,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100367,30103,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (210719113980100402,20304,2107191139100327,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210719113980100403,20504,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100404,30107,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100405,3010701,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100406,3010702,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100407,30108,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100408,3010803,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100409,3010806,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100410,3010807,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100411,3010805,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100412,3010804,2107191139100327,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210719113980100413,3010800,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100414,3010801,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100415,3010802,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100416,20305,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100417,20306,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100418,21003,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100419,20307,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100429,20505,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100431,20205,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100432,20204,2107191139100327,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210719113980100434,30109,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100435,30111,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100436,30110,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100437,3011000,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100438,20210,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210512135280100011,20105,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100012,20106,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100013,20107,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100014,20108,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100015,20109,2105121352100002,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210512135280100016,20110,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100017,20111,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100018,202,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100019,20201,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100020,20202,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100021,20203,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100022,20204,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100023,20208,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100024,20209,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100025,203,2105121352100002,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210512135280100026,20301,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100027,20302,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100028,20304,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100029,20307,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100030,20306,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100031,20305,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100032,20303,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100042,205,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100043,20502,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100044,20503,2105121352100002,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210512135280100045,20505,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (220325182180100368,3010301,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (210512135280100047,210,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (220325182180100369,3010302,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100370,3010303,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (210512135280100050,20504,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100051,20501,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100052,207,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100053,20701,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100054,20702,2105121352100002,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210512135280100055,20703,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100056,21001,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100057,21003,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100058,21002,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100059,213,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100060,21301,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100061,2130101,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100062,2130103,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100063,2130102,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100064,2130104,2105121352100002,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210512135280100065,2130105,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100066,2130106,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210719113980100439,6,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100440,601,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100441,602,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100442,603,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100443,40205,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100444,3010101,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100445,3010102,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100446,3010103,2107191139100327,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210719113980100447,3010104,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100448,3010105,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100449,3010201,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100450,3010202,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100451,3010203,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100452,3010205,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100453,3010206,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100454,3010204,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100455,3010207,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100456,3010208,2107191139100327,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210719113980100457,3010209,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100458,3010210,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100459,3010212,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100460,3010301,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100461,3010302,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100462,3010304,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100463,3010305,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (220325182180100371,3010304,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100372,3010305,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100373,3010306,2203251821100307,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220325182180100374,3010307,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100375,3010313,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100376,30104,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100377,30105,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (210512135280100067,2130108,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210719113980100464,3010303,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100465,3010306,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100466,3010307,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (220325182180100378,3010404,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100379,3010401,2203251821100307,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220325182180100380,3010402,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100381,3010403,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100382,3010501,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (210719113980100472,3010313,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100473,3010401,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100474,30104,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100475,3010402,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100476,3010403,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100477,3010404,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100478,30105,2107191139100327,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210719113980100479,3010501,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100480,3010504,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100481,3010503,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100482,30106,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100483,3010601,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100484,3010602,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100485,3010603,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (220325182180100383,3010503,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (210719113980100487,40104,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100488,40105,2107191139100327,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210719113980100489,40204,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210719113980100490,20308,2107191139100327,1,'2023-05-28 01:21:48.143'),
	 (210512135280100068,2130107,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100069,3,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100070,301,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100071,30101,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100072,3010101,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100073,3010102,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100074,3010103,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100075,3010104,2105121352100002,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210512135280100076,30102,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100077,3010105,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100078,3010201,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100079,3010202,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100080,3010204,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100081,3010205,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100082,3010208,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100083,3010210,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100084,3010213,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100085,3010212,2105121352100002,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210512135280100086,3010209,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100087,3010207,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100088,3010206,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100089,3010203,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100090,30103,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100091,3010302,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100092,3010303,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100093,3010305,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (220325182180100384,3010504,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100385,30106,2203251821100307,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210512135280100096,3010313,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (220325182180100386,3010601,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100387,3010602,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100388,3010603,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (210512135280100100,3010307,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100101,3010306,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100102,3010304,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100103,3010301,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100104,30104,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100105,3010401,2105121352100002,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210512135280100106,3010402,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100107,3010403,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100108,3010404,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100109,30105,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100110,3010501,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100111,3010503,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100112,3010504,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100113,30106,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100114,3010601,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100115,3010602,2105121352100002,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210512135280100116,30107,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100117,3010603,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100118,3010701,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100119,3010702,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100120,3010800,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100121,30108,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100122,3010802,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100123,3010801,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100124,3010803,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100125,3010805,2105121352100002,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210512135280100126,3010806,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100127,3010807,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100128,3010804,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100129,30109,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100130,302,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100131,30201,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100132,30202,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100133,303,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100134,4,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100135,402,2105121352100002,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210512135280100136,40202,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100137,40204,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (210512135280100138,20205,2105121352100002,1,'2023-05-28 01:21:48.143'),
	 (220325182180100389,30202,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100390,30201,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100391,60401,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100392,60402,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100393,60404,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100394,60403,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100395,60405,2203251821100307,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220325182180100396,40204,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100397,3010213,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100398,3010701,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100399,30107,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (210408094001104805,1,2104080940104802,1,'2023-05-28 01:21:48.143'),
	 (210408094001104806,2,2104080940104802,1,'2023-05-28 01:21:48.143'),
	 (210408094001104807,205,2104080940104802,1,'2023-05-28 01:21:48.143'),
	 (210408094001104808,20501,2104080940104802,1,'2023-05-28 01:21:48.143'),
	 (210408094001104809,20502,2104080940104802,1,'2023-05-28 01:21:48.143'),
	 (210408094001104810,4,2104080940104802,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210408094001104811,402,2104080940104802,1,'2023-05-28 01:21:48.143'),
	 (210408094001104812,40204,2104080940104802,1,'2023-05-28 01:21:48.143'),
	 (210408094001104813,20503,2104080940104802,1,'2023-05-28 01:21:48.143'),
	 (210408094001104814,3,2104080940104802,1,'2023-05-28 01:21:48.143'),
	 (210408094001104815,301,2104080940104802,1,'2023-05-28 01:21:48.143'),
	 (210408094001104816,30109,2104080940104802,1,'2023-05-28 01:21:48.143'),
	 (210413151901103482,2,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103483,210,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103484,21001,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103485,21002,2104131519103477,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210413151901103486,3,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103487,301,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103488,30108,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103489,3010800,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103490,3010801,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103491,3010802,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103492,3010803,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103493,3010804,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103494,3010805,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103495,3010806,2104131519103477,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210413151901103496,3010807,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103497,20504,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103498,205,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103499,203,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103500,20306,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103501,21003,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103502,20501,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103503,30103,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103504,3010305,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103505,3010306,2104131519103477,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (210413151901103506,3010307,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (210413151901103507,3010313,2104131519103477,1,'2023-05-28 01:21:48.143'),
	 (220325182180100400,3010702,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100401,30108,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100402,3010801,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100403,3010802,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100404,3010804,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100405,3010806,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100406,3010807,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100407,3010805,2203251821100307,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220325182180100408,3010803,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100409,3010800,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100410,604,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220118111980100026,303,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100027,4,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100028,20203,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100029,40101,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100030,401,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100031,40102,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100032,40201,2201181119100002,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220118111980100033,20106,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100034,20107,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100035,20108,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100036,40205,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100037,20109,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100038,20110,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100039,20111,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100040,6,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100041,20208,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100042,20209,2201181119100002,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220118111980100043,20303,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100044,20501,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100045,20503,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100046,205,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100047,20502,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100048,207,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100049,20702,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100050,20703,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100051,20701,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100053,601,2201181119100002,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220118111980100054,602,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100055,603,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220325182180100411,6,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100412,402,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100413,4,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100414,2,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100415,202,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100416,20201,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100417,20202,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100418,20203,2203251821100307,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220325182180100419,20208,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100420,20209,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100421,210,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100422,21001,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100423,21002,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100424,21003,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100425,603,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100426,602,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100427,60101,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100428,601,2203251821100307,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220325182180100429,40205,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100430,40202,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100431,40201,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100432,40105,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100433,401,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100434,30109,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100435,2130108,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100436,2130107,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100437,2130106,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100438,2130105,2203251821100307,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220325182180100439,2130103,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100440,2130102,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100441,2130101,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (221108162501100074,20111,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100075,6,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100076,20208,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100077,20209,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100078,20303,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100079,20501,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100080,20503,2211081625100056,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (221108162501100081,205,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100082,20502,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100083,207,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100084,20505,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100085,30109,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100087,20204,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100088,20205,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100089,30110,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100090,3011000,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100091,30111,2211081625100056,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (221108162501100092,20210,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100093,20308,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100094,20307,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100095,20704,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100096,20705,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100097,40101,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100098,40102,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100099,20309,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100100,30112,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100101,20211,2211081625100056,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (221108162501100102,30113,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100103,20706,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100104,30114,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100105,20702,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100106,20703,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100107,20701,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100108,601,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100109,602,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100110,603,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100111,30104,2211081625100056,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (221108162501100112,3010401,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100113,3010402,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100114,3010403,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100115,3010404,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100116,30105,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100117,3010501,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100118,3010503,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100119,30106,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100120,3010504,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100121,3010601,2211081625100056,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (221108162501100122,3010602,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100123,3010603,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100124,3010313,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100125,3010307,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100126,3010305,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100127,3010304,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100128,3010302,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100129,3010102,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100130,3010101,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100131,3010201,2211081625100056,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (221108162501100132,3010203,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100133,3010204,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100134,3010208,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100135,3010209,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100136,3010205,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (220325182180100442,21301,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (221108162501100137,3010207,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100138,3010206,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (220325182180100443,213,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100444,2130104,2203251821100307,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220325182180100445,20205,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100446,30110,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (221108162501100139,3010202,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100140,3010212,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100141,3010103,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100142,3010104,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100143,3010105,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100144,3010210,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100145,3010303,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100146,3010301,2211081625100056,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (221108162501100147,3010306,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100148,30201,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100149,30202,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100150,30107,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100151,3010701,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100152,3010702,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100153,21001,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100154,210,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100155,21002,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100156,213,2211081625100056,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (221108162501100157,21301,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100158,2130101,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100159,2130102,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (220325182180100447,3011000,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100448,30111,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100449,20308,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100450,20210,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100451,20704,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100452,20705,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100453,30112,2203251821100307,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220325182180100454,30113,2203251821100307,1,'2023-05-28 01:21:48.143'),
	 (220325182180100457,2,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100458,201,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100459,20102,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100460,20101,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100461,202,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100462,20201,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100463,20202,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100464,3,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100465,301,2203251821100455,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220325182180100466,30110,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100467,3011000,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100468,30111,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100469,30101,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100470,30112,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100471,30113,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100472,302,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100473,303,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100474,30202,2203251821100455,1,'2023-05-28 01:21:48.143'),
	 (220325182180100475,30102,2203251821100455,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (221108162501100160,2130103,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100161,2130106,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100162,2130105,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100163,2130104,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100164,2130107,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100165,2130108,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100166,30108,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100167,3010800,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100168,3010802,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100169,3010801,2211081625100056,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (221108162501100170,3010803,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100171,3010804,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100172,3010805,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100173,3010806,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100174,60101,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100175,20304,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100176,3010213,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100177,3010807,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100178,20504,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100179,604,2211081625100056,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (221108162501100180,60401,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100181,60402,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100182,60403,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100183,60404,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100184,60405,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100185,21003,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100186,40104,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100187,40105,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100188,20305,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100189,20306,2211081625100056,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (221108162501100190,402,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100191,40202,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100192,20105,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100193,20104,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100194,20103,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100195,20102,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100196,20101,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100197,201,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100198,2,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100199,1,2211081625100056,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (221108162501100200,3,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100201,40204,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100202,202,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100203,20201,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100204,20202,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100205,203,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100206,20301,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100207,20302,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100208,301,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100209,30101,2211081625100056,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (221108162501100210,30102,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (221108162501100211,30103,2211081625100056,1,'2023-05-28 01:21:48.143'),
	 (220118111980100003,402,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100004,40202,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100005,20105,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100006,20104,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100007,20103,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100008,20102,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100009,20101,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100010,201,2201181119100002,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220118111980100011,2,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100012,1,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100013,3,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100014,40204,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100015,202,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100016,20201,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100017,20202,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100018,203,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100019,20301,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100020,20302,2201181119100002,1,'2023-05-28 01:21:48.143');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (220118111980100021,301,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100022,30101,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100023,30102,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100024,30103,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (220118111980100025,302,2201181119100002,1,'2023-05-28 01:21:48.143'),
	 (2306071128301064,1,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301065,2,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301066,202,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301067,20201,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301068,20202,2205101711100002,1,'2023-06-07 11:28:30.330');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (2306071128301069,20203,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301070,205,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301071,20501,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301072,20502,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301073,20503,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301074,20504,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301075,207,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301076,20703,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301077,20701,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301078,20702,2205101711100002,1,'2023-06-07 11:28:30.330');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (2306071128301079,20704,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301080,4,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301081,401,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301082,40104,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301083,40105,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301084,402,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301085,40204,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301086,40202,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301087,40205,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301088,2306061317111000,2205101711100002,1,'2023-06-07 11:28:30.330');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (2306071128301089,2305312137051000,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301090,2306071059211044,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301091,2306071059581045,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301092,2306071100531046,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301093,2306071101221047,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301094,2306071102041048,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301095,2306071103001049,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301096,2306071103581050,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301097,2306071104431051,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301098,2305281726041000,2205101711100002,1,'2023-06-07 11:28:30.330');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (2306071128301099,2306071054451040,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301100,2306071055271041,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301101,2306071056141042,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301102,2306071057061043,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301103,40106,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301104,2306071035211000,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301105,2306071036061001,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301106,2306071037411002,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301107,2306071038151003,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301108,2306071050181034,2205101711100002,1,'2023-06-07 11:28:30.330');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (2306071128301109,2306071050571035,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301110,2306071051311036,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301111,2306071052151037,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301112,2306071052451038,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301113,2306071054101039,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301114,2305252217261000,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301115,2306040014281000,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301116,2306071117001052,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301117,2306071117421053,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301118,2306071118051054,2205101711100002,1,'2023-06-07 11:28:30.330');
INSERT INTO mee_admin.sys_role_menu (id,menu_id,role_id,create_by,create_time) VALUES
	 (2306071128301119,2306071118301055,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301120,2306071120161056,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301121,2306071120451057,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301122,2306071122221058,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301123,2306071122481059,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301124,2306071123171060,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301125,2306071125011061,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301126,2306071125291062,2205101711100002,1,'2023-06-07 11:28:30.330'),
	 (2306071128301127,2306071126091063,2205101711100002,1,'2023-06-07 11:28:30.330');

CREATE TABLE "mee_admin"."sys_role_user" (
  "id" int8 primary key,
  "user_id" int8 NOT NULL,
  "role_id" int8 NOT NULL,
  "create_by" int8,
  "create_time" timestamp not null
);

COMMENT ON TABLE "mee_admin"."sys_role_user" IS '系统::角色用户表';
COMMENT ON COLUMN "mee_admin"."sys_role_user"."user_id" IS '用户ID';
COMMENT ON COLUMN "mee_admin"."sys_role_user"."role_id" IS '角色ID';
COMMENT ON COLUMN "mee_admin"."sys_role_user"."create_by" IS '创建人';
COMMENT ON COLUMN "mee_admin"."sys_role_user"."create_time" IS '创建时间';

INSERT INTO mee_admin.sys_role_user (id,user_id,role_id,create_by,create_time) VALUES
	 (201127152080100001,160369509280019000,2010261453100047,1,'2023-05-28 01:35:54.978'),
	 (201127152880100001,160369509280019000,2011271522100068,1,'2023-05-28 01:35:54.978'),
	 (210107144301100108,161000029780011000,2101071443100107,1,'2023-05-28 01:35:54.978'),
	 (210113093601104295,160888911880011000,2101130936104294,1,'2023-05-28 01:35:54.978'),
	 (210719113980100328,160281680680019000,2107191139100327,1,'2023-05-28 01:35:54.978'),
	 (210719113980100329,160939731380011001,2107191139100327,1,'2023-05-28 01:35:54.978'),
	 (210719113980100330,161603004480011000,2107191139100327,1,'2023-05-28 01:35:54.978'),
	 (210719113980100331,161966714280011000,2107191139100327,1,'2023-05-28 01:35:54.978'),
	 (210719113980100332,162313684780011000,2107191139100327,1,'2023-05-28 01:35:54.978'),
	 (210408094001104803,161726665280011000,2104080940104802,1,'2023-05-28 01:35:54.978');
INSERT INTO mee_admin.sys_role_user (id,user_id,role_id,create_by,create_time) VALUES
	 (210408094001104804,161784598780011000,2104080940104802,1,'2023-05-28 01:35:54.978'),
	 (210412181501100491,160992657280011002,2104121815100490,1,'2023-05-28 01:35:54.978'),
	 (210412181501100492,160992649780011001,2104121815100490,1,'2023-05-28 01:35:54.978'),
	 (210412181501100493,3,2104121815100490,1,'2023-05-28 01:35:54.978'),
	 (210412181501100494,161767261980011000,2104121815100490,1,'2023-05-28 01:35:54.978'),
	 (210412181501100495,161767266280011001,2104121815100490,1,'2023-05-28 01:35:54.978'),
	 (210412181501100496,161793361380011000,2104121815100490,1,'2023-05-28 01:35:54.978'),
	 (210413151901103478,161051692680011000,2104131519103477,1,'2023-05-28 01:35:54.978'),
	 (210413151901103479,161102240880011000,2104131519103477,1,'2023-05-28 01:35:54.978'),
	 (210413151901103480,161605787580011001,2104131519103477,1,'2023-05-28 01:35:54.978');
INSERT INTO mee_admin.sys_role_user (id,user_id,role_id,create_by,create_time) VALUES
	 (210413151901103481,161829606280011000,2104131519103477,1,'2023-05-28 01:35:54.978'),
	 (210512135280100003,160628674600801000,2105121352100002,1,'2023-05-28 01:35:54.978'),
	 (221108162501100058,161640203380011000,2211081625100056,1,'2023-05-28 01:35:54.978'),
	 (221108162501100060,164238498300801000,2211081625100056,1,'2023-05-28 01:35:54.978'),
	 (220325182180100308,160369509280019000,2203251821100307,1,'2023-05-28 01:35:54.978'),
	 (220325182180100309,162035287280011000,2203251821100307,1,'2023-05-28 01:35:54.978'),
	 (220325182180100456,160369509280019000,2203251821100455,1,'2023-05-28 01:35:54.978'),
	 (220510171180100003,1,2205101711100002,1,'2023-05-28 01:35:54.978'),
	 (220510171180100004,164238498300801000,2205101711100002,1,'2023-05-28 01:35:54.978'),
	 (220510171180100005,162313684780011000,2205101711100002,1,'2023-05-28 01:35:54.978');
INSERT INTO mee_admin.sys_role_user (id,user_id,role_id,create_by,create_time) VALUES
	 (2306060948261000,161838568080011000,2201181119100002,1,'2023-06-06 09:48:26.707'),
	 (2306060950521001,161603004480011000,2201181119100002,1,'2023-06-06 09:50:52.998'),
	 (2306060950531002,161519267780011001,2201181119100002,1,'2023-06-06 09:50:52.998'),
	 (2306060950531003,161404345580011000,2201181119100002,1,'2023-06-06 09:50:52.998'),
	 (2306060950591004,1,2201181119100002,1,'2023-06-06 09:50:59.355'),
	 (2306060959211005,161110935880011000,2201181119100002,1,'2023-06-06 09:59:21.619'),
	 (2306060959341006,160973949180011000,2201181119100002,1,'2023-06-06 09:59:34.707'),
	 (2306060959341007,160992643480011000,2201181119100002,1,'2023-06-06 09:59:34.707'),
	 (2306060959501008,160750575380011000,2201181119100002,1,'2023-06-06 09:59:50.173'),
	 (2306060959501009,160888911880011000,2201181119100002,1,'2023-06-06 09:59:50.173');
INSERT INTO mee_admin.sys_role_user (id,user_id,role_id,create_by,create_time) VALUES
	 (2306060959501010,160914442780011000,2201181119100002,1,'2023-06-06 09:59:50.173'),
	 (2306060959501011,160939731380011001,2201181119100002,1,'2023-06-06 09:59:50.173'),
	 (2306061000011012,164247652300801000,2201181119100002,1,'2023-06-06 10:00:01.372'),
	 (2306061000101013,161767261980011000,2201181119100002,1,'2023-06-06 10:00:10.268'),
	 (2306061001591014,160689294880011004,2101071443100107,1,'2023-06-06 10:01:59.508'),
	 (2306061001591015,160628674600801000,2101071443100107,1,'2023-06-06 10:01:59.508'),
	 (2306061001591016,160619769300801000,2101071443100107,1,'2023-06-06 10:01:59.508'),
	 (2306061001591017,160445277280011000,2101071443100107,1,'2023-06-06 10:01:59.508'),
	 (2306061001591018,160369509280019000,2101071443100107,1,'2023-06-06 10:01:59.508'),
	 (2306061001591019,160281680680019000,2101071443100107,1,'2023-06-06 10:01:59.508');
INSERT INTO mee_admin.sys_role_user (id,user_id,role_id,create_by,create_time) VALUES
	 (2306061001591020,160121139280019000,2101071443100107,1,'2023-06-06 10:01:59.508'),
	 (2306061001591021,160092623080019000,2101071443100107,1,'2023-06-06 10:01:59.508'),
	 (2306061001591022,157925105000801000,2101071443100107,1,'2023-06-06 10:01:59.508'),
	 (2306061001591023,3,2101071443100107,1,'2023-06-06 10:01:59.508'),
	 (2306061001591024,1,2101071443100107,1,'2023-06-06 10:01:59.508'),
	 (2306061032441025,164247652300801000,2211081625100056,1,'2023-06-06 10:32:44.063'),
	 (2306061032441027,162313684780011000,2211081625100056,1,'2023-06-06 10:32:44.063'),
	 (2306061032441028,162035287280011000,2211081625100056,1,'2023-06-06 10:32:44.063'),
	 (2306061032441029,161966714280011000,2211081625100056,1,'2023-06-06 10:32:44.063'),
	 (2306061032441030,161838568080011000,2211081625100056,1,'2023-06-06 10:32:44.063');
INSERT INTO mee_admin.sys_role_user (id,user_id,role_id,create_by,create_time) VALUES
	 (2306061032441031,161830720080011000,2211081625100056,1,'2023-06-06 10:32:44.063'),
	 (2306061032441032,161829606280011000,2211081625100056,1,'2023-06-06 10:32:44.063'),
	 (2306061032441033,161820545280011000,2211081625100056,1,'2023-06-06 10:32:44.063'),
	 (2306061032441034,161793361380011000,2211081625100056,1,'2023-06-06 10:32:44.063'),
	 (2306061032441035,161784598780011000,2211081625100056,1,'2023-06-06 10:32:44.063'),
	 (2306061032541036,161767266280011001,2211081625100056,1,'2023-06-06 10:32:54.965'),
	 (2306061032541037,161767261980011000,2211081625100056,1,'2023-06-06 10:32:54.965'),
	 (2306061033421000,160628674600801000,2211081625100056,1,'2023-06-06 10:33:42.936'),
	 (2306061033421001,160445277280011000,2211081625100056,1,'2023-06-06 10:33:42.936'),
	 (2306061033421002,1,2211081625100056,1,'2023-06-06 10:33:42.936');
INSERT INTO mee_admin.sys_role_user (id,user_id,role_id,create_by,create_time) VALUES
	 (2306061033531003,160992657280011002,2211081625100056,1,'2023-06-06 10:33:53.657'),
	 (2306061033561004,161603004480011000,2211081625100056,1,'2023-06-06 10:33:56.142'),
	 (2306061033581005,161726665280011000,2211081625100056,1,'2023-06-06 10:33:58.173'),
	 (2306061047451006,161102240880011000,2211081625100056,1,'2023-06-06 10:47:45.260'),
	 (2306061047581007,162035287280011000,2203251821100455,1,'2023-06-06 10:47:58.004'),
	 (2306061101361000,161640203380011000,2203251821100455,1,'2023-06-06 11:01:36.752'),
	 (2306061102351002,160121139280019000,2211081625100056,1,'2023-06-06 11:02:35.865');

DROP TABLE IF EXISTS mee_admin."sys_user";
CREATE TABLE mee_admin."sys_user" (
  "id" int8 PRIMARY KEY,
  "dept_id" int8,
  "user_name" varchar(50) NOT NULL,
  "nick_name" varchar(50),
  "gender" varchar(1),
  "phone" varchar(22),
  "email" varchar(50) ,
  "password" varchar(256) NOT NULL,
  "register_date" timestamp(6) NOT NULL,
  "last_login_date" timestamp(6),
  "pwd_reset_time" timestamp(6),
  "status" numeric(1) NOT NULL,
  "del_flag" numeric(1) NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "create_by" varchar(50),
  "update_time" timestamp(6) NOT NULL,
  "update_by" varchar(50)
);

COMMENT ON COLUMN mee_admin."sys_user"."id" IS '表ID/用户ID';
COMMENT ON COLUMN mee_admin."sys_user"."dept_id" IS '部门ID(保留字段暂不使用)';
COMMENT ON COLUMN mee_admin."sys_user"."user_name" IS '用户名称';
COMMENT ON COLUMN mee_admin."sys_user"."nick_name" IS '用户昵称';
COMMENT ON COLUMN mee_admin."sys_user"."gender" IS 'M.男 F.女';
COMMENT ON COLUMN mee_admin."sys_user"."phone" IS '手机号码';
COMMENT ON COLUMN mee_admin."sys_user"."email" IS '用户email(可用于登陆)';
COMMENT ON COLUMN mee_admin."sys_user"."password" IS '用户密码';
COMMENT ON COLUMN mee_admin."sys_user"."register_date" IS '用户注册时间';
COMMENT ON COLUMN mee_admin."sys_user"."last_login_date" IS '最后登录时间';
COMMENT ON COLUMN mee_admin."sys_user"."pwd_reset_time" IS '密码最后重置时间';
COMMENT ON COLUMN mee_admin."sys_user"."status" IS '状态1.启用 0.禁用';
COMMENT ON COLUMN mee_admin."sys_user"."del_flag" IS '删除标记1.正常 0.删除';
COMMENT ON COLUMN mee_admin."sys_user"."create_time" IS '创建时间';
COMMENT ON COLUMN mee_admin."sys_user"."create_by" IS '创建人';
COMMENT ON COLUMN mee_admin."sys_user"."update_time" IS '创建时间';
COMMENT ON COLUMN mee_admin."sys_user"."update_by" IS '创建人';
COMMENT ON TABLE mee_admin."sys_user" IS '系统::用户信息表';

INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (161456761080011000, NULL, 'N183006', '林仙儿', 'F', '18854783006', 'N183006@mee-admin.me', 'df10ef8509dc176d733d59549e7dbfaf', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (161640203380011000, NULL, 'H183872', '赵敏', 'F', '13325203872', 'H183872@mee-admin.me', 'df10ef8509dc176d733d59549e7dbfaf', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (161516867680011000, NULL, 'K454320', '阿飞', 'M', '13325204308', 'K454320@mee-admin.me', 'df10ef8509dc176d733d59549e7dbfaf', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '0', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (161820545280011000, NULL, 'K454322', '任我行', 'M', '18854784320', 'K454322@mee-admin.me', 'df10ef8509dc176d733d59549e7dbfaf', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (161405991780011001, NULL, 'L454325', '水笙', 'F', '13325204322', 'L454325@mee-admin.me', 'df10ef8509dc176d733d59549e7dbfaf', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '0', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (161404345580011000, NULL, 'Z454394', '帝释天', 'M', '13657894325', 'Z454394@mee-admin.me', '3bf2612bf2f595481897c675cc7ae915', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (161829606280011000, NULL, 'Z094395', '叶丽娜', 'F', '13325204394', 'Z094395@mee-admin.me', 'e040a1080a3a414a3aa29b6990d93b57', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (161784598780011000, NULL, 'Z094401', '紫衣侯', 'M', '13325204395', 'Z094401@mee-admin.me', 'b1b35ecceb709edc2b136a2ee10d5c81', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '0', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (161830720080011000, NULL, 'Z094604', '程灵素', 'F', '18854784401', 'Z094604@mee-admin.me', '8e72de6cc33e7a46e324a9c7db59472f', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (161966714280011000, NULL, 'Z096123', '岳不群', 'F', '18854784604', 'Z096123@mee-admin.me', '57356f900f91b04ceebeb5aaf1d59fb8', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (166789588780011000, NULL, 'Y096554', '李寻欢', 'M', '13325206123', 'Y096554@mee-admin.me', '6b9d38e73066d0f607c32ec7aed617e5f2d8855207bea57ef14ec49f1e4d554b6bbac7479e77c9e6b58209422d731484d891c7a8efd6b81b93f8baf065c7a8b7', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-06-02 16:44:43.039', '0', '1', '2023-05-30 05:50:41.886', '1', '2023-06-02 16:44:43.039', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (160939731380011001, NULL, 'J182674', '成是非(程成)', 'F', '18854782674', '7002674@mee.me', 'e10adc3949ba59abbe56e057f20f883e', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (160445277280011000, NULL, 'J182990', '石破天 ', 'M', '13325202990', '7002990@mee.me', 'e10adc3949ba59abbe56e057f20f883e', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (160121139280019000, NULL, 'N183049', '谢烟客', 'M', '18854783049', '7003049@mee.me', 'df10ef8509dc176d733d59549e7dbfaf', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (160992643480011000, NULL, 'N183284', '薛采月', 'F', '13325203284', '7003284@mee.me', 'df10ef8509dc176d733d59549e7dbfaf', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (161181835980011001, NULL, 'N183362', '袁紫霞', 'F', '13325203362', '7003362@mee.me', '3b433860e24e1d8b6b871c3f6b276f89', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (160992661280011003, NULL, 'H183826', '无名', 'M', '18755553826', '7003826@mee.me', 'df10ef8509dc176d733d59549e7dbfaf', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (161110935880011000, NULL, 'J183965', '高老大', 'F', '13325203965', '7003965@mee.me', 'df10ef8509dc176d733d59549e7dbfaf', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (161000690380011000, NULL, 'J183998', '血刀老祖', 'M', '13325203968', '7003968@mee.me', 'e10adc3949ba59abbe56e057f20f883e', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (160992649780011001, NULL, 'J284062', '诸葛一笑', 'M', '13657893998', '7003998@mee.me', 'df10ef8509dc176d733d59549e7dbfaf', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '0', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (160888911880011000, NULL, 'L284284', '方可可', 'F', '13325204153', '7004153@mee.me', 'e10adc3949ba59abbe56e057f20f883e', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (161232166980011000, NULL, 'K284308', '宋终', 'M', '18854784284', '7004284@mee.me', 'df10ef8509dc176d733d59549e7dbfaf', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '0', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (160914442780011000, NULL, 'H183642', '戚芳', 'F', '13325203642', '7003642@mail.cn', 'e10adc3949ba59abbe56e057f20f883e', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (164247652300801000, NULL, 'test', '蔡语袂', 'F', '18371086755', 'test@mee-admin.me', 'a7e2a48da5e0b8bb6da6d0c240765c1d0a184a2edb9a906a36a991bb3a3c96ff3692daf11338d7e598462cdc2fcc842aa9ad865a83fafc8818f521621063df17', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-06-09 18:14:05.367918', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (1, NULL, 'admin', '超级管理员', 'M', '13816349201', 'admin@mail.com', '039df5f4fe7f76ce0251bec67ceea39747284c1a68d6a0bb215a830a7eb47cfdb52e90aecaff84574180a6318a3f43e0333e594059de4783123380ce7cfc28d1', '2023-05-30 05:50:41.886', '2023-06-11 19:22:12.690766', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (162614362600801000, NULL, 'default', '第三梦', 'F', '136578984411', '136578984411@mail.com', 'ec9c1820c000201a45e41f06701fba0109177f91c705e327c30c463de2375acdb54ed7e3821423bc952be7ef19f2cae836abc79250f13d723c6d10d929002eb5', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '1', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (2306111723021000, NULL, 'J7877', '诸葛丑', '', '', NULL, '1a734c08892dcabc31875ab016d31a1f93bd098bf68455c3b301d2d05c022cb22d8b0f44fc77500a055806e4661b9a1ce358b44105524b81d3f3b355da606b5d', '2023-06-11 17:23:02.359984', NULL, '2023-06-11 17:23:02.359984', '0', '0', '2023-06-11 17:23:02.359984', '1', '2023-06-11 17:23:02.359984', '1');
INSERT INTO "mee_admin"."sys_user" ("id", "dept_id", "user_name", "nick_name", "gender", "phone", "email", "password", "register_date", "last_login_date", "pwd_reset_time", "status", "del_flag", "create_time", "create_by", "update_time", "update_by") VALUES (161000029780011000, NULL, 'L284153', '灭绝师太', 'F', '13325204062', '7004062@mee.me', 'df10ef8509dc176d733d59549e7dbfaf', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '2023-05-30 05:50:41.886', '0', '1', '2023-05-30 05:50:41.886', '1', '2023-05-30 05:50:41.886', '1');

DROP TABLE IF EXISTS MEE_ADMIN.T_OFFLINE_STORE;
CREATE TABLE MEE_ADMIN.T_OFFLINE_STORE (
	ID INT8 PRIMARY KEY,
	OPEN_DATE DATE NOT NULL,
	CODE VARCHAR(50) NOT NULL,
	NAME VARCHAR(100) NOT NULL,
	NICK_NAME VARCHAR(100),
	ADDR VARCHAR(255),
	BRAND INT2 ,
	STATUS INT2 NOT NULL,
	DESCRIPTION varchar(255),
    CREATE_BY    INT8 NOT NULL,
    CREATE_TIME  TIMESTAMP(6) NOT NULL,
    UPDATE_BY    INT8,
    UPDATE_TIME  TIMESTAMP(6)
);

COMMENT ON TABLE  mee_admin."t_offline_store" IS '测试::线下店铺';
COMMENT ON COLUMN mee_admin."t_offline_store".ID IS '主键';
COMMENT ON COLUMN mee_admin."t_offline_store".OPEN_DATE IS '开店时间';
COMMENT ON COLUMN mee_admin."t_offline_store".CODE IS '编号';
COMMENT ON COLUMN mee_admin."t_offline_store".NAME IS '名称';
COMMENT ON COLUMN mee_admin."t_offline_store".NICK_NAME IS '店铺简称或昵称';
COMMENT ON COLUMN mee_admin."t_offline_store".ADDR IS '店铺地址';
COMMENT ON COLUMN mee_admin."t_offline_store".BRAND IS '品牌(从字典获取)';
COMMENT ON COLUMN mee_admin."t_offline_store".STATUS IS '状态 0.关闭 1.开启';
COMMENT ON COLUMN mee_admin."t_offline_store".DESCRIPTION IS '备注';
COMMENT ON COLUMN mee_admin."t_offline_store".UPDATE_BY IS '更新人';
COMMENT ON COLUMN mee_admin."t_offline_store".UPDATE_TIME IS '更新时间';
COMMENT ON COLUMN mee_admin."t_offline_store".CREATE_BY IS '创建人';
COMMENT ON COLUMN mee_admin."t_offline_store".CREATE_TIME IS '创建时间';

INSERT INTO mee_admin.t_offline_store (id,open_date,code,"name",nick_name,addr,brand,status,description,create_by,create_time,update_by,update_time) VALUES
	 (230616151615801000,'2023-06-01','C001','西藏南路店','xznl','黄浦区西藏南路102号',1,1,'靠近地铁 集合店',1,'2023-06-16 15:16:15.986',1,'2023-06-16 15:16:15.986'),
	 (230616151747801001,'2018-05-10','C002','襄阳路店','xyl','徐汇区淮海中路1008号',3,1,' 旗舰店',1,'2023-06-16 15:17:47.000',1,'2023-06-16 15:18:28.758'),
	 (230616152253801002,'2019-09-09','C003','来福士广场店','lfs','长宁区长宁路1191号',NULL,1,'老店，待装修',1,'2023-06-16 15:22:53.598',1,'2023-06-16 15:22:53.598'),
	 (230616155255801000,'2023-06-01','C0034','测试01店','cs01','某某区某某路8号',NULL,0,'',1,'2023-06-16 15:52:55.706',1,'2023-06-16 15:52:55.706'),
	 (230616155255801011,'2020-01-01','C0035','测试02店','cs02','某某区某某路9号',NULL,0,'',1,'2023-06-16 15:55:03.712',1,'2023-06-16 15:55:03.712'),
	 (230616155255801012,'2020-02-01','C0036','测试03店','cs03','某某区某某路10号',NULL,0,'',1,'2023-06-16 15:55:03.712',1,'2023-06-16 15:55:03.712'),
	 (230616155255801013,'2020-03-01','C0037','测试04店','cs04','某某区某某路11号',NULL,0,'',1,'2023-06-16 15:55:03.712',1,'2023-06-16 15:55:03.712');