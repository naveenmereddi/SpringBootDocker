This application is enabled with swagger-ui. To access the swagger-ui, navigate to

http://localhost:8080/swagger-ui.html

once the application has initialized.

This application is enabled with Fabric8 Maven Plugin, which automates the docker build/publish process.

To take advantage of that, simply run

mvn clean package docker:build to build the image

Once the image is built, you can run the application from within docker as follows

docker run -d -p 8080:8080 todo-list-app-docker
