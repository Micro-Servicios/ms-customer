FROM azul/zulu-openjdk:11.0.10
RUN apt update

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

#COPY target/ms-customer-0.0.1.jar ms-customer-0.0.1.jar
#ENV SPRING_PROFILE local
ENV SERVER_PORT 6945
ENV EUREKA_URI http://localhost:8762/eureka

ENTRYPOINT ["java", \
            "-cp", \
            "app:app/lib/*", \
            "arquitectura.software.mscustomer.MsCustomerApplication"]
            #"-Dspring.profiles.active=${SPRING_PROFILE}", \
            #"-cd",  \
            #"/ms-customer-0.0.1.jar"]