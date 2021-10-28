INSERT INTO Teacher (first_name, last_name, email, faculty, cathedra, rank) VALUES
('Vega D.', '', 'danvega@gmail.com', 'Fac', 'Cat', 'Lieutenant'),
('Vega J.', '', 'jenvega@gmail.com', 'Fac', 'Cat', 'Lieutenant'),
('Androshuk M.', '',  'androshuk@gmail.com', 'Faculty of Computer Scince', 'Multimedia', 'Master');

INSERT INTO SUBJECT_WEEKS (SUBJECT_SUBJECT_ID, weeks) VALUES
    (SELECT S.SUBJECT_ID FROM SUBJECT S WHERE S.SUBJECT_NAME = 'Системне програмування' AND S.SUBJECT_GROUP = 2, 11);

INSERT INTO Teacher (first_name, last_name, faculty, cathedra, rank) VALUES
('Vega D.', '', 'Fac', 'Cat', 'Lieutenant');
-- INSERT INTO Teacher (user_id, first_name, last_name, email, faculty, cathedra, rank) VALUES
--     (1, 'Vega D.', '', 'danvega@gmail.com', 'Fac', 'Cat', 'Lieutenant'),
--     (1, 'Vega J.', '', 'jenvega@gmail.com', 'Fac', 'Cat', 'Lieutenant'),
--     (1, 'Androshuk M.', '',  'androshuk@gmail.com', 'Faculty of Computer Scince', 'Multimedia', 'Master');

INSERT INTO STUDENT (first_name, last_name, email, faculty, speciality, student_year) VALUES
('f_name', 'l_name', 'semail1@ukma.edu.ua', 'Fac', 'Seee', 2),
('f_name', 'l_name', 'semail2@ukma.edu.ua', 'Fac', 'Se', 1);

-- INSERT INTO STUDENT (user_id, first_name, last_name, email, faculty, speciality, student_year) VALUES
-- (1, '', '', '', 'Fac', 'Seee', 2),
-- (1, '', '', '', 'Fac', 'Se', 1);

INSERT INTO SUBJECT (subject_name, day_of_week, subject_time, subject_group, user_id, subject_faculty,
                     subject_speciality, education_format) VALUES
('Spring Boot', 1, '23:00', 0,
    SELECT t.USER_ID FROM TEACHER t
 WHERE t.EMAIL = 'androshuk@gmail.com',
 '', '', ''),
('Spring Boot', 2, '23:05', 1,
    SELECT t.USER_ID FROM TEACHER t
 WHERE t.EMAIL = 'androshuk@gmail.com',
 '', '', '');

INSERT INTO SUBJECT_WEEKS (SUBJECT_SUBJECT_ID, weeks) VALUES
(SELECT s.SUBJECT_ID FROM SUBJECT s
        where s.SUBJECT_NAME = 'Spring Boot' AND s.SUBJECT_GROUP = 0
, 1),
(SELECT s.SUBJECT_ID FROM SUBJECT s
        where s.SUBJECT_NAME = 'Spring Boot' AND s.SUBJECT_GROUP = 1
, 2);

-- show tables;
