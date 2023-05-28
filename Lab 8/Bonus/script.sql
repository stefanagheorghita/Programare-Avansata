
drop table albums;
 CREATE TABLE albums (
   id INT NOT NULL AUTO_INCREMENT,
   release_year INT NOT NULL,
   title VARCHAR(100) NOT NULL,
   artist VARCHAR(100) NOT NULL,
   genre VARCHAR(100) NOT NULL  DEFAULT '',
   PRIMARY KEY (id)

 );

--CREATE TABLE albums (
--  id INT NOT NULL AUTO_INCREMENT,
--  release_year INT NOT NULL,
--  title VARCHAR(100) NOT NULL,
--  artist_id INT NOT NULL,
--  genre VARCHAR(100) NOT NULL  DEFAULT '',
--  PRIMARY KEY (id)
--);

CREATE TABLE artists (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL unique,
  PRIMARY KEY (id)
);

CREATE TABLE genres (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL unique,
  PRIMARY KEY (id)
);

CREATE TABLE album_genre (

  album_id INT NOT NULL,
  genre_id INT NOT NULL,
  PRIMARY KEY (album_id, genre_id),
  FOREIGN KEY (album_id) REFERENCES albums(id),
  FOREIGN KEY (genre_id) REFERENCES genres(id)
);


create TABLE playlists (
id INT NOT NULL AUTO_INCREMENT,
name varchar(100) not null,
albums text not null,
created_at date,
PRIMARY KEY (id)
);



delete from artists where name='The Beatles';
drop table album_genre;
drop table artists;
drop table genres;
drop table albums;
delete from albums where id=2;
select * from artists;
delete from genres where id=2;