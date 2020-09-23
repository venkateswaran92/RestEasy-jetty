create table Employee(
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(100) NOT NULL,
   address VARCHAR(40) NOT NULL,
   PRIMARY KEY (id)
);

INSERT INTO Employee (id,name, address)
VALUES (1,'venkat', 'cuddalore');


INSERT INTO Employee (id,name, address)
VALUES (2,'venkat1', 'cuddalore1');


INSERT INTO Employee (id,name, address)
VALUES (3,'venkat2', 'cuddalore2');
