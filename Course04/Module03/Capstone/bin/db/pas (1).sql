-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 22, 2022 at 08:49 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pas`
--

-- --------------------------------------------------------

--
-- Table structure for table `accident`
--

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

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `address` longtext NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `firstname` (`firstname`),
  UNIQUE KEY `lastname` (`lastname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `policy`
--

CREATE TABLE IF NOT EXISTS `policy` (
  `policynumber` int(6) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `effectdate` date NOT NULL,
  `expiredate` date NOT NULL,
  `policyowner` int(4) DEFAULT NULL,
  `policypremium` double DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  UNIQUE KEY `policynumber` (`policynumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `policyholder`
--

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

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
