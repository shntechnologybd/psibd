package org.openmrs.module.PSI;

import java.io.Serializable;

import org.openmrs.BaseOpenmrsData;

public class PSIServiceManagement extends BaseOpenmrsData implements Serializable {
	
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private int sid;
	
	private String name;
	
	private String code;
	
	private String category;
	
	private String provider;
	
	private int unitCost;
	
	private long timestamp;
	
	private String field1;
	
	private String field2;
	
	private int field3;
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	public int getSid() {
		return sid;
	}
	
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getProvider() {
		return provider;
	}
	
	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	public int getUnitCost() {
		return unitCost;
	}
	
	public void setUnitCost(int unitCost) {
		this.unitCost = unitCost;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getField1() {
		return field1;
	}
	
	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	public String getField2() {
		return field2;
	}
	
	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	public int getField3() {
		return field3;
	}
	
	public void setField3(int field3) {
		this.field3 = field3;
	}
	
}
