class Configuration{
    static String gitRepo = 'https://github.com/tuanmhoang/groovy-build-tools.git'
    static String qaJobExactName = 'qaJob'
    static String prodJobExactName = 'prodJob'
}

def printJobName() {
    sh "echo 'Invoking job ${params.nameOfJob}'"
}

def cloneCodeFromRepo() {
    sh "echo '<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Cloning code >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>'"
    git Configuration.gitRepo
}

def runWithSelectedBuildTool() {
    sh "echo '<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Building project with ${params.selectedBuildTool} >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>'"
    dir('builders-all'){
        sh "pwd"
        if (params.selectedBuildTool == 'Maven') {
            buildProjectUsingMaven()
        } else {
            buildPrjectUsingGradle()
        }
    }   
}

def buildProjectUsingMaven(){
    echo '-------- Building using Maven --------'
    sh "mvn clean package -DskipTests"
}

def buildPrjectUsingGradle(){
    echo '-------- Building using Gradle --------'
    sh "gradle clean assemble"
    // can use gradle clean build for unit test
}

def testWithSelectedBuildTool() {
    sh "echo '<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Running test with ${params.selectedBuildTool} >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>'"
    dir('builders-all'){
        sh "pwd"
        if (params.selectedBuildTool == 'Maven') {
            testProjectUsingMaven()
        } else {
            testPrjectUsingGradle()
        }
    }   
}

def testProjectUsingMaven(){
    sh "echo '-------- Building using Maven --------'"
    sh "mvn test"
}

def testPrjectUsingGradle(){
    sh "echo '-------- Building using Gradle --------'"
    sh "gradle test"
}

def runQaProcess(){
    sh "echo '<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Running QA process >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>'"
    if( params.separatedJob == 'Yes'){
        build job: Configuration.qaJobExactName 
    } else {
        sh "echo '-------- Running QA process --------'"       
    }   
}

def runProdProcess(){
    sh "echo '<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Running Production process >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>'"
    if( params.separatedJob == 'Yes'){
        build job: Configuration.prodJobExactName 
    } else {
         sh "echo '-------- Running Production process --------'"            
    }  
}

pipeline {
    agent any

    parameters {
        choice(name: 'selectedBuildTool', choices: ['Maven', 'Gradle'], description: 'Select build tool')
        string(name: 'nameOfJob', defaultValue: 'Builder Application', description: 'Name of the job. Default is BuilderApp')
        choice(name: 'separatedJob', choices: ['No', 'Yes'], description: 'Call separated job for QA and Prod or not')
    }

    tools {        
        maven 'M3'
        gradle 'G7'
    }

    stages {
        stage ('Preparation') {
            steps {
                printJobName()
                cloneCodeFromRepo()
            }
        }
        stage('Build') {
            steps {
                runWithSelectedBuildTool()
            }
        }
        stage('Test') {
            steps {
                testWithSelectedBuildTool()
            }
        }        
        stage('QA Steps') {
            steps {
                runQaProcess()
            }
        }
        stage('Production Steps') {
            steps {
                input message: "Approve to Production?"
                runProdProcess()
            }
        } 
    }
}
