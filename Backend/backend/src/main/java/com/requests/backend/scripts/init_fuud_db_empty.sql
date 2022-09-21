
-- Fast, Unique and User-Friendly Dieting!
CREATE DATABASE IF NOT EXISTS fuud_db;

CREATE TABLE IF NOT EXISTS users (
    username CHAR(50) PRIMARY KEY,
    p_hash BINARY(256),
    p_salt BINARY(16),
    user_type CHAR(10) NOT NULL,
    email VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    age INT
);

CREATE TABLE IF NOT EXISTS fav_foods (
    username CHAR(50),
    food_name VARCHAR(255),
    fid INT,
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



