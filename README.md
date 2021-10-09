# TECH PLAYGROUND

## Summary
The aim of this project is to create access technologies for learning and upskilling.

This Tech playground is simulating an Adult Community Learning center. There are members and there are courses.
Note all the data available will be generated on startup. No 2 instances will have exactly the same data.

### Members
Each member has a name, age, isTrouble maker and courses they have registered for.
### Courses
Each Course has a name, a category and a price
### Categories
The course categories for this playground are:
- Business
- Hobbies
- Information Technology

There are a variety of ways to access the data. From REST endpoint to a database. The data would be the same no matter 
what you choose. So for example you can perform analysis on the csv, you can try the same analysis from the database just to make sure you are getting the same result.

## Prerequisites
-   Docker
-   Python 3
-   Java 14

## How to run
1. python3 run.py

## Learning and experimentation
### REST endpoints
- http://localhost:8080/members/json (To access the JSON response. Good tutorial [here](https://towardsdatascience.com/restful-apis-in-python-121d3763a0e4))
- http://localhost:8080/members/xml  (To access the XML response. Another good tutorial here [here](https://realpython.com/api-integration-in-python/))
- http://localhost:8080/members/members.csv (This would download a csv file that you can use to play with things)
- http://localhost:8080/courses/json (To access the JSON response. Good tutorial [here](https://towardsdatascience.com/restful-apis-in-python-121d3763a0e4))
- http://localhost:8080/courses/xml  (To access the XML response. Another good tutorial here [here](https://realpython.com/api-integration-in-python/))
- http://localhost:8080/courses/courses.csv (This would download a csv file that you can use to play with things)

### Databases
#### Postgres RDBMS 
Good tips on how to connect to postgres [here](https://www.postgresqltutorial.com/postgresql-python/connect/)

**Connection Parameters**

    host=localhost
    port=5432
    database=techplayground
    user=postgres
    password=postgres

**Existing table names**

    members
    courses
    members_courses

**Sample members table contents**

SQL query: `select * from members limit 2`

|id|name|age|trouble_maker|
|--|----|---|-------------|
|abf0dceb-dac9-447f-ae4a-4009ab073dc6|Muoi Runte|53|false|
|a563d605-07ee-425f-bdb1-eda8c7437a80|Arthur Schulist V|20|true|

**Sample courses table contents**

SQL query: `select * from courses limit 2`

|id|category|title|price|
|--|--------|-----|-----|
|da4fd6a2-231f-4ae8-b4eb-5a6035b3149f|information_technology|The Daffodil Sky|584.8917389682547|
|1ed80eba-8c0b-4755-be68-52f36e58a909|hobbies|The Moving Toyshop|313.98030200388564|

**Sample members_courses table contents**

SQL query: `select * from members_courses limit 2`

|id|member_id|course_id|
|--|---------|---------|
|3654014a-9fcf-42f1-885c-42692cf264a7|abf0dceb-dac9-447f-ae4a-4009ab073dc6|32d865b0-dda4-474e-b185-a279b84d4fce|
|f9623a91-0cc3-45f9-9016-b20d6405d91b|abf0dceb-dac9-447f-ae4a-4009ab073dc6|da4fd6a2-231f-4ae8-b4eb-5a6035b3149f|

**Sample result from joining all tables**

SQL query: `select mc.id, m."name" as member_name, m.age as member_age, c.title as course_title , c.category as course_category, c.price as course_price from members_courses mc join members m on m.id = mc.member_id join courses c on c.id = mc.course_id limit 2`

|id|member_name|member_age|course_title|course_category|course_price|
|--|-----------|----------|------------|---------------|------------|
|f6e0736c-dd78-45e1-87e2-367538a3742a|Muoi Runte|53|The Moving Toyshop|hobbies|313.98030200388564|
|dc20d30a-c1bc-4c77-afa2-c5db43e4b503|Arthur Schulist V|20|Endless Night|information_technology|881.3140564668872|

#### Mongo NoSql DB
**Connection Parameters**
You can simply use mongodb://root:example@localhost:27017 to connect from you python script
The collection name is **techplayground**. A good tutorial can be found [here](https://realpython.com/introduction-to-mongodb-and-python/#using-mongodb-with-python-and-pymongo)

    host=localhost
    port=27017
    username=root
    password=example


