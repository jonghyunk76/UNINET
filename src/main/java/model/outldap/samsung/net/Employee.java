/**
 * Employee.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package model.outldap.samsung.net;

public class Employee  implements java.io.Serializable {
    private java.lang.String cn;

    private java.lang.String department;

    private java.lang.String departmentnumber;

    private java.lang.String description;

    private java.lang.String employeenumber;

    private java.lang.String employeetype;

    private java.lang.String epaccountstatus;

    private java.lang.String epbusicode;

    private java.lang.String epbusiname;

    private java.lang.String epenbusiname;

    private java.lang.String epencn;

    private java.lang.String ependepartment;

    private java.lang.String ependescription;

    private java.lang.String epengivenname;

    private java.lang.String epengradename;

    private java.lang.String epenhomepostaladdress;

    private java.lang.String epenorganizationname;

    private java.lang.String epenpostaladdress;

    private java.lang.String epenregionname;

    private java.lang.String epensendbusiname;

    private java.lang.String epensendcompanyname;

    private java.lang.String epensenddeptname;

    private java.lang.String epensendgradename;

    private java.lang.String epensendregionname;

    private java.lang.String epensendsuborgname;

    private java.lang.String epensendtitlename;

    private java.lang.String epensn;

    private java.lang.String epensuborgname;

    private java.lang.String epentitle;

    private java.lang.String epgender;

    private java.lang.String epgradename;

    private java.lang.String ephomel;

    private java.lang.String ephomepostalcode;

    private java.lang.String epid;

    private java.lang.String eporganizationnumber;

    private java.lang.String epregioncode;

    private java.lang.String epregionname;

    private java.lang.String epregisternumber;

    private java.lang.String epsuborgcode;

    private java.lang.String epsuborgname;

    private java.lang.String eptitlename;

    private java.lang.String eptitlenumber;

    private java.lang.String epuserstatus;

    private java.lang.String homephone;

    private java.lang.String homepostaladdress;

    private java.lang.String l;

    private java.lang.String mail;

    private java.lang.String mailHost;

    private java.lang.String mobile;

    private java.lang.String o;

    private java.lang.String otherfacsimiletelephonenumber;

    private java.lang.String ou;

    private java.lang.String postaladdress;

    private java.lang.String postalcode;

    private java.lang.String sn;

    private java.lang.String telephonenumber;

    private java.lang.String title;

    private java.lang.String userid;

    private java.lang.String givenname;

    private java.lang.String eptitlesortorder;

    private java.lang.String epsendtitlesortorder;

    private java.lang.String c;

    private java.lang.String dn;

    private java.lang.String epdefaultcompcode;

    private java.lang.String epgradeortitle;

    private java.lang.String episblue;

    private java.lang.String eppreferredlanguage;

    private java.lang.String epsecuritylevel;

    private java.lang.String epsendbusicode;

    private java.lang.String epsendcompanycode;

    private java.lang.String epsendcompanyname;

    private java.lang.String epsenddeptcode;

    private java.lang.String epsenddeptname;

    private java.lang.String epsendgradename;

    private java.lang.String epsendgradeortitle;

    private java.lang.String epsendregioncode;

    private java.lang.String epsendsecuritylevel;

    private java.lang.String epsendsuborgcode;

    private java.lang.String epsendtitlename;

    private java.lang.String epsendtitlenumber;

    private java.lang.String epuserlevel;

    private java.lang.String epvoipnumber;

    private java.lang.String facsimiletelephonenumber;

    private java.lang.String nickname;

    private java.lang.String preferredlanguage;

    private java.lang.String epindeptcode;

    private java.lang.String epindeptcodename;

    private java.lang.String epjob;

    private java.lang.String epjobname;

    private java.lang.String epsendsuborgname;

    private java.lang.String userpassword;

    private java.lang.String serverlocation;

    private java.lang.String epwithdrawdate;

    private java.lang.String currentpage;

    private java.lang.String totalcount;

    private java.lang.String totalpage;

    private java.lang.String rn;

    private java.lang.String epnative;

    private java.lang.String epuserclassify;

    private java.lang.String internalAttachOnly;

    private java.lang.String archiveUser;

    private java.lang.String epexternalmail;

    private java.lang.String epopencode;

    public Employee() {
    }

    public Employee(
           java.lang.String cn,
           java.lang.String department,
           java.lang.String departmentnumber,
           java.lang.String description,
           java.lang.String employeenumber,
           java.lang.String employeetype,
           java.lang.String epaccountstatus,
           java.lang.String epbusicode,
           java.lang.String epbusiname,
           java.lang.String epenbusiname,
           java.lang.String epencn,
           java.lang.String ependepartment,
           java.lang.String ependescription,
           java.lang.String epengivenname,
           java.lang.String epengradename,
           java.lang.String epenhomepostaladdress,
           java.lang.String epenorganizationname,
           java.lang.String epenpostaladdress,
           java.lang.String epenregionname,
           java.lang.String epensendbusiname,
           java.lang.String epensendcompanyname,
           java.lang.String epensenddeptname,
           java.lang.String epensendgradename,
           java.lang.String epensendregionname,
           java.lang.String epensendsuborgname,
           java.lang.String epensendtitlename,
           java.lang.String epensn,
           java.lang.String epensuborgname,
           java.lang.String epentitle,
           java.lang.String epgender,
           java.lang.String epgradename,
           java.lang.String ephomel,
           java.lang.String ephomepostalcode,
           java.lang.String epid,
           java.lang.String eporganizationnumber,
           java.lang.String epregioncode,
           java.lang.String epregionname,
           java.lang.String epregisternumber,
           java.lang.String epsuborgcode,
           java.lang.String epsuborgname,
           java.lang.String eptitlename,
           java.lang.String eptitlenumber,
           java.lang.String epuserstatus,
           java.lang.String homephone,
           java.lang.String homepostaladdress,
           java.lang.String l,
           java.lang.String mail,
           java.lang.String mailHost,
           java.lang.String mobile,
           java.lang.String o,
           java.lang.String otherfacsimiletelephonenumber,
           java.lang.String ou,
           java.lang.String postaladdress,
           java.lang.String postalcode,
           java.lang.String sn,
           java.lang.String telephonenumber,
           java.lang.String title,
           java.lang.String userid,
           java.lang.String givenname,
           java.lang.String eptitlesortorder,
           java.lang.String epsendtitlesortorder,
           java.lang.String c,
           java.lang.String dn,
           java.lang.String epdefaultcompcode,
           java.lang.String epgradeortitle,
           java.lang.String episblue,
           java.lang.String eppreferredlanguage,
           java.lang.String epsecuritylevel,
           java.lang.String epsendbusicode,
           java.lang.String epsendcompanycode,
           java.lang.String epsendcompanyname,
           java.lang.String epsenddeptcode,
           java.lang.String epsenddeptname,
           java.lang.String epsendgradename,
           java.lang.String epsendgradeortitle,
           java.lang.String epsendregioncode,
           java.lang.String epsendsecuritylevel,
           java.lang.String epsendsuborgcode,
           java.lang.String epsendtitlename,
           java.lang.String epsendtitlenumber,
           java.lang.String epuserlevel,
           java.lang.String epvoipnumber,
           java.lang.String facsimiletelephonenumber,
           java.lang.String nickname,
           java.lang.String preferredlanguage,
           java.lang.String epindeptcode,
           java.lang.String epindeptcodename,
           java.lang.String epjob,
           java.lang.String epjobname,
           java.lang.String epsendsuborgname,
           java.lang.String userpassword,
           java.lang.String serverlocation,
           java.lang.String epwithdrawdate,
           java.lang.String currentpage,
           java.lang.String totalcount,
           java.lang.String totalpage,
           java.lang.String rn,
           java.lang.String epnative,
           java.lang.String epuserclassify,
           java.lang.String internalAttachOnly,
           java.lang.String archiveUser,
           java.lang.String epexternalmail,
           java.lang.String epopencode) {
           this.cn = cn;
           this.department = department;
           this.departmentnumber = departmentnumber;
           this.description = description;
           this.employeenumber = employeenumber;
           this.employeetype = employeetype;
           this.epaccountstatus = epaccountstatus;
           this.epbusicode = epbusicode;
           this.epbusiname = epbusiname;
           this.epenbusiname = epenbusiname;
           this.epencn = epencn;
           this.ependepartment = ependepartment;
           this.ependescription = ependescription;
           this.epengivenname = epengivenname;
           this.epengradename = epengradename;
           this.epenhomepostaladdress = epenhomepostaladdress;
           this.epenorganizationname = epenorganizationname;
           this.epenpostaladdress = epenpostaladdress;
           this.epenregionname = epenregionname;
           this.epensendbusiname = epensendbusiname;
           this.epensendcompanyname = epensendcompanyname;
           this.epensenddeptname = epensenddeptname;
           this.epensendgradename = epensendgradename;
           this.epensendregionname = epensendregionname;
           this.epensendsuborgname = epensendsuborgname;
           this.epensendtitlename = epensendtitlename;
           this.epensn = epensn;
           this.epensuborgname = epensuborgname;
           this.epentitle = epentitle;
           this.epgender = epgender;
           this.epgradename = epgradename;
           this.ephomel = ephomel;
           this.ephomepostalcode = ephomepostalcode;
           this.epid = epid;
           this.eporganizationnumber = eporganizationnumber;
           this.epregioncode = epregioncode;
           this.epregionname = epregionname;
           this.epregisternumber = epregisternumber;
           this.epsuborgcode = epsuborgcode;
           this.epsuborgname = epsuborgname;
           this.eptitlename = eptitlename;
           this.eptitlenumber = eptitlenumber;
           this.epuserstatus = epuserstatus;
           this.homephone = homephone;
           this.homepostaladdress = homepostaladdress;
           this.l = l;
           this.mail = mail;
           this.mailHost = mailHost;
           this.mobile = mobile;
           this.o = o;
           this.otherfacsimiletelephonenumber = otherfacsimiletelephonenumber;
           this.ou = ou;
           this.postaladdress = postaladdress;
           this.postalcode = postalcode;
           this.sn = sn;
           this.telephonenumber = telephonenumber;
           this.title = title;
           this.userid = userid;
           this.givenname = givenname;
           this.eptitlesortorder = eptitlesortorder;
           this.epsendtitlesortorder = epsendtitlesortorder;
           this.c = c;
           this.dn = dn;
           this.epdefaultcompcode = epdefaultcompcode;
           this.epgradeortitle = epgradeortitle;
           this.episblue = episblue;
           this.eppreferredlanguage = eppreferredlanguage;
           this.epsecuritylevel = epsecuritylevel;
           this.epsendbusicode = epsendbusicode;
           this.epsendcompanycode = epsendcompanycode;
           this.epsendcompanyname = epsendcompanyname;
           this.epsenddeptcode = epsenddeptcode;
           this.epsenddeptname = epsenddeptname;
           this.epsendgradename = epsendgradename;
           this.epsendgradeortitle = epsendgradeortitle;
           this.epsendregioncode = epsendregioncode;
           this.epsendsecuritylevel = epsendsecuritylevel;
           this.epsendsuborgcode = epsendsuborgcode;
           this.epsendtitlename = epsendtitlename;
           this.epsendtitlenumber = epsendtitlenumber;
           this.epuserlevel = epuserlevel;
           this.epvoipnumber = epvoipnumber;
           this.facsimiletelephonenumber = facsimiletelephonenumber;
           this.nickname = nickname;
           this.preferredlanguage = preferredlanguage;
           this.epindeptcode = epindeptcode;
           this.epindeptcodename = epindeptcodename;
           this.epjob = epjob;
           this.epjobname = epjobname;
           this.epsendsuborgname = epsendsuborgname;
           this.userpassword = userpassword;
           this.serverlocation = serverlocation;
           this.epwithdrawdate = epwithdrawdate;
           this.currentpage = currentpage;
           this.totalcount = totalcount;
           this.totalpage = totalpage;
           this.rn = rn;
           this.epnative = epnative;
           this.epuserclassify = epuserclassify;
           this.internalAttachOnly = internalAttachOnly;
           this.archiveUser = archiveUser;
           this.epexternalmail = epexternalmail;
           this.epopencode = epopencode;
    }


    /**
     * Gets the cn value for this Employee.
     * 
     * @return cn
     */
    public java.lang.String getCn() {
        return cn;
    }


    /**
     * Sets the cn value for this Employee.
     * 
     * @param cn
     */
    public void setCn(java.lang.String cn) {
        this.cn = cn;
    }


    /**
     * Gets the department value for this Employee.
     * 
     * @return department
     */
    public java.lang.String getDepartment() {
        return department;
    }


    /**
     * Sets the department value for this Employee.
     * 
     * @param department
     */
    public void setDepartment(java.lang.String department) {
        this.department = department;
    }


    /**
     * Gets the departmentnumber value for this Employee.
     * 
     * @return departmentnumber
     */
    public java.lang.String getDepartmentnumber() {
        return departmentnumber;
    }


    /**
     * Sets the departmentnumber value for this Employee.
     * 
     * @param departmentnumber
     */
    public void setDepartmentnumber(java.lang.String departmentnumber) {
        this.departmentnumber = departmentnumber;
    }


    /**
     * Gets the description value for this Employee.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Employee.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the employeenumber value for this Employee.
     * 
     * @return employeenumber
     */
    public java.lang.String getEmployeenumber() {
        return employeenumber;
    }


    /**
     * Sets the employeenumber value for this Employee.
     * 
     * @param employeenumber
     */
    public void setEmployeenumber(java.lang.String employeenumber) {
        this.employeenumber = employeenumber;
    }


    /**
     * Gets the employeetype value for this Employee.
     * 
     * @return employeetype
     */
    public java.lang.String getEmployeetype() {
        return employeetype;
    }


    /**
     * Sets the employeetype value for this Employee.
     * 
     * @param employeetype
     */
    public void setEmployeetype(java.lang.String employeetype) {
        this.employeetype = employeetype;
    }


    /**
     * Gets the epaccountstatus value for this Employee.
     * 
     * @return epaccountstatus
     */
    public java.lang.String getEpaccountstatus() {
        return epaccountstatus;
    }


    /**
     * Sets the epaccountstatus value for this Employee.
     * 
     * @param epaccountstatus
     */
    public void setEpaccountstatus(java.lang.String epaccountstatus) {
        this.epaccountstatus = epaccountstatus;
    }


    /**
     * Gets the epbusicode value for this Employee.
     * 
     * @return epbusicode
     */
    public java.lang.String getEpbusicode() {
        return epbusicode;
    }


    /**
     * Sets the epbusicode value for this Employee.
     * 
     * @param epbusicode
     */
    public void setEpbusicode(java.lang.String epbusicode) {
        this.epbusicode = epbusicode;
    }


    /**
     * Gets the epbusiname value for this Employee.
     * 
     * @return epbusiname
     */
    public java.lang.String getEpbusiname() {
        return epbusiname;
    }


    /**
     * Sets the epbusiname value for this Employee.
     * 
     * @param epbusiname
     */
    public void setEpbusiname(java.lang.String epbusiname) {
        this.epbusiname = epbusiname;
    }


    /**
     * Gets the epenbusiname value for this Employee.
     * 
     * @return epenbusiname
     */
    public java.lang.String getEpenbusiname() {
        return epenbusiname;
    }


    /**
     * Sets the epenbusiname value for this Employee.
     * 
     * @param epenbusiname
     */
    public void setEpenbusiname(java.lang.String epenbusiname) {
        this.epenbusiname = epenbusiname;
    }


    /**
     * Gets the epencn value for this Employee.
     * 
     * @return epencn
     */
    public java.lang.String getEpencn() {
        return epencn;
    }


    /**
     * Sets the epencn value for this Employee.
     * 
     * @param epencn
     */
    public void setEpencn(java.lang.String epencn) {
        this.epencn = epencn;
    }


    /**
     * Gets the ependepartment value for this Employee.
     * 
     * @return ependepartment
     */
    public java.lang.String getEpendepartment() {
        return ependepartment;
    }


    /**
     * Sets the ependepartment value for this Employee.
     * 
     * @param ependepartment
     */
    public void setEpendepartment(java.lang.String ependepartment) {
        this.ependepartment = ependepartment;
    }


    /**
     * Gets the ependescription value for this Employee.
     * 
     * @return ependescription
     */
    public java.lang.String getEpendescription() {
        return ependescription;
    }


    /**
     * Sets the ependescription value for this Employee.
     * 
     * @param ependescription
     */
    public void setEpendescription(java.lang.String ependescription) {
        this.ependescription = ependescription;
    }


    /**
     * Gets the epengivenname value for this Employee.
     * 
     * @return epengivenname
     */
    public java.lang.String getEpengivenname() {
        return epengivenname;
    }


    /**
     * Sets the epengivenname value for this Employee.
     * 
     * @param epengivenname
     */
    public void setEpengivenname(java.lang.String epengivenname) {
        this.epengivenname = epengivenname;
    }


    /**
     * Gets the epengradename value for this Employee.
     * 
     * @return epengradename
     */
    public java.lang.String getEpengradename() {
        return epengradename;
    }


    /**
     * Sets the epengradename value for this Employee.
     * 
     * @param epengradename
     */
    public void setEpengradename(java.lang.String epengradename) {
        this.epengradename = epengradename;
    }


    /**
     * Gets the epenhomepostaladdress value for this Employee.
     * 
     * @return epenhomepostaladdress
     */
    public java.lang.String getEpenhomepostaladdress() {
        return epenhomepostaladdress;
    }


    /**
     * Sets the epenhomepostaladdress value for this Employee.
     * 
     * @param epenhomepostaladdress
     */
    public void setEpenhomepostaladdress(java.lang.String epenhomepostaladdress) {
        this.epenhomepostaladdress = epenhomepostaladdress;
    }


    /**
     * Gets the epenorganizationname value for this Employee.
     * 
     * @return epenorganizationname
     */
    public java.lang.String getEpenorganizationname() {
        return epenorganizationname;
    }


    /**
     * Sets the epenorganizationname value for this Employee.
     * 
     * @param epenorganizationname
     */
    public void setEpenorganizationname(java.lang.String epenorganizationname) {
        this.epenorganizationname = epenorganizationname;
    }


    /**
     * Gets the epenpostaladdress value for this Employee.
     * 
     * @return epenpostaladdress
     */
    public java.lang.String getEpenpostaladdress() {
        return epenpostaladdress;
    }


    /**
     * Sets the epenpostaladdress value for this Employee.
     * 
     * @param epenpostaladdress
     */
    public void setEpenpostaladdress(java.lang.String epenpostaladdress) {
        this.epenpostaladdress = epenpostaladdress;
    }


    /**
     * Gets the epenregionname value for this Employee.
     * 
     * @return epenregionname
     */
    public java.lang.String getEpenregionname() {
        return epenregionname;
    }


    /**
     * Sets the epenregionname value for this Employee.
     * 
     * @param epenregionname
     */
    public void setEpenregionname(java.lang.String epenregionname) {
        this.epenregionname = epenregionname;
    }


    /**
     * Gets the epensendbusiname value for this Employee.
     * 
     * @return epensendbusiname
     */
    public java.lang.String getEpensendbusiname() {
        return epensendbusiname;
    }


    /**
     * Sets the epensendbusiname value for this Employee.
     * 
     * @param epensendbusiname
     */
    public void setEpensendbusiname(java.lang.String epensendbusiname) {
        this.epensendbusiname = epensendbusiname;
    }


    /**
     * Gets the epensendcompanyname value for this Employee.
     * 
     * @return epensendcompanyname
     */
    public java.lang.String getEpensendcompanyname() {
        return epensendcompanyname;
    }


    /**
     * Sets the epensendcompanyname value for this Employee.
     * 
     * @param epensendcompanyname
     */
    public void setEpensendcompanyname(java.lang.String epensendcompanyname) {
        this.epensendcompanyname = epensendcompanyname;
    }


    /**
     * Gets the epensenddeptname value for this Employee.
     * 
     * @return epensenddeptname
     */
    public java.lang.String getEpensenddeptname() {
        return epensenddeptname;
    }


    /**
     * Sets the epensenddeptname value for this Employee.
     * 
     * @param epensenddeptname
     */
    public void setEpensenddeptname(java.lang.String epensenddeptname) {
        this.epensenddeptname = epensenddeptname;
    }


    /**
     * Gets the epensendgradename value for this Employee.
     * 
     * @return epensendgradename
     */
    public java.lang.String getEpensendgradename() {
        return epensendgradename;
    }


    /**
     * Sets the epensendgradename value for this Employee.
     * 
     * @param epensendgradename
     */
    public void setEpensendgradename(java.lang.String epensendgradename) {
        this.epensendgradename = epensendgradename;
    }


    /**
     * Gets the epensendregionname value for this Employee.
     * 
     * @return epensendregionname
     */
    public java.lang.String getEpensendregionname() {
        return epensendregionname;
    }


    /**
     * Sets the epensendregionname value for this Employee.
     * 
     * @param epensendregionname
     */
    public void setEpensendregionname(java.lang.String epensendregionname) {
        this.epensendregionname = epensendregionname;
    }


    /**
     * Gets the epensendsuborgname value for this Employee.
     * 
     * @return epensendsuborgname
     */
    public java.lang.String getEpensendsuborgname() {
        return epensendsuborgname;
    }


    /**
     * Sets the epensendsuborgname value for this Employee.
     * 
     * @param epensendsuborgname
     */
    public void setEpensendsuborgname(java.lang.String epensendsuborgname) {
        this.epensendsuborgname = epensendsuborgname;
    }


    /**
     * Gets the epensendtitlename value for this Employee.
     * 
     * @return epensendtitlename
     */
    public java.lang.String getEpensendtitlename() {
        return epensendtitlename;
    }


    /**
     * Sets the epensendtitlename value for this Employee.
     * 
     * @param epensendtitlename
     */
    public void setEpensendtitlename(java.lang.String epensendtitlename) {
        this.epensendtitlename = epensendtitlename;
    }


    /**
     * Gets the epensn value for this Employee.
     * 
     * @return epensn
     */
    public java.lang.String getEpensn() {
        return epensn;
    }


    /**
     * Sets the epensn value for this Employee.
     * 
     * @param epensn
     */
    public void setEpensn(java.lang.String epensn) {
        this.epensn = epensn;
    }


    /**
     * Gets the epensuborgname value for this Employee.
     * 
     * @return epensuborgname
     */
    public java.lang.String getEpensuborgname() {
        return epensuborgname;
    }


    /**
     * Sets the epensuborgname value for this Employee.
     * 
     * @param epensuborgname
     */
    public void setEpensuborgname(java.lang.String epensuborgname) {
        this.epensuborgname = epensuborgname;
    }


    /**
     * Gets the epentitle value for this Employee.
     * 
     * @return epentitle
     */
    public java.lang.String getEpentitle() {
        return epentitle;
    }


    /**
     * Sets the epentitle value for this Employee.
     * 
     * @param epentitle
     */
    public void setEpentitle(java.lang.String epentitle) {
        this.epentitle = epentitle;
    }


    /**
     * Gets the epgender value for this Employee.
     * 
     * @return epgender
     */
    public java.lang.String getEpgender() {
        return epgender;
    }


    /**
     * Sets the epgender value for this Employee.
     * 
     * @param epgender
     */
    public void setEpgender(java.lang.String epgender) {
        this.epgender = epgender;
    }


    /**
     * Gets the epgradename value for this Employee.
     * 
     * @return epgradename
     */
    public java.lang.String getEpgradename() {
        return epgradename;
    }


    /**
     * Sets the epgradename value for this Employee.
     * 
     * @param epgradename
     */
    public void setEpgradename(java.lang.String epgradename) {
        this.epgradename = epgradename;
    }


    /**
     * Gets the ephomel value for this Employee.
     * 
     * @return ephomel
     */
    public java.lang.String getEphomel() {
        return ephomel;
    }


    /**
     * Sets the ephomel value for this Employee.
     * 
     * @param ephomel
     */
    public void setEphomel(java.lang.String ephomel) {
        this.ephomel = ephomel;
    }


    /**
     * Gets the ephomepostalcode value for this Employee.
     * 
     * @return ephomepostalcode
     */
    public java.lang.String getEphomepostalcode() {
        return ephomepostalcode;
    }


    /**
     * Sets the ephomepostalcode value for this Employee.
     * 
     * @param ephomepostalcode
     */
    public void setEphomepostalcode(java.lang.String ephomepostalcode) {
        this.ephomepostalcode = ephomepostalcode;
    }


    /**
     * Gets the epid value for this Employee.
     * 
     * @return epid
     */
    public java.lang.String getEpid() {
        return epid;
    }


    /**
     * Sets the epid value for this Employee.
     * 
     * @param epid
     */
    public void setEpid(java.lang.String epid) {
        this.epid = epid;
    }


    /**
     * Gets the eporganizationnumber value for this Employee.
     * 
     * @return eporganizationnumber
     */
    public java.lang.String getEporganizationnumber() {
        return eporganizationnumber;
    }


    /**
     * Sets the eporganizationnumber value for this Employee.
     * 
     * @param eporganizationnumber
     */
    public void setEporganizationnumber(java.lang.String eporganizationnumber) {
        this.eporganizationnumber = eporganizationnumber;
    }


    /**
     * Gets the epregioncode value for this Employee.
     * 
     * @return epregioncode
     */
    public java.lang.String getEpregioncode() {
        return epregioncode;
    }


    /**
     * Sets the epregioncode value for this Employee.
     * 
     * @param epregioncode
     */
    public void setEpregioncode(java.lang.String epregioncode) {
        this.epregioncode = epregioncode;
    }


    /**
     * Gets the epregionname value for this Employee.
     * 
     * @return epregionname
     */
    public java.lang.String getEpregionname() {
        return epregionname;
    }


    /**
     * Sets the epregionname value for this Employee.
     * 
     * @param epregionname
     */
    public void setEpregionname(java.lang.String epregionname) {
        this.epregionname = epregionname;
    }


    /**
     * Gets the epregisternumber value for this Employee.
     * 
     * @return epregisternumber
     */
    public java.lang.String getEpregisternumber() {
        return epregisternumber;
    }


    /**
     * Sets the epregisternumber value for this Employee.
     * 
     * @param epregisternumber
     */
    public void setEpregisternumber(java.lang.String epregisternumber) {
        this.epregisternumber = epregisternumber;
    }


    /**
     * Gets the epsuborgcode value for this Employee.
     * 
     * @return epsuborgcode
     */
    public java.lang.String getEpsuborgcode() {
        return epsuborgcode;
    }


    /**
     * Sets the epsuborgcode value for this Employee.
     * 
     * @param epsuborgcode
     */
    public void setEpsuborgcode(java.lang.String epsuborgcode) {
        this.epsuborgcode = epsuborgcode;
    }


    /**
     * Gets the epsuborgname value for this Employee.
     * 
     * @return epsuborgname
     */
    public java.lang.String getEpsuborgname() {
        return epsuborgname;
    }


    /**
     * Sets the epsuborgname value for this Employee.
     * 
     * @param epsuborgname
     */
    public void setEpsuborgname(java.lang.String epsuborgname) {
        this.epsuborgname = epsuborgname;
    }


    /**
     * Gets the eptitlename value for this Employee.
     * 
     * @return eptitlename
     */
    public java.lang.String getEptitlename() {
        return eptitlename;
    }


    /**
     * Sets the eptitlename value for this Employee.
     * 
     * @param eptitlename
     */
    public void setEptitlename(java.lang.String eptitlename) {
        this.eptitlename = eptitlename;
    }


    /**
     * Gets the eptitlenumber value for this Employee.
     * 
     * @return eptitlenumber
     */
    public java.lang.String getEptitlenumber() {
        return eptitlenumber;
    }


    /**
     * Sets the eptitlenumber value for this Employee.
     * 
     * @param eptitlenumber
     */
    public void setEptitlenumber(java.lang.String eptitlenumber) {
        this.eptitlenumber = eptitlenumber;
    }


    /**
     * Gets the epuserstatus value for this Employee.
     * 
     * @return epuserstatus
     */
    public java.lang.String getEpuserstatus() {
        return epuserstatus;
    }


    /**
     * Sets the epuserstatus value for this Employee.
     * 
     * @param epuserstatus
     */
    public void setEpuserstatus(java.lang.String epuserstatus) {
        this.epuserstatus = epuserstatus;
    }


    /**
     * Gets the homephone value for this Employee.
     * 
     * @return homephone
     */
    public java.lang.String getHomephone() {
        return homephone;
    }


    /**
     * Sets the homephone value for this Employee.
     * 
     * @param homephone
     */
    public void setHomephone(java.lang.String homephone) {
        this.homephone = homephone;
    }


    /**
     * Gets the homepostaladdress value for this Employee.
     * 
     * @return homepostaladdress
     */
    public java.lang.String getHomepostaladdress() {
        return homepostaladdress;
    }


    /**
     * Sets the homepostaladdress value for this Employee.
     * 
     * @param homepostaladdress
     */
    public void setHomepostaladdress(java.lang.String homepostaladdress) {
        this.homepostaladdress = homepostaladdress;
    }


    /**
     * Gets the l value for this Employee.
     * 
     * @return l
     */
    public java.lang.String getL() {
        return l;
    }


    /**
     * Sets the l value for this Employee.
     * 
     * @param l
     */
    public void setL(java.lang.String l) {
        this.l = l;
    }


    /**
     * Gets the mail value for this Employee.
     * 
     * @return mail
     */
    public java.lang.String getMail() {
        return mail;
    }


    /**
     * Sets the mail value for this Employee.
     * 
     * @param mail
     */
    public void setMail(java.lang.String mail) {
        this.mail = mail;
    }


    /**
     * Gets the mailHost value for this Employee.
     * 
     * @return mailHost
     */
    public java.lang.String getMailHost() {
        return mailHost;
    }


    /**
     * Sets the mailHost value for this Employee.
     * 
     * @param mailHost
     */
    public void setMailHost(java.lang.String mailHost) {
        this.mailHost = mailHost;
    }


    /**
     * Gets the mobile value for this Employee.
     * 
     * @return mobile
     */
    public java.lang.String getMobile() {
        return mobile;
    }


    /**
     * Sets the mobile value for this Employee.
     * 
     * @param mobile
     */
    public void setMobile(java.lang.String mobile) {
        this.mobile = mobile;
    }


    /**
     * Gets the o value for this Employee.
     * 
     * @return o
     */
    public java.lang.String getO() {
        return o;
    }


    /**
     * Sets the o value for this Employee.
     * 
     * @param o
     */
    public void setO(java.lang.String o) {
        this.o = o;
    }


    /**
     * Gets the otherfacsimiletelephonenumber value for this Employee.
     * 
     * @return otherfacsimiletelephonenumber
     */
    public java.lang.String getOtherfacsimiletelephonenumber() {
        return otherfacsimiletelephonenumber;
    }


    /**
     * Sets the otherfacsimiletelephonenumber value for this Employee.
     * 
     * @param otherfacsimiletelephonenumber
     */
    public void setOtherfacsimiletelephonenumber(java.lang.String otherfacsimiletelephonenumber) {
        this.otherfacsimiletelephonenumber = otherfacsimiletelephonenumber;
    }


    /**
     * Gets the ou value for this Employee.
     * 
     * @return ou
     */
    public java.lang.String getOu() {
        return ou;
    }


    /**
     * Sets the ou value for this Employee.
     * 
     * @param ou
     */
    public void setOu(java.lang.String ou) {
        this.ou = ou;
    }


    /**
     * Gets the postaladdress value for this Employee.
     * 
     * @return postaladdress
     */
    public java.lang.String getPostaladdress() {
        return postaladdress;
    }


    /**
     * Sets the postaladdress value for this Employee.
     * 
     * @param postaladdress
     */
    public void setPostaladdress(java.lang.String postaladdress) {
        this.postaladdress = postaladdress;
    }


    /**
     * Gets the postalcode value for this Employee.
     * 
     * @return postalcode
     */
    public java.lang.String getPostalcode() {
        return postalcode;
    }


    /**
     * Sets the postalcode value for this Employee.
     * 
     * @param postalcode
     */
    public void setPostalcode(java.lang.String postalcode) {
        this.postalcode = postalcode;
    }


    /**
     * Gets the sn value for this Employee.
     * 
     * @return sn
     */
    public java.lang.String getSn() {
        return sn;
    }


    /**
     * Sets the sn value for this Employee.
     * 
     * @param sn
     */
    public void setSn(java.lang.String sn) {
        this.sn = sn;
    }


    /**
     * Gets the telephonenumber value for this Employee.
     * 
     * @return telephonenumber
     */
    public java.lang.String getTelephonenumber() {
        return telephonenumber;
    }


    /**
     * Sets the telephonenumber value for this Employee.
     * 
     * @param telephonenumber
     */
    public void setTelephonenumber(java.lang.String telephonenumber) {
        this.telephonenumber = telephonenumber;
    }


    /**
     * Gets the title value for this Employee.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this Employee.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the userid value for this Employee.
     * 
     * @return userid
     */
    public java.lang.String getUserid() {
        return userid;
    }


    /**
     * Sets the userid value for this Employee.
     * 
     * @param userid
     */
    public void setUserid(java.lang.String userid) {
        this.userid = userid;
    }


    /**
     * Gets the givenname value for this Employee.
     * 
     * @return givenname
     */
    public java.lang.String getGivenname() {
        return givenname;
    }


    /**
     * Sets the givenname value for this Employee.
     * 
     * @param givenname
     */
    public void setGivenname(java.lang.String givenname) {
        this.givenname = givenname;
    }


    /**
     * Gets the eptitlesortorder value for this Employee.
     * 
     * @return eptitlesortorder
     */
    public java.lang.String getEptitlesortorder() {
        return eptitlesortorder;
    }


    /**
     * Sets the eptitlesortorder value for this Employee.
     * 
     * @param eptitlesortorder
     */
    public void setEptitlesortorder(java.lang.String eptitlesortorder) {
        this.eptitlesortorder = eptitlesortorder;
    }


    /**
     * Gets the epsendtitlesortorder value for this Employee.
     * 
     * @return epsendtitlesortorder
     */
    public java.lang.String getEpsendtitlesortorder() {
        return epsendtitlesortorder;
    }


    /**
     * Sets the epsendtitlesortorder value for this Employee.
     * 
     * @param epsendtitlesortorder
     */
    public void setEpsendtitlesortorder(java.lang.String epsendtitlesortorder) {
        this.epsendtitlesortorder = epsendtitlesortorder;
    }


    /**
     * Gets the c value for this Employee.
     * 
     * @return c
     */
    public java.lang.String getC() {
        return c;
    }


    /**
     * Sets the c value for this Employee.
     * 
     * @param c
     */
    public void setC(java.lang.String c) {
        this.c = c;
    }


    /**
     * Gets the dn value for this Employee.
     * 
     * @return dn
     */
    public java.lang.String getDn() {
        return dn;
    }


    /**
     * Sets the dn value for this Employee.
     * 
     * @param dn
     */
    public void setDn(java.lang.String dn) {
        this.dn = dn;
    }


    /**
     * Gets the epdefaultcompcode value for this Employee.
     * 
     * @return epdefaultcompcode
     */
    public java.lang.String getEpdefaultcompcode() {
        return epdefaultcompcode;
    }


    /**
     * Sets the epdefaultcompcode value for this Employee.
     * 
     * @param epdefaultcompcode
     */
    public void setEpdefaultcompcode(java.lang.String epdefaultcompcode) {
        this.epdefaultcompcode = epdefaultcompcode;
    }


    /**
     * Gets the epgradeortitle value for this Employee.
     * 
     * @return epgradeortitle
     */
    public java.lang.String getEpgradeortitle() {
        return epgradeortitle;
    }


    /**
     * Sets the epgradeortitle value for this Employee.
     * 
     * @param epgradeortitle
     */
    public void setEpgradeortitle(java.lang.String epgradeortitle) {
        this.epgradeortitle = epgradeortitle;
    }


    /**
     * Gets the episblue value for this Employee.
     * 
     * @return episblue
     */
    public java.lang.String getEpisblue() {
        return episblue;
    }


    /**
     * Sets the episblue value for this Employee.
     * 
     * @param episblue
     */
    public void setEpisblue(java.lang.String episblue) {
        this.episblue = episblue;
    }


    /**
     * Gets the eppreferredlanguage value for this Employee.
     * 
     * @return eppreferredlanguage
     */
    public java.lang.String getEppreferredlanguage() {
        return eppreferredlanguage;
    }


    /**
     * Sets the eppreferredlanguage value for this Employee.
     * 
     * @param eppreferredlanguage
     */
    public void setEppreferredlanguage(java.lang.String eppreferredlanguage) {
        this.eppreferredlanguage = eppreferredlanguage;
    }


    /**
     * Gets the epsecuritylevel value for this Employee.
     * 
     * @return epsecuritylevel
     */
    public java.lang.String getEpsecuritylevel() {
        return epsecuritylevel;
    }


    /**
     * Sets the epsecuritylevel value for this Employee.
     * 
     * @param epsecuritylevel
     */
    public void setEpsecuritylevel(java.lang.String epsecuritylevel) {
        this.epsecuritylevel = epsecuritylevel;
    }


    /**
     * Gets the epsendbusicode value for this Employee.
     * 
     * @return epsendbusicode
     */
    public java.lang.String getEpsendbusicode() {
        return epsendbusicode;
    }


    /**
     * Sets the epsendbusicode value for this Employee.
     * 
     * @param epsendbusicode
     */
    public void setEpsendbusicode(java.lang.String epsendbusicode) {
        this.epsendbusicode = epsendbusicode;
    }


    /**
     * Gets the epsendcompanycode value for this Employee.
     * 
     * @return epsendcompanycode
     */
    public java.lang.String getEpsendcompanycode() {
        return epsendcompanycode;
    }


    /**
     * Sets the epsendcompanycode value for this Employee.
     * 
     * @param epsendcompanycode
     */
    public void setEpsendcompanycode(java.lang.String epsendcompanycode) {
        this.epsendcompanycode = epsendcompanycode;
    }


    /**
     * Gets the epsendcompanyname value for this Employee.
     * 
     * @return epsendcompanyname
     */
    public java.lang.String getEpsendcompanyname() {
        return epsendcompanyname;
    }


    /**
     * Sets the epsendcompanyname value for this Employee.
     * 
     * @param epsendcompanyname
     */
    public void setEpsendcompanyname(java.lang.String epsendcompanyname) {
        this.epsendcompanyname = epsendcompanyname;
    }


    /**
     * Gets the epsenddeptcode value for this Employee.
     * 
     * @return epsenddeptcode
     */
    public java.lang.String getEpsenddeptcode() {
        return epsenddeptcode;
    }


    /**
     * Sets the epsenddeptcode value for this Employee.
     * 
     * @param epsenddeptcode
     */
    public void setEpsenddeptcode(java.lang.String epsenddeptcode) {
        this.epsenddeptcode = epsenddeptcode;
    }


    /**
     * Gets the epsenddeptname value for this Employee.
     * 
     * @return epsenddeptname
     */
    public java.lang.String getEpsenddeptname() {
        return epsenddeptname;
    }


    /**
     * Sets the epsenddeptname value for this Employee.
     * 
     * @param epsenddeptname
     */
    public void setEpsenddeptname(java.lang.String epsenddeptname) {
        this.epsenddeptname = epsenddeptname;
    }


    /**
     * Gets the epsendgradename value for this Employee.
     * 
     * @return epsendgradename
     */
    public java.lang.String getEpsendgradename() {
        return epsendgradename;
    }


    /**
     * Sets the epsendgradename value for this Employee.
     * 
     * @param epsendgradename
     */
    public void setEpsendgradename(java.lang.String epsendgradename) {
        this.epsendgradename = epsendgradename;
    }


    /**
     * Gets the epsendgradeortitle value for this Employee.
     * 
     * @return epsendgradeortitle
     */
    public java.lang.String getEpsendgradeortitle() {
        return epsendgradeortitle;
    }


    /**
     * Sets the epsendgradeortitle value for this Employee.
     * 
     * @param epsendgradeortitle
     */
    public void setEpsendgradeortitle(java.lang.String epsendgradeortitle) {
        this.epsendgradeortitle = epsendgradeortitle;
    }


    /**
     * Gets the epsendregioncode value for this Employee.
     * 
     * @return epsendregioncode
     */
    public java.lang.String getEpsendregioncode() {
        return epsendregioncode;
    }


    /**
     * Sets the epsendregioncode value for this Employee.
     * 
     * @param epsendregioncode
     */
    public void setEpsendregioncode(java.lang.String epsendregioncode) {
        this.epsendregioncode = epsendregioncode;
    }


    /**
     * Gets the epsendsecuritylevel value for this Employee.
     * 
     * @return epsendsecuritylevel
     */
    public java.lang.String getEpsendsecuritylevel() {
        return epsendsecuritylevel;
    }


    /**
     * Sets the epsendsecuritylevel value for this Employee.
     * 
     * @param epsendsecuritylevel
     */
    public void setEpsendsecuritylevel(java.lang.String epsendsecuritylevel) {
        this.epsendsecuritylevel = epsendsecuritylevel;
    }


    /**
     * Gets the epsendsuborgcode value for this Employee.
     * 
     * @return epsendsuborgcode
     */
    public java.lang.String getEpsendsuborgcode() {
        return epsendsuborgcode;
    }


    /**
     * Sets the epsendsuborgcode value for this Employee.
     * 
     * @param epsendsuborgcode
     */
    public void setEpsendsuborgcode(java.lang.String epsendsuborgcode) {
        this.epsendsuborgcode = epsendsuborgcode;
    }


    /**
     * Gets the epsendtitlename value for this Employee.
     * 
     * @return epsendtitlename
     */
    public java.lang.String getEpsendtitlename() {
        return epsendtitlename;
    }


    /**
     * Sets the epsendtitlename value for this Employee.
     * 
     * @param epsendtitlename
     */
    public void setEpsendtitlename(java.lang.String epsendtitlename) {
        this.epsendtitlename = epsendtitlename;
    }


    /**
     * Gets the epsendtitlenumber value for this Employee.
     * 
     * @return epsendtitlenumber
     */
    public java.lang.String getEpsendtitlenumber() {
        return epsendtitlenumber;
    }


    /**
     * Sets the epsendtitlenumber value for this Employee.
     * 
     * @param epsendtitlenumber
     */
    public void setEpsendtitlenumber(java.lang.String epsendtitlenumber) {
        this.epsendtitlenumber = epsendtitlenumber;
    }


    /**
     * Gets the epuserlevel value for this Employee.
     * 
     * @return epuserlevel
     */
    public java.lang.String getEpuserlevel() {
        return epuserlevel;
    }


    /**
     * Sets the epuserlevel value for this Employee.
     * 
     * @param epuserlevel
     */
    public void setEpuserlevel(java.lang.String epuserlevel) {
        this.epuserlevel = epuserlevel;
    }


    /**
     * Gets the epvoipnumber value for this Employee.
     * 
     * @return epvoipnumber
     */
    public java.lang.String getEpvoipnumber() {
        return epvoipnumber;
    }


    /**
     * Sets the epvoipnumber value for this Employee.
     * 
     * @param epvoipnumber
     */
    public void setEpvoipnumber(java.lang.String epvoipnumber) {
        this.epvoipnumber = epvoipnumber;
    }


    /**
     * Gets the facsimiletelephonenumber value for this Employee.
     * 
     * @return facsimiletelephonenumber
     */
    public java.lang.String getFacsimiletelephonenumber() {
        return facsimiletelephonenumber;
    }


    /**
     * Sets the facsimiletelephonenumber value for this Employee.
     * 
     * @param facsimiletelephonenumber
     */
    public void setFacsimiletelephonenumber(java.lang.String facsimiletelephonenumber) {
        this.facsimiletelephonenumber = facsimiletelephonenumber;
    }


    /**
     * Gets the nickname value for this Employee.
     * 
     * @return nickname
     */
    public java.lang.String getNickname() {
        return nickname;
    }


    /**
     * Sets the nickname value for this Employee.
     * 
     * @param nickname
     */
    public void setNickname(java.lang.String nickname) {
        this.nickname = nickname;
    }


    /**
     * Gets the preferredlanguage value for this Employee.
     * 
     * @return preferredlanguage
     */
    public java.lang.String getPreferredlanguage() {
        return preferredlanguage;
    }


    /**
     * Sets the preferredlanguage value for this Employee.
     * 
     * @param preferredlanguage
     */
    public void setPreferredlanguage(java.lang.String preferredlanguage) {
        this.preferredlanguage = preferredlanguage;
    }


    /**
     * Gets the epindeptcode value for this Employee.
     * 
     * @return epindeptcode
     */
    public java.lang.String getEpindeptcode() {
        return epindeptcode;
    }


    /**
     * Sets the epindeptcode value for this Employee.
     * 
     * @param epindeptcode
     */
    public void setEpindeptcode(java.lang.String epindeptcode) {
        this.epindeptcode = epindeptcode;
    }


    /**
     * Gets the epindeptcodename value for this Employee.
     * 
     * @return epindeptcodename
     */
    public java.lang.String getEpindeptcodename() {
        return epindeptcodename;
    }


    /**
     * Sets the epindeptcodename value for this Employee.
     * 
     * @param epindeptcodename
     */
    public void setEpindeptcodename(java.lang.String epindeptcodename) {
        this.epindeptcodename = epindeptcodename;
    }


    /**
     * Gets the epjob value for this Employee.
     * 
     * @return epjob
     */
    public java.lang.String getEpjob() {
        return epjob;
    }


    /**
     * Sets the epjob value for this Employee.
     * 
     * @param epjob
     */
    public void setEpjob(java.lang.String epjob) {
        this.epjob = epjob;
    }


    /**
     * Gets the epjobname value for this Employee.
     * 
     * @return epjobname
     */
    public java.lang.String getEpjobname() {
        return epjobname;
    }


    /**
     * Sets the epjobname value for this Employee.
     * 
     * @param epjobname
     */
    public void setEpjobname(java.lang.String epjobname) {
        this.epjobname = epjobname;
    }


    /**
     * Gets the epsendsuborgname value for this Employee.
     * 
     * @return epsendsuborgname
     */
    public java.lang.String getEpsendsuborgname() {
        return epsendsuborgname;
    }


    /**
     * Sets the epsendsuborgname value for this Employee.
     * 
     * @param epsendsuborgname
     */
    public void setEpsendsuborgname(java.lang.String epsendsuborgname) {
        this.epsendsuborgname = epsendsuborgname;
    }


    /**
     * Gets the userpassword value for this Employee.
     * 
     * @return userpassword
     */
    public java.lang.String getUserpassword() {
        return userpassword;
    }


    /**
     * Sets the userpassword value for this Employee.
     * 
     * @param userpassword
     */
    public void setUserpassword(java.lang.String userpassword) {
        this.userpassword = userpassword;
    }


    /**
     * Gets the serverlocation value for this Employee.
     * 
     * @return serverlocation
     */
    public java.lang.String getServerlocation() {
        return serverlocation;
    }


    /**
     * Sets the serverlocation value for this Employee.
     * 
     * @param serverlocation
     */
    public void setServerlocation(java.lang.String serverlocation) {
        this.serverlocation = serverlocation;
    }


    /**
     * Gets the epwithdrawdate value for this Employee.
     * 
     * @return epwithdrawdate
     */
    public java.lang.String getEpwithdrawdate() {
        return epwithdrawdate;
    }


    /**
     * Sets the epwithdrawdate value for this Employee.
     * 
     * @param epwithdrawdate
     */
    public void setEpwithdrawdate(java.lang.String epwithdrawdate) {
        this.epwithdrawdate = epwithdrawdate;
    }


    /**
     * Gets the currentpage value for this Employee.
     * 
     * @return currentpage
     */
    public java.lang.String getCurrentpage() {
        return currentpage;
    }


    /**
     * Sets the currentpage value for this Employee.
     * 
     * @param currentpage
     */
    public void setCurrentpage(java.lang.String currentpage) {
        this.currentpage = currentpage;
    }


    /**
     * Gets the totalcount value for this Employee.
     * 
     * @return totalcount
     */
    public java.lang.String getTotalcount() {
        return totalcount;
    }


    /**
     * Sets the totalcount value for this Employee.
     * 
     * @param totalcount
     */
    public void setTotalcount(java.lang.String totalcount) {
        this.totalcount = totalcount;
    }


    /**
     * Gets the totalpage value for this Employee.
     * 
     * @return totalpage
     */
    public java.lang.String getTotalpage() {
        return totalpage;
    }


    /**
     * Sets the totalpage value for this Employee.
     * 
     * @param totalpage
     */
    public void setTotalpage(java.lang.String totalpage) {
        this.totalpage = totalpage;
    }


    /**
     * Gets the rn value for this Employee.
     * 
     * @return rn
     */
    public java.lang.String getRn() {
        return rn;
    }


    /**
     * Sets the rn value for this Employee.
     * 
     * @param rn
     */
    public void setRn(java.lang.String rn) {
        this.rn = rn;
    }


    /**
     * Gets the epnative value for this Employee.
     * 
     * @return epnative
     */
    public java.lang.String getEpnative() {
        return epnative;
    }


    /**
     * Sets the epnative value for this Employee.
     * 
     * @param epnative
     */
    public void setEpnative(java.lang.String epnative) {
        this.epnative = epnative;
    }


    /**
     * Gets the epuserclassify value for this Employee.
     * 
     * @return epuserclassify
     */
    public java.lang.String getEpuserclassify() {
        return epuserclassify;
    }


    /**
     * Sets the epuserclassify value for this Employee.
     * 
     * @param epuserclassify
     */
    public void setEpuserclassify(java.lang.String epuserclassify) {
        this.epuserclassify = epuserclassify;
    }


    /**
     * Gets the internalAttachOnly value for this Employee.
     * 
     * @return internalAttachOnly
     */
    public java.lang.String getInternalAttachOnly() {
        return internalAttachOnly;
    }


    /**
     * Sets the internalAttachOnly value for this Employee.
     * 
     * @param internalAttachOnly
     */
    public void setInternalAttachOnly(java.lang.String internalAttachOnly) {
        this.internalAttachOnly = internalAttachOnly;
    }


    /**
     * Gets the archiveUser value for this Employee.
     * 
     * @return archiveUser
     */
    public java.lang.String getArchiveUser() {
        return archiveUser;
    }


    /**
     * Sets the archiveUser value for this Employee.
     * 
     * @param archiveUser
     */
    public void setArchiveUser(java.lang.String archiveUser) {
        this.archiveUser = archiveUser;
    }


    /**
     * Gets the epexternalmail value for this Employee.
     * 
     * @return epexternalmail
     */
    public java.lang.String getEpexternalmail() {
        return epexternalmail;
    }


    /**
     * Sets the epexternalmail value for this Employee.
     * 
     * @param epexternalmail
     */
    public void setEpexternalmail(java.lang.String epexternalmail) {
        this.epexternalmail = epexternalmail;
    }


    /**
     * Gets the epopencode value for this Employee.
     * 
     * @return epopencode
     */
    public java.lang.String getEpopencode() {
        return epopencode;
    }


    /**
     * Sets the epopencode value for this Employee.
     * 
     * @param epopencode
     */
    public void setEpopencode(java.lang.String epopencode) {
        this.epopencode = epopencode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Employee)) return false;
        Employee other = (Employee) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cn==null && other.getCn()==null) || 
             (this.cn!=null &&
              this.cn.equals(other.getCn()))) &&
            ((this.department==null && other.getDepartment()==null) || 
             (this.department!=null &&
              this.department.equals(other.getDepartment()))) &&
            ((this.departmentnumber==null && other.getDepartmentnumber()==null) || 
             (this.departmentnumber!=null &&
              this.departmentnumber.equals(other.getDepartmentnumber()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.employeenumber==null && other.getEmployeenumber()==null) || 
             (this.employeenumber!=null &&
              this.employeenumber.equals(other.getEmployeenumber()))) &&
            ((this.employeetype==null && other.getEmployeetype()==null) || 
             (this.employeetype!=null &&
              this.employeetype.equals(other.getEmployeetype()))) &&
            ((this.epaccountstatus==null && other.getEpaccountstatus()==null) || 
             (this.epaccountstatus!=null &&
              this.epaccountstatus.equals(other.getEpaccountstatus()))) &&
            ((this.epbusicode==null && other.getEpbusicode()==null) || 
             (this.epbusicode!=null &&
              this.epbusicode.equals(other.getEpbusicode()))) &&
            ((this.epbusiname==null && other.getEpbusiname()==null) || 
             (this.epbusiname!=null &&
              this.epbusiname.equals(other.getEpbusiname()))) &&
            ((this.epenbusiname==null && other.getEpenbusiname()==null) || 
             (this.epenbusiname!=null &&
              this.epenbusiname.equals(other.getEpenbusiname()))) &&
            ((this.epencn==null && other.getEpencn()==null) || 
             (this.epencn!=null &&
              this.epencn.equals(other.getEpencn()))) &&
            ((this.ependepartment==null && other.getEpendepartment()==null) || 
             (this.ependepartment!=null &&
              this.ependepartment.equals(other.getEpendepartment()))) &&
            ((this.ependescription==null && other.getEpendescription()==null) || 
             (this.ependescription!=null &&
              this.ependescription.equals(other.getEpendescription()))) &&
            ((this.epengivenname==null && other.getEpengivenname()==null) || 
             (this.epengivenname!=null &&
              this.epengivenname.equals(other.getEpengivenname()))) &&
            ((this.epengradename==null && other.getEpengradename()==null) || 
             (this.epengradename!=null &&
              this.epengradename.equals(other.getEpengradename()))) &&
            ((this.epenhomepostaladdress==null && other.getEpenhomepostaladdress()==null) || 
             (this.epenhomepostaladdress!=null &&
              this.epenhomepostaladdress.equals(other.getEpenhomepostaladdress()))) &&
            ((this.epenorganizationname==null && other.getEpenorganizationname()==null) || 
             (this.epenorganizationname!=null &&
              this.epenorganizationname.equals(other.getEpenorganizationname()))) &&
            ((this.epenpostaladdress==null && other.getEpenpostaladdress()==null) || 
             (this.epenpostaladdress!=null &&
              this.epenpostaladdress.equals(other.getEpenpostaladdress()))) &&
            ((this.epenregionname==null && other.getEpenregionname()==null) || 
             (this.epenregionname!=null &&
              this.epenregionname.equals(other.getEpenregionname()))) &&
            ((this.epensendbusiname==null && other.getEpensendbusiname()==null) || 
             (this.epensendbusiname!=null &&
              this.epensendbusiname.equals(other.getEpensendbusiname()))) &&
            ((this.epensendcompanyname==null && other.getEpensendcompanyname()==null) || 
             (this.epensendcompanyname!=null &&
              this.epensendcompanyname.equals(other.getEpensendcompanyname()))) &&
            ((this.epensenddeptname==null && other.getEpensenddeptname()==null) || 
             (this.epensenddeptname!=null &&
              this.epensenddeptname.equals(other.getEpensenddeptname()))) &&
            ((this.epensendgradename==null && other.getEpensendgradename()==null) || 
             (this.epensendgradename!=null &&
              this.epensendgradename.equals(other.getEpensendgradename()))) &&
            ((this.epensendregionname==null && other.getEpensendregionname()==null) || 
             (this.epensendregionname!=null &&
              this.epensendregionname.equals(other.getEpensendregionname()))) &&
            ((this.epensendsuborgname==null && other.getEpensendsuborgname()==null) || 
             (this.epensendsuborgname!=null &&
              this.epensendsuborgname.equals(other.getEpensendsuborgname()))) &&
            ((this.epensendtitlename==null && other.getEpensendtitlename()==null) || 
             (this.epensendtitlename!=null &&
              this.epensendtitlename.equals(other.getEpensendtitlename()))) &&
            ((this.epensn==null && other.getEpensn()==null) || 
             (this.epensn!=null &&
              this.epensn.equals(other.getEpensn()))) &&
            ((this.epensuborgname==null && other.getEpensuborgname()==null) || 
             (this.epensuborgname!=null &&
              this.epensuborgname.equals(other.getEpensuborgname()))) &&
            ((this.epentitle==null && other.getEpentitle()==null) || 
             (this.epentitle!=null &&
              this.epentitle.equals(other.getEpentitle()))) &&
            ((this.epgender==null && other.getEpgender()==null) || 
             (this.epgender!=null &&
              this.epgender.equals(other.getEpgender()))) &&
            ((this.epgradename==null && other.getEpgradename()==null) || 
             (this.epgradename!=null &&
              this.epgradename.equals(other.getEpgradename()))) &&
            ((this.ephomel==null && other.getEphomel()==null) || 
             (this.ephomel!=null &&
              this.ephomel.equals(other.getEphomel()))) &&
            ((this.ephomepostalcode==null && other.getEphomepostalcode()==null) || 
             (this.ephomepostalcode!=null &&
              this.ephomepostalcode.equals(other.getEphomepostalcode()))) &&
            ((this.epid==null && other.getEpid()==null) || 
             (this.epid!=null &&
              this.epid.equals(other.getEpid()))) &&
            ((this.eporganizationnumber==null && other.getEporganizationnumber()==null) || 
             (this.eporganizationnumber!=null &&
              this.eporganizationnumber.equals(other.getEporganizationnumber()))) &&
            ((this.epregioncode==null && other.getEpregioncode()==null) || 
             (this.epregioncode!=null &&
              this.epregioncode.equals(other.getEpregioncode()))) &&
            ((this.epregionname==null && other.getEpregionname()==null) || 
             (this.epregionname!=null &&
              this.epregionname.equals(other.getEpregionname()))) &&
            ((this.epregisternumber==null && other.getEpregisternumber()==null) || 
             (this.epregisternumber!=null &&
              this.epregisternumber.equals(other.getEpregisternumber()))) &&
            ((this.epsuborgcode==null && other.getEpsuborgcode()==null) || 
             (this.epsuborgcode!=null &&
              this.epsuborgcode.equals(other.getEpsuborgcode()))) &&
            ((this.epsuborgname==null && other.getEpsuborgname()==null) || 
             (this.epsuborgname!=null &&
              this.epsuborgname.equals(other.getEpsuborgname()))) &&
            ((this.eptitlename==null && other.getEptitlename()==null) || 
             (this.eptitlename!=null &&
              this.eptitlename.equals(other.getEptitlename()))) &&
            ((this.eptitlenumber==null && other.getEptitlenumber()==null) || 
             (this.eptitlenumber!=null &&
              this.eptitlenumber.equals(other.getEptitlenumber()))) &&
            ((this.epuserstatus==null && other.getEpuserstatus()==null) || 
             (this.epuserstatus!=null &&
              this.epuserstatus.equals(other.getEpuserstatus()))) &&
            ((this.homephone==null && other.getHomephone()==null) || 
             (this.homephone!=null &&
              this.homephone.equals(other.getHomephone()))) &&
            ((this.homepostaladdress==null && other.getHomepostaladdress()==null) || 
             (this.homepostaladdress!=null &&
              this.homepostaladdress.equals(other.getHomepostaladdress()))) &&
            ((this.l==null && other.getL()==null) || 
             (this.l!=null &&
              this.l.equals(other.getL()))) &&
            ((this.mail==null && other.getMail()==null) || 
             (this.mail!=null &&
              this.mail.equals(other.getMail()))) &&
            ((this.mailHost==null && other.getMailHost()==null) || 
             (this.mailHost!=null &&
              this.mailHost.equals(other.getMailHost()))) &&
            ((this.mobile==null && other.getMobile()==null) || 
             (this.mobile!=null &&
              this.mobile.equals(other.getMobile()))) &&
            ((this.o==null && other.getO()==null) || 
             (this.o!=null &&
              this.o.equals(other.getO()))) &&
            ((this.otherfacsimiletelephonenumber==null && other.getOtherfacsimiletelephonenumber()==null) || 
             (this.otherfacsimiletelephonenumber!=null &&
              this.otherfacsimiletelephonenumber.equals(other.getOtherfacsimiletelephonenumber()))) &&
            ((this.ou==null && other.getOu()==null) || 
             (this.ou!=null &&
              this.ou.equals(other.getOu()))) &&
            ((this.postaladdress==null && other.getPostaladdress()==null) || 
             (this.postaladdress!=null &&
              this.postaladdress.equals(other.getPostaladdress()))) &&
            ((this.postalcode==null && other.getPostalcode()==null) || 
             (this.postalcode!=null &&
              this.postalcode.equals(other.getPostalcode()))) &&
            ((this.sn==null && other.getSn()==null) || 
             (this.sn!=null &&
              this.sn.equals(other.getSn()))) &&
            ((this.telephonenumber==null && other.getTelephonenumber()==null) || 
             (this.telephonenumber!=null &&
              this.telephonenumber.equals(other.getTelephonenumber()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.userid==null && other.getUserid()==null) || 
             (this.userid!=null &&
              this.userid.equals(other.getUserid()))) &&
            ((this.givenname==null && other.getGivenname()==null) || 
             (this.givenname!=null &&
              this.givenname.equals(other.getGivenname()))) &&
            ((this.eptitlesortorder==null && other.getEptitlesortorder()==null) || 
             (this.eptitlesortorder!=null &&
              this.eptitlesortorder.equals(other.getEptitlesortorder()))) &&
            ((this.epsendtitlesortorder==null && other.getEpsendtitlesortorder()==null) || 
             (this.epsendtitlesortorder!=null &&
              this.epsendtitlesortorder.equals(other.getEpsendtitlesortorder()))) &&
            ((this.c==null && other.getC()==null) || 
             (this.c!=null &&
              this.c.equals(other.getC()))) &&
            ((this.dn==null && other.getDn()==null) || 
             (this.dn!=null &&
              this.dn.equals(other.getDn()))) &&
            ((this.epdefaultcompcode==null && other.getEpdefaultcompcode()==null) || 
             (this.epdefaultcompcode!=null &&
              this.epdefaultcompcode.equals(other.getEpdefaultcompcode()))) &&
            ((this.epgradeortitle==null && other.getEpgradeortitle()==null) || 
             (this.epgradeortitle!=null &&
              this.epgradeortitle.equals(other.getEpgradeortitle()))) &&
            ((this.episblue==null && other.getEpisblue()==null) || 
             (this.episblue!=null &&
              this.episblue.equals(other.getEpisblue()))) &&
            ((this.eppreferredlanguage==null && other.getEppreferredlanguage()==null) || 
             (this.eppreferredlanguage!=null &&
              this.eppreferredlanguage.equals(other.getEppreferredlanguage()))) &&
            ((this.epsecuritylevel==null && other.getEpsecuritylevel()==null) || 
             (this.epsecuritylevel!=null &&
              this.epsecuritylevel.equals(other.getEpsecuritylevel()))) &&
            ((this.epsendbusicode==null && other.getEpsendbusicode()==null) || 
             (this.epsendbusicode!=null &&
              this.epsendbusicode.equals(other.getEpsendbusicode()))) &&
            ((this.epsendcompanycode==null && other.getEpsendcompanycode()==null) || 
             (this.epsendcompanycode!=null &&
              this.epsendcompanycode.equals(other.getEpsendcompanycode()))) &&
            ((this.epsendcompanyname==null && other.getEpsendcompanyname()==null) || 
             (this.epsendcompanyname!=null &&
              this.epsendcompanyname.equals(other.getEpsendcompanyname()))) &&
            ((this.epsenddeptcode==null && other.getEpsenddeptcode()==null) || 
             (this.epsenddeptcode!=null &&
              this.epsenddeptcode.equals(other.getEpsenddeptcode()))) &&
            ((this.epsenddeptname==null && other.getEpsenddeptname()==null) || 
             (this.epsenddeptname!=null &&
              this.epsenddeptname.equals(other.getEpsenddeptname()))) &&
            ((this.epsendgradename==null && other.getEpsendgradename()==null) || 
             (this.epsendgradename!=null &&
              this.epsendgradename.equals(other.getEpsendgradename()))) &&
            ((this.epsendgradeortitle==null && other.getEpsendgradeortitle()==null) || 
             (this.epsendgradeortitle!=null &&
              this.epsendgradeortitle.equals(other.getEpsendgradeortitle()))) &&
            ((this.epsendregioncode==null && other.getEpsendregioncode()==null) || 
             (this.epsendregioncode!=null &&
              this.epsendregioncode.equals(other.getEpsendregioncode()))) &&
            ((this.epsendsecuritylevel==null && other.getEpsendsecuritylevel()==null) || 
             (this.epsendsecuritylevel!=null &&
              this.epsendsecuritylevel.equals(other.getEpsendsecuritylevel()))) &&
            ((this.epsendsuborgcode==null && other.getEpsendsuborgcode()==null) || 
             (this.epsendsuborgcode!=null &&
              this.epsendsuborgcode.equals(other.getEpsendsuborgcode()))) &&
            ((this.epsendtitlename==null && other.getEpsendtitlename()==null) || 
             (this.epsendtitlename!=null &&
              this.epsendtitlename.equals(other.getEpsendtitlename()))) &&
            ((this.epsendtitlenumber==null && other.getEpsendtitlenumber()==null) || 
             (this.epsendtitlenumber!=null &&
              this.epsendtitlenumber.equals(other.getEpsendtitlenumber()))) &&
            ((this.epuserlevel==null && other.getEpuserlevel()==null) || 
             (this.epuserlevel!=null &&
              this.epuserlevel.equals(other.getEpuserlevel()))) &&
            ((this.epvoipnumber==null && other.getEpvoipnumber()==null) || 
             (this.epvoipnumber!=null &&
              this.epvoipnumber.equals(other.getEpvoipnumber()))) &&
            ((this.facsimiletelephonenumber==null && other.getFacsimiletelephonenumber()==null) || 
             (this.facsimiletelephonenumber!=null &&
              this.facsimiletelephonenumber.equals(other.getFacsimiletelephonenumber()))) &&
            ((this.nickname==null && other.getNickname()==null) || 
             (this.nickname!=null &&
              this.nickname.equals(other.getNickname()))) &&
            ((this.preferredlanguage==null && other.getPreferredlanguage()==null) || 
             (this.preferredlanguage!=null &&
              this.preferredlanguage.equals(other.getPreferredlanguage()))) &&
            ((this.epindeptcode==null && other.getEpindeptcode()==null) || 
             (this.epindeptcode!=null &&
              this.epindeptcode.equals(other.getEpindeptcode()))) &&
            ((this.epindeptcodename==null && other.getEpindeptcodename()==null) || 
             (this.epindeptcodename!=null &&
              this.epindeptcodename.equals(other.getEpindeptcodename()))) &&
            ((this.epjob==null && other.getEpjob()==null) || 
             (this.epjob!=null &&
              this.epjob.equals(other.getEpjob()))) &&
            ((this.epjobname==null && other.getEpjobname()==null) || 
             (this.epjobname!=null &&
              this.epjobname.equals(other.getEpjobname()))) &&
            ((this.epsendsuborgname==null && other.getEpsendsuborgname()==null) || 
             (this.epsendsuborgname!=null &&
              this.epsendsuborgname.equals(other.getEpsendsuborgname()))) &&
            ((this.userpassword==null && other.getUserpassword()==null) || 
             (this.userpassword!=null &&
              this.userpassword.equals(other.getUserpassword()))) &&
            ((this.serverlocation==null && other.getServerlocation()==null) || 
             (this.serverlocation!=null &&
              this.serverlocation.equals(other.getServerlocation()))) &&
            ((this.epwithdrawdate==null && other.getEpwithdrawdate()==null) || 
             (this.epwithdrawdate!=null &&
              this.epwithdrawdate.equals(other.getEpwithdrawdate()))) &&
            ((this.currentpage==null && other.getCurrentpage()==null) || 
             (this.currentpage!=null &&
              this.currentpage.equals(other.getCurrentpage()))) &&
            ((this.totalcount==null && other.getTotalcount()==null) || 
             (this.totalcount!=null &&
              this.totalcount.equals(other.getTotalcount()))) &&
            ((this.totalpage==null && other.getTotalpage()==null) || 
             (this.totalpage!=null &&
              this.totalpage.equals(other.getTotalpage()))) &&
            ((this.rn==null && other.getRn()==null) || 
             (this.rn!=null &&
              this.rn.equals(other.getRn()))) &&
            ((this.epnative==null && other.getEpnative()==null) || 
             (this.epnative!=null &&
              this.epnative.equals(other.getEpnative()))) &&
            ((this.epuserclassify==null && other.getEpuserclassify()==null) || 
             (this.epuserclassify!=null &&
              this.epuserclassify.equals(other.getEpuserclassify()))) &&
            ((this.internalAttachOnly==null && other.getInternalAttachOnly()==null) || 
             (this.internalAttachOnly!=null &&
              this.internalAttachOnly.equals(other.getInternalAttachOnly()))) &&
            ((this.archiveUser==null && other.getArchiveUser()==null) || 
             (this.archiveUser!=null &&
              this.archiveUser.equals(other.getArchiveUser()))) &&
            ((this.epexternalmail==null && other.getEpexternalmail()==null) || 
             (this.epexternalmail!=null &&
              this.epexternalmail.equals(other.getEpexternalmail()))) &&
            ((this.epopencode==null && other.getEpopencode()==null) || 
             (this.epopencode!=null &&
              this.epopencode.equals(other.getEpopencode())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCn() != null) {
            _hashCode += getCn().hashCode();
        }
        if (getDepartment() != null) {
            _hashCode += getDepartment().hashCode();
        }
        if (getDepartmentnumber() != null) {
            _hashCode += getDepartmentnumber().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getEmployeenumber() != null) {
            _hashCode += getEmployeenumber().hashCode();
        }
        if (getEmployeetype() != null) {
            _hashCode += getEmployeetype().hashCode();
        }
        if (getEpaccountstatus() != null) {
            _hashCode += getEpaccountstatus().hashCode();
        }
        if (getEpbusicode() != null) {
            _hashCode += getEpbusicode().hashCode();
        }
        if (getEpbusiname() != null) {
            _hashCode += getEpbusiname().hashCode();
        }
        if (getEpenbusiname() != null) {
            _hashCode += getEpenbusiname().hashCode();
        }
        if (getEpencn() != null) {
            _hashCode += getEpencn().hashCode();
        }
        if (getEpendepartment() != null) {
            _hashCode += getEpendepartment().hashCode();
        }
        if (getEpendescription() != null) {
            _hashCode += getEpendescription().hashCode();
        }
        if (getEpengivenname() != null) {
            _hashCode += getEpengivenname().hashCode();
        }
        if (getEpengradename() != null) {
            _hashCode += getEpengradename().hashCode();
        }
        if (getEpenhomepostaladdress() != null) {
            _hashCode += getEpenhomepostaladdress().hashCode();
        }
        if (getEpenorganizationname() != null) {
            _hashCode += getEpenorganizationname().hashCode();
        }
        if (getEpenpostaladdress() != null) {
            _hashCode += getEpenpostaladdress().hashCode();
        }
        if (getEpenregionname() != null) {
            _hashCode += getEpenregionname().hashCode();
        }
        if (getEpensendbusiname() != null) {
            _hashCode += getEpensendbusiname().hashCode();
        }
        if (getEpensendcompanyname() != null) {
            _hashCode += getEpensendcompanyname().hashCode();
        }
        if (getEpensenddeptname() != null) {
            _hashCode += getEpensenddeptname().hashCode();
        }
        if (getEpensendgradename() != null) {
            _hashCode += getEpensendgradename().hashCode();
        }
        if (getEpensendregionname() != null) {
            _hashCode += getEpensendregionname().hashCode();
        }
        if (getEpensendsuborgname() != null) {
            _hashCode += getEpensendsuborgname().hashCode();
        }
        if (getEpensendtitlename() != null) {
            _hashCode += getEpensendtitlename().hashCode();
        }
        if (getEpensn() != null) {
            _hashCode += getEpensn().hashCode();
        }
        if (getEpensuborgname() != null) {
            _hashCode += getEpensuborgname().hashCode();
        }
        if (getEpentitle() != null) {
            _hashCode += getEpentitle().hashCode();
        }
        if (getEpgender() != null) {
            _hashCode += getEpgender().hashCode();
        }
        if (getEpgradename() != null) {
            _hashCode += getEpgradename().hashCode();
        }
        if (getEphomel() != null) {
            _hashCode += getEphomel().hashCode();
        }
        if (getEphomepostalcode() != null) {
            _hashCode += getEphomepostalcode().hashCode();
        }
        if (getEpid() != null) {
            _hashCode += getEpid().hashCode();
        }
        if (getEporganizationnumber() != null) {
            _hashCode += getEporganizationnumber().hashCode();
        }
        if (getEpregioncode() != null) {
            _hashCode += getEpregioncode().hashCode();
        }
        if (getEpregionname() != null) {
            _hashCode += getEpregionname().hashCode();
        }
        if (getEpregisternumber() != null) {
            _hashCode += getEpregisternumber().hashCode();
        }
        if (getEpsuborgcode() != null) {
            _hashCode += getEpsuborgcode().hashCode();
        }
        if (getEpsuborgname() != null) {
            _hashCode += getEpsuborgname().hashCode();
        }
        if (getEptitlename() != null) {
            _hashCode += getEptitlename().hashCode();
        }
        if (getEptitlenumber() != null) {
            _hashCode += getEptitlenumber().hashCode();
        }
        if (getEpuserstatus() != null) {
            _hashCode += getEpuserstatus().hashCode();
        }
        if (getHomephone() != null) {
            _hashCode += getHomephone().hashCode();
        }
        if (getHomepostaladdress() != null) {
            _hashCode += getHomepostaladdress().hashCode();
        }
        if (getL() != null) {
            _hashCode += getL().hashCode();
        }
        if (getMail() != null) {
            _hashCode += getMail().hashCode();
        }
        if (getMailHost() != null) {
            _hashCode += getMailHost().hashCode();
        }
        if (getMobile() != null) {
            _hashCode += getMobile().hashCode();
        }
        if (getO() != null) {
            _hashCode += getO().hashCode();
        }
        if (getOtherfacsimiletelephonenumber() != null) {
            _hashCode += getOtherfacsimiletelephonenumber().hashCode();
        }
        if (getOu() != null) {
            _hashCode += getOu().hashCode();
        }
        if (getPostaladdress() != null) {
            _hashCode += getPostaladdress().hashCode();
        }
        if (getPostalcode() != null) {
            _hashCode += getPostalcode().hashCode();
        }
        if (getSn() != null) {
            _hashCode += getSn().hashCode();
        }
        if (getTelephonenumber() != null) {
            _hashCode += getTelephonenumber().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getUserid() != null) {
            _hashCode += getUserid().hashCode();
        }
        if (getGivenname() != null) {
            _hashCode += getGivenname().hashCode();
        }
        if (getEptitlesortorder() != null) {
            _hashCode += getEptitlesortorder().hashCode();
        }
        if (getEpsendtitlesortorder() != null) {
            _hashCode += getEpsendtitlesortorder().hashCode();
        }
        if (getC() != null) {
            _hashCode += getC().hashCode();
        }
        if (getDn() != null) {
            _hashCode += getDn().hashCode();
        }
        if (getEpdefaultcompcode() != null) {
            _hashCode += getEpdefaultcompcode().hashCode();
        }
        if (getEpgradeortitle() != null) {
            _hashCode += getEpgradeortitle().hashCode();
        }
        if (getEpisblue() != null) {
            _hashCode += getEpisblue().hashCode();
        }
        if (getEppreferredlanguage() != null) {
            _hashCode += getEppreferredlanguage().hashCode();
        }
        if (getEpsecuritylevel() != null) {
            _hashCode += getEpsecuritylevel().hashCode();
        }
        if (getEpsendbusicode() != null) {
            _hashCode += getEpsendbusicode().hashCode();
        }
        if (getEpsendcompanycode() != null) {
            _hashCode += getEpsendcompanycode().hashCode();
        }
        if (getEpsendcompanyname() != null) {
            _hashCode += getEpsendcompanyname().hashCode();
        }
        if (getEpsenddeptcode() != null) {
            _hashCode += getEpsenddeptcode().hashCode();
        }
        if (getEpsenddeptname() != null) {
            _hashCode += getEpsenddeptname().hashCode();
        }
        if (getEpsendgradename() != null) {
            _hashCode += getEpsendgradename().hashCode();
        }
        if (getEpsendgradeortitle() != null) {
            _hashCode += getEpsendgradeortitle().hashCode();
        }
        if (getEpsendregioncode() != null) {
            _hashCode += getEpsendregioncode().hashCode();
        }
        if (getEpsendsecuritylevel() != null) {
            _hashCode += getEpsendsecuritylevel().hashCode();
        }
        if (getEpsendsuborgcode() != null) {
            _hashCode += getEpsendsuborgcode().hashCode();
        }
        if (getEpsendtitlename() != null) {
            _hashCode += getEpsendtitlename().hashCode();
        }
        if (getEpsendtitlenumber() != null) {
            _hashCode += getEpsendtitlenumber().hashCode();
        }
        if (getEpuserlevel() != null) {
            _hashCode += getEpuserlevel().hashCode();
        }
        if (getEpvoipnumber() != null) {
            _hashCode += getEpvoipnumber().hashCode();
        }
        if (getFacsimiletelephonenumber() != null) {
            _hashCode += getFacsimiletelephonenumber().hashCode();
        }
        if (getNickname() != null) {
            _hashCode += getNickname().hashCode();
        }
        if (getPreferredlanguage() != null) {
            _hashCode += getPreferredlanguage().hashCode();
        }
        if (getEpindeptcode() != null) {
            _hashCode += getEpindeptcode().hashCode();
        }
        if (getEpindeptcodename() != null) {
            _hashCode += getEpindeptcodename().hashCode();
        }
        if (getEpjob() != null) {
            _hashCode += getEpjob().hashCode();
        }
        if (getEpjobname() != null) {
            _hashCode += getEpjobname().hashCode();
        }
        if (getEpsendsuborgname() != null) {
            _hashCode += getEpsendsuborgname().hashCode();
        }
        if (getUserpassword() != null) {
            _hashCode += getUserpassword().hashCode();
        }
        if (getServerlocation() != null) {
            _hashCode += getServerlocation().hashCode();
        }
        if (getEpwithdrawdate() != null) {
            _hashCode += getEpwithdrawdate().hashCode();
        }
        if (getCurrentpage() != null) {
            _hashCode += getCurrentpage().hashCode();
        }
        if (getTotalcount() != null) {
            _hashCode += getTotalcount().hashCode();
        }
        if (getTotalpage() != null) {
            _hashCode += getTotalpage().hashCode();
        }
        if (getRn() != null) {
            _hashCode += getRn().hashCode();
        }
        if (getEpnative() != null) {
            _hashCode += getEpnative().hashCode();
        }
        if (getEpuserclassify() != null) {
            _hashCode += getEpuserclassify().hashCode();
        }
        if (getInternalAttachOnly() != null) {
            _hashCode += getInternalAttachOnly().hashCode();
        }
        if (getArchiveUser() != null) {
            _hashCode += getArchiveUser().hashCode();
        }
        if (getEpexternalmail() != null) {
            _hashCode += getEpexternalmail().hashCode();
        }
        if (getEpopencode() != null) {
            _hashCode += getEpopencode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Employee.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Employee"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cn");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Cn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("department");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Department"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("departmentnumber");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Departmentnumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("employeenumber");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Employeenumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("employeetype");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Employeetype"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epaccountstatus");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epaccountstatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epbusicode");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epbusicode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epbusiname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epbusiname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epenbusiname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epenbusiname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epencn");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epencn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ependepartment");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Ependepartment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ependescription");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Ependescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epengivenname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epengivenname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epengradename");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epengradename"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epenhomepostaladdress");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epenhomepostaladdress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epenorganizationname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epenorganizationname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epenpostaladdress");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epenpostaladdress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epenregionname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epenregionname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epensendbusiname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epensendbusiname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epensendcompanyname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epensendcompanyname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epensenddeptname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epensenddeptname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epensendgradename");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epensendgradename"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epensendregionname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epensendregionname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epensendsuborgname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epensendsuborgname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epensendtitlename");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epensendtitlename"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epensn");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epensn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epensuborgname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epensuborgname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epentitle");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epentitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epgender");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epgender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epgradename");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epgradename"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ephomel");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Ephomel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ephomepostalcode");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Ephomepostalcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epid");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eporganizationnumber");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Eporganizationnumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epregioncode");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epregioncode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epregionname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epregionname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epregisternumber");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epregisternumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsuborgcode");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsuborgcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsuborgname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsuborgname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eptitlename");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Eptitlename"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eptitlenumber");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Eptitlenumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epuserstatus");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epuserstatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("homephone");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Homephone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("homepostaladdress");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Homepostaladdress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("l");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "L"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mail");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Mail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mailHost");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "MailHost"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mobile");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Mobile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("o");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "O"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("otherfacsimiletelephonenumber");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Otherfacsimiletelephonenumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ou");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Ou"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postaladdress");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Postaladdress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postalcode");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Postalcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sn");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Sn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telephonenumber");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Telephonenumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userid");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Userid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("givenname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Givenname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eptitlesortorder");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Eptitlesortorder"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsendtitlesortorder");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsendtitlesortorder"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("c");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "C"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dn");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Dn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epdefaultcompcode");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epdefaultcompcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epgradeortitle");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epgradeortitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("episblue");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Episblue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eppreferredlanguage");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Eppreferredlanguage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsecuritylevel");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsecuritylevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsendbusicode");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsendbusicode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsendcompanycode");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsendcompanycode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsendcompanyname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsendcompanyname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsenddeptcode");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsenddeptcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsenddeptname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsenddeptname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsendgradename");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsendgradename"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsendgradeortitle");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsendgradeortitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsendregioncode");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsendregioncode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsendsecuritylevel");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsendsecuritylevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsendsuborgcode");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsendsuborgcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsendtitlename");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsendtitlename"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsendtitlenumber");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsendtitlenumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epuserlevel");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epuserlevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epvoipnumber");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epvoipnumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("facsimiletelephonenumber");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Facsimiletelephonenumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nickname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Nickname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preferredlanguage");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Preferredlanguage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epindeptcode");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epindeptcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epindeptcodename");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epindeptcodename"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epjob");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epjob"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epjobname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epjobname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epsendsuborgname");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epsendsuborgname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userpassword");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Userpassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serverlocation");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Serverlocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epwithdrawdate");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epwithdrawdate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentpage");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Currentpage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalcount");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Totalcount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalpage");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Totalpage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rn");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Rn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epnative");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epnative"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epuserclassify");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epuserclassify"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("internalAttachOnly");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "InternalAttachOnly"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archiveUser");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "ArchiveUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epexternalmail");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epexternalmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("epopencode");
        elemField.setXmlName(new javax.xml.namespace.QName("java:net.samsung.outldap.model", "Epopencode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
