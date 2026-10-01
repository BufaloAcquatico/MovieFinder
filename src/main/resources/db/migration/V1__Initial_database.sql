CREATE TABLE users (
	id BIGINT NOT NULL AUTO_INCREMENT,
	password VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL,
	locked BOOLEAN NOT NULL,
    role VARCHAR(20) NOT NULL,
	CONSTRAINT pk_users PRIMARY KEY (id),
	CONSTRAINT uk_users_email UNIQUE (email)
) ENGINE=InnoDB;

CREATE TABLE movies (
	id BIGINT NOT NULL AUTO_INCREMENT,
	title VARCHAR(255) NOT NULL,
	description TEXT,
	release_date DATE,
	duration INT,
	language VARCHAR(20),
	director VARCHAR(100),
	rating SMALLINT,
	CONSTRAINT pk_movies PRIMARY KEY (id),
	CONSTRAINT chk_movies_rating CHECK (rating IS NULL OR rating BETWEEN 1 AND 10)
) ENGINE=InnoDB;

CREATE TABLE genres (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	CONSTRAINT pk_genres PRIMARY KEY (id),
	CONSTRAINT uk_genres_name UNIQUE (name)
) ENGINE=InnoDB;

CREATE TABLE reviews (
	id BIGINT NOT NULL AUTO_INCREMENT,
	rating SMALLINT NOT NULL,
	comment TEXT,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	user_id BIGINT NOT NULL,
	movie_id BIGINT NOT NULL,
	CONSTRAINT pk_reviews PRIMARY KEY (id),
	CONSTRAINT chk_reviews_rating CHECK (rating BETWEEN 1 AND 10),
	CONSTRAINT fk_reviews_user FOREIGN KEY (user_id) REFERENCES users (id),
	CONSTRAINT fk_reviews_movie FOREIGN KEY (movie_id) REFERENCES movies (id)
) ENGINE=InnoDB;

CREATE TABLE movie_genres (
	movie_id BIGINT NOT NULL,
	genre_id BIGINT NOT NULL,
	CONSTRAINT pk_movie_genre PRIMARY KEY (movie_id, genre_id),
	CONSTRAINT fk_movie_genre_movie FOREIGN KEY (movie_id) REFERENCES movies (id),
	CONSTRAINT fk_movie_genre_genre FOREIGN KEY (genre_id) REFERENCES genres (id)
) ENGINE=InnoDB;
