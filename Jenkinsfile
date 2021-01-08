pipeline {
    agent any
   environment {
      colorPrint='black'
   }
    stages {
        stage('Build') {
            steps {           
                   echo 'APIOD service working successfully'     
                   echo env.colorPrint
            }
        }
       stage('Reading environment variable defined in groovy file') {
            steps {
                script {
                    load "./variables.groovy"
                    echo "${env.env_var1}"
                    echo "${env.env_var2}"
                }
            }
        }  
        
    }
}
