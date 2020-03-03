-- drop tables before re creating them
drop table if exists MoviesCollections;
drop table if exists Ratings;
drop table if exists Movies;
drop table if exists UsersCollections;
drop table if exists Collections;
drop table if exists Roles;
drop table if exists Users;


-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-03-03 16:37:36.853

-- tables
-- Table: Collections
CREATE TABLE Collections (
                             id int NOT NULL AUTO_INCREMENT,
                             collectionType varchar(255) NOT NULL,
                             CONSTRAINT Collections_pk PRIMARY KEY (id)
);

-- Table: Movies
CREATE TABLE Movies (
                        id int NOT NULL AUTO_INCREMENT,
                        api_id int NOT NULL,
                        title varchar(255) NOT NULL,
                        CONSTRAINT Movies_pk PRIMARY KEY (id)
);

-- Table: MoviesCollections
CREATE TABLE MoviesCollections (
                                   id int NOT NULL AUTO_INCREMENT,
                                   collection_id int NOT NULL,
                                   movie_id int NOT NULL,
                                   hasDvd bool NOT NULL DEFAULT true,
                                   hasBluRay bool NOT NULL DEFAULT false,
                                   has4k bool NOT NULL DEFAULT false,
                                   CONSTRAINT MoviesCollections_pk PRIMARY KEY (id)
);

-- Table: Ratings
CREATE TABLE Ratings (
                         id int NOT NULL AUTO_INCREMENT,
                         user_id int NOT NULL,
                         movie_id int NOT NULL,
                         dateWatched date NOT NULL,
                         rating int NOT NULL,
                         CONSTRAINT Ratings_pk PRIMARY KEY (id)
);

-- Table: Roles
CREATE TABLE Roles (
                       id int NOT NULL AUTO_INCREMENT,
                       roleName varchar(25) NOT NULL,
                       userName varchar(255) NOT NULL,
                       user_id int NOT NULL,
                       CONSTRAINT Roles_pk PRIMARY KEY (id)
);

-- Table: Users
CREATE TABLE Users (
                       id int NOT NULL AUTO_INCREMENT,
                       firstName varchar(255) NOT NULL,
                       lastName varchar(255) NOT NULL,
                       username varchar(255) NOT NULL,
                       password varchar(255) NOT NULL,
                       hasDvd bool NOT NULL,
                       hasBluRay bool NOT NULL,
                       has4k bool NOT NULL,
                       CONSTRAINT Users_pk PRIMARY KEY (id)
);

-- Table: UsersCollections
CREATE TABLE UsersCollections (
                                  user_id int NOT NULL,
                                  collection_id int NOT NULL,
                                  CONSTRAINT UsersCollections_pk PRIMARY KEY (user_id,collection_id)
);

-- foreign keys
-- Reference: MoviesCollections_Collections (table: MoviesCollections)
ALTER TABLE MoviesCollections ADD CONSTRAINT MoviesCollections_Collections FOREIGN KEY MoviesCollections_Collections (collection_id)
    REFERENCES Collections (id)
    ON DELETE CASCADE;

-- Reference: MoviesCollections_Movies (table: MoviesCollections)
ALTER TABLE MoviesCollections ADD CONSTRAINT MoviesCollections_Movies FOREIGN KEY MoviesCollections_Movies (movie_id)
    REFERENCES Movies (id)
    ON DELETE CASCADE;

-- Reference: Ratings_Movies (table: Ratings)
ALTER TABLE Ratings ADD CONSTRAINT Ratings_Movies FOREIGN KEY Ratings_Movies (movie_id)
    REFERENCES Movies (id);

-- Reference: Ratings_Users (table: Ratings)
ALTER TABLE Ratings ADD CONSTRAINT Ratings_Users FOREIGN KEY Ratings_Users (user_id)
    REFERENCES Users (id);

-- Reference: Roles_Users (table: Roles)
ALTER TABLE Roles ADD CONSTRAINT Roles_Users FOREIGN KEY Roles_Users (user_id)
    REFERENCES Users (id);

-- Reference: UsersCollections_Collections (table: UsersCollections)
ALTER TABLE UsersCollections ADD CONSTRAINT UsersCollections_Collections FOREIGN KEY UsersCollections_Collections (collection_id)
    REFERENCES Collections (id)
    ON DELETE CASCADE;

-- Reference: UsersCollections_Users (table: UsersCollections)
ALTER TABLE UsersCollections ADD CONSTRAINT UsersCollections_Users FOREIGN KEY UsersCollections_Users (user_id)
    REFERENCES Users (id)
    ON DELETE CASCADE;

-- End of file.




-- insert sample data

insert into Users values (default, 'Bob', 'Behnken', 'astrobob', 'password', true, true, false);
insert into Users values (default, 'Doug', 'Hurley', 'astrodoug', 'password', true, true, false);
insert into Users values (default, 'Chris', 'Hadfield', 'astrochris', 'password', true, true, false);
insert into Users values (default, 'Scott', 'Kelly', 'astroscott', 'password', true, true, false);

insert into Movies values (default, 1, 'The Martian');
insert into Movies values (default, 2, 'Harry Potter');
insert into Movies values (default, 3, 'Ad Astra');

insert into Ratings values (default, 1, 1, current_date(), 5);
insert into Ratings values (default, 2, 1, current_date(), 5);
insert into Ratings values (default, 3, 1, current_date(), 5);
insert into Ratings values (default, 4, 2, current_date(), 5);
insert into Ratings values (default, 1, 1, current_date(), 4);

insert into Collections values (default, 'personal');
insert into Collections values (default, 'personal');
insert into Collections values (default, 'personal');
insert into Collections values (default, 'personal');

insert into UsersCollections values (1, 1);
insert into UsersCollections values (2, 2);
insert into UsersCollections values (3, 3);
insert into UsersCollections values (4, 4);
insert into UsersCollections values (4, 1);

insert into MoviesCollections values (1, 1, true, true, false);
insert into MoviesCollections values (1, 2, true, false, false);
insert into MoviesCollections values (1, 3, true, true, true);
insert into MoviesCollections values (2, 1, true, true, true);

