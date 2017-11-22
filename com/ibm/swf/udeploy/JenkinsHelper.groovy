/*
* Licensed Materials - Property of IBM Corp.
* IBM UrbanCode Deploy
* (c) Copyright IBM Corporation 2011, 2017. All Rights Reserved.
*
* U.S. Government Users Restricted Rights - Use, duplication or disclosure restricted by
* GSA ADP Schedule Contract with IBM Corp.
*/
package com.ibm.swf.udeploy

import java.net.URLEncoder

String.metaClass.encodeURL = {
   java.net.URLEncoder.encode(delegate, "UTF-8")
}

public class JenkinsHelper {
    def jenkinsUser
    def  jenkinsPass
    def weburl
 
    public AppHelper(String inUser, String inPass, String inUrl) {
        jenkinsUser = inUser
        jenkinsPass = inPass
        weburl = inUrl

    }
    
    public static AppHelper getHelper(String inUser, String inPass, String inUrl) {
        return new AppHelper(inUser, inPass, inUrl)
    }

    public startJob( String jobName, Map parameterMap, Boolean waitForCompletion) {
        def creds = jenkinsUser + ":" + jenkinsPass
        
        // create the url to get the crumb
        def url = weburl + '/crumbIssuer/api/xml?xpath=concat(//crumbRequestField,\":\",//crumb)'
        print url 
        crump =sh ( returnStdout: true, returnStatus: false, script: "curl -u \"${creds}\" \"${url}\"")
        print crump
        if ( crumb.indexOf("Error 404 Not Found") >= 0 ) {
            crumb = ""
        }
        crumb = crumb.trim()
        if ( crub.size() > 0 ) {
            crumb = "-H \"" + crumb + "\""
        }
        // now create url for starting the job
        url= weburl+"/job/"+jobName +/build"
        
        if (parameterMap.size() > 0 ) {
            url += "WithParameters?"
            sep ="?"
            for (key in parameterMap.getKeys() ){
                url += sep + "${key.encodeURL()}=${parameterMap[key].encodeURL()}"
            }
            url += "&delay=0"
        } else {
            url += "?delay=0"
        print url
        
        
        status= sh ( returnStdout: false, returnStatus: true, script: "curl -X POST -u \"${creds}\" ${crunb} '${url}' " )
        
        
        //curl -X POST -u walter:passwordOrToken -H "Jenkins-Crumb:6c14d58527881a8635586ea4d3310e19" "http://localhost:8080/job/StartTestDummy/buildWithParameters?User=Simon&delay=0"

    }
}
