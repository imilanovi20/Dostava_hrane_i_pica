<%@ page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vježba 8 - zadaća 3 - Dodaj partnera</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/forma.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/status.css">
</head>
<body>

	<%
request.setAttribute("naslovStranice", "Vježba 8 - zadaća 3 - Dodaj partnera");
%>

	<jsp:include page="predlozak.jsp" />

	<div class="forma-container">
		<h2>Dodaj novog partnera</h2>

		<%
		String poruka = (String) request.getAttribute("poruka");
		if (poruka != null) {
		  Object statusObj = request.getAttribute("status");
		  String ikona = "✗";
		  String cssClass = "status_neuspijesan";
		  if (statusObj != null) {
		    Integer status = (Integer) statusObj;
		    boolean statusOk = (status == 201);
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
			action="${pageContext.servletContext.contextPath}/mvc/tvrtka/admin/dodajPartnera"
			method="POST">
			<div class="forma-red-3">
				<div class="forma-grupa">
					<label for="id" class="forma-label">ID partnera</label> <input
						type="number" id="id" name="id" class="forma-input" required
						min="1" placeholder="Jedinstveni ID">
				</div>

				<div class="forma-grupa">
					<label for="naziv" class="forma-label">Naziv partnera</label> <input
						type="text" id="naziv" name="naziv" class="forma-input" required
						maxlength="100" placeholder="Naziv partnera">
				</div>

				<div class="forma-grupa">
					<label for="vrstaKuhinje" class="forma-label">Vrsta kuhinje</label>
					<select id="vrstaKuhinje" name="vrstaKuhinje" class="forma-select"
						required>
						<option value="MK">MK</option>
						<option value="VK">VK</option>
						<option value="KK">KK</option>
					</select>
				</div>
			</div>

			<div class="forma-red-3">
				<div class="forma-grupa">
					<label for="adresa" class="forma-label">Adresa</label> <input
						type="text" id="adresa" name="adresa" class="forma-input" required
						maxlength="200" placeholder="Adresa partnera">
				</div>

				<div class="forma-grupa">
					<label for="mreznaVrata" class="forma-label">Mrežna vrata</label> <input
						type="number" id="mreznaVrata" name="mreznaVrata"
						class="forma-input" required>
				</div>

				<div class="forma-grupa">
					<label for="mreznaVrataKraj" class="forma-label">Vrata za
						kraj</label> <input type="number" id="mreznaVrataKraj"
						name="mreznaVrataKraj" class="forma-input" required>
				</div>
			</div>

			<div class="forma-red-3">
				<div class="forma-grupa">
					<label for="gpsSirina" class="forma-label">GPS Širina</label> <input
						type="number" id="gpsSirina" name="gpsSirina" class="forma-input"
						step="0.000001" required min="-90" max="90" placeholder="Širina">
				</div>

				<div class="forma-grupa">
					<label for="gpsDuzina" class="forma-label">GPS Dužina</label> <input
						type="number" id="gpsDuzina" name="gpsDuzina" class="forma-input"
						step="0.000001" required min="-180" max="180" placeholder="Dužina">
				</div>

				<div class="forma-grupa">
					<label for="adminKod" class="forma-label">Admin kod</label> <input
						type="text" id="adminKod" name="adminKod" class="forma-input"
						required maxlength="50" placeholder="Admin kod">
				</div>
			</div>

			<button type="submit" class="forma-gumb">Dodaj partnera</button>
		</form>
	</div>

</body>
</html>