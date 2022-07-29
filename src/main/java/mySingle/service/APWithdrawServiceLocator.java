/**
 * APWithdrawServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mySingle.service;

import stw.eai.common.Common;

public class APWithdrawServiceLocator extends org.apache.axis.client.Service implements mySingle.service.APWithdrawService {

    public APWithdrawServiceLocator() {
    }


    public APWithdrawServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public APWithdrawServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for APWithdrawService_InboundPort
    private java.lang.String APWithdrawService_InboundPort_address = Common.MYSINGLE_SERVER_ADDR()+"/APWithdrawService/APWithdrawService_InboundPort";

    public java.lang.String getAPWithdrawService_InboundPortAddress() {
        return APWithdrawService_InboundPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String APWithdrawService_InboundPortWSDDServiceName = "APWithdrawService_InboundPort";

    public java.lang.String getAPWithdrawService_InboundPortWSDDServiceName() {
        return APWithdrawService_InboundPortWSDDServiceName;
    }

    public void setAPWithdrawService_InboundPortWSDDServiceName(java.lang.String name) {
        APWithdrawService_InboundPortWSDDServiceName = name;
    }

    public samsung.esb.approval.service.APWithdrawService getAPWithdrawService_InboundPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(APWithdrawService_InboundPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAPWithdrawService_InboundPort(endpoint);
    }

    public samsung.esb.approval.service.APWithdrawService getAPWithdrawService_InboundPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            mySingle.service.APWithdrawService_InboundPortBindingStub _stub = new mySingle.service.APWithdrawService_InboundPortBindingStub(portAddress, this);
            _stub.setPortName(getAPWithdrawService_InboundPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAPWithdrawService_InboundPortEndpointAddress(java.lang.String address) {
        APWithdrawService_InboundPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (samsung.esb.approval.service.APWithdrawService.class.isAssignableFrom(serviceEndpointInterface)) {
                mySingle.service.APWithdrawService_InboundPortBindingStub _stub = new mySingle.service.APWithdrawService_InboundPortBindingStub(new java.net.URL(APWithdrawService_InboundPort_address), this);
                _stub.setPortName(getAPWithdrawService_InboundPortWSDDServiceName());
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
        if ("APWithdrawService_InboundPort".equals(inputPortName)) {
            return getAPWithdrawService_InboundPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:service.mySingle/Service", "APWithdrawService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:service.mySingle/Service", "APWithdrawService_InboundPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("APWithdrawService_InboundPort".equals(portName)) {
            setAPWithdrawService_InboundPortEndpointAddress(address);
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
