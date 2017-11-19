

def extFile="comes form extern"
node {
   def mvnHome
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
