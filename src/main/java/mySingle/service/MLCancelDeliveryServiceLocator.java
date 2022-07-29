/**
 * MLCancelDeliveryServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mySingle.service;

import stw.eai.common.Common;

public class MLCancelDeliveryServiceLocator extends org.apache.axis.client.Service implements mySingle.service.MLCancelDeliveryService {

    public MLCancelDeliveryServiceLocator() {
    }


    public MLCancelDeliveryServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MLCancelDeliveryServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MLCancelDeliveryService_InboundPort
    private java.lang.String MLCancelDeliveryService_InboundPort_address = Common.MYSINGLE_SERVER_ADDR()+"/MLCancelDeliveryService/MLCancelDeliveryService_InboundPort";

    public java.lang.String getMLCancelDeliveryService_InboundPortAddress() {
        return MLCancelDeliveryService_InboundPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MLCancelDeliveryService_InboundPortWSDDServiceName = "MLCancelDeliveryService_InboundPort";

    public java.lang.String getMLCancelDeliveryService_InboundPortWSDDServiceName() {
        return MLCancelDeliveryService_InboundPortWSDDServiceName;
    }

    public void setMLCancelDeliveryService_InboundPortWSDDServiceName(java.lang.String name) {
        MLCancelDeliveryService_InboundPortWSDDServiceName = name;
    }

    public samsung.esb.mail.service.MLCancelDeliveryService getMLCancelDeliveryService_InboundPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MLCancelDeliveryService_InboundPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMLCancelDeliveryService_InboundPort(endpoint);
    }

    public samsung.esb.mail.service.MLCancelDeliveryService getMLCancelDeliveryService_InboundPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            mySingle.service.MLCancelDeliveryService_InboundPortBindingStub _stub = new mySingle.service.MLCancelDeliveryService_InboundPortBindingStub(portAddress, this);
            _stub.setPortName(getMLCancelDeliveryService_InboundPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMLCancelDeliveryService_InboundPortEndpointAddress(java.lang.String address) {
        MLCancelDeliveryService_InboundPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (samsung.esb.mail.service.MLCancelDeliveryService.class.isAssignableFrom(serviceEndpointInterface)) {
                mySingle.service.MLCancelDeliveryService_InboundPortBindingStub _stub = new mySingle.service.MLCancelDeliveryService_InboundPortBindingStub(new java.net.URL(MLCancelDeliveryService_InboundPort_address), this);
                _stub.setPortName(getMLCancelDeliveryService_InboundPortWSDDServiceName());
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
        if ("MLCancelDeliveryService_InboundPort".equals(inputPortName)) {
            return getMLCancelDeliveryService_InboundPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:service.mySingle/Service", "MLCancelDeliveryService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:service.mySingle/Service", "MLCancelDeliveryService_InboundPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MLCancelDeliveryService_InboundPort".equals(portName)) {
            setMLCancelDeliveryService_InboundPortEndpointAddress(address);
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
