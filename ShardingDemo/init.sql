CREATE DATABASE `demo_ds0`;
CREATE DATABASE `demo_ds1`;

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
