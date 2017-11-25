
import  com.ibm.swf.udeploy.AppHelper
import  com.ibm.swf.udeploy.JenkinsHelper

AppHelper helper = new AppHelper('admin', 'admin', 'https://josua.haenel.local:8443' );
JenkinsHelper myHelper= JenkinsHelper.getHelper('admin', 'admin', 'https://josua.haenel.local:8443' )
//def id = helper.createSnapshotOfEnvironment('hugo', 'bla bla', 'EnvTempApp2','MyAppFT')
//String id = helper.createVersion('FromCompTemplate', '1.0' )
helper.getVersionProperties('FromCompTemplate', '1.0')
//print id
def includes=['**/*'] as String[];
def excludes=[] as String[];
helper.addVersionFiles('FromCompTemplate', '1.0','TestData/version',includes,excludes,true)

def id = helper.getVersionId('FromCompTemplate', '1.0')
println "call returned id ${id}"

try {
    id = helper.getVersionId('FromCompTemplate', '1.2')
} catch(java.io.IOException ex) {
    println "cought exception with message >>${ex.message}<<"
    if (ex.message.indexOf("400") == 0 ) {
        id = ex.message
    } else {
        println "throwing a new one "
        throw new java.io.IOException(ex.message)
    }
}
println "call returned id ${id}"

