# Lider IT REST API

Storage of sports team and players data

Version: 0.0.1

## Get Started

### With Docker

1. Install [Docker](https://docs.docker.com/desktop/)
2. Download [deploy file](https://drive.google.com/file/d/1aAd6PdPpu2Lo-8GTAthr9hyUg-cEgN1Q/view?usp=share_link)
3. Execute the command

    "docker compose -f ^path to downloaded file^ up"

### Manualy

1. Install [JRE 8](https://www.oracle.com/cis/java/technologies/javase/javase8-archive-downloads.html)
2. Install [PostgresQL](https://www.postgresqltutorial.com/postgresql-getting-started/)
3. Download [LiderIt.jar](https://github.com/MorozovArYu/LiderIt/raw/main/deploy/LiderIt.jar)
4. Excecute:

    "java -jar LiderIt.jar ^user^ ^password^>"

if you don't want automatically generated bd:

1. Execute [createUser.sql](https://github.com/MorozovArYu/TestRepo/raw/main/deploy/createUser.sql)
2. Execute [createDB.sql](https://github.com/MorozovArYu/TestRepo/raw/main/deploy/createDB.sql)
3. Execute:
    
    "java -jar LiderIt.jar"

## REST API doc

### <a name="ref13"></a>Table of Contents

___TEAMS:___

+ [Get list of teams](#ref7)

+ [Get list of teams and filtered by sportKind](#ref8)

+ [Get list of teams and filtered by date](#ref9)

+ [Post a team](#ref10)

+ [Put a team by teamId](#ref11)

+ [Delete a team by teamId](#ref12)

___PLAYERS:___

+ [Get players of team by teamId](#ref1)

+ [Get list of players from team by role](#ref2)

+ [Post players to team](#ref3)

+ [Change player team](#ref4)

+ [Put new data to the player by teamId](#ref5)

+ [Delete a player by playerId](#ref6)

**SERVER:**

http://localhost:8080

### <a name="ref7"></a>Get list of teams

    GET /sports/teams

+ Responses:

| Index | Code |  Response Body   |
|:-----:|:----:|:----------------:|
|   1   | 200  | application/json |
|   2   | 204  |      empty       |

+ Response example{1}:

| id  | name      | sportKind | creationDate |
|-----|-----------|-----------|--------------|
| 1   | Barcelona | Football  | 1899-11-29   |
| 2   | Avangard  | Hockey    | 1950-11-07   |

[Table of Contents](#ref13)

### <a name="ref8"></a>Get list of teams and filtered by sportKind

    GET /sport/teams/filters/sport-kinds

+ Parameters:

|   Name    |     Type      | Required |  Description  | Example  |
|:---------:|:-------------:|:--------:|:-------------:|:--------:|
| sportKind | String(query) |   True   | Kind of sport | Football |

+ Responses:

| Index | Code |  Response Body   |
|:-----:|:----:|:----------------:|
|   1   | 200  | application/json |
|   2   | 204  |      empty       |

+ Response example{1}:

| id  |       name        | sportKind | creationDate |
|:---:|:-----------------:|:---------:|:------------:|
|  1  |     Barcelona     | Football  |  1899-11-29  |
|  2  | Manchester United | Football  |  1879-01-01  |

[Table of Contents](#ref13)

### <a name="ref9"></a>Get list of teams and filtered by date.

    GET /sport/teams/filters/dates

+ Description:

  Get teams created between startDate and endDate


+ Parameters:

|   Name    |     Type      | Required | Description |  Example   |    Format    |
|:---------:|:-------------:|:--------:|:-----------:|:----------:|:------------:|
| startDate | String(query) |   True   |  From date  | 1800-01-01 | 'yyyy-MM-dd' |
|  endDate  | String(query) |   True   |   To date   | 2000-01-01 | 'yyyy-MM-dd' |

+ Responses:

| Index | Code |  Response Body   |
|:-----:|:----:|:----------------:|
|   1   | 200  | application/json |
|   2   | 204  |      empty       |
|   3   | 400  | application/json |
|   4   | 400  | application/json |

+ Response example{1}:

| id  |       name        | sportKind | creationDate |
|:---:|:-----------------:|:---------:|:------------:|
|  1  |     Barcelona     | Football  |  1899-11-29  |
|  2  | Manchester United | Football  |  1879-01-01  |
|  3  |     Avangard      |  Hockey   |  1950-11-07  |

+ Response example{3}:

|                                  message                                  |          date           |
|:-------------------------------------------------------------------------:|:-----------------------:|
| The endDate cannot be earlier than the startDate [2100-01-01, 2200-01-01] | 2023-03-21T02:56:20.368 |

+ Response example{4}:

| message                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       | date                   | description                                              |
|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-----------------------|:---------------------------------------------------------|
| Failed to convert value of type 'java.lang.String' to required type 'java.time.LocalDate'; nested exception is org.springframework.core.convert.ConversionFailedException: Failed to convert from type [java.lang.String] to type [@org.springframework.web.bind.annotation.RequestParam @org.springframework.format.annotation.DateTimeFormat java.time.LocalDate] for value '220-01-01'; nested exception is java.lang.IllegalArgumentException: Parse attempt failed for value [220-01-01] | 2023-03-21T03:38:00.79 | 220-01-01: incorrect format. Format must be 'yyyy-MM-dd' |

[Table of Contents](#ref13)

### <a name="ref10"></a>Post a team

    POST /sports/teams

+ Request body:

|   name    | sportKind | creationDate |
|:---------:|:---------:|:------------:|
| Barcelona | Football  |  1899-11-29  |

+ Responses:

| Index | Code |  Response Body   |
|:-----:|:----:|:----------------:|
|   1   | 201  | application/json |
|   2   | 400  |      empty       |

+ Response example{1}:

| id  |   name    | sportKind | creationDate |
|:---:|:---------:|:---------:|:------------:|
|  1  | Barcelona | Football  |  1899-11-29  |

[Table of Contents](#ref13)

### <a name="ref11"></a>Put a team by teamId

    PUT /sports/teams/{teamId}

+ Description:

  Add team if teamId not found or change team info


+ Parameters:

|  Name  |     Type      | Required |          Description          | Example |
|:------:|:-------------:|:--------:|:-----------------------------:|:-------:|
| teamId | Integer(path) |   True   | Numeric id of the team to get |    1    |

+ Request body:

|   name    | sportKind | creationDate |
|:---------:|:---------:|:------------:|
| Barcelona | Football  |  1899-11-29  |

+ Responses:

| Index | Code |  Response Body   |
|:-----:|:----:|:----------------:|
|   1   | 200  | application/json |
|   2   | 201  | application/json |
|   3   | 400  |      empty       |

+ Response example{1}:

| id  |   name    | sportKind | creationDate |
|:---:|:---------:|:---------:|:------------:|
|  1  | Barcelona | Football  |  1899-11-29  |

+ Response example{2}:

| id  |   name    | sportKind | creationDate |
|:---:|:---------:|:---------:|:------------:|
|  2  | Barcelona | Football  |  1899-11-29  |

[Table of Contents](#ref13)

### <a name="ref12"></a>Delete a team by teamId

    DELETE /sports/teams/{teamId}

+ Parameters:

|  Name  |     Type      | Required |          Description          | Example |
|:------:|:-------------:|:--------:|:-----------------------------:|:-------:|
| teamId | Integer(path) |   True   | Numeric id of the team to get |    1    |

+ Responses:

| Index | Code |  Response Body   |
|:-----:|:----:|:----------------:|
|   1   | 204  |      empty       |
|   2   | 404  | application/json |

+ Response example{2}:

|         message          |          date           |
|:------------------------:|:-----------------------:|
| Team with id:1 not found | 2023-03-21T02:56:20.368 |

[Table of Contents](#ref13)

### <a name="ref1"></a>Get players of team by teamId

    GET /sports/players/{teamId}

+ Description:

  If team found, return list of players in team


+ Parameters:

|  Name  |     Type      | Required |          Description          | Example |
|:------:|:-------------:|:--------:|:-----------------------------:|:-------:|
| teamId | Integer(path) |   True   | Numeric ID of the team to get |    1    |

+ Responses:

| Index | Code |  Response Body   |
|:-----:|:----:|:----------------:|
|   1   | 200  | application/json |
|   2   | 204  |      empty       |
|   3   | 404  | application/json |

+ Response example{1}:

| id  | name  |  surname  | birthDate  |    role    |
|:---:|:-----:|:---------:|:----------:|:----------:|
|  1  | Ander | Astralaga | 2004-03-03 | Goalkeeper |
|  2  | Arnau |   Tenas   | 2001-05-30 | Goalkeeper |

+ Response example{3}:

|          message           |          date           |
|:--------------------------:|:-----------------------:|
| Team with id:100 not found | 2023-03-21T04:24:28.745 |

[Table of Contents](#ref13)

### <a name="ref2"></a>Get list of players from team by role

    GET /sports/players/{teamId}/filters/roles

+ Description:

  If team found get all players and filters them by role


+ Parameters:

|  Name  |     Type      | Required |          Description          |  Example   |
|:------:|:-------------:|:--------:|:-----------------------------:|:----------:|
|  role  | String(query) |   True   |         Name of role          | Goalkeeper |
| teamId | Integer(path) |   True   | Numeric ID of the team to get |     1      |

+ Request body:

| name  |  surname  | birthDate  |    role    |
|:-----:|:---------:|:----------:|:----------:|
| Ander | Astralaga | 2004-03-03 | Goalkeeper |

+ Responses:

| Index | Code |  Response Body   |
|:-----:|:----:|:----------------:|
|   1   | 201  | application/json |
|   2   | 404  | application/json |

+ Response example{1}:

| id  | name  |  surname  | birthDate  |    role    |
|:---:|:-----:|:---------:|:----------:|:----------:|
|  1  | Ander | Astralaga | 2004-03-03 | Goalkeeper |

+ Response example{2}:

|         message          |          date           |
|:------------------------:|:-----------------------:|
| Team with id:1 not found | 2023-03-21T02:56:20.368 |

[Table of Contents](#ref13)

### <a name="ref3"></a>Post players to team

    POST /sports/players/{playerId}

+ Description:

  If team found, post player to it.

+ Request body:

| name  |  surname  | birthDate  |    role    |
|:-----:|:---------:|:----------:|:----------:|
| Ander | Astralaga | 2004-03-03 | Goalkeeper |

+ Parameters:

|  Name  |     Type      | Required |          Description          | Example |
|:------:|:-------------:|:--------:|:-----------------------------:|:-------:|
| teamId | Integer(path) |   True   | Numeric ID of the team to get |    1    |

+ Responses:

| Index | Code |  Response Body   |
|:-----:|:----:|:----------------:|
|   1   | 201  | application/json |
|   2   | 404  | application/json |

+ Response example{1}:

| id  | name  |  surname  | birthDate  |    role    |
|:---:|:-----:|:---------:|:----------:|:----------:|
|  1  | Ander | Astralaga | 2004-03-03 | Goalkeeper |

+ Response example{2}:

|         message          |          date           |
|:------------------------:|:-----------------------:|
| Team with id:1 not found | 2023-03-21T02:56:20.368 |

[Table of Contents](#ref13)

### <a name="ref4"></a>Change player team

    PATCH /sports/players/{playerId}

+ Description:

  If team found and player found changes player team.

+ Parameters:

|   Name    |      Type      | Required |           Description            | Example |
|:---------:|:--------------:|:--------:|:--------------------------------:|:-------:|
| newTeamId | Integer(query) |   True   | Numeric ID of new team to player |    2    |
| playerId  | Integer(path)  |   True   | Numeric ID of the player to get  |    1    |

+ Responses:

| Index | Code |  Response Body   |
|:-----:|:----:|:----------------:|
|   1   | 200  | application/json |
|   2   | 400  | application/json |

+ Response example{1}:

| id  |       name        | sportKind | creationDate |
|:---:|:-----------------:|:---------:|:------------:|
|  2  | Manchester United | Football  |  1879-01-01  |

+ Response example{2}:

|         message          |          date           |
|:------------------------:|:-----------------------:|
| Team with id:1 not found | 2023-03-21T02:56:20.368 |

[Table of Contents](#ref13)

### <a name="ref5"></a>Put new data to the player by teamId

    PUT /sports/players/{playerId}

+ Description:

  Change player information. Don't create player, if not found by id


+ Parameters:

|   Name   |     Type      | Required |           Description           | Example |
|:--------:|:-------------:|:--------:|:-------------------------------:|:-------:|
| playerId | Integer(path) |   True   | Numeric ID of the player to get |    1    |

+ Request body:

| name  |  surname  | birthDate  |    role    |
|:-----:|:---------:|:----------:|:----------:|
| Ander | Astralaga | 2004-03-03 | Goalkeeper |

+ Responses:

| Index | Code |  Response Body   |
|:-----:|:----:|:----------------:|
|   1   | 200  | application/json |
|   2   | 404  | application/json |

+ Response example{1}:

| id  | name  |  surname  | birthDate  |    role    |
|:---:|:-----:|:---------:|:----------:|:----------:|
|  1  | Ander | Astralaga | 2004-03-03 | Goalkeeper |

+ Response example{2}:

|          message           |          date           |
|:--------------------------:|:-----------------------:|
| Player with id:1 not found | 2023-03-21T02:56:20.368 |

[Table of Contents](#ref13)

### <a name="ref6"></a>Delete a player by playerId

    DELETE /sports/players/{playerId}

+ Parameters:

|   Name   |     Type      | Required |           Description           | Example |
|:--------:|:-------------:|:--------:|:-------------------------------:|:-------:|
| playerId | Integer(path) |   True   | Numeric ID of the player to get |    1    |

+ Responses:

| Index | Code |  Response Body   |
|:-----:|:----:|:----------------:|
|   1   | 204  |      empty       |
|   2   | 404  | application/json |

+ Response example{2}:

|          message           |          date           |
|:--------------------------:|:-----------------------:|
| Player with id:1 not found | 2023-03-21T02:56:20.368 |

[Table of Contents](#ref13)
