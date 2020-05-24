-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 24, 2020 at 08:38 AM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.3

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
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
