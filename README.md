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


## Zápočtový program 

Jako svůj zápočtový program jsem pokračaovl na již dřívějším tomto projektu.
Níže jsou sepsáný změny, ke kterým došlo. Vypsal jsem jen ty nejdůležitější a největší.
Změny si můžete ověřit v commit history.

Projekt je rozdělen na PCClienta a Server. Obě aplikace jsou v podadresářích, jako maven projekty. 
Server se při spuštění pokusí vytvořit.
Zároveň spuštění více klientů způsobí přidání jako několik různých klientů i přes to
že mají stejnou ip addressu (pro snazší debugování).

Dokumentaci lze vygenerovat pomoci příkazu `doxygen` ve složce doc pro daný projekt. 

### Server changes
- date changed from int to long

Již nehrozí přetečení času.

- history of visibles added

Visible je každá Creature, Item, nebo Resource. Nyní uchovává informace o své historii

- memory of creatures added

Každá creature si nyní uchovává informace o tom, co vnímá okolo sebe. Uchovává si inormaci, kterou visible viděl kdy. Stav visible v danou chvíli je pak zjistitelný z historie daného visible.

- unbounded place added

Místo, které není vázáno s žádnou mapou. Place dědí z UnboudedPlace a je vázáno s konkrétní mapou.

- space item added

Jedná se o speciální typ předmětu, který je spojený s nějakým UnboundedPlace. Může to být vchod do jiné mapy, nebo předmět s místem, které není vázáno s žádnou mapou. Tedy například batoh, nebo skříň. 

- knowledges added

Umožňuje specifikovat, čeho je dané creature schopna.

- creatures BehavioursPossibleIngredients added

BehavioursPossibleIngredients je konkrétní visible, nebo knowledge, které je zapotřebí pro uskutečnění nějaké behaviour.
Každý má seznam BehaviourPossibleRequirements, které splňuje splnilo. 

- Behaviours Possible requirement added

Jedná se o požadavek, který je potřeba aby byl splněn pro uskutečnění nějaké behaviour. Každý BehaviourType má jasně stanovené kolik jakých BehaviourPossibleRequirementů je potřeba. Například k vytvoření ohniště je potřeba mimo jiné získat hořlavý předmět. Tudíž Behaviour postavOhniště bude mít v seznamu požadavků mimo jiné BehavioursPossibleRequirement označující potřebuji daný počet hořlavých visibles.

- Tools Utilization added

Jedná se o BehavioursPossibleRequirement pro nějaký nástroj.

- memory of visible items split into currentlyVisibleObjects, lastVisiblesPositionWhenVisionLost and visibleObjectSpotted.

Každá creatura má nyní seznam aktuálně viděných visibles. Zároveň si ukládá kdy byl naposledy spatřen nějaký visible na konkrétním místě. To je vhodné pro to, když se creature navrátí na místo, kde dříve už někdy byl a něco viděl. Tak aby nemusel hledat dané věci, které se nezměnily znovu.
Dále si pamatuje seznam kdy spatřil danou visible. To může mít celou řadu využití.

- comments added

- new feasible behaviours added 

Jedná se o seznam aktuálně proveditelných behaviours u dané creatury. 
Seznam se mění se změnami v BehavioursPossibleIngredient.
- some synchronization conflicts resolved

Především listy, do kterých se přistupuje z více vláken.
- messages added

Přidáno odesílání messages a informací dané creatury. Pokud je creaturou hráč, informace se odešle klientovy.


### PC Client changes

Ten byl vytvořen komplet celý. Využívá swing knihovnu pro vykreslování uživatelského rozhraní.

Přijímá messages ze serveru a snaží se je přehledně zobrazovat klientovy.

Klient se automaticky objeví při zapnutí serveru v rohu mapy. Zobrazují se pouze places ve vzdálenosti 2, a to pouze ty, které hráč vidí. Při tom záleží na altitude daného place. Pokud brání ve výhledu jedno place, places za ním nejsou vidět, pokud se za ním nenachází ještě vyšší.


