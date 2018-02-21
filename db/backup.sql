CREATE DATABASE `developer` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(85) NOT NULL,
  `lastName` varchar(85) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

insert into users (firstName, lastName, salary, dateOfBirth) values('Pavel', 'Gor',2000, '2011-12-28');
insert into users (firstName, lastName, salary, dateOfBirth) values('Mariya', 'Miller',1500, '2000-02-01');