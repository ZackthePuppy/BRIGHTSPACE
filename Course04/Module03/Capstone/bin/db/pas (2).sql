SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS `pas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `pas`;

CREATE TABLE IF NOT EXISTS `accident` (
  `claimnumber` int(5) NOT NULL AUTO_INCREMENT,
  `date` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  `accidentdescription` longtext NOT NULL,
  `damagedescription` longtext NOT NULL,
  `repaircost` double NOT NULL,
  `policy` int(50) NOT NULL,
  PRIMARY KEY (`claimnumber`),
  UNIQUE KEY `policy` (`policy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `address` longtext NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `firstname` (`firstname`),
  UNIQUE KEY `lastname` (`lastname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `policy` (
  `policynumber` int(6) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `effectdate` date NOT NULL,
  `expiredate` date NOT NULL,
  `policyowner` int(4) DEFAULT NULL,
  `policypremium` double DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  UNIQUE KEY `policynumber` (`policynumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `policyholder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `birthdate` varchar(50) NOT NULL,
  `address` longtext NOT NULL,
  `licensenumber` varchar(50) NOT NULL,
  `licensedate` date NOT NULL,
  `policy` int(255) DEFAULT NULL,
  `policyowner` int(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `licensenumber` (`licensenumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `vehicle` (
  `id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `brand` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `year` year(4) NOT NULL,
  `type` varchar(50) NOT NULL,
  `fuel` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `color` varchar(50) NOT NULL,
  `premiumcharge` double NOT NULL,
  `policyholder` int(255) NOT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
