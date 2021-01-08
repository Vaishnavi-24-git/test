pipeline {
    agent any
   environment {
      colorPrint='black'
      test = get_first()
   }
    stages {
        stage('Build') {
            steps {           
                   echo 'APIOD service working successfully'     
                   echo env.test
            }
        }      
        
    }
}
def get_first() {
   
        load "./variables.groovy"
        return "${env.env_var1}"
    
}
