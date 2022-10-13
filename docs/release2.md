**Release-notes for release 2:**
=
**Sprint tilknyttet release 2**  
<u>Mål for sprinten:</u> Modularisere prosjektet - dele opp i ulike moduler for å skille mellom kjernelogikk (core) og brukergrensesnitt (fxui). Utvide programmet ved å legge til funksjonalitet som gir brukeren mulighet til å fjerne matvarer, både spesifikke instanser og et visst antall av en spesifisert matvare. Legge til validering av input og forbedre stabiliteten til grafiske elementer som knapper og tekstfelt. Innføre bruk av JSON til fillagring. 

<u>Oppgaver tilknyttet sprinten:</u>  
* Modularisere prosjektet (estimat = 10 timer)
* Implementere funksjonalitet for å fjerne matvarer fra fridge eller freezer (2 timer)
* Bytte til fillagring vha. JSON (12 timer)
* Gjøre brukergrensnitt mer robust og inituivt (3 timer)
* Skrive tester for exceptions (2 timer)
* Gjøre testene mer oversiktlige og lesbare (1 time)
* Legge til validering av input fra bruker (1 & 1/2 time)
* Gjøre kontroller mer oversiktlig og lesbar (2 timer)
* Skrive tester til kontroller (10 timer)
* Skrive tester til lesing og skriving fra fil (7 timer)

<u>**Hva har vi gjort?**</u>  
Vi har utvidet appen med ytterligere funksjonalitet, da spesielt har vi gitt brukeren mulighet til å fjerne matvarer fra kjøleskapet. Dette kan gjøres ved å trykke på en bestemt matvare i enten fridge eller freezer, og deretter trykke på den øverste av de to "Remove Item"-knappene nederst på skjermen. 

Brukeren har også mulighet til å fjerne et bestemt antall av en gitt matvare ved hjelp av inntastingsfeltene nederst på skjermen. Her er det mulig å skrive inn navnet på og antallet av den matvaren du ønsker å fjerne, samt velge om du vil fjerne denne fra fridge eller freezer gjennom en drop-down meny. Deretter trykker man på den nederste "Remove-item"-knappen til høyre for nevnte inntastingsfelter. 

Om antallet brukeren ønsker å fjerne av en matvare er <u>**mindre**</u> enn det totale antallet av den matvaren i enten fridge eller freezer vil programmet gå gjennom den av disse som er valgt og minske "quantity" til ulike instanser av objektet helt til hele mengden er fjernet. Det er viktig å merke seg at alle instanser av en matvare med quantity = 0 vil fjernes fra fridge og freezer hver gang en av dem blir manipulert av programmet.  
Om antallet brukereren ønsker å fjerne av en matvare er <u>**større**</u> enn det totale antallet av den matvaren i enten fridge eller freezer vil programmet fjerne alle instanser av denne matvaren. 

I tillegg har vi lagt til diverse forbedringer og stabilitetsoppgraderinger, som f.eks. validering av input. Dette er integrert i alle tekstfeltene, og vil sjekke om disse er skrevet inn på rett format. Eksempler på ugyldig input er tall i enten matvarenavn eller navn på eier, samt uriktig format på utløpsdato (dd/mm/åååå). Om input er ugyldig vil programmet fremvise teksten "Ugyldig input" i rød skrift midt på skjermen. Denne forsvinner når brukeren trykker på et av tekstfeltene, ettersom man da får mulighet til å rette på sin egen input. 

Vi har også endret programmet slik at inntastingsfeltene brukeren benytter seg av for å legge til matvarer tømmes for innhold hver gang noe blir lagt til i enten fridge eller freezer. Dette fordi det er unaturlig å legge til to like instanser av samme matvare rett etter hverandre, sammenlignet med å bare øke "quantity" til en instans av samme objektet til riktig verdi. 


