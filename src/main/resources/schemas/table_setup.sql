CREATE TABLE user (
  username VARCHAR(20),
  password VARCHAR(50),
  phone VARCHAR(10),
  type VARCHAR(15),
  PRIMARY KEY (username),
  UNIQUE (phone)
);

CREATE TABLE sender(
  username VARCHAR(20),
  sender_rating INT(2),
  location VARCHAR(100),
  PRIMARY KEY (username),
  FOREIGN KEY (username) REFERENCES user (username)
    ON DELETE CASCADE
);

CREATE TABLE receiver(
  username VARCHAR(20),
  PRIMARY KEY (username),
  FOREIGN KEY (username) REFERENCES user (username)
    ON DELETE CASCADE
);


CREATE TABLE restaurant(
  username VARCHAR(20),
  resname VARCHAR(40) NOT NULL UNIQUE,
  restaurant_rating INT(2),
  location VARCHAR(100) NOT NULL ,
  PRIMARY KEY (username),
  FOREIGN KEY (username) REFERENCES user (username)
    ON DELETE CASCADE
);

CREATE TABLE food (
  res_username VARCHAR(20) NOT NULL,
  foodname VARCHAR(40),
  price DECIMAL(6,2) NOT NULL ,
  type VARCHAR(20) NOT NULL ,
  PRIMARY KEY (res_username,foodname),
  FOREIGN KEY (res_username) REFERENCES restaurant (username)
    ON DELETE CASCADE
);

CREATE TABLE order_info(
  order_id INT AUTO_INCREMENT,
  sender_name VARCHAR(20),
  receiver_name VARCHAR(20) NOT NULL,
  restaurant_name VARCHAR(20) NOT NULL ,
  order_cost DECIMAL(6,2) NOT NULL ,
  deliver_tip DECIMAL(6,2) NOT NULL ,
  order_time DATETIME,
  deadline DATETIME,
  delivery_location VARCHAR(100) NOT NULL ,
  PRIMARY KEY (order_id),
  FOREIGN KEY (sender_name) REFERENCES sender (username) ON DELETE SET NULL,
  FOREIGN KEY (receiver_name) REFERENCES receiver (username) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_name) REFERENCES restaurant (username) ON DELETE CASCADE
);

CREATE TABLE cancellation (
  order_id INT,
  username VARCHAR(20) NOT NULL ,
  cancel_time DATETIME,
  reason VARCHAR(100),
  PRIMARY KEY (order_id,username),
  FOREIGN KEY (order_id) REFERENCES order_info (order_id)
    ON DELETE CASCADE,
  FOREIGN KEY (username) REFERENCES user (username)
    ON DELETE CASCADE,
  UNIQUE (order_id)
);


CREATE TABLE order_include_food (
  order_id INT,
  res_username VARCHAR(20),
  foodname VARCHAR(40),
  food_quantity INT(2) DEFAULT 1,
  PRIMARY KEY (order_id,res_username,foodname),
  FOREIGN KEY (order_id) REFERENCES order_info(order_id)
    ON DELETE CASCADE,
  FOREIGN KEY (res_username, foodname) REFERENCES food (res_username, foodname)
    ON DELETE CASCADE
);

CREATE TABLE rating (
  order_id INT,
  receiver_to_sender_rate INTEGER,
  reveiver_to_rest_rate INTEGER,
  receiver_to_sender_comment VARCHAR(100),
  reveiver_to_rest_comment VARCHAR(100),
  PRIMARY KEY (order_id),
  FOREIGN KEY (order_id) REFERENCES order_info (order_id)
    ON DELETE CASCADE
);

CREATE TABLE delivered (
  order_id INT,
  final_tip DECIMAL(6,2),
  final_total_cost DECIMAL(6,2),
  delivered_time DATETIME,
  PRIMARY KEY (order_id),
  FOREIGN KEY (order_id) REFERENCES order_info (order_id)
    ON DELETE CASCADE
);

CREATE VIEW userInfo AS SELECT username, phone,type FROM user;
CREATE VIEW deliveredOrders AS SELECT DISTINCT O.order_id from order_info O, delivered D WHERE O.order_id=D.order_id;
CREATE VIEW takenOrders AS SELECT DISTINCT order_id from order_info O, sender S WHERE O.sender_name=S.username;
CREATE VIEW cancelledOrders AS SELECT DISTINCT O.order_id from order_info O, cancellation C WHERE o.order_id=C.order_id;