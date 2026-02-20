# Banking System V3 

## Overview
A full stack banking application built with Spring Boot and React (in development). This is the third iteration of the project. V1 was procedural, V2 introduced JavaFX and implemented OOP principles, V3 implements full-stack architecture with RESTful API design and database persistence. This is being built alongside my university studies to develop comprehensive full-stack development Currently; backend complete with Spring Boot REST API and JPA; frontend with React and PostgreSQL is planned.  


## Current State

**Completed**  

- [x] Rest API with core banking functions (deposit, withdraw, transfer funds, create account)
- [x] Transaction history with timestamps stored in a database
- [x] Custom exception handling returning appropriate HTTP status codes (404 for account not found)
- [x] Three tier architecture (Controller,Service,Repository)
- [x] JPA entity relationships (one-to-many between Account and TransactionHistory)


**In progress**  

- [ ] PostgreSQL migration
- [ ] Basic login authentication with PIN


**Planned**

- [ ] Frontend using React
- [ ] Different account types (savings, etc)
- [ ] Interest Rates
- [ ] Authentication with JWT
- [ ] Input validation
- [ ] Junit tests



