/**
 * APSubmitServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mySingle.service;

import stw.eai.common.Common;

public class APSubmitServiceLocator extends org.apache.axis.client.Service implements mySingle.service.APSubmitService {

    public APSubmitServiceLocator() {
    }


    public APSubmitServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public APSubmitServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for APSubmitService_InboundPort
    private java.lang.String APSubmitService_InboundPort_address = Common.MYSINGLE_SERVER_ADDR()+"/APSubmitService/APSubmitService_InboundPort";

    public java.lang.String getAPSubmitService_InboundPortAddress() {
        return APSubmitService_InboundPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String APSubmitService_InboundPortWSDDServiceName = "APSubmitService_InboundPort";

    public java.lang.String getAPSubmitService_InboundPortWSDDServiceName() {
        return APSubmitService_InboundPortWSDDServiceName;
    }

    public void setAPSubmitService_InboundPortWSDDServiceName(java.lang.String name) {
        APSubmitService_InboundPortWSDDServiceName = name;
    }

    public samsung.esb.approval.service.APSubmitService getAPSubmitService_InboundPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(APSubmitService_InboundPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAPSubmitService_InboundPort(endpoint);
    }

    public samsung.esb.approval.service.APSubmitService getAPSubmitService_InboundPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            mySingle.service.APSubmitService_InboundPortBindingStub _stub = new mySingle.service.APSubmitService_InboundPortBindingStub(portAddress, this);
            _stub.setPortName(getAPSubmitService_InboundPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAPSubmitService_InboundPortEndpointAddress(java.lang.String address) {
        APSubmitService_InboundPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (samsung.esb.approval.service.APSubmitService.class.isAssignableFrom(serviceEndpointInterface)) {
                mySingle.service.APSubmitService_InboundPortBindingStub _stub = new mySingle.service.APSubmitService_InboundPortBindingStub(new java.net.URL(APSubmitService_InboundPort_address), this);
                _stub.setPortName(getAPSubmitService_InboundPortWSDDServiceName());
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
        if ("APSubmitService_InboundPort".equals(inputPortName)) {
            return getAPSubmitService_InboundPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:service.mySingle/Service", "APSubmitService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:service.mySingle/Service", "APSubmitService_InboundPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("APSubmitService_InboundPort".equals(portName)) {
            setAPSubmitService_InboundPortEndpointAddress(address);
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
