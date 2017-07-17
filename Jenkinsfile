pipeline {
    agent any
    // The options directive is for configuration that applies to the whole job.
    options {
      //  gitLabConnection('Git-t')
      //  gitlabCommitStatus(name: 'jenkins')
      // we don't fill up our storage!
      buildDiscarder(logRotator(numToKeepStr:'10'))
      // And we'd really like to be sure that this build doesn't hang forever, so
      // let's time it out after an hour.
      timeout(time: 5, unit: 'MINUTES')
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building with maven '
                sh '''
                	mvn compile
                '''
                
            }
        } // stage build
        stage('Test') {
            steps {
                echo 'Testing with maven '
                sh '''
                	mvn test
                '''
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
            
        } // stage Test
        stage ('Deploy')
        {
            steps {
            
            script   {
                   timeout(time: 3, unit: 'SECONDS') {
            
                    input message: 'Soll ich das Ding nun auch mit Sonar analysieren  ?', ok: 'Yes'
                    }
               }
            }
        }
        
          stage("SonarQube analysis") {
          steps {
           
        	        withSonarQubeEnv('sonar5') {
        		     sh "env "
        		     sh "mvn -Dsonar.host.url=${SONAR_HOST_URL} sonar:sonar -DskipTests -Dsonar.verbose=true "
        	    }
        	  
            }// steps 
          } // stage
          
/* Geht nicht ssl problem ?
          stage("Quality Gate"){
        	  steps {
        	    script   {
                   timeout(time: 1, unit: 'MINUTES') {
                    def qg = waitForQualityGate()
                       if (qg.status != 'OK') {
                        error "Pipeline aborted due to quality gate failure: ${qg.status}"
                       } //if
                  } //script
               } //Timeout
            } //Steps
         } //Stage
    */

   }//stages 
    
   post { 
        success {
	  echo 'posting success to GitLab'
        //  updateGitlabCommitStatus(name: 'jenkins-build', state: 'success')
  	}
	failure {
	  echo 'postinng failure to GitLab'
         // updateGitlabCommitStatus(name: 'jenkins-build', state: 'failed')
  	}
   }    
    


} // Pipeline 
