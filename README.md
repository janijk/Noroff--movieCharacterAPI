# Movie character API
## Table of contents
* [Introduction](#introduction)
* [Features](#features)
* [Technologies](#technologies)
* [Usage](#usage)
* [Authors](#authors)
* [Sources](#sources)

## Introduction
The goal of this project was to create API for a database and deploy it in the
cloud. API is documented with Swagger and CI/CD pipelines are used to
automatically deploy production version of the app.

### Continuous Integration/Deployment

App uses GitLab CI pipelines to run through two stages build and deploy.
When committing to protected branch build stage is triggered and a docker
image of the app is built and pushed to GitLab's container registry. After
build stage is successfully executed deploy stage can be triggered manually.
Deploy stage then automatically pulls the earlier created image and pushes
it to Heroku's registry, after which Heroku redeploys app from the new image.

## Features
-  
-
## Technologies
- IntelliJ
- Java 17
- Spring Boot
- PostgreSQL
- JPA
- Gradle
- GitLab CI
- Docker
- Heroku
- Swagger/OpenAPI
## Usage
API is documented with Swagger and it's specifications can be found [Here](https://motionpicture-api.herokuapp.com/swagger-ui/index.html#/)

## Authors
[@ Jani](https://gitlab.com/janijk) <br />
[@ Emil](https://gitlab.com/emilcalonius)
## Sources
Project was an assignment done during education program created by
Noroff Education
