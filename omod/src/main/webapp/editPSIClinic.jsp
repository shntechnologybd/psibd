<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<%@ include file="template/localHeader.jsp"%>

<script type="text/javascript" src="/openmrs/moduleResources/PSI/js/jquery-1.10.2.js"></script>
 


<c:url var="saveUrl" value="/module/PSI/addPsiClinic.form" />
<c:url var="cancelUrl" value="/module/PSI/PSIClinicList.form" />
<openmrs:require privilege="Edit Clinic" otherwise="/login.htm" />

<div class="container register-form" style="max-width: 100%;padding: 0px; margin: 0px;">
	<div class="form">
    	<div class="note">
        	<p>Edit Community Clinic.</p>
       	</div>
		<p>${message}</p>
		 <div id="loading" style="display: none;position: absolute; z-index: 1000;margin-left:45%"> 
		 	<img width="50px" height="50px" src="<c:url value="/moduleResources/PSI/images/ajax-loading.gif"/>"></div>
		 </div>
		<span class="text-red" id="usernameUniqueErrorMessage"></span>
        <form:form method="POST"  id="clinicInfo" action="ff" modelAttribute="pSIClinic">
  		<div class="form-content">
        	<div class="row">
            	<div class="col-md-6">
                	<div class="form-group">
                		Clinic Name : <form:input style="height: 39px;" path="name" class="form-control" required="required" tabindex="0"/>
                  	</div>
                  	<div class="form-group">
                  		Clinic ID: <form:input style="height: 39px;" pattern=".{3,}" path="clinicId" class="form-control" required="required" tabindex="2"/>
                   	</div>
                   	<div class="form-group">
                  	Division <form:select path="divisionId" class="form-control selcls" required="required" tabindex="4">
                  			<form:option value="">Please Select</form:option>
                  				  <c:forEach items="${divisions}" var="division">	                  				 
						              <form:option value="${division.id}">${division.name}</form:option>						             
					              </c:forEach>				             
					         </form:select>
					</div>
             	</div>
              	<div class="col-md-6">
               		<div class="form-group">
                  	Category Type	<form:select path="category" class="form-control selcls" required="required" tabindex="1">
                  				<!-- <form:option value="BEmOC"/>
					              <form:option value="CEmOC"/>
					              <form:option value="Vital"/> -->
					              <c:forEach items="${clinicTypes}" var="type"> 
                                     <form:option value="${type.clinicTypeName}" label="${type.clinicTypeName}"/>                               
                                  </c:forEach>      					             
					         </form:select>
					</div>
                  	<div class="form-group">
                   	Address: <form:input style="height: 39px;" path="address" class="form-control" required="required" tabindex="3"/>                	
                   	
                  	</div>
                  	<div class="form-group">
                  	District 
 					<form:select path="districtId" class="form-control selcls"  required="required" tabindex="5">
 						<form:option value=""/>
                  		<c:forEach items="${districts}" var="district">	                  				 
							<form:option value="${district.id}">${district.name}</form:option>						             
					        </c:forEach>
                  	</form:select>
					</div>
              	</div>
              	<form:hidden path="cid" />
              	
              	<div class="col-md-6">  
              		<div class="form-group">
                  	Upazila 
 					<form:select path="upazilaId" class="form-control selcls" required="required" tabindex="6">
 					    <form:option value=""/>
                  		<c:forEach items="${upazilas}" var="upazila">	                  				 
							<form:option value="${upazila.id}">${upazila.name}</form:option>						             
					    </c:forEach>
                  	</form:select>
					</div> 
              	</div>
              	
              	<div class="col-md-6"> 
                  	<div class="form-group">
                   	DHIS2 Org ID <form:input path="dhisId" style="height: 39px;" class="form-control" required="required" autocomplete="off" tabindex="7"/>
                  	</div>
                  	
              	</div>
          	</div>
          	<button type="submit" class="btnSubmit">Submit</button> <a href="${cancelUrl}">Back</a>
      	</div>
   	</div>
</div>       
	

</form:form>
<script type="text/javascript" src="/openmrs/moduleResources/PSI/js/clinic.js"></script>


<%@ include file="/WEB-INF/template/footer.jsp"%>