package org.openmrs.module.PSI.web.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openmrs.api.context.Context;
import org.openmrs.module.PSI.PSIClinicManagement;
import org.openmrs.module.PSI.api.PSIClinicManagementService;
import org.openmrs.module.PSI.utils.HttpResponse;
import org.openmrs.module.PSI.utils.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PSIClinicManageController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	private static final String OPENMRS_BASE_URL = "https://192.168.33.10/openmrs";
	
	final String USER_URL = "ws/rest/v1/user";
	
	@RequestMapping(value = "/module/PSI/addPSIClinic", method = RequestMethod.GET)
	public void addPSIClinic(HttpServletRequest request, HttpSession session, Model model) throws JSONException {
		HttpResponse op = HttpUtil.get(HttpUtil.removeEndingSlash(OPENMRS_BASE_URL) + "/" + USER_URL + "/", "v=default",
		    "superman", "Admin123");
		JSONObject body = new JSONObject(op.body());
		JSONArray users = new JSONArray(body.getJSONArray("results").toString());
		JSONArray usernamesArray = new JSONArray();
		for (int i = 0; i < users.length(); i++) {
			JSONObject nameObject = new JSONObject();
			JSONObject user = (JSONObject) users.get(i);
			JSONObject person = user.getJSONObject("person");
			nameObject.put("username", user.get("username"));
			nameObject.put("display", person.get("display"));
			usernamesArray.put(nameObject);
			
		}
		
		/*JSONArray userIds = new JSONArray();
		session.setAttribute("userIds", userIds.toString());*/
		session.setAttribute("message", "this clinic id is already taken ");
		session.setAttribute("users", usernamesArray.toString());
		model.addAttribute("pSIClinic", new PSIClinicManagement());
	}
	
	@RequestMapping(value = "/module/PSI/PSIClinicList", method = RequestMethod.GET)
	public void pSIClinicList(HttpServletRequest request, HttpSession session, Model model) {
		model.addAttribute("pSIClinics", Context.getService(PSIClinicManagementService.class).getAllClinic());
	}
	
	@RequestMapping(value = "/module/PSI/editPSIClinic", method = RequestMethod.GET)
	public void editPSIClinic(HttpServletRequest request, HttpSession session, Model model, @RequestParam int id) {
		model.addAttribute("pSIClinic", Context.getService(PSIClinicManagementService.class).findById(id));
		
	}
	
	@RequestMapping(value = "/module/PSI/deletePSIClinic", method = RequestMethod.GET)
	public ModelAndView deletePSIClinic(HttpServletRequest request, HttpSession session, Model model, @RequestParam int id) {
		Context.getService(PSIClinicManagementService.class).delete(id);
		return new ModelAndView("redirect:/module/PSI/PSIClinicList.form");
	}
	
	@RequestMapping(value = "/module/PSI/addPsiClinic", method = RequestMethod.POST)
	public ModelAndView addORUpdatePSIClinic(@ModelAttribute("psiClinic") PSIClinicManagement psiClinic,
	                                         HttpSession session, ModelMap model) throws Exception {
		
		PSIClinicManagement getClinicByClinicId = Context.getService(PSIClinicManagementService.class).findByClinicId(
		    psiClinic.getClinicId());
		if (getClinicByClinicId != null) {
			/*JSONArray userIds = new JSONArray();
			for (String user : users) {
				userIds.put(user);
			}*/
			//model.addAttribute("pSIClinic", psiClinic);
			//session.setAttribute("userIds", userIds.toString());
			session.setAttribute("message", "this clinic id is already taken ");
			return new ModelAndView("redirect:/module/PSI/addPSIClinic.form");
		} else {
			
			if (psiClinic != null) {
				log.info("saving new module objects...................");
				psiClinic.setDateCreated(new Date());
				psiClinic.setCreator(Context.getAuthenticatedUser());
				psiClinic.setUuid(UUID.randomUUID().toString());
				
				/*Set<PSIClinicUser> clinicUser = new HashSet<PSIClinicUser>();
				for (String user : users) {
					
					PSIClinicUser pSIClinicUser = new PSIClinicUser();
					pSIClinicUser.setUserName(user);
					pSIClinicUser.setDateCreated(new Date());
					pSIClinicUser.setCreator(Context.getAuthenticatedUser());
					pSIClinicUser.setUuid(UUID.randomUUID().toString());
					clinicUser.add(pSIClinicUser);
					
				}
				psiClinic.setpSIClinicUser(clinicUser);*/
				Context.openSession();
				Context.getService(PSIClinicManagementService.class).saveOrUpdateClinic(psiClinic);
				Context.clearSession();
				return new ModelAndView("redirect:/module/PSI/PSIClinicList.form");
			}
			
		}
		return null;
	}
}
