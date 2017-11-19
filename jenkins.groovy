

def extFile="comes form extern"
node {
   def mvnHome
   git changelog: false, credentialsId: '7ca6d9cb-99fc-4770-886c-18f2947b67ec', url: 'https://github.com/whaenel/JenkinsTest.git'
   Parms=load "extparms.groovy"
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/whaenel/JenkinsTest.git'
      
   }
   stage('Build') {
      // Run the maven build
      print Parms.extFile
   }
   stage('Results') {
        print "sucessful"
   }
}
