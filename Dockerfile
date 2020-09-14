FROM openjdk:8
ADD build/libs/ME-0.0.1.jar ME-0.0.1.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","ME-0.0.1.jar"]