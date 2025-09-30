<%@page import="edu.unizg.foi.nwtis.podaci.Partner"%>
<%@page
	import="edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti.Partneri"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>REST MVC - Pregled partnera</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/tablica.css">
</head>
<body>

	<%
	request.setAttribute("naslovStranice", "Vježba 8 - zadaća 3 - Pregled partnera");
	%>

	<jsp:include page="predlozak.jsp" />

	<br />
	<div id="partneri-container">
		<table>
			<tr class="naslov_tablice">
				<th>R.br.
				<th>Korisnik</th>
				<th>Naziv</th>
				<th>Adresa</th>
				<th>Mrežna vrata</th>
				<th>Mrežna vrata za kraj</th>
				<th>Admin kod</th>
			</tr>
			<%
			int i = 0;
			List<Partner> partneri = (List<Partner>) request.getAttribute("partneri");
			for (Partner p : partneri) {
				i++;
			%>

			<tr class="red_tablice"
				onclick="window.location.href='${pageContext.servletContext.contextPath}/mvc/tvrtka/partner/<%=p.id()%>';">
				<td class="desno"><%=i%></td>
				<td><%=p.id()%></td>
				<td><%=p.naziv()%></td>
				<td><%=p.adresa()%></td>
				<td><%=p.mreznaVrata()%></td>
				<td><%=p.mreznaVrataKraj()%></td>
				<td><%=p.adminKod()%></td>
			</tr>

			<%
			}
			%>
		</table>
	</div>

</body>
</html>
