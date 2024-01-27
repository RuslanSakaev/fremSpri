CREATE TABLE IF NOT EXISTS userTable (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName varchar(50) NOT NULL,
    age INT NOT NULL,
    email varchar(50) NOT NULL,
    password varchar(50) NOT NULL,
    isLogin BOOL DEFAULT 0,
    isAdmin BOOL DEFAULT 0
);

INSERT INTO userTable VALUES (DEFAULT, 'Roman Wayz', 25, 'sup.makulin@mail.ru', '12s', default , 1);
INSERT INTO userTable VALUES (DEFAULT, 'Test admin', 25, 'admin@mail.ru', '12s', default , 1);