#Base image
FROM centos:latest

#Set maintainer - Optional
LABEL maintainer "Bana Solutions"

#Create working directory
RUN mkdir -p /apps/prod/ck001

#Set base directory to the current module
WORKDIR /apps/prod/ck001

# Install tools required for project, fortran compiler
RUN yum install -y gcc-gfortran gdb make
RUN yum update -y


#Build the ck010 code and copy it to the working directory
COPY . .


#Set volume to a specific directory to maintain data consistency
VOLUME /apps/prod/ck001:/apps/prod/ck001

#Set of command to be executed withing the container
#CMD []

#Tell docker what port to expose at browser
EXPOSE 5000
