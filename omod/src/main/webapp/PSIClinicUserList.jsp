<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>
<openmrs:require privilege="Clinic User List" otherwise="/login.htm" />
<style>
table.dataTable tbody th, table.dataTable tbody td {
     padding: 0px 0px; 
}

</style>
 <a href="${pageContext.request.contextPath}/module/PSI/addPSIClinicUser.form?id=${id}"><spring:message
				code="PSI.psiClinicUserAssing" /></a> 	
<%-- <a class="" href="" onclick="syncClinicUser(${id},'${psiClinicManagement.clinicId}')" style="margin-left: 10px;">Sync User</a> --%>
<div id="loader_clinic_list" style="display: none;position: absolute; z-index: 1000;margin-left:45%"> 
	<img width="50px" height="50px" src="<c:url value="/moduleResources/PSI/images/ajax-loading.gif"/>">
</div>
<br /> 
<div id="message" style="font-weight: bold;position: inherit; z-index: 1000;margin-left:37%"></div>
<div class="container register-form" style="max-width: 100%;padding: 0px; margin: 0px;">
	<div class="form">
    	<div class="note">
        	<p>User List of ${name} (${psiClinicManagement.clinicId})</p>
       	</div>					
	<table id="table_id" class="display cell-border compact">
	    <thead>
	        <tr>
	        	<th>Name</th> 
	            <th>User Name</th> 
	            <th>Email</th> 
	            <th>Mobile</th>
	            <th>Roles</th> 
	            <th>Status</th> 
	            <th>Action</th>           
	        </tr>
	    </thead>
	    <tbody>
	    	<c:forEach var="user" items="${ pSIClinicUsers }">
	        <tr>
	            <td>${ user.firstName } ${ user.lastName }</td>
	            <td>${ user.userName }</td>
	            <td>${ user.email }</td>
	            <td>${ user.mobile }</td>
	            <td>${ user.role }</td>
	           	<c:if test="${user.retired}">
	            <td >Inactive</td>
	            </c:if>
	            <c:if test="${!user.retired}">
	            <td >Active</td>
	            </c:if>
	            <td ><a class="btn btn-primary" href="<c:url value="/module/PSI/editPSIClinicUser.form?id=${ user.cuid }"/>"> Edit</a> </td>
	        </tr>
	       </c:forEach>
	        
	    </tbody>
	</table>
</div>
<script type="text/javascript" src="/openmrs/moduleResources/PSI/js/jquery.dataTables.js"></script>
<script type="text/javascript">

var $jq = jQuery.noConflict();
$jq(document).ready( function () {
	$jq('#table_id').DataTable();
} );

function syncClinicUser(clinicId,code) {
	var url = "/openmrs/ws/rest/v1/clinic-user/syncUser/" + clinicId + "/" + code;
	var token = $jq("meta[name='_csrf']").attr("content");
	var header = $jq("meta[name='_csrf_header']").attr("content");
	event.preventDefault();
	$jq("#loader_clinic_list").show();
	$jq.ajax({
		type:"GET",
		contentType : "application/json",
	    url : url,	 
	    dataType : 'html',
	    timeout : 100000,
	    beforeSend: function() {	    
	    		
	    },
	    success:function(data){
			$jq("#message").html(data);
			$jq("#loader_clinic_list").hide();
		   if(data == "Success"){					   
			   window.location.replace("/openmrs/module/PSI/PSIClinicUserList.form?id="+clinicId);
		   }
	    },
	    error:function(data){
	    	$jq("#loader_clinic_list").hide();
	    }
	    
	});
};
</script>
<%@ include file="/WEB-INF/template/footer.jsp"%>