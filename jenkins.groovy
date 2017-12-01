
import com.urbancode.ud.client.ApplicationClient

node {
   def mvnHome
   git changelog: false, credentialsId: '7ca6d9cb-99fc-4770-886c-18f2947b67ec', url: 'https://github.com/whaenel/JenkinsTest.git'
   def Parms=load ("extparms.groovy")
   def jHelper = load ("src/com/ibm/swf/udeploy/JenkinsHelper.groovy")
   stage('Preparation') { // for display purposes
        Parms.lookAtThis("Steve")
        print env.extFile
   }
   stage('Build') {
      // Run the maven build
      build job: 'StartRemoteTestDummy', parameters: [string(name: 'User', value: 'Sarah')]
   }
   stage('buid with curl') {
        helper=jHelper.JenkinsHelper.getHelper("walter",JPASS,"http://localhost:8080/")
        params = [ "user":"Sarah"]
        helper.startJob("StartTestDummy", params, true )
        print "sucessful"
   }
}
