CREATE TABLE Studenttable (
  id varchar(20) ,
  studentname varchar(100) ,
  paymentstatus varchar(50) ,
  address varchar(50) ,
  department varchar(50),
  PRIMARY KEY (id),
  UNIQUE KEY UK_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table Studenttable add age int(20);
