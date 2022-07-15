package com.yni.fta.common.tools;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import kr.yni.frame.mail.Mail;
import kr.yni.frame.resources.MessageResource;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.util.SystemHelper;

public class MailSupporter extends Mail {
	
	private static Log log = LogFactory.getLog(MailSupporter.class);
	
	/**
	 * 기본 생성자
	 */
	public MailSupporter() {
		super();
	}
	
	/**
	 * resource명에 대한 mail을 셋팅한다.
	 * 
	 * resource명은 회사코드+"_MailSender"로 지정할 것
	 * JavaMailSenderImpl sender = (JavaMailSenderImpl) applicationContext.getBean(resource);
	 * @param resource
	 */
	public MailSupporter(JavaMailSenderImpl resource) {
		super(resource);
	}
	
	public String getSmtpUserMail() throws Exception {
		String mailAdr = "";
		
		if(this.getUsername().indexOf("@") <= 0) {
			String host = this.getHost();
			
			if(host.indexOf("naver") > 0) mailAdr = this.getUsername() + "@naver.com";
			else if(host.indexOf("gmail") > 0) mailAdr = this.getUsername() + "@gmail.com";
			else if(host.indexOf("daum") > 0) mailAdr = this.getUsername() + "@daum.net";
			else if(host.indexOf("phakr") > 0) mailAdr = this.getUsername() + "@phakr.com";
			else {
				StringBuffer sb = new StringBuffer();
				String[] hostAry = host.split("\\.");
				
				if(hostAry.length > 0) {
					for(int i = 1; i < hostAry.length; i++) {
						sb.append(hostAry[i]);
						
						if((i+1) < hostAry.length) {
							sb.append(".");
						}
					}
				}
				
				mailAdr = this.getUsername() + "@" + sb.toString();
			}
		} else {
			mailAdr = StringHelper.null2void(this.getUsername());
		}
		
		if(log.isDebugEnabled()) log.debug("SMTP's user name = " + mailAdr);
		
		return mailAdr;
	}
	
	/**
	 * 원산지 확인서 요청메일 내용 생성
	 * @param map
	 * @param ilist
	 * @param messageSource
	 * @return
	 * @throws Exception
	 */
	public String getCooContents(Map map, List ilist, MessageResource messageSource, String defaultLang) throws Exception {
		StringBuffer mailMsg = new StringBuffer();
		StringBuffer requestHtml = new StringBuffer();
		StringBuffer itemHtml = new StringBuffer();
		StringBuffer signHtml = new StringBuffer();
		
        String css_header =  "font-size:9pt;background:#f1f1f1;padding:1px 5px 0px 5px;color:#484242;font-weight:bold;height:26px;padding-bottom:1px;border-right:1px solid #b0bcd3;border-bottom:1px solid #b0bcd3;";
        String css_contens = "font-size:9pt;background:#ffffff;padding:0px 5px 0px 5px;height:26px;padding-bottom:1px;border-right:1px solid #b0bcd3;border-bottom:1px solid #b0bcd3;";
        String css_title = "color:#545454;font-weight:bold;padding:0;padding:5px 0 0 3px;height:20px;font-size:10pt; letter-spacing:-1px;";
        String css_sign = "color:#545454;font-family:Dotum, Gulim, Arial, Helvetica, sans-serif;font-size:11px;font-weight:normal;text-align:center;padding:10px 0 10px 0;";
        
        String coments = StringHelper.null2void(map.get("COMMENTS_TM"));
        Locale locale = SystemHelper.getLocale(defaultLang);
        String partNumViewYn = StringHelper.null2void(map.get("PART_NUM_VIEW_YN")); //PartNo,ECNCode 표시 여부
        
        if(!coments.isEmpty()) {
        	coments = coments.replaceAll("\n", "<br>");
        }
        
        requestHtml.append("<div style=\""+css_title+"\">"+messageSource.getMessage("REQ, INFMT", null, locale)+"</div>");
        requestHtml.append("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-left:1px solid #b0bcd3;border-top:1px solid #b0bcd3;\">");
        requestHtml.append("<colgroup><col style=\"width:18%;\"/><col style=\"width:32%;\"/><col style=\"width:18%;\"/><col style=\"width:32%;\"/></colgroup>");
        requestHtml.append("<tr>");
        requestHtml.append("<td style=\""+css_header+"\">"+messageSource.getMessage("CONPY, NAME", null, locale)+"</td>");
        requestHtml.append("<td colspan=\"3\" style=\""+css_contens+"\">"+StringHelper.null2void(map.get("COMPANY_NAME"))+"</td>");
        requestHtml.append("</tr>");
        requestHtml.append("<tr>");
        requestHtml.append("<td style=\""+css_header+"\">"+messageSource.getMessage("THPIC", null, locale)+"</td>");
        requestHtml.append("<td style=\""+css_contens+"\">"+StringHelper.null2void(map.get("WRITER"))+"</td>");
        requestHtml.append("<td style=\""+css_header+"\">"+messageSource.getMessage("CNTACT", null, locale)+"</td>");
        requestHtml.append("<td style=\""+css_contens+"\">"+StringHelper.null2void(map.get("WRITE_CONTECTS"))+"</td>");
        requestHtml.append("</tr>");
        requestHtml.append("<tr>");
        requestHtml.append("<td style=\""+css_header+"\">"+messageSource.getMessage("SUNMT, RSTS", null, locale)+"</td>");
        requestHtml.append("<td style=\""+css_contens+"\">"+StringHelper.null2void(map.get("APPLY_DATE")) +" ~ " + StringHelper.null2void(map.get("END_DATE")) +"</td>");
        requestHtml.append("<td style=\""+css_header+"\">"+messageSource.getMessage("REQ, REASN", null, locale)+"</td>");
        requestHtml.append("<td style=\""+css_contens+"\">"+StringHelper.null2void(map.get("REQUEST_TYPE_NAME"))+"</td>");
        requestHtml.append("</tr>");
        requestHtml.append("<tr height=\"150px\">");
        requestHtml.append("<td style=\""+css_header+"\">"+messageSource.getMessage("COMTS", null, locale)+"</td>");
        requestHtml.append("<td valign=\"top\" colspan=\"3\" style=\""+css_contens+"\">"+coments+"</td>");
        requestHtml.append("</tr>");
        requestHtml.append("</table>");
        
        if(ilist.size() > 0) {
	        itemHtml.append("<div style=\""+css_title+"\">"+messageSource.getMessage("REQ, PRDT, LIST", null, locale)+"</div>");
	        itemHtml.append("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-left:1px solid #b0bcd3;border-top:1px solid #b0bcd3;\">");
	        itemHtml.append("<tr>");
	        itemHtml.append("<td style=\""+css_header+"\">No</td>");
	        itemHtml.append("<td style=\""+css_header+"\">"+messageSource.getMessage("ITEM,CODE", null, locale)+"</td>");
	        if(partNumViewYn.equals("Y")) {
	        	itemHtml.append("<td style=\""+css_header+"\">"+messageSource.getMessage("Part,MSSNM", null, locale)+"</td>");
	        	itemHtml.append("<td style=\""+css_header+"\">"+messageSource.getMessage("ECN,CODE", null, locale)+"</td>");
	        }
	        itemHtml.append("<td style=\""+css_header+"\">"+messageSource.getMessage("ITEM,NAME", null, locale)+"</td>");
	        itemHtml.append("<td style=\""+css_header+"\">"+messageSource.getMessage("TXT_FTA_GRP_CD", null, locale)+"</td>");
	        itemHtml.append("<td style=\""+css_header+"\">"+messageSource.getMessage("REQ, STAUS", null, locale)+"</td>");
	        itemHtml.append("</tr>");
	        
	        for(int i = 0; i < ilist.size(); i++) {
	        	Map iMap = (Map) ilist.get(i);
	        	
	            itemHtml.append("<tr>");
	            itemHtml.append("<td style=\""+css_contens+"\"><center>" + (i+1) + "</center></td>");
	            itemHtml.append("<td style=\""+css_contens+"\">" + StringHelper.null2void(iMap.get("ITEM_CD")) + "</td>");
	            if(partNumViewYn.equals("Y")) {
	            	itemHtml.append("<td style=\""+css_contens+"\">" + StringHelper.null2void(iMap.get("TEMP_COLUMN3")) + "</td>");
	            	itemHtml.append("<td style=\""+css_contens+"\">" + StringHelper.null2void(iMap.get("TEMP_COLUMN4")) + "</td>");
	            }
	            itemHtml.append("<td style=\""+css_contens+"\">" + StringHelper.null2void(iMap.get("ITEM_NAME")) + "</td>");
	            itemHtml.append("<td style=\""+css_contens+"\">" + StringHelper.null2void(iMap.get("FTA_GROUP_NAME")) + "</td>");
	            itemHtml.append("<td style=\""+css_contens+"\">" + StringHelper.null2void(iMap.get("STATUS_NAME")) + "</td>");
	            itemHtml.append("</tr>");
	        }
	        
	        itemHtml.append("</table>");
        }
        signHtml.append("<div style=\""+css_sign+"\">"+StringHelper.null2void(map.get("SIGNATURE"))+"</div>");
        
        mailMsg.append(requestHtml.toString());
        mailMsg.append(itemHtml.toString());
        mailMsg.append(signHtml.toString());
        
		return mailMsg.toString();
	}
	
	/**
	 * 미결요청서 수집에 대해 담당자에게 알리는 메시지
	 * @param pendCnt
	 * @return
	 * @throws Exception
	 */
	public String getPendingContent(String pendCnt) throws Exception {
		StringBuffer html = new StringBuffer();

		html.append("<html>");
		html.append("  <head>");
		html.append("  <title>FTA CERTIFICATE OF ORIGIN </title>");
		html.append("  </head>");
		html.append("  <body style='font-family: Gulim, arial; font-size:9pt; color:#333333; margin:0; padding:0; line-height:160%;'>");
		html.append("  <table width='100%' height='100%' cellpadding='0' cellspacing='0' border='0'>");
		html.append("    <tr>");
		html.append("      <td align='center' valign='top'>");
		html.append("        <table width='770' height='' cellpadding='0' cellspacing='0' border='0'>");
		html.append("          <tr>");
		html.append("            <td height='30' style='font-weight:bold; color:#002f81; font-size:10pt; padding:0 0 0 0px;'> FTA원산지 관리 시스템에서 발송된 메일입니다.</td>");
		html.append("          </tr>");
		html.append("          <tr>");
		html.append("            <td height='30' style='font-weight:bold; color:#002f81; font-size:10pt; padding:0 0 0 0px;'> 현재 원산지 미결요청이  " + pendCnt + " 건 접수되었습니다.</td>");
		html.append("          </tr>");
		html.append("          <tr>");
		html.append("            <td height='30' style='font-weight:bold; color:#002f81; font-size:10pt; padding:0 0 0 0px;'> FTA원산지 관리 시스템을 통해 원산지 증명서를 등록해 주시기 바랍니다.</td>");
		html.append("          </tr>");
		html.append("          <tr>");
		html.append("            <td height='30' style='font-weight:bold; color:#ff0000; font-size:10pt; padding:0 0 0 0px;'> [본 메일은 사용자 등록 시 바츠ID을 기록한 사용자에게만 전송되는 메시지입니다.]</td>");
		html.append("          </tr>");
		html.append("        </table>");
		html.append("      </td>");
		html.append("    </tr>");
		html.append("  </table>");
		html.append("  </body>");
		html.append("  </html>");

		return html.toString();
	}
	
	/**
	 * 메일주소만 찾아서 리턴하는 매소드
	 * 
	 * @param receivers
	 * @return
	 * @throws Exception
	 */
	public String getEmailAddr(String mails) throws Exception {
		StringBuffer recs = new StringBuffer();
		
		if(mails != null) {
			String[] resAry = mails.split(",");
			
			for(int i = 0; i < resAry.length; i++) {
				int sidx = resAry[i].lastIndexOf("<");
				int eidx = resAry[i].lastIndexOf(">");
				
				if(sidx > -1 && eidx > -1) {
					recs.append(resAry[i].substring(sidx+1, eidx));
				} else {
					recs.append(resAry[i]);
				}
				if(i != (resAry.length-1)) recs.append(",");
				
//				log.debug("start index = " + sidx + " , end index = " + eidx + " > result = " + resAry[i].substring(sidx, eidx));
			}
		}
		
		log.debug("final value = " + recs.toString());
		
		return recs.toString();
	}
	
}
