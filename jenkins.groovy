

def extFile="comes form extern"
node {
   def mvnHome
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/whaenel/JenkinsTest.git'
      
   }
   stage('Build') {
      // Run the maven build
      print extFile
   }
   stage('Results') {
        print sucessful
   }
}
