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

