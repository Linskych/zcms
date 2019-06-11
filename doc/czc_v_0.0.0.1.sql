CREATE TABLE `tbl_user`  (
  `id` int(10) NOT NULL COMMENT '主键id',
  `nick_name` varchar(32) NOT NULL COMMENT '昵称',
  `real_name` varchar(32) NOT NULL COMMENT '姓名',
  `phone` varchar(11) NOT NULL COMMENT '电话号码',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uni_phone`(`phone`)
) COMMENT = '用户信息表';

ALTER TABLE `tbl_user` 
MODIFY COLUMN `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id' FIRST,
MODIFY COLUMN `nick_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '昵称' AFTER `id`,
MODIFY COLUMN `real_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '姓名' AFTER `nick_name`,
MODIFY COLUMN `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '电话号码' AFTER `real_name`,
ADD COLUMN `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码' AFTER `phone`,
ADD COLUMN `salt` varchar(32) NOT NULL DEFAULT '' COMMENT '盐值' AFTER `password`,
ADD COLUMN `open_id` varchar(40) NOT NULL DEFAULT '' COMMENT '微信openid' AFTER `salt`,
ADD COLUMN `status` tinyint(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户状态，0正常，1失效' AFTER `open_id`;

ALTER TABLE `tbl_user` 
CHANGE COLUMN `phone` `user_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '账号(电话号码)' AFTER `real_name`;