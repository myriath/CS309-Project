USE fuud_db;

CREATE TABLE all_foods (
	fdc_id TEXT,
    data_type TEXT,
    description TEXT,
    food_category_id TEXT,
    publication_date TEXT,
    FULLTEXT (description)
)  ENGINE=InnoDB;

CREATE TABLE all_foods_cleaned (
	fdc_id INTEGER,
    data_type TEXT,
    description TEXT,
    food_category_id TEXT,
    publication_date TEXT,
    FULLTEXT (description)
)  ENGINE=InnoDB;

LOAD DATA LOCAL INFILE 'G:/CS 309/FoodData_Central_csv_2022-04-28/food.csv' IGNORE 
INTO TABLE all_foods
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n';

SHOW WARNINGS;

INSERT INTO all_foods_cleaned (fdc_id, data_type, description, food_category_id, publication_date)
	SELECT CAST(fdc_id AS UNSIGNED INT), data_type, description, food_category_id, MAX(publication_date) FROM all_foods GROUP BY description;

