use grquickrescue;

drop table Contact;
drop table Contract;
drop table AlertProfile;
drop table Address;
drop table Account;
drop table QRLogin;


CREATE TABLE `QRLogin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE `Address` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `streetAddress` varchar(70) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `province` varchar(30) DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `Account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `timeZone` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `Contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountFK` int(10) unsigned DEFAULT NULL,
  `maxContacts` int(11) DEFAULT NULL,
  `maxLogins` int(11) DEFAULT NULL,
  `startDate` varchar(30) DEFAULT  NULL,
  `endDate` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `accountFK` (`accountFK`),
  CONSTRAINT `accountFK` FOREIGN KEY (`accountFK`) REFERENCES `account` (`id`) on delete cascade
);



CREATE TABLE `Contact` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `accountID` int(10) unsigned DEFAULT NULL,
  `addressID` int(10) unsigned DEFAULT NULL,
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `emailAddress` varchar(50) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `hasLogin` tinyint(1) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `accountID` (`accountID`),
  KEY `addressID` (`addressID`),
  CONSTRAINT `accountID` FOREIGN KEY (`accountID`) REFERENCES `account` (`id`) on delete cascade,
  CONSTRAINT `addressID` FOREIGN KEY (`addressID`) REFERENCES `address` (`id`)
);

CREATE TABLE `AlertProfile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountIDFK` int(10) unsigned DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `accountIDFK` (`accountIDFK`),
  CONSTRAINT `accountIDFK` FOREIGN KEY (`accountIDFK`) REFERENCES `account` (`id`) on delete cascade
);

