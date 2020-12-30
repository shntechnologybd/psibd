package org.openmrs.module.PSI.api.db;

import java.util.List;

import org.openmrs.module.PSI.SHNFollowUpAction;
import org.openmrs.module.PSI.dto.SHNFollowUPReportDTO;

public interface SHNFollowUpActionDAO {

	public SHNFollowUpAction saveOrUpdate(SHNFollowUpAction shnFollowUpAction);
	
	public SHNFollowUpAction findById(int id);
	
	public SHNFollowUpAction findByUuidAndEncounter (String uuid, String encounterUuid);
	
	public SHNFollowUpAction findByCodedConceptAndEncounter (int conceptId, String encounterUuid);
	
	public List<SHNFollowUpAction> getAllFollowUPAction();
	
	public List<SHNFollowUPReportDTO> getfollowUpReprt(String visitStartDate,String visitEnd,String followUpStartDate,String followUpEndDate,String moileNo,String patientHid, String clinicCode,String patientName);
}