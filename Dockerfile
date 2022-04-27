FROM openjdk:11

# install chrome
RUN apt-get update -y && \
    apt-get install -y gconf-service libgbm1 libasound2 libatk1.0-0 libcairo2 libcups2 libfontconfig1 libgdk-pixbuf2.0-0 libgtk-3-0 libnspr4 libpango-1.0-0 libxss1 fonts-liberation libnss3 lsb-release xdg-utils && \
    wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    dpkg -i google-chrome-stable_current_amd64.deb; apt-get -fy install && \
    google-chrome-stable -version

WORKDIR /opt/tests

#init gradle
COPY build.gradle .
COPY gradle.properties .
COPY gradlew .
COPY gradle ./gradle
COPY src/main/java/com/practis/TestApplication.java ./src/main/java/com/practis/TestApplication.java

RUN ./gradlew test --no-watch-fs --refresh-dependencies

ENTRYPOINT ./gradlew test \
          --tests com.practis.selenide.LoginTest \
          --info --no-watch-fs