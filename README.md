# hotel-spring - Java - Spring project
REST API Hotel using Java and spring framework - web,jpa-data,lombok,h2,devtools

               ███╗░░░███╗░█████╗░███╗░░██╗██╗░░░██╗███████╗██╗░░░░░
               ████╗░████║██╔══██╗████╗░██║██║░░░██║██╔════╝██║░░░░░
               ██╔████╔██║███████║██╔██╗██║██║░░░██║█████╗░░██║░░░░░
               ██║╚██╔╝██║██╔══██║██║╚████║██║░░░██║██╔══╝░░██║░░░░░
               ██║░╚═╝░██║██║░░██║██║░╚███║╚██████╔╝███████╗███████╗
               ╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝░╚═════╝░╚══════╝╚══════╝

## Description

Generate with Spring CLI.
```
    spring init -d=devtools,web,lombok,data-jpa,h2,devtools --build=maven --groupId=com.co.manuel --java-version=21 --name=hotel-spring --type=maven-project hotel-spring
```

This app is to practice spring project in java and other tech stack.


To run the project (or see the [Docker section](#DOCKER) in this file):
Run with HOT reload (require devtools) -->
```
    SERVER_PORT=4000 mvn spring-boot:run
```
Run with maven flat jar -->
```
    SERVER_PORT=4000 java -jar target/hotel-spring-0.0.1-SNAPSHOT.jar
```

From console run: curl http://localhost:4000/api/v1/guest

To run the project in docker [Read this section](#DOCKER)

## TESTING

For test the end points, use the file in the route with kulala:
.src/test/java/com/co/manuel/hotel_spring/controllers/hotelRequest.http

## SEO

N/A

## DOCKER

Use the Dockerfile, this app run in the internal port 5174, use:

1. docker build -t manuelarias/react-ts-list:v1 .
2. docker run -dp 5555:5174 --name react-ts-list-app -v /media/manuel/Datos/mgallegoa/conceptsProbes/react-ts-list-app/src:/app/react-ts-list-app/src manuelarias/react-ts-list:v1
3. docker exec -it react-ts-list-app bash

## DOCKER - Compose

- Development service: docker-compose up -d dev-react-ts-list-app
- Production build service: docker-compose up -d prod-build-react-ts-list-app --build
- Production run service: docker-compose up -d prod-run-react-ts-list-app

Note: use --build if previously exist the image, otherwise (the image don't exist) the parameter is not required

## HTTP and HTTPS

To view working correctly, it is necessary to run over HTTPS secure protocol.

This is necessary for the crypto.randomUUID() method, the method is accessible for the browser only in https session.

For testing in a Play With Docker page or Oracle Cloud, you can use cloudflare (WARP).

# Run in Play With Docker PWD:

docker run -dp 8080:80 --name react-ts-list-app manuelarias/react-ts-list:v2

# Instructions to install WARP (cloudfare CLI) in an Alpine Linux server (Play With Docker use it):

1. Download the Binary:
   wget https://github.com/cloudflare/cloudflared/releases/latest/download/cloudflared-linux-amd64 -O /usr/local/bin/cloudflared
2. Set Execute Permissions:
   chmod +x /usr/local/bin/cloudflared
3. Verify Installation:
   cloudflared -v
4. Create the Cloudflared tunel:
   cloudflared tunnel --url http://localhost:8080
   (This give a page similar to https://lauren-tones-incorrect-sonic.trycloudflare.com/)

# Instructions to install Oracle Command Infrastructure CLI in a machine running Alpine Linux:

1. Ensure your /etc/apk/repositories file includes the community repository. Add the following line if it's missing:
   http://dl-cdn.alpinelinux.org/alpine/v3.20/community
2. apk update
3. apk add oci-cli
4. oci -v

