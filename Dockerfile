#--build stage--
FROM maven:3.9-eclipse-temurin-21-alpine AS build

WORKDIR /authentication-app

COPY pom.xml .

COPY /src ./src

RUN mvn clean package -DskipTests

RUN jlink \
    --add-modules java.se,jdk.crypto.ec,jdk.unsupported,jdk.management,jdk.naming.dns,jdk.zipfs \
    --bind-services \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --compress=2 \
    --output /opt/java-minimal
#--run stage--
FROM alpine:3.22 AS runtime

RUN apk add --no-cache libstdc++ zlib

ENV JAVA_HOME=/opt/java-minimal
ENV PATH="${JAVA_HOME}/bin:${PATH}"

WORKDIR /authentication-app

COPY --from=build /opt/java-minimal /opt/java-minimal
COPY --from=build /authentication-app/target/*.jar authentication.jar

ENTRYPOINT ["java", "-jar", "authentication.jar"]