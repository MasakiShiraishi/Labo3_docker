FROM bitnami/wildfly:latest AS test

EXPOSE 8080
COPY target/Laboration2-1.0-SNAPSHOT.war /opt/bitnami/wildfly/standalone/deployments/
