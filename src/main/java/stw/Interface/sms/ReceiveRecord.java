package stw.Interface.sms;

public class ReceiveRecord {
	private int result;
	private int tcs_result;
	private String reserved;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getTcs_result() {
		return tcs_result;
	}
	public void setTcs_result(int tcs_result) {
		this.tcs_result = tcs_result;
	}

	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved1) {
		this.reserved = reserved1;
	}
}
