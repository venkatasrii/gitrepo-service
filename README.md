# gitrepo-service
gitrepo-service
Software Required
Java8 JDK or Java10 JDK (this project was built with jdk8)
Apache Maven
Windows
mvnw.cmd clean install
mvnw.cmd spring-boot:run
(we need modifiy the pom if any build issue wth docker related config)

API Spec: endpoints
 User repository info : localhost:8080/projects/{user}
 User repository activities: localhost:8080/projects/{user}/{repo}
