# expApp-tests
Simple automatic tests for simple web application.

## Run demo app on localhost

pull image from dockerhub (https://hub.docker.com/r/olakatosz/exp-app-image)

`docker pull olakatosz/exp-app-image`

run container with app:

`docker run -d --rm --name=ExpApp -p 4200:4200 olakatosz/exp-app-image`
