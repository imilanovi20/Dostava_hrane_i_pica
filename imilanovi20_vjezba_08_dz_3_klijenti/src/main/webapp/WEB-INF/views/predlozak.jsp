<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/cssLayout.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/default.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/nwtis.css">
</head>

<body>

	<%
	String naslovStranice = (String) request.getAttribute("naslovStranice");
	if (naslovStranice == null) {
	  naslovStranice = "Vježba 8 - zadaća 3";
	}

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


	<div id="top" class="top">
		<!-- Logo gore desno -->
		<div class="logo-sekcija"
			onclick="window.location.href='${pageContext.servletContext.contextPath}/mvc/tvrtka/pocetak';">

			<div id="logo" class="logo"></div>
			<div id="naziv_tvrtke" class="naziv_tvrtke">FOLT</div>
		</div>
		<div class="glavni_naslov"><%=naslovStranice%></div>
		<div class="informacije">
			Sveučilište u Zagrebu<br /> Fakultet organizacije i informatike<br />
			Napredne web tehnologije i servisi<br /> Autor: Ivan Milanović-Litre
		</div>
		<%
		if (vrstaKorisnika != null && imeKorisnika != null) {
		%>
		<div id="prijavljenKorisnik" style="margin-top: 8px;">
			Prijavljen korisnik: <span style="color: yellow;"><%=imeKorisnika%></span>
			<br /> <a
				href="<%=request.getContextPath()%>/privatno/odjavaKorisnika.xhtml">
				Odjava korisnika </a> <br />
		</div>
		<%
		}
		%>
	</div>

	<%
	if (vrstaKorisnika != null) {
	%>

	<div id="nav" class="nav">
		<ul>
			<li><a
				href="${pageContext.servletContext.contextPath}/index.xhtml">Početna
					stranica Partner</a></li>
			<li><a
				href="${pageContext.servletContext.contextPath}/mvc/tvrtka/pocetak">Početna
					stranica Tvrtka</a></li>
			<li><a
				href="${pageContext.servletContext.contextPath}/mvc/tvrtka/provjera">Provjera
					poslužitelja Tvrtka</a></li>
			<li><a
				href="${pageContext.servletContext.contextPath}/mvc/tvrtka/partner">Pregled
					partnera</a></li>

			<%
			if ("korisnik".equals(vrstaKorisnika)) {
			%>
			<li><a
				href="${pageContext.servletContext.contextPath}/mvc/tvrtka/privatno/pocetak">
					Pogledi privatno</a></li>
			<%
			}
			%>
			<%
			if ("nwtis".equals(vrstaKorisnika) || "admin".equals(vrstaKorisnika)) {
			%>
			<li><a
				href="${pageContext.servletContext.contextPath}/mvc/tvrtka/privatno/obracuni">Pregled
					obračuna</a></li>
			<li><a
				href="${pageContext.servletContext.contextPath}/mvc/tvrtka/privatno/obracuniPartnera">Pregled
					obračuna partnera</a></li>
			<%
			}
			%>
			<%
			if ("admin".equals(vrstaKorisnika)) {
			%>

			<li><a
				href="${pageContext.servletContext.contextPath}/mvc/tvrtka/admin/dodajPartnera">Dodaj
					partnera</a></li>
			<li><a
				href="${pageContext.servletContext.contextPath}/mvc/tvrtka/admin/spavanje">Spavanje
					poslužitelja</a></li>
			<li><a
				href="${pageContext.servletContext.contextPath}/mvc/tvrtka/admin/nadzornaKonzolaTvrtka">Nadzorna
					konzola tvrtka</a></li>
			<%
			}
			%>
		</ul>
	</div>
	<%
	}
	%>
</body>
</html>