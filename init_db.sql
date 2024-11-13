CREATE USER IF NOT EXISTS 'myUser'@'%' IDENTIFIED BY 'p';

DROP DATABASE IF EXISTS `demo_ds0`;
DROP DATABASE IF EXISTS `demo_ds1`;
DROP DATABASE IF EXISTS `demo_ds2`;

CREATE DATABASE `demo_ds0`;
CREATE DATABASE `demo_ds1`;
CREATE DATABASE `demo_ds2`;

GRANT ALL PRIVILEGES ON demo_ds0.* TO 'myUser'@'%';
GRANT ALL PRIVILEGES ON demo_ds1.* TO 'myUser'@'%';
GRANT ALL PRIVILEGES ON demo_ds2.* TO 'myUser'@'%';
flush privileges;


-- MasterSlave start --

CREATE TABLE demo_ds0.`t_master_slave` (
    `id` bigint NOT NULL,
    `code` varchar(10),
    `name` varchar(20),
    PRIMARY KEY (`id`)
);
INSERT INTO demo_ds0.t_master_slave (id,code,name) VALUES
(1,'mc','mn');

CREATE TABLE demo_ds1.`t_master_slave` (
    `id` bigint NOT NULL,
    `code` varchar(10),
    `name` varchar(20),
    PRIMARY KEY (`id`)
);
INSERT INTO demo_ds1.t_master_slave (id,code,name)
VALUES (1,'sc','sn');

-- MasterSlave end --


-- Sharding start --

CREATE TABLE demo_ds0.t_sharding (
     `id` bigint NOT NULL,
     `code` varchar(10),
     `name` varchar(20),
     PRIMARY KEY (`id`)
);
INSERT INTO demo_ds0.t_sharding (id,code,name) VALUES
(1,'s0_code','s0_name');

CREATE TABLE demo_ds1.t_sharding (
     `id` bigint NOT NULL,
     `code` varchar(10),
     `name` varchar(20),
     PRIMARY KEY (`id`)
);
INSERT INTO demo_ds1.t_sharding (id,code,name) VALUES
(1,'s1_code','s1_name');

-- Sharding end --


-- dynamic datasource start --

CREATE TABLE demo_ds2.t_sharding (
     `id` bigint NOT NULL,
     `code` varchar(10),
     `name` varchar(20),
     PRIMARY KEY (`id`)
);
INSERT INTO demo_ds2.t_sharding (id,code,name) VALUES
(1,'s2_code','s2_name');

-- dynamic datasource end --
