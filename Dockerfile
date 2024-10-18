FROM bitnami/wildfly:latest AS test
COPY target/Laboration2-1.0-SNAPSHOT.war /opt/bitnami/wildfly/standalone/deployments/

