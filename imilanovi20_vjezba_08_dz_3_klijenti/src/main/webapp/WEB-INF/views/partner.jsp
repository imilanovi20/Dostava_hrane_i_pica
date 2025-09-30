
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, edu.unizg.foi.nwtis.podaci.Partner"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
Partner partner = (Partner) request.getAttribute("partner");
Integer status = (Integer) request.getAttribute("status");

String nazivPartnera = "";
if (partner != null) {
  nazivPartnera = partner.naziv();
}
%>
<title>Vježba 8 - zadaća 3 - Pregled partnera <%=nazivPartnera%></title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/tablica.css">
</head>
<body>

	<%
	request.setAttribute("naslovStranice", "Vježba 8 - zadaća 3 - Pregled partnera " + nazivPartnera);
	%>

	<jsp:include page="predlozak.jsp" />

	<div id="partneri-container">
		<table>
			<tr class="naslov_tablice">
				<th colspan="2"><%=nazivPartnera%></th>
			</tr>

			<tr class="red_tablice">
				<td>KORISNIK</td>
				<td><%=partner.id()%></td>
			</tr>
			<tr class="red_tablice">
				<td>VRSTA KUHINJE</td>
				<td><%=partner.vrstaKuhinje()%></td>
			</tr>
			<tr class="red_tablice">
				<td>GPS ŠIRINA</td>
				<td><%=partner.gpsSirina()%></td>
			</tr>
			<tr class="red_tablice">
				<td>GPS DUŽINA</td>
				<td><%=partner.gpsDuzina()%></td>
			</tr>
			</tr>
			<tr class="red_tablice">
				<td>MREŽNA VRATA</td>
				<td><%=partner.mreznaVrata()%></td>
			</tr>
			<tr class="red_tablice">
				<td>MREŽNA VRATA KRAJ</td>
				<td><%=partner.mreznaVrataKraj()%></td>
			</tr>


		</table>
	</div>

</body>
</html>
