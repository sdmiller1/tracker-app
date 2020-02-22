-- Drop tables in the correct order
drop table if exists ratings;
drop table if exists users;


-- create the users table

create table users (
    id int not null auto_increment,
    username varchar(255),
    password varchar(255),
    primary key (id)
);


-- create the ratings table

create table ratings (
    id int not null auto_increment,
    user_id int,
    movie varchar(255),
    rating int,
    primary key (id),
    foreign key (user_id) references users(id) on delete cascade
);

-- insert sample data

insert into users values (default, 'bob', 'password');
insert into users values (default, 'doug', 'password');
insert into users values (default, 'chris', 'password');
insert into users values (default, 'scott', 'password');

insert into ratings values (default, 1, 'The Martian', 5);
insert into ratings values (default, 2, 'The Martian', 5);
insert into ratings values (default, 3, 'The Martian', 5);
insert into ratings values (default, 4, 'The Martian', 5);
insert into ratings values (default, 1, 'Harry Potter', 4);