FROM payara/micro:6.2024.5-jdk21
COPY target/jakarta-jumpstart-0.1-SNAPSHOT.war $DEPLOY_DIR
