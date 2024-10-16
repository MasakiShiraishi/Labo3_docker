FROM jboss/wildfly:latest AS test
EXPOSE 8080
COPY target/Laboration2-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]