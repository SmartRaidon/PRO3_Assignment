CREATE SCHEMA IF NOT EXISTS pro3;
SET SCHEMA 'pro3';

DROP SCHEMA pro3 CASCADE;

DROP TABLE IF EXISTS animal CASCADE;
DROP TABLE IF EXISTS part CASCADE;
DROP TABLE IF EXISTS tray CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS trayProduct CASCADE;

CREATE TABLE IF NOT EXISTS animal (
    regNum SERIAL,
    weight INT,
    PRIMARY KEY (regNum)
);

CREATE TABLE IF NOT EXISTS part (
    regNum SERIAL,
    trayNum INT,
    originNum INT,
    productNum INT,
    type VARCHAR(50),
    weight INT,
    PRIMARY KEY (regNum),
    FOREIGN KEY (originNum) REFERENCES animal,
    FOREIGN KEY (trayNum) REFERENCES tray,
    FOREIGN KEY (productNum) REFERENCES product
);

CREATE TABLE IF NOT EXISTS tray (
    trayNum SERIAL,
    partType VARCHAR(50),
    capacity INT,
    currentWeight INT,
    PRIMARY KEY (trayNum)
);

CREATE TABLE IF NOT EXISTS product (
    productNum SERIAL,
    type VARCHAR(50),
    PRIMARY KEY (productNum)
);

CREATE TABLE IF NOT EXISTS trayProduct (
    trayNum INT,
    productNum INT,
    PRIMARY KEY (trayNum, productNum),
    FOREIGN KEY (trayNum) REFERENCES tray,
    FOREIGN KEY (productNum) REFERENCES product
)