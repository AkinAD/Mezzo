######################################################################################
##	EECS4413 -BUILDING E-COMMERCE SYSTEMS 											##
##																					##
######################################################################################

DROP TABLE Category;


CREATE TABLE Category (
  category varchar(40) NOT NULL,
   primary key(category)
);

INSERT INTO Category (category) VALUES
	('Hip-hop'),
	('Rap'),
	('Jazz'),
	('Folk'),
	('Pop'),
	('Rock'),
	('Blues'),
	('Country'),
	('Heavy metal'),
	('RnB'),
	('Hard Rock'),
	('Punk rock'),
	('Reggae'),
	('Disco'),
	('Gospel'),
	('Indie'),
	('Electro'),
	('Funk'),
	('Synth-pop');

DROP TABLE if exists Album;

CREATE TABLE Album (
	aid INT NOT NULL,
	artist varchar(60) NOT NULL,
	title varchar(100) NOT NULL,
	category varchar(40) NOT NULL,
	price float NOT NULL,
	picture varchar(300) NOT NULL,
	PRIMARY KEY (aid),
	FOREIGN KEY (category) REFERENCES Category
);

INSERT INTO Album (aid, artist, title, category, price, picture) VALUES
(1, 'Michael Jackson',	'Thriller',	'Pop', 47.30, 'https://upload.wikimedia.org/wikipedia/en/5/55/Michael_Jackson_-_Thriller.png'),
(2, 'AC/DC', 'Back in Black', 'Hard Rock', 29.4, 'https://upload.wikimedia.org/wikipedia/commons/9/92/ACDC_Back_in_Black.png'),
(3, 'Meat Loaf', 'Bat Out of Hell',	'Hard Rock', 21.7, 'https://upload.wikimedia.org/wikipedia/en/0/00/Bat_out_of_Hell.jpg'),
(4, 'Roddy Ricch', 'Please Excuse Me for Being Antisocial', 'Hip-hop', 15.00, 'https://upload.wikimedia.org/wikipedia/en/c/c5/Roddy_Ricch_-_Please_Excuse_Me_for_Being_Antisocial.png');
(5, 'Pink Floyd',	'The Dark Side of the Moon', 'Rock', 24.2, 'https://upload.wikimedia.org/wikipedia/en/3/3b/Dark_Side_of_the_Moon.png'),
(6, 'The Weeknd', 'Beauty Behind the Madness', 'RnB', 15.00, 'https://upload.wikimedia.org/wikipedia/en/b/bd/The_Weeknd_-_Beauty_Behind_the_Madness.png'),
(7, 'Young Thug', 'So Much Fun', 'Hip-hop', 12.25, 'https://upload.wikimedia.org/wikipedia/en/a/a9/Young_Thug_-_So_Much_Fun.png'),
(8, 'Tyler, the Creator', 'Igor', 'Funk', 19.77, 'https://upload.wikimedia.org/wikipedia/en/5/51/Igor_-_Tyler%2C_the_Creator.jpg'),
(9, 'Drake', 'If You''re Reading This It''s Too Late', 'Hip-hop', 15.00, 'https://upload.wikimedia.org/wikipedia/en/1/11/Drake_-_If_You%27re_Reading_This_It%27s_Too_Late.png'),
(10, 'Tame Impala', 'Currents', 'Synth-pop', 20.5, 'https://upload.wikimedia.org/wikipedia/en/9/9b/Tame_Impala_-_Currents.png');

SELECT * FROM ALBUM

/*
 * Address
 * fname: First Name
 * lname: Last Name
 * username: user's username
 * email: users email
 * password: users Password
 */
DROP TABLE account;


CREATE TABLE account (
	fname varchar(25) NOT NULL,
	lname varchar(25) NOT NULL,
	username varchar(25) NOT NULL,
	email varchar(40) NOT NULL,
	password varchar(64) NOT NULL,
	primary key (username)
);

--
-- Dumping data for table `account`
--
INSERT INTO account (fname, lname, username, email, password) VALUES
	('Akin', 'Adewale', 'akinAd', 'akin.pjadewale@yahoo.com', 'akin123'),
	('Aya', 'Abu-Allan', 'ayaAb', 'aya_ab@example.com', 'aya123'),
	('Alan', 'Tang', 'alanT', 'AlanT@example.com', 'alan123'),
	('Cima', 'lastname', 'cimaa', 'cimaa@exmaple.com', 'cima123'),
	('Hugh', 'Mungus', 'hughM','hugh.mungus@example.com', 'hugh123'),
	('Adam', 'Brihmi', 'adamB', 'adamB@example.com', 'adam123'),
	('Mary', 'Jane', 'mj420', 'mj420@stone.com', 'mary123'),
	('Normie', 'Clamp', 'nclamp2', 'nclamp2@usa.gov', 'normie123'),
	('Alfi', 'Ffrench', 'affrench8', 'affrench8@tinypic.com', 'alfi123'),
	('Sabrina', 'Orkney', 'sorkney1', 'sorkney1@redcross.org', 'sabrina123');

SELECT * FROM Account	
DELETE FROM Account WHERE username='jlee';

	
--
-- *put info about table customer here*
--
DROP TABLE Customer;


CREATE TABLE Customer (
username	VARCHAR(25) NOT NULL,
password	VARCHAR(64) NOT NULL,
PRIMARY KEY (username)
);

INSERT INTO Customer (username, password) VALUES
	('hughM', 'hugh123'),
	('mj420', 'mary123'),
	('cimaa', 'cima123'),
	('adamB', 'adam123'),
	('akinAd', 'akin123'),
	('ayaAb', 'aya123'),
	('alanT', 'alan123');
	
SELECT * FROM Customer	
DELETE FROM Customer WHERE username='janedoe';

--
-- Customer profile
-- username: Username to sign in, FK to CUSTOMER
-- fname: first name (can be left empty)
-- lname: last name (can be left empty)
-- email: email address (can be left empty)
--

CREATE TABLE Profile (
username	VARCHAR(25) NOT NULL,
fname		VARCHAR(25),
lname		VARCHAR(25),
email		VARCHAR(40),
privilege	VARCHAR(25) NOT NULL,

PRIMARY KEY (username),
FOREIGN KEY(username) REFERENCES Customer(username) ON DELETE CASCADE);

INSERT INTO Profile (username, fname, lname, email, privilege) VALUES 
	('akinAd', 'Akin', 'Adewale', 'akin.pjadewale@yahoo.com', 'admin'),
	('hughM', 'Hugh', 'Mungus', 'hugh.mungus@example.com', 'customer'),
	('mj420', 'Mary', 'Jane', 'mj420@stone.com', 'customer');

INSERT INTO Profile (username, email, privilege) VALUES 
	('adamB', 'adamB@example.com', 'partner'),
	('ayaAb', 'aya_ab@example.com', 'admin');

INSERT INTO Profile(username,privilege) VALUES
	('alanT', 'admin'),
	('cimaa', 'admin');

SELECT * FROM Profile

SELECT username FROM account WHERE username = 'akinAd'

--
-- visit to website
-- day: date
-- bid: unique identifier of Book
-- eventtype: status of purchase
--

CREATE TABLE VisitEvent (
day varchar(8) NOT NULL,
aid INT not null REFERENCES Album(aid),
eventtype varchar(20) NOT NULL,
FOREIGN KEY(aid) REFERENCES Album(aid)
);

--
-- Data Dump for VisitEvent
--
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('03192020', 1, 'VIEW'),
 ('03212020', 1, 'CART'),
 ('03222020', 1, 'PURCHASE');


--
-- Table structure for table `address`
--
DROP TABLE if exists Address;

CREATE TABLE Address (
	a_id INT  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	username varchar(25) NOT NULL,
	street VARCHAR(100) NOT NULL,
	province VARCHAR(25) NOT NULL,
	country VARCHAR(24) NOT NULL,
	zip VARCHAR(20) NOT NULL,
	phone VARCHAR(20),
	addrType VARCHAR(15) NOT NULL,
	PRIMARY KEY(a_id),
	FOREIGN KEY(username) REFERENCES Customer(username) ON DELETE CASCADE
)

--
-- Dumping data for table `address`
--
INSERT INTO address (username, street, province, country, zip, phone, addrType) VALUES
	('akinAd', '4700 Keele St', 'ON', 'Canada', 'M3J1P3', '613-123-4533', 'Shipping'),
	('ayaAb', '97 Pond RD', 'ON', 'Canada', 'M3J2S5', '416-123-4533', 'Billing'),
	('AlanT', '11 Arboretum Ln', 'ON', 'Canada', 'M3J2S5', '647-123-4533', 'Billing'),
	('cimaa', ' 170 Campus Walk', 'ON', 'Canada', 'M3J1P3', '913-123-4533', 'Shipping');

--
-- Purchase Order
-- lname: last name
-- fname: first name
-- id: purchase order id ( autogenerated, full timestamp of order)
-- status:status of purchase
--

DROP TABLE if exists PO;

CREATE TABLE PO (
po_id 		VARCHAR(15) NOT NULL,
username 	VARCHAR(25)  NOT NULL,
status 		VARCHAR(25) NOT NULL,
a_id 		INT NOT NULL,
po_date 	VARCHAR(10) NOT NULL,
lname		VARCHAR(25) NOT NULL,
fname		VARCHAR(25) NOT NULL,
PRIMARY KEY(po_id),
FOREIGN KEY (a_id) REFERENCES Address(a_id)
);

INSERT INTO PO (po_id, username, status, a_id, po_date, lname, fname) VALUES 
( '201506071545332', 'Roger', 'PROCESSED', 2, '20150607', 'Akin', 'Adewale'),
( '201511212359082', 'John', 'DENIED', 6, '20151121', 'John', 'Willie'),
( '201512041403527', 'Andy', 'ORDERD', 4, '20151204', 'Andy', 'Olly'),
( '201604010203634', 'Roger', 'ORDERD', 10, '20160401', 'Andy', 'Sherry');

SELECT * from PO order by PO_date desc;
select * from PO where PO_date > '20150710' and po_date < '20151203';

--
-- Items on order
-- id : purchase order id
-- aid: unique identifier of Album
-- price: unit price
--

DROP TABLE POItem;

CREATE TABLE POItem (
po_id 		VARCHAR(15) NOT NULL,
aid 		INT NOT NULL,
quantity	INT NOT NULL,
PRIMARY KEY(po_id,aid),
FOREIGN KEY(po_id) REFERENCES PO(po_id) ON DELETE CASCADE,
FOREIGN KEY(aid) REFERENCES Album(aid) ON DELETE CASCADE
);

INSERT INTO POItem (po_id, aid, quantity) VALUES ('201906071545332', 1, 1);
('201911212359082', 2, 2),
('201912041403527', 3, 1),
('201912041403527', 4, 1),
('201912041403527', 1, 3),
('201904010203634', 1, 3),
('202004010203634', 2, 2),
('202004010203634', 3, 1);

--
-- Credit Card payment information
-- username: Username to sign in, FK to CUSTOMER
-- cardType: card type
-- cardHolder: card holder
-- cardNumber: card number
-- expireM: expire month
-- expireY: expire year
--

DROP TABLE CreditInfo;

CREATE TABLE CreditInfo (
username	VARCHAR(25) NOT NULL,
cardType	INT NOT NULL,
cardHolder	VARCHAR(25) NOT NULL,
cardNumber	VARCHAR(20) NOT NULL,
expireM		INT NOT NULL,
expireY		INT NOT NULL,
a_id		INT,
PRIMARY KEY (username),
FOREIGN KEY (username) REFERENCES Customer(username) ON DELETE CASCADE,
FOREIGN KEY (a_id) REFERENCES Address(a_id) ON DELETE CASCADE
);

INSERT INTO CreditInfo (username, cardType, CardHolder, CardNumber, expireM, expireY, a_id) 
VALUES ('akinAd', 1, 'Akin Adewale', '1111 2222 3333 4444', 8, 17, 1);

--
--Order Status
--

-- CREATE TABLE Status (
--     status varchar(20) not null,
--     constraint stat_pk
--         primary key(status)
-- );

-- INSERT INTO Status (status) VALUES ('ORDERED');
-- INSERT INTO Status (status) VALUES ('PROCESSED');
-- INSERT INTO Status (status) VALUES ('DENIED');


/*
 * Review comments on books
 * bid: Book ID
 * username: user's username
 * rating: rate from 1-5
 * review: paragraph of review
 */
CREATE TABLE Review(
aid 		INT NOT NULL,
username	VARCHAR(25) NOT NULL,
rating		INT NOT NULL,
review		VARCHAR(500),
PRIMARY KEY(aid, username),
FOREIGN KEY(aid) REFERENCES Album(aid) ON DELETE CASCADE,
FOREIGN KEY(username) REFERENCES Customer(username) ON DELETE CASCADE
);


INSERT INTO Review (aid, username, rating, review) VALUES 
 (1, 'AlanT', 5, 'All time Classic!'),
 (7, 'akinAd', 4, 'Bangersssss!');


