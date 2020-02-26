-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-02-26 21:44:56.747

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
    title varchar(255) NOT NULL,
    CONSTRAINT Movies_pk PRIMARY KEY (id)
);

-- Table: MoviesCollections
CREATE TABLE MoviesCollections (
    collection_id int NOT NULL,
    movie_id int NOT NULL,
    CONSTRAINT MoviesCollections_pk PRIMARY KEY (collection_id,movie_id)
);

-- Table: Ratings
CREATE TABLE Ratings (
    user_id int NOT NULL,
    movie_id int NOT NULL,
    dateWatched date NOT NULL,
    rating int NOT NULL,
    CONSTRAINT Ratings_pk PRIMARY KEY (user_id,movie_id,dateWatched)
);

-- Table: Users
CREATE TABLE Users (
    id int NOT NULL AUTO_INCREMENT,
    firstName varchar(255) NOT NULL,
    lastName varchar(255) NOT NULL,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    CONSTRAINT Users_pk PRIMARY KEY (id)
);

-- Table: UsersCollections
CREATE TABLE UsersCollections (
    user_id int NOT NULL,
    collections_id int NOT NULL,
    CONSTRAINT UsersCollections_pk PRIMARY KEY (user_id,collections_id)
);

-- foreign keys
-- Reference: MoviesCollections_Collections (table: MoviesCollections)
ALTER TABLE MoviesCollections ADD CONSTRAINT MoviesCollections_Collections FOREIGN KEY MoviesCollections_Collections (collection_id)
    REFERENCES Collections (id);

-- Reference: MoviesCollections_Movies (table: MoviesCollections)
ALTER TABLE MoviesCollections ADD CONSTRAINT MoviesCollections_Movies FOREIGN KEY MoviesCollections_Movies (movie_id)
    REFERENCES Movies (id);

-- Reference: Ratings_Movies (table: Ratings)
ALTER TABLE Ratings ADD CONSTRAINT Ratings_Movies FOREIGN KEY Ratings_Movies (movie_id)
    REFERENCES Movies (id);

-- Reference: Ratings_Users (table: Ratings)
ALTER TABLE Ratings ADD CONSTRAINT Ratings_Users FOREIGN KEY Ratings_Users (user_id)
    REFERENCES Users (id);

-- Reference: UsersCollections_Collections (table: UsersCollections)
ALTER TABLE UsersCollections ADD CONSTRAINT UsersCollections_Collections FOREIGN KEY UsersCollections_Collections (collections_id)
    REFERENCES Collections (id);

-- Reference: UsersCollections_Users (table: UsersCollections)
ALTER TABLE UsersCollections ADD CONSTRAINT UsersCollections_Users FOREIGN KEY UsersCollections_Users (user_id)
    REFERENCES Users (id);

-- End of file.

