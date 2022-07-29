/**
 * APStatusInquiryServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mySingle.service;

import stw.eai.common.Common;

public class APStatusInquiryServiceLocator extends org.apache.axis.client.Service implements mySingle.service.APStatusInquiryService {

    public APStatusInquiryServiceLocator() {
    }


    public APStatusInquiryServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public APStatusInquiryServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for APStatusInquiryService_InboundPort
    private java.lang.String APStatusInquiryService_InboundPort_address = Common.MYSINGLE_SERVER_ADDR()+"/APStatusInquiryService/APStatusInquiryService_InboundPort";

    public java.lang.String getAPStatusInquiryService_InboundPortAddress() {
        return APStatusInquiryService_InboundPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String APStatusInquiryService_InboundPortWSDDServiceName = "APStatusInquiryService_InboundPort";

    public java.lang.String getAPStatusInquiryService_InboundPortWSDDServiceName() {
        return APStatusInquiryService_InboundPortWSDDServiceName;
    }

    public void setAPStatusInquiryService_InboundPortWSDDServiceName(java.lang.String name) {
        APStatusInquiryService_InboundPortWSDDServiceName = name;
    }

    public samsung.esb.approval.service.APStatusInquiryService getAPStatusInquiryService_InboundPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(APStatusInquiryService_InboundPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAPStatusInquiryService_InboundPort(endpoint);
    }

    public samsung.esb.approval.service.APStatusInquiryService getAPStatusInquiryService_InboundPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            mySingle.service.APStatusInquiryService_InboundPortBindingStub _stub = new mySingle.service.APStatusInquiryService_InboundPortBindingStub(portAddress, this);
            _stub.setPortName(getAPStatusInquiryService_InboundPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAPStatusInquiryService_InboundPortEndpointAddress(java.lang.String address) {
        APStatusInquiryService_InboundPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (samsung.esb.approval.service.APStatusInquiryService.class.isAssignableFrom(serviceEndpointInterface)) {
                mySingle.service.APStatusInquiryService_InboundPortBindingStub _stub = new mySingle.service.APStatusInquiryService_InboundPortBindingStub(new java.net.URL(APStatusInquiryService_InboundPort_address), this);
                _stub.setPortName(getAPStatusInquiryService_InboundPortWSDDServiceName());
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
        if ("APStatusInquiryService_InboundPort".equals(inputPortName)) {
            return getAPStatusInquiryService_InboundPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:service.mySingle/Service", "APStatusInquiryService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:service.mySingle/Service", "APStatusInquiryService_InboundPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("APStatusInquiryService_InboundPort".equals(portName)) {
            setAPStatusInquiryService_InboundPortEndpointAddress(address);
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
