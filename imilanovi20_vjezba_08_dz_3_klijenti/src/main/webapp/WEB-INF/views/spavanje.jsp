<%@ page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vježba 8 - zadaća 3 - Spavanje</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/forma.css">
	<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/status.css">
</head>
<body>

	<%
request.setAttribute("naslovStranice", "Vježba 8 - zadaća 3 - Spavanje");
%>

	<jsp:include page="predlozak.jsp" />

	<div class="forma-container">
		<h2>Aktiviraj spavanje poslužitelja</h2>

		<%
		String poruka = (String) request.getAttribute("poruka");
		if (poruka != null) {
		  Object statusObj = request.getAttribute("status");
		  String ikona = "✗";
		  String cssClass = "status_neuspijesan";
		  if (statusObj != null) {
		    Integer status = (Integer) statusObj;
		    boolean statusOk = (status == 200);
		    ikona = statusOk ? "✓" : "✗";
		    cssClass = statusOk ? "status_uspijesan" : "status_neuspijesan";
		  }
		%>
		<br> <br>
		<div class="status_item  <%=cssClass%>"style="padding-bottom: 20px;">
			<span class="status_ikona"><%=ikona%></span> <span> <%=poruka%></span>
		</div>
		<br> <br>
		<%
		}
		%>

		<form
			action="${pageContext.servletContext.contextPath}/mvc/tvrtka/admin/spavanje"
			method="GET">
			<div class="forma-grupa">
				<label for="vrijeme" class="forma-label">Vrijeme spavanja
					(milisekunde)</label> <input type="number" id="vrijeme" name="vrijeme"
					class="forma-input" required min="1" value="5000"
					placeholder="Vrijeme u milisekundama">
			</div>

			<button type="submit" class="forma-gumb">Aktiviraj spavanje</button>
		</form>
	</div>

</body>
</html>