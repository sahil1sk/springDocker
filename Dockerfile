FROM java:16
VOLUME /tmp
EXPOSE 10555
ADD Demo_SpringREST_DELETE-0.0.1-SNAPSHOT.jar Demo_SpringREST_DELETE-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Demo_SpringREST_DELETE-0.0.1-SNAPSHOT.jar"]