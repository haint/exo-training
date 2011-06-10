<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<portlet:defineObjects/>

<div>
	<p>Use the form below to create a contact. The data is sent over to other portlets into a HashMap.</p>
	<form method="post" action="<portlet:actionURL><portlet:param name="javax.portlet.action" value="savecontact" /></portlet:actionURL>">
		<fieldset>
			<table>
				<tbody>
					<tr>
						<th><label>Contact name:</label></th>
						<td><input type="text" name="name"/></td>
					</tr>
					<tr>
						<th><label>Contact e-mail</label></th>
						<td><input type="text" name="email" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Save Contact"/></td>
					</tr>
				</tbody>
			</table>
		</fieldset>					
	</form>
</div>
