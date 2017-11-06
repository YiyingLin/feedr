CREATE TABLE IF NOT EXISTS user (
  username VARCHAR(20),
  password VARCHAR(50),
  phone VARCHAR(10),
  PRIMARY KEY (username),
  UNIQUE (phone)
);

CREATE TABLE IF NOT EXISTS sender(
  username VARCHAR(20),
  sender_rating INT(2),
  lat DOUBLE,
  lon DOUBLE,
  PRIMARY KEY (username),
  FOREIGN KEY (username) REFERENCES user (username)
  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS receiver(
  username VARCHAR(20),
  receiver_rating INT(2),
  PRIMARY KEY (username),
  FOREIGN KEY (username) REFERENCES user (username)
  ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS restaurant(
  username VARCHAR(20),
  resname VARCHAR(40),
  location VARCHAR(60),
  PRIMARY KEY (username),
  FOREIGN KEY (username) REFERENCES user (username)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS food (
  resname VARCHAR(20),
  foodname VARCHAR(40),
  price DECIMAL(6,2),
  type VARCHAR(20),
  PRIMARY KEY (resname,foodname),
  FOREIGN KEY (resname) REFERENCES restaurant (username)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS order_info(
  order_id VARCHAR(20),
  sender_name VARCHAR(20),
  receiver_name VARCHAR(20),
  restaurant_name VARCHAR(20),
  order_cost DECIMAL(6,2),
  deliver_tip DECIMAL(6,2),
  order_time DATETIME,
  dead_line DATETIME,
  delivery_location VARCHAR(60),
  PRIMARY KEY (order_id),
  FOREIGN KEY (sender_name) REFERENCES sender (username)
    ON DELETE CASCADE,
  FOREIGN KEY (receiver_name) REFERENCES receiver (username)
    ON DELETE CASCADE,
  FOREIGN KEY (restaurant_name) REFERENCES restaurant (username)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS cancellation (
  order_id VARCHAR(20),
  username VARCHAR(20),
  cancel_time DATETIME,
  reason VARCHAR(100),
  PRIMARY KEY (order_id,username),
  FOREIGN KEY (order_id) REFERENCES order_info (order_id)
    ON DELETE CASCADE,
  FOREIGN KEY (username) REFERENCES user (username)
  ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS include (
  order_id VARCHAR(20),
  username VARCHAR(20),
  food_name VARCHAR(40),
  PRIMARY KEY (order_id,username,food_name),
  FOREIGN KEY (order_id) REFERENCES order_info (order_id)
    ON DELETE CASCADE,
  FOREIGN KEY (username) REFERENCES restaurant (username)
    ON DELETE CASCADE,
  FOREIGN KEY (food_name) REFERENCES food (food_name)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS rating (
  order_id VARCHAR(20),
  receiver_to_sender_rate INTEGER,
  reveiver_to_rest_rate INTEGER,
  sender_to_receiver INTEGER,
  receiver_to_sender_comment VARCHAR(100),
  reveiver_to_rest_comment VARCHAR(100),
  sender_to_receiver_comment VARCHAR(100),
  PRIMARY KEY (order_id),
  FOREIGN KEY (order_id) REFERENCES order_info (order_id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS delivered (
  order_id VARCHAR(20),
  final_tip DECIMAL(6,2),
  final_total_cost DECIMAL(6,2),
  delivered_time DATETIME,
  PRIMARY KEY (order_id),
  FOREIGN KEY (order_id) REFERENCES order_info (order_id)
    ON DELETE CASCADE
);
