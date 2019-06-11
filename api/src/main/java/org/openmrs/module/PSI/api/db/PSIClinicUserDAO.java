package org.openmrs.module.PSI.api.db;

import java.util.List;

import org.openmrs.module.PSI.PSIClinicUser;
import org.openmrs.module.PSI.dto.UserDTO;

public interface PSIClinicUserDAO {
	
	public PSIClinicUser saveOrUpdate(PSIClinicUser psiClinicUser);
	
	public List<PSIClinicUser> getAll();
	
	public PSIClinicUser findById(int id);
	
	public PSIClinicUser findByUserName(String username);
	
	public UserDTO findByUserNameFromOpenmrs(String username);
	
	public List<UserDTO> findUserByCode(String code);
	
	public void delete(int id);
	
}
