# AisProducer

# data
```
$ \
 mkdir data &&\
 wget https://coast.noaa.gov/htdata/CMSP/AISDataHandler/2022/AIS_2022_06_30.zip &&\
 unzip AIS_2022_06_30.zip
```

# Usage

## local
```
$ mvn spring-boot:run  -Drun.arguments="--spring.kafka.bootstrap-servers: localhost:9092 --spring.kafka.properties.schema.registry.url=http://localhost:8081"
```

## remote
```
$ \
 SPRING_KAFKA_BOOTSTRAP_SERVERS=192.168.1.2:9092 \
 SPRING_KAFKA_PROPERTIES_SCHEMA_REGISTRY_URL=http://192.168.1.2:8081 \
 mvn spring-boot:run
```

# developnent

## phases
```
$ mvn fr.jcgay.maven.plugins:buildplan-maven-plugin:list-phas

[INFO] Build Plan for demo:
process-resources -----------------------------------------------------
    + maven-resources-plugin   | default-resources     | resources
compile ---------------------------------------------------------------
    + maven-compiler-plugin    | default-compile       | compile
process-test-resources ------------------------------------------------
    + maven-resources-plugin   | default-testResources | testResources
test-compile ----------------------------------------------------------
    + maven-compiler-plugin    | default-testCompile   | testCompile
test ------------------------------------------------------------------
    + maven-surefire-plugin    | default-test          | test
package ---------------------------------------------------------------
    + maven-jar-plugin         | default-jar           | jar
    + spring-boot-maven-plugin | repackage             | repackage
install ---------------------------------------------------------------
    + maven-install-plugin     | default-install       | install
deploy ----------------------------------------------------------------
    + maven-deploy-plugin      | default-deploy        | deploy
```

## spring-boot
```
$ mvn help:describe -e -Dplugin=spring-boot

Name: Spring Boot Maven Plugin
Description: (no description available)
Group Id: org.springframework.boot
Artifact Id: spring-boot-maven-plugin
Version: 2.7.5
Goal Prefix: spring-boot

This plugin has 7 goals:

spring-boot:build-image
  Description: Package an application into an OCI image using a buildpack.

spring-boot:build-info
  Description: Generate a build-info.properties file based on the content of
    the current MavenProject.

spring-boot:help
  Description: Display help information on spring-boot-maven-plugin.
    Call mvn spring-boot:help -Ddetail=true -Dgoal=<goal-name> to display
    parameter details.

spring-boot:repackage
  Description: Repackage existing JAR and WAR archives so that they can be
    executed from the command line using java -jar. With layout=NONE can also
    be used simply to package a JAR with nested dependencies (and no main
    class, so not executable).

spring-boot:run
  Description: Run an application in place.

spring-boot:start
  Description: Start a spring application. Contrary to the run goal, this
    does not block and allows other goals to operate on the application. This
    goal is typically used in integration test scenario where the application
    is started before a test suite and stopped after.

spring-boot:stop
  Description: Stop an application that has been started by the 'start' goal.
    Typically invoked once a test suite has completed.

```
