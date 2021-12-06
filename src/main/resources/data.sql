INSERT INTO USER (email, password, first_name, last_name, user_role) VALUES
('vkd@ukma.edu.ua', '$2a$10$7SreZc.eTO3bKTcVPgS9c.Q662JnCDqn2ybx/Vdw53e7OCOEsQrku', 'Ivan', 'Maister', 'STUDENT');
INSERT INTO STUDENT (student_id,user_id) VALUES
(1,1);
INSERT INTO USER (email, password, first_name, last_name, user_role) VALUES
('vk@ukma.edu.ua', '$2a$10$7SreZc.eTO3bKTcVPgS9c.Q662JnCDqn2ybx/Vdw53e7OCOEsQrku', 'Andriy', 'Glubovets', 'TEACHER');
INSERT INTO TEACHER (teacher_id,user_id) VALUES
(1,2);
INSERT INTO USER (email, password, first_name, last_name, user_role) VALUES
('bb@ukma.edu.ua', '$2a$10$7SreZc.eTO3bKTcVPgS9c.Q662JnCDqn2ybx/Vdw53e7OCOEsQrku', 'Volodymyr', 'Bublik', 'TEACHER');
INSERT INTO TEACHER (teacher_id,user_id) VALUES
(2,3);
INSERT INTO SUBJECT (subject_name,day_of_week,subject_time, subject_group, teacher_id) VALUES
('Spring',0,'08:30:00', 2, 1);
INSERT INTO SUBJECT (subject_name,day_of_week,subject_time, subject_group, teacher_id) VALUES
('Бази даних',2,'10:00:00',5, 1);
INSERT INTO SUBJECT (subject_name,day_of_week,subject_time, subject_group, teacher_id) VALUES
('МООП',1,'10:00:00',1, 2);
INSERT INTO SUBJECT (subject_name,day_of_week,subject_time, subject_group, teacher_id) VALUES
('МООП',2,'08:30:00',3, 2);