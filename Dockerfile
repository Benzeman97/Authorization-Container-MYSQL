FROM openjdk:11
COPY ./build/libs/identity-server.jar identity-server.jar
EXPOSE 9091
CMD ["java","-jar","identity-server.jar"]