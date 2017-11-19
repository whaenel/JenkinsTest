


node {
   def mvnHome
   git changelog: false, credentialsId: '7ca6d9cb-99fc-4770-886c-18f2947b67ec', url: 'https://github.com/whaenel/JenkinsTest.git'
   def Parms=load ("extparms.groovy")
   stage('Preparation') { // for display purposes
        Parms.lookAtThis("Steve")
        print env.extFile
   }
   stage('Build') {
      // Run the maven build
      print env.extFile
      print Parms.extFile2()
   }
   stage('Results') {
        print "sucessful"
   }
}
