CREATE TABLE IF NOT EXISTS USERS (
  UID int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  password varchar(45) NOT NULL,
  type enum('A','D','C','R') NOT NULL,
  name varchar(45) NOT NULL,
  family varchar(45) NOT NULL,
  PRIMARY KEY (UID),
  UNIQUE KEY username_UNIQUE (username)
);
CREATE TABLE IF NOT EXISTS WARE (
  WID int(11) NOT NULL,
  name varchar(45) NOT NULL,
  manufacturer varchar(45) DEFAULT NULL,
  weight int(11) DEFAULT NULL,
  quantity int(11) DEFAULT NULL,
  price int(11) DEFAULT NULL,
  price_s enum('B','E','S') DEFAULT NULL,
  PRIMARY KEY (WID)
);
CREATE TABLE IF NOT EXISTS WAREHOUSE (
  WHID int(11) NOT NULL,
  WID int(11) NOT NULL,
  KEY fk_WH_WID_idx (WID),
  CONSTRAINT fk_WH_WID FOREIGN KEY (WID) REFERENCES WARE (WID) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS CERTIFICATE (
  CID int(11) NOT NULL,
  date_to date DEFAULT NULL,
  price_to int(11) DEFAULT NULL,
  source_country varchar(45) DEFAULT NULL,
  enterance enum('A','E','W','F') DEFAULT NULL,
  WHID int(11) DEFAULT NULL,
  PRIMARY KEY (CID)
);
CREATE TABLE IF NOT EXISTS CERTHOUSE (
  CHID int(11) NOT NULL,
  CID int(11) NOT NULL,
  KEY fk_CID_idx (CID),
  CONSTRAINT fk_CH_CID FOREIGN KEY (CID) REFERENCES CERTIFICATE (CID) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS DECLERATION (
  DID int(11) NOT NULL AUTO_INCREMENT,
  date date NOT NULL,
  WHID int(11) NOT NULL,
  source_country varchar(45) DEFAULT NULL,
  enterance enum('A','E','W','F') DEFAULT NULL,
  CHID int(11) DEFAULT NULL,
  PRIMARY KEY (DID)
);
CREATE TABLE IF NOT EXISTS RULE (
  RID int(11) NOT NULL AUTO_INCREMENT,
  date_from date DEFAULT NULL,
  date_to date DEFAULT NULL,
  source_country varchar(45) DEFAULT NULL,
  enterance enum('A','E','W','F') DEFAULT NULL,
  price_from int(11) DEFAULT NULL,
  price_to int(11) DEFAULT NULL,
  per_price_from int(11) DEFAULT NULL,
  per_price_to int(11) DEFAULT NULL,
  ware_names varchar(400) DEFAULT NULL,
  manufacturer_names varchar(400) DEFAULT NULL,
  CHID int(11) DEFAULT NULL,
  PRIMARY KEY (RID)
);