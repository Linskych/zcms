CREATE TABLE `czc`.`tbl_user`  (
  `id` int(10) NOT NULL COMMENT '主键id',
  `nick_name` varchar(32) NOT NULL COMMENT '昵称',
  `real_name` varchar(32) NOT NULL COMMENT '姓名',
  `phone` varchar(11) NOT NULL COMMENT '电话号码',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uni_phone`(`phone`)
) COMMENT = '用户信息表';