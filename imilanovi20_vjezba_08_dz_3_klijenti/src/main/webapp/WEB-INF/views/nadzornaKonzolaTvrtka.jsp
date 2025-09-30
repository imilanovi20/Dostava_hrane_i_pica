<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vježba 8 - zadaća 3 - Nadzorna kontrola tvrtka</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/status.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/tablica.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/forma.css">
</head>
<body>
	<%
	request.setAttribute("naslovStranice", "Vježba 8 - zadaća 3 - Nadzorna kontrola tvrtka");

	%>

	<jsp:include page="predlozak.jsp" />

	<div class="kontrola_container">
		<h2>Pošalji internu poruku</h2>

		<div class="forma_kontroler_container">
			<form id="formaKontroler">
				<div class="">
					<div class="forma-grupa">
						<label for="internaPoruka" class="forma-label">Interna
							poruka</label> <input id="internaPoruka" name="internaPoruka"
							class="forma-input">
					</div>

				</div>
				<button type="submit" class="forma-gumb">Pošalji poruku</button>

			</form>
		</div>

		<div id="partneri-container">
			<table>
				<tr class="naslov_tablice">
					<td>RAD POSLUŽITELJA</td>
					<td><span id="rad"></span></td>
				</tr>

				<tr class="red_tablice">
					<td>BROJ OBRAČUNA</td>
					<td><span id="obracuni"></span></td>
				</tr>
				<tr class="red_tablice">
					<td>PORUKA</td>
					<td><span id="poruka_tekst"></span></td>
				</tr>
			</table>
		</div>
	</div>

	<div class="status_container" id="status-container">
		<form class="status osvjezavanje" method="GET"
			action="${pageContext.servletContext.contextPath}/mvc/tvrtka/admin/nadzornaKonzolaTvrtka">
			<%
			Object statusObjT1 = request.getAttribute("statusT1");
			if (statusObjT1 != null) {
				Integer status = (Integer) statusObjT1;
				boolean statusOk = (status == 200);
				String ikona = statusOk ? "✓" : "✗";
				String cssClass = statusOk ? "status_uspijesan" : "status_neuspijesan";
				String cssClassGumb = statusOk ? "status_gumb_crveni" : "status_gumb_zeleni";
				String akcija = statusOk ? "zaustavi" : "pokreni";
			%>
			<div class="status_item  <%=cssClass%>">
				<span class="status_ikona"><%=ikona%></span> <span>Status -
					REGISTRACIJA: <%=status%></span>
			</div>

			<input type="hidden" name="<%=akcija%>" value=1>
			<button type="submit" class="<%=cssClassGumb%>"><%=akcija%></button>

			<%
			} else {
			%>
			<div class="status_item  status_neuspijesan">
				<span>Nema podataka - REGISTRACIJA</span>
			</div>
			<%
			}
			%>
		</form>

		<form class="status cijeliPos" id="cijeliPos" method="GET"
			action="${pageContext.servletContext.contextPath}/mvc/tvrtka/admin/nadzornaKonzolaTvrtka">
			<div class="status_item" id="status-item-server">
				<span class="status_ikona" id="ikona-server">-</span> <span>Status
					- CIJELI POSLUŽITELJ: <span id="status-kod-server">-</span>
				</span>
			</div>

			<input type="hidden" name="kraj" value="true">
			<button type="submit" class="hidden" id="gumb-server">-</button>
		</form>

		<form class="status osvjezavanje" method="GET"
			action="${pageContext.servletContext.contextPath}/mvc/tvrtka/admin/nadzornaKonzolaTvrtka">
			<%
			Object statusObjT2 = request.getAttribute("statusT2");
			if (statusObjT2 != null) {
				Integer status = (Integer) statusObjT2;
				boolean statusOk = (status == 200);
				String ikona = statusOk ? "✓" : "✗";
				String cssClass = statusOk ? "status_uspijesan" : "status_neuspijesan";
				String cssClassGumb = statusOk ? "status_gumb_crveni" : "status_gumb_zeleni";
				String akcija = statusOk ? "zaustavi" : "pokreni";
			%>
			<div class="status_item  <%=cssClass%>">
				<span class="status_ikona"><%=ikona%></span> <span>Status -
					RAD: <%=status%></span>
			</div>

			<input type="hidden" name="<%=akcija%>" value=2>
			<button type="submit" class="<%=cssClassGumb%>"><%=akcija%></button>

			<%
			} else {
			%>
			<div class="status_item  status_neuspijesan">
				<span>Nema podataka - RAD</span>
			</div>
			<%
			}
			%>
		</form>
	</div>
	<br />

	<script type="text/javascript">
	var wsocket;
	
	function connect() {
	    var adresa = window.location.pathname;
	    var dijelovi = adresa.split("/");
	    adresa = "ws://" + window.location.hostname + ":"
	            + window.location.port + "/" + dijelovi[1] + "/ws/tvrtka";
	    if ('WebSocket' in window) {
	        wsocket = new WebSocket(adresa);
	    } else if ('MozWebSocket' in window) {
	        wsocket = new MozWebSocket(adresa);
	    } else {
	        alert('WebSocket nije podržan od web preglednika.');
	        return;
	    }
	    wsocket.onmessage = onMessage;
	
	    let statusPoslan = false;
	
	    wsocket.onopen = function() {
	        if (!statusPoslan) {
	            wsocket.send("");
	            statusPoslan = true;
	        }
	    };
	}
	
	function onMessage(evt) {
	    var poruka = evt.data;
	    var dijelovi = poruka.split(";");
	    var radElem = document.getElementById("rad");
	    var obracuniElem = document.getElementById("obracuni");
	    var porukaElem = document.getElementById("poruka_tekst");
	    radElem.innerHTML = dijelovi[0];
	    obracuniElem.innerHTML = dijelovi[1];
	    porukaElem.innerHTML = dijelovi[2];
	    
	    azurirajStatusPosluzitelja(dijelovi[0]);
	}
	
	function azurirajStatusPosluzitelja(statusTekst) {
	    var statusOk = (statusTekst === "RADI");
	    var ikona = statusOk ? "✓" : "✗";
	    var cssClass = statusOk ? "status_uspijesan" : "status_neuspijesan";
	    var cssClassGumb = statusOk ? "status_gumb_crveni" : "hidden";
	    var akcija = statusOk ? "zaustavi" : "pokreni";
	    var statusItem = document.getElementById("status-item-server");
	    var ikonaElem = document.getElementById("ikona-server");
	    var statusKodElem = document.getElementById("status-kod-server");
	    var gumbElem = document.getElementById("gumb-server");
	    
	    if (statusItem && ikonaElem && statusKodElem && gumbElem) {
	        statusItem.className = "status_item " + cssClass;
	        ikonaElem.textContent = ikona;
	        statusKodElem.textContent = statusTekst;
	        gumbElem.className = cssClassGumb;
	        gumbElem.textContent = akcija;
	    }
	    
	    if(!statusOk) gumbElem.style.display = "none";
	}
	
	async function _ajaxRukovatelj(e) {
		  e.preventDefault();
		  const forma = e.currentTarget;
		  const params = new URLSearchParams(new FormData(forma));
		  const url = forma.action + '?' + params;

		  try {
		    const resp = await fetch(url, {
		      headers: { 'X-Requested-With': 'XMLHttpRequest' }
		    });
		    const text = await resp.text();
		    const parser = new DOMParser();
		    const doc = parser.parseFromString(text, 'text/html');

		    const noveForme = doc.querySelectorAll('#status-container form.osvjezavanje');
		    const stareForme = document.querySelectorAll('#status-container form.osvjezavanje');
		    noveForme.forEach((nf, i) => {
		      if (stareForme[i]) {
		        stareForme[i].outerHTML = nf.outerHTML;
		      }
		    });
		    bindStatusForms();

		  } catch (err) {
		    console.error('Neuspješan AJAX poziv:', err);
		    alert('Greška prilikom osvježavanja statusa. Pokušajte ponovno.');
		  }
		}

		function bindStatusForms() {
		  document
		    .querySelectorAll('#status-container form.osvjezavanje')
		    .forEach(form => {
		      form.removeEventListener('submit', form._ajaxRukovatelj);
		      form._ajaxRukovatelj = _ajaxRukovatelj;
		      form.addEventListener('submit', form._ajaxRukovatelj);
		    });
		}
	window.addEventListener('load', function() {
	    connect();
	    bindStatusForms();
	    
	    const kontrolaForma = document.getElementById("formaKontroler");
	    kontrolaForma.addEventListener("submit", function(e) {
	        e.preventDefault();
	        const tekst = document.getElementById("internaPoruka").value;
	        if (wsocket.readyState === WebSocket.OPEN) {
	            wsocket.send(tekst);
	            this.reset();
	        } else {
	            alert("Veza nije otvorena, pokušajte kasnije.");
	        }
	    });
	}, false);
	</script>

</body>
</html>
