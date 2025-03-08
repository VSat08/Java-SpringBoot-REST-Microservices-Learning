-- File: views_demo.sql
-- Purpose: Demonstrates SQL Views in MySQL

-- Use the world database (assumes it’s available)
USE world;

-- Section 3.1: Creating a View
-- Example 1: Single Table with Selected Columns
CREATE VIEW country_view AS
SELECT code, name, continent, region, population, life_expectancy, capital
FROM country;
SELECT * FROM country_view LIMIT 10;

-- Example 2: Filtered View
CREATE VIEW african_countries AS
SELECT *
FROM country
WHERE continent = 'Africa';
SELECT name, capital FROM african_countries;
SELECT COUNT(*) FROM african_countries; -- 58 rows

-- Section 3.2: Replacing a View
-- Example 1: Update Columns
CREATE OR REPLACE VIEW country_view AS
SELECT code, name, continent, population
FROM country;
SELECT * FROM country_view LIMIT 10;

-- Example 2: Fix a Mistake
CREATE OR REPLACE VIEW african_countries AS
SELECT *
FROM country
WHERE continent = 'Africa';
SELECT COUNT(*) FROM african_countries; -- Still 58

-- Section 3.3: Dropping a View
DROP VIEW african_countries;
SHOW FULL TABLES; -- Verify removal

-- Section 4.1: Views on Multiple Tables
-- Example: Country and City Join
CREATE OR REPLACE VIEW indian_city_name_view AS
SELECT country.code, country.name AS country_name, city.id, city.name AS city_name
FROM country
JOIN city
ON country.code = city.countrycode
WHERE country.code = 'IND';
SELECT * FROM indian_city_name_view LIMIT 10;

-- Updated Example: Add Region
CREATE OR REPLACE VIEW indian_city_name_view AS
SELECT country.code, country.name AS country_name, city.id, city.name AS city_name, country.region
FROM country
JOIN city
ON country.code = city.countrycode
WHERE country.code = 'IND';
SELECT * FROM indian_city_name_view LIMIT 25;

-- Section 4.2: Views with Referential Actions (Conceptual)
-- Note: Shows base table setup; view reflects changes
CREATE TABLE customers (
    cid INT PRIMARY KEY,
    cname VARCHAR(50)
);
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(cid) ON DELETE CASCADE
);
INSERT INTO customers VALUES (1, 'Ram');
INSERT INTO orders VALUES (1, 1);
CREATE VIEW order_view AS
SELECT * FROM orders;
DELETE FROM customers WHERE cid = 1; -- Cascades to orders, reflected in order_view
SELECT * FROM order_view;
