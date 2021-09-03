CREATE USER IF NOT EXISTS 'myUser'@'%' IDENTIFIED BY 'p';

DROP DATABASE IF EXISTS `demo_ds0`;
DROP DATABASE IF EXISTS `demo_ds1`;
DROP DATABASE IF EXISTS `demo_dsp`;

CREATE DATABASE `demo_ds0`;
CREATE DATABASE `demo_ds1`;
CREATE DATABASE `demo_dsp`;

GRANT ALL PRIVILEGES ON demo_ds0.* TO 'myUser'@'%';
GRANT ALL PRIVILEGES ON demo_ds1.* TO 'myUser'@'%';
GRANT ALL PRIVILEGES ON demo_dsp.* TO 'myUser'@'%';
flush privileges;


-- MasterSlave start --

use demo_ds0;

CREATE TABLE `t_master_slave` (
    `id` bigint NOT NULL,
    `code` varchar(10),
    `name` varchar(20),
    PRIMARY KEY (`id`)
);
INSERT INTO demo_ds0.t_master_slave (id,code,name) VALUES
(1,'mc','mn');

use demo_ds1;

CREATE TABLE `t_master_slave` (
    `id` bigint NOT NULL,
    `code` varchar(10),
    `name` varchar(20),
    PRIMARY KEY (`id`)
);
INSERT INTO demo_ds1.t_master_slave (id,code,name)
VALUES (1,'sc','sn');

-- MasterSlave end --


-- Sharding start --

use demo_ds0;

CREATE TABLE t_order_0 (
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT t_order_0_pk PRIMARY KEY (order_id)
);
CREATE TABLE t_order_1 (
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT t_order_1_pk PRIMARY KEY (order_id)
);
CREATE TABLE t_order_item_0 (
    order_item_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT t_order_item_0_pk PRIMARY KEY (order_item_id)
);
CREATE TABLE t_order_item_1 (
    order_item_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT t_order_item_1_pk PRIMARY KEY (order_item_id)
);
CREATE TABLE `t_dict` (
    `id` bigint NOT NULL,
    `name` varchar(10) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE t_transaction (
    id BIGINT NOT NULL,
    name varchar(10) NULL,
    PRIMARY KEY (id)
);

use demo_ds1;

CREATE TABLE t_order_0 (
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT t_order_0_pk PRIMARY KEY (order_id)
);
CREATE TABLE t_order_1 (
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT t_order_1_pk PRIMARY KEY (order_id)
);
CREATE TABLE t_order_item_0 (
    order_item_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT t_order_item_0_pk PRIMARY KEY (order_item_id)
);
CREATE TABLE t_order_item_1 (
    order_item_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT t_order_item_1_pk PRIMARY KEY (order_item_id)
);
CREATE TABLE `t_dict` (
    `id` bigint NOT NULL,
    `name` varchar(10) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO demo_ds0.t_dict (id,name) VALUES
(1,'n0');
INSERT INTO demo_ds1.t_dict (id,name) VALUES
(1,'n1');

INSERT INTO demo_ds0.t_order_0 (order_id,user_id)
VALUES (0,0);
INSERT INTO demo_ds0.t_order_1 (order_id,user_id)
VALUES (1,0);
INSERT INTO demo_ds1.t_order_0 (order_id,user_id)
VALUES (2,1);
INSERT INTO demo_ds1.t_order_1 (order_id,user_id)
VALUES (3,1);

INSERT INTO demo_ds0.t_order_item_0 (order_item_id,order_id,user_id)
VALUES (0,0,0);
INSERT INTO demo_ds0.t_order_item_1 (order_item_id,order_id,user_id)
VALUES (1,1,0);
INSERT INTO demo_ds1.t_order_item_0 (order_item_id,order_id,user_id)
VALUES (2,2,1);
INSERT INTO demo_ds1.t_order_item_1 (order_item_id,order_id,user_id)
VALUES (3,3,1);

INSERT INTO demo_ds0.t_order_item_0 (order_item_id,order_id,user_id)
VALUES (4,1,0);

INSERT INTO demo_ds0.t_transaction (id,name) VALUES
(1,'n0');

-- Sharding end --


-- dynamic datasource start --

use demo_dsp;

CREATE TABLE `t_dynamic` (
`id` bigint NOT NULL,
`code` varchar(10),
`name` varchar(20),
PRIMARY KEY (`id`)
);
INSERT INTO t_dynamic (id,code,name) VALUES
(1,'dc','dn');

-- dynamic datasource start --