# Treasure Map Project

## Introduction
Within this project, I present my implementation of Carbon IT's technical test challenge : "La carte au trésor".

## How to execute the Project

1. Clone this repository to your local machine
2. Option 1 : Excute with IDE
   2.1 Open the project with your preferred IDE.
   2.2 Execute the Main Class located at src/main/java/youcef/treasureProject/Main.java
3. Option 2: mvn command line
   3.1 Get to the project root
   3.2 Execute the command "mvn clean install"
   3.3 Execute the command "mvn exec:java"

## How to test the application with your own data:

1. Option 1: Modify the content of the `input.txt` file directly in the resource directory located at "src/main/resources/input.txt" with your own data.
2. Option 2: Import your file into the `resources` directory and modify the line 15 of `Main.java` by replacing `"input.txt"` with your file name.

## Features
The following features have been developped in this project:

1. Developement of models classes: Adventurer, Position, Treasure map.
2. Manage all Adventurer movements and rotation.
3. Check if all adventurer movements adhere to the constraints of the treasure map fixed by the topic.
4. Read the file containing the treasure map initialization.
5. Write the result in an output file.
6. Implementation of unit tests covering the majority of cases.
   
