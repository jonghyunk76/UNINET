/**
 * APStatusInquiryService_InboundPortBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mySingle.service;

public class APStatusInquiryService_InboundPortBindingStub extends org.apache.axis.client.Stub implements samsung.esb.approval.service.APStatusInquiryService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[7];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getStatusByMisId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "esbAuthVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBAuthVO"), samsung.esb.common.vo.ESBAuthVO.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "misKeyWSVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "MisKeyWSVO"), samsung.esb.approval.vo.MisKeyWSVO.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ProcessStatusWSVO"));
        oper.setReturnClass(samsung.esb.approval.vo.ProcessStatusWSVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "processStatusWSVO"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://samsung/esb/approval/service", "getStatusByMisId_esbFaultVO"),
                      "samsung.esb.common.vo.ESBFaultVO",
                      new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBFaultVO"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getStatusByBulkMisId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "esbAuthVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBAuthVO"), samsung.esb.common.vo.ESBAuthVO.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "misKeyWSVOs"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ArrayOfMisKeyWSVO_literal"), samsung.esb.approval.vo.MisKeyWSVO[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "MisKeyWSVO"));
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ArrayOfProcessBriefStatusWSVO_literal"));
        oper.setReturnClass(samsung.esb.approval.vo.ProcessBriefStatusWSVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "processBriefStatusWSVOs"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "ProcessBriefStatusWSVO"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://samsung/esb/approval/service", "getStatusByBulkMisId_esbFaultVO"),
                      "samsung.esb.common.vo.ESBFaultVO",
                      new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBFaultVO"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getListByUserKey");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "esbAuthVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBAuthVO"), samsung.esb.common.vo.ESBAuthVO.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "userKeyWSVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "UserKeyWSVO"), samsung.esb.approval.vo.UserKeyWSVO.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ArrayOfProcessListWSVO_literal"));
        oper.setReturnClass(samsung.esb.approval.vo.ProcessListWSVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "processListWSVOs"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "ProcessListWSVO"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://samsung/esb/approval/service", "getListByUserKey_esbFaultVO"),
                      "samsung.esb.common.vo.ESBFaultVO",
                      new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBFaultVO"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getProcessIdByMisId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "esbAuthVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBAuthVO"), samsung.esb.common.vo.ESBAuthVO.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "misKeyWSVOs"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ArrayOfMisKeyWSVO_literal"), samsung.esb.approval.vo.MisKeyWSVO[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "MisKeyWSVO"));
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ArrayOfProcessRevisionWSVO_literal"));
        oper.setReturnClass(samsung.esb.approval.vo.ProcessRevisionWSVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ProcessRevisionWSVOs"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "ProcessRevisionWSVO"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://samsung/esb/approval/service", "getProcessIdByMisId_esbFaultVO"),
                      "samsung.esb.common.vo.ESBFaultVO",
                      new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBFaultVO"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRevisionByMisId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "esbAuthVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBAuthVO"), samsung.esb.common.vo.ESBAuthVO.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "misKeyWSVOs"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ArrayOfMisKeyWSVO_literal"), samsung.esb.approval.vo.MisKeyWSVO[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "MisKeyWSVO"));
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ArrayOfProcessRevisionWSVO_literal"));
        oper.setReturnClass(samsung.esb.approval.vo.ProcessRevisionWSVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ProcessRevisionWSVOs"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "ProcessRevisionWSVO"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://samsung/esb/approval/service", "getRevisionByMisId_esbFaultVO"),
                      "samsung.esb.common.vo.ESBFaultVO",
                      new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBFaultVO"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getCallbackFailKey");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "esbAuthVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBAuthVO"), samsung.esb.common.vo.ESBAuthVO.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "processCallbackFailWSVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ProcessCallbackFailWSVO"), samsung.esb.approval.vo.ProcessCallbackFailWSVO.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ArrayOfMisKeyWSVO_literal"));
        oper.setReturnClass(samsung.esb.approval.vo.MisKeyWSVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "misKeyWSVOs"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "MisKeyWSVO"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://samsung/esb/approval/service", "getCallbackFailKeyFault1_esbFaultVO"),
                      "samsung.esb.common.vo.ESBFaultVO",
                      new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBFaultVO"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getOriginUserID");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "esbAuthVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBAuthVO"), samsung.esb.common.vo.ESBAuthVO.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "misKeyWSVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "MisKeyWSVO"), samsung.esb.approval.vo.MisKeyWSVO.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "routeDelegateWSVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "RouteDelegateWSVO"), samsung.esb.approval.vo.RouteDelegateWSVO.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "RouteDelegateWSVO"));
        oper.setReturnClass(samsung.esb.approval.vo.RouteDelegateWSVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://samsung/esb/approval/service", "getOriginUserIDFault1_esbFaultVO"),
                      "samsung.esb.common.vo.ESBFaultVO",
                      new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBFaultVO"), 
                      true
                     ));
        _operations[6] = oper;

    }

    public APStatusInquiryService_InboundPortBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public APStatusInquiryService_InboundPortBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public APStatusInquiryService_InboundPortBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ArrayOfMisKeyWSVO_literal");
            cachedSerQNames.add(qName);
            cls = samsung.esb.approval.vo.MisKeyWSVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "MisKeyWSVO");
            qName2 = new javax.xml.namespace.QName("", "MisKeyWSVO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ArrayOfProcessBriefStatusWSVO_literal");
            cachedSerQNames.add(qName);
            cls = samsung.esb.approval.vo.ProcessBriefStatusWSVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ProcessBriefStatusWSVO");
            qName2 = new javax.xml.namespace.QName("", "ProcessBriefStatusWSVO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ArrayOfProcessListWSVO_literal");
            cachedSerQNames.add(qName);
            cls = samsung.esb.approval.vo.ProcessListWSVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ProcessListWSVO");
            qName2 = new javax.xml.namespace.QName("", "ProcessListWSVO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ArrayOfProcessRevisionWSVO_literal");
            cachedSerQNames.add(qName);
            cls = samsung.esb.approval.vo.ProcessRevisionWSVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ProcessRevisionWSVO");
            qName2 = new javax.xml.namespace.QName("", "ProcessRevisionWSVO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "AttachmentWSVO");
            cachedSerQNames.add(qName);
            cls = samsung.esb.approval.vo.AttachmentWSVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "MisKeyWSVO");
            cachedSerQNames.add(qName);
            cls = samsung.esb.approval.vo.MisKeyWSVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ProcessBriefStatusWSVO");
            cachedSerQNames.add(qName);
            cls = samsung.esb.approval.vo.ProcessBriefStatusWSVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ProcessCallbackFailWSVO");
            cachedSerQNames.add(qName);
            cls = samsung.esb.approval.vo.ProcessCallbackFailWSVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ProcessListWSVO");
            cachedSerQNames.add(qName);
            cls = samsung.esb.approval.vo.ProcessListWSVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ProcessRevisionWSVO");
            cachedSerQNames.add(qName);
            cls = samsung.esb.approval.vo.ProcessRevisionWSVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "ProcessStatusWSVO");
            cachedSerQNames.add(qName);
            cls = samsung.esb.approval.vo.ProcessStatusWSVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "RouteDelegateWSVO");
            cachedSerQNames.add(qName);
            cls = samsung.esb.approval.vo.RouteDelegateWSVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "RouteWSVO");
            cachedSerQNames.add(qName);
            cls = samsung.esb.approval.vo.RouteWSVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://samsung/esb/approval/vo", "UserKeyWSVO");
            cachedSerQNames.add(qName);
            cls = samsung.esb.approval.vo.UserKeyWSVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBAuthVO");
            cachedSerQNames.add(qName);
            cls = samsung.esb.common.vo.ESBAuthVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://samsung/esb/common/vo", "ESBFaultVO");
            cachedSerQNames.add(qName);
            cls = samsung.esb.common.vo.ESBFaultVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws-i.org/profiles/basic/1.1/xsd", "swaRef");
            cachedSerQNames.add(qName);
            cls = org.apache.axis.types.URI.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public samsung.esb.approval.vo.ProcessStatusWSVO getStatusByMisId(samsung.esb.common.vo.ESBAuthVO esbAuthVO, samsung.esb.approval.vo.MisKeyWSVO misKeyWSVO) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://samsung/esb/approval/service", "getStatusByMisId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {esbAuthVO, misKeyWSVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (samsung.esb.approval.vo.ProcessStatusWSVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (samsung.esb.approval.vo.ProcessStatusWSVO) org.apache.axis.utils.JavaUtils.convert(_resp, samsung.esb.approval.vo.ProcessStatusWSVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof samsung.esb.common.vo.ESBFaultVO) {
              throw (samsung.esb.common.vo.ESBFaultVO) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public samsung.esb.approval.vo.ProcessBriefStatusWSVO[] getStatusByBulkMisId(samsung.esb.common.vo.ESBAuthVO esbAuthVO, samsung.esb.approval.vo.MisKeyWSVO[] misKeyWSVOs) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://samsung/esb/approval/service", "getStatusByBulkMisId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {esbAuthVO, misKeyWSVOs});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (samsung.esb.approval.vo.ProcessBriefStatusWSVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (samsung.esb.approval.vo.ProcessBriefStatusWSVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, samsung.esb.approval.vo.ProcessBriefStatusWSVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof samsung.esb.common.vo.ESBFaultVO) {
              throw (samsung.esb.common.vo.ESBFaultVO) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public samsung.esb.approval.vo.ProcessListWSVO[] getListByUserKey(samsung.esb.common.vo.ESBAuthVO esbAuthVO, samsung.esb.approval.vo.UserKeyWSVO userKeyWSVO) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://samsung/esb/approval/service", "getListByUserKey"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {esbAuthVO, userKeyWSVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (samsung.esb.approval.vo.ProcessListWSVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (samsung.esb.approval.vo.ProcessListWSVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, samsung.esb.approval.vo.ProcessListWSVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof samsung.esb.common.vo.ESBFaultVO) {
              throw (samsung.esb.common.vo.ESBFaultVO) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public samsung.esb.approval.vo.ProcessRevisionWSVO[] getProcessIdByMisId(samsung.esb.common.vo.ESBAuthVO esbAuthVO, samsung.esb.approval.vo.MisKeyWSVO[] misKeyWSVOs) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://samsung/esb/approval/service", "getProcessIdByMisId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {esbAuthVO, misKeyWSVOs});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (samsung.esb.approval.vo.ProcessRevisionWSVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (samsung.esb.approval.vo.ProcessRevisionWSVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, samsung.esb.approval.vo.ProcessRevisionWSVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof samsung.esb.common.vo.ESBFaultVO) {
              throw (samsung.esb.common.vo.ESBFaultVO) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public samsung.esb.approval.vo.ProcessRevisionWSVO[] getRevisionByMisId(samsung.esb.common.vo.ESBAuthVO esbAuthVO, samsung.esb.approval.vo.MisKeyWSVO[] misKeyWSVOs) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://samsung/esb/approval/service", "getRevisionByMisId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {esbAuthVO, misKeyWSVOs});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (samsung.esb.approval.vo.ProcessRevisionWSVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (samsung.esb.approval.vo.ProcessRevisionWSVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, samsung.esb.approval.vo.ProcessRevisionWSVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof samsung.esb.common.vo.ESBFaultVO) {
              throw (samsung.esb.common.vo.ESBFaultVO) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public samsung.esb.approval.vo.MisKeyWSVO[] getCallbackFailKey(samsung.esb.common.vo.ESBAuthVO esbAuthVO, samsung.esb.approval.vo.ProcessCallbackFailWSVO processCallbackFailWSVO) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://samsung/esb/approval/service", "getCallbackFailKey"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {esbAuthVO, processCallbackFailWSVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (samsung.esb.approval.vo.MisKeyWSVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (samsung.esb.approval.vo.MisKeyWSVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, samsung.esb.approval.vo.MisKeyWSVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof samsung.esb.common.vo.ESBFaultVO) {
              throw (samsung.esb.common.vo.ESBFaultVO) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public samsung.esb.approval.vo.RouteDelegateWSVO getOriginUserID(samsung.esb.common.vo.ESBAuthVO esbAuthVO, samsung.esb.approval.vo.MisKeyWSVO misKeyWSVO, samsung.esb.approval.vo.RouteDelegateWSVO routeDelegateWSVO) throws java.rmi.RemoteException, samsung.esb.common.vo.ESBFaultVO {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://samsung/esb/approval/service", "getOriginUserID"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {esbAuthVO, misKeyWSVO, routeDelegateWSVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (samsung.esb.approval.vo.RouteDelegateWSVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (samsung.esb.approval.vo.RouteDelegateWSVO) org.apache.axis.utils.JavaUtils.convert(_resp, samsung.esb.approval.vo.RouteDelegateWSVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof samsung.esb.common.vo.ESBFaultVO) {
              throw (samsung.esb.common.vo.ESBFaultVO) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
