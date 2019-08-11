# expApp-tests
Simple automatic tests with selenium for simple web application.

## Run demo app on localhost
pull image from dockerhub (https://hub.docker.com/r/olakatosz/exp-app-image)

`docker pull olakatosz/exp-app-image`

run container with app:

`docker run -d --rm --name=ExpApp -p 4200:4200 olakatosz/exp-app-image`

If you are not using docker, clone source repository from: https://github.com/Pasquda95/expApp-demo and follow instructions in README.md.

## Run tests on app
test project is build with Maven. All dependencies and plugins are already in pom.xml.
To setup working test environment, run docker container with app as mentioned in previous section and perform
`mvn clean install` to ensure that build plugin downloads needed dependencies and Chrome Driver binaries.
