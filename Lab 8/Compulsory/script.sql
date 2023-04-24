CREATE TABLE albums (
  id INT NOT NULL AUTO_INCREMENT,
  release_year INT NOT NULL,
  title VARCHAR(20) NOT NULL,
  artist VARCHAR(20) NOT NULL,
  genre VARCHAR(50) NOT NULL ,
  PRIMARY KEY (id)
);

CREATE TABLE artists (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL unique,
  PRIMARY KEY (id)
);

CREATE TABLE genres (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL unique,
  PRIMARY KEY (id)
);

CREATE TABLE album_genre (

  album_id INT NOT NULL,
  genre_id INT NOT NULL,
  PRIMARY KEY (album_id, genre_id),
  FOREIGN KEY (album_id) REFERENCES albums(id),
  FOREIGN KEY (genre_id) REFERENCES genres(id)
);

drop table album_genre;
drop table artists;