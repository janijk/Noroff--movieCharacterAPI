-- Franchises
INSERT INTO franchise ("name", description) VALUES ('Transformers', 'Series of science
fiction action movies directed by Micheal Bay.');
INSERT INTO franchise ("name", description) VALUES ('Indiana Jones', 'Movies based on the adventures of
Dr. Henry Walton "Indiana" Jones, Jr., a professor of archeology.');
-- Movies
INSERT INTO movie (title, genre, release_year, director, picture, trailer)
VALUES ('Revenge of the Fallen', 'sci-fi, action', 2014, 'Michael Bay',
'https://www.themoviedb.org/t/p/original/pLBb0whOzVDtJvyD4DPeQyQNOqp.jpg',
'https://www.youtube.com/watch?v=fnXzKwUgDhg');
INSERT INTO movie (title, genre, release_year, director, picture, trailer)
VALUES ('Indiana Jones and the Raiders of the Lost Ark', 'adventure, action', 1981, 'Steven Spielberg',
'https://www.themoviedb.org/t/p/original/ceG9VzoRAVGwivFU403Wc3AHRys.jpg',
'https://www.youtube.com/watch?v=0xQSIdSRlAk');
INSERT INTO movie (title, genre, release_year, director, picture, trailer)
VALUES ('Indiana Jones and the Temple of Doom', 'adventure, action', 1984, 'Steven Spielberg',
'https://www.themoviedb.org/t/p/original/qMTFkksVVGEgXcyFJILERDJsPlv.jpg',
'https://www.youtube.com/watch?v=WBdyLyijZhU');
-- Characters
INSERT INTO "character" ("name", "alias", gender, picture) VALUES ('Henry Walton Jones, Jr.', 'Indy', 'male',
'https://upload.wikimedia.org/wikipedia/en/8/8e/Indiana_Jones_in_Raiders_of_the_Lost_Ark.jpg');
INSERT INTO "character" ("name", "alias", gender, picture) VALUES ('Samuel James Witwicky', 'Sam', 'male',
'https://tfwiki.net/mediawiki/images2/3/39/Sam_Witwicky.jpg');
INSERT INTO "character" ("name", "alias", gender, picture) VALUES ('Optimus Prime', 'Convoy', 'robot',
'https://upload.wikimedia.org/wikipedia/commons/3/3d/Optimus_Prime_%28Transformers_-_The_Last_Knight%29.jpg');
-- Characters in movie
INSERT INTO movie_characters (characters_id, movies_id) VALUES (1,2);
INSERT INTO movie_characters (characters_id, movies_id) VALUES (1,3);
INSERT INTO movie_characters (characters_id, movies_id) VALUES (2,1);
INSERT INTO movie_characters (characters_id, movies_id) VALUES (3,1);