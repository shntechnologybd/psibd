package org.openmrs.module.PSI.api;

import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.PSI.PSIServiceManagement;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PSIServiceManagementService extends OpenmrsService {
	
	public PSIServiceManagement saveOrUpdate(PSIServiceManagement psiServiceManagement);
	
	public List<PSIServiceManagement> getAll();
	
	public PSIServiceManagement findById(int id);
	
	public int getUnitCostByName(String naem);
	
	public void delete(int id);
	
}
