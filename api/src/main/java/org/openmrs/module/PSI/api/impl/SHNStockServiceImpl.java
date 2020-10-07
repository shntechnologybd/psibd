package org.openmrs.module.PSI.api.impl;

import java.util.List;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.PSI.SHNStock;
import org.openmrs.module.PSI.api.SHNStockService;
import org.openmrs.module.PSI.api.db.SHNStockDAO;
import org.openmrs.module.PSI.dto.SHNStockDTO;
import org.openmrs.module.PSI.dto.SHNStockDetailsDTO;

public class SHNStockServiceImpl extends BaseOpenmrsService implements SHNStockService {
	
	private SHNStockDAO dao;
	
	public SHNStockDAO getDao() {
		return dao;
	}

	public void setDao(SHNStockDAO dao) {
		this.dao = dao;
	}

	@Override
	public SHNStock saveOrUpdate(SHNStock shnStock) {
		// TODO Auto-generated method stub
		return dao.saveOrUpdate(shnStock);
	}

	@Override
	public SHNStock findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<SHNStock> getAllStockByClinicCode(String clinincCode) {
		// TODO Auto-generated method stub
		return dao.getAllStockByClinicCode(clinincCode);
	}

	@Override
	public List<SHNStockDetailsDTO> getStockDetailsByStockId(int stockId) {
		// TODO Auto-generated method stub
		return dao.getStockDetailsByStockId(stockId);
	}

	@Override
	public List<SHNStockDTO> findStockByPrductIdInvoiceAndExpiryDate(int productId,
			String invoiceNo, String expiryDate) {
		// TODO Auto-generated method stub
		return dao.findStockByPrductIdInvoiceAndExpiryDate(productId, invoiceNo, expiryDate);
	}

	@Override
	public String updateStockByEarliestExpiryDate(String eslipNo,String clinicCode) {
		// TODO Auto-generated method stub
		return dao.updateStockByEarliestExpiryDate(eslipNo, clinicCode);
	}

}
