#!/bin/sh

cd /idhayangal/regisry
git pull
docker exec -t maven sh -c "cd /apps/rolodex && mvn clean install"
docker exec -t tomcat sh -c "rm -rf /usr/local/tomcat/webapps/registry* && cp /apps/rolodex/target/registry.war /usr/local/tomcat/webapps && ls -l /usr/local/tomcat/webapps"
docker restart tomcat
