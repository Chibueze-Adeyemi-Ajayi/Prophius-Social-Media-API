```markdown
# Eureka Server (SERVICE DISCOVERY)

This is a basic guide on how to set up a Eureka Server using Spring Cloud, which listens on PORT 5000.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Accessing the Eureka Dashboard](#accessing-the-eureka-dashboard)
- [Stopping the Server](#stopping-the-server)
- [Custom Configuration](#custom-configuration)

## Prerequisites

Before you begin, make sure you have the following:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) version 8 or higher installed on your machine.
- [Apache Maven](https://maven.apache.org/download.cgi) for building the project.
- A basic understanding of how Eureka and Spring Cloud work.

## Installation

1. Clone this repository:

   ```bash
   git clone <repository_url>
   ```

2. Change to the project directory:

   ```bash
   cd eureka-server
   ```

3. Build the project using Maven:

   ```bash
   mvn clean install
   ```

4. Run the Eureka Server:

   ```bash
   java -jar target/eureka-server-1.0.jar
   ```

The Eureka Server will start and listen on PORT 5000.

## Usage

To use the Eureka Server in your microservices architecture, configure your microservices to register with it. When they do, they become discoverable by other services.

Here's a sample `bootstrap.properties` file for a microservice using Spring Cloud:

```properties
spring.application.name=your-service-name
server.port=your-service-port
eureka.client.serviceUrl.defaultZone=http://localhost:5000/eureka
```

In the `bootstrap.properties`, `your-service-name` is your service's name, `your-service-port` is the port your service runs on, and `http://localhost:5000/eureka` is the URL of your Eureka Server.

## Accessing the Eureka Dashboard

You can access the Eureka Server's dashboard by opening your web browser and navigating to:

```
http://localhost:5000
```

You should see the Eureka dashboard, which displays the registered services.

## Stopping the Server

To stop the Eureka Server, simply press `Ctrl + C` in the terminal where it's running.

## Custom Configuration

For custom configuration, you can edit the `application.properties` file in the project. You can change the server's port and other settings as needed.

That's it! You now have a Eureka Server running on PORT 5000.

For more advanced usage and production deployment, consult the Spring Cloud documentation.

Happy microservices development!
```

This `README.md` provides a basic guide on how to install and use a Eureka Server listening on PORT 5000. Be sure to replace `<repository_url>` with the actual URL of your repository.

You can further customize and enhance this documentation to suit your specific project's needs and requirements.