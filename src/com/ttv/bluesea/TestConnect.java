package com.ttv.bluesea;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessageContext;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

public class TestConnect {
	
	private static final String WS_URL = "http://sms.8x77.vn:8077/mt-services/MTService?wsdl";
	
	public static void main(String[] args) throws Exception {
	   
	URL url = new URL(WS_URL);
        QName qname = new QName("http://sms.8x77.vn:8077/", "HelloWorldImplService");

        Service service = Service.create(url, qname);
       // HelloWorld hello = service.getPort(HelloWorld.class);
        
        /*******************UserName & Password ******************************/
      //  Map<String, Object> req_ctx = ((BindingProvider)hello).getRequestContext();
        //req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, WS_URL);

        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Username", Collections.singletonList("mkyong"));
        headers.put("Password", Collections.singletonList("password"));
       // req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
        /**********************************************************************/
        
       // System.out.println(hello.getHelloWorldAsString());
       
    }
	 
}
