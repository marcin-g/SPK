
-- ---
-- Globals
-- ---

-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;

-- ---
-- Table 'User'
-- 
-- ---

DROP TABLE OrderO IF EXISTS;
DROP TABLE Book IF EXISTS;
DROP TABLE UserU IF EXISTS;
		
CREATE TABLE UserU (
  id INT NOT NULL primary key IDENTITY,
  name VARCHAR(255) NULL,
  surname VARCHAR(255) NULL,
  userType VARCHAR(255) NULL,
  email VARCHAR(255) NULL
);

-- ---
-- Table 'Book'
-- 
-- ---
		
CREATE TABLE Book (
  id INT NOT NULL primary key IDENTITY,
  title VARCHAR(255) NULL,
  isbn VARCHAR(255) NULL,
  year INT NULL,
  publisher VARCHAR(255) NULL,
  review_url VARCHAR(255) NULL,
  book_url VARCHAR(255) NULL,
  state VARCHAR(255) NULL
);

-- ---
-- Table 'Order'
-- 
-- ---
		
CREATE TABLE OrderO (
  id INT NOT NULL primary key IDENTITY,
  book_id INT NOT NULL,
  user_id INT NOT NULL,
  time INT NULL,
  book_url VARCHAR(255) NULL
);

-- ---
-- Foreign Keys 
-- ---

ALTER TABLE OrderO ADD FOREIGN KEY (book_id) REFERENCES Book (id);
ALTER TABLE OrderO ADD FOREIGN KEY (user_id) REFERENCES UserU (id);

-- ---
-- Table Properties
-- ---

-- ALTER TABLE 'User' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE 'Book' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE 'Order' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

-- INSERT INTO 'User' ('id','name','surname','userType','email') VALUES
-- ('','','','','');
-- INSERT INTO 'Book' ('id','title','isbn','year','publisher','review_url','book_url','state') VALUES
-- ('','','','','','','','');
-- INSERT INTO 'Order' ('id','book_id','user_id','time','book_url') VALUES
-- ('','','','','');
