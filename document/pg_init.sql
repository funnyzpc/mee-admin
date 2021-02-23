
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
INSERT INTO "public"."sys_menu" VALUES (202009152040590080100000, '其他', 1, '2020-09-15 20:40:59.044', 'sys', '05', NULL);
INSERT INTO "public"."sys_menu" VALUES (202009161158140080100002, '测试01', 0, '2020-09-16 11:58:14.818', 'sys', '0501', '05');
INSERT INTO "public"."sys_menu" VALUES (202009161542570080100000, '角色&用户', 1, '2020-09-16 15:42:57.103', 'sys', '040104', '0401');
INSERT INTO "public"."sys_menu" VALUES (202009161543270080100001, '角色&菜单', 0, '2020-09-16 15:43:27.246', 'sys', '040105', '0401');
INSERT INTO "public"."sys_menu" VALUES (202009161546370080100002, '监控管理', 0, '2020-09-16 15:46:37.296', 'sys', '040203', '0402');
INSERT INTO "public"."sys_menu" VALUES (202009161201520080100005, '测试02++', 1, '2020-09-16 12:01:52.733', 'sys', '0502', '05');
INSERT INTO "public"."sys_menu" VALUES (202009161940380080100000, '测试03', 1, '2020-09-16 19:40:38.282119', 'sys', '0503', '05');

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
INSERT INTO "public"."sys_role" VALUES (1, 'ROLE_admin', 1, '2020-09-16 16:16:00.099', '2020-09-16 16:16:00.099', '管理员组');
INSERT INTO "public"."sys_role" VALUES (202009161819220080100000, 'ROLE_guest', 1, '2020-09-16 18:19:22.452353', '2020-09-16 18:19:22.452353', '访客组');
INSERT INTO "public"."sys_role" VALUES (2, 'ROLE_test', 1, '2020-09-14 16:45:33.099294', '2020-09-16 19:41:32.181096', '测试用户组');

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
INSERT INTO "public"."sys_role_menu" VALUES (202009161819440080100004, 202009161819220080100000, '02');
INSERT INTO "public"."sys_role_menu" VALUES (202009161819440080100005, 202009161819220080100000, '01');
INSERT INTO "public"."sys_role_menu" VALUES (202009161819440080100006, 202009161819220080100000, '0201');
INSERT INTO "public"."sys_role_menu" VALUES (202009161819440080100007, 202009161819220080100000, '04');
INSERT INTO "public"."sys_role_menu" VALUES (202009161819440080100008, 202009161819220080100000, '03');
INSERT INTO "public"."sys_role_menu" VALUES (202009161819440080100009, 202009161819220080100000, '0401');
INSERT INTO "public"."sys_role_menu" VALUES (202009161819440080100010, 202009161819220080100000, '05');
INSERT INTO "public"."sys_role_menu" VALUES (202009161819440080100012, 202009161819220080100000, '0402');
INSERT INTO "public"."sys_role_menu" VALUES (1, 1, '040101');
INSERT INTO "public"."sys_role_menu" VALUES (2, 1, '04');
INSERT INTO "public"."sys_role_menu" VALUES (3, 1, '03');
INSERT INTO "public"."sys_role_menu" VALUES (4, 1, '02');
INSERT INTO "public"."sys_role_menu" VALUES (5, 1, '01');
INSERT INTO "public"."sys_role_menu" VALUES (6, 1, '0402');
INSERT INTO "public"."sys_role_menu" VALUES (7, 1, '0401');
INSERT INTO "public"."sys_role_menu" VALUES (8, 1, '040103');
INSERT INTO "public"."sys_role_menu" VALUES (9, 1, '040102');
INSERT INTO "public"."sys_role_menu" VALUES (10, 1, '040201');
INSERT INTO "public"."sys_role_menu" VALUES (11, 1, '040202');
INSERT INTO "public"."sys_role_menu" VALUES (12, 1, '0201');
INSERT INTO "public"."sys_role_menu" VALUES (13, 1, '020101');
INSERT INTO "public"."sys_role_menu" VALUES (14, 1, '020103');
INSERT INTO "public"."sys_role_menu" VALUES (15, 1, '020104');
INSERT INTO "public"."sys_role_menu" VALUES (16, 1, '020105');
INSERT INTO "public"."sys_role_menu" VALUES (17, 1, '020102');
INSERT INTO "public"."sys_role_menu" VALUES (18, 1, '05');
INSERT INTO "public"."sys_role_menu" VALUES (24, 1, '040203');
INSERT INTO "public"."sys_role_menu" VALUES (202009161941400080100002, 2, '0402');
INSERT INTO "public"."sys_role_menu" VALUES (202009161941400080100003, 2, '040201');
INSERT INTO "public"."sys_role_menu" VALUES (202009161941400080100004, 2, '040202');
INSERT INTO "public"."sys_role_menu" VALUES (202009161941400080100005, 2, '05');
INSERT INTO "public"."sys_role_menu" VALUES (202009161941400080100006, 2, '020105');
INSERT INTO "public"."sys_role_menu" VALUES (202009161941400080100007, 2, '020104');
INSERT INTO "public"."sys_role_menu" VALUES (202009161941400080100008, 2, '020103');
INSERT INTO "public"."sys_role_menu" VALUES (202009161941400080100009, 2, '020102');
INSERT INTO "public"."sys_role_menu" VALUES (202009161941400080100010, 2, '020101');
INSERT INTO "public"."sys_role_menu" VALUES (202009161941400080100011, 2, '0201');
INSERT INTO "public"."sys_role_menu" VALUES (202009161941400080100012, 2, '02');
INSERT INTO "public"."sys_role_menu" VALUES (202009161941400080100013, 2, '01');
INSERT INTO "public"."sys_role_menu" VALUES (202009161941400080100014, 2, '03');
INSERT INTO "public"."sys_role_menu" VALUES (202009161941400080100015, 2, '0503');
INSERT INTO "public"."sys_role_menu" VALUES (202009161941400080100016, 2, '0502');

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
INSERT INTO "public"."sys_role_user" VALUES (1, '1', 1);
INSERT INTO "public"."sys_role_user" VALUES (202009161819410080100001, '157925105000801000', 202009161819220080100000);
INSERT INTO "public"."sys_role_user" VALUES (202009161941390080100001, '2', 2);

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
  "status" numeric(1) NOT NULL,
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
COMMENT ON COLUMN "public"."sys_user"."status" IS '用户登录状态';
COMMENT ON COLUMN "public"."sys_user"."create_date" IS '创建时间';
COMMENT ON COLUMN "public"."sys_user"."create_by" IS '创建人';
COMMENT ON TABLE "public"."sys_user" IS '系统::用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO "public"."sys_user" VALUES (202001171650550080100001, '157925105000801000', '帅157925105000801000', 'guest', 'guest@mee.com', '084e0343a0486ff05530df6c705c8bb4', '2020-01-17 16:50:50.3', '2020-09-15 00:51:00', 1, '2020-01-17 16:50:55.244', 'sys');
INSERT INTO "public"."sys_user" VALUES (1, '1', '超级管理员', 'admin', 'admin@mail.com', 'c4ca4238a0b923820dcc509a6f75849b', '2020-01-17 16:46:28.887', '2020-09-15 00:51:00', 1, '2020-01-17 16:46:30.063', 'sys');
INSERT INTO "public"."sys_user" VALUES (2, '2', '测试用户', 'test', 'test@mail.com', '098f6bcd4621d373cade4e832627b4f6', '2020-09-14 16:44:06.609', NULL, 1, '2020-09-14 16:44:08.216996', 'sys');

-- ----------------------------
-- Primary Key structure for table sys_dict
-- ----------------------------
ALTER TABLE "public"."sys_dict" ADD CONSTRAINT "sys_dict_pkey" PRIMARY KEY ("id");

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
-- Uniques structure for table sys_user
-- ----------------------------
ALTER TABLE "public"."sys_user" ADD CONSTRAINT "su_user_name_unique" UNIQUE ("user_id");

-- ----------------------------
-- Primary Key structure for table sys_user
-- ----------------------------
ALTER TABLE "public"."sys_user" ADD CONSTRAINT "sys_user_pkey" PRIMARY KEY ("id");
