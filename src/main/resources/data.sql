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

--BookCopy......................................

INSERT INTO book_copies (id, barcode, number_of_pages, total_word_count, format, in_written_form, audio_book, dyslexia_friendly, year_published)
VALUES (1000, 2345, 200, 2000, 'paperback', true, true, false,'1991-02-02'),
       (1001, 2346, 100, 1000, 'hardcover', true, true, false,'1992-02-02'),
       (1002, 3456, 200, 2500, 'hardcover', true, false, true,'1993-03-03'),
       (1003, 3567, 30, 300, 'hardcover', true, false, true,'1994-04-04'),
       (1004, 1367, 40, 400, 'paperback', true, false, false,'2000-01-01'),
       (1005, 3467, 50, 500, 'hardcover', false, true, true,'2010-05-05'),
       (1006, 5678, 250, 3000, 'hardcover', true, false, true, '2015-07-07'),
       (1007, 6789, 250, 4000, 'paperback', true, false, false, '2020-10-10'),
       (1008, 3790, 25, 300, 'hardcover', true, true, true, '2021-12-12'),
       (1009, 0987, 100, 1010, 'paperback', true, true, true,'2023-01-01'),
       (1010, 8765, 101, 1010, 'hardcover', true, true, false,'2023-02-02');

--Book......................................

INSERT INTO books (id, isbn, book_title, name_author, suitable_age, number_of_copies)
VALUES (1000, 98765, 'Kleine onderzoekers voertuigen', 'Ruth Martin', 4, 5),
       (1001, 8765, 'Graafmachines en kiepautos', 'Angela Royston', 4, 2),
       (1002, 763987, 'Barbapapa', 'Annette Tilson', 5, 1),
       (1003, 62983, 'Woeste Willem', 'Ingrid Schubert', 5, 3),
       (1004, 38928, 'Langzaam, zosnel als zij konden', 'Toon Tellegen', 6, 1),
       (1005, 729387, 'Sprookjes voor alle kinderen', 'Lea Smulders', 7, 2),
       (1006, 92876, 'Die dag in Hiroshima', 'Toshi Maruki', 9, 1),
       (1007, 6283987, 'Boom is boos', 'Marjolein Krijger', 6, 4),
       (1008, 28764, 'De man die bomen plantte', 'Jean Giono', 9, 1),
       (1009, 720936, 'Ufo alarm', 'Jozua Douglas', 10, 2),
       (1010, 398276, 'Gozert', 'Pieter Koolwijk', 11, 1),
       (1011, 38927, 'De reuzenperzik', 'Roald Dahl', 11, 2),
       (1012, 38765, 'Vos en haas', 'Sylvia Vanden Heede', 7, 1);