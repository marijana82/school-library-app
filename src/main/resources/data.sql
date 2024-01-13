--User......................................
INSERT INTO users (username, password)
VALUES ('Marijana', '$2a$10$qE0oZtzS1fqthOUYebCPxucengug3Oh9Okq2eKONl8k6eIkX3AHc2' );

--INSERT INTO authorities (username, authority)
--VALUES ('Marijana', 'ADMIN'),
--       ('Maj', 'LIBRARIAN'),
--       ('Vid', 'STUDENT');


--Account.....................................
INSERT INTO accounts (id, first_name_student, last_name_student, dob, student_class, name_of_teacher)
VALUES (1000, 'David Eric', 'Grohl', '2018-01-01', 'obb', 'Gaby'),
       (1001, 'Oliver Taylor', 'Hawkins', '2017-02-02', 'obb', 'Gaby'),
       (1002, 'Nathnael', 'Mendel', '2016-03-04', 'mba', 'Chrystel'),
       (1003, 'Chris', 'Shifflet', '2016-04-04', 'mba', 'Chrystel'),
       (1004, 'Pat', 'Smear', '2015-05-05', 'mbc', 'Tina'),
       (1005, 'Michael', 'Balzary', '2015-08-08', 'mbe', 'Dennis'),
       (1006, 'Anthony', 'Kiedis', '2014-01-01', 'bba', 'Mirjam'),
       (1007, 'Chad', 'Smith', '2014-02-02', 'bba', 'Mirjam'),
       (1008, 'John', 'Frusciante', '2013-07-07', 'bbc', 'Ricardo'),
       (1009, 'Krist', 'Novoselic', '2012-09-09', 'bbd', 'Max');

--Reservation ............................
INSERT INTO reservations(id, reservation_date, book_title, sidenote)
VALUES(5000, '2023-12-12', 'Book', 'Book reserved'),
      (5001, '2023-12-12', 'Book 2', 'Book will be picked up by a friend');

--AccountReservations......................
INSERT INTO accounts_reservations(accounts_id, reservations_id)
VALUES(1001, 5000),
      (1002, 5001);


--Borrowal ............................
INSERT INTO borrowals (id, date_of_borrowal, due_date, book_title, number_of_books_borrowed)
VALUES(6000, '2024-02-02', '2024-03-03', 'Book', 1);

/*--BorrowalsReservations......................
INSERT INTO borrowal_reservation(borrowal_id, reservation_id)
VALUES(6050, 5050);
*/

--BookCopy......................................

INSERT INTO copies (id, barcode, number_of_pages, total_word_count, format, in_written_form, audio_book, dyslexia_friendly, year_published)
VALUES (2000, 2345, 200, 2000, 'paperback', true, false, false,'1991-02-02'),
       (2001, 2346, 100, 1000, 'hardcover', true, false, false,'1992-02-02'),
       (2002, 3456, 200, 2500, 'hardcover', true, false, true,'1993-03-03'),
       (2003, 3567, 30, 300, 'hardcover', true, false, true,'1994-04-04'),
       (2004, 1367, 40, 400, 'paperback', true, false, false,'2000-01-01'),
       (2005, 3467, 50, 500, 'audio', false, true, true,'2010-05-05'),
       (2006, 5678, 250, 3000, 'hardcover', true, false, true, '2015-07-07'),
       (2007, 6789, 250, 4000, 'paperback', true, false, false, '2020-10-10'),
       (2008, 3790, 25, 300, 'audio', false, true, true, '2021-12-12'),
       (2009, 0987, 100, 1010, 'paperback', true, false, true,'2023-01-01'),
       (2010, 8765, 101, 1010, 'hardcover', true, false, false,'2023-02-02');

--Book......................................

INSERT INTO books (id, isbn, book_title, name_author, name_illustrator, suitable_age, current_topic, education_level, language, current_genre, reading_level)
VALUES (3000, 98765, 'Kleine onderzoekers voertuigen', 'Ruth Martin', 'Ruth Martin', 4, 'adventure', 'beginners', 'dutch', 'adventure', 'basic'),
       (3001, 8765, 'Graafmachines en kiepautos', 'Angela Royston', 'David Barrow', 4, 'adventure', 'beginners','dutch', 'adventure', 'basic' ),
       (3002, 763987, 'Barbapapa', 'Annette Tilson', 'Talus Taylor', 5, 'fantasy', 'beginners','dutch', 'adventure', 'basic'),
       (3003, 62983, 'Woeste Willem', 'Ingrid Schubert', 'Dieter Schubert', 5, 'humor', 'beginners', 'dutch', 'adventure', 'basic'),
       (3004, 38928, 'Langzaam, zosnel als zij konden', 'Toon Tellegen', 'Mance Post', 7, 'love', 'beginners', 'dutch', 'adventure', 'basic'),
       (3005, 729387, 'Sprookjes voor alle kinderen', 'Lea Smulders', 'Gyo Fujikawa', 7, 'fantasy', 'beginners', 'dutch', 'adventure', 'basic'),
       (3006, 92876, 'Die dag in Hiroshima', 'Toshi Maruki', 'Toshi Maruki', 9, 'war', 'beginners', 'dutch', 'adventure', 'basic'),
       (3007, 6283987, 'Boom is boos', 'Marjolein Krijger', 'Marjolein Krijger', 6, 'humor', 'beginners', 'dutch', 'adventure', 'basic'),
       (3008, 28764, 'De man die bomen plantte', 'Jean Giono', 'Michael McCurdy', 9, 'love', 'beginners', 'dutch', 'adventure', 'basic'),
       (3009, 720936, 'Ufo alarm', 'Jozua Douglas', 'Elly Hees', 10, 'humor', 'beginners', 'dutch', 'adventure', 'basic'),
       (3010, 398276, 'Gozert', 'Pieter Koolwijk', 'Linde Faas', 11, 'family', 'beginners', 'dutch', 'adventure', 'basic');

--Author......................................
INSERT INTO reviews (id, name, review)
VALUES(1000, 'Toon Tellegen', 'This is the best book ever'),
      (1001, 'Roald Dahl', 'A bit boring but ok'),
      (1002, 'Dick Bruna', 'Funny.');


INSERT INTO review_book (review_id, book_id)
VALUES (1000, 3000),
       (1001, 3001),
       (1002, 3002);

