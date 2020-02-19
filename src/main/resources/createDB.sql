create table users (
    id int not null auto_increment,
    username varchar(255),
    password varchar(255),
    primary key (id)
)

create table ratings (
    id int not null auto_increment,
    user_id int,
    rating int,
    primary key id,
    foreign key user_id references users.id
)