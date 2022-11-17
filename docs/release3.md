**Release-notes for release 3:**
=
<u><font size="3">**Sprint tilknyttet release 3**</font></u>  
<u>Mål for sprinten:</u> Implementere REST-API slik at appen kommuniserer med server, i tillegg til å utvide UI-funksjonalitet.

<u>Oppgaver tilknyttet sprinten:</u>  
* Implementere REST-API backend (estimat = 10 timer)
* Implementere REST-API frontend (5 timer)
* Endre expirationDate fra String til LocalDate (5 timer)
* Endre UI-design (3 timer)
* Legge til funksjonalitet der matvarer som snart går ut på dato får rød skrift (2 timer)
* Skrive tester til all kode (10 timer)
* Fikse alle CheckStyle-advarsler (2 timer)
* Lage pakkediagram og sekvensdiagram samt. oppdatere klassediagrammet (3 timer)
* Skrive release3.md (1 time)
* Dokumentere REST-tjenesten (1 time)
* Lage konfigurasjonen for shippable product med JPackage og JLink (2 timer)

<u><font size="3">**Hva har vi gjort?**</font></u>  
Vi har implementert REST-api i appen. Enhver endring i innhold i enten Fridge eller Freezer blir kommunisert til serveren, slik at programmets gjeldende status alltid er lagret på nettet. Programmet både sender og henter data fra server. 

Vi har også utvidet appens funksjonalitet, f.eks. ved å ha endret utløpsdatoens datatype fra en String til et LocalDate-objekt. Vi har endret brukergrensesnittet til å ta i bruk en DatePicker slik at brukeren kan velge utløpsdato på en mer naturlig måte. I tillegg har vi lagt til en funksjon der matvarer som er 10 eller færre dager unna å gå ut på dato blir fremhevet med rød skrift. Vi har også lagt til støtte for at ethvert Food-objekt kan ha en av fire enheter knyttet til seg, nemlig liter, stk., gram eller kilo. Vi har også ryddet opp i og modernisert designet ved at brukeren nå trykker ENTER for å legge til matvarer istedenfor å trykke på en knapp. 

Vi har også lagt til nye knapper med bilde av en søppelkasse til fjerning av matvarer for å tydeliggjøre funksjonen deres og gjøre brukergrensesnittet mer intuitivt. I tillegg har vi opprettet en ny klasse ved navn FoodComparator som sorterer matvarene i henholdsvis fridge og freezer basert på utløpsdato. Matvarene med utløpsdato kortest frem i tid fra dagens dato kommer først slik at brukeren blir gjort ekstra oppmerksom på hva som snart må brukes opp. 

Vi har også økt appens stabilitet ved å ha forbedret validering av input i tekstfeltene knyttet til fjerning av matvarer.

