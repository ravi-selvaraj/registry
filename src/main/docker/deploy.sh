#!/bin/sh

cd /idhayangal/registry
git pull
docker exec -t maven sh -c "cd /apps/registry && mvn clean install"
docker exec -t tomcat sh -c "rm -rf /usr/local/tomcat/webapps/registry* && cp /apps/registry/target/registry.war /usr/local/tomcat/webapps && ls -l /usr/local/tomcat/webapps"
docker restart tomcat
