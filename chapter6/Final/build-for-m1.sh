#! /bin/bash
mvn clean package
docker build -t jhcode33/configserver:0.0.1-SNAPSHOT --build-arg JAR_FILE=target/configserver-0.0.1-SNAPSHOT.jar ./configserver
docker build -t jhcode33/eurekaserver:0.0.1-SNAPSHOT --build-arg JAR_FILE=target/eurekaserver-0.0.1-SNAPSHOT.jar ./eurekaserver
docker build -t jhcode33/licensing-service:0.0.3-SNAPSHOT --build-arg JAR_FILE=target/licensing-service-0.0.3-SNAPSHOT.jar ./licensing-service
docker build -t jhcode33/organization-service:0.0.1-SNAPSHOT --build-arg JAR_FILE=target/organization-service-0.0.1-SNAPSHOT.jar ./organization-service