/**
 * MLSendServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mySingle.service;

import stw.eai.common.Common;

public class MLSendServiceLocator extends org.apache.axis.client.Service implements mySingle.service.MLSendService {

    public MLSendServiceLocator() {
    }


    public MLSendServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MLSendServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MLSendService_InboundPort
    private java.lang.String MLSendService_InboundPort_address = Common.MYSINGLE_SERVER_ADDR()+"/MLSendService/MLSendService_InboundPort";

    public java.lang.String getMLSendService_InboundPortAddress() {
        return MLSendService_InboundPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MLSendService_InboundPortWSDDServiceName = "MLSendService_InboundPort";

    public java.lang.String getMLSendService_InboundPortWSDDServiceName() {
        return MLSendService_InboundPortWSDDServiceName;
    }

    public void setMLSendService_InboundPortWSDDServiceName(java.lang.String name) {
        MLSendService_InboundPortWSDDServiceName = name;
    }

    public samsung.esb.mail.service.MLSendService getMLSendService_InboundPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MLSendService_InboundPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMLSendService_InboundPort(endpoint);
    }

    public samsung.esb.mail.service.MLSendService getMLSendService_InboundPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            mySingle.service.MLSendService_InboundPortBindingStub _stub = new mySingle.service.MLSendService_InboundPortBindingStub(portAddress, this);
            _stub.setPortName(getMLSendService_InboundPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMLSendService_InboundPortEndpointAddress(java.lang.String address) {
        MLSendService_InboundPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (samsung.esb.mail.service.MLSendService.class.isAssignableFrom(serviceEndpointInterface)) {
                mySingle.service.MLSendService_InboundPortBindingStub _stub = new mySingle.service.MLSendService_InboundPortBindingStub(new java.net.URL(MLSendService_InboundPort_address), this);
                _stub.setPortName(getMLSendService_InboundPortWSDDServiceName());
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
        if ("MLSendService_InboundPort".equals(inputPortName)) {
            return getMLSendService_InboundPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:service.mySingle/Service", "MLSendService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:service.mySingle/Service", "MLSendService_InboundPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MLSendService_InboundPort".equals(portName)) {
            setMLSendService_InboundPortEndpointAddress(address);
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
