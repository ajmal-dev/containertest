FROM openjdk:8-jre-slim
WORKDIR /usr/share/tag
# Add the jar with all the dependencies
ADD  target/sampleTest-1SNAPSHOT.jar sampleTest-1SNAPSHOT.jar
ADD  target/libs libs
# Add the suite xmls
ADD testng.xml testng.xml

# Command line to execute the test
# Expects below ennvironment variables
# BROWSER = chrome / firefox
# MODULE  = order-module / search-module
# SELENIUM_HUB = selenium hub hostname / ipaddress

ENTRYPOINT java -cp sampleTest-1SNAPSHOT.jar:libs/* org.testng.TestNG testng.xml