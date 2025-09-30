<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, edu.unizg.foi.nwtis.podaci.Obracun, edu.unizg.foi.nwtis.podaci.Partner, java.text.SimpleDateFormat, java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vježba 8 - zadaća 3 - Pregled obračuna partnera</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/forma.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/tablica.css">
</head>
<body>
	<%
request.setAttribute("naslovStranice", "Vježba 8 - zadaća 3 - Pregled obračuna partnera");
%>

	<jsp:include page="predlozak.jsp" />

	<div class="forma-container">
		<h2 class="forma-naslov">Pretraživanje obračuna po partneru</h2>

		<form method="GET"
			action="${pageContext.servletContext.contextPath}/mvc/tvrtka/privatno/obracuniPartnera">

			<div class="forma-grupa">
				<label for="idPartnera" class="forma-label">Partner</label> <select
					id="idPartnera" name="idPartnera" class="forma-select" required>
					<option value="">-- Odaberite partnera --</option>
					<%
					@SuppressWarnings("unchecked")
					List<Partner> partneri = (List<Partner>) request.getAttribute("partneri");
					Integer odabraniPartnerId = (Integer) request.getAttribute("odabraniPartnerId");

					if (partneri != null) {
					  for (Partner p : partneri) {
					    boolean selected = (odabraniPartnerId != null && odabraniPartnerId.equals(p.id()));
					%>
					<option value="<%=p.id()%>" <%=selected ? "selected" : ""%>>
						<%=p.naziv()%> -
						<%=p.vrstaKuhinje()%>
					</option>
					<%
					}
					}
					%>
				</select>
			</div>

			<div class="forma-red">
				<div class="forma-grupa">
					<label for="datumOd" class="forma-label">Datum od</label> <input
						type="datetime-local" id="datumOd" name="odVr" class="forma-input">
				</div>

				<div class="forma-grupa">
					<label for="datumDo" class="forma-label">Datum do</label> <input
						type="datetime-local" id="datumDo" name="doVr" class="forma-input">
				</div>
			</div>

			<button type="submit" class="forma-gumb">Dohvati obračune
				partnera</button>
		</form>
	</div>

	<%
	String vrstaDohvacanja = (String) request.getAttribute("vrstaDohvacanja");
	Integer status = (Integer) request.getAttribute("status");

	if (vrstaDohvacanja != null && !"bez".equals(vrstaDohvacanja)) {
	%>

	<div id="partneri-container">
		<%
		if (status != null && status == 200) {
		  @SuppressWarnings("unchecked")
		  List<Obracun> obracuni = (List<Obracun>) request.getAttribute("obracuni");
		  Partner odabraniPartner = (Partner) request.getAttribute("odabraniPartner");

		  if (obracuni != null && !obracuni.isEmpty()) {
		%>

		<table>
			<tr class="naslov_tablice">
				<th>R.br.</th>
				<th>ID stavke</th>
				<th>Jelo</th>
				<th>Količina</th>
				<th>Cijena</th>
				<th>Ukupno</th>
				<th>Vrijeme</th>
			</tr>
			<%
			int i = 0;
			float ukupniIznos = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

			for (Obracun o : obracuni) {
			  i++;
			  float stavkaUkupno = o.kolicina() * o.cijena();
			  ukupniIznos += stavkaUkupno;
			  Date datum = new Date(o.vrijeme());
			%>

			<tr class="red_tablice">
				<td class="desno"><%=i%></td>
				<td><%=o.id()%></td>
				<td class="centar"><%=o.jelo() ? "DA" : "NE"%></td>
				<td class="desno"><%=String.format("%.2f", o.kolicina())%></td>
				<td class="desno"><%=String.format("%.2f €", o.cijena())%></td>
				<td class="desno"><%=String.format("%.2f €", stavkaUkupno)%></td>
				<td class="centar"><%=sdf.format(datum)%></td>
			</tr>

			<%
}
%>
			<tr class="naslov_tablice">
				<td colspan="5"><strong>UKUPNO:</strong></td>
				<td class="desno"><strong><%=String.format("%.2f €", ukupniIznos)%></strong></td>
				<td></td>
			</tr>
		</table>


		<%
		} else {
		%>
		<p>Nema obračuna za odabranog partnera u odabranom periodu.</p>
		<%
		}
		} else if ("greska".equals(vrstaDohvacanja)) {
		String poruka = (String) request.getAttribute("poruka");
		%>
		<p">
			Greška:
			<%=poruka != null ? poruka : "Nepoznata greška"%></p>
		<%
		} else {
		String poruka = (String) request.getAttribute("poruka");
		%>
		<p;">
			Greška pri dohvaćanju obračuna. Status:
			<%=status%>
			<%
			if (poruka != null) {
			%>
			<br><%=poruka%>
			<%
			}
			%>
		</p>
		<%
}
%>
	</div>

	<%
}
%>

</body>
</html>