# use other db name, username, etc as necessary
CREATE DATABASE IF NOT EXISTS feedr;
CREATE user IF NOT EXISTS feedr_user;
GRANT ALL ON feedr.* TO 'feedr_user'@'localhost' identified by 'feedr_user';
