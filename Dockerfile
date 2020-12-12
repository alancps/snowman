FROM openjdk:8

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /opt/spring_boot

COPY /target/snowman*.jar snowman-api.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 8081

CMD java -jar snowman-api.jar --spring.profiles.active=${PROFILE}