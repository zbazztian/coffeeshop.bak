FROM --platform=linux/amd64 maven:3-openjdk-11
RUN mkdir /usr/src/project
COPY target/coffeeshop.jar /usr/src/project/
WORKDIR /usr/src/project
CMD java -jar coffeeshop.jar
