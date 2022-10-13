[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2239/gr2239)

Gruppe 39 ITP - FridgeManager
=

<u><font size="3"> **Beskrivelse av prosjektets strktur**</font></u>   

Dette prosjektet inneholder Java-kode og tester til disse. Det er delt opp i to separate moduler: "core" og "fxui".   
<u>**Core:**</u>  
"core" inneholder "Model"-delen av prosjektet, nemlig kjernelogikken. Dette inkluderer klassene "Food" og "FridgeManager". Disse brukes i Controlleren, og fungerer som grunnmuren i programmet. Disse ligger altså i mappen "fridge_manager/core/src/main/java/fridge_manager/core. Tester til disse to klassene ligger under "fridge_manager/core/src/test". "core" inneholder også klassene som bruker JSON til skriving og lesing fra fil. Disse ligger under ""fridge_manager/core/src/main/java/fridge_manager/json".  
<u>**FXUI:**</u>  
"fxui"-modulen inneholder klassene som styrer appens brukergrensesnitt, nemlig "FridgeController" og "FridgeApp". Disse ligger under "fridge_manager/fxui/src/main/java/fridge_manager/ui. "fxui" inneholder også FXML-filen "FridgeApp.fxml" med appens design. Denne ligger under "fridge_manager/fxui/src/main/resources". 

Bilder som er blitt brukt ligger under "fridge_manager/pictures". 

<u><font size="3"> **Beskrivelse av bygging og kjøring**</font></u>  

FridgeManager bygges og kjøres ved hjelp av Maven. 

For å kjøre prosjektet har man to muligheter. Åpne en terminal og skriv følgende tre kommandoer:
```
cd FridgeManager/
mvn install
mvn javafx:run -f fxui/pom.xml
```
Alternativt kan man benytte seg av:
```
cd FridgeManager/
mvn install
cd fxui/
mvn javafx:run
```
Legg merke til at `mvn install` både installerer Maven og kjører alle tester og kvalitetssjekker. 


<u><font size="3"> **Gruppens arbeidsmetode**</font></u>  

<u>**Arbeidsvaner**</u>: Vi bruker Gitlab flittig for å holde styr på arbeidsfordeling og nåværende status i prosjektet. For hver nye arbeidsoppgave oppretter vi en «issue», og merker denne med riktig label, som f.eks. «doing». Vi holder styr på alle gjeldende issues vha. «boardet» i sidemenyen på Gitlab. Dette gjør at vi enkelt kan endre statusen til ulike utviklingsoppgaver. 
Vi har laget flere hensiktsmessige labels for å kunne holde styr på utviklingsoppgavene, som f.eks. «importance:high», «importance:medium» og «importance:low» for å kunne gi ulike oppgaver ulik prioritet. Vi knytter også utviklingsoppgaver til ulike moduler gjennom merkelapper av typen «feat/UI», «feat/testing», «feat/IO» eller «feat/core». Dette gjør det enklere å skille mellom forskjellige deler av prosjektet.  

Ved utvikling av nye features oppretter vi en ny branch ved navn «feat/navnpåfeature». Dette for å unngå konflikter i master, ettersom det er viktig at denne fungerer til enhver tid. Ved ferdigstillelse av feature merger vi først alle brancher utenom master inn i en, sjekker at denne funker, for så å merge denne inn i master. På denne måten minimerer vi risikoen for at master ikke vil fungere etter merging.  

<u>**Arbeidsflyt:**</u> Gruppen møtes tre ganger i uken slik som beskrevet i gruppekontrakten, og møtene følger stort sett samme struktur. Vi avholder et kort innledende møte i starten av økten for å få oversikt over hva gruppemedlemmene har jobbet med på egenhånd siden sist møte. Deretter diskuterer vi hva som må gjøres den aktuelle økten, og fordeler arbeidsoppgaver basert på det vi kommer frem til.  
Den resterende møtetiden på individuell/parvis jobbing og løpende spørsmål/diskusjon innad i gruppen om man skulle lure på noe eller ønsker å oppklare noe tilknyttet en oppgave. På slutten av møtet diskuterer vi kort hva vi har fått til i dag, og hva som må gjøres innen neste møte blir avholdt. Ved arbeid utenfor møtetider benytter vi oss av Messenger for kommunikasjon.  

<u>**Kodekvalitet:**</u> For å sikre at koden vår holder god kvalitet rådfører vi oss ofte med resten av gruppen om egen kode. Ved å få en annen på gruppen til å lese gjennom egen kode før commits sikrer vi at vi unngår åpenbare feiltrinn som kan være vanskelige å oppdage selv. Gruppen har også et stort fokus på å skrive ryddig og lesbar kode, noe som har ført til flere oppryddinger i f.eks. Controller og tester. Dette gjør det betydelig lettere for gruppens medlemmer å få oversikt over andres kode, noe som effektiviserer utviklingsarbeidet.  

I tillegg mener vi det er viktig å sjekke at koden vår faktisk fungerer før vi sier oss ferdig med den. Derfor flytter vi alle nye features til «intest» før de blir plassert i «done». Dette for å sikre at all logikk blir ferdigtestet før vi sier oss ferdig med den aktuelle featuren. Målet for testingen av prosjektet er å teste så å si alle metoder som inneholder logikk utenom helt ordinære getter- og setter-metoder. Dette inkluderer ikke metoder som kun oppdaterer det visuelle, altså f.eks. metoden startup() i Controlleren som setter riktig starttilstand for alle FXML-elementer ved oppstart av appen. 

Checkstyle, Spotbugs og JaCoCo er implementert som plugins i prosjektets POM-fil  for å sikre god kodekvalitet.
Vi benytter oss for Checkstyle for sjekk av formatering. Hensikten med dette er å sørge for ryddig kode. 
Vi bruker også Spotbugs for å sikre oss mot kode som kan føre til uventet programkrasj, i tillegg til å sørge for at programmet har et hensiktsmessig design. 
Vi bruker JaCoCo for å sørge for at prosjektet har høy testdekningsgrad. 
For å gjennomføre en sjekk kan man bruke kommandoen `mvn verify` i terminalen. 