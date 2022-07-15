package com.yni.fta.common.parameter;

import java.util.Locale;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import kr.yni.frame.Constants;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.util.SystemHelper;

public class YniAbstractBatch {
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Autowired 
	protected Properties properties;
	
	@Resource(name = "messageSource")
	protected MessageSource messageSource;
	
	public YniAbstractBatch() { }

	/**
	 * message_locale.properties에 등록된 메시지를 구한다.
	 * 
	 * @param messageKey 메시지 코드
	 * @return String
	 * @exception Exception
	 */
	protected String getMessage(String messageKey)
			throws Exception {
		return messageSource.getMessage(messageKey, null, null);
	}

	/**
	 * message_locale.properties에 등록된 메시지를 구한다.
	 * 
	 * @param messageKey 메시지 코드
	 * @param messageParameters 맵핑할 배열
	 * @return String
	 * @exception Exception
	 */
	protected String getMessage(String messageKey, String messageParameters[])
			throws Exception {
		return messageSource.getMessage(messageKey, messageParameters, null);
	}
	
	/**
	 * message_locale.properties에 등록된 메시지를 구한다.
	 * 
	 * @param messageKey 메시지 코드
	 * @param messageParameters 맵핑할 배열
	 * @param locale 언어(KOR, ENG, LOC)
	 * @return String
	 * @exception Exception
	 */
	protected String getMessage(String messageKey, String messageParameters[],
			String locale) throws Exception {
		Locale rLocale = SystemHelper.getLocale(locale);
		
		return messageSource.getMessage(messageKey, messageParameters, rLocale);
	}

	/**
	 * Get Process Message
	 * 
	 * @param parmOne
	 *            messageKey
	 * @param paramTwo
	 *            messageParameters
	 * @return String
	 * @exception Exception
	 */
	protected String getProcessMssage(HttpSession session, String messageArgKey)
			throws Exception {
		return getMessage("MSG_SUCCESS_PROCESS",
				new String[] { getMessage(messageArgKey, null, Constants.DEFAULT_LANGUAGE) },
									  Constants.DEFAULT_LANGUAGE);

	}

	/**
	 * Get Save Message
	 * 
	 * @param parmOne
	 *            messageKey
	 * @param paramTwo
	 *            messageParameters
	 * @return String
	 * @exception Exception
	 */
	protected String getSaveSuccessMsg(HttpSession session) throws Exception {
		return getProcessMssage(session, "MSG_SAVE");
	}

	/**
	 * Get Modify Message
	 * 
	 * @param parmOne
	 *            messageKey
	 * @param paramTwo
	 *            messageParameters
	 * @return String
	 * @exception Exception
	 */
	protected String getModifySuccessMsg(HttpSession session) throws Exception {
		return getProcessMssage(session, "MSG_MODIFY");
	}

	/**
	 * Get Delete Message
	 * 
	 * @param parmOne
	 *            messageKey
	 * @param paramTwo
	 *            messageParameters
	 * @return String
	 * @exception Exception
	 */
	protected String getDeleteSuccessMsg(HttpSession session) throws Exception {
		return getProcessMssage(session, "MSG_DELETE");
	}

	/**
	 * Get Save Message
	 * 
	 * @param parmOne
	 *            messageKey
	 * @param paramTwo
	 *            messageParameters
	 * @return String
	 * @exception Exception
	 */
	protected String getOriginDeterminSuccessMsg(HttpSession session)
			throws Exception {
		return getProcessMssage(session, "MSG_ORIGIN_SUCESS");
	}

	/**
	 * Get Save Message
	 * 
	 * @param parmOne
	 *            messageKey
	 * @param paramTwo
	 *            messageParameters
	 * @return String
	 * @exception Exception
	 */
	protected String getPreDeterminSuccessMsg(HttpSession session)
			throws Exception {
		return getProcessMssage(session, "MSG_PRE_SUCESS");
	}

	/**
	 * Get Save Message
	 * 
	 * @param parmOne
	 *            messageKey
	 * @param paramTwo
	 *            messageParameters
	 * @return String
	 * @exception Exception
	 */
	protected String getSimulationSuccessMsg(HttpSession session)
			throws Exception {
		return getProcessMssage(session, "MSG_SIMULATION_SUCESS");
	}
	

	/**
	 * 
	 * @param session
	 * @param messageArgKey
	 * @return
	 * @throws Exception
	 */
	protected String getReturnMsg(HttpSession session, String messageArgKey)
			throws Exception {
		return getProcessMssage(session, messageArgKey);
	}	
	
	/**
	 * 예외처리 메시지를 조회한다.
	 * 
	 * @param request 요청정보
	 * @param exception 예외사항
	 * @return 예외메세지
	 */
	protected String getExceptionMessage(HttpServletRequest request,
			Exception exception) {
		return getExceptionMessage(request, exception, null);
	}
			
	/**
	 * 예외처리 메시지를 조회한다.
	 * 
	 * @param request 요청정보
	 * @param exception 예외사항
	 * @param messageKey 메시지 코드
	 * @return 예외메세지
	 */
	protected String getExceptionMessage(HttpServletRequest request,
			Exception exception, String messageKey) {
		String returnMessage = null;
		Locale rLocale = SystemHelper.getLocale(null);
		// 메시지 구하기
		try {
			if(!StringHelper.isNull(messageKey)) {
				returnMessage = messageSource.getMessage(messageKey, null, rLocale); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnMessage;
	}
	
}
