CREATE TABLE IF NOT EXISTS user (
	user_id 	   INT(20) NOT NULL AUTO_INCREMENT,
    login 		   VARCHAR(10) NOT NULL,
    password 	   VARCHAR(64) NOT NULL,
    first_name 	   VARCHAR(20) NOT NULL,
    last_name 	   VARCHAR(30) NOT NULL,
    security_token VARCHAR(36) NOT NULL,

    CONSTRAINT pk_user
        PRIMARY KEY (user_id),
    CONSTRAINT uk_user_login
        UNIQUE KEY (login),
    CONSTRAINT uk_user_security_token
        UNIQUE KEY (security_token)
) ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS location (
	location_id   INT(20) NOT NULL AUTO_INCREMENT,
    country    	  VARCHAR(30) NOT NULL,
    city 	   	  VARCHAR(30) NOT NULL,
    street 	   	  VARCHAR(35) NOT NULL,
    street_number VARCHAR(5) NOT NULL,
    zipcode 	  VARCHAR(10) NOT NULL,

    CONSTRAINT pk_location
        PRIMARY KEY (location_id),
    CONSTRAINT uk_location
        UNIQUE KEY (zipcode, street, street_number)
) ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS parkingslot (
	parking_slot_id INT(20) NOT NULL AUTO_INCREMENT,
    name 		    VARCHAR(30) NOT NULL,
    start_date 	    INT(20) NOT NULL,
    end_date 	    INT(20) NOT NULL,
    is_active 	    BINARY(1) NOT NULL DEFAULT 1,
    description     TEXT NULL,
    width   		FLOAT(7,2) NULL,
    height 		    FLOAT(7,2) NULL,
	cost 		    FLOAT(5) NOT NULL,
    is_disabled     BINARY(1) NOT NULL DEFAULT 0,
    location_id     INT(20) NOT NULL,

    CONSTRAINT pk_parkingslot
        PRIMARY KEY (parking_slot_id),
    CONSTRAINT fk_parkingslot_location
        FOREIGN KEY (location_id)
        REFERENCES location(location_id) ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT uk_parkingslot
	    UNIQUE KEY (name)
) ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS photo (
	photo_id 	    INT(20) NOT NULL AUTO_INCREMENT,
    path 		    VARCHAR(512) NOT NULL,
    parking_slot_id INT(20) NOT NULL,

    CONSTRAINT pk_photo
        PRIMARY KEY (photo_id),
    CONSTRAINT fk_photo_parkingslot
        FOREIGN KEY (parking_slot_id)
        REFERENCES parkingslot(parking_slot_id)	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT uk_photo
	    UNIQUE KEY (path)
) ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS bookinghistory (
	booking_id      INT(20) NOT NULL AUTO_INCREMENT,
    start_date      INTEGER(20) NOT NULL,
    end_date        INTEGER(20) NOT NULL,
    is_active       BINARY(1) NOT NULL DEFAULT 1,
    owner_id        INT(20) NOT NULL,
    first_name      VARCHAR(20) NOT NULL,
    last_name       VARCHAR(30) NOT NULL,
    parking_slot_id INT(20) NOT NULL,

    CONSTRAINT pk_bookinghistory
        PRIMARY KEY(booking_id),
    CONSTRAINT fk_bookinghistory_parkingslot
        FOREIGN KEY (parking_slot_id)
        REFERENCES parkingslot(parking_slot_id) ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT uk_booking_history
	    UNIQUE KEY(start_date, end_date, parking_slot_id)
) ENGINE = INNODB;