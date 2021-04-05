package org.openmrs.module.PSI.web.listener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openmrs.api.context.Context;
import org.openmrs.module.PSI.PSIClinicManagement;
import org.openmrs.module.PSI.PSIDHISException;
import org.openmrs.module.PSI.PSIDHISMarker;
import org.openmrs.module.PSI.PSIServiceProvision;
import org.openmrs.module.PSI.SHNDhisEncounterException;
import org.openmrs.module.PSI.SHNDhisIndicatorDetails;
import org.openmrs.module.PSI.SHNDhisMultipleChoiceObsElement;
import org.openmrs.module.PSI.SHNDhisObsElement;
import org.openmrs.module.PSI.SHNStock;
import org.openmrs.module.PSI.SHNStockAdjust;
import org.openmrs.module.PSI.SHNVoidedMoneyReceiptLog;
import org.openmrs.module.PSI.api.PSIClinicManagementService;
import org.openmrs.module.PSI.api.PSIClinicUserService;
import org.openmrs.module.PSI.api.PSIDHISExceptionService;
import org.openmrs.module.PSI.api.PSIDHISMarkerService;
import org.openmrs.module.PSI.api.PSIServiceProvisionService;
import org.openmrs.module.PSI.api.SHNDhisEncounterExceptionService;
import org.openmrs.module.PSI.api.SHNDhisObsElementService;
import org.openmrs.module.PSI.api.SHNStockService;
import org.openmrs.module.PSI.api.SHNVoidedMoneyReceiptLogService;
import org.openmrs.module.PSI.converter.DHISDataConverter;
import org.openmrs.module.PSI.converter.DhisObsEventDataConverter;
import org.openmrs.module.PSI.converter.DhisObsJsonDataConverter;
import org.openmrs.module.PSI.converter.SHNStockAdjustDataConverter;
import org.openmrs.module.PSI.dhis.service.PSIAPIServiceFactory;
import org.openmrs.module.PSI.dto.EventReceordDTO;
import org.openmrs.module.PSI.dto.ShnIndicatorDetailsDTO;
import org.openmrs.module.PSI.dto.UserDTO;
import org.openmrs.module.PSI.utils.DHISMapper;
import org.openmrs.module.PSI.utils.PSIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;

@Service
@EnableScheduling
@Configuration
@EnableAsync
@Controller
public class DeleteMoneyReceiptAndOthersListener {
	
	@Autowired
	private PSIAPIServiceFactory psiapiServiceFactory;
	

	private static ResourceBundle resource = ResourceBundle.getBundle("deploymentConfig");
	
	private final static String DHIS2BASEURL = resource.getString("dhis2BaseUrl");
	
	private final String VERSIONAPI = DHIS2BASEURL + "/api/metadata/version";
	
	private final String EVENTURL = DHIS2BASEURL + "/api/events";
	
	private final String DATASETURL = DHIS2BASEURL + "/api/dataValueSets";
	
	private final String GETEVENTURL = DHIS2BASEURL + "/api/events.json";


	
	protected final Log log = LogFactory.getLog(getClass());
	
	@SuppressWarnings("rawtypes")
	public void sendData() throws Exception {
		boolean status = true;
			try {
				psiapiServiceFactory.getAPIType("dhis2").get("", "", VERSIONAPI);
				
			}
			catch (Exception e) {
				
				status = false;
			}
		if (status) {				
				try {
					//sendIndicatorDataToDhis();
				}
				catch (Exception e) {
					
				}
				try {
					deleteMoneyReceiptFromDhis2();
				}
				catch (Exception e) {
					
				}
		}

	}
	

	public void sendIndicatorDataToDhis() {
		
		SHNDhisIndicatorDetails shnDhisIndicatorDetails = null;
		
		try {
//			int calculateCountOfFpConraceptiveMethod = Context.getService(SHNDhisObsElementService.class).calculateCountOfFpConraceptiveMethod();
//			log.error("calculateCountOfFpConraceptiveMethod" + calculateCountOfFpConraceptiveMethod);
//	
//			int calculateCountOfFphypertensionAndDiabetic = Context.getService(SHNDhisObsElementService.class).calculateCountOfFphypertensionAndDiabetic();
//			log.error("calculateCountOfFphypertensionAndDiabetic" + calculateCountOfFphypertensionAndDiabetic);
//			
//			int calculateCountOfFpPermanentMethod = Context.getService(SHNDhisObsElementService.class).calculateCountOfFpPermanentMethod();
//			log.error("calculateCountOfFpPermanentMethod" + calculateCountOfFpPermanentMethod);
//			
//			int calculateCountOfFpAncTakenAtleastOne = Context.getService(SHNDhisObsElementService.class).calculateCountOfFpAncTakenAtleastOne();
//			log.error("calculateCountOfFpAncTakenAtleastOne" + calculateCountOfFpAncTakenAtleastOne);
//			
//			int calculatePercentageOfFp = Context.getService(SHNDhisObsElementService.class).calculatePercentageOfFp();
//			log.error("calculatePercentageOfFp" + calculatePercentageOfFp);
			
			int getCompletedAncFullCountFromMoneyReceipt = Context.getService(SHNDhisObsElementService.class).getCompletedAncFullCountFromMoneyReceipt();
			log.error("getCompletedAncFullCountFromMoneyReceipt" + getCompletedAncFullCountFromMoneyReceipt);
			
			ShnIndicatorDetailsDTO shnIndicatorDetailsDTO = new ShnIndicatorDetailsDTO();
			
//			shnIndicatorDetailsDTO.setFpContraceptiveMethod(calculateCountOfFpConraceptiveMethod);
//			shnIndicatorDetailsDTO.setFpHypertensionAndDiabetic(calculateCountOfFphypertensionAndDiabetic);
//			shnIndicatorDetailsDTO.setFpPermanentMethod(calculateCountOfFpPermanentMethod);
//			shnIndicatorDetailsDTO.setFpAncTakenAtleastOne(calculateCountOfFpAncTakenAtleastOne);
//			shnIndicatorDetailsDTO.setCalculatePercentageOfFp(calculatePercentageOfFp);
			shnIndicatorDetailsDTO.setCalculateAncAllTakenFullCount(getCompletedAncFullCountFromMoneyReceipt);
			
			JSONObject indicatorData = DHISDataConverter.toConvertDhisIndicatorData(shnIndicatorDetailsDTO);
			log.error("indicatorData" + indicatorData.toString());
			JSONObject eventResponse = new JSONObject();
			shnDhisIndicatorDetails = Context.getService(SHNDhisObsElementService.class).getDhisIndicatorByType("INDICATOR");
				eventResponse = psiapiServiceFactory.getAPIType("dhis2").add("", indicatorData, DATASETURL);

					String importStatus = eventResponse.getString("status");
					if (importStatus.equalsIgnoreCase("SUCCESS")) {
						log.error("response has SUCCESS" + importStatus);
						if(shnDhisIndicatorDetails == null) {
							shnDhisIndicatorDetails = new SHNDhisIndicatorDetails();
						}
						updateIndicatorDetailsStatus(shnDhisIndicatorDetails,indicatorData.toString(),DATASETURL,PSIConstants.SUCCESSSTATUS,eventResponse.toString(),"","zPaSPZ5vk4n");
					}
					else {
						log.error("response has not SUCCESS" + importStatus);
						if(shnDhisIndicatorDetails == null) {
							shnDhisIndicatorDetails = new SHNDhisIndicatorDetails();
						}
						updateIndicatorDetailsStatus(shnDhisIndicatorDetails,indicatorData.toString(),DATASETURL,PSIConstants.FAILEDSTATUS,eventResponse.toString(),"Check response for details error","zPaSPZ5vk4n");
					}
			}
	
		catch (Exception ex) {
			if(shnDhisIndicatorDetails == null) {
				shnDhisIndicatorDetails = new SHNDhisIndicatorDetails();
			}
			updateIndicatorDetailsStatus(shnDhisIndicatorDetails,"Internal Error Occured",DATASETURL,PSIConstants.FAILEDSTATUS,"Check Error Message for Details",ex.getMessage(),"zPaSPZ5vk4n");
		}
	}
	
	
	private void updateIndicatorDetailsStatus(SHNDhisIndicatorDetails shnDhisIndicatorDetails,
			String postJson,String url, int status,
			String response, String error,String referenceId) {
		Context.openSession();

		shnDhisIndicatorDetails.setError(error);
		shnDhisIndicatorDetails.setPostJson(postJson.toString());
		shnDhisIndicatorDetails.setUrl(url);
		shnDhisIndicatorDetails.setStatus(status);
		shnDhisIndicatorDetails.setResponse(response.toString());
		shnDhisIndicatorDetails.setDateCreated(new Date());
		shnDhisIndicatorDetails.setIndicatorType("INDICATOR");
		shnDhisIndicatorDetails.setReferenceId(referenceId);

		Context.getService(SHNDhisObsElementService.class).saveOrupdate(shnDhisIndicatorDetails);
		Context.clearSession();
	}
	
	
	private void deleteMoneyReceiptFromDhis2() {
		
		List<SHNVoidedMoneyReceiptLog> shnVoidedMoneyReceiptLog = Context.getService(SHNVoidedMoneyReceiptLogService.class).getAllVoidedMoneyReceipt();
		for (SHNVoidedMoneyReceiptLog shnVoidedMoneyReceiptLogObject : shnVoidedMoneyReceiptLog) {
			if(shnVoidedMoneyReceiptLogObject != null && !StringUtils.isBlank(shnVoidedMoneyReceiptLogObject.getServiceUuid())) {
				
//				ArrayList<String> elephantList = new ArrayList<>(Arrays.asList(shnVoidedMoneyReceiptLogObject.getDhisId().split(",")));
				ArrayList<String> serviceUuidList = new ArrayList<>(Arrays.asList(shnVoidedMoneyReceiptLogObject.getServiceUuid().split(",")));
				try {
					for (String serviceUuid : serviceUuidList) {
						if(!StringUtils.isBlank(serviceUuid)) {
						log.error("splitted single serviceUuid" + serviceUuid);
						JSONObject eventResponse = new JSONObject();
						JSONArray getEevnts = new JSONArray();
						String eventURL = GETEVENTURL + "?program=" + DHISMapper.registrationMapper.get("program") + "&filter="
						        + DHISMapper.ServiceProvision.get("serviceUuid") + ":eq:" + serviceUuid;
						
						JSONObject getEventResponse = new JSONObject();
	
						getEventResponse = psiapiServiceFactory.getAPIType("dhis2").get("", "", eventURL);
						if (getEventResponse.has("events")) {
							getEevnts = getEventResponse.getJSONArray("events");
						}
						if (getEevnts.length() != 0) {
							JSONObject eventValue = getEevnts.getJSONObject(0);
							if(eventValue.has("event")) {
								String referenceId = eventValue.getString("event");
								if (!StringUtils.isBlank(referenceId)) {
									String referenceUrl = EVENTURL + "/" + referenceId.trim();
									eventResponse = psiapiServiceFactory.getAPIType("dhis2").delete("", "", referenceUrl);
									int statusCode = Integer.parseInt(eventResponse.getString("httpStatusCode"));
									//log.info("statusCode:" + statusCode + "" + eventResponse);
									if (statusCode == 200) {
										JSONObject successResponse = eventResponse.getJSONObject("response");
										log.error("successResponse" + successResponse.toString());
										if(successResponse.has("reference")) {
											log.error("successResponse has reference" + successResponse.toString());
											String importStatus = successResponse.getString("status");
											if (importStatus.equalsIgnoreCase("SUCCESS")) {
												log.error("response has SUCCESS" + importStatus);
												shnVoidedMoneyReceiptLogObject.setVoided(true);
												Context.openSession();
												Context.getService(SHNVoidedMoneyReceiptLogService.class).saveOrUpdate(shnVoidedMoneyReceiptLogObject);
												Context.clearSession();
		
											}
										}
									}
								}
							}

						  }
						}
					  }

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
	
