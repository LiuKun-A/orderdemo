DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_sn` varchar(20) NOT NULL COMMENT '订单编号',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `produce_amount` bigint(20) NOT NULL COMMENT '商品总额',
  `payment_amount` bigint(20) NOT NULL COMMENT '支付总额',
  `user_info` varchar(1000) DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `comment` varchar(1000) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_sn` (`order_sn`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `order_sn` varchar(20) NOT NULL COMMENT '订单编号',
  `produce_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `price` bigint(20) DEFAULT NULL COMMENT '价格',
  `item_number` int(11) DEFAULT NULL COMMENT '数量',
  `comment` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_sn` varchar(20) NOT NULL COMMENT '订单编号',
  `status` int(11) NOT NULL COMMENT '状态，1：待支付，2：待确认，3：成功，4：过期',
  `pay_channel` tinyint(4) NOT NULL COMMENT '支付渠道',
  `source` tinyint(4) DEFAULT NULL COMMENT '支付来源',
  `pay_sum` bigint(20) NOT NULL COMMENT '金额',
  `pay_time` datetime DEFAULT NULL,
  `comment` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;