-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 27, 2020 at 02:51 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eventscheduler`
--

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `Room_id` varchar(255) NOT NULL,
  `Event_date` date NOT NULL,
  `Period` varchar(255) NOT NULL,
  `Event_name` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `capacity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`Room_id`, `Event_date`, `Period`, `Event_name`, `username`, `capacity`) VALUES
('Room1', '2020-06-22', 'P1', 'Graduation_Party', 'mahmoud98', 50),
('Room1', '2020-06-22', 'P3', 'Biology_Class', 'faroukelsaroo5', 50),
('Room1', '2020-06-22', 'P5', 'Math_Class', 'glitteraway', 50),
('Room2', '2020-07-22', 'P1', 'Geography_Class', 'lightning21', 50),
('Room2', '2020-07-22', 'P2', 'Arabic_Class', 'keko_32', 50),
('Room2', '2020-07-22', 'P4', 'History_Class', 'glitteraway', 50);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `occupation` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `email`, `phone`, `occupation`, `first_name`, `last_name`) VALUES
('faroukelsaroo5', 'elnegma', 'lowkeypretty@gmail.com', '12554221', 'Teacher', 'Farouk', 'Saroo5'),
('flyonmountain', 'elnegma', 'fares@gam.com', '2212312', 'Nurse', 'Fares', '3atawya'),
('glitteraway', 'elnegma', 's.kabnoury65@outlook.com', '1255521', 'Attendant', 'Samira', 'Kabnoury'),
('keko_32', 'elnegma', 'Elhelw@gmail.com', '435345', 'Dentist', 'Karim', 'ELhelw'),
('lightning21', 'elnegma', 'Inas_Farouk@gmail.com', '435345', 'N/A', 'Inas', 'Farouk'),
('mahmoud98', 'elnegma', 'mahmoud_44@gmail.com', '32123342', 'Lawyer', 'Mahmoud', 'Elnegma'),
('oleodark', 'yasalama', 'laryfary@sf.com', '2151651', 'N/A', 'Lary', 'Fary');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`Room_id`,`Event_date`,`Period`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
