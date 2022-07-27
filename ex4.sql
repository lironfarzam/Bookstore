-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 16, 2022 at 10:18 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ex4`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `id` bigint(20) NOT NULL,
  `book_name` varchar(255) DEFAULT NULL,
  `discount` double NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`id`, `book_name`, `discount`, `image`, `price`, `quantity`) VALUES
(1, 'A Game of Thrones (Reissue)', 10, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/0074/9780007448036.jpg', 120, 100),
(2, 'A Game of Thrones: The Graphic Novel : Volume One', 35, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/4404/9780440423218.jpg', 90, 100),
(3, 'A Game of Thrones : Book 1 of A Song of Ice and Fire', 5, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/0064/9780006479888.jpg', 65, 150),
(4, 'A Game of Thrones', 20, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/0074/9780007428540.jpg', 90, 100),
(5, 'A Game of Thrones : A Song of Ice and Fire: Book One', 50, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/5531/9780553103540.jpg', 200, 150),
(6, 'Game of Thrones and Philosophy - Logic Cuts Deeper Than Swords', 30, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/1181/9781118161999.jpg', 150, 150),
(7, 'Harry Potter and the Philosopher\'s Stone', 40, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/4088/9781408855652.jpg', 150, 200),
(8, 'Harry Potter and the Chamber of Secrets', 45, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/4088/9781408855669.jpg', 200, 200),
(9, 'Harry Potter and the Cursed Child - Parts I & II', 5, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/7515/9780751565355.jpg', 50, 100),
(10, 'Harry Potter and the Half-Blood Prince', 20, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/4088/9781408855706.jpg', 100, 100),
(11, 'Harry Potter and the Deathly Hallows', 60, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/4088/9781408855713.jpg', 200, 150),
(12, 'Harry Potter and the Order of the Phoenix', 20, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/4088/9781408855690.jpg', 140, 100),
(13, 'Harry Potter and the Goblet of Fire', 35, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/4088/9781408855683.jpg', 200, 250),
(14, 'Harry Potter and the Prisoner of Azkaban', 25, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/4088/9781408855676.jpg', 100, 100),
(15, 'Harry Potter and the Philosopher\'s Stone', 50, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/4088/9781408855898.jpg', 150, 150),
(16, 'Harry Potter and the Sorcerer\'s Stone', 10, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/5903/9780590353427.jpg', 140, 150),
(17, 'Harry Potter and the Chamber of Secrets', 25, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/4088/9781408855904.jpg', 400, 150),
(18, 'Harry Potter Und Der Stein Der Weisen', 30, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9783/5513/9783551354013.jpg', 180, 100),
(19, 'The Book of Form and Emptiness : Winner of the Women\'s Prize', 0, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/8388/9781838855277.jpg', 120, 150),
(20, 'The Bread the Devil Knead', 0, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/9124/9781912408993.jpg', 150, 100),
(21, 'One piece guía yellow', 0, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9788/4158/9788415866985.jpg', 100, 100);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(22);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `purchase_amount` double NOT NULL,
  `user_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
