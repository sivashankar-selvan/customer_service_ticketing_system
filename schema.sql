CREATE DATABASE customer_service;

USE customer_service;

CREATE TABLE Customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100),
    customer_email VARCHAR(100)
);

INSERT INTO Customer (customer_name, customer_email) VALUES ('John Doe', 'john.doe@example.com');

CREATE TABLE Representative (
    representative_id INT AUTO_INCREMENT PRIMARY KEY,
    representative_name VARCHAR(100),
    representative_email VARCHAR(100)
);

CREATE TABLE Ticket (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    creation_date DATETIME,
    issue_description TEXT,
    status VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Assignment (
    assignment_id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_id INT,
    representative_id INT,
    assignment_date DATETIME,
    status VARCHAR(50),
    FOREIGN KEY (ticket_id) REFERENCES Ticket(ticket_id),
    FOREIGN KEY (representative_id) REFERENCES Representative(representative_id)
);

CREATE TABLE Resolution (
    resolution_id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_id INT,
    resolution_date DATETIME,
    resolution_details TEXT,
    status VARCHAR(50),
    FOREIGN KEY (ticket_id) REFERENCES Ticket(ticket_id)
);
