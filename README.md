# TECH PLAYGROUND

## Summary
This project is aimed at creating sample technologies for use to learn and upskill.
This is a learning annex. There are members and there are courses and there are course categories.
Note all data available has been generated. No 2 instances will have exactly the same data.

### Members
Each member has a name, age, isTrouble maker and courses they have registered for.
### Courses
Each Course has a name, a category and a price
### Categories
The course categories for this playground are:
- Business
- Hobbies
- IT

There are a variety of ways to access the data. From REST endpoint to a database. The data would be the same no matter 
what you choose. So for example you can perform analysis on the csv, you can try the same analysis from the database just to make sure you are getting the same result.

## Prerequisites
-   Docker
-   Python 3

## How to run
1. python3 run.py

## Learning and experimentation
### REST endpoints
- http://localhost:8080/members/json (To access the JSON response. Good tutorial [here](https://towardsdatascience.com/restful-apis-in-python-121d3763a0e4))
- http://localhost:8080/members/xml  (To access the XML response. Another good tutorial here [here](https://realpython.com/api-integration-in-python/))
- http://localhost:8080/members/members.csv (This would download a csv file that you can use to play with things)

### Databases
#### Postgres RDBMS 
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

Good tips on how to connect to postgres [here](https://www.postgresqltutorial.com/postgresql-python/connect/)
#### Mongo NoSql DB
**Connection Parameters**

    host=localhost
    port=27017
    username=root
    password=example
You can simply use mongodb://root:example@localhost:27017 to connect from you python script
The collection name is **techplayground**. A good tutorial can be found [here](https://realpython.com/introduction-to-mongodb-and-python/#using-mongodb-with-python-and-pymongo)

