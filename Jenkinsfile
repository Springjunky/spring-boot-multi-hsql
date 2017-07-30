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
    }  //options
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
        } // stage Test
       
    
   post { 
        success {
	  echo 'OK '
  	}
	failure {
	  echo 'Fail'
  	}

} // Pipeline 
