CREATE USER IF NOT EXISTS 'myUser'@'%' IDENTIFIED BY 'p';

DROP DATABASE IF EXISTS `demo_ds_m`;
DROP DATABASE IF EXISTS `demo_ds_s`;
DROP DATABASE IF EXISTS `demo_ds_1`;

CREATE DATABASE `demo_ds_m`;
CREATE DATABASE `demo_ds_s`;
CREATE DATABASE `demo_ds_1`;

GRANT ALL PRIVILEGES ON demo_ds_m.* TO 'myUser'@'%';
GRANT ALL PRIVILEGES ON demo_ds_s.* TO 'myUser'@'%';
GRANT ALL PRIVILEGES ON demo_ds_1.* TO 'myUser'@'%';
flush privileges;


-- MasterSlave start --

CREATE TABLE demo_ds_m.`t_master_slave` (
    `id` bigint NOT NULL,
    `code` varchar(10),
    `name` varchar(20),
    PRIMARY KEY (`id`)
);
INSERT INTO demo_ds_m.t_master_slave (id,code,name) VALUES
(1,'mc','mn');

CREATE TABLE demo_ds_s.`t_master_slave` (
    `id` bigint NOT NULL,
    `code` varchar(10),
    `name` varchar(20),
    PRIMARY KEY (`id`)
);
INSERT INTO demo_ds_s.t_master_slave (id,code,name)
VALUES (1,'sc','sn');

-- MasterSlave end --


-- Sharding start --

CREATE TABLE demo_ds_m.t_sharding_0 (
     `id` bigint NOT NULL,
     `code` varchar(10),
     `name` varchar(20),
     PRIMARY KEY (`id`)
);
INSERT INTO demo_ds_m.t_sharding_0 (id,code,name) VALUES
(1,'code_m','name_m');

CREATE TABLE demo_ds_s.t_sharding_0 (
     `id` bigint NOT NULL,
     `code` varchar(10),
     `name` varchar(20),
     PRIMARY KEY (`id`)
);
INSERT INTO demo_ds_s.t_sharding_0 (id,code,name) VALUES
(1,'code_s','name_s');

-- Sharding end --


-- dynamic datasource start --

CREATE TABLE demo_ds_1.t_sharding_0 (
     `id` bigint NOT NULL,
     `code` varchar(10),
     `name` varchar(20),
     PRIMARY KEY (`id`)
);
INSERT INTO demo_ds_1.t_sharding_0 (id,code,name) VALUES
(1,'code_ds1','name_ds1');

-- dynamic datasource end --
