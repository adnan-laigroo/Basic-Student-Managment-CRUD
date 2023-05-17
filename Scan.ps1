mvn clean install
docker compose -f Scan.yaml up -d --build

docker run  --rm  --network net -e SONAR_HOST_URL="http://sonarqube:9000" -v "$(pwd):/usr/src" sonarsource/sonar-scanner-cli

java -jar api-test-framework-0.0.1-SNAPSHOT-jar-with-dependencies.jar