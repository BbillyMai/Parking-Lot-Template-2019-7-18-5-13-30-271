create table `parking_lot`(
  `id` int auto_increment primary key,
  `name` varchar(255) unique,
  `address` varchar(255),
  `capacity` int
)