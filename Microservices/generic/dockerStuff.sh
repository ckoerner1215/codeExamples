#!/bin/bash

# Run as:
#   . dockerStuff.sh 
#   buildImage
#   generic

# Bash wrapper for building a docker image

buildImage(){
docker stop generic
docker rm generic
docker rmi generic

rm -rf target
mvn clean install

docker build -t generic .
}

# Bash wrapper for starting a docker container

generic(){
  docker run -it --name generic -p 8889:8889 --net my-web-net -v /apps/carolyn/prod:/app/prod -d generic
}

#  testing...
hello(){
  echo Hello!!!
}

