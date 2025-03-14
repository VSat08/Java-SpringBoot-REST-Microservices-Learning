
CREATE TABLE employee (
    emp_id INT,
    emp_name VARCHAR(20),
    emp_city VARCHAR(20),
    emp_pay FLOAT
);

-- Insert 10 rows 
INSERT INTO employee VALUES (123, 'Ram', 'New Delhi', 78000);
INSERT INTO employee VALUES (112, 'Sita', 'Mumbai', 56000);
INSERT INTO employee VALUES (100, 'Sam', 'Mumbai', 86000);
INSERT INTO employee VALUES (200, 'Rita', 'Bangalore', 75000);
INSERT INTO employee VALUES (201, 'Geetha', 'Hyderabad', 75000);
INSERT INTO employee VALUES (211, 'Vikas', 'Bangalore', 56000);
INSERT INTO employee VALUES (139, 'Vasu', 'Bangalore', 78000);
INSERT INTO employee VALUES (111, 'Anil', 'Hyderabad', 86000);
INSERT INTO employee VALUES (129, 'Ramesh', 'Hyderabad', 66000);
INSERT INTO employee VALUES (150, 'Suresh', 'New Delhi', 66000);

-- Updates 
UPDATE employee SET emp_pay = 86000 WHERE emp_id = 211;
UPDATE employee SET emp_pay = 66000 WHERE emp_id = 150;

-- Basic SELECT for context
SELECT * FROM employee;

-- GROUP BY: Employees per city
SELECT COUNT(emp_id) AS number_of_employees, emp_city FROM employee GROUP BY emp_city;

-- GROUP BY: Employees per pay
SELECT COUNT(emp_id), emp_pay FROM employee GROUP BY emp_pay;

-- GROUP BY with ORDER BY: Employees per city, ordered by count
SELECT COUNT(emp_id) AS number_of_employees, emp_city FROM employee GROUP BY emp_city ORDER BY COUNT(emp_id) DESC;

-- HAVING: Cities with >= 3 employees
SELECT COUNT(emp_id) AS number_of_employees, emp_city FROM employee GROUP BY emp_city HAVING COUNT(emp_id) >= 3;

-- Full Query: WHERE, GROUP BY, HAVING, ORDER BY
SELECT COUNT(emp_id) AS number_of_employees, emp_city FROM employee WHERE emp_city IN ('Hyderabad', 'Bangalore', 'Mumbai', 'New Delhi') GROUP BY emp_city HAVING COUNT(emp_id) >= 2 ORDER BY COUNT(emp_id) DESC, emp_city ASC;

-- Switch to 'world' database (assumed pre-existing)
-- SHOW TABLES and SELECT DATABASE() for context
SHOW TABLES;
SELECT DATABASE();

-- DESCRIBE city table
DESCRIBE city;

-- Basic SELECT from city with LIMIT
SELECT * FROM city LIMIT 20;

-- SELECT cities for India with LIMIT
SELECT * FROM city WHERE countrycode = 'IND' LIMIT 20;

-- Count total cities
SELECT COUNT(*) FROM city;

-- Count distinct country codes
SELECT DISTINCT countrycode FROM city;

-- GROUP BY: Cities per country
SELECT COUNT(name), countrycode FROM city GROUP BY countrycode;

-- GROUP BY with ORDER BY: Cities per country, ordered by count
SELECT COUNT(name), countrycode FROM city GROUP BY countrycode ORDER BY COUNT(name) DESC;

-- GROUP BY with LIMIT: Top 10 countries by city count
SELECT COUNT(name), countrycode FROM city GROUP BY countrycode ORDER BY COUNT(name) DESC LIMIT 10;

-- HAVING: Countries with > 100 cities
SELECT COUNT(name), countrycode FROM city GROUP BY countrycode HAVING COUNT(name) > 100;

-- HAVING with LIMIT: Countries with > 50 cities, limited to 15
SELECT COUNT(name), countrycode FROM city GROUP BY countrycode HAVING COUNT(name) > 50 LIMIT 15;

-- Full Query: WHERE, GROUP BY, HAVING, ORDER BY for countries with 'i'
SELECT COUNT(name), countrycode FROM city WHERE countrycode LIKE '%i%' GROUP BY countrycode HAVING COUNT(name) >= 10 ORDER BY COUNT(name) DESC;

-- Additional WHERE examples
SELECT COUNT(name), countrycode FROM city WHERE countrycode LIKE '%n%' GROUP BY countrycode HAVING COUNT(name) >= 10 ORDER BY COUNT(name) DESC;
SELECT COUNT(name), countrycode FROM city WHERE countrycode LIKE '%d%' GROUP BY countrycode HAVING COUNT(name) >= 10 ORDER BY COUNT(name) DESC;