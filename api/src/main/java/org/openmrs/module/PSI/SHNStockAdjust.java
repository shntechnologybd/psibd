package org.openmrs.module.PSI;

import java.io.Serializable;
import java.util.Date;

import org.openmrs.BaseOpenmrsData;

public class SHNStockAdjust  extends BaseOpenmrsData implements Serializable {

	private static final long serialVersionUID = 1L;

	private int adjustId;
	
	private int productId;
	
	private int clinicId;
	
	private String clinicCode;
	
	private Date adjustDate;
	
	private int previousStock;
	
	private int changedStock;
	
	private String adjustReason;

	public int getAdjustId() {
		return adjustId;
	}

	public void setAdjustId(int adjustId) {
		this.adjustId = adjustId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getClinicId() {
		return clinicId;
	}

	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}

	public String getClinicCode() {
		return clinicCode;
	}

	public void setClinicCode(String clinicCode) {
		this.clinicCode = clinicCode;
	}

	public Date getAdjustDate() {
		return adjustDate;
	}

	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}

	public int getPreviousStock() {
		return previousStock;
	}

	public void setPreviousStock(int previousStock) {
		this.previousStock = previousStock;
	}

	public int getChangedStock() {
		return changedStock;
	}

	public void setChangedStock(int changedStock) {
		this.changedStock = changedStock;
	}

	public String getAdjustReason() {
		return adjustReason;
	}

	public void setAdjustReason(String adjustReason) {
		this.adjustReason = adjustReason;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	
	
}