<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vježba 8 - zadaća 3 - Početna stranica</title>


<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/kartica.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/forma.css">
<body>

	<jsp:include page="predlozak.jsp" />
	<%
	request.setAttribute("naslovStranice", "Vježba 8 - zadaća 3 - Početna stranica");
	%>
	<%
	String imeKorisnika = request.getRemoteUser();

	String vrstaKorisnika = "korisnik";
	if (request.getRemoteUser() != null) {
	  if (request.isUserInRole("admin")) {
	    vrstaKorisnika = "admin";
	  } else if (request.isUserInRole("nwtis")) {
	    vrstaKorisnika = "nwtis";
	  }
	}
	%>

	<div style="text-align: center;">

		<a
			href="${pageContext.servletContext.contextPath}/index.xhtml">Početna
			stranica Partner</a>

	</div>



	<%
	if (vrstaKorisnika.equals("korisnik")) {
	%>

	<div class="forma-container">
		<br />
		<form method="get"
			action="${pageContext.servletContext.contextPath}/mvc/tvrtka/privatno/pocetak"
			style="display: inline;">
			<input type="submit" class="forma-gumb" value="Privatni pogledi">
		</form>

	</div>
	<%
	}
	%>



	<div class="narudzba-container">

		<div class="narudzba-kartica">
			<div class="narudzba-id">JAVNO</div>
			<div class="narudzba-naziv">Javni pogledi</div>

			<form method="get"
				action="${pageContext.servletContext.contextPath}/index.xhtml"
				style="display: inline;">
				<input type="submit" class="narudzba-gumb"
					value="Početna stranica Partner">
			</form>
			<br /> <br />

			<form method="get"
				action="${pageContext.servletContext.contextPath}/mvc/tvrtka/pocetak"
				style="display: inline;">
				<input type="submit" class="narudzba-gumb"
					value="Početna stranica Tvrtka">
			</form>
			<br /> <br />

			<form method="get"
				action="${pageContext.servletContext.contextPath}/mvc/tvrtka/provjera"
				style="display: inline;">
				<input type="submit" class="narudzba-gumb"
					value="Provjera poslužitelja Tvrtka">
			</form>
			<br /> <br />

			<form method="get"
				action="${pageContext.servletContext.contextPath}/mvc/tvrtka/partner"
				style="display: inline;">
				<input type="submit" class="narudzba-gumb" value="Pregled partnera">
			</form>
		</div>

		<%
		if (imeKorisnika != null && ("nwtis".equals(vrstaKorisnika) || "admin".equals(vrstaKorisnika))) {
		%>
		<div class="narudzba-kartica">
			<div class="narudzba-id">PRIVATNO</div>
			<div class="narudzba-naziv">Privatni pogledi</div>

			<form method="get"
				action="${pageContext.servletContext.contextPath}/mvc/tvrtka/privatno/obracuni"
				style="display: inline;">
				<input type="submit" class="narudzba-gumb" value="Pregled obračuna">
			</form>
			<br /> <br />

			<form method="get"
				action="${pageContext.servletContext.contextPath}/mvc/tvrtka/privatno/obracuniPartnera"
				style="display: inline;">
				<input type="submit" class="narudzba-gumb"
					value="Pregled obračuna partnera">
			</form>

			<%
			if ("korisnik".equals(vrstaKorisnika)) {
			%>
			<br /> <br />
			<form method="get"
				action="${pageContext.servletContext.contextPath}/mvc/tvrtka/privatno/pocetak"
				style="display: inline;">
				<input type="submit" class="narudzba-gumb" value="Pogledi privatno">
			</form>
			<%
			}
			%>
		</div>
		<%
		}
		%>

		<%
		if ("admin".equals(vrstaKorisnika)) {
		%>
		<div class="narudzba-kartica">
			<div class="narudzba-id">ADMIN</div>
			<div class="narudzba-naziv">Admin pogledi</div>

			<form method="get"
				action="${pageContext.servletContext.contextPath}/mvc/tvrtka/admin/dodajPartnera"
				style="display: inline;">
				<input type="submit" class="narudzba-gumb" value="Dodaj partnera">
			</form>
			<br /> <br />

			<form method="get"
				action="${pageContext.servletContext.contextPath}/mvc/tvrtka/admin/spavanje"
				style="display: inline;">
				<input type="submit" class="narudzba-gumb"
					value="Spavanje poslužitelja">
			</form>
			<br /> <br />

			<form method="get"
				action="${pageContext.servletContext.contextPath}/mvc/tvrtka/admin/nadzornaKonzolaTvrtka"
				style="display: inline;">
				<input type="submit" class="narudzba-gumb"
					value="Nadzorna konzola tvrtka">
			</form>

			<%
			if ("korisnik".equals(vrstaKorisnika)) {
			%>
			<br /> <br />
			<form method="get"
				action="${pageContext.servletContext.contextPath}/mvc/tvrtka/admin/pocetak"
				style="display: inline;">
				<input type="submit" class="narudzba-gumb" value="Pogledi admin">
			</form>
			<%
			}
			%>
		</div>
		<%
		}
		%>

	</div>

</body>
</html>
