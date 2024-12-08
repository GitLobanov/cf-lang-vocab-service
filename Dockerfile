# Строим JAR файл в образе Maven
FROM maven:3.8.5-openjdk-17-slim AS jar-builder
WORKDIR /opt/build

# Копируем файл POM и устанавливаем зависимости
COPY pom.xml .
RUN mvn dependency:go-offline

# Копируем исходный код и собираем приложение
COPY /src /opt/build/src/
RUN mvn clean install -Dmaven.test.skip

# Собираем компактный JDK с помощью jlink для уменьшения размера
FROM eclipse-temurin:17 AS compact-jar-builder
WORKDIR /opt/build
COPY --from=jar-builder /opt/build/target/*.jar ./application.jar

RUN java -Djarmode=layertools -jar application.jar extract

RUN $JAVA_HOME/bin/jlink \
         --add-modules `jdeps --ignore-missing-deps -q -recursive --multi-release 17 --print-module-deps -cp 'dependencies/BOOT-INF/lib/*':'snapshot-dependencies/BOOT-INF/lib/*' application.jar` \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output jdk

# Финальный образ на основе минимального Debian
FROM debian:buster-slim

ARG BUILD_PATH=/opt/build
ENV JAVA_HOME=/opt/jdk
ENV PATH "${JAVA_HOME}/bin:${PATH}"

# Создаем пользователя и группу для безопасности
RUN groupadd --gid 1000 spring-app \
  && useradd --uid 1000 --gid spring-app --shell /bin/bash --create-home spring-app

USER spring-app:spring-app
WORKDIR /opt/workspace

# Копируем необходимые файлы из промежуточных образов
COPY --from=compact-jar-builder $BUILD_PATH/jdk $JAVA_HOME
COPY --from=compact-jar-builder $BUILD_PATH/spring-boot-loader/ ./
COPY --from=compact-jar-builder $BUILD_PATH/dependencies/ ./
COPY --from=compact-jar-builder $BUILD_PATH/snapshot-dependencies/ ./
COPY --from=compact-jar-builder $BUILD_PATH/application/ ./

# Указываем точку входа
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
