# Nodejs Base image
FROM node

WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH

# install and app dependencies
COPY package.json /app/package.json

RUN npm install -g npm@7.5.6
RUN npm install
RUN npm update
RUN npm install -g @angular/cli
RUN npm install bootstrap

# add app
COPY . /app

#VOLUME /app
# start app
CMD ng serve --host 0.0.0.0
