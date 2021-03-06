# To build, run the following command from the top level project directory:
#
# docker build -f Dockerfile .
# docker build -t mehmet2aktas/team-course:latest  -f Dockerfile .

FROM adoptopenjdk:8-jre-hotspot as builder
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk:8-jre-hotspot
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
