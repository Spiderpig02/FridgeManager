[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2239/gr2239)

Gruppe 39 ITP - FridgeManager
=
**Beskrivelse av prosjektets mappestruktur**  
Dette prosjektet inneholder Java-kode og tester til disse. Dette er modularisert og ligger dermed i to mapper: 

"FridgeManager/core/src/main/java/fridge_manager"
og
"FridgeManager/fxui/src/main/java/fridge_manager"

Disse har tilhørende tester :

"FridgeManager/core/src/test/java/fridge_manager/core"
og
"FridgeManager/fxui/src/test/java/fridge_manager/ui"


  Prosjektet inneholder også en FXML-fil med appens brukergrensesnitt som ligger i fxui. Denne ligger under "FridgeManager/fxui/src/main/resources/fridge_manager/ui". Eventuelle bilder som er blitt brukt ligger under "FridgeManager/core/src/main/resources/pictures". 

**Beskrivelse av bygging og kjøring**  
FridgeManager bygges og kjøres ved hjelp av Maven, og testes ved bruk av Junit. Testdekningsgraden rapporteres av Jacoco.

**Kjøring av programmet**

For å kjøre programmet er det tre nødvendinge komandoer
```
cd FridgeManager/
mvn install
mvn javafx:run -f fxui/pom.xml
```
