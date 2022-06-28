FROM openjdk:11
#RUN mvn clean install
ADD target/*.jar excelUploadService.jar
EXPOSE 4500
ENTRYPOINT ["java", "-jar", "/excelUploadService.jar"]