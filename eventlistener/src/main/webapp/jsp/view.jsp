<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="org.exoplatform.portlet.ContactInfoBean" %>

<portlet:defineObjects/>
<%!ContactInfoBean bean = null; %>
<%
	bean = (ContactInfoBean) renderRequest.getPortletSession().getAttribute("contactInfo") ;
	if(bean != null) {
%>
<div>
<table>
	<tbody>
		<tr>
			<th>Name:</th>
			<td><%=bean.getName()%></td>
		</tr>
		<tr>
			<th>Email:</th>
			<td><%=bean.getEmail()%></td>
		</tr>
	</tbody>
</table>
</div>
<%
	} else {
%>
	<p>No contact information	
<%	  
	}
%>