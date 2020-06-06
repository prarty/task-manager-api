CREATE TABLE tasks (id INTEGER NOT NULL PRIMARY KEY auto_increment,
user_id INTEGER ,
description VARCHAR(255),
due_date DATE,
label VARCHAR(255),
status ENUM('PENDING', 'DONE'),
FOREIGN KEY (user_id) REFERENCES users(id)
);