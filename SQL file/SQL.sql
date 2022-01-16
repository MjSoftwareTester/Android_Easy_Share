-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 09, 2022 at 09:48 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `SQL`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_uploads`
--

CREATE TABLE IF NOT EXISTS `tbl_uploads` (
  `upload_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `title` varchar(500) NOT NULL,
  `upload_path` text NOT NULL,
  PRIMARY KEY (`upload_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `tbl_uploads`
--

INSERT INTO `tbl_uploads` (`upload_id`, `user_id`, `title`, `upload_path`) VALUES
(1, 1, 'Documents for reference', 'uploads/documents/IMG-20220103-WA0005.jpg'),
(2, 2, 'Test', 'uploads/documents/IMG_20220104_091906.jpg'),
(5, 12, 'mydocument', 'uploads/documents/IMG-20220106-WA0002.jpeg'),
(6, 13, 'myimage', 'uploads/documents/IMG-20220106-WA0000.jpg'),
(7, 14, 'img', 'uploads/documents/IMG-20220106-WA0002.jpeg'),
(8, 16, 'my docu', 'uploads/documents/Screenshot_20220108_153520.jpg'),
(9, 17, 'my Adhara', 'uploads/documents/Screenshot_20220108_153520.jpg'),
(10, 19, 'images of licence', 'uploads/documents/Screenshot_20220108_153520.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE IF NOT EXISTS `tbl_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `email` varchar(200) NOT NULL,
  `mobile` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `created` varchar(50) NOT NULL,
  `photo` text NOT NULL,
  `otp` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
