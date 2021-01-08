pipeline {
    agent any
   environment {
      colorPrint='black'
      PROJECT = get_project()
      APP_NAME = get_appName()
      CLUSTER = get_cluster()
      CLUSTER_ZONE = get_clusterZone()
      FE_SVC_NAME = "${APP_NAME}-frontend"
      IMAGE_TAG = "gcr.io/${PROJECT}/${APP_NAME}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
      JENKINS_CRED = "${PROJECT}"
   }
    stages {
        stage('Build') {
            steps {           
                   echo 'APIOD service working successfully'     
                   echo env.PROJECT
                   echo env.APP_NAME
                   echo env.CLUSTER
                   echo env.FE_SVC_NAME
                
            }
        }      
        
    }
}
def get_project() {
        load "./variables.groovy"
        return "${PROJECT}"   
}
def get_appName() {
        load "./variables.groovy"
        return "${APP_NAME}"   
}
def get_cluster() {
        load "./variables.groovy"
        return "${CLUSTER}"   
}
def get_clusterZone() {
        load "./variables.groovy"
        return "${CLUSTER_ZONE}"   
}
