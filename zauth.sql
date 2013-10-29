/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50523
Source Host           : localhost:3306
Source Database       : zauth

Target Server Type    : MYSQL
Target Server Version : 50523
File Encoding         : 65001

Date: 2013-10-29 16:54:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app`
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `app_name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_name` (`app_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES ('5', 'zauth', '权限管理系统');
INSERT INTO `app` VALUES ('8', 'tccz', '');

-- ----------------------------
-- Table structure for `app_user`
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `app_name` varchar(100) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_name` (`app_name`,`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES ('20', 'mzyl', 'dev');
INSERT INTO `app_user` VALUES ('14', 'zauth', 'dev');

-- ----------------------------
-- Table structure for `group_role`
-- ----------------------------
DROP TABLE IF EXISTS `group_role`;
CREATE TABLE `group_role` (
  `Id` int(16) NOT NULL AUTO_INCREMENT,
  `group_id` int(16) DEFAULT NULL,
  `role_id` int(16) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of group_role
-- ----------------------------

-- ----------------------------
-- Table structure for `group_user`
-- ----------------------------
DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user` (
  `Id` int(16) NOT NULL AUTO_INCREMENT,
  `group_id` int(16) NOT NULL DEFAULT '0',
  `user_id` int(16) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of group_user
-- ----------------------------

-- ----------------------------
-- Table structure for `organization`
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(255) DEFAULT NULL,
  `parent_id` int(16) unsigned zerofill DEFAULT NULL,
  `biz_name` varchar(255) DEFAULT NULL,
  `biz_id` int(11) DEFAULT NULL,
  `role_group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES ('85', '太仓民生村镇银行', '2013-10-29 13:54:35', '', '0000000000000000', '太仓民生村镇银行', '7600', '0');
INSERT INTO `organization` VALUES ('86', '风险管理部', '2013-10-29 13:55:25', '', '0000000000000085', '风险管理部', '7601', '0');
INSERT INTO `organization` VALUES ('87', '同业票据部', '2013-10-29 13:56:19', '', '0000000000000085', '同业票据部', '7602', '0');
INSERT INTO `organization` VALUES ('88', '综合管理部', '2013-10-29 13:56:33', '', '0000000000000085', '综合管理部', '7603', '0');
INSERT INTO `organization` VALUES ('89', '其他部门', '2013-10-29 16:10:42', '', '0000000000000085', '其他部门', '9699', '0');

-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `Id` int(16) unsigned NOT NULL AUTO_INCREMENT,
  `resourcemark` varchar(255) DEFAULT NULL,
  `resourcename` varchar(255) DEFAULT NULL,
  `resourcevalue` varchar(255) DEFAULT NULL,
  `resource_parent_id` int(16) DEFAULT NULL,
  `resource_type` varchar(50) DEFAULT NULL,
  `system` varchar(255) NOT NULL DEFAULT '',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `resourcemark` (`resourcemark`)
) ENGINE=InnoDB AUTO_INCREMENT=1111335 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1111255', 'app-zauth', 'zauth', 'zauth', '0', 'APP', 'zauth', '2013-05-19 21:03:29', '');
INSERT INTO `resource` VALUES ('1111257', 'zauth-page-user', '用户管理界面', '/manager/user.htm', '1111255', 'PAGE', 'zauth', '2013-05-19 21:13:00', '');
INSERT INTO `resource` VALUES ('1111258', 'zauth-page-organization', '组织管理界面', '/manager/organization.htm', '1111255', 'PAGE', 'zauth', '2013-05-19 21:16:27', '');
INSERT INTO `resource` VALUES ('1111259', 'zauth-page-role', '角色管理界面', '/manager/role.htm', '1111255', 'PAGE', 'zauth', '2013-05-19 21:19:01', '');
INSERT INTO `resource` VALUES ('1111260', 'zauth-page-resource', '资源管理', '/manager/resource.htm', '1111255', 'PAGE', 'zauth', '2013-05-19 21:20:36', '');
INSERT INTO `resource` VALUES ('1111261', 'zauth-userList', '用户列表页面', '/manager/userList.htm', '1111257', 'PAGE', 'zauth', '2013-05-19 21:35:15', '');
INSERT INTO `resource` VALUES ('1111264', 'zauth-user-add', '新增用户页面', '/manager/enterUserAdd.htm', '1111257', 'PAGE', 'zauth', '2013-05-19 22:20:42', '');
INSERT INTO `resource` VALUES ('1111265', 'zauth-user-doAdd', '新增用户保存事件', '/manager/doUserAdd.htm', '1111264', 'BUTTON', 'zauth', '2013-05-19 22:21:04', '');
INSERT INTO `resource` VALUES ('1111266', 'zauth-user-delete', '删除用户', '/manager/deleteUser.htm', '1111257', 'BUTTON', 'zauth', '2013-05-19 22:33:54', '');
INSERT INTO `resource` VALUES ('1111267', 'zauth-user-edit', '修改用户', '/manager/editUser.htm', '1111257', 'PAGE', 'zauth', '2013-05-19 22:36:18', '');
INSERT INTO `resource` VALUES ('1111268', 'zauth-user-role-assign-page', '用户角色分配界面', '/manager/enterAssignRoleToUser.htm', '1111257', 'PAGE', 'zauth', '2013-05-19 22:41:01', '');
INSERT INTO `resource` VALUES ('1111269', 'zauth-user-role-assign-event', '用户角色分配事件', '/manager/doGrantRoleToUser.json', '1111268', 'BUTTON', 'zauth', '2013-05-19 22:41:43', '');
INSERT INTO `resource` VALUES ('1111270', 'zauth-user-role-node-load', '用户角色分配树节点加载事件', '/manager/roleTreeNodesForUserGrant.json', '1111268', 'BUTTON', 'zauth', '2013-05-19 22:42:41', '');
INSERT INTO `resource` VALUES ('1111271', 'zauth-organization-add', '新增组织', '/manager/addOrganization.htm', '1111258', 'PAGE', 'zauth', '2013-05-21 22:57:42', '');
INSERT INTO `resource` VALUES ('1111272', 'zauth-organization-delete', '删除组织', '/manager/deleteOrganization.json', '1111258', 'BUTTON', 'zauth', '2013-05-21 23:01:38', '');
INSERT INTO `resource` VALUES ('1111273', 'zauth-organization-modify', '修改组织', '/manager/editOrganization.htm', '1111258', 'PAGE', 'zauth', '2013-05-21 23:02:08', '');
INSERT INTO `resource` VALUES ('1111274', 'zauth-organization-addChild', '新建子组', '/manager/addChildOrganization.htm', '1111258', 'PAGE', 'zauth', '2013-05-21 23:22:10', '');
INSERT INTO `resource` VALUES ('1111275', 'zauth-organization-view-user', '查看组织用户', '/manager/organizationUser.htm', '1111258', 'PAGE', 'zauth', '2013-05-21 23:23:58', '');
INSERT INTO `resource` VALUES ('1111276', 'zauth-organization-add-user', '增加组织用户页面', '/manager/addUserToOrganization.htm', '1111258', 'PAGE', 'zauth', '2013-05-21 23:26:03', '');
INSERT INTO `resource` VALUES ('1111277', 'zauth-organization-add-user-json', '增加组织用户事件', '/manager/addUserToOrganization.json', '1111276', 'BUTTON', 'zauth', '2013-05-21 23:26:42', '');
INSERT INTO `resource` VALUES ('1111278', 'zauth-organization-tree-page', '组织管理树界面', '/manager/organizationTree.htm', '1111258', 'PAGE', 'zauth', '2013-05-21 23:28:09', '');
INSERT INTO `resource` VALUES ('1111279', 'zauth-organization-tree-json', '组织管理树节点加载', '/manager/organizationTreeNodes.json', '1111278', 'BUTTON', 'zauth', '2013-05-21 23:29:21', '');
INSERT INTO `resource` VALUES ('1111280', 'zauth-resource-add', '新增资源', '/manager/addResource.htm', '1111260', 'PAGE', 'zauth', '2013-05-25 10:00:36', '');
INSERT INTO `resource` VALUES ('1111281', 'zauth-resource-tree', '资源树', '/manager/resourceTree.htm', '1111260', 'PAGE', 'zauth', '2013-05-25 10:04:53', '');
INSERT INTO `resource` VALUES ('1111282', 'zauth-resource-tree-load', '资源树加载事件', '/manager/resourceTreeNodes.json', '1111260', 'BUTTON', 'zauth', '2013-05-25 10:07:54', '');
INSERT INTO `resource` VALUES ('1111284', 'zauth-resource-addChild', '新建子资源', '/manager/addChildResource.htm', '1111260', 'PAGE', 'zauth', '2013-05-25 10:12:50', '');
INSERT INTO `resource` VALUES ('1111286', 'zauth-resource-edit', '修改资源', '/manager/editResource.htm', '1111260', 'PAGE', 'zauth', '2013-05-25 10:15:41', '');
INSERT INTO `resource` VALUES ('1111289', 'zauth-page-app', '应用管理界面', '/manager/app.htm', '1111255', 'PAGE', 'zauth', '2013-06-23 13:00:11', '');
INSERT INTO `resource` VALUES ('1111290', 'zauth-role-add', '新增角色', '/manager/addRole.htm', '1111259', 'PAGE', 'zauth', '2013-06-23 13:10:54', '');
INSERT INTO `resource` VALUES ('1111291', 'zauth-role-delete', '删除角色', '/manager/deleteRole.json', '1111259', 'BUTTON', 'zauth', '2013-06-23 13:11:50', '');
INSERT INTO `resource` VALUES ('1111292', 'zauth-role-addChild', '新增子角色', '/manager/addChildRole.htm', '1111259', 'PAGE', 'zauth', '2013-06-23 13:12:54', '');
INSERT INTO `resource` VALUES ('1111293', 'zauth-role-edit', '修改角色', '/manager/editRole.htm', '1111259', 'PAGE', 'zauth', '2013-06-23 13:14:07', '');
INSERT INTO `resource` VALUES ('1111294', 'zauth-role-resource-assign-page', '角色资源分配界面', '/manager/enterGrantResourceToRole.htm', '1111259', 'PAGE', 'zauth', '2013-06-23 13:17:00', '');
INSERT INTO `resource` VALUES ('1111295', 'zauth-role-resource-assign-event', '角色资源分配事件', '/manager/doGrantResourceToRole.json', '1111294', 'BUTTON', 'zauth', '2013-06-23 13:18:15', '');
INSERT INTO `resource` VALUES ('1111296', 'zauth-role-resource-node-load', '角色资源分配树节点加载', '/manager/resourceTreeNodesForGrant.json', '1111294', 'BUTTON', 'zauth', '2013-06-23 13:20:15', '');
INSERT INTO `resource` VALUES ('1111297', 'zauth-role-grantedUsers', '查询授权用户', '/manager/grantedUser.htm', '1111259', 'PAGE', 'zauth', '2013-06-23 13:23:27', '');
INSERT INTO `resource` VALUES ('1111298', 'zauth-resource-delete', '删除资源', '/manager/deleteResource.json', '1111260', 'BUTTON', 'zauth', '2013-06-23 13:25:50', '');
INSERT INTO `resource` VALUES ('1111299', 'zauth-resource-grantedRoles', '查看授权角色', '/manager/grantedRole.htm', '1111260', 'PAGE', 'zauth', '2013-06-23 13:27:56', '');
INSERT INTO `resource` VALUES ('1111300', 'zauth-app-add', '新建应用', '/manager/addApp.htm', '1111289', 'PAGE', 'zauth', '2013-06-23 13:28:41', '');
INSERT INTO `resource` VALUES ('1111301', 'zauth-app-delete', '删除应用', '/manager/deleteApp.htm', '1111289', 'PAGE', 'zauth', '2013-06-23 13:29:53', '');
INSERT INTO `resource` VALUES ('1111302', 'zauth-app-addAdmin', '授权管理员', '/manager/addUserToApp.htm', '1111289', 'PAGE', 'zauth', '2013-06-23 13:30:49', '');
INSERT INTO `resource` VALUES ('1111303', 'zauth-app-deleteAdmin', '删除应用管理员', '/manager/removeUserFromApp.json', '1111289', 'BUTTON', 'zauth', '2013-06-23 13:31:18', '');
INSERT INTO `resource` VALUES ('1111305', 'zauth-role-tree', '角色树节点界面', '/manager/roleTree.htm', '1111259', 'PAGE', 'zauth', '2013-06-23 14:32:19', '');
INSERT INTO `resource` VALUES ('1111306', 'zauth-role-tree-load', '角色树节点加载事件', '/manager/roleTreeNodes.json', '1111305', 'BUTTON', 'zauth', '2013-06-23 14:32:57', '');
INSERT INTO `resource` VALUES ('1111307', 'app-tccz', 'tccz', 'tccz', '0', 'APP', 'tccz', '2013-10-29 13:49:27', '');
INSERT INTO `resource` VALUES ('1111308', 'tccz-page-index', '首页', '/index.htm', '1111307', 'PAGE', 'tccz', '2013-10-29 13:50:59', '');
INSERT INTO `resource` VALUES ('1111309', 'tccz-page-fl', '流贷管理', '/query/floatingloan/index.htm', '1111307', 'PAGE', 'tccz', '2013-10-29 14:41:32', '');
INSERT INTO `resource` VALUES ('1111310', 'tccz-page-bn', '银票管理', '/query/bandarnote/index.htm', '1111307', 'PAGE', 'tccz', '2013-10-29 14:41:52', '');
INSERT INTO `resource` VALUES ('1111311', 'tccz-page-dc', '贴现管理', '/query/discount/index.htm', '1111307', 'PAGE', 'tccz', '2013-10-29 14:42:16', '');
INSERT INTO `resource` VALUES ('1111312', 'tccz-page-ei', '企业信息管理', '/query/enterprise/index.htm', '1111307', 'PAGE', 'tccz', '2013-10-29 14:43:00', '');
INSERT INTO `resource` VALUES ('1111313', 'tccz-page-pi', '个人信息管理', '/query/person/index.htm', '1111307', 'PAGE', 'tccz', '2013-10-29 14:43:12', '');
INSERT INTO `resource` VALUES ('1111315', 'tccz-fl-add', '增加流贷', '/update/floatingloan/add.htm', '1111309', 'PAGE', 'tccz', '2013-10-29 14:45:20', '');
INSERT INTO `resource` VALUES ('1111316', 'tccz-fl-delete', '删除流贷', '/update/floatingloan/delete.htm', '1111309', 'PAGE', 'tccz', '2013-10-29 14:45:29', '');
INSERT INTO `resource` VALUES ('1111317', 'tccz-fl-modify', '修改流贷', '/update/floatingloan/modify.htm', '1111309', 'PAGE', 'tccz', '2013-10-29 14:46:11', '');
INSERT INTO `resource` VALUES ('1111318', 'tccz-bn-add', '增加银票', '/update/bandarnote/add.htm', '1111310', 'PAGE', 'tccz', '2013-10-29 14:48:12', '');
INSERT INTO `resource` VALUES ('1111319', 'tccz-bn-delete', '删除银票', '/update/bandarnote/delete.htm', '1111310', 'PAGE', 'tccz', '2013-10-29 14:48:21', '');
INSERT INTO `resource` VALUES ('1111320', 'tccz-bn-modify', '修改银票', '/update/bandarnote/modify.htm', '1111310', 'PAGE', 'tccz', '2013-10-29 14:48:44', '');
INSERT INTO `resource` VALUES ('1111321', 'tccz-dc-add', '增加贴现', '/update/discount/add.htm', '1111311', 'PAGE', 'tccz', '2013-10-29 14:48:59', '');
INSERT INTO `resource` VALUES ('1111322', 'tccz-dc-delete', '删除贴现', '/update/discount/delete.htm', '1111311', 'PAGE', 'tccz', '2013-10-29 14:49:07', '');
INSERT INTO `resource` VALUES ('1111323', 'tccz-dc-modify', '修改贴现', '/update/discount/modify.htm', '1111311', 'PAGE', 'tccz', '2013-10-29 14:49:14', '');
INSERT INTO `resource` VALUES ('1111324', 'tccz-ei-add', '增加企业', '/update/enterprise/add.htm', '1111312', 'PAGE', 'tccz', '2013-10-29 14:49:37', '');
INSERT INTO `resource` VALUES ('1111325', 'tccz-ei-delete', '删除企业', '/update/enterprise/delete.htm', '1111312', 'PAGE', 'tccz', '2013-10-29 14:49:47', '');
INSERT INTO `resource` VALUES ('1111326', 'tccz-ei-modify', '修改企业', '/update/enterprise/modify.htm', '1111312', 'PAGE', 'tccz', '2013-10-29 14:49:56', '');
INSERT INTO `resource` VALUES ('1111327', 'tccz-ei-upload', '上传企业', '/update/enterprise/uploadEnterpriseInfo.htm', '1111312', 'PAGE', 'tccz', '2013-10-29 14:50:05', '');
INSERT INTO `resource` VALUES ('1111328', 'tccz-pi-add', '增加个人', '/update/person/add.htm', '1111313', 'PAGE', 'tccz', '2013-10-29 14:50:20', '');
INSERT INTO `resource` VALUES ('1111329', 'tccz-pi-delete', '删除个人', '/update/person/delete.htm', '1111313', 'PAGE', 'tccz', '2013-10-29 14:50:28', '');
INSERT INTO `resource` VALUES ('1111330', 'tccz-pi-modify', '修改个人', '/update/person/modify.htm', '1111313', 'PAGE', 'tccz', '2013-10-29 14:50:41', '');
INSERT INTO `resource` VALUES ('1111331', 'tccz-pi-upload', '上传个人', '/update/person/uploadPersonInfo.htm', '1111313', 'PAGE', 'tccz', '2013-10-29 14:50:48', '');
INSERT INTO `resource` VALUES ('1111334', 'tccz-account-uploadData', '上传台账', '/update/data/index.htm', '1111307', 'PAGE', 'tccz', '2013-10-29 16:08:24', '');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `Id` int(16) unsigned NOT NULL AUTO_INCREMENT,
  `rolename` varchar(50) NOT NULL DEFAULT '',
  `builder` varchar(255) NOT NULL DEFAULT '',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(255) DEFAULT NULL,
  `creator_org_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('109', '权限管理系统-超级管理员', 'dev', '2013-05-19 20:58:01', '', null);
INSERT INTO `role` VALUES ('110', '权限管理系统-只读', 'dev', '2013-05-19 20:59:36', '', null);
INSERT INTO `role` VALUES ('111', '查看用户', 'dev', '2013-05-19 21:01:33', '', null);
INSERT INTO `role` VALUES ('112', '查看组织', 'dev', '2013-05-19 21:01:42', '', null);
INSERT INTO `role` VALUES ('113', '查看角色', 'dev', '2013-05-19 21:01:49', '', null);
INSERT INTO `role` VALUES ('114', '查看资源', 'dev', '2013-05-19 21:01:53', '', null);
INSERT INTO `role` VALUES ('115', '查看应用', 'dev', '2013-05-19 21:02:00', '', null);
INSERT INTO `role` VALUES ('116', '权限管理系统-管理', 'admin', '2013-05-19 22:16:27', '', null);
INSERT INTO `role` VALUES ('117', '用户管理', 'admin', '2013-05-19 22:21:53', '', null);
INSERT INTO `role` VALUES ('118', '资源管理', 'admin', '2013-05-25 10:01:27', '', null);
INSERT INTO `role` VALUES ('119', '角色管理', 'admin', '2013-06-23 13:35:59', '', null);
INSERT INTO `role` VALUES ('120', '组织管理', 'admin', '2013-06-23 13:36:06', '', null);
INSERT INTO `role` VALUES ('121', '应用管理', 'admin', '2013-06-23 13:36:11', '', null);
INSERT INTO `role` VALUES ('149', '太仓民生村镇银行-管理员', 'admin', '2013-10-29 13:51:58', '', '0');
INSERT INTO `role` VALUES ('150', '普通用户', 'admin', '2013-10-29 13:52:42', '', '0');
INSERT INTO `role` VALUES ('151', '数据管理员', 'admin', '2013-10-29 13:52:56', '', '0');
INSERT INTO `role` VALUES ('152', '流贷管理员', 'admin', '2013-10-29 15:59:58', '', '0');
INSERT INTO `role` VALUES ('153', '银票管理员', 'admin', '2013-10-29 16:00:05', '', '0');
INSERT INTO `role` VALUES ('154', '贴现管理员', 'admin', '2013-10-29 16:00:11', '', '0');

-- ----------------------------
-- Table structure for `role_group`
-- ----------------------------
DROP TABLE IF EXISTS `role_group`;
CREATE TABLE `role_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_group
-- ----------------------------

-- ----------------------------
-- Table structure for `role_group_relation`
-- ----------------------------
DROP TABLE IF EXISTS `role_group_relation`;
CREATE TABLE `role_group_relation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_group_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_group_id` (`role_group_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_group_relation
-- ----------------------------

-- ----------------------------
-- Table structure for `role_relation`
-- ----------------------------
DROP TABLE IF EXISTS `role_relation`;
CREATE TABLE `role_relation` (
  `Id` int(16) NOT NULL AUTO_INCREMENT,
  `role_parent_id` int(16) DEFAULT NULL,
  `role_child_id` int(16) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of role_relation
-- ----------------------------
INSERT INTO `role_relation` VALUES ('115', '109', '110');
INSERT INTO `role_relation` VALUES ('116', '110', '111');
INSERT INTO `role_relation` VALUES ('117', '110', '112');
INSERT INTO `role_relation` VALUES ('118', '110', '113');
INSERT INTO `role_relation` VALUES ('119', '110', '114');
INSERT INTO `role_relation` VALUES ('120', '110', '115');
INSERT INTO `role_relation` VALUES ('121', '109', '116');
INSERT INTO `role_relation` VALUES ('122', '116', '117');
INSERT INTO `role_relation` VALUES ('123', '116', '118');
INSERT INTO `role_relation` VALUES ('124', '116', '119');
INSERT INTO `role_relation` VALUES ('125', '116', '120');
INSERT INTO `role_relation` VALUES ('126', '116', '121');
INSERT INTO `role_relation` VALUES ('127', '123', '128');
INSERT INTO `role_relation` VALUES ('128', '129', '130');
INSERT INTO `role_relation` VALUES ('129', '134', '135');
INSERT INTO `role_relation` VALUES ('130', '135', '136');
INSERT INTO `role_relation` VALUES ('131', '132', '138');
INSERT INTO `role_relation` VALUES ('132', '139', '140');
INSERT INTO `role_relation` VALUES ('133', '146', '147');
INSERT INTO `role_relation` VALUES ('134', '149', '150');
INSERT INTO `role_relation` VALUES ('135', '149', '151');
INSERT INTO `role_relation` VALUES ('136', '149', '152');
INSERT INTO `role_relation` VALUES ('137', '149', '153');
INSERT INTO `role_relation` VALUES ('138', '149', '154');

-- ----------------------------
-- Table structure for `role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `Id` int(16) NOT NULL AUTO_INCREMENT,
  `role_id` int(16) DEFAULT NULL,
  `resource_id` int(16) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=886 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('763', '111', '1111255');
INSERT INTO `role_resource` VALUES ('764', '111', '1111257');
INSERT INTO `role_resource` VALUES ('765', '111', '1111261');
INSERT INTO `role_resource` VALUES ('770', '117', '1111255');
INSERT INTO `role_resource` VALUES ('771', '117', '1111257');
INSERT INTO `role_resource` VALUES ('772', '117', '1111264');
INSERT INTO `role_resource` VALUES ('773', '117', '1111265');
INSERT INTO `role_resource` VALUES ('776', '117', '1111261');
INSERT INTO `role_resource` VALUES ('777', '117', '1111267');
INSERT INTO `role_resource` VALUES ('778', '117', '1111268');
INSERT INTO `role_resource` VALUES ('782', '117', '1111270');
INSERT INTO `role_resource` VALUES ('783', '117', '1111269');
INSERT INTO `role_resource` VALUES ('784', '111', '1111268');
INSERT INTO `role_resource` VALUES ('785', '111', '1111270');
INSERT INTO `role_resource` VALUES ('786', '112', '1111255');
INSERT INTO `role_resource` VALUES ('787', '112', '1111258');
INSERT INTO `role_resource` VALUES ('788', '112', '1111275');
INSERT INTO `role_resource` VALUES ('789', '112', '1111278');
INSERT INTO `role_resource` VALUES ('790', '112', '1111279');
INSERT INTO `role_resource` VALUES ('791', '118', '1111255');
INSERT INTO `role_resource` VALUES ('792', '118', '1111260');
INSERT INTO `role_resource` VALUES ('793', '118', '1111280');
INSERT INTO `role_resource` VALUES ('794', '114', '1111255');
INSERT INTO `role_resource` VALUES ('795', '114', '1111260');
INSERT INTO `role_resource` VALUES ('796', '114', '1111281');
INSERT INTO `role_resource` VALUES ('797', '114', '1111282');
INSERT INTO `role_resource` VALUES ('798', '118', '1111284');
INSERT INTO `role_resource` VALUES ('799', '118', '1111286');
INSERT INTO `role_resource` VALUES ('801', '113', '1111255');
INSERT INTO `role_resource` VALUES ('802', '113', '1111259');
INSERT INTO `role_resource` VALUES ('803', '113', '1111294');
INSERT INTO `role_resource` VALUES ('804', '113', '1111296');
INSERT INTO `role_resource` VALUES ('805', '113', '1111297');
INSERT INTO `role_resource` VALUES ('806', '114', '1111299');
INSERT INTO `role_resource` VALUES ('807', '115', '1111255');
INSERT INTO `role_resource` VALUES ('808', '115', '1111289');
INSERT INTO `role_resource` VALUES ('809', '118', '1111281');
INSERT INTO `role_resource` VALUES ('810', '118', '1111282');
INSERT INTO `role_resource` VALUES ('811', '118', '1111298');
INSERT INTO `role_resource` VALUES ('812', '118', '1111299');
INSERT INTO `role_resource` VALUES ('813', '119', '1111255');
INSERT INTO `role_resource` VALUES ('814', '119', '1111259');
INSERT INTO `role_resource` VALUES ('815', '119', '1111297');
INSERT INTO `role_resource` VALUES ('816', '119', '1111290');
INSERT INTO `role_resource` VALUES ('817', '119', '1111291');
INSERT INTO `role_resource` VALUES ('818', '119', '1111292');
INSERT INTO `role_resource` VALUES ('819', '119', '1111293');
INSERT INTO `role_resource` VALUES ('820', '119', '1111294');
INSERT INTO `role_resource` VALUES ('821', '119', '1111295');
INSERT INTO `role_resource` VALUES ('822', '119', '1111296');
INSERT INTO `role_resource` VALUES ('823', '120', '1111255');
INSERT INTO `role_resource` VALUES ('824', '120', '1111258');
INSERT INTO `role_resource` VALUES ('825', '120', '1111271');
INSERT INTO `role_resource` VALUES ('826', '120', '1111272');
INSERT INTO `role_resource` VALUES ('827', '120', '1111273');
INSERT INTO `role_resource` VALUES ('828', '120', '1111274');
INSERT INTO `role_resource` VALUES ('829', '120', '1111276');
INSERT INTO `role_resource` VALUES ('830', '120', '1111275');
INSERT INTO `role_resource` VALUES ('831', '120', '1111277');
INSERT INTO `role_resource` VALUES ('832', '120', '1111278');
INSERT INTO `role_resource` VALUES ('833', '120', '1111279');
INSERT INTO `role_resource` VALUES ('834', '121', '1111255');
INSERT INTO `role_resource` VALUES ('835', '121', '1111289');
INSERT INTO `role_resource` VALUES ('836', '121', '1111300');
INSERT INTO `role_resource` VALUES ('837', '121', '1111301');
INSERT INTO `role_resource` VALUES ('838', '121', '1111302');
INSERT INTO `role_resource` VALUES ('839', '121', '1111303');
INSERT INTO `role_resource` VALUES ('840', '117', '1111266');
INSERT INTO `role_resource` VALUES ('841', '113', '1111305');
INSERT INTO `role_resource` VALUES ('842', '113', '1111306');
INSERT INTO `role_resource` VALUES ('843', '119', '1111305');
INSERT INTO `role_resource` VALUES ('844', '119', '1111306');
INSERT INTO `role_resource` VALUES ('851', '150', '1111307');
INSERT INTO `role_resource` VALUES ('852', '150', '1111308');
INSERT INTO `role_resource` VALUES ('853', '151', '1111307');
INSERT INTO `role_resource` VALUES ('854', '151', '1111312');
INSERT INTO `role_resource` VALUES ('855', '151', '1111324');
INSERT INTO `role_resource` VALUES ('856', '151', '1111325');
INSERT INTO `role_resource` VALUES ('857', '151', '1111326');
INSERT INTO `role_resource` VALUES ('858', '151', '1111327');
INSERT INTO `role_resource` VALUES ('859', '151', '1111313');
INSERT INTO `role_resource` VALUES ('860', '151', '1111328');
INSERT INTO `role_resource` VALUES ('861', '151', '1111329');
INSERT INTO `role_resource` VALUES ('862', '151', '1111331');
INSERT INTO `role_resource` VALUES ('863', '151', '1111330');
INSERT INTO `role_resource` VALUES ('864', '151', '1111308');
INSERT INTO `role_resource` VALUES ('865', '152', '1111307');
INSERT INTO `role_resource` VALUES ('866', '152', '1111309');
INSERT INTO `role_resource` VALUES ('867', '152', '1111315');
INSERT INTO `role_resource` VALUES ('868', '152', '1111316');
INSERT INTO `role_resource` VALUES ('869', '152', '1111317');
INSERT INTO `role_resource` VALUES ('870', '152', '1111308');
INSERT INTO `role_resource` VALUES ('871', '153', '1111307');
INSERT INTO `role_resource` VALUES ('872', '153', '1111308');
INSERT INTO `role_resource` VALUES ('873', '153', '1111310');
INSERT INTO `role_resource` VALUES ('874', '153', '1111318');
INSERT INTO `role_resource` VALUES ('875', '153', '1111319');
INSERT INTO `role_resource` VALUES ('876', '153', '1111320');
INSERT INTO `role_resource` VALUES ('877', '154', '1111307');
INSERT INTO `role_resource` VALUES ('878', '154', '1111308');
INSERT INTO `role_resource` VALUES ('879', '154', '1111311');
INSERT INTO `role_resource` VALUES ('880', '154', '1111321');
INSERT INTO `role_resource` VALUES ('881', '154', '1111322');
INSERT INTO `role_resource` VALUES ('882', '154', '1111323');
INSERT INTO `role_resource` VALUES ('883', '152', '1111334');
INSERT INTO `role_resource` VALUES ('884', '153', '1111334');
INSERT INTO `role_resource` VALUES ('885', '154', '1111334');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Id` int(16) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `user_type` varchar(50) DEFAULT NULL,
  `user_group_id` int(16) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `organization_id` int(16) DEFAULT NULL,
  `staff_no` varchar(50) DEFAULT NULL,
  `true_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `job` varchar(50) DEFAULT NULL,
  `ext_number` varchar(50) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `creator_org_id` int(11) DEFAULT NULL,
  `create_person` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('49', 'dev', '1', 'ADMIN', null, '', '85', '', '', '', '', '', '', null, null, 'dev1');
INSERT INTO `user` VALUES ('50', 'admin', '1', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('86', '戴诚', '1', 'ADMIN', null, '', '88', '', '', '', '', '', '', '2013-10-29 16:12:00', '0', 'admin');
INSERT INTO `user` VALUES ('87', '龚春雅', '1', 'NORMAL', null, '', '86', '', '', '', '', '', '', '2013-10-29 16:20:14', '0', 'admin');
INSERT INTO `user` VALUES ('88', '陈正一', '1', 'NORMAL', null, '', '87', '', '', '', '', '', '', '2013-10-29 16:20:43', '0', 'admin');

-- ----------------------------
-- Table structure for `usergroup`
-- ----------------------------
DROP TABLE IF EXISTS `usergroup`;
CREATE TABLE `usergroup` (
  `Id` int(16) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(50) DEFAULT NULL,
  `parent_id` int(16) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of usergroup
-- ----------------------------

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `Id` int(16) NOT NULL AUTO_INCREMENT,
  `user_id` int(16) DEFAULT NULL,
  `role_id` int(16) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('178', '49', '110', '2013-05-19 21:26:38', null);
INSERT INTO `user_role` VALUES ('180', '49', '116', '2013-05-19 22:38:23', null);
INSERT INTO `user_role` VALUES ('188', '57', '110', '2013-06-23 13:40:53', null);
INSERT INTO `user_role` VALUES ('189', '58', '110', '2013-06-23 13:41:00', null);
INSERT INTO `user_role` VALUES ('190', '58', '117', '2013-06-23 13:41:08', null);
INSERT INTO `user_role` VALUES ('191', '58', '120', '2013-06-23 13:41:12', null);
INSERT INTO `user_role` VALUES ('192', '49', '122', '2013-06-23 15:36:52', null);
INSERT INTO `user_role` VALUES ('193', '61', '109', '2013-08-24 20:35:16', null);
INSERT INTO `user_role` VALUES ('194', '64', '109', '2013-08-24 23:04:58', null);
INSERT INTO `user_role` VALUES ('195', '59', '109', '2013-08-25 12:45:52', null);
INSERT INTO `user_role` VALUES ('196', '60', '109', '2013-08-25 12:51:17', null);
INSERT INTO `user_role` VALUES ('197', '59', '0', '2013-08-29 14:59:41', null);
INSERT INTO `user_role` VALUES ('198', '59', '138', '2013-08-29 14:59:53', null);
INSERT INTO `user_role` VALUES ('200', '59', '139', '2013-08-29 15:01:10', null);
INSERT INTO `user_role` VALUES ('201', '49', '137', '2013-09-01 19:52:58', null);
INSERT INTO `user_role` VALUES ('202', '65', '110', '2013-09-01 22:32:47', null);
INSERT INTO `user_role` VALUES ('203', '66', '110', '2013-09-01 22:33:02', null);
INSERT INTO `user_role` VALUES ('204', '67', '110', '2013-09-01 22:33:07', null);
INSERT INTO `user_role` VALUES ('206', '67', '137', '2013-09-01 23:26:33', null);
INSERT INTO `user_role` VALUES ('209', '67', '134', '2013-09-02 13:55:21', null);
INSERT INTO `user_role` VALUES ('210', '68', '134', '2013-09-02 16:32:45', null);
INSERT INTO `user_role` VALUES ('211', '68', '137', '2013-09-02 16:32:48', null);
INSERT INTO `user_role` VALUES ('212', '50', '138', '2013-09-02 16:41:07', null);
INSERT INTO `user_role` VALUES ('213', '49', '134', '2013-09-02 21:21:59', null);
INSERT INTO `user_role` VALUES ('214', '49', '133', '2013-09-02 21:29:06', null);
INSERT INTO `user_role` VALUES ('215', '50', '0', '2013-09-03 23:08:05', null);
INSERT INTO `user_role` VALUES ('216', '50', '140', '2013-09-03 23:08:13', null);
INSERT INTO `user_role` VALUES ('217', '50', '146', '2013-09-03 23:18:43', null);
INSERT INTO `user_role` VALUES ('218', '50', '147', '2013-09-03 23:19:05', null);
INSERT INTO `user_role` VALUES ('219', '50', '148', '2013-09-03 23:19:09', null);
INSERT INTO `user_role` VALUES ('220', '72', '134', '2013-09-05 10:42:51', null);
INSERT INTO `user_role` VALUES ('221', '72', '137', '2013-09-05 10:42:53', null);
INSERT INTO `user_role` VALUES ('223', '65', '134', '2013-10-08 15:03:40', null);
INSERT INTO `user_role` VALUES ('226', '65', '137', '2013-10-08 15:04:38', null);
INSERT INTO `user_role` VALUES ('238', '65', '131', '2013-10-08 15:35:50', null);
INSERT INTO `user_role` VALUES ('251', '49', '148', '2013-10-08 16:07:36', null);
INSERT INTO `user_role` VALUES ('252', '49', '146', '2013-10-08 16:07:48', null);
INSERT INTO `user_role` VALUES ('253', '49', '145', '2013-10-08 16:07:59', null);
INSERT INTO `user_role` VALUES ('254', '76', '148', '2013-10-08 16:25:44', null);
INSERT INTO `user_role` VALUES ('255', '76', '146', '2013-10-08 16:25:46', null);
INSERT INTO `user_role` VALUES ('256', '76', '145', '2013-10-08 16:25:47', null);
INSERT INTO `user_role` VALUES ('257', '50', '149', '2013-10-29 13:51:58', null);
INSERT INTO `user_role` VALUES ('258', '50', '150', '2013-10-29 13:52:42', null);
INSERT INTO `user_role` VALUES ('259', '50', '151', '2013-10-29 13:52:56', null);
INSERT INTO `user_role` VALUES ('261', '85', '150', '2013-10-29 13:57:55', null);
INSERT INTO `user_role` VALUES ('262', '50', '152', '2013-10-29 15:59:58', null);
INSERT INTO `user_role` VALUES ('263', '50', '153', '2013-10-29 16:00:05', null);
INSERT INTO `user_role` VALUES ('264', '50', '154', '2013-10-29 16:00:11', null);
INSERT INTO `user_role` VALUES ('265', '86', '151', '2013-10-29 16:13:00', null);
INSERT INTO `user_role` VALUES ('266', '87', '152', '2013-10-29 16:20:31', null);
INSERT INTO `user_role` VALUES ('267', '87', '153', '2013-10-29 16:20:32', null);
INSERT INTO `user_role` VALUES ('268', '88', '154', '2013-10-29 16:20:51', null);
INSERT INTO `user_role` VALUES ('269', '87', '151', '2013-10-29 16:34:38', null);
INSERT INTO `user_role` VALUES ('270', '88', '151', '2013-10-29 16:34:42', null);
