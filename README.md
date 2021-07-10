# mutant 
## Stepts to run application:
  ### Option 1 (Windows):
    - Go to the project root on CMD
    - Run mvnw.cmd clean install
    - Go to target folder
    - Run application: java -jar meli-mutant-0.0.1-SNAPSHOT.jar
  ### Option 2 (Linux):
    - Go to the project root on terminal
    - Run mvnw clean install
    - Go to target folder
    - Run application: java -jar meli-mutant-0.0.1-SNAPSHOT.jar
  ### Option 3 (Linux or Windows):
    - Import the project on sts editor as a maven project.
    - Rigth click to the main class and run as spring boot app.
## Test Report
	- To check the test report after run the mvnw corresponding to the SO described in the run application section.
	- Go to target -> site -> jacoco and open index file in the browser.
## URL Application:
	http://mutants-env.eba-mjwrxsfv.us-east-2.elasticbeanstalk.com
