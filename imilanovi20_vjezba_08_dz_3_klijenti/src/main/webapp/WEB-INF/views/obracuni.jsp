<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, edu.unizg.foi.nwtis.podaci.Obracun, java.text.SimpleDateFormat, java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vježba 8 - zadaća 3 - Pregled obračuna</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/forma.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/tablica.css">
</head>
<body>
	<%
request.setAttribute("naslovStranice", "Vježba 8 - zadaća 3 - Pregled obračuna");
%>

	<jsp:include page="predlozak.jsp" />

	<div class="forma-container">
		<h2 class="forma-naslov">Pretraživanje obračuna</h2>

		<form method="GET"
			action="${pageContext.servletContext.contextPath}/mvc/tvrtka/privatno/obracuni">
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

			<div class="forma-grupa">
				<label for="vrstaObracuna" class="forma-label">Vrsta
					obračuna</label> <select id="vrstaObracuna" name="vrstaObracuna"
					class="forma-select" required>
					<option value="svi">Svi obračuni</option>
					<option value="jela">Obračuni jela</option>
					<option value="pica">Obračuni pića</option>
				</select>
			</div>

			<button type="submit" class="forma-gumb">Dohvati obračune</button>
		</form>
	</div>

	<%
	String vrstaDohvacanja = (String) request.getAttribute("vrstaDohvacanja");
	Integer status = (Integer) request.getAttribute("status");

	if (vrstaDohvacanja != null && !"bez".equals(vrstaDohvacanja)) {
	%>

	<div id="partneri-container" class="partneri-container">
		<%
		if (status != null && status == 200) {
		  @SuppressWarnings("unchecked")
		  List<Obracun> obracuni = (List<Obracun>) request.getAttribute("obracuni");
		  if (obracuni != null && !obracuni.isEmpty()) {
		%>
		<h3>
			Rezultati pretrage -
			<%
		if ("obracuni".equals(vrstaDohvacanja)) {
		%>
			Svi obračuni
			<%
		} else if ("jela".equals(vrstaDohvacanja)) {
		%>
			Obračuni jela
			<%
		} else if ("pica".equals(vrstaDohvacanja)) {
		%>
			Obračuni pića
			<%
		}
		%>
		</h3>

		<table>
			<tr class="naslov_tablice">
				<th>R.br.</th>
				<th>Partner ID</th>
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
				<td class="desno"><%=o.partner()%></td>
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
				<td colspan="6"><strong>UKUPNO:</strong></td>
				<td class="desno"><strong><%=String.format("%.2f €", ukupniIznos)%></strong></td>
				<td></td>
			</tr>
		</table>

		<p>
			<strong>Broj stavki:</strong>
			<%=obracuni.size()%>
			| <strong>Ukupni iznos:</strong>
			<%=String.format("%.2f €", ukupniIznos)%></p>

		<%
		} else {
		%>
		<p>Nema obračuna za odabrani period i vrstu.</p>
		<%
		}
		} else {
		%>
		<p>
			Greška pri dohvaćanju obračuna. Status:
			<%=status%></p>
		<%
}
%>
	</div>

	<%
}
%>

</body>
</html>