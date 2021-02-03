# ProductModule

This is a microservice for handling requests related to products

#SetUp

Requires Postgres DB
Update the properties files and required

# DOCKER
The app docker file can be found at the root of the project

#CI/CD
There is a github actions workflow located at .github folder at the root of the project. It builds, tests (where are unit or integration tests), deploy to Heroku whenever a new commit is made on the master branch
