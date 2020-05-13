FROM maven:3.6.3-openjdk-8-slim AS build

RUN mkdir -p /app
WORKDIR /app

# copy project
COPY pom.xml /app
COPY src /app/src

# run maven build
RUN mvn install

FROM openjdk:8-jdk-alpine

# copy build from build-stage container
COPY --from=build /app/target/sentiment-analysis-web-0.0.1-SNAPSHOT.jar /app/sentiment-analysis-web-0.0.1-SNAPSHOT.jar

# Environment Variable that defines the endpoint of sentiment-analysis python api.
ENV SA_LOGIC_API_URL http://sa-logic
ENV MYSQL_DATABASE sa_webapp
ENV MYSQL_USER sa_user
ENV MYSQL_PASSWORD sa_password

EXPOSE 8080

CMD ["java", "-jar", "/app/sentiment-analysis-web-0.0.1-SNAPSHOT.jar", "--sa.logic.api.url=${SA_LOGIC_API_URL}"]
