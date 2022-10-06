SELECT * FROM fuud_db.branded_foods;

SELECT * FROM fuud_db.all_foods;

-- Find best match based on user keyword input
SELECT *, MATCH (description) AGAINST ('+starbucks' IN BOOLEAN MODE) AS score FROM all_foods_cleaned
WHERE MATCH (description) AGAINST ('+starbucks' IN BOOLEAN MODE);

-- Get ingredients for food
SELECT ingredients FROM branded_foods WHERE fdc_id = 350561;
