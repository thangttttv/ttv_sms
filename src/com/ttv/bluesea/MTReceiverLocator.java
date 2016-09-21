/**
 * MTReceiverLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ttv.bluesea;

public class MTReceiverLocator extends org.apache.axis.client.Service implements MTReceiver {

    public MTReceiverLocator() {
    }


    public MTReceiverLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MTReceiverLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MTReceiverPort
    private java.lang.String MTReceiverPort_address = "http://sms.8x77.vn:8077/mt-services/MTService";

    public java.lang.String getMTReceiverPortAddress() {
        return MTReceiverPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MTReceiverPortWSDDServiceName = "MTReceiverPort";

    public java.lang.String getMTReceiverPortWSDDServiceName() {
        return MTReceiverPortWSDDServiceName;
    }

    public void setMTReceiverPortWSDDServiceName(java.lang.String name) {
        MTReceiverPortWSDDServiceName = name;
    }

    public MTReceiverPort getMTReceiverPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MTReceiverPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMTReceiverPort(endpoint);
    }

    public MTReceiverPort getMTReceiverPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            MTReceiverPortStub _stub = new MTReceiverPortStub(portAddress, this);
            _stub.setPortName(getMTReceiverPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMTReceiverPortEndpointAddress(java.lang.String address) {
        MTReceiverPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (MTReceiverPort.class.isAssignableFrom(serviceEndpointInterface)) {
                MTReceiverPortStub _stub = new MTReceiverPortStub(new java.net.URL(MTReceiverPort_address), this);
                _stub.setPortName(getMTReceiverPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("MTReceiverPort".equals(inputPortName)) {
            return getMTReceiverPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("MTService", "MTReceiver");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("MTService", "MTReceiverPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MTReceiverPort".equals(portName)) {
            setMTReceiverPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
