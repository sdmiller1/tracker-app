
-- create the users table
drop table if exists users;

create table users (
    id int not null auto_increment,
    username varchar(255),
    password varchar(255),
    primary key (id)
);


-- create the ratings table
drop table if exists ratings;

create table ratings (
    id int not null auto_increment,
    user_id int,
    movie varchar(255),
    rating int,
    primary key id,
    foreign key (user_id) references users(id)
);
