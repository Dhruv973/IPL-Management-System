# IPL Management System

A Java-based IPL Management System developed using JDBC and Oracle Database to manage teams, players, stadiums, matches, coaches, and player statistics.

The project demonstrates database design, SQL queries, PL/SQL integration, JDBC connectivity, and a JavaFX-based graphical user interface.

## Features

- View all IPL teams
- Search player by name
- View all coaches
- View all stadiums
- Display top player statistics
- Calculate total runs scored by each team
- Add a new stadium
- Add a new match
- Generate match summary using a stored procedure
- Calculate player strike rate using a PL/SQL function
- Display players of a team
- Menu-driven console application
- JavaFX graphical user interface

## Tech Stack

- Java 17
- JDBC
- Oracle Database XE
- SQL
- PL/SQL
- JavaFX
- VS Code / IntelliJ IDEA

## Project Overview

The IPL Management System is designed to manage and retrieve information related to an Indian Premier League cricket tournament.

The application connects to an Oracle Database using JDBC and performs various operations such as retrieving teams, players, coaches, stadiums, match information, and player statistics.

The project contains two interfaces:

1. **Console Application** – menu-driven Java application
2. **JavaFX Application** – graphical user interface

The system also demonstrates the use of Oracle PL/SQL features such as stored procedures, functions, and cursors.

---

## Project Structure

```text
IPL-Management-System/
│
├── src/
│   ├── DBConnection.java
│   ├── Main.java
│   ├── MainFX.java
│   ├── Queries.java
│   └── ipl.png
│
├── database.sql
├── plsql.sql
├── README.md
└── .gitignore
```

## Database

The project uses Oracle Database XE.

The SQL script contains:
- Database schema
- Table creation
- Sample IPL data
- Update statements

The application also uses PL/SQL components such as:
- Stored Procedures
- Functions
- Cursors

## How to Run

1. Install Java 17 or later.
2. Install Oracle Database XE.
3. Import `database.sql` into Oracle.
4. Execute the required PL/SQL procedure/function definitions.
5. Update database credentials in `DBConnection.java` if required.
6. Run `Main.java` for the console version or `MainFX.java` for the JavaFX version.


## License

This project is intended for educational purposes.
