package stw.Interface.sms;

public class SendRecord {
	private String user_id;
	private int schedule_type;
	private String subject;
	private String sms_msg;
	private String callback_url;
	private String now_date;
	private String send_date;
	private String callback;
	private int dest_type;
	private int dest_count;
	private String dest_info;
	private String kt_office_code;
	private String cdr_id;
	private String reserved1;
	private String reserved2;
	private String reserved3;
	private String reserved4;
	private String reserved5;
	private String reserved6;
	private int send_status;
	private int send_count;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getSchedule_type() {
		return schedule_type;
	}
	public void setSchedule_type(int schedule_type) {
		this.schedule_type = schedule_type;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSms_msg() {
		return sms_msg;
	}
	public void setSms_msg(String sms_msg) {
		this.sms_msg = sms_msg;
	}
	public String getCallback_url() {
		return callback_url;
	}
	public void setCallback_url(String callback_url) {
		this.callback_url = callback_url;
	}
	public String getNow_date() {
		return now_date;
	}
	public void setNow_date(String now_date) {
		this.now_date = now_date;
	}
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public int getDest_type() {
		return dest_type;
	}
	public void setDest_type(int dest_type) {
		this.dest_type = dest_type;
	}
	public int getDest_count() {
		return dest_count;
	}
	public void setDest_count(int dest_count) {
		this.dest_count = dest_count;
	}
	public String getDest_info() {
		return dest_info;
	}
	public void setDest_info(String dest_info) {
		this.dest_info = dest_info;
	}
	public String getKt_office_code() {
		return kt_office_code;
	}
	public void setKt_office_code(String kt_office_code) {
		this.kt_office_code = kt_office_code;
	}
	public String getCdr_id() {
		return cdr_id;
	}
	public void setCdr_id(String cdr_id) {
		this.cdr_id = cdr_id;
	}
	public String getReserved1() {
		return reserved1;
	}
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}
	public String getReserved2() {
		return reserved2;
	}
	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
	public String getReserved3() {
		return reserved3;
	}
	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}
	public String getReserved4() {
		return reserved4;
	}
	public void setReserved4(String reserved4) {
		this.reserved4 = reserved4;
	}
	public String getReserved5() {
		return reserved5;
	}
	public void setReserved5(String reserved5) {
		this.reserved5 = reserved5;
	}
	public String getReserved6() {
		return reserved6;
	}
	public void setReserved6(String reserved6) {
		this.reserved6 = reserved6;
	}
	public int getSend_status() {
		return send_status;
	}
	public void setSend_status(int send_status) {
		this.send_status = send_status;
	}
	public int getSend_count() {
		return send_count;
	}
	public void setSend_count(int send_count) {
		this.send_count = send_count;
	}
}
