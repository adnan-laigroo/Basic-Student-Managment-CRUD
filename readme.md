#### • Download Docker for windows from below URL :
     https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe

##### Copy paste the entire content of Windows folder in the root location of your project.

#### • Run below mentioned command from powershell (With Administrator Access) to make powershell script executable.
     Set-ExecutionPolicy RemoteSigned

#### • Now execute below command to initiate the setup : 
     docker compose -f Setup.yaml up -d --build

##### Once the above command is completed, please validate the Sonarqube URL if login prompt has appeared or not :
##### http://localhost:9000

#### • Once Sonarqube setup is complete, run the below command to configure sonarqube :
     docker compose -f Config.yaml up -d --build

#### Sonarqube username and password
     username : admin
     password : admin@1234

##### Properties related to project are in sonar-project.properties
##### Modify the sonar-project.properties file and write your Name and employee id in the sonar.projectkey value.

#### • Post completion of above step, run the below mentioned powershell script in powershell terminal. This will Build, test, run sonar scanner and run api test cases in your code :
     .\Scan.ps1

##### Java Project Port in .env

### • To stop all the containers, run the below command :
     docker kill $(docker ps -q)

#### • To remove all the containers, run the below command :
     docker rm $(docker ps -aq)

