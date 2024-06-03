# CucumberWithTestNGUsingSeleniumJava

docker run -d --network=grid -e SE_EVENT_BUS_HOST=<host ip address> --shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 --name selenium-node selenium/node-chrome:latest

 docker run -d --network=grid -p 4442:4442 -p 4443:4443 -p 4444:4444 --name selenium-hub selenium/hub:latest