-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 25, 2023 at 09:37 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bancaire`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `accountNumber` varchar(50) NOT NULL,
  `InternalAccountNumber` varchar(50) DEFAULT NULL,
  `balance` varchar(50) DEFAULT NULL,
  `accountHolder` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `accountNumber`, `InternalAccountNumber`, `balance`, `accountHolder`) VALUES
(1, 'ACC123', 'INT123', '2290.0', 1),
(2, 'ACC456', 'INT456', '195.0', 2),
(3, '33378222', 'LCFEM40I', '5010.0', 3);

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  `STATUS` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`, `person_id`, `STATUS`) VALUES
(1, 'admin', '123456', 1, 1),
(2, 'mdk19', '123456', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `banktransaction`
--

CREATE TABLE `banktransaction` (
  `id` int(11) NOT NULL,
  `amount` varchar(50) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `TransactionType` varchar(50) DEFAULT NULL,
  `sourceAccountNumber` varchar(50) DEFAULT NULL,
  `targetAccountNumber` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `banktransaction`
--

INSERT INTO `banktransaction` (`id`, `amount`, `date`, `TransactionType`, `sourceAccountNumber`, `targetAccountNumber`) VALUES
(1, '200.00', '2023-01-01', 'Transfer', 'ACC123', 'ACC456'),
(2, '50.00', '2023-02-01', 'Deposit', '33378222', 'ACC456'),
(3, '100.00', '2023-03-01', 'Withdrawal', 'ACC456', '33378222');

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phoneNumber` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`id`, `firstName`, `lastName`, `email`, `phoneNumber`) VALUES
(1, 'John', 'Doe', 'john.doe@example.com', '123-456-7890'),
(2, 'Jane', 'Smith', 'jane.smith@example.com', '987-654-3210'),
(3, 'Mohamed', 'Doukkani', 'dk@hjdf', '02555'),
(5, 'ff', 'ffs', 'dff', 'fddf'),
(6, 'mohamed', 'jfjhf', 'hhjjj@hjjhf', 'nfjfj'),
(7, 'fdhud', 'dfj', 'fddd', 'sdf'),
(8, 'jdfhj', 'jfjh', 'jfdhjjhd', '45455');

-- --------------------------------------------------------

--
-- Stand-in structure for view `personaccountview`
-- (See below for the actual view)
--
CREATE TABLE `personaccountview` (
`person_id` int(11)
,`firstName` varchar(50)
,`lastName` varchar(50)
,`email` varchar(50)
,`phoneNumber` varchar(50)
,`accountNumber` varchar(50)
,`InternalAccountNumber` varchar(50)
,`balance` varchar(50)
);

-- --------------------------------------------------------

--
-- Structure for view `personaccountview`
--
DROP TABLE IF EXISTS `personaccountview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `personaccountview`  AS SELECT `p`.`id` AS `person_id`, `p`.`firstName` AS `firstName`, `p`.`lastName` AS `lastName`, `p`.`email` AS `email`, `p`.`phoneNumber` AS `phoneNumber`, `a`.`accountNumber` AS `accountNumber`, `a`.`InternalAccountNumber` AS `InternalAccountNumber`, `a`.`balance` AS `balance` FROM (`person` `p` join `account` `a` on(`p`.`id` = `a`.`accountHolder`)) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`,`accountHolder`,`accountNumber`),
  ADD KEY `fk_account_person` (`accountHolder`);

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_admin_person` (`person_id`);

--
-- Indexes for table `banktransaction`
--
ALTER TABLE `banktransaction`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `banktransaction`
--
ALTER TABLE `banktransaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `fk_account_person` FOREIGN KEY (`accountHolder`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `fk_admin_person` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
