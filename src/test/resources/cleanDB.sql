-- drop tables before re creating them
drop table if exists MoviesCollections;
drop table if exists Ratings;
drop table if exists Movies;
drop table if exists UsersCollections;
drop table if exists Collections;
drop table if exists Roles;
drop table if exists Users;


-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-04-03 21:55:01.645

-- tables
-- Table: Collections
CREATE TABLE Collections (
                             id int NOT NULL AUTO_INCREMENT,
                             user_id int NOT NULL,
                             collectionName varchar(255) NOT NULL,
                             CONSTRAINT Collections_pk PRIMARY KEY (id)
);

-- Table: Movies
CREATE TABLE Movies (
                        id int NOT NULL AUTO_INCREMENT,
                        imdb_id varchar(100) NOT NULL,
                        title varchar(255) NOT NULL,
                        image text NOT NULL,
                        runtime int NOT NULL,
                        ratingMPAA varchar(10) NOT NULL,
                        releaseDate varchar(20) NOT NULL,
                        plot text NOT NULL,
                        genre text NOT NULL,
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
                       username varchar(255) NOT NULL,
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

-- foreign keys
-- Reference: Collections_Users (table: Collections)
ALTER TABLE Collections ADD CONSTRAINT Collections_Users FOREIGN KEY Collections_Users (user_id)
    REFERENCES Users (id);

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
    REFERENCES Movies (id)
    ON DELETE CASCADE;

-- Reference: Ratings_Users (table: Ratings)
ALTER TABLE Ratings ADD CONSTRAINT Ratings_Users FOREIGN KEY Ratings_Users (user_id)
    REFERENCES Users (id)
    ON DELETE CASCADE;

-- Reference: Roles_Users (table: Roles)
ALTER TABLE Roles ADD CONSTRAINT Roles_Users FOREIGN KEY Roles_Users (user_id)
    REFERENCES Users (id);

-- End of file.




-- insert sample data

insert into Users values (default, 'Bob', 'Behnken', 'astrobob', 'password', true, true, false);
insert into Users values (default, 'Doug', 'Hurley', 'astrodoug', 'password', true, true, false);
insert into Users values (default, 'Chris', 'Hadfield', 'astrochris', 'password', true, true, false);
insert into Users values (default, 'Scott', 'Kelly', 'astroscott', 'password', true, true, false);

insert into Movies values (default
        , 'tt3659388'
        ,'The Martian'
        , 'https://m.media-amazon.com/images/M/MV5BMTc2MTQ3MDA1Nl5BMl5BanBnXkFtZTgwODA3OTI4NjE@._V1_SX300.jpg'
        , 144, 'PG-13', '02 Oct 2015'
        , 'An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive.'
        , 'Adventure, Drama, Sci-Fi');
insert into Movies values (default
        , 'tt1201607'
        , 'Harry Potter and the Deathly Hallows – Part 2'
        , 'https://m.media-amazon.com/images/M/MV5BMjIyZGU4YzUtNDkzYi00ZDRhLTljYzctYTMxMDQ4M2E0Y2YxXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg'
        , 130, 'PG-13', '15 Jul 2011'
        , 'Harry, Ron, and Hermione search for Voldemort''s remaining Horcruxes in their effort to destroy the Dark Lord as the final battle rages on at Hogwarts.'
        , 'Adventure, Drama, Fantasy, Mystery');
insert into Movies values (default
        , 'tt2935510'
        , 'Ad Astra'
        , 'https://m.media-amazon.com/images/M/MV5BZTllZTdlOGEtZTBmMi00MGQ5LWFjN2MtOGEyZTliNGY1MzFiXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_SX300.jpg'
        , 123, 'PG-13', '20 Sep 2019'
        , 'Astronaut Roy McBride undertakes a mission across an unforgiving solar system to uncover the truth about his missing father and his doomed expedition that now, 30 years later, threatens the universe.'
        , 'Adventure, Drama, Mystery, Sci-Fi, Thriller');

insert into Ratings values (default, 1, 1, current_date(), 5);
insert into Ratings values (default, 2, 1, current_date(), 5);
insert into Ratings values (default, 3, 1, current_date(), 5);
insert into Ratings values (default, 4, 2, current_date(), 5);
insert into Ratings values (default, 1, 1, current_date(), 4);

insert into Collections values (default, 1, 'personal');
insert into Collections values (default, 2, 'personal');
insert into Collections values (default, 3, 'personal');
insert into Collections values (default, 4, 'personal');

insert into MoviesCollections values (default, 1, 1, true, true, false);
insert into MoviesCollections values (default, 1, 2, true, false, false);
insert into MoviesCollections values (default, 1, 3, true, true, true);
insert into MoviesCollections values (default, 2, 1, true, true, true);

insert into Roles values (default, 'admin', 'astroscott', 4);