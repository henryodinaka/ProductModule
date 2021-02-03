# ProductModule

This is a microservice for handling requests related to products attempts to make call to Order service.


#SetUp
Ensure the active propertied is staging otherwise you have to setup postgres db on the application-dev.properties file as follows


# DOCKER
The app docker file can be found at the root of the project

#CI/CD
There is a github actions workflow located at .github folder at the root of the project. It builds, tests , deploy to Heroku whenever a new commit is made to the master branch

#URL TO Swagger page

https://inits-product-service.herokuapp.com/product/swagger-ui.html