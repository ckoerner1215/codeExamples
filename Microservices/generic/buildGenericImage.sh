#!/bin/bash


buildImage(){
docker stop generic
docker rm generic
docker rmi generic

rm -rf target
mvn clean install

docker build -t generic .
}
