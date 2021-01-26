package org.openmrs.module.PSI.api;

import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.PSI.SHNFollowUpAction;
import org.openmrs.module.PSI.dto.SHNFollowUPReportDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SHNFollowUpActionService  extends OpenmrsService {
	
	public SHNFollowUpAction saveOrUpdate(SHNFollowUpAction shnFollowUpAction);
	
	public SHNFollowUpAction findById(int id);
	
	public SHNFollowUpAction findByUuidAndEncounter (String uuid, String encounterUuid);
	
	public SHNFollowUpAction findByCodedConceptAndEncounter (int conceptId, String encounterUuid);
	
	public List<SHNFollowUpAction> getAllFollowUPAction();
	
	public List<SHNFollowUPReportDTO> getfollowUpReprt(String visitStartDate,String visitEnd,String followUpStartDate,String followUpEndDate,String moileNo,String patientHid, String clinicCode,String patientName,String defaultLoad);
	
}
