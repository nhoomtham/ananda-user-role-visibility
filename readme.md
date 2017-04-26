## Using MySQL in Spring Boot via Spring Data JPA and Hibernate

See here for more informations:
http://blog.netgloo.com/2014/10/27/using-mysql-in-spring-boot-via-spring-data-jpa-and-hibernate/

### Build and run

#### Configurations

Open the `application.properties` file and set your own configurations. and specific environment profile with `application-${profile}.properties`

#### Prerequisites

- Java 8
- Maven > 3.0

#### From terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run
    
To activate specific dev environment:

    $ mvn spring-boot:run -Dspring.profiles.active=dev

or staging environment:

    $ mvn spring-boot:run -Dspring.profiles.active=stg
    

#### From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.


### Usage

- Run the application and go on http://localhost:8080/
	
	http://localhost:8080/userroles/{roleName}/{objectName}
	
: roleName= LD,LS,BD,DD,MI,RSK ; objectName= actions, columns, formFields

example usage >> 
	
	http://localhost:8080/userroles/BD/columns


