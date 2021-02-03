FROM maven:3.6.1-jdk-11-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
ENV REDIS_URL localhost
ENV PORT 9000
ENV DB_URL 127.0.0.1
ENV DB_USER wallet-mgt
ENV DB_PASS minwalletDb1#
ENV DB_NAME wallet-mgt
COPY src /workspace/src
RUN mvn -B clean package --file pom.xml -DskipTests

FROM openjdk:14-slim
COPY --from=build /workspace/target/*product.jar product.jar
EXPOSE 80
VOLUME /usr/src
ENTRYPOINT ["java","-jar","product.jar"]