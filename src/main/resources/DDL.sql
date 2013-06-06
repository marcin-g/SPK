DROP TABLE OrderO IF EXISTS;
DROP TABLE Borrow IF EXISTS;
DROP TABLE Book IF EXISTS;
DROP TABLE UserU IF EXISTS;
		
CREATE TABLE UserU (
  id INT NOT NULL primary key IDENTITY,
  username VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NULL,
  email VARCHAR(255) NULL,
  roles VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
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
  author VARCHAR(255) NULL,
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
  time BIGINT NULL,
  book_url VARCHAR(255) NULL
);

CREATE TABLE Borrow (
  id INT NOT NULL primary key IDENTITY,
  book_id INT NOT NULL,
  user_id INT NOT NULL,
  begin BIGINT NULL,
  end BIGINT NULL
);

-- ---
-- Foreign Keys 
-- ---

ALTER TABLE OrderO ADD FOREIGN KEY (book_id) REFERENCES Book (id);
ALTER TABLE OrderO ADD FOREIGN KEY (user_id) REFERENCES UserU (id);

ALTER TABLE Borrow ADD FOREIGN KEY (book_id) REFERENCES Book (id);
ALTER TABLE Borrow ADD FOREIGN KEY (user_id) REFERENCES UserU (id);