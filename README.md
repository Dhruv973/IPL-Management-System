# IPL Management System

A Java-based IPL Management System developed using JDBC and Oracle Database to manage teams, players, stadiums, matches, coaches, and player statistics.

The project demonstrates database design, SQL queries, PL/SQL integration, JDBC connectivity, and a JavaFX-based graphical user interface.

---

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

---

## Tech Stack

- **Java 17**
- **JDBC**
- **Oracle Database XE**
- **SQL**
- **PL/SQL**
- **JavaFX**
- **VS Code**
- **IntelliJ IDEA**
- **Git & GitHub**

---

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

---

## Database

The project uses **Oracle Database XE** as the backend database.

The database manages information related to:

- Teams
- Players
- Coaches
- Stadiums
- Matches
- Player statistics

### SQL Files

- **`database.sql`** – Contains the database schema, table creation, and sample IPL data.
- **`plsql.sql`** – Contains PL/SQL procedures, functions, and cursors used by the application.

---

## Requirements

Before running the project, make sure the following are installed:

- **Java 17**
- **Oracle Database XE**
- **JavaFX SDK 17.0.20**
- **Oracle JDBC Driver (`ojdbc11.jar`)**
- **VS Code or IntelliJ IDEA**

The Oracle Database should be running before starting the application.

---

## Database Setup

1. Install and configure **Oracle Database XE**.
2. Open Oracle SQL Developer or another Oracle SQL environment.
3. Execute `database.sql` to create the required tables and insert the sample IPL data.
4. Execute `plsql.sql` to create the required procedures, functions, and cursors.
5. Open `src/DBConnection.java`.
6. Update the Oracle database username, password, and connection URL if required.

The database must be running before starting the Java application.

---

## How to Run

### Console Version

The console application is located in:

```text
src/Main.java
```

Compile:

```powershell
javac -cp "lib\ojdbc11.jar" -d out src\Main.java src\Queries.java src\DBConnection.java
```

Run:

```powershell
java -cp "out;lib\ojdbc11.jar" Main
```

### JavaFX Version

The JavaFX application is located in:

```text
src/MainFX.java
```

Compile:

```powershell
javac --module-path "lib\javafx-sdk-17.0.20\lib" --add-modules javafx.controls,javafx.fxml -cp "lib\ojdbc11.jar" -d out src\MainFX.java src\Queries.java src\DBConnection.java
```

Run:

```powershell
java --module-path "lib\javafx-sdk-17.0.20\lib" --add-modules javafx.controls,javafx.fxml -cp "out;lib\ojdbc11.jar" MainFX
```

The JavaFX application will open as a graphical IPL Management System.

---

## Important

The `lib/` folder is ignored by Git and is not included in the GitHub repository.

Anyone cloning the project will need to install the JavaFX SDK and Oracle JDBC Driver (`ojdbc11.jar`) separately.

---

## License

This project is intended for educational purposes.

