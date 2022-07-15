-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 22, 2022 at 08:25 AM
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

CREATE TABLE `accident` (
  `claimnumber` int(5) NOT NULL,
  `date` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  `accidentdescription` longtext NOT NULL,
  `damagedescription` longtext NOT NULL,
  `repaircost` double NOT NULL,
  `policy` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `accident`
--

INSERT INTO `accident` (`claimnumber`, `date`, `address`, `accidentdescription`, `damagedescription`, `repaircost`, `policy`) VALUES
(10000, '1999/01/01', 'imus', 'sabog ulo', 'wasak bumper', 100.9, 100025),
(10001, '1999/01/01', 'Secret', 'Wasak ulo driver', 'Wasak engine', 900.6969, 100026),
(10002, '2020/01/01', 'Hatdog', 'asdg', 'asdg', 123123, 69);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(4) UNSIGNED ZEROFILL NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `address` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `firstname`, `lastname`, `address`) VALUES
(0001, 'John', 'Doe', 'Dasma'),
(0012, 'Juan', 'Dela Cruz', 'Imus'),
(0013, 'Jayp', 'Babaran', 'Magallanes'),
(0014, 'Russel', 'Sauli', 'Gentri'),
(0015, 'Test', 'Name', 'Silang'),
(0016, 'Test1', 'Name1', 'Canada'),
(0017, 'Jesus', 'Christ', 'Heaven'),
(1000, 'Lucifer', 'Morningstar', 'Hell'),
(1001, 'Hat', 'Dog', 'Footlong'),
(1002, 'Mecha', 'Hesukristo', 'Heaven'),
(1003, 'Mark', 'Joseph', 'IMus'),
(1005, '\\', '\\', '\\');

-- --------------------------------------------------------

--
-- Table structure for table `policy`
--

CREATE TABLE `policy` (
  `policynumber` int(6) UNSIGNED ZEROFILL NOT NULL,
  `effectdate` date NOT NULL,
  `expiredate` date NOT NULL,
  `policyowner` int(4) DEFAULT NULL,
  `policypremium` double DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `policy`
--

INSERT INTO `policy` (`policynumber`, `effectdate`, `expiredate`, `policyowner`, `policypremium`, `status`) VALUES
(100028, '2022-06-06', '1998-02-02', 1002, 0, 'Cancelled'),
(100029, '2022-06-06', '2022-06-05', 1002, 0, 'Cancelled'),
(100030, '2022-06-06', '2022-12-06', 1002, 0, 'Active'),
(100031, '2022-06-06', '2022-12-06', 1002, 0, 'Active'),
(100032, '2023-05-01', '2023-11-01', 1, NULL, 'Inactive'),
(100033, '2021-06-06', '2021-12-06', 1, 2010, 'Expired'),
(100034, '2022-06-06', '2022-12-06', 1002, 8205.882352941177, 'Active'),
(100035, '2022-06-06', '2022-12-06', 1002, NULL, 'Active'),
(100036, '2021-06-08', '2021-12-08', 1002, NULL, 'Expired'),
(100037, '2023-01-01', '2023-07-01', 1001, 1.0004761904761905, 'Inactive'),
(100038, '0000-00-00', '0000-00-00', 696969, NULL, 'Expired'),
(100039, '2023-01-01', '2023-07-01', 1002, NULL, 'Inactive'),
(100040, '2020-02-29', '2020-08-29', 1001, NULL, 'Expired'),
(100041, '2022-03-02', '2022-08-30', 1001, NULL, 'Active'),
(100042, '2022-03-03', '2022-08-31', 1001, NULL, 'Active'),
(100043, '2022-03-03', '2022-08-31', 1001, NULL, 'Active'),
(100044, '2022-02-28', '2022-08-28', 1001, NULL, 'Active'),
(100045, '2022-02-28', '2022-08-28', 1001, NULL, 'Active'),
(100046, '2023-07-21', '2024-01-21', 1001, NULL, 'Inactive'),
(100047, '2024-08-22', '2025-02-22', 1001, NULL, 'Inactive'),
(100048, '2022-07-21', '2023-01-21', 1001, NULL, 'Inactive'),
(100049, '2022-06-21', '2022-12-21', 1001, NULL, 'Active'),
(100050, '2022-06-02', '2022-12-02', 1001, NULL, 'Active'),
(100051, '2022-02-02', '2022-07-02', 1001, NULL, 'Active'),
(100052, '2022-07-20', '2023-01-20', 1001, NULL, 'Inactive'),
(100053, '2023-06-21', '2023-12-21', 1001, NULL, 'Inactive'),
(100054, '2022-02-02', '2022-08-02', 1001, NULL, 'Active'),
(100055, '2023-02-02', '2023-08-02', 1001, NULL, 'Inactive');

-- --------------------------------------------------------

--
-- Table structure for table `policyholder`
--

CREATE TABLE `policyholder` (
  `id` int(11) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `birthdate` varchar(50) NOT NULL,
  `address` longtext NOT NULL,
  `licensenumber` varchar(50) NOT NULL,
  `licensedate` date NOT NULL,
  `policy` int(255) DEFAULT NULL,
  `policyowner` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `policyholder`
--

INSERT INTO `policyholder` (`id`, `firstname`, `lastname`, `birthdate`, `address`, `licensenumber`, `licensedate`, `policy`, `policyowner`) VALUES
(1, 'Hat', 'Dog', 'Jan 1 2022', '', '123asdf', '2012-01-01', NULL, 1001),
(2, 'Hatdog', 'Cheese', 'secret', '', '123assdf', '2002-01-01', 69, 1001),
(3, 'Jesus', 'Chris', 'Heaven', '', 'asdf123', '2001-12-25', 100037, 1001),
(4, 'Mecha', 'Hesukristo', 'Dec 25', '', '123adsf2', '2005-12-25', 100034, 1002),
(5, 'Test', 'Lang', 'Secret', '', '123adsfSS', '2020-01-01', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

CREATE TABLE `vehicle` (
  `id` int(10) UNSIGNED ZEROFILL NOT NULL,
  `brand` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `year` year(4) NOT NULL,
  `type` varchar(50) NOT NULL,
  `fuel` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `color` varchar(50) NOT NULL,
  `premiumcharge` double NOT NULL,
  `policyholder` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vehicle`
--

INSERT INTO `vehicle` (`id`, `brand`, `model`, `year`, `type`, `fuel`, `price`, `color`, `premiumcharge`, `policyholder`) VALUES
(0000000017, 'sadf', 'sad', 2001, '4-Door Sedan', 'Diesel', 200, 'sadf', 200.11764705882354, 4),
(0000000018, 'sdf', 'sadf', 2001, '4-Door Sedan', 'Diesel', 12313, 'sdf', 12320.24294117647, 4),
(0000000019, 'adsf', 'sadf', 2002, '4-Door Sedan', 'Diesel', 10000, 'dsfg', 8005.882352941177, 4),
(0000000020, 'asdf', 'sdaf', 2001, 'Truck', 'Petrol', 1000, 'dsf', 1000.5882352941177, 4),
(0000000021, 'asdf', 'sdaf', 2001, '2-Door Sports Car', 'Electric', 1000, 'red', 1000.4761904761905, 3),
(0000000022, 'asdf', 'sdaf', 2001, '4-Door Sedan', 'Diesel', 100, 'dsf', 100.05882352941177, 4),
(0000000023, 'sadf', 'sdaf', 2001, '4-Door Sedan', 'Diesel', 100, 'sdf', 100.05882352941177, 4),
(0000000024, 'sadf', 'sadg', 2001, '4-Door Sedan', 'Diesel', 122, 'dsaf', 122.07176470588236, 4),
(0000000025, 'adsf', 'adsf', 2001, '4-Door Sedan', 'Diesel', 1000, 'sadf', 1000.5882352941177, 4),
(0000000026, 'sadf', 'dsaf', 2001, '4-Door Sedan', 'Diesel', 1123123, 'dsf', 1123783.6605882354, 4),
(0000000027, 'dsaf', 'dsf', 2001, '4-Door Sedan', 'Diesel', 1000, 'sdf', 1000.5882352941177, 4),
(0000000028, 'sadf', 'dsaf', 2001, '4-Door Sedan', 'Diesel', 123123, 'saef', 123195.42529411765, 4),
(0000000029, 'dsgf', 'sdf', 2001, '4-Door Sedan', 'Diesel', 123124, 'sadf', 123196.42588235294, 4),
(0000000030, 'dsaf', 'sadf', 2001, '4-Door Sedan', 'Electric', 2000, 'red', 2010, 5),
(0000000031, 'sadf', 'sadf', 2001, '4-Door Sedan', 'Electric', 1000, 'saerf', 1000.5882352941177, 4),
(0000000032, 'safgd', 'fdjhg', 2002, 'SUV', 'Petrol', 9000, 'penk', 7205.294117647059, 4),
(0000000033, 'asdf', 'asdf', 2001, '4-Door Sedan', 'Diesel', 1, '1', 1.0004761904761905, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accident`
--
ALTER TABLE `accident`
  ADD PRIMARY KEY (`claimnumber`),
  ADD UNIQUE KEY `policy` (`policy`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `firstname` (`firstname`),
  ADD UNIQUE KEY `lastname` (`lastname`);

--
-- Indexes for table `policy`
--
ALTER TABLE `policy`
  ADD UNIQUE KEY `policynumber` (`policynumber`);

--
-- Indexes for table `policyholder`
--
ALTER TABLE `policyholder`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `licensenumber` (`licensenumber`);

--
-- Indexes for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accident`
--
ALTER TABLE `accident`
  MODIFY `claimnumber` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10003;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1007;

--
-- AUTO_INCREMENT for table `policy`
--
ALTER TABLE `policy`
  MODIFY `policynumber` int(6) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100056;

--
-- AUTO_INCREMENT for table `policyholder`
--
ALTER TABLE `policyholder`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `vehicle`
--
ALTER TABLE `vehicle`
  MODIFY `id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
