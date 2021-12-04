#!/bin/bash
# Bash wrapper for starting a docker container

generic(){
  docker run -it --name generic -p 8889:8889 --net my-web-net -v /apps/carolyn/prod:/app/prod -d generic
}
