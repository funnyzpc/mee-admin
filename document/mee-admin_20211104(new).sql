
DROP TABLE IF EXISTS "public"."dicts";
CREATE TABLE "public"."dicts" (
  "id" int8 NOT NULL,
  "p_id" int8,
  "name" varchar(50) COLLATE "pg_catalog"."default"
);


INSERT INTO "public"."dicts" VALUES (1, 0, '防控点级别');
INSERT INTO "public"."dicts" VALUES (2, 0, '道路标准');
INSERT INTO "public"."dicts" VALUES (3, 0, '应急响应等级');
INSERT INTO "public"."dicts" VALUES (10000, 1, '一级');
INSERT INTO "public"."dicts" VALUES (10001, 1, '二级');
INSERT INTO "public"."dicts" VALUES (10002, 1, '三级');
INSERT INTO "public"."dicts" VALUES (10003, 1, '四级');
INSERT INTO "public"."dicts" VALUES (10004, 1, '五级');
INSERT INTO "public"."dicts" VALUES (10005, 2, '主干路');
INSERT INTO "public"."dicts" VALUES (10006, 2, '次干路');
INSERT INTO "public"."dicts" VALUES (10007, 2, '支路');
INSERT INTO "public"."dicts" VALUES (10008, 2, '城市下立交区');
INSERT INTO "public"."dicts" VALUES (10011, 10005, '边支路1');
INSERT INTO "public"."dicts" VALUES (10012, 10005, '边支路2');

-- ----------------------------
-- Table structure for dicts2
-- ----------------------------
DROP TABLE IF EXISTS "public"."dicts2";
CREATE TABLE "public"."dicts2" (
  "id" int8 NOT NULL,
  "p_id" int8,
  "name" varchar(50) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of dicts2
-- ----------------------------
INSERT INTO "public"."dicts2" VALUES (10000, 10013, '一级');
INSERT INTO "public"."dicts2" VALUES (10001, 10013, '二级');
INSERT INTO "public"."dicts2" VALUES (10002, 10013, '三级');
INSERT INTO "public"."dicts2" VALUES (10003, 10013, '四级');
INSERT INTO "public"."dicts2" VALUES (10004, 10013, '五级');
INSERT INTO "public"."dicts2" VALUES (10005, 10011, '主干路');
INSERT INTO "public"."dicts2" VALUES (10006, 10011, '次干路');
INSERT INTO "public"."dicts2" VALUES (10007, 10011, '支路');
INSERT INTO "public"."dicts2" VALUES (10008, 10011, '城市下立交区');
INSERT INTO "public"."dicts2" VALUES (10011, 99999, '防控点级别');
INSERT INTO "public"."dicts2" VALUES (10012, 99999, '应急响应等级');
INSERT INTO "public"."dicts2" VALUES (10013, 99999, '道路标准');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_dict";
CREATE TABLE "public"."sys_dict" (
  "id" numeric(24) NOT NULL,
  "series" varchar(48) COLLATE "pg_catalog"."default" NOT NULL,
  "series_desc" varchar(128) COLLATE "pg_catalog"."default" NOT NULL,
  "key" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "value" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "desc" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "del_flag" char(1) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" timestamp(6) NOT NULL,
  "create_by" varchar(48) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON TABLE "public"."sys_dict" IS '系统::字典配置表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO "public"."sys_dict" VALUES (1, 'enabled.status', '开启or关闭', '0', '关闭', '关闭状态', '0', '2019-12-17 16:59:31.056502', 'sys');
INSERT INTO "public"."sys_dict" VALUES (2, 'enabled.status', '开启or关闭', '1', '开启', '开启状态', '0', '2019-12-17 16:59:31.056502', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912211737260080100000, 'article.type', '文章类型', '1', '故事', '故事类型', '0', '2019-12-21 17:37:26.726', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912191757370080100000, 'article.type', '文章类型', '故事', '1', '故事类型', '1', '2019-12-19 17:57:37.996', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912211853340080100000, 'article.type', '文章类型', '2', '诗词', '诗词', '0', '2019-12-21 18:53:34.917', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912251639470080100000, 'yy', 'yyu', 'iii', 'ppoiii', 'ooiiiiii', '1', '2019-12-25 16:39:47.111', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912251639490080100001, 'yy', 'yyu', 'iii', 'ppoiii', 'ooiiiiii', '1', '2019-12-25 16:39:49.087', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912251639520080100005, 'yy', 'yyu', 'iii', 'ppoiii', 'ooiiiiii', '1', '2019-12-25 16:39:52.267', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912251639510080100003, 'yy', 'yyu', 'iii', 'ppoiii', 'ooiiiiii', '1', '2019-12-25 16:39:51.993', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912251639520080100004, 'yy', 'yyu', 'iii', 'ppoiii', 'ooiiiiii', '1', '2019-12-25 16:39:52.135', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912261742080080100000, 'YUYUY', '<br/>', '<hr/>', '???', 'select *& from  tt;^%*&*)(*YUYTTYR"&lt;&gt;', '1', '2019-12-26 17:42:08.291', 'sys');
INSERT INTO "public"."sys_dict" VALUES (202001031830540080100000, 'menu-level', '菜单级别', '1', '一级', '一级菜单', '0', '2020-01-03 18:30:54.6', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912261858360080100001, '88888', '88888', '88888', '0000000', '88888', '1', '2019-12-26 18:58:36.316', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912271406440080100000, 'oplll', 'mmmmmm', 'mmmmmm', 'mmmmmm', 'mmmmmm', '1', '2019-12-27 14:06:44.834', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912251639510080100002, 'yy', 'yyu', 'LLLLL', '99999', 'YYYYYL', '1', '2019-12-25 16:39:51.097', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912261747120080100001, 'ggggg', 'ggggg', 'ggggg', 'ggggg', 'ggggg', '1', '2019-12-26 17:47:12.902', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912261858140080100000, '77777', '77777', '77777', '77777', '77777', '1', '2019-12-26 18:58:14.423', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912261747440080100002, 'bbbbbb', 'bbbbbb', 'bbbbbb', 'bbbbbb', 'bbbbbb', '1', '2019-12-26 17:47:44.008', 'sys');
INSERT INTO "public"."sys_dict" VALUES (202001031833310080100001, 'menu-level', '菜单级别', '2', '二级', '二级菜单', '0', '2020-01-03 18:33:31.963', 'sys');
INSERT INTO "public"."sys_dict" VALUES (202001031833540080100002, 'menu-level', '菜单级别', '3', '三级', '三级菜单', '0', '2020-01-03 18:33:54.12', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912271734550080100003, '离开就开了交离', '离开就开了交离开解开了', '哦i', '离开就开了交离开', '离开就开了交离', '1', '2019-12-27 17:34:55.191', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912261859020080100002, '666666', '666666', '666666', '666666', '666666', '1', '2019-12-26 18:59:02.455', 'sys');
INSERT INTO "public"."sys_dict" VALUES (202001061601060080100000, 'request-method', '请求方式', 'GET', 'GET请求', 'GET请求方式', '0', '2020-01-06 16:01:06.008', 'sys');
INSERT INTO "public"."sys_dict" VALUES (202001061602230080100001, 'request-method', '请求方式', 'POST', 'POST请求', 'POST请求方式', '0', '2020-01-06 16:02:23.116', 'sys');
INSERT INTO "public"."sys_dict" VALUES (202001061603360080100002, 'request-method', '请求方式', 'DELETE', 'DELETE请求', 'DELETE请求方式', '0', '2020-01-06 16:03:36.387', 'sys');
INSERT INTO "public"."sys_dict" VALUES (201912261746190080100000, 'ppppp', 'ppppp', 'ppppp', 'ppppp', 'ppppp', '1', '2019-12-26 17:46:19.083', 'sys');
INSERT INTO "public"."sys_dict" VALUES (202001061618370080100003, 'show-flag', '是否显示', '0', '不显示', '不显示标识', '1', '2020-01-06 16:18:37.698', 'sys');
INSERT INTO "public"."sys_dict" VALUES (202001061619080080100004, 'show-flag', '是否显示', '1', '显示', '显示标识', '1', '2020-01-06 16:19:08.512', 'sys');
INSERT INTO "public"."sys_dict" VALUES (202001171559560080100001, 'user_status', '用户状态', '1', '有效用户', '用户状态', '0', '2020-01-17 15:59:56.449', 'sys');
INSERT INTO "public"."sys_dict" VALUES (202001171600370080100002, 'user_status', '用户状态', '2', '未激活用户', '用户状态', '0', '2020-01-17 16:00:37.514', 'sys');
INSERT INTO "public"."sys_dict" VALUES (202001171559300080100000, 'user_status', '用户状态', '0', '无效用户', '用户状态', '0', '2020-01-17 15:59:30.767', 'sys');
INSERT INTO "public"."sys_dict" VALUES (202009141136250080100003, 'role_status', '角色状态', '0', '关闭角色', '关闭角色', '0', '2020-09-14 11:36:25.763', 'sys');
INSERT INTO "public"."sys_dict" VALUES (202009141136500080100004, 'role_status', '角色状态', '1', '开启角色', '开启角色', '0', '2020-09-14 11:36:50.884', 'sys');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_file";
CREATE TABLE "public"."sys_file" (
  "id" numeric(24) NOT NULL,
  "original_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "file_path" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "file_type" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "category" int2 NOT NULL,
  "create_date" timestamp(6) NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."sys_file"."id" IS '文件表ID';
COMMENT ON COLUMN "public"."sys_file"."original_name" IS '上传文件名称';
COMMENT ON COLUMN "public"."sys_file"."name" IS '保存文件名';
COMMENT ON COLUMN "public"."sys_file"."file_path" IS '文件保存路径';
COMMENT ON COLUMN "public"."sys_file"."file_type" IS '文件类型';
COMMENT ON COLUMN "public"."sys_file"."category" IS '文件分类 1.手动上传 2.';
COMMENT ON COLUMN "public"."sys_file"."create_date" IS '创建时间';
COMMENT ON COLUMN "public"."sys_file"."create_by" IS '创建人';
COMMENT ON TABLE "public"."sys_file" IS '系统::文件表';

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO "public"."sys_file" VALUES (1, '报表01.xls', '001.xls', '/2020-09-20/001.xls', '.xls', 1, '2020-09-20 15:45:44', 'sys');
INSERT INTO "public"."sys_file" VALUES (2, '报表01.zip', '002.xls', '/2020-09-20/002.zip', '.zip', 1, '2020-09-18 15:45:44', 'sys');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_log";
CREATE TABLE "public"."sys_log" (
  "id" numeric(24) NOT NULL,
  "log_type" int2 NOT NULL,
  "log_title" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "log_date" timestamp(6) NOT NULL,
  "remote_address" varchar(30) COLLATE "pg_catalog"."default",
  "log_content" varchar(1024) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."sys_log"."id" IS '日志表ID';
COMMENT ON COLUMN "public"."sys_log"."log_type" IS '1.用户登录';
COMMENT ON COLUMN "public"."sys_log"."log_title" IS '日志标题';
COMMENT ON COLUMN "public"."sys_log"."log_date" IS '日志时间';
COMMENT ON COLUMN "public"."sys_log"."remote_address" IS '远程地址IP';
COMMENT ON COLUMN "public"."sys_log"."log_content" IS '日志内容';
COMMENT ON TABLE "public"."sys_log" IS '系统::日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO "public"."sys_log" VALUES (2009201804410080100001, 1, 'admin', '2020-09-20 18:04:10.999', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009201805130080100000, 1, 'admin', '2020-09-20 18:05:06.738', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009201815060080100000, 1, 'admin', '2020-09-20 18:14:59.839', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009201816150080100000, 1, 'admin', '2020-09-20 18:16:11.501', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009201818540080100001, 1, 'admin', '2020-09-20 18:18:48.252', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009201827140080100000, 1, 'admin', '2020-09-20 18:27:09.432', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009211015010080100000, 1, 'admin', '2020-09-21 10:15:01.476', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009231131030080100000, 1, 'admin', '2020-09-23 11:31:01.371', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009231135340080100000, 1, 'admin', '2020-09-23 11:35:29.439', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009231213560080100000, 1, 'admin', '2020-09-23 12:13:56.22', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009231217210080100000, 1, 'admin', '2020-09-23 12:17:21.69', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009231347550080100001, 1, 'admin', '2020-09-23 13:47:55.842', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009231352490080100000, 1, 'admin', '2020-09-23 13:52:49.706', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009231400360080100000, 1, 'admin', '2020-09-23 14:00:36.017', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009231850040080100000, 1, 'admin', '2020-09-23 18:50:04.044', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009231933560080100000, 1, 'admin', '2020-09-23 19:33:56.92', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009231937500080100000, 1, 'admin', '2020-09-23 19:37:50.876', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009231947090080100000, 1, 'admin', '2020-09-23 19:47:09.771', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009251842140080100000, 1, 'admin', '2020-09-25 18:42:14.675', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009251847230080100000, 1, 'admin', '2020-09-25 18:47:23.343', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009270932010080100000, 1, 'admin', '2020-09-27 09:32:01.532', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009270938220080100000, 1, 'admin', '2020-09-27 09:38:22.283', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009270948050080100000, 1, 'admin', '2020-09-27 09:48:05.766', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271430210080100000, 1, 'admin', '2020-09-27 14:30:21.093', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271435160080100000, 1, 'admin', '2020-09-27 14:35:16.365', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271439110080100000, 1, 'admin', '2020-09-27 14:39:11.061', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271445270080100000, 1, 'admin', '2020-09-27 14:45:27.715', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271519520080100000, 1, 'admin', '2020-09-27 15:19:52.956', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271523250080100000, 1, 'admin', '2020-09-27 15:23:25.114', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271528370080100000, 1, 'admin', '2020-09-27 15:28:37.807', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271530010080100000, 1, 'admin', '2020-09-27 15:30:01.969', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271531430080100000, 1, 'admin', '2020-09-27 15:31:43.739', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271533180080100000, 1, 'admin', '2020-09-27 15:33:18.082', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271534290080100000, 1, 'admin', '2020-09-27 15:34:29.823', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271535560080100000, 1, 'admin', '2020-09-27 15:35:56.036', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271537230080100000, 1, 'admin', '2020-09-27 15:37:23.875', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271538350080100000, 1, 'admin', '2020-09-27 15:38:35.549', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271540000080100000, 1, 'admin', '2020-09-27 15:40:00.787', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271542140080100000, 1, 'admin', '2020-09-27 15:42:14.854', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271545030080100000, 1, 'admin', '2020-09-27 15:45:03.092', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271546200080100000, 1, 'admin', '2020-09-27 15:46:20.548', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271547330080100000, 1, 'admin', '2020-09-27 15:47:33.583', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271550170080100000, 1, 'admin', '2020-09-27 15:50:17.776', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271551230080100000, 1, 'admin', '2020-09-27 15:51:23.568', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271552310080100000, 1, 'admin', '2020-09-27 15:52:31.707', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271553320080100000, 1, 'admin', '2020-09-27 15:53:32.591', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271556340080100000, 1, 'admin', '2020-09-27 15:56:34.628', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271607460080100000, 1, 'admin', '2020-09-27 16:07:46.477', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271609300080100000, 1, 'admin', '2020-09-27 16:09:30.889', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271611050080100000, 1, 'admin', '2020-09-27 16:11:05.58', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271620480080100000, 1, 'admin', '2020-09-27 16:20:48.978', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271622480080100000, 1, 'admin', '2020-09-27 16:22:48.87', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271650020080100000, 1, 'admin', '2020-09-27 16:50:02.176', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2009271651590080100000, 1, 'admin', '2020-09-27 16:51:59.811', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052019380080100000, 1, 'admin', '2020-10-05 20:19:38.738', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052048380080100000, 1, 'admin', '2020-10-05 20:48:38.055', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052055110080100000, 1, 'admin', '2020-10-05 20:55:11.429', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052058450080100000, 1, 'admin', '2020-10-05 20:58:45.715', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052103590080100000, 1, 'admin', '2020-10-05 21:03:59.407', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052106000080100000, 1, 'admin', '2020-10-05 21:06:00.938', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052121200080100000, 1, 'admin', '2020-10-05 21:21:20.244', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052126160080100000, 1, 'admin', '2020-10-05 21:26:16.901', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052133030080100000, 1, 'admin', '2020-10-05 21:33:03.896', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052135140080100000, 1, 'admin', '2020-10-05 21:35:14.221', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052137170080100000, 1, 'admin', '2020-10-05 21:37:17.491', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052149110080100000, 1, 'admin', '2020-10-05 21:49:11.257', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052220050080100000, 1, 'admin', '2020-10-05 22:20:05.701', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052221450080100000, 1, 'admin', '2020-10-05 22:21:45.388', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052224410080100000, 1, 'admin', '2020-10-05 22:24:41.616', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052247390080100000, 1, 'admin', '2020-10-05 22:47:39.721', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010052301300080100000, 1, 'admin', '2020-10-05 23:01:30.446', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010061252560080100000, 1, 'admin', '2020-10-06 12:52:56.34', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010061316140080100000, 1, 'admin', '2020-10-06 13:16:14.854', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010061333320080100000, 1, 'admin', '2020-10-06 13:33:32.193', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010061714360080100000, 1, 'admin', '2020-10-06 17:14:36.949', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010061727240080100000, 1, 'admin', '2020-10-06 17:27:24.568', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010061731520080100000, 1, 'admin', '2020-10-06 17:31:52.865', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010061742520080100001, 1, 'admin', '2020-10-06 17:42:52.68', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010061748190080100035, 1, 'admin', '2020-10-06 17:48:19.195', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010061807250080100036, 1, 'admin', '2020-10-06 18:07:25.78', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010061807250080100037, 1, 'admin', '2020-10-06 18:07:25.822', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010061807280080100038, 1, 'admin', '2020-10-06 18:07:28.326', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010061842000080100000, 1, 'admin', '2020-10-06 18:42:00.3', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010061844440080100000, 1, 'admin', '2020-10-06 18:44:44.279', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010061850180080100001, 1, 'admin', '2020-10-06 18:50:18.644', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010061909230080100027, 1, 'admin', '2020-10-06 19:09:23.471', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010061926120080100000, 1, 'admin', '2020-10-06 19:26:12.571', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010071151540080100000, 1, 'admin', '2020-10-07 11:51:54.145', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010071154030080100000, 1, 'admin', '2020-10-07 11:54:03.373', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010071156050080100001, 1, 'admin', '2020-10-07 11:56:05.664', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010071200400080100000, 1, 'admin', '2020-10-07 12:00:40.921', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010071433570080100000, 1, 'admin', '2020-10-07 14:33:57.728', '10.0.133.179', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010071435310080100000, 1, 'admin', '2020-10-07 14:35:31.653', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010071437200080100001, 1, 'test', '2020-10-07 14:37:20.105', '10.0.133.179', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010071448250080100002, 1, 'admin', '2020-10-07 14:48:25.22', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010071603190080100006, 1, 'admin', '2020-10-07 16:03:19.334', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010071646540080100000, 1, 'admin', '2020-10-07 16:46:54.795', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010071654020080100001, 1, 'admin', '2020-10-07 16:54:02.002', '10.0.133.179', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010081050080080100002, 1, 'admin', '2020-10-08 10:50:08.866', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010081100430080100003, 1, 'admin', '2020-10-08 11:00:43.629', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010081210290080100004, 1, 'admin', '2020-10-08 12:10:29.396', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010191031280080100000, 1, 'admin', '2020-10-19 10:31:28.138', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010191058590080100000, 1, 'admin', '2020-10-19 10:58:59.982', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010191101090080100000, 1, 'admin', '2020-10-19 11:01:09.19', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010191105250080100000, 1, 'admin', '2020-10-19 11:05:25.186', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010191127070080100000, 1, 'admin', '2020-10-19 11:27:07.263', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010191141010080100000, 1, 'admin', '2020-10-19 11:41:01.004', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010191142400080100000, 1, 'admin', '2020-10-19 11:42:40.246', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010191154270080100000, 1, 'admin', '2020-10-19 11:54:27.278', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2010191200570080100000, 1, 'admin', '2020-10-19 12:00:57.063', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082013460080100000, 1, 'admin', '2020-11-08 20:13:46.118', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082019050080100000, 1, 'admin', '2020-11-08 20:19:05.185', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082035420080100000, 1, 'admin', '2020-11-08 20:35:42.178', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082048230080100000, 1, 'admin', '2020-11-08 20:48:23.759', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082050140080100029, 1, 'admin', '2020-11-08 20:50:14.751', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082056420080100000, 1, 'admin', '2020-11-08 20:56:42.731', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082113560080100000, 1, 'admin', '2020-11-08 21:13:56.917', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082114470080100000, 1, 'admin', '2020-11-08 21:14:47.04', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082131270080100000, 1, 'admin', '2020-11-08 21:31:27.999', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082133290080100000, 1, 'admin', '2020-11-08 21:33:29.027', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082145530080100000, 1, 'admin', '2020-11-08 21:45:53.475', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082147090080100000, 1, 'admin', '2020-11-08 21:47:09.99', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082151380080100000, 1, 'admin', '2020-11-08 21:51:38.911', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082155440080100000, 1, 'admin', '2020-11-08 21:55:44.895', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082201350080100000, 1, 'admin', '2020-11-08 22:01:35.823', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082207420080100027, 1, 'guest', '2020-11-08 22:07:42.912', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011082221160080100000, 1, 'admin', '2020-11-08 22:21:16.04', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011090945480080100000, 1, 'admin', '2020-11-09 09:45:48.397', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011091013490080100000, 1, 'admin', '2020-11-09 10:13:49.455', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011091015350080100000, 1, 'admin', '2020-11-09 10:15:35.299', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011091053310080100000, 1, 'admin', '2020-11-09 10:53:31.37', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011091053310080100001, 1, 'admin', '2020-11-09 10:53:31.423', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2011091053350080100002, 1, 'admin', '2020-11-09 10:53:35.34', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2111041806410080100000, 1, 'admin', '2021-11-04 18:06:41.937', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2111041806410080100001, 1, 'admin', '2021-11-04 18:06:41.968', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2111041807000080100002, 1, 'admin', '2021-11-04 18:07:00.719', '127.0.0.1', 'user login record');
INSERT INTO "public"."sys_log" VALUES (2111041807000080100003, 1, 'admin', '2021-11-04 18:07:00.747', '127.0.0.1', 'user login record');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_menu";
CREATE TABLE "public"."sys_menu" (
  "id" numeric(24) NOT NULL,
  "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "show_flag" numeric(1) NOT NULL,
  "create_date" timestamp(6) NOT NULL,
  "create_by" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "code" varchar(8) COLLATE "pg_catalog"."default",
  "parent_code" varchar(8) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."sys_menu"."name" IS '菜单名称';
COMMENT ON COLUMN "public"."sys_menu"."show_flag" IS '是否显示(0:否 1:是)';
COMMENT ON COLUMN "public"."sys_menu"."create_date" IS '创建时间';
COMMENT ON COLUMN "public"."sys_menu"."create_by" IS '创建人';
COMMENT ON COLUMN "public"."sys_menu"."code" IS '菜单权限编号';
COMMENT ON COLUMN "public"."sys_menu"."parent_code" IS '菜单权限父编号';
COMMENT ON TABLE "public"."sys_menu" IS '系统::菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO "public"."sys_menu" VALUES (202008291857220080100002, '用户配置', 1, '2020-08-29 18:57:22.669', 'sys', '040101', '0401');
INSERT INTO "public"."sys_menu" VALUES (202001061654540080100005, '系统', 1, '2020-01-06 16:54:54.02', 'sys', '04', NULL);
INSERT INTO "public"."sys_menu" VALUES (202001061652550080100004, '报表', 1, '2020-01-06 16:52:55.716', 'sys', '03', NULL);
INSERT INTO "public"."sys_menu" VALUES (202001061651380080100003, '业务', 1, '2020-01-06 16:51:38.735', 'sys', '02', NULL);
INSERT INTO "public"."sys_menu" VALUES (202001061645320080100002, '主页', 1, '2020-01-06 16:45:32.321', 'sys', '01', NULL);
INSERT INTO "public"."sys_menu" VALUES (202008291856150080100001, '基础管理', 1, '2020-08-29 18:56:15.562', 'sys', '0402', '04');
INSERT INTO "public"."sys_menu" VALUES (202008291855050080100000, '系统配置', 1, '2020-08-29 18:55:05.991', 'sys', '0401', '04');
INSERT INTO "public"."sys_menu" VALUES (202008291857440080100003, '菜单配置', 1, '2020-08-29 18:57:44.087', 'sys', '040103', '0401');
INSERT INTO "public"."sys_menu" VALUES (202008291857440080100013, '角色分配', 1, '2020-08-29 18:57:44', 'sys', '040102', '0401');
INSERT INTO "public"."sys_menu" VALUES (202008291858100080100004, '字典配置', 1, '2020-08-29 18:58:10.787', 'sys', '040201', '0402');
INSERT INTO "public"."sys_menu" VALUES (202008291858450080100005, '日志配置', 1, '2020-08-29 18:58:45.927', 'sys', '040202', '0402');
INSERT INTO "public"."sys_menu" VALUES (2010071452590080100003, '测试050301', 1, '2020-10-07 14:52:59.846455', '1', '050301', '0503');
INSERT INTO "public"."sys_menu" VALUES (2010071454000080100004, '编辑权限', 1, '2020-10-07 14:54:00.595631', '1', '05030101', '050301');
INSERT INTO "public"."sys_menu" VALUES (202009152040590080100000, '其他', 1, '2020-09-15 20:40:59.044', 'sys', '05', NULL);
INSERT INTO "public"."sys_menu" VALUES (2010071454390080100005, '删除权限', 1, '2020-10-07 14:54:39.441053', '1', '05030102', '050301');
INSERT INTO "public"."sys_menu" VALUES (202009161158140080100002, '测试01', 0, '2020-09-16 11:58:14.818', 'sys', '0501', '05');
INSERT INTO "public"."sys_menu" VALUES (202009161546370080100002, '监控管理', 0, '2020-09-16 15:46:37.296', 'sys', '040203', '0402');
INSERT INTO "public"."sys_menu" VALUES (2010191057590080100001, '定时任务', 1, '2020-10-19 10:57:59.706038', '1', '040205', '0402');
INSERT INTO "public"."sys_menu" VALUES (202009161542570080100000, '角色&用户', 1, '2020-09-16 15:42:57.103', 'sys', '040104', '0401');
INSERT INTO "public"."sys_menu" VALUES (202009161543270080100001, '角色&菜单', 1, '2020-09-16 15:43:27.246', 'sys', '040105', '0401');
INSERT INTO "public"."sys_menu" VALUES (202009161201520080100005, '测试02++', 1, '2020-09-16 12:01:52.733', 'sys', '0502', '05');
INSERT INTO "public"."sys_menu" VALUES (202009161940380080100000, '测试03', 1, '2020-09-16 19:40:38.282119', 'sys', '0503', '05');
INSERT INTO "public"."sys_menu" VALUES (202009171447140080100000, '报表分类01', 1, '2020-09-17 14:47:14.534607', 'sys', '0301', '03');
INSERT INTO "public"."sys_menu" VALUES (202009171447280080100001, '报表分类02', 1, '2020-09-17 14:47:28.316033', 'sys', '0302', '03');
INSERT INTO "public"."sys_menu" VALUES (202009171447490080100002, '报表分类03', 1, '2020-09-17 14:47:49.374799', 'sys', '0303', '03');
INSERT INTO "public"."sys_menu" VALUES (202009171449010080100003, '分类01-功能1', 1, '2020-09-17 14:49:01.130965', 'sys', '030101', '0301');
INSERT INTO "public"."sys_menu" VALUES (202009171449360080100004, '分类01-功能2', 1, '2020-09-17 14:49:36.744762', 'sys', '030102', '0301');
INSERT INTO "public"."sys_menu" VALUES (202009201518070080100000, '文件管理', 1, '2020-09-20 15:18:07.36705', 'sys', '040204', '0402');
INSERT INTO "public"."sys_menu" VALUES (202009152043470080100001, '业务分类01', 1, '2020-09-15 20:43:47.14', 'sys', '0201', '02');
INSERT INTO "public"."sys_menu" VALUES (202009152045330080100002, '业务项01', 1, '2020-09-15 20:45:33.27', 'sys', '020101', '0201');
INSERT INTO "public"."sys_menu" VALUES (202009152046510080100003, '业务项02', 1, '2020-09-15 20:46:51.764', 'sys', '020102', '0201');
INSERT INTO "public"."sys_menu" VALUES (202009152047380080100004, '业务项03', 1, '2020-09-15 20:47:38.789', 'sys', '020103', '0201');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role";
CREATE TABLE "public"."sys_role" (
  "id" numeric(24) NOT NULL,
  "role_name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "status" numeric(1) NOT NULL,
  "create_date" timestamp(6) NOT NULL,
  "update_date" timestamp(6) NOT NULL,
  "role_desc" varchar(100) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."sys_role"."id" IS '表ID';
COMMENT ON COLUMN "public"."sys_role"."role_name" IS '角色名称';
COMMENT ON COLUMN "public"."sys_role"."status" IS '角色状态[0.关闭 1.开启]';
COMMENT ON COLUMN "public"."sys_role"."create_date" IS '创建时间';
COMMENT ON COLUMN "public"."sys_role"."update_date" IS '更新时间';
COMMENT ON COLUMN "public"."sys_role"."role_desc" IS '角色描述';
COMMENT ON TABLE "public"."sys_role" IS '系统::角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO "public"."sys_role" VALUES (2, 'ROLE_test', 1, '2020-09-14 16:45:33.099294', '2020-10-06 17:45:52.550563', '测试用户组');
INSERT INTO "public"."sys_role" VALUES (1, 'ROLE_admin', 1, '2020-09-16 16:16:00.099', '2020-11-08 20:50:00.228271', '管理员组');
INSERT INTO "public"."sys_role" VALUES (202009161819220080100000, 'ROLE_guest', 1, '2020-09-16 18:19:22.452353', '2020-11-08 22:06:48.380435', '访客组');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role_menu";
CREATE TABLE "public"."sys_role_menu" (
  "id" numeric(24) NOT NULL,
  "role_id" numeric(24) NOT NULL,
  "menu_code" varchar(24) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON TABLE "public"."sys_role_menu" IS '系统::角色&菜单表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100003, 202009161819220080100000, '02');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100004, 202009161819220080100000, '01');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100005, 202009161819220080100000, '0201');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100006, 202009161819220080100000, '04');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100007, 202009161819220080100000, '03');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100008, 202009161819220080100000, '0401');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100009, 202009161819220080100000, '05');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100010, 202009161819220080100000, '0402');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100011, 202009161819220080100000, '040201');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100012, 202009161819220080100000, '040202');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100013, 202009161819220080100000, '0301');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100014, 202009161819220080100000, '030101');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100015, 202009161819220080100000, '030102');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100016, 202009161819220080100000, '0302');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100017, 202009161819220080100000, '0303');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100018, 202009161819220080100000, '0502');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100019, 202009161819220080100000, '0503');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100020, 202009161819220080100000, '040204');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100021, 202009161819220080100000, '020101');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100022, 202009161819220080100000, '020102');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100023, 202009161819220080100000, '020103');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100024, 202009161819220080100000, '040205');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100025, 202009161819220080100000, '040104');
INSERT INTO "public"."sys_role_menu" VALUES (2011082206480080100026, 202009161819220080100000, '040105');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100002, 1, '040101');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100003, 1, '04');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100004, 1, '03');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100005, 1, '02');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100006, 1, '01');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100007, 1, '0402');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100008, 1, '0401');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100009, 1, '040103');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100010, 1, '040102');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100011, 1, '040201');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100012, 1, '040202');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100013, 1, '0201');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100014, 1, '05');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100015, 1, '0301');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100016, 1, '030101');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100017, 1, '030102');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100018, 1, '0302');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100019, 1, '0303');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100020, 1, '0502');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100021, 1, '0503');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100022, 1, '040204');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100023, 1, '020101');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100024, 1, '020102');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100025, 1, '020103');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100026, 1, '040205');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100027, 1, '040104');
INSERT INTO "public"."sys_role_menu" VALUES (2011082050000080100028, 1, '040105');
INSERT INTO "public"."sys_role_menu" VALUES (2010061745520080100003, 2, '0402');
INSERT INTO "public"."sys_role_menu" VALUES (2010061745520080100004, 2, '040201');
INSERT INTO "public"."sys_role_menu" VALUES (2010061745520080100005, 2, '040202');
INSERT INTO "public"."sys_role_menu" VALUES (2010061745520080100006, 2, '05');
INSERT INTO "public"."sys_role_menu" VALUES (2010061745520080100007, 2, '0201');
INSERT INTO "public"."sys_role_menu" VALUES (2010061745520080100008, 2, '02');
INSERT INTO "public"."sys_role_menu" VALUES (2010061745520080100009, 2, '01');
INSERT INTO "public"."sys_role_menu" VALUES (2010061745520080100010, 2, '03');
INSERT INTO "public"."sys_role_menu" VALUES (2010061745520080100011, 2, '0503');
INSERT INTO "public"."sys_role_menu" VALUES (2010061745520080100012, 2, '0502');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role_user";
CREATE TABLE "public"."sys_role_user" (
  "id" numeric(24) NOT NULL,
  "user_id" varchar(24) COLLATE "pg_catalog"."default" NOT NULL,
  "role_id" numeric(24) NOT NULL
)
;
COMMENT ON TABLE "public"."sys_role_user" IS '系统::角色&用户表';

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO "public"."sys_role_user" VALUES (2010061745520080100002, '2', 2);
INSERT INTO "public"."sys_role_user" VALUES (2011082050000080100001, '1', 1);
INSERT INTO "public"."sys_role_user" VALUES (2011082206480080100002, '160484434600801000', 202009161819220080100000);

-- ----------------------------
-- Table structure for sys_shedlock
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_shedlock";
CREATE TABLE "public"."sys_shedlock" (
  "name" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "label" varchar(100) COLLATE "pg_catalog"."default",
  "locked_at" timestamp(6) NOT NULL,
  "lock_until" timestamp(6) NOT NULL,
  "locked_by" varchar(100) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."sys_shedlock"."name" IS '任務名稱(即ID)';
COMMENT ON COLUMN "public"."sys_shedlock"."label" IS '任務標識';
COMMENT ON COLUMN "public"."sys_shedlock"."locked_at" IS '任務開始鎖定';
COMMENT ON COLUMN "public"."sys_shedlock"."lock_until" IS '任務鎖定至';
COMMENT ON COLUMN "public"."sys_shedlock"."locked_by" IS '任務執行人';

-- ----------------------------
-- Records of sys_shedlock
-- ----------------------------
INSERT INTO "public"."sys_shedlock" VALUES ('testTask2', '测试定时任务02', '2020-11-08 22:00:00.067', '2020-11-08 22:05:00.02', 'ZBLTP0VIP0146');
INSERT INTO "public"."sys_shedlock" VALUES ('testTask222', '测试定时任务022', '2020-10-19 11:30:00.132', '2020-10-19 11:31:00.023', 'ZBLTP0VIP0146');
INSERT INTO "public"."sys_shedlock" VALUES ('testTask333', '测试定时任务022', '2020-10-19 11:30:00.132', '2020-10-19 11:31:00.023', 'ZBLTP0VIP0146');
INSERT INTO "public"."sys_shedlock" VALUES ('testTask555', '测试定时任务022', '2020-10-19 11:30:00.132', '2020-10-19 11:31:00.023', 'ZBLTP0VIP0146');
INSERT INTO "public"."sys_shedlock" VALUES ('testTask444', '测试定时任务022', '2020-10-18 11:30:00', '2020-10-18 11:31:00', 'ZBLTP0VIP0146');
INSERT INTO "public"."sys_shedlock" VALUES ('testTask1', '测试定时任务01', '2021-11-04 18:10:00.036', '2021-11-04 18:11:00.014', 'ZBLTP0VIP0146');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user";
CREATE TABLE "public"."sys_user" (
  "id" numeric(24) NOT NULL,
  "user_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "nick_name" varchar(50) COLLATE "pg_catalog"."default",
  "user_name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "email" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(256) COLLATE "pg_catalog"."default" NOT NULL,
  "register_date" timestamp(6) NOT NULL,
  "last_login_date" timestamp(6),
  "status" int2 NOT NULL,
  "create_date" timestamp(6) NOT NULL,
  "create_by" varchar(48) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."sys_user"."id" IS '表ID';
COMMENT ON COLUMN "public"."sys_user"."user_id" IS '用户ID';
COMMENT ON COLUMN "public"."sys_user"."nick_name" IS '用户昵称';
COMMENT ON COLUMN "public"."sys_user"."user_name" IS '用户名称';
COMMENT ON COLUMN "public"."sys_user"."email" IS '用户email(可用于登陆)';
COMMENT ON COLUMN "public"."sys_user"."password" IS '用户密码';
COMMENT ON COLUMN "public"."sys_user"."register_date" IS '用户注册时间';
COMMENT ON COLUMN "public"."sys_user"."last_login_date" IS '最后登录时间';
COMMENT ON COLUMN "public"."sys_user"."status" IS '用户状态 0:无效 1:有效 2:未激活(绑定邮箱)';
COMMENT ON COLUMN "public"."sys_user"."create_date" IS '创建时间';
COMMENT ON COLUMN "public"."sys_user"."create_by" IS '创建人';
COMMENT ON TABLE "public"."sys_user" IS '系统::用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO "public"."sys_user" VALUES (2, '2', '测试用户', 'test', 'test@mail.com', '098f6bcd4621d373cade4e832627b4f6', '2020-09-14 16:44:06.609', NULL, 1, '2020-09-14 16:44:08.216996', 'sys');
INSERT INTO "public"."sys_user" VALUES (2011082205460080100001, '160484434600801000', '访客001', 'guest', 'guest@mee.com', '084e0343a0486ff05530df6c705c8bb4', '2020-11-08 22:05:46.26', NULL, 1, '2020-11-08 22:05:46.310982', '1');
INSERT INTO "public"."sys_user" VALUES (1, '1', '超级管理员', 'admin', 'admin@mail.com', 'e034fb6b66aacc1d48f445ddfb08da98', '2020-01-17 16:46:28.887', '2021-11-04 18:07:00.719', 1, '2020-01-17 16:46:30.063', 'sys');

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_article";
CREATE TABLE "public"."t_article" (
  "id" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "subject" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "picture" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "body" text COLLATE "pg_catalog"."default" NOT NULL,
  "user_id" varchar(20) COLLATE "pg_catalog"."default",
  "origin" varchar(100) COLLATE "pg_catalog"."default",
  "visited" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "post_type" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "status" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "update_date" varchar(100) COLLATE "pg_catalog"."default",
  "create_date" timestamp(6) NOT NULL
)
;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO "public"."t_article" VALUES ('100001', '假如你被送到了精神病医院，如何最快出来？', '', '假如把一个正常的你送进精神病医院，你用什么样的策略，使得你能够用最短的时间出来？我想这个时候，你最需要的是冷静，思考，观察，做出合理的策略，来证明你康复了，可以出院了。\n千万不要一味的强调，重复你没病。因为医生见多了，人人进来都说自己没病。\n你需要的不是企图证明你没病，而是你要做给医生看，你每天在康', '<div>假如把一个正常的你送进精神病医院，你用什么样的策略，使得你能够用最短的时间出来？我想这个时候，你最需要的是冷静，思考，观察，做出合理的策略，来证明你康复了，可以出院了。</div><div style=\"text-align: center;\"><img src=\"/upload/20170806/52f29f2af0053916025b85c63a3a4447.jpg\"><br></div><div>千万不要一味的强调，重复你没病。因为医生见多了，人人进来都说自己没病。</div><div><br></div><div>你需要的不是企图证明你没病，而是你要做给医生看，你每天在康复。你的策略可以这样：</div><div>第一天：吃饭就打碗，还骂人，傻笑；</div><div>第二天：在医生的监控下，你会吃东西了；</div><div>第三天：你会独自处理自己的事情了；</div><div>第四天：你会嘘寒问暖，还会关心医生了；</div><div>第五天：出院。</div><div><br></div><div><font color=\"#990033\"><i>总结：你被治好了，或者说医生被你治好了，^_^。回到我们企业也是如此，当领导认为你不OK的时候，你千万不可与之争辩，来证明自己做得好，没用的，用以上的策略去治愈你的领导，拿走不谢，^_^。</i></font></div>', '5', '爱施缘', '374', '故事', '0', NULL, '2019-12-01 12:51:52.374003');
INSERT INTO "public"."t_article" VALUES ('100002', '管人的艺术：孔子为什么教不了领导力？', '', '孔子的教育方法太高级了，他是为有悟性、聪明甚至有缘分的人准备的。他终其一生都在选择颜回、子贡这样的高级人才，他讲出的每一句话都很深刻，听不懂的就算了。听得懂的，授以衣钵。\n直到今天我们的教育部门还是不断投入资源到重点院校，而我们的基础教育令人失望至极。大家觉得呢，孔子为什么教不了领导力？', '孔子的教育方法太高级了，他是为有悟性、聪明甚至有缘分的人准备的。他终其一生都在选择颜回、子贡这样的高级人才，他讲出的每一句话都很深刻，听不懂的就算了。听得懂的，授以衣钵。<div style=\"text-align: center;\"><img src=\"/upload/20170811/fd8128c9c8838713f444b27dc832a1f1blob\"><br></div><div>直到今天我们的教育部门还是不断投入资源到重点院校，而我们的基础教育令人失望至极。大家觉得呢，孔子为什么教不了领导力？</div>', '5', '爱施缘', '377', '故事', '0', NULL, '2019-12-01 12:51:52.374003');

-- ----------------------------
-- Table structure for t_functionality
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_functionality";
CREATE TABLE "public"."t_functionality" (
  "id" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "functionality_name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "functionality_type" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "controller" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" timestamp(6) NOT NULL,
  "update_date" timestamp(6) NOT NULL
)
;

-- ----------------------------
-- Records of t_functionality
-- ----------------------------
INSERT INTO "public"."t_functionality" VALUES ('1', '添加文章', '内容管理', 'postarticle', '2019-12-01 11:27:51.773862', '2019-12-01 11:27:51.773862');
INSERT INTO "public"."t_functionality" VALUES ('2', '用户列表', '用户管理', 'accountlist', '2019-12-01 11:27:51.773862', '2019-12-01 11:27:51.773862');
INSERT INTO "public"."t_functionality" VALUES ('10', '文章列表', '内容管理', 'articlelist', '2019-12-01 11:27:51.773862', '2019-12-01 11:27:51.773862');
INSERT INTO "public"."t_functionality" VALUES ('11', '角色列表', '用户管理', 'rolelist', '2019-12-01 11:27:51.773862', '2019-12-01 11:27:51.773862');
INSERT INTO "public"."t_functionality" VALUES ('12', '权限列表', '系统管理', 'functionlist', '2019-12-01 11:27:51.773862', '2019-12-01 11:27:51.773862');
INSERT INTO "public"."t_functionality" VALUES ('13', '添加权限', '系统管理', 'postfunction', '2019-12-01 11:27:51.773862', '2019-12-01 11:27:51.773862');
INSERT INTO "public"."t_functionality" VALUES ('14', '添加角色', '系统管理', 'postrole', '2019-12-01 11:27:51.773862', '2019-12-01 11:27:51.773862');
INSERT INTO "public"."t_functionality" VALUES ('15', '角色权限配置', '系统管理', 'mappingpost', '2019-12-01 11:27:51.773862', '2019-12-01 11:27:51.773862');
INSERT INTO "public"."t_functionality" VALUES ('16', '文件上传', '内容管理', 'upload', '2019-12-01 11:27:51.773862', '2019-12-01 11:27:51.773862');
INSERT INTO "public"."t_functionality" VALUES ('25', '获取角色修改', '系统管理', 'getrole', '2019-12-01 11:27:51.773862', '2019-12-01 11:27:51.773862');
INSERT INTO "public"."t_functionality" VALUES ('26', '获取权限编辑', '系统管理', 'getfunction', '2019-12-01 11:27:51.773862', '2019-12-01 11:27:51.773862');
INSERT INTO "public"."t_functionality" VALUES ('27', '模版页面', '系统管理', 'webpack', '2019-12-01 11:27:51.773862', '2019-12-01 11:27:51.773862');
INSERT INTO "public"."t_functionality" VALUES ('28', '角色权限表', '系统管理', 'functiongroup', '2019-12-01 11:27:51.773862', '2019-12-01 11:27:51.773862');
INSERT INTO "public"."t_functionality" VALUES ('29', '获取文章编辑', '内容管理', 'getarticle', '2019-12-01 11:27:51.773862', '2019-12-01 11:27:51.773862');
INSERT INTO "public"."t_functionality" VALUES ('30', '删除文章', '内容管理', 'delarticle', '2019-12-01 11:27:51.773862', '2019-12-01 11:27:51.773862');

-- ----------------------------
-- Table structure for t_role_function_mapping
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_role_function_mapping";
CREATE TABLE "public"."t_role_function_mapping" (
  "id" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "function_id" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "role_id" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "create_date" timestamp(6) NOT NULL
)
;

-- ----------------------------
-- Records of t_role_function_mapping
-- ----------------------------
INSERT INTO "public"."t_role_function_mapping" VALUES ('1', '1', '1', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('2', '1', '2', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('3', '2', '1', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('4', '2', '2', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('5', '10', '1', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('6', '10', '2', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('7', '11', '1', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('8', '11', '2', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('9', '12', '1', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('10', '14', '1', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('11', '15', '1', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('12', '16', '1', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('13', '16', '2', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('14', '26', '1', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('15', '27', '1', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('16', '27', '2', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('17', '28', '1', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('18', '28', '2', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('19', '29', '1', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('20', '29', '2', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('21', '30', '2', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('22', '12', '2', '2019-12-01 11:35:08.776735');
INSERT INTO "public"."t_role_function_mapping" VALUES ('23', '13', '1', '2019-12-01 11:35:08.776735');

-- ----------------------------
-- Table structure for tmp
-- ----------------------------
DROP TABLE IF EXISTS "public"."tmp";
CREATE TABLE "public"."tmp" (
  "id" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(100) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of tmp
-- ----------------------------
INSERT INTO "public"."tmp" VALUES ('2010021556139000', 'I''m DB1');
INSERT INTO "public"."tmp" VALUES ('2010021556259000', 'I''m DB1');

-- ----------------------------
-- Primary Key structure for table dicts
-- ----------------------------
ALTER TABLE "public"."dicts" ADD CONSTRAINT "dicts_copy1_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table dicts2
-- ----------------------------
ALTER TABLE "public"."dicts2" ADD CONSTRAINT "dicts_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_dict
-- ----------------------------
ALTER TABLE "public"."sys_dict" ADD CONSTRAINT "sys_dict_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_file
-- ----------------------------
ALTER TABLE "public"."sys_file" ADD CONSTRAINT "sys_file_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_log
-- ----------------------------
ALTER TABLE "public"."sys_log" ADD CONSTRAINT "sys_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table sys_menu
-- ----------------------------
ALTER TABLE "public"."sys_menu" ADD CONSTRAINT "sm_code_unique" UNIQUE ("code");

-- ----------------------------
-- Primary Key structure for table sys_menu
-- ----------------------------
ALTER TABLE "public"."sys_menu" ADD CONSTRAINT "sys_menu_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role
-- ----------------------------
ALTER TABLE "public"."sys_role" ADD CONSTRAINT "t_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role_menu
-- ----------------------------
ALTER TABLE "public"."sys_role_menu" ADD CONSTRAINT "sys_role_menu_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role_user
-- ----------------------------
ALTER TABLE "public"."sys_role_user" ADD CONSTRAINT "sys_user_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_shedlock
-- ----------------------------
ALTER TABLE "public"."sys_shedlock" ADD CONSTRAINT "sys_shedlock_pk" PRIMARY KEY ("name");

-- ----------------------------
-- Uniques structure for table sys_user
-- ----------------------------
ALTER TABLE "public"."sys_user" ADD CONSTRAINT "su_user_name_unique" UNIQUE ("user_id");

-- ----------------------------
-- Primary Key structure for table sys_user
-- ----------------------------
ALTER TABLE "public"."sys_user" ADD CONSTRAINT "sys_user_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_article
-- ----------------------------
ALTER TABLE "public"."t_article" ADD CONSTRAINT "t_article_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_functionality
-- ----------------------------
ALTER TABLE "public"."t_functionality" ADD CONSTRAINT "t_functionality_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_role_function_mapping
-- ----------------------------
ALTER TABLE "public"."t_role_function_mapping" ADD CONSTRAINT "t_role_function_mapping_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table tmp
-- ----------------------------
ALTER TABLE "public"."tmp" ADD CONSTRAINT "tmp_pk" PRIMARY KEY ("id");
