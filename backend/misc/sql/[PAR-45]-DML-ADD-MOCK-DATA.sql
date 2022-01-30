INSERT INTO LOCATION (location_id, city, country, street, street_number, zipcode)
VALUES(2, 'city 2', 'country 2', 'street 2', 'A2', 'zipcode2');

INSERT INTO LOCATION (location_id, city, country, street, street_number, zipcode)
VALUES(3, 'city 3', 'country 3', 'street 3', 'A3', 'zipcode3');

INSERT INTO LOCATION (location_id, city, country, street, street_number, zipcode)
VALUES(4, 'city 4', 'country 4', 'street 4', 'A4', 'zipcode4');


INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(1, 100, 'parking spot 1 desc', 1641078000, 10, 1, 1, 'parking spot 1', 1640991600, 20, 4);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(2, 50, 'parking spot 2 desc', 1641078000, 10, 0, 1, 'parking spot 2', 1640991600, 20, 4);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(3, 25, 1641078000, 10, 1, 1, 'parking spot 3', 1640991600, 20, 2);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, is_active, is_disabled, name, start_date, width, location_id)
VALUES(4, 200, 'parking spot 4 desc', 1640991600, 1, 0, 'parking spot 4', 1638313200, 20, 2);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, location_id)
VALUES(5, 0, 'parking spot 5 desc', 1640991600, 10, 1, 1, 'parking spot 5', 1638313200, 3);


INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(1, 1641340800, 'First name 1', 1, 'Last name 1', 1, 1641337200, 1);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(2, 1641513600, 'First name 2', 1, 'Last name 2', 2, 1641510000, 1);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(3, 1641340800, 'First name 3', 1, 'Last name 3', 3, 1641337200, 2);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(4, 1641513600, 'First name 4', 1, 'Last name 4', 4, 1641510000, 2);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(5, 1641340800, 'First name 5', 1, 'Last name 5', 5, 1641337200, 3);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(6, 1641513600, 'First name 6', 1, 'Last name 6', 6, 1641510000, 3);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(7, 1641340800, 'First name 7', 1, 'Last name 7', 7, 1641337200, 4);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(8, 1641513600, 'First name 8', 1, 'Last name 8', 8, 1641510000, 4);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(9, 1641513600, 'First name 9', 1, 'Last name 9', 9, 1641337200, 5);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(10, 1641513600, 'First name 10', 0, 'Last name 10', 10, 1641510000, 5);
