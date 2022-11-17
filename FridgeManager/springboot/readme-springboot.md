**Beskrivelse av klassene i springboot-modulen:**
=
<u><font size="3">FridgeManagerApp.java:</font></u> Klasse for å starte springboot-serveren.    
<u><font size="3">FridgeManagerController.java:</font></u> Formaterer og håndtere HTTP-forespørsler til and fra springboot-serveren        
<u><font size="3">FridgeManagerService.java:</font></u> Bindeleddet mellom springboot, fillagring og modellen     


**Valg av lagringsmetode**
=
Vi har valgt å bruke implisitt lagring, ettersom mange moderne applikasjoner benytter seg av dette, og det vil dermed være mer intuitivt for brukerne. Vi unngår også faren for at data vil gå tapt, f.eks. ved en krasj eller tap av tilkobling. 

