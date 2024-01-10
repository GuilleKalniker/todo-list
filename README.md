# Technical Test for Evolution Code

Todo list made with Java, Spring Boot, H2, Maven and JUnit 

## Developed By
* [Guillermo Kalniker](https://github.com/GuilleKalniker)

## Pre-requesites

To run the application, we'll need a small initial setup, which involves installing and configuring Java 17 and Maven. 
If you have any doubts on how to install these tools, you can refer to the official documentation at:
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Maven](https://maven.apache.org/install.html)

Later, you can validate that the configuration was successful by opening a command prompt (cmd) and executing the following commands:
* java --version
** You should see something like this:
![image](https://github.com/GuilleKalniker/todo-list/assets/63016011/d8695eb0-ff9c-4553-938e-63961e9ea997)
* mvn --version
** You should see something like this:
![image](https://github.com/GuilleKalniker/todo-list/assets/63016011/441139fa-708f-42ec-80c6-ffe9235fc608)

## Installation
To initialize the local repository:
```console
    git clone https://github.com/GuilleKalniker/todo-list.git
    cd todo-list
```

## Executing Tests
To run the application's tests, all you need to do is executing the command 
```console
   mvn test
```

## Excecuting the app

```console
   mvn clean package
   cd target
   java -jar jarFileName.jar
```

## Examples of use with Postman

You can test the endpoints with Postman by importing the file 'to-do list.postman_collection.json' located in root folder. 
