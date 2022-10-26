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
VALUES  (100000 ,'popcorn', 	date '2021-09-28' + time '11:00', 200),
        (100000 ,'fish',    	date '2021-09-28' + time '15:00', 250),
        (100000 ,'fish',    	date '2021-09-28' + time '03:00', 350),
        (100000 ,'apple',    	date '2021-09-29' + time '08:00', 450),
        (100000 ,'milk',    	date '2021-09-29' + time '22:00', 150),
        (100000 ,'potato',    	date '2021-09-30' + time '20:00', 550),
        (100000 ,'tomato',    	date '2021-09-30' + time '16:00', 250),
        (100001 ,'milk', 	    date '2021-09-28' + time '18:00', 100),
        (100001 ,'sausage', 	date '2021-09-28' + time '22:00', 250);

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);
