CREATE TABLE IF NOT EXISTS user (
  username VARCHAR(20),
  password VARCHAR(50),
  phone VARCHAR(10),
  type VARCHAR(15),
  PRIMARY KEY (username),
  UNIQUE (phone)
);

CREATE TABLE IF NOT EXISTS sender(
  username VARCHAR(20),
  sender_rating INT(2),
  location VARCHAR(60),
  PRIMARY KEY (username),
  FOREIGN KEY (username) REFERENCES user (username)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS receiver(
  username VARCHAR(20),
  PRIMARY KEY (username),
  FOREIGN KEY (username) REFERENCES user (username)
    ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS restaurant(
  username VARCHAR(20),
  resname VARCHAR(40) NOT NULL UNIQUE,
  restaurant_rating INT(2),
  location VARCHAR(60) NOT NULL ,
  PRIMARY KEY (username),
  FOREIGN KEY (username) REFERENCES user (username)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS food (
  res_username VARCHAR(20) NOT NULL,
  foodname VARCHAR(40),
  price DECIMAL(6,2) NOT NULL ,
  type VARCHAR(20) NOT NULL ,
  PRIMARY KEY (res_username,foodname),
  FOREIGN KEY (res_username) REFERENCES restaurant (username)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS order_info(
  order_id INT AUTO_INCREMENT,
  sender_name VARCHAR(20),
  receiver_name VARCHAR(20) NOT NULL,
  restaurant_name VARCHAR(20) NOT NULL ,
  order_cost DECIMAL(6,2) NOT NULL ,
  deliver_tip DECIMAL(6,2) NOT NULL ,
  order_time TIMESTAMP,
  deadline TIMESTAMP,
  delivery_location VARCHAR(60) NOT NULL ,
  PRIMARY KEY (order_id),
  FOREIGN KEY (sender_name) REFERENCES sender (username),
  FOREIGN KEY (receiver_name) REFERENCES receiver (username),
  FOREIGN KEY (restaurant_name) REFERENCES restaurant (username)
);

CREATE TABLE IF NOT EXISTS cancellation (
  order_id INT,
  username VARCHAR(20) NOT NULL ,
  cancel_time TIMESTAMP,
  reason VARCHAR(100),
  PRIMARY KEY (order_id,username),
  FOREIGN KEY (order_id) REFERENCES order_info (order_id)
    ON DELETE CASCADE,
  FOREIGN KEY (username) REFERENCES user (username)
    ON DELETE NO ACTION,
  UNIQUE (order_id)
);


CREATE TABLE IF NOT EXISTS order_include_food (
  order_id INT,
  res_username VARCHAR(20),
  foodname VARCHAR(40),
  food_quantity INT(2) DEFAULT 1,
  PRIMARY KEY (order_id,res_username,foodname),
  FOREIGN KEY (order_id) REFERENCES order_info(order_id)
    ON DELETE CASCADE,
  FOREIGN KEY (res_username, foodname) REFERENCES food (res_username, foodname)
    ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS rating (
  order_id INT,
  receiver_to_sender_rate INTEGER,
  reveiver_to_rest_rate INTEGER,
  receiver_to_sender_comment VARCHAR(100),
  reveiver_to_rest_comment VARCHAR(100),
  PRIMARY KEY (order_id),
  FOREIGN KEY (order_id) REFERENCES order_info (order_id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS delivered (
  order_id INT,
  final_tip DECIMAL(6,2),
  final_total_cost DECIMAL(6,2),
  delivered_time TIMESTAMP,
  PRIMARY KEY (order_id),
  FOREIGN KEY (order_id) REFERENCES order_info (order_id)
    ON DELETE CASCADE
);

CREATE VIEW userInfo AS SELECT username, phone,type FROM user;