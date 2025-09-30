<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Provjera rada poslužitelja Tvrtka</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/status.css">
</head>
<body>
	<%
	request.setAttribute("naslovStranice", "Vježba 8 - zadaća 3 - Provjera poslužitelja Tvrtka");
	%>

	<jsp:include page="predlozak.jsp" />

	<div class="status">
		<%
		Object statusObj = request.getAttribute("statusPosluzitelja");
		if (statusObj != null) {
		  Integer status = (Integer) statusObj;
		  String poruka = (String) request.getAttribute("poruka");
		  boolean statusOk = (status == 200);
		  String ikona = statusOk ? "✓" : "✗";
		  String cssClass = statusOk ? "status_uspijesan" : "status_neuspijesan";
		%>
		<div class="status_item  <%=cssClass%>">
			<span class="status_ikona"><%=ikona%></span> <span>Status
				poslužitelja: <%=status%></span>
		</div>

		<div class="status_item  <%=cssClass%>">
			<span><%=poruka%></span>
		</div>
		<%
		} else {
		%>
		<div class="status_item  status_neuspijesan">
			<span>Nema podataka o statusu poslužitelja</span>
		</div>
		<%
		}
		%>
	</div>

</body>
</html>