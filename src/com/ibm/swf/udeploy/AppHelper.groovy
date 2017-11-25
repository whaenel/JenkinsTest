/*
* Licensed Materials - Property of IBM Corp.
* IBM UrbanCode Deploy
* (c) Copyright IBM Corporation 2011, 2017. All Rights Reserved.
*
* U.S. Government Users Restricted Rights - Use, duplication or disclosure restricted by
* GSA ADP Schedule Contract with IBM Corp.
*/
package com.ibm.swf.udeploy

import com.urbancode.ud.client.ApplicationClient
import com.urbancode.ud.client.VersionClient;
import com.urbancode.ud.client.VersionInfoClient;
import com.urbancode.ud.client.ComponentClient;
import com.urbancode.ds.client.AddVersionFilesCommand;
import org.apache.http.impl.client.DefaultHttpClient;
import com.urbancode.commons.httpcomponentsutil.HttpClientBuilder

import java.rmi.RemoteException
import java.util.UUID

import org.apache.commons.lang.StringUtils

import org.codehaus.jettison.json.JSONArray
import org.codehaus.jettison.json.JSONObject
import org.codehaus.jettison.json.JSONException

public class AppHelper {
    def udUser
    def udPass
    def weburl
    ApplicationClient client

    public AppHelper(String inUser, String inPass, String inUrl) {
        udUser = inUser
        udPass = inPass
        weburl = inUrl
        client = new ApplicationClient(new URI(weburl), udUser, udPass)

    }
    public def createSnapshot(def snapshotName, def snapshotDescription, def applicationName, HashMap<String, List<String>> versionMap) {
        def snapshotId = client.createSnapshot(snapshotName, snapshotDescription, applicationName,
                versionMap);
        println "\nSnapshot "+snapshotName+" created."
        return snapshotId

    }
    
    public def createSnapshotOfEnvironment(def snapshotName, def snapshotDescription, def applicationName,def environmentName) {

        def snapshotId = client.createSnapshotOfEnvironment(environmentName, applicationName,
                snapshotName, snapshotDescription);


                println "\nSnapshot "+snapshotName+" created."
    }

    public createVersion(String componentName, String versionName){
        def udRestClient = new VersionClient(new URI(weburl), udUser, udPass);

        println "Creating version $versionName on component $componentName";
        def versionId = udRestClient.createVersion(componentName, versionName, "");
        print versionId
        return versionId.toString()
    }
    public addVersionFiles(String componentName, String versionName, String directoryName,  String[] includes,  String[] excludes, Boolean  saveFileExecuteBits){
        HttpClientBuilder builder = new HttpClientBuilder();
        builder.setTrustAllCerts(true);

        builder.setUsername(udUser);
        builder.setPassword(udPass);
        builder.setPreemptiveAuthentication(true);
        DefaultHttpClient client = builder.buildClient();
        def command = new AddVersionFilesCommand(weburl, client, componentName,
                                            versionName, new File(directoryName), null, includes, excludes,
                                             saveFileExecuteBits, true, null, null);
        command.execute();
}

    public getVersionId(String componentName, String versionName){
        def udRestClient = new VersionInfoClient(new URI(weburl), udUser, udPass);
        return udRestClient.getVersionId(componentName,versionName)
 
 }





    public addVersionStatus(String componentName, String versionName, String status ){
        def udRestClient = new ComponentClient(new URI(weburl), udUser, udPass);

        println "Adding status $status to version $versionName on component $componentName";
        udRestClient.addComponentVersionStatus(componentName,
                                       versionName,
                                       status);
     }  
     public Map<String, String> getVersionProperties(String componentName, String versionName){
        def udRestClient = new VersionClient(new URI(weburl), udUser, udPass)

        println "Getting version properties for version id ${versionName}."
        Map<String, String> versionProps = udRestClient.getVersionProperties(versionName, componentName);
        for (String key : versionProps.keySet()) {
            String value = versionProps.get(key)
            println "$key: $value"
        }
        println "Version properties retrieved."
        return versionProps
    }

    
}
