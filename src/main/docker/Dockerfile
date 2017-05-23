FROM openjdk

VOLUME /tmp

ADD maven/todo-list-app-0.1.0.jar myapp.jar

RUN sh -c 'touch /myapp.jar'

ENTRYPOINT ["java","-jar","/myapp.jar"]

