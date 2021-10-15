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

SELECT * FROM Customer;

DESC Customer;

CREATE TABLE Medicine(
	medicine_id INT PRIMARY KEY AUTO_INCREMENT,
    medicine_name VARCHAR(32) UNIQUE,
    quantity INT,
    price INT
);

-- UPDATE Medicine SET quantity = 40, price = 80 WHERE medicine_name = 'Paracetemol';

DESC Medicine;

SELECT * FROM Medicine;

TRUNCATE TABLE MEDICINE;

CREATE TABLE Bills(
	id INT PRIMARY KEY AUTO_INCREMENT,
    cust_id INT NOT NULL,
    FOREIGN KEY (cust_id) REFERENCES Customer(cust_id),
	amount INT NOT NULL
);

DESC Bills;
INSERT INTO Bills (cust_id, amount) VALUES (1,1000);


DROP DATABASE Pharmacy_Management_System;


