INSERT INTO Teacher (email, faculty, cathedra, rank) VALUES
    ('danvega@gmail.com', 'Fac', 'Cat', 'Lieutenant'),
    ('jenvega@gmail.com', 'Fac', 'Cat', 'Lieutenant'),
    ('androshuk@gmail.com', 'Faculty of Computer Scince', 'Multimedia', 'Master');

INSERT INTO STUDENT (faculty, speciality, student_year) VALUES
    ('Fac', 'Se', 2),
    ('Fac', 'Se', 3);

INSERT INTO SUBJECT (lesson_name, day_of_week, lesson_time, lesson_group, teacher_id) VALUES
    ('Spring Boot', 1, '23:00', 0,
        SELECT t.TEACHER_ID FROM TEACHER t
            WHERE t.EMAIL = 'androshuk@gmail.com'),
    ('Spring Boot', 2, '23:05', 1,
        SELECT t.TEACHER_ID FROM TEACHER t
            WHERE t.EMAIL = 'androshuk@gmail.com');

INSERT INTO SUBJECT_WEEKS (SUBJECT_SUBJECT_ID, weeks) VALUES
    (SELECT s.SUBJECT_ID FROM SUBJECT s
     where s.LESSON_NAME = 'Spring Boot' AND s.LESSON_GROUP = 0
    , 1),
    (SELECT s.SUBJECT_ID FROM SUBJECT s
     where s.LESSON_NAME = 'Spring Boot' AND s.LESSON_GROUP = 1
    , 2);

-- show tables;
