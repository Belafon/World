# World
Discrete simulation of the World 

## Getting Started

Cílem mého programu je vytvořit herní svět. Vytvořit prostor tvořený mapou, která se
skládá z jednotlivých políček. Každé políčko má pak své vlastnosti (nadmořská výška, pří-
rodní bohatství, počasí...).
Na této mapě se pak pohybují stvoření, které mohou provádět nějaké činnosti. Dále se na
mapě vyskytují předměty, se kterými mohou stvoření interagovat.

Další důležitou částí světa je pak čas, se kterým se pojí denní cyklus, změna všech statistik
postav, předmětů, činností...

Celý projekt je rozdělen na Server a Klienta, přičemž server je jeden konkrétní program,
zatímco klient může být jakýkoli program posílající zprávy serveru odpovídající jeho proto-
kolu, přičemž klient vždy pouze ovládá nějaké jedno stvoření ve světě.

## Zápočtový program  -  advanced programming in java

Jedná se o pokračování v projektu.
Zápočtový program obsahuje rozšíření o umožnění provádění behaviours, 
tedy jedná se o ovládání postavy hráčem.

Následně byl vytvořen také mobilní klient pro android, jehož kód a spustitelný soubor se nachází v podadresáři MobileClient.
Minimální verze androidu je Android 10 (API level 29).

Samotný kód využívá javu verzi 17 s gradle 8.0.2.

Pro spuštění musí být server i client spuštěn ve stejné lokální síti.

Spuštění mobilního klienta by mělo být možné přesunutím souboru `MobileClient/apk/debug/app-debug.apk` na mobilní zařízení
a následným supštěním. S tím, že android se tomu bude snažit zabránít co to jen jde.

Popřípadě lze spustit aplikaci skrze android studio. Na to je potřeba android studio s java verzí 17.

Server lze spustit jako jar aplikaci ideálně z příkazové řádky.

`java -jar Server/world/target/world-0.1-jar-with-dependencies.jar`.

Dokumentaci lze vygenerovat pomoci příkazu `doxygen` ve složce doc pro daný projekt. 

### Mechanika behaviours

Libovolná creatura může provádět nějaké behaviour pouze pokud splňuje všechny požadavky definovány pomocí BehavioursPossibleReauirementů a jejich počtem, například pro vykonání behaviour Move je zapotřebí splňovat jedenkrát requirements REQUIREMENT_IS_REACHABLE. 

Následně libovolná creatura mlže splnit dané requirements za pomoci BehavioursPossibleIngredient. 
Například UnbounddedPlace implementuje BehavioursPossibleIngredient, což znamená, že musí implementovat methodu ```List<BehavioursPossibleRequirement> getBehavioursPossibleRequirementType(Creature creature)```, což je metoda, která bere za parametr creaturu a vrací seznam požadavků, které může daná creatura pomocí dané ingredienty splnit.

Následně, pokud pro danou creaturu vrací dané UnboundedPlace list, který obsahuje REQUIREMENT_IS_REACHABLE, tak může daná creatura provést behaviour Move.

Nelze splnit více BehavioursPossibleRequirement pomocí jedné ingredienty.

## logs na serveru

Byly přidány nove log hlášky, které jsou sepsány v README u serverovské aplikace.
Jsou přidány log hlášky pro manuální nastavení aktuální denní doby, počasí,
nebo mraků na aktuální pozici hráče.

Také byla přidána log hláška log time x, pro zrychlení, nebo zpomalení celkové rychlosti hry, herního prostředí.

X udává delay hrací smyčky.

Pro lehčí sledování doporučuji zrychlení času pomocí logu `log time 20` do konzole na serveru.
Pokud budete chtít sledovat vlastní počasí denní dobu, nebo mraky, pak doproučuji naopak zpomalit čas pomocí `log time 3000`,
aby Vaše logy server neměnil moc často.


### MobileClient

Po zapnutí a připojení k serveru se aplikace automaticky pokusí spustit hru,
přičemž server by měl být nastaven na hru pro jednoho hráče.
Automaticky vygeneruje svět a vytvoří hráče, který se nachází na mapě na políčku 0, 0.

Zároveň je na serveru nastaveno, že na stejném políčku se vygenerují nějaké předměty,
resource a cratury, které hráč vidí od začátku,
protože zatím neobsahuje hra behaviour pro hledání předmětů, nebo reesourcerů.

Celá hra je v jedné aktivitě GameActivity, která obsahuje všechny potřebné komponenty pro hru v jedotlivých 
fragmentech. Na levé straně je fragment s navigací, kde lze přepínat mezi fragmenty pro:
- **statistiky postavy**
- **seznamy s visibles**, které jsou rozděleny do tří sloupců:
    - creatures, které jsou v okolí na stejném políčku jako hráč
    - items na stejném place jako hráč
    - resources nápodobně
- **seznam proveditelných behaviours**
- **mapa okolí**, která zobrazuje políčka v okruhu s poloměrem 3 políčka. Každé je možné rozkliknout a vidět podrobnější informace o daném políčku.
- **inventář**, který zobrazuje všechny předměty, které má hráč u sebe.
- **rozhlídnutí po aktuálním políčku**. Je zde pouze pro to, aby si hráč mohl lépe prohlédnout obrázek  v pozadí.

V pozadí aktivity je imageView, který se mění podle aktuální Place na které se hráč nachází.
Těsně před ním je částečně transparentní view, které mění svou barvu podle aktuálního počasí a denní doby.

Filter je pouze jeden a jeho barva se vypočítává ve WeatherFragment, kde se nachází množina colorViewTransitions dle které dochází k výpočtu.

Implicitně má přibližně 50 FPS.

Zatím je filter ovlivněn 2 typy událostí:
- **denní dobou**, neustále se opakuje jednou denně.
- **počasím**, 
    - **při dešti** se ztmaví a zesvětlí,
    - **při bouřce** se ztmaví a může docházet k bleskům
    - **při oblačnu** záleží na velikosti mraků. Pokud není zcela zataženo, může docházet k občasnému zatmavení a s následným zesvětlením. Naopak, pokud je zcela zataženo, tak se filter zatmaví, dokud nedojde ke změně mraků.
  

V případě u transition pro část dne `PartOfDayColorViewTransition` se využívá fronta pro jednotlivé přechody. Pokud je fronta delší, přechody se schválně zrychlí. Tyto požadavky jsou definovány pomocí BehaviourPossibleIngredients. Přechod se ovšem nezrychlí uprostřed, ale pouze při začátku. Pokud se tedy zrychlí čas moc, může se stát, že jeden přechod jede normálně rychle, ale mezi tím se fronta naplnila tak, že další transmition skoro okamžitě skončí, což vede k blikání. Děje se to ale jen při velmi vysokém zrychlení času. 

Zatím lze provádět pouze 3 typy behaviours:
- **Move**, lze provést přes fragment s mapou, nebo přes fragment se seznamem behaviours. (to platí pro všechny behaviours a info fragmenty o konkrétních ingrediencích).
- **Eat**, lze provést pouze z inventáře, nebo z fragmentu se seznamem behaviours.
- **Pick up an item**, lze provést z fragmentu se seznamem behaviours, nebo ze seznamu visibles.

## Nedokončené části

Určitý kód zatím nemá vliv na výslednou apolikaci, protože není zcela hotov.

Je tím například:
- **WelcomingActivity**, kde WelcomingActivity by se měl zobrazit pouze při prvním spuštění aplikace a obsahovat nějaké uvítací informace. Hotové je částečně, ale nevěděl jsem co zde zatím přidat kromě výběru jména.
- **MenuActivity** obsahuje základní menu, ale zatím funguje pouze tlačítko pro spuštění hry, proto se automaticky hra spustí.
- **Hudba**, ta je ovládána skrze ClipsAdapter a Pool ve složce sound. Pool slouží pro krátké zvuky, ClipsAdapter pak pro delší hudbu, nebo zvuk.
- **Vybavení**, neboli PlayersGear by mělo být v inventáři s behaviours nasazení. Nasazené oblečení by mělo přidávat postavě nějaké staty.
- **Behaviour hledání resources na nějakém políčku** - teoreticky je hotové, ale není odladěno. Hledání a pozorování jiných visibles funguje podle hodnoty viditelnosti. V případě resource se jedná o hodnotu mass. Následně při hledání něčeho nějakou creaturou jsou nejviditelnější visibles viditelné ihned, následně čím méně jsou visibles viditelné oproti ostatním visibles, tím těžší je je nalézt. Ovšem poté, co je nějaké visible nalezeno, creatura by si měla její pozici zapamatovat a do doby, než visible změní svoji pozici, mělo by být viditelné pro danou creaturu.