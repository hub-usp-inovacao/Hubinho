FROM gradle:7.4.2-jdk17

WORKDIR /usr/src/app

COPY ./ ./

RUN gradle shadowJar

CMD java -jar ./build/libs/*-all.jar