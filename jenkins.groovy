


node {
   def mvnHome
   git changelog: false, credentialsId: '7ca6d9cb-99fc-4770-886c-18f2947b67ec', url: 'https://github.com/whaenel/JenkinsTest.git'
   def Parms=load ("extparms.groovy")
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
        git changelog: false, credentialsId: '7ca6d9cb-99fc-4770-886c-18f2947b67ec', url: 'https://github.com/whaenel/JenkinsTest.git'
        //def Parms=load( "extparms.groovy")
        //def externalMethod = load("externalMethod.groovy")

    // Call the method we defined in externalMethod.
        Parms.lookAtThis("Steve")
        //print Parms.'extFile'
        library identifier: 'constants', retriever: modernSCM([$class: 'GitSCMSource', credentialsId: '7ca6d9cb-99fc-4770-886c-18f2947b67ec', id: '499b2680-d143-45eb-801b-5b4d0ab507a4', remote: 'https://github.com/whaenel/JenkinsTest.git', traits: [[$class: 'BranchDiscoveryTrait']]])

        print Constants.extFile
   }
   stage('Build') {
      // Run the maven build
      print Parms
      print Parms.extFile
   }
   stage('Results') {
        print "sucessful"
   }
}
