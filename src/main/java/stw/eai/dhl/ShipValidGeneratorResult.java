/**
 * @author ally
 * This Class is Return Object for stw.eai.dhl.ProcessShipValid.processing(ShipmentValidateRequestEA);
 */
package stw.eai.dhl;

public class ShipValidGeneratorResult {
	
	private String awbNumber; 
	private String resultDescription;
	
	public String getAwbNumber() {
		return awbNumber;
	}
	
	public void setAwbNumber(String awbNumber) {
		this.awbNumber = awbNumber;
	}
	
	public String getResultDescription() {
		return resultDescription;
	}

	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	
}
