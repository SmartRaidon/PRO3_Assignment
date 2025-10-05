CREATE SCHEMA IF NOT EXISTS pro3;
SET SCHEMA 'pro3';

DROP SCHEMA pro3 CASCADE;

DROP TABLE IF EXISTS animal CASCADE;
DROP TABLE IF EXISTS part CASCADE;
DROP TABLE IF EXISTS tray CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS trayProduct CASCADE;

CREATE TABLE IF NOT EXISTS animal (
    type varchar(30),
    reg_num SERIAL,
    weight double precision,
    PRIMARY KEY (reg_num)
);

CREATE TABLE IF NOT EXISTS part (
    reg_num SERIAL,
    tray_num INT,
    origin_num INT,
    product_num INT,
    type VARCHAR(50),
    weight double precision,
    PRIMARY KEY (reg_num),
    FOREIGN KEY (origin_num) REFERENCES animal(reg_num),
    FOREIGN KEY (tray_num) REFERENCES tray(tray_num),
    FOREIGN KEY (product_num) REFERENCES product(product_num)
);

CREATE TABLE IF NOT EXISTS tray (
    tray_num SERIAL,
    part_type VARCHAR(50),
    capacity INT,
    current_weight INT,
    PRIMARY KEY (tray_num)
);

CREATE TABLE IF NOT EXISTS product (
    product_num SERIAL,
    type VARCHAR(50),
    PRIMARY KEY (product_num)
);

CREATE TABLE IF NOT EXISTS trayProduct (
    tray_num INT,
    product_num INT,
    PRIMARY KEY (tray_num, product_num),
    FOREIGN KEY (tray_num) REFERENCES tray,
    FOREIGN KEY (product_num) REFERENCES product
);

INSERT INTO pro3.animal (type, reg_num, weight) VALUES ('cow', 2, 100.00);