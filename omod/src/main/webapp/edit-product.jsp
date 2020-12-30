<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<script type="text/javascript" src="/openmrs/moduleResources/PSI/js/jquery-1.10.2.js"></script>
<meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<%@ include file="template/localHeader.jsp"%>
<openmrs:require privilege="Edit Clinic Service" otherwise="/login.htm" />

<c:url var="saveUrl" value="/module/PSI/addPPSIClinicService.form" />
<c:url var="cancelUrl" value="/module/PSI/product-list.form?id=${pSIServiceManagement.psiClinicManagement.cid}" />




<div class="container register-form" style="max-width: 100%;padding: 0px; margin: 0px;">
	<div class="form">
    	<div class="note">
    	    
        	<p>Edit Product</p>
        	
       	</div>
		<span class="text-red" id="usernameUniqueErrorMessage"></span>
		 <div id="loading" style="display: none;position: absolute; z-index: 1000;margin-left:45%"> 
			<img width="50px" height="50px" src="<c:url value="/moduleResources/PSI/images/ajax-loading.gif"/>"></div>
							
		</div>
		<form:form method="POST"  id="serviceForm" action="${saveUrl}" modelAttribute="pSIServiceManagement">
  		<div class="form-content">
        	<div class="row">
            	<div class="col-md-6">
                	<div class="form-group">
                							
						Product Code
						<form:input style="height: 39px;" path="code" class="form-control" required="required"/>
                  	</div>
                  	<div class="form-group">                							
						Brand Name:
						<form:input  style="height: 39px;" path="brandName" class="form-control" required="required"/>
                  	</div>
             	</div>
              	<div class="col-md-6">
               		<div class="form-group">
                  	<label for="Service Code">Product Name</label>
						<form:input style="height: 39px;" path="name" class="form-control" required="required"/>                  			
					</div>
                  	<div class="form-group">
                  	Service Category: 
                  			<form:select path="category" class="form-control selcls" required="required">
                  				<form:option value=""/>
					               <c:forEach items="${services}" var="service"> 
					              	 <form:option value="${service.categoryName}" label="${service.categoryName}"/>					              
					              </c:forEach>				             

					         </form:select>	
                   	</div>
              	</div>
              	
              	<form:hidden path="sid" />
              	<form:hidden path="psiClinicManagement" value="${pSIServiceManagement.psiClinicManagement.cid}"/>
              	
              	<div class="col-md-6">
              		<div class="form-group">
                  	Unit Cost:
                  		<form:input type="number" style="height: 39px;" path="unitCost" class="form-control" required="required" min="0"/>
                   	 	
                  	</div> 
              	</div>
              	<div class="col-md-6">               		
                  	<div class="form-group">
                  		Purchase price:
                  		<form:input type="number" style="height: 39px;" path="purchasePrice" class="form-control"  min="0"/>
                  	</div> 
					
              	</div>
              	<div class="col-md-6">
              		
                  	<div class="form-group">
                  	Discount (%) for Poor:
                  		<form:input type="number" style="height: 39px;" path="discountPoor" class="form-control" required="required" min="0"/>
                   	 	
                  	</div>  
                  	<div class="form-group">
                  	Discount (%) for Able to Pay:
                  		<form:input type="number" style="height: 39px;" path="discountAblePay" class="form-control" required="required" min="0"/>
                   	 	
                  	</div>          		
                  	
              	</div>
              	<div class="col-md-6">
              		
              		<div class="form-group">
                  	Discount (%) for PoP:
                  		<form:input type="number" style="height: 39px;" path="discountPop" class="form-control" required="required" min="0"/>
                   	 	
                  	</div> 
              	</div>
              	<c:if test="${!pSIServiceManagement.voided}">
				<div class="col-md-6">
					<div class="form-inline form-group">
						<label>Disable Service</label>
						<div>
							<input type="radio" name="disableService" id="yesDisable" value="yes"> Yes
						</div>
						<div style ="margin-left: 10px">
							<input type="radio" name="disableService" id="noDisable" value="no"> No
						</div>
					</div>
				</div>
				</c:if>
				<c:if test="${pSIServiceManagement.voided}">
				<div class="col-md-6">
					<div class="form-inline form-group">
					<label>Enable Service</label>
						<input type="radio" name="enableService" id="enableService" value="yes"> Yes
					</div>
				</div>
				</c:if>
			</div>
          	<button type="submit" class="btnSubmit">Submit</button> <a href="${cancelUrl}">Back</a>
      	</div>
   	</div>
</div>       
	

</form:form>

<script type="text/javascript" src="/openmrs/moduleResources/PSI/js/product.js"></script>
<%@ include file="/WEB-INF/template/footer.jsp"%>