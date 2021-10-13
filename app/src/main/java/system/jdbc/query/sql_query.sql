CREATE DATABASE Pharmacy_Management_System;
USE Pharmacy_Management_System;



CREATE TABLE Chemist(
	id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(32) UNIQUE,
    password VARCHAR(32)	
);

INSERT INTO Chemist (username, password) VALUES ('admin@chemist', 'chemist@1234');
SELECT * FROM Chemist;

DESC Chemist;


CREATE TABLE Customer(
	cust_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(32),
    last_name VARCHAR(32),
    contact_no VARCHAR(12),
    email_id VARCHAR(32) UNIQUE,
    password VARCHAR(32)	
);

DESC Customer;

CREATE TABLE Medicine(
	medicine_id INT PRIMARY KEY AUTO_INCREMENT,
    medicine_name VARCHAR(32) UNIQUE,
    quantity INT,
    price INT
);

DESC Medicine;

SELECT * FROM Medicine;

TRUNCATE TABLE MEDICINE;


DROP DATABASE Pharmacy_Management_System;


