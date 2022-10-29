DELETE
FROM user_roles;
DELETE
FROM meals;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO meals (user_id, description, datetime, calories)
VALUES  (100000 ,'popcorn', 	date '2020-10-25' + time '8:00', 200),
        (100000 ,'fish',    	date '2020-10-25' + time '11:00', 300),
        (100000 ,'fish',    	date '2020-10-25' + time '14:00', 400),
        (100000 ,'apple',    	date '2020-10-26' + time '17:00', 500),
        (100000 ,'milk',    	date '2020-10-26' + time '19:00', 150),
        (100000 ,'potato',    	date '2020-10-26' + time '22:00', 410),
        (100000 ,'tomato',    	date '2020-10-26' + time '23:30', 210),
        (100000 ,'night egg', 	date '2020-10-26' + time '0:00', 100),
        (100001 ,'milk', 	    date '2020-10-25' + time '14:00', 210),
        (100001 ,'sausage', 	date '2020-10-26' + time '21:00', 400);

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);
