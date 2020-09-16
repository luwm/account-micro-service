FROM java:8-jdk-alpine

COPY ./target/account-1.0.0.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch account-1.0.0.jar'

ENTRYPOINT ["java", "-jar", "account-1.0.0.jar"]