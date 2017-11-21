/*
* Licensed Materials - Property of IBM Corp.
* IBM UrbanCode Deploy
* (c) Copyright IBM Corporation 2011, 2016. All Rights Reserved.
*
* U.S. Government Users Restricted Rights - Use, duplication or disclosure restricted by
* GSA ADP Schedule Contract with IBM Corp.
*/
package com.urbancode.ud.client;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.urbancode.ds.client.AddVersionFilesCommand;
import com.urbancode.ds.client.DownloadVersionFilesCommand;

@SuppressWarnings("deprecation") // Triggered by DefaultHttpClient
public class VersionInfoClient extends UDRestClient{

    //**********************************************************************************************
    // CLASS
    //**********************************************************************************************
    private static final Logger log = Logger.getLogger(ComponentClient.class);

    //**********************************************************************************************
    // INSTANCE
    //**********************************************************************************************

    //----------------------------------------------------------------------------------------------
    public VersionInfoClient(URI url, String clientUser, String clientPassword) {
        super(url, clientUser, clientPassword);
    }

    //----------------------------------------------------------------------------------------------
    //public VersionClient(URI url, String clientUser, String clientPassword, boolean trustAllCerts) {
    //    super(url, clientUser, clientPassword, trustAllCerts);
    //}

    //----------------------------------------------------------------------------------------------
    /**
     * Construct with a supplied HTTP client. See UDRestClient for configuration
     * requirements and restrictions.
     */
    public VersionInfoClient(URI url, DefaultHttpClient client) {
        super(url, client);
    }

    //----------------------------------------------------------------------------------------------

    public String getVersionId(String component, String version)     throws IOException {
        //UUID result = null;
        print url.toString()
        String uri = url.toString() + "/cli/version/getVersionId?component=" +  encodePath(component) +
                    "&version=" +  encodePath(version) 

        HttpGet method = new HttpGet(uri);
        HttpResponse response = invokeMethod(method);
        String body = getBody(response).toString();

        return body;
    }
    
}
