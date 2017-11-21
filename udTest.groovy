
import  com.ibm.swf.udeploy.AppHelper

AppHelper helper = new AppHelper('admin', 'admin', 'https://josua.haenel.local:8443' );
//def id = helper.createSnapshotOfEnvironment('hugo', 'bla bla', 'EnvTempApp2','MyAppFT')
//String id = helper.createVersion('FromCompTemplate', '1.0' )
helper.getVersionProperties('FromCompTemplate', '1.0')
//print id
def includes=['**/*'] as String[];
def excludes=[] as String[];
helper.addVersionFiles('FromCompTemplate', '1.0','TestData/version',includes,excludes,true)

def id = helper.getVersionId('FromCompTemplate', '1.0')
print id

id = helper.getVersionId('FromCompTemplate', '1.2')
print id
