# FOLT – Dostava hrane i pića
Aplikacija u sklopu predmeta Napredne web tehnologije i servisi  (Fakultet organizacije i informatike) čija je svrha dostava hrane i pića franšize restorana.

# Korištene tehnologije

- Java (17) – glavni programski jezik

- Maven – upravljanje projektima i ovisnostima

- JUnit – jedinično testiranje

- MicroProfile – izrada i integracija RESTful servisa

- Jakarta EE:

  - Jakarta MVC – korisničko sučelje

 - Jakarta Faces (JSF) – sučelje za rad partnera i korisnika

  - Jakarta JPA – rad s bazom podataka (entiteti i fasade)

- WebSockets – dvosmjerna komunikacija u realnom vremenu

- JSON/XML – razmjena podataka

- Eclipse IDE – razvojno okruženje

# Ključne značajke
- Mrežni soketi — tekstualne naredbe (originalna verzija, vježba 4): komande su u čitljivom tekstu, riječi odvojene jednom prazninom, svaka komanda završava s \n. Odgovori su OK ili ERROR .... Jelovnici, karte pića i obračuni se šalju kao JSON kad je potrebno.
- REST (HTTP + JSON, MicroProfile — vježba 7/8): dostupne su dvije glavne skupine endpointa api/tvrtka i api/partner za sve GET/POST/HEAD pozive; podatci idu/vrate se kao application/json. Autentikacija za operacije vezane uz narudžbe koristi HTTP zaglavlja (trenutno korisnik i lozinka u čitljivom obliku) — ako nisu ispravni, REST vraća grešku i komanda se ne šalje partneru. Podaci o partnerima i obračunima pohranjuju se u baze/tablice (partneri,obracuni itd.).
- WebSocket (real-time): admin/klijent sučelje prima poruke na npr. /ws/tvrtka s oblikom STATUS;BROJ_OBRAČUNA;PORUKA npr. RADI;201;Danas je petak. To služi za realtime status poslužitelja i broja obračuna.

# 📌 Funkcionalnosti aplikacije FOLT
## 👤 Prijava i korisnici
- Registracija partnera u sustav (partner se povezuje s jednom vrstom kuhinje, dobiva sigurnosni kod koji služi za autentikaciju)
<img width="1917" height="899" alt="image" src="https://github.com/user-attachments/assets/c7ee36e7-f774-478b-b3de-cfb58b95365c" />
- Autentikacija kupaca putem web-sučelja (Jakarta Faces, container-based form auth, bilježi se u tablicu zapisi)
<img width="1907" height="865" alt="image" src="https://github.com/user-attachments/assets/957fea87-7e93-49ad-8149-c415ce16461f" />
- Dodavanje novih korisnika
- Administracijski i privatni dijelovi aplikacije odvojeni su od javnih (različite role: nwtis, admin)

## 🍽️ Naručivanje hrane i pića
- Pregled jelovnika i karte pića
<img width="1910" height="930" alt="image" src="https://github.com/user-attachments/assets/fb6a7d38-621f-494f-9faa-389fd8afb2cc" />
- Otvaranje nove narudžbe
<img width="1920" height="942" alt="image" src="https://github.com/user-attachments/assets/9de8b15e-85b3-46c8-a5ea-79a17aa5e0a1" />
- Dodavanje jela u narudžbu
- Dodavanje pića u narudžbu
- Pregled aktivne narudžbe
- Dopuna postojeće narudžbe
- Plaćanje narudžbe

## 🛠️ Administracija i upravljanje sustavom
- Administracijska konzola za Tvrtku
  - pregled statusa pojedinih poslužitelja
  - pauziranje i nastavak rada poslužitelja
  - kraj rada poslužitelja
  - osvježavanje jelovnika i karte pića
  - pregled broja primljenih obračuna i statusa rada u realnom vremenu
<img width="1927" height="919" alt="image" src="https://github.com/user-attachments/assets/5b4ed7a5-cb8a-499d-a561-ccc7eb5c95c3" />
- Administracijska konzola za Partnere
  - pregled i upravljanje korisnicima
  - pregled obračuna u razdoblju od-do
  - pregled rada korisnika
  - upravljanje statusom poslužitelja partnera (pauza, start, kraj rada, spavanje dretvi)
<img width="1916" height="916" alt="image" src="https://github.com/user-attachments/assets/de934d06-0e11-444c-9345-2a277ae0ab91" />

## Analitika i statistika
- Pregled obračuna prema razdoblju (od-do), s filtriranjem:
  - ukupno (jela i pića)
  - samo jela
  - samo pića
  - po partneru
<img width="1912" height="939" alt="image" src="https://github.com/user-attachments/assets/c87e00e1-25f1-4038-925e-20c3fa0079a9" />

- Evidencija prijava i odjava korisnika

