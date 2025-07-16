# SoccorsoWeb - Sviluppo Web Avanzato
---
## Operazioni da realizzare
Segue la lista delle operazioni implementate.
URL di base: **http://swa.net/soccorsoweb/rest** e ci riferimo ad essa tramite **[BASE]**

## Riepilogo operazioni
- #### Operazione 1.1 - Login con username e password
- #### Operazione 1.2 - Logout
- #### Operazione 1.3 - Refresh
- #### Operazione 2 - Inserimento di una richiesta di soccorso
- #### Operazione 3 - Convalida di una richiesta di soccorso
- #### Operazione 4 - Lista (paginata) delle richieste di soccorso, filtrata in base alla tipologia (attive, in corso, chiuse, ignorate)
- #### Operazione 5 - Lista delle richieste di soccorso chiuse con risultato non totalmente positivo (livello di successo minore di 5)
- #### Opetazione 6 - Lista degli operatori filtrati per stato 
- #### Operazione 7 - Creazione di una missione
- #### Operazione 8 - Chiusura di una missione in corso
- #### Operazione 9 - Annullamento di una richiesta du soccorso (da parte dell'amministratore)
- #### Operazione 10 - Dettagli di una missione
- #### Operazione 11 - Dettagli di una richiesta di soccorso
- #### Operazione 12 - Dettagli di un operatore
- #### Operazione 13 - Lista delle missioni in cui un operatore è stato coinvolto
- #### Operazione 14 (Operazione aggiuntiva) - Aggiunta di un operatore

---
# Specifica operazioni
### Operazione 1.1 - Login con username e password
**API URL**: _POST_ [BASE]/auth/login<br>
**Output**: 204 - NO CONTENT

---
### Operazione 1.2 - Logout
**API URL**: _DELETE_ [BASE]/auth/logout<br>
**Output**: 204 - NO CONTENT

---
### Operazione 1.3 - Refresh
**API URL**: _GET_ [BASE]/auth/refresh<br>
**OUTPUT**: 204 - NO CONtent

---
### Operazione 2 - Inserimento di una richiesta di soccorso
**API URL**: _POST_ [BASE]/requests<br>
**Input**: {request}<br>
**Output**: 201 - CREATED ([BASE]/requests/{idRichiestaAggiunta})

---
### Operazione 3 - Convalida di una richiesta di soccorso
**API URL**: _PATCH_ [BASE]/requests/{id}/validate<br>
**INPUT**:

    {
        "status":"attiva"
    }

**OUTPUT**: 204 - NO CONTENT

---
### Operazione 4 - Lista (paginata) delle richieste di soccorso, filtrata in base alla tipologia (in attesa, attive, in corso, chiuse, ignorate)
**API URL**: _GET_ [BASE]/requests/{status}?page={numero_pagina}&size={size_pagina}<br>
**OUTPUT**: 200 - OK

    {
        "page":{numero_pagina},
        "size":{size_pagina},
        "total":{numero_richieste_totali}
        "requests": [
            {
                "id":{id_richiesta},
                "descpription":{descrizione_richiesta},
                "position": {
                    "address":{indirizzo_richiesta},
                    "coordinates":{coordinate_richiesta}
                },
                "reporter": {
                    "name":{nome_segnalante},
                    "email":{email_segnalante}
                },
                "status":{status}
            },
            ...
        ]
    }

---
### Operazione 5 - Lista delle richieste di soccorso chiuse con risultato non totalmente positivo (livello di successo minore di 5)
**API URL**: _GET_ [BASE]/requests/closed?max_success_level=[0,1,2,3,4]<br>
**OUTPUT**: 200 - OK

    [
        {
            "id":{id_richiesta},
            "descpription":{descrizione_richiesta},
            "position": {
                "address":{indirizzo_richiesta},
                "coordinates":{coordinate_richiesta},
            },
            "reporter": {
                "name":{nome_segnalante},
                "email":{email_segnalante}
            },
            "status":"chiusa",
            "mission": {
                "teamLeader":{operatore_caposquadra},
                "team":[{operatori_team}],
                "objective":{obiettivo_missione},
                "startTime":{timestamp_inizio_missione},
                "endTime":{timestamp_fine_missione},
                "successLevel":{livello_successo_missione}
                "endigAdmin": {mission_ending_admin}
            }
        },
        ...
    ]

Nota: in questo output, endTime, successLevel e endingAdmin **non sono sicuramente nulli**, in quanto stiamo prendendo solo richieste con stato "chiusa"

---
### Opetazione 6 - Lista degli operatori filtrati per stato 
**API URL**: _GET_ [BASE]/operators/{status}<br>
**OUTPUT**: 200 - OK

    [
        {
            "id":{id_operatore},
            "name":{nome_operatore},
            "email":{email_operatore},
            "username":{username_operatore},
            "status":{status}
            "skills":[{abilità_operatore}],
            "licenses":[{patenti_operatore}]
        },
        ...
    ]

Nota: l'operazione chiedeva di prendere la lista degli operatori **liberi**, ma in questo modo abbiamo un'operazione parametrica, con la variabile che può essere "free", oppure "not free"

---
### Operazione 7 - Creazione di una missione
**API URL**: _PATCH_ [BASE]/requests/{id}/mission<br>
**INPUT**:

    {
        mission: {
            "teamLeader":{operatore_caposquadra},
            "team": [{operatori_team}],
            "objective":{obiettivo-missione},
            "startTime":{timestamp_inizio_missione},
            "endTime":null,
            "successLevel":null,
            "endingAdmin":null
        }
    }

**OUTPUT**: 204 - NO CONTENT<br>
Nota come **_"endTime"_** e **_"successLevel"_** siano settati a **_"null"_** in quanto, appena aggiunta una missione, questa non ha una data di fine o un livello di successo.

---
### Operazione 8 - Chiusura di una missione in corso
**API URL**: _PATCH_ [BASE]/requests/{id}/close<br>
**INPUT**: 

    {
        "status":"chiusa",
        "successLevel":{livello_di_successo},
        "endTime":{timestamp_chiusura_missione},
        "endingAdmin": {mission_ending_admin}
    }

**OUTPUT**: 204 - NO CONTENT

---
### Operazione 9 - Annullamento di una richiesta du soccorso (da parte dell'amministratore)
**API URL**: _DELETE_ [BASE]/requests/{id}<br>
**OUTPUT**: 204 - NO CONTENT

---
### Operazione 10 - Dettagli di una missione
**API URL**: _GET_ [BASE]/requests/{id}/missionDetails<br>
**OUTPUT**: 200

    {
        "status":{stato_richiesta},
        "mission": {
            "teamLeader":{operatore_caposquadra},
            "team":[{operatori_team}],
            "objective":{obiettivo_missione},
            "startTime":{timestamp_inizio_missione},
            "endTime":{timestamp_fine_missione},
            "successLevel":{livello_successo_missione},
            "endingAdmin":{mission_ending_admin}
        }
    }

Nota: in questo output, endTime, successLevel e endingAdmin **potrebbero essere nulli**, in quanto la missione potrebbe non essere conclusa

---
### Operazione 11 - Dettagli di una richiesta di soccorso
**API URL**: _GET_ [BASE]/requests/{id}<br>
**OUTPUT**: 200

    {
        "id":{id_richiesta},
        "descpription":{descrizione_richiesta},
        "position": {
            "address":{indirizzo_richiesta},
            "coordinates":{coordinate_richiesta}
        },
        "reporter": {
            "name":{nome_segnalante},
            "email":{email_segnalante}
        },
        "status":{stato_richiesta}
    }

---
### Operazione 12 - Dettagli di un operatore
**API URL**: _GET_ [BASE]/operators/{id}<br>
**OUTPUT**: 200

    {
        "id":{id_operatore},
        "name":{nome_operatore},
        "email":{email_operatore},
        "username":{username_operatore},
        "licences":[{patenti-operatore}],
        "skills":[{abilità-operatore}],
        "status":{stato_operatore}
    }

---
### Operazione 13 - Lista delle missioni in cui un operatore è stato coinvolto
**API URL**: _GET_ [BASE]/operators/{id}/missions<br>
**OUTPUT**: 200

    [
        {
            "id":{id_richiesta},
            "position": {
                "address":{indirizzo_richiesta},
                "coordinates":{coordinate_richiesta}
            },
            "reporter": {
                "name":{nome_segnalante},
                "email":{email_segnalante}
            },
            "status":{stato_richiesta},
            "mission": {
                "teamLeader":{operatoreCaposquadra},
                "team":[{operatori_team}],
                "objective":{obiettivo_missione},
                "startTime":{timestamp_inizio_missione},
                "endTime":{timestamp_fine_missione},
                "successLevel":{livello_successo_missione},
                "endingAdmin":{mission_ending_admin}
            }
        },
        ...
    ]

Nota: in questo output, endTime, successLevel e endingAdmin **potrebbero essere nulli**, in quanto l'operazione mostra in output anche le missioni non concluse

---
### Operazione 14 (Operazione aggiuntiva) - Aggiunta di un operatore
**API URL**: _POST_ [BASE]/operators<br>
**INPUT**:

     {
        "id":{id_operatore},
        "name":{nome_operatore},
        "email":{email_operatore},
        "username":{username_operatore},
        "licences":[{patenti-operatore}],
        "skills":[{abilità-operatore}],
        "status":{stato_operatore}
    }

**OUTPUT**: 201 - CREATED ([BASE]/operators/{idOperatoreAggiunto})
