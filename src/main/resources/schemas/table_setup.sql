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