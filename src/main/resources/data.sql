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
INSERT INTO books (id, isbn, book_title, name_author, name_illustrator, suitable_age, current_topic, education_level, language, current_genre, reading_level)
VALUES (1000, 98765, 'Kleine onderzoekers voertuigen', 'Ruth Martin', 'Ruth Martin', 4, 'adventure', 'beginners', 'dutch', 'adventure', 'basic'),
       (1001, 8765, 'Graafmachines en kiepautos', 'Angela Royston', 'David Barrow', 4, 'adventure', 'beginners','dutch', 'adventure', 'basic' ),
       (1002, 763987, 'Barbapapa', 'Annette Tilson', 'Talus Taylor', 5, 'fantasy', 'beginners','dutch', 'adventure', 'basic'),
       (1003, 62983, 'Woeste Willem', 'Ingrid Schubert', 'Dieter Schubert', 5, 'humor', 'beginners', 'dutch', 'adventure', 'basic'),
       (1004, 38928, 'Langzaam, zosnel als zij konden', 'Toon Tellegen', 'Mance Post', 7, 'love', 'beginners', 'dutch', 'adventure', 'basic'),
       (1005, 729387, 'Sprookjes voor alle kinderen', 'Lea Smulders', 'Gyo Fujikawa', 7, 'fantasy', 'beginners', 'dutch', 'adventure', 'basic'),
       (1006, 92876, 'Die dag in Hiroshima', 'Toshi Maruki', 'Toshi Maruki', 9, 'war', 'beginners', 'dutch', 'adventure', 'basic'),
       (1007, 6283987, 'Boom is boos', 'Marjolein Krijger', 'Marjolein Krijger', 6, 'humor', 'beginners', 'dutch', 'adventure', 'basic'),
       (1008, 28764, 'De man die bomen plantte', 'Jean Giono', 'Michael McCurdy', 9, 'love', 'beginners', 'dutch', 'adventure', 'basic'),
       (1009, 720936, 'Ufo alarm', 'Jozua Douglas', 'Elly Hees', 10, 'humor', 'beginners', 'dutch', 'adventure', 'basic'),
       (1010, 398276, 'Gozert', 'Pieter Koolwijk', 'Linde Faas', 11, 'family', 'beginners', 'dutch', 'adventure', 'basic');


--Reservation ............................
INSERT INTO reservations(id, reservation_date, book_title, sidenote)
VALUES(1000, '2023-12-12', 'Book', 'Book reserved'),
      (1001, '2023-12-12', 'Book2', 'Book will be picked up by a friend'),
      (1002, '2023-12-12', 'Book3', 'Can come after the second lesson.'),
      (1003, '2023-12-12', 'Book3', 'Is there a dyslexia friendly version?'),
      (1004, '2023-12-12', 'Book4', 'I do not know if I will like this book.'),
      (1005, '2023-12-12', 'Book5', 'I am reading this book for the second time'),
      (1006, '2023-12-12', 'Book6', 'No message.'),
      (1007, '2023-12-12', 'Book7', 'Can you recommend a similar book?'),
      (1008, '2023-12-12', 'Book8', 'Book reserved'),
      (1009, '2023-12-12', 'Book9', 'Book will be picked up by a friend');


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
VALUES (1000, 2345, 200, 2000, 'paperback', true, false, false,'1991-02-02'),
       (1001, 2346, 100, 1000, 'hardcover', true, false, false,'1992-02-02'),
       (1002, 3456, 200, 2500, 'hardcover', true, false, true,'1993-03-03'),
       (1003, 3567, 30, 300, 'hardcover', true, false, true,'1994-04-04'),
       (1004, 1367, 40, 400, 'paperback', true, false, false,'2000-01-01'),
       (1005, 3467, 50, 500, 'audio', false, true, true,'2010-05-05'),
       (1006, 5678, 250, 3000, 'hardcover', true, false, true, '2015-07-07'),
       (1007, 6789, 250, 4000, 'paperback', true, false, false, '2020-10-10'),
       (1008, 3790, 25, 300, 'audio', false, true, true, '2021-12-12'),
       (1009, 0987, 100, 1010, 'paperback', true, false, true,'2023-01-01'),
       (1010, 8765, 101, 1010, 'hardcover', true, false, false,'2023-02-02');

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

--Borrowal ............................
INSERT INTO borrowals (id, date_of_borrowal, due_date, book_title, number_of_books_borrowed)
VALUES(1000, '2024-03-03', '2024-04-04', 'Book1', 1),
      (1001, '2024-04-04', '2024-05-05', 'Book2', 3),
      (1002, '2024-05-05', '2024-06-06', 'Book3', 2),
      (1003, '2024-06-06', '2024-07-07', 'Book4', 1),
      (1004, '2024-07-07', '2024-08-08', 'Book5', 2),
      (1005, '2024-08-08', '2024-09-09', 'Book6', 1),
      (1006, '2024-09-09', '2024-10-10', 'Book7', 1),
      (1007, '2024-10-10', '2024-11-11', 'Book8', 3),
      (1008, '2024-11-11', '2024-12-12', 'Book9', 1);

--Add book-copy to borrowal
UPDATE borrowals SET book_copy_id = 1000 WHERE id = 1008;
UPDATE borrowals SET book_copy_id = 1001 WHERE id = 1007;
UPDATE borrowals SET book_copy_id = 1002 WHERE id = 1007;

--TODO: 1, 2, 3, 4 + choose 20 books and set up the data in a way that it all makes sense!
--TODO: create enums and see how they get integrated in the system
--TODO: what to do with the file upload?

--1. Add reservation to borrowal

--2. Add account to borrowal

--3. File upload

--4. Review....................................
INSERT INTO reviews (id, name, review)
VALUES(1000, 'Toon Tellegen', 'This is the best book ever'),
      (1001, 'Roald Dahl', 'A bit boring but ok'),
      (1002, 'Dick Bruna', 'Funny.');









