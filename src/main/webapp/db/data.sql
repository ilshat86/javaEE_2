CREATE TABLE person
(
    id         INT NOT NULL AUTO_INCREMENT,
    name       VARCHAR(250),
    birth_date LONG(250)
);

CREATE TABLE user
(
    id         INT NOT NULL AUTO_INCREMENT,
    username    VARCHAR(250),
    password VARCHAR(250),
    role VARCHAR(250)

);

INSERT INTO user (username, password, role) VALUES ( 'Ivan', 'password1','ADMIN' );
INSERT INTO user (username, password, role) VALUES ( 'petr', 'password2','USER' );
