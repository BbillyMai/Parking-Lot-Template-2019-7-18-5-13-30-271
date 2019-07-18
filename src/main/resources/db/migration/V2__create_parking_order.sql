create table `parking_order`(
  `id` int auto_increment primary key,
  `parking_lot_name` varchar(255) unique,
  `car_number` varchar(255),
  `start_time` datetime,
  `end_time` datetime,
  `status` boolean,
  `parking_lot_id` int
)