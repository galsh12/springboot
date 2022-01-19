# Spring-boot assignment
spring-boot application with a REST controller for managing items.

The application runs on:

http://localhost:8080

In order to use swagger documentation:

http://localhost:8080/swagger-ui.html


The data persisted on H2 DB using JPA. 

Database console:

http://localhost:8080/h2-console

Docker:

pull docker image from docker hub:  `docker pull galsh12/springboot-gal`

run docker: `docker run -p 8008:8080 galsh12/springboot-gal`

Heroku: 

https://springboot-op-project.herokuapp.com/swagger-ui.html

The application expose the following APIs:
1. List of the inventory items list (item no, name, amount, inventory code)
2. Read item details (by item no)
3. Withdrawal quantity of a specific item from stock
4. Deposit quantity of a specific item to stock
5. Add item to stock
6. Delete an item from stock

you can also view a specific item: http://localhost:8080/api/item/1 (1 means to item no)
