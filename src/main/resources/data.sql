--Users......................................
INSERT INTO users (username, password, email, enabled)
VALUES ('Admin', '$2a$10$hvWJkmZmWsRN/HYKWoqSNOzM0BwW.NqPkw8swa6lRGrYIE6t7BoZq', 'admin1@test.com', TRUE ),
       ('Librarian1','$2a$10$hvWJkmZmWsRN/HYKWoqSNOzM0BwW.NqPkw8swa6lRGrYIE6t7BoZq', 'librarian1@test.com', TRUE),
       ('Librarian2','$2a$10$hvWJkmZmWsRN/HYKWoqSNOzM0BwW.NqPkw8swa6lRGrYIE6t7BoZq','librarian2@test.com', TRUE),
       ('Librarian3','$2a$10$hvWJkmZmWsRN/HYKWoqSNOzM0BwW.NqPkw8swa6lRGrYIE6t7BoZq','librarian3@test.com', TRUE),
       ('Student1','$2a$10$hvWJkmZmWsRN/HYKWoqSNOzM0BwW.NqPkw8swa6lRGrYIE6t7BoZq','student1@test.com', TRUE),
       ('Student2','$2a$10$hvWJkmZmWsRN/HYKWoqSNOzM0BwW.NqPkw8swa6lRGrYIE6t7BoZq','student2@test.com', TRUE),
       ('Student3','$2a$10$hvWJkmZmWsRN/HYKWoqSNOzM0BwW.NqPkw8swa6lRGrYIE6t7BoZq','student3@test.com', TRUE),
       ('Student4','$2a$10$hvWJkmZmWsRN/HYKWoqSNOzM0BwW.NqPkw8swa6lRGrYIE6t7BoZq','student4@test.com', TRUE),
       ('Student5','$2a$10$hvWJkmZmWsRN/HYKWoqSNOzM0BwW.NqPkw8swa6lRGrYIE6t7BoZq','student5@test.com', TRUE),
       ('Student6','$2a$10$hvWJkmZmWsRN/HYKWoqSNOzM0BwW.NqPkw8swa6lRGrYIE6t7BoZq','student6@test.com', TRUE),
       ('Student7','$2a$10$hvWJkmZmWsRN/HYKWoqSNOzM0BwW.NqPkw8swa6lRGrYIE6t7BoZq','student7@test.com', TRUE),
       ('Student8','$2a$10$hvWJkmZmWsRN/HYKWoqSNOzM0BwW.NqPkw8swa6lRGrYIE6t7BoZq','student8@test.com', TRUE),
       ('Student9','$2a$10$hvWJkmZmWsRN/HYKWoqSNOzM0BwW.NqPkw8swa6lRGrYIE6t7BoZq','student9@test.com', TRUE),
       ('Student10','$2a$10$hvWJkmZmWsRN/HYKWoqSNOzM0BwW.NqPkw8swa6lRGrYIE6t7BoZq','student10@test.com', TRUE);

--Authorities......................................
INSERT INTO authorities (username, authority)
VALUES ('Admin', 'ROLE_ADMIN');

INSERT INTO authorities (username, authority)
VALUES ('Librarian1', 'ROLE_LIBRARIAN'),
       ('Librarian2', 'ROLE_LIBRARIAN'),
       ('Librarian3', 'ROLE_LIBRARIAN');

INSERT INTO authorities (username, authority)
VALUES ('Student1', 'ROLE_STUDENT'),
       ('Student2', 'ROLE_STUDENT'),
       ('Student3', 'ROLE_STUDENT'),
       ('Student4', 'ROLE_STUDENT'),
       ('Student5', 'ROLE_STUDENT'),
       ('Student6', 'ROLE_STUDENT'),
       ('Student7', 'ROLE_STUDENT'),
       ('Student8', 'ROLE_STUDENT'),
       ('Student9', 'ROLE_STUDENT'),
       ('Student10', 'ROLE_STUDENT');

--Accounts.....................................
INSERT INTO accounts (id, first_name_student, last_name_student, dob, student_class, name_of_teacher)
VALUES (1000, 'David Eric', 'Grohl', '2018-01-01', 'obb', 'Gaby'),
       (1001, 'Oliver Taylor', 'Hawkins', '2017-02-02', 'obb', 'Gaby'),
       (1002, 'Nat', 'Mendel', '2016-03-04', 'mba', 'Chrystel'),
       (1003, 'Chris', 'Shifflet', '2016-04-04', 'mba', 'Chrystel'),
       (1004, 'Pat', 'Smear', '2015-05-05', 'mbc', 'Tina'),
       (1005, 'Michael', 'Balzary', '2015-08-08', 'mbe', 'Dennis'),
       (1006, 'Anthony', 'Kiedis', '2014-01-01', 'bba', 'Mirjam'),
       (1007, 'Chad', 'Smith', '2014-02-02', 'bba', 'Mirjam'),
       (1008, 'John', 'Frusciante', '2013-07-07', 'bbc', 'Ricardo'),
       (1009, 'Krist', 'Novoselic', '2012-09-09', 'bbd', 'Max'),
       (1010, 'Marijana', 'Mikolcic', '1982-01-09', 'bba', 'Hobbe'),
       (1011, 'Ravi', 'Vid', '1980-01-01', 'mbe', 'Dennis'),
       (1012, 'Ilon', 'Maj', '1978-01-01', 'obb', 'Gaby');

--Add user to account
UPDATE accounts SET username = 'Student10' WHERE id = 1000;
UPDATE accounts SET username = 'Student1' WHERE id = 1001;
UPDATE accounts SET username = 'Student2' WHERE id = 1002;
UPDATE accounts SET username = 'Student3' WHERE id = 1003;
UPDATE accounts SET username = 'Student4' WHERE id = 1004;
UPDATE accounts SET username = 'Student5' WHERE id = 1005;
UPDATE accounts SET username = 'Student6' WHERE id = 1006;
UPDATE accounts SET username = 'Student7' WHERE id = 1010;
UPDATE accounts SET username = 'Student8' WHERE id = 1011;
UPDATE accounts SET username = 'Student9' WHERE id = 1012;

--Book......................................
INSERT INTO books (id, isbn, book_title, name_author, name_illustrator, suitable_age)
VALUES (1000, 12345, 'Kleine onderzoekers voertuigen', 'Ruth Martin', 'Ruth Martin', 4),
       (1001, 23456, 'Graafmachines en kiepautos', 'Angela Royston', 'David Barrow', 4),
       (1002, 34567, 'Barbapapa', 'Annette Tilson', 'Talus Taylor', 4),
       (1003, 45678, 'Woeste Willem', 'Ingrid Schubert', 'Dieter Schubert', 5),
       (1004, 56789, 'Sjaantje doet alsof', 'Sjoerd Kuyper', 'Daan Remmerts de Vries', 5),
       (1005, 67890, 'Kleine Muis ontdekt de wereld', 'Gillian Lobel', 'Daniel Howarth', 5),
       (1006, 78901, 'Moppereend', 'Joyce Dunbar', 'Petr Horacek', 5),
       (1007, 89012, 'Molli', 'Katri Kirkkopelto', 'Katri Kirkkopelto', 6),
       (1008, 90123, 'Hier die bal', 'Vivian den Hollander', 'Saskia Halfmouw', 6),
       (1009, 01234, 'Paw Patrol Problemen in de jungle', 'Big Balloon Publishers', 'Nickelodeon', 6),
       (1010, 123456, 'Langzaam, zosnel als zij konden', 'Toon Tellegen', 'Mance Post', 7),
       (1011, 234567, 'Sprookjes voor alle kinderen', 'Lea Smulders', 'Gyo Fujikawa', 7),
       (1012, 345678, 'Vos en Haas', 'Sylvia Vanden Heede', 'The Tjong Khing', 7),
       (1013, 456789, 'Boom is boos', 'Marjolein Krijger', 'Marjolein Krijger', 7),
       (1014, 567890, 'De reuzenperzik', 'Roald Dahl', 'Quentin Blake', 8),
       (1015, 678901, 'Matilda', 'Roald Dahl', 'Quentin Blake', 8),
       (1016, 789012, 'Griezelbeelden', 'Paul van Loon', 'Paul van Loon', 8),
       (1017, 890123, 'Ufo Alarm', 'Jozua Douglas', 'Elly Hees', 8),
       (1018, 901234, 'De gruwelijke generaal', 'Jozua Douglas', 'Elly Hees', 9),
       (1019, 09876, 'Die dag in Hiroshima', 'Toshi Maruki', 'Toshi Maruki', 9),
       (1020, 98765, 'De man die bomen plantte', 'Jean Giono','Michael McCurdy', 9 ),
       (1021, 398276, 'Gozert', 'Pieter Koolwijk', 'Linde Faas', 9),
       (1022, 87654, 'De jongen in de jurk', 'David Walliams', 'Quentin Blake', 10),
       (1023, 76543, 'De kleine Nicolaas', 'Jean Jacques Sempe', 'Rene Goscinny', 10),
       (1024, 65432, 'Nieuwe avonturen van de kleine Nicolaas','Jean Jacques Sempe', 'Rene Goscinny', 10),
       (1025, 54321, 'De waanzinnige boomhut van 26 verdiepingen', 'Andy Griffiths', 'Terry Denton', 10),
       (1026, 43210, 'De waanzinnige boomhut van 13 verdiepingen', 'Andy Griffiths', 'Terry Denton', 10),
       (1027, 32109, 'Vader kerstmis en ik', 'Matt Haig', 'Chris Mould', 11),
       (1028, 210987, 'Mijn avonturen', 'Toon Tellegen', 'Marc Terstroet', 11),
       (1029, 10987, 'De kleine prins', 'Antoine de Saint Exupery', 'Antoine de Saint Exupery', 11),
       (1030, 132435, 'The kite and other stories', 'Somerset Maugham', 'S Maugham', 11),
       (1031, 243546, 'The wonderful story of Henry Sugar', 'Roald Dahl', 'Penguin Books', 12),
       (1032, 354657, 'Animal Farm', 'George Orwell', 'Longman', 12),
       (1033, 465768, 'The restaurant at the end of the universe', 'Douglas Adams', 'Pan Publishers', 12),
       (1034, 576879, 'The catcher in the rye', 'JD Salinger', 'Penguin Books', 12);


--Reservation ............................
INSERT INTO reservations(id, reservation_date, sidenote)
VALUES(1000, '2023-12-12', 'Book reserved'),
      (1001, '2023-12-12', 'Book will be picked up by a friend'),
      (1002, '2023-12-12', 'Can come after the second lesson.'),
      (1003, '2023-12-12', 'Is there a dyslexia friendly version?'),
      (1004, '2023-12-12', 'I do not know if I will like this book.'),
      (1005, '2023-12-12', 'I am reading this book for the second time'),
      (1006, '2023-12-12', 'No message.'),
      (1007, '2023-12-12', 'Can you recommend a similar book?'),
      (1008, '2023-12-12', 'Book reserved'),
      (1009, '2023-12-12', 'Book will be picked up by a friend');


--Add account to reservation
UPDATE reservations SET account_id = 1000 WHERE id = 1000;
UPDATE reservations SET account_id = 1001 WHERE id = 1001;
UPDATE reservations SET account_id = 1002 WHERE id = 1002;
UPDATE reservations SET account_id = 1003 WHERE id = 1003;
UPDATE reservations SET account_id = 1004 WHERE id = 1004;
UPDATE reservations SET account_id = 1005 WHERE id = 1005;
UPDATE reservations SET account_id = 1006 WHERE id = 1006;
UPDATE reservations SET account_id = 1007 WHERE id = 1007;
UPDATE reservations SET account_id = 1008 WHERE id = 1008;
UPDATE reservations SET account_id = 1009 WHERE id = 1009;

--Add book to reservation
UPDATE reservations SET book_id = 1000 WHERE id = 1009;
UPDATE reservations SET book_id = 1001 WHERE id = 1008;
UPDATE reservations SET book_id = 1002 WHERE id = 1007;
UPDATE reservations SET book_id = 1003 WHERE id = 1006;
UPDATE reservations SET book_id = 1004 WHERE id = 1005;
UPDATE reservations SET book_id = 1005 WHERE id = 1004;
UPDATE reservations SET book_id = 1006 WHERE id = 1003;
UPDATE reservations SET book_id = 1007 WHERE id = 1002;
UPDATE reservations SET book_id = 1008 WHERE id = 1001;
UPDATE reservations SET book_id = 1009 WHERE id = 1000;

--BookCopy......................................
INSERT INTO copies (id, barcode, number_of_pages, total_word_count, format, in_written_form, audio_book, dyslexia_friendly, year_published)
VALUES (1000, 102345, 20, 200, 'hardcover', true, false, false,'1991-01-01'),
       (1001, 102346, 20, 100, 'hardcover', true, false, false,'1995-02-02'),
       (1002, 103456, 30, 500, 'paperback', true, false, false,'1998-03-03'),
       (1003, 30567, 30, 400, 'hardcover', true, false, false,'2020-01-01'),
       (1004, 10367, 25, 300, 'hardcover', true, false, true,'2010-01-01'),
       (1005, 101346, 25, 150, 'audio', false, true, true,'2018-05-05'),
       (1006, 105678, 35, 550, 'hardcover', true, false, true, '2015-01-01'),
       (1007, 60789, 55, 1000, 'hardcover', true, false, false, '2017-10-10'),
       (1008, 30790, 100, 30000, 'hardcover', true, false, true, '2021-12-12'),
       (1009, 20987, 30, 700, 'hardcover', true, true, true,'2022-01-01'),
       (1010, 80765, 100, 20000, 'paperback', true, true, false,'2000-01-01'),
       (1011, 10928, 75, 20000, 'paperback', true, true, true, '2001-01-01'),
       (1012, 20341, 140, 15000, 'hardcover', true, false, true, '2005-01-01'),
       (1013, 30123, 35, 300, 'paperback', true, true, true, '2006-01-01'),
       (1014, 10432, 158, 40000, 'paperback', true, false, false, '2018-01-01'),
       (1015, 30276, 176, 50000, 'paperback', true, true, false, '2015-02-02'),
       (1016, 70658, 156, 47500, 'hardcover', true, false, false, '2017-03-03'),
       (1017, 80768, 234, 60000, 'hardcover', true, false, false, '2019-03-03'),
       (1018, 40738, 244, 55000, 'hardcover', true, false, false, '2015-01-01'),
       (1019, 60758, 55, 5000, 'hardcover', true, true, true, '2000-01-01'),
       (1020, 10987, 100, 30000, 'paperback', true, false, false, '2003-01-01'),
       (1021, 20987, 252, 60000, 'hardcover', true, false, false, '2020-05-05'),
       (1022, 30011, 210, 50000, 'hardcover', true, false, true, '2008-01-01'),
       (1023, 30023, 157, 37000, 'hardcover', true, true, false, '2005-09-09'),
       (1024, 20009, 185, 43000, 'hardcover', true, false, false, '2004-04-04'),
       (1025, 10099, 245, 58000, 'hardcover', true, false, true, '2022-01-01'),
       (1026, 10010, 350, 70000, 'hardcover', true, false, true, '2023-01-01'),
       (1027, 20090, 260, 55000, 'hardcover', true, true, false, '2017-01-01'),
       (1028, 12002, 100, 25000, 'paperback', true, false, false, '1998-01-01'),
       (1029, 10098, 90, 19000, 'paperback', true, true, true, '1999-01-01'),
       (1030, 10098, 130, 22000, 'hardcover', true, true, false, '1984-01-01'),
       (1031, 20009, 235, 40000, 'paperback', true, true, false, '1982-01-01'),
       (1032, 29001, 88, 20000, 'paperback', true, true, false, '1986-01-01'),
       (1033, 20090, 180, 40000, 'paperback', true, false, false, '1980-01-01'),
       (1034, 30010, 156, 30000, 'paperback', true, false, false, '1983-01-01');

--Add book to book-copy
UPDATE copies SET book_id = 1000 WHERE id = 1000;
UPDATE copies SET book_id = 1001 WHERE id = 1001;
UPDATE copies SET book_id = 1002 WHERE id = 1002;
UPDATE copies SET book_id = 1003 WHERE id = 1003;
UPDATE copies SET book_id = 1004 WHERE id = 1004;
UPDATE copies SET book_id = 1005 WHERE id = 1005;
UPDATE copies SET book_id = 1006 WHERE id = 1006;
UPDATE copies SET book_id = 1007 WHERE id = 1007;
UPDATE copies SET book_id = 1008 WHERE id = 1008;
UPDATE copies SET book_id = 1009 WHERE id = 1009;
UPDATE copies SET book_id = 1010 WHERE id = 1010;
UPDATE copies SET book_id = 1011 WHERE id = 1011;
UPDATE copies SET book_id = 1012 WHERE id = 1012;
UPDATE copies SET book_id = 1013 WHERE id = 1013;
UPDATE copies SET book_id = 1014 WHERE id = 1014;
UPDATE copies SET book_id = 1015 WHERE id = 1015;
UPDATE copies SET book_id = 1016 WHERE id = 1016;
UPDATE copies SET book_id = 1017 WHERE id = 1017;
UPDATE copies SET book_id = 1018 WHERE id = 1018;
UPDATE copies SET book_id = 1019 WHERE id = 1019;
UPDATE copies SET book_id = 1020 WHERE id = 1020;
UPDATE copies SET book_id = 1021 WHERE id = 1021;
UPDATE copies SET book_id = 1022 WHERE id = 1022;
UPDATE copies SET book_id = 1023 WHERE id = 1023;
UPDATE copies SET book_id = 1024 WHERE id = 1024;
UPDATE copies SET book_id = 1025 WHERE id = 1025;
UPDATE copies SET book_id = 1026 WHERE id = 1026;
UPDATE copies SET book_id = 1027 WHERE id = 1027;
UPDATE copies SET book_id = 1028 WHERE id = 1028;
UPDATE copies SET book_id = 1029 WHERE id = 1029;
UPDATE copies SET book_id = 1030 WHERE id = 1030;
UPDATE copies SET book_id = 1031 WHERE id = 1031;
UPDATE copies SET book_id = 1032 WHERE id = 1032;
UPDATE copies SET book_id = 1033 WHERE id = 1033;
UPDATE copies SET book_id = 1034 WHERE id = 1034;

--Borrowal ............................
INSERT INTO borrowals (id, date_of_borrowal, due_date, number_of_books_borrowed)
VALUES(1000, '2024-03-03', '2024-04-04', 1),
      (1001, '2024-04-04', '2024-05-05', 1),
      (1002, '2024-05-05', '2024-06-06', 1),
      (1003, '2024-06-06', '2024-07-07', 1),
      (1004, '2024-07-07', '2024-08-08', 1),
      (1005, '2024-08-08', '2024-09-09', 1),
      (1006, '2024-09-09', '2024-10-10', 1),
      (1007, '2024-10-10', '2024-11-11', 1),
      (1008, '2024-11-11', '2024-12-12', 1),
      (1009, '2024-12-12', '2024-12-12', 1),
      (1010, '2024-12-12', '2024-12-12', 1),
      (1011, '2024-12-12', '2024-12-12', 1);

--Add book-copy to borrowal
UPDATE borrowals SET book_copy_id = 1000 WHERE id = 1000;
UPDATE borrowals SET book_copy_id = 1016 WHERE id = 1001;
UPDATE borrowals SET book_copy_id = 1002 WHERE id = 1002;
UPDATE borrowals SET book_copy_id = 1021 WHERE id = 1003;
UPDATE borrowals SET book_copy_id = 1018 WHERE id = 1004;
UPDATE borrowals SET book_copy_id = 1013 WHERE id = 1005;
UPDATE borrowals SET book_copy_id = 1030 WHERE id = 1006;
UPDATE borrowals SET book_copy_id = 1011 WHERE id = 1007;
UPDATE borrowals SET book_copy_id = 1003 WHERE id = 1008;
UPDATE borrowals SET book_copy_id = 1031 WHERE id = 1009;
UPDATE borrowals SET book_copy_id = 1028 WHERE id = 1010;
UPDATE borrowals SET book_copy_id = 1015 WHERE id = 1011;

--Add reservation to borrowal
UPDATE borrowals SET reservation_id = 1000 WHERE id = 1000;
UPDATE borrowals SET reservation_id = 1001 WHERE id = 1001;
UPDATE borrowals SET reservation_id = 1009 WHERE id = 1002;
UPDATE borrowals SET reservation_id = 1007 WHERE id = 1003;
UPDATE borrowals SET reservation_id = 1003 WHERE id = 1004;
UPDATE borrowals SET reservation_id = 1005 WHERE id = 1005;

--Add account to borrowal
UPDATE borrowals SET account_id = 1000 WHERE id = 1000;
UPDATE borrowals SET account_id = 1001 WHERE id = 1001;
UPDATE borrowals SET account_id = 1003 WHERE id = 1002;
UPDATE borrowals SET account_id = 1005 WHERE id = 1003;
UPDATE borrowals SET account_id = 1007 WHERE id = 1004;
UPDATE borrowals SET account_id = 1009 WHERE id = 1005;


---File upload.......................
INSERT INTO file_uploads (id, file_name, content_type, url)
VALUES (1000, 'onderzoekers.jpeg', 'image/jpeg','http://localhost:8080/download/onderzoekers.jpeg' ),
       (1001, 'autos.jpeg', 'image/jpeg','http://localhost:8080/download/autos.jpeg' ),
       (1002, 'barbapapa.jpeg', 'image/jpeg','http://localhost:8080/download/barbapapa.jpeg'),
       (1003, 'willem.jpeg','image/jpeg','http://localhost:8080/download/willem.jpeg' ),
       (1004, 'sjaantje.jpeg', 'image/jpeg','http://localhost:8080/download/sjaantje.jpeg'),
       (1005, 'muis.jpeg', 'image/jpeg', 'http://localhost:8080/download/muis.jpeg' ),
       (1006, 'mopper.jpeg', 'image/jpeg', 'http://localhost:8080/download/mopper.jpeg' ),
       (1007, 'molli.jpeg', 'image/jpeg', 'http://localhost:8080/download/molli.jpeg'),
       (1008, 'voetbal.jpeg', 'image/jpeg', 'http://localhost:8080/download/voetbal.jpeg'),
       (1009, 'paw.jpeg', 'image/jpeg', 'http://localhost:8080/download/paw.jpeg'),
       (1010, 'toon.jpeg', 'image/jpeg', 'http://localhost:8080/download/toon.jpeg'),
       (1011, 'sprookjes.jpeg', 'image/jpeg', 'http://localhost:8080/download/sprookjes.jpeg'),
       (1012, 'vos.jpeg', 'image/jpeg', 'http://localhost:8080/download/vos.jpeg'),
       (1013, 'boom.jpeg', 'image/jpeg', 'http://localhost:8080/download/boom.jpeg'),
       (1014, 'perzik.jpeg','image/jpeg', 'http://localhost:8080/download/perzik.jpeg'),
       (1015, 'matilda.jpeg', 'image/jpeg', 'http://localhost:8080/download/matilda.jpeg'),
       (1016, 'griezel.jpeg', 'image/jpeg', 'http://localhost:8080/download/griezel.jpeg'),
       (1017, 'ufo.jpeg', 'image/jpeg', 'http://localhost:8080/download/ufo.jpeg'),
       (1018, 'generaal.jpeg', 'image/jpeg', 'http://localhost:8080/download/generaal.jpeg'),
       (1019, 'hiroshima.jpeg', 'image/jpeg', 'http://localhost:8080/download/hiroshima.jpeg'),
       (1020, 'man.jpeg', 'image/jpeg', 'http://localhost:8080/download/man.jpeg'),
       (1021, 'gozert.jpeg', 'image/jpeg', 'http://localhost:8080/download/gozert.jpeg'),
       (1022, 'jurk.jpeg', 'image/jpeg', 'http://localhost:8080/download/jurk.jpeg'),
       (1023, 'nicolaas.jpeg', 'image/jpeg', 'http://localhost:8080/download/nicolaas.jpeg'),
       (1024, 'nicolaas.jpeg', 'image/jpeg', 'http://localhost:8080/download/nicolaas.jpeg'),
       (1025, 'boomhut.jpeg', 'image/jpeg', 'http://localhost:8080/download/boomhut.jpeg');

--Add file upload to book
UPDATE books SET book_photo_id = 1000 WHERE id = 1000;
UPDATE books SET book_photo_id = 1001 WHERE id = 1001;
UPDATE books SET book_photo_id = 1002 WHERE id = 1002;
UPDATE books SET book_photo_id = 1003 WHERE id = 1003;
UPDATE books SET book_photo_id = 1004 WHERE id = 1004;
UPDATE books SET book_photo_id = 1005 WHERE id = 1005;
UPDATE books SET book_photo_id = 1006 WHERE id = 1006;
UPDATE books SET book_photo_id = 1007 WHERE id = 1007;
UPDATE books SET book_photo_id = 1008 WHERE id = 1008;
UPDATE books SET book_photo_id = 1009 WHERE id = 1009;
UPDATE books SET book_photo_id = 1010 WHERE id = 1010;
UPDATE books SET book_photo_id = 1011 WHERE id = 1011;
UPDATE books SET book_photo_id = 1012 WHERE id = 1012;
UPDATE books SET book_photo_id = 1013 WHERE id = 1013;
UPDATE books SET book_photo_id = 1014 WHERE id = 1014;
UPDATE books SET book_photo_id = 1015 WHERE id = 1015;
UPDATE books SET book_photo_id = 1016 WHERE id = 1016;
UPDATE books SET book_photo_id = 1017 WHERE id = 1017;
UPDATE books SET book_photo_id = 1018 WHERE id = 1018;
UPDATE books SET book_photo_id = 1019 WHERE id = 1019;
UPDATE books SET book_photo_id = 1020 WHERE id = 1020;

--TODO: create enums and see how they get integrated in the system








--4. Review....................................
INSERT INTO reviews (id, name, review)
VALUES(1000, 'Marijana', 'De waanzinnige boomhut is the best book ever'),
      (1001, 'Koen', 'Roald Dahls books are a bit boring but they are ok.'),
      (1002, 'Matthijs', 'Funny.'),
      (1003, 'Lucia', 'I would never read this stupid book ever again.');









