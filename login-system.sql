CREATE DATABASE loginSystem;
USE loginSystem;
CREATE TABLE roles (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE users (
    username VARCHAR(255) PRIMARY KEY,
    hashedPassword VARCHAR(255) NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);
INSERT into loginSystem.roles
(id,name)
VALUES(
	0,'admin'
);
INSERT into loginSystem.roles
(id,name)
VALUES(
	1,'user'
);