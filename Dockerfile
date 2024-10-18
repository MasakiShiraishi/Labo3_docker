FROM bitnami/wildfly:latest
COPY target/Laboration2-1.0-SNAPSHOT.war /opt/bitnami/wildfly/standalone/deployments/

