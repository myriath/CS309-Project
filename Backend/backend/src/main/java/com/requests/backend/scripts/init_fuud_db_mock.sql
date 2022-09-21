-- Fast, Unique and User-Friendly Dieting!
CREATE DATABASE IF NOT EXISTS fuud_db;

USE fuud_db;

CREATE TABLE IF NOT EXISTS users (
    username CHAR(50) PRIMARY KEY,
    p_hash VARCHAR(64),
    p_salt CHAR(16),
    user_type CHAR(10) NOT NULL,
    email VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    age INT
);

CREATE TABLE IF NOT EXISTS fav_foods (
    username CHAR(50),
    food_name VARCHAR(255),
    fid VARCHAR(255),
    rank_val INT
);

CREATE TABLE IF NOT EXISTS food_log (
    username CHAR(50),
    fid VARCHAR(255),
    quantity INT,
    unit_type CHAR(50),
    log_date DATETIME
);

CREATE TABLE IF NOT EXISTS user_recipes (
    username CHAR(50),
    rname CHAR(50) PRIMARY KEY,
    serving_size INT,
    unit_type CHAR(50),
    calories INT,
    fat INT,
    protein INT,
    carbs INT
);

-- Create user "papajohn"
INSERT INTO users (username, p_hash, p_salt, user_type, email, full_name, age)
	VALUES ('papajohn', SHA2('pizza', 64), MD5('pizza'), 'Admin', 'bibp@gmail.com', 'Papa Johnson', 40);

-- Create user "uraniumlover22"
INSERT INTO users (username, p_hash, p_salt, user_type, email, full_name, age)
	VALUES ('uraniumlover22', SHA2('radioactive', 64), MD5('radioactive'), 'Standard', 'gone-fission@gmail.com', 'J Robert Oppenheimer', 35);

-- Create favorite food entry for user "papajohn" for food ID "513fbc1283aa2dc80c00000e", food name "Ultimate Pepperoni Pizza, Epic Stuffed Crust"
INSERT INTO fav_foods (username, food_name, fid, rank_val)
	VALUES ('papajohn', 'Ultimate Pepperoni Pizza, Epic Stuffed Crust', '513fbc1283aa2dc80c00000e', 1)


