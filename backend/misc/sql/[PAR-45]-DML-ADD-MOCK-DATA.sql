INSERT INTO LOCATION (location_id, city, country, street, street_number, zipcode)
VALUES(1, 'Warszawa', 'Poland', 'Jana Pawła II', '120', '01-202');

INSERT INTO LOCATION (location_id, city, country, street, street_number, zipcode)
VALUES(2, 'Poznań', 'Poland', 'Młyńska', '12', '60-001');

INSERT INTO LOCATION (location_id, city, country, street, street_number, zipcode)
VALUES(3, 'Białystok', 'Poland', 'Szkolna', '20', '15-200');

INSERT INTO LOCATION (location_id, city, country, street, street_number, zipcode)
VALUES(4, 'Sosnowiec', 'Poland', 'Spokojna', 'A5', '41-200');

INSERT INTO LOCATION (location_id, city, country, street, street_number, zipcode)
VALUES(5, 'Wrocław', 'Poland', 'Pereca', 'B12', '50-100');

INSERT INTO LOCATION (location_id, city, country, street, street_number, zipcode)
VALUES(6, 'Sulejówek', 'Poland', '3 Maja', '100', '05-079');

INSERT INTO LOCATION (location_id, city, country, street, street_number, zipcode)
VALUES(7, 'Pruszków', 'Poland', 'Komorowska', '3', '05-700');

INSERT INTO LOCATION (location_id, city, country, street, street_number, zipcode)
VALUES(8, 'Kraków', 'Poland', 'Szeroka', '4/C', '30-010');

INSERT INTO LOCATION (location_id, city, country, street, street_number, zipcode)
VALUES(9, 'New York', 'USA', 'Wall Street', '10', '10018');

INSERT INTO LOCATION (location_id, city, country, street, street_number, zipcode)
VALUES(10, 'Łódź', 'Poland', 'Piotrkowska', '2', '90-001');

INSERT INTO LOCATION (location_id, city, country, street, street_number, zipcode)
VALUES(11, 'Gdańsk', 'Poland', 'Cyprysowa', '7', '80-003');


INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(1, 100, 'Parking spot in private multi-level car park', 1643670000, 6, 1, 1, 'Parking spot Warsaw', 1638313200,
 3, 1);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(2, 50, 'Parking spot in the underground car park in the city center', 1643670000, 11, 1, 1, 'City center
parking spot', 1638313200, 3, 1);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(3, 25, 'Parking in the bussiness city center next to Warsaw Spire', 1643670000, 8, 1, 1, 'Warsaw Spire parking',
1638313200, 4, 1);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(4, 200, 'Luxury parking spot in the private underground parking lot next to the sea', 1643670000, 9, 1, 1,
'Dockyard parking spot', 1638313200, 3, 11);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(5, 7, 'Parking spot close to the city center', 1643670000, 8, 1, 1, 'Parking spot Karolkowa', 1638313200, 4, 3);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(6, 70, 'Parking spot next to the stadium in the multi-level car park', 1643670000, 7, 1, 1, 'Stadium parking
spot', 1638313200, 3, 4);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(7, 15, 'Parking spot in the nice green area', 1643670000, 5, 0, 1, 'Park parking spot', 1638313200, 3, 5);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(8, 25, 'Parking spot next to the shopping hall Atrium', 1643670000, 8, 0, 1, 'Atrium parking spot',
1638313200, 4, 3);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(9, 30, 'Parking spot in the subarbs close to the train station', 1643670000, 9, 0, 1, 'Train station parking',
1638313200, 3, 6);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(10, 2, 'Parking spot close the woods and the main street', 1643670000, 10, 0, 1, 'Sulejowek parking spot',
1638313200, 3, 6);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(11, 3, 'Parking spot which is disability friendly for large cars', 1643670000, 9, 1, 1, 'Parking spot family',
1638313200, 4, 7);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(12, 1, 'Parking spot which is ideal for the small trucks', 1643670000, 8, 1, 1, 'Parking spot trucks',
1638313200,
 3, 8);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(13, 4, 'Parking spot for a family car next to city center', 1643670000, 7, 1, 1, 'City parking spot', 1638313200,
 4, 7);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(14, 9, 'Parking spot for a small car in the close area to shopping hall', 1643670000, 6, 1, 1, 'Mall parking
spot', 1638313200, 3, 8);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(15, 1.5, 'Small parking spot on the underground car park', 1643670000, 7, 1, 1, 'Underground parking spot',
1638313200, 4, 9);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(16, 13, 'Parking spot on the multi-level underground car park', 1643670000, 10, 1, 1, 'Parking spot car park',
1638313200, 4, 10);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(17, 300, 'Parking spot on the close area to the business city center', 1643670000, 9, 1, 1, 'Business parking
spot', 1638313200, 5, 9);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(18, 52, '1+1 Two parking spots in one next to each other', 1643670000, 8, 1, 1, 'Two parking spots', 1638313200,
3, 10);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(19, 2, 'Parking spot for a rather small car next to Cyprysowa street', 1643670000, 9, 1, 1, 'Cyprysowa parking',
1638313200, 3, 11);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(20, 6, 'Parking spot in the close area to the kindergarden', 1643670000, 10, 1, 1, 'Kindergarden parking',
1638313200, 2, 2);

INSERT INTO PARKINGSLOT
(parking_slot_id, cost, description, end_date, height, is_active, is_disabled, name, start_date, width, location_id)
VALUES(21, 10, 'Parking spot for a small truck or a large car', 1643670000, 7, 1, 1, 'Medium size parking spot',
1638313200, 3, 2);


INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(1, 1641769200, 'Rhys', 1, 'Hawkins', 1, 1641772800, 1);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(2, 1641423600, 'Vilma', 1, 'Jarvi', 2, 1641430800, 2);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(3, 1641697200, 'Vilma', 1, 'Jarvi', 2, 1641700800, 3);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(4, 1641880800, 'Ted', 1, 'Ellison', 3, 1641891600, 4);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(5, 1642334400, 'Ted', 1, 'Ellison', 3, 1642338000, 5);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(6, 1641513600, 'Heath', 1, 'Atwood', 4, 1641510000, 6);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(7, 1642417200, 'Kinslee', 1, 'Fink', 5, 1642420800, 7);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(8, 1642593600, 'Joshua', 1, 'Wilson', 6, 1642597200, 8);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(9, 1641034800, 'Victoria', 1, 'Roach', 7, 1641045600, 9);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(10, 1641124800, 'Ellis', 1, 'Schaefer', 8, 1641128400, 10);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(11, 1638441000, 'Regan', 1, 'Rosen', 9, 1638448200, 11);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(12, 1638520200, 'Daisy', 1, 'Morgan', 10, 1638523800, 12);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(13, 1638599400, 'Taniyah', 1, 'Miles', 11, 1638606600, 13);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(14, 1638595800, 'Becky', 1, 'George', 12, 1638603000, 14);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(15, 1638851400, 'Alyvia', 1, 'Cope', 13, 1638855000, 15);

INSERT INTO BOOKINGHISTORY
(booking_id, end_date, first_name, is_active, last_name, owner_id, start_date, parking_slot_id)
VALUES(16, 1638775800, 'Clyde', 1, 'Lucas', 14, 1638862200, 16);