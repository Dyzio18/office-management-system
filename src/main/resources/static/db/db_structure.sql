-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 08, 2018 at 07:50 PM
-- Server version: 5.6.34-log
-- PHP Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `office_management_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `case`
--

CREATE TABLE `case` (
  `case_id` bigint(20) NOT NULL,
  `case_date` varchar(255) NOT NULL,
  `case_note` varchar(255) DEFAULT NULL,
  `case_price` decimal(19,2) DEFAULT NULL,
  `case_time` varchar(255) NOT NULL,
  `case_type` varchar(255) DEFAULT NULL,
  `client_name` varchar(255) NOT NULL,
  `client_surname` varchar(255) NOT NULL,
  `case_lawyer` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `case`
--

INSERT INTO `case` (`case_id`, `case_date`, `case_note`, `case_price`, `case_time`, `case_type`, `client_name`, `client_surname`, `case_lawyer`) VALUES
(1, '2018-11-11', 'Prosba o adwokata ws. marszu niepodleglosci', 500.00, '11:00', 'kryminalna', 'Marian', 'Kowalski', 7),
(2, '2018-06-12', 'Pan prosi o porade prawna w sprawie konstytucji', 100.00, '16:00', 'konsultacje', 'Ryszard', 'Kalisz', 7);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role`) VALUES
(1, 'ADMIN'),
(2, 'RECORDER'),
(3, 'LAWYER'),
(4, 'ACCOUNTANT');

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `service_id` int(11) NOT NULL,
  `service_name` varchar(255) NOT NULL,
  `service_date` varchar(32) NOT NULL,
  `service_case_id` int(11) NOT NULL,
  `service_price` decimal(19,2) NOT NULL,
  `service_inner_price` decimal(19,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `active`, `email`, `last_name`, `name`, `password`, `role_id`) VALUES
(1, 1, 'admin@gmail.com', 'Admin', 'Pan', '$2a$10$TNilSEl6ePjGf1K9a5q4NuNB.vkaPzcP/XBRY.zwrnfNPMfF9KxAK', 1),
(2, 1, 'rejestrator@gmail.com', 'Rejestrator', 'Pan', '$2a$10$TNilSEl6ePjGf1K9a5q4NuNB.vkaPzcP/XBRY.zwrnfNPMfF9KxAK', 2),
(3, 1, 'adwokat@gmail.com', 'Adwokat', 'Pan', '$2a$10$TNilSEl6ePjGf1K9a5q4NuNB.vkaPzcP/XBRY.zwrnfNPMfF9KxAK', 3),
(4, 1, 'ksiegowa@gmail.com', 'Ksiegowa', 'Pani', '$2a$10$TNilSEl6ePjGf1K9a5q4NuNB.vkaPzcP/XBRY.zwrnfNPMfF9KxAK', 4),
(17, 0, 'test@gmail.com', 'NazwiskoTestera1', 'ImieTestera1', '$2a$10$/W6BDeKZyjt8LCdz4gc.o.dHHsUVLhJjlIczSx4pKXUPjfP8g7Ue6', 2);

--
-- Indexes for dumped tables
--

# DROP TABLE IF EXISTS `service`;
# /*!40101 SET @saved_cs_client     = @@character_set_client */;
# /*!40101 SET character_set_client = utf8 */;
# CREATE TABLE `service` (
#   `service_id` int(11) NOT NULL AUTO_INCREMENT,
#   `service_name` varchar(255) NOT NULL,
#   `service_price` decimal(10,2) NOT NULL,
#   `service_inner_price` decimal(10,2) NOT NULL,
#   PRIMARY KEY (`service_id`)
# ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
# /*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `client_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_name` varchar(255) NOT NULL,
  `client_surname` varchar(255) NOT NULL,
  `client_phone` varchar(255) NOT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO `client` (`client_id`, `client_name`, `client_surname`, `client_phone`) VALUES (NULL, 'Adam', 'Kowalski', '788996411'), (NULL, 'Beata', 'Tyszkiewicz', '964789125'), (NULL, 'Stefan', 'Kowalski', '789444566'), (NULL, 'Mirosław', 'Nowak', '655455155');

--
-- Indexes for table `case`
--
ALTER TABLE `case`
  ADD PRIMARY KEY (`case_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`service_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `case`
--
ALTER TABLE `case`
  MODIFY `case_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;



INSERT INTO `service` (`service_id`, `service_name`, `service_date`, `service_case_id`, `service_price`, `service_inner_price`) VALUES
(null, 'Konsultacje', '2018-06-08', 1, '150.00', '20.00'),
(null, 'Konsultacje', '2018-06-15', 2, '290.00', '0.00'),
(null, 'Przygotowanie dokumentacji', '2018-06-15', 2, '190.00', '20.00'),
(null, 'Porada prawna', '2018-05-20', 1, '80.00', '0.00'),
(null, 'Porada prawna', '2018-05-22', 1, '80.00', '0.00'),
(null, 'Porada prawna', '2018-05-28', 1, '160.00', '0.00'),
(null, 'Szkolenie z RODO', '2018-05-28', 2, '450.00', '50.00');