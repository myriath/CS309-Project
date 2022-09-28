-- Fast, Unique and User-Friendly Dieting!
CREATE DATABASE IF NOT EXISTS fuud_db;

USE fod_db;

CREATE TABLE IF NOT EXISTS users (
    username CHAR(50) PRIMARY KEY,
    p_hash VARCHAR(64),
    p_salt CHAR(32),
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

-- Create user "papajohn". Unhashed password is "hello".food_logfood_log
INSERT INTO users (username, p_hash, p_salt, user_type, email, full_name, age)
	VALUES ('papajohn', 'b1880571f997b2b19f66e6fedf82c19ba3be1ffc8e65c3a98f4e22ba9784c2bf', '41ccde641586d6c393c1cf5f9436564a', 'Admin', 'bibp@gmail.com', 'Papa Johnson', 40);

-- Create user "uraniumlover22". Unhashed password is "world".
INSERT INTO users (username, p_hash, p_salt, user_type, email, full_name, age)
	VALUES ('uraniumlover22', 'a606a804cce3f7a6ea40e0fec1adf66c510df1a69511d5633639a5768b59a28c', '02047aec31856db44b5da6ec1d294393', 'Standard', 'gone-fission@gmail.com', 'J Robert Oppenheimer', 35);

-- Create favorite food entry for user "papajohn" for food ID "513fbc1283aa2dc80c00000e", food name "Ultimate Pepperoni Pizza, Epic Stuffed Crust"
INSERT INTO fav_foods (username, food_name, fid, rank_val)
	VALUES ('papajohn', 'Ultimate Pepperoni Pizza, Epic Stuffed Crust', '513fbc1283aa2dc80c00000e', 1)


