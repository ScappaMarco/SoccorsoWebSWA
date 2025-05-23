# Funzionalità Realizzate
Segue la lista delle operazioni implementate.
URL di base: http;//swa.net/soccorsoweb/rest e ci riferimo ad essa tramite [BASE]

---
# Operazioni
### Operazione 1.1 - Login con username e password
**API URL**: _POST_ [BASE]/auth/login
**Output**: 204 - NO CONTENT

---
### Operazione 1.2 - Logout
**API URL**: _DELETE_ [BASE]/auth/logout
**Output**: 204 - NO CONTENT

---
### Operazione 2 - Inserimento di una richiesta di soccorso
**API URL**: _POST_ [BASE]/request
**Input**: {request}
**Output**: 201 - CREATED ([BASE]/requests/{id})

---
### Operazione 3 - Convalida di una richiesta di soccorso
**API URL**: _PUT_ [BASE]/requests/{id}/validate
**OUTPUT**: 204 - NO CONTENT

---
### Operazione 4 - Lista (paginata) delle richieste di soccorso, filtrata in base alla tipologia (attive, in corso, chiuse, ignorate)
**API URL**: _GET_ [BASE]/requests/{status}?page={numero_pagina}&size={size_pagina}
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
**API URL**: _GET_ [BASE]/requests/closed?max_success_level=4
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
            "status":{stato_richiesta},
            "mission": {
                "teamLeader":{operatore_caposquadra},
                "team":[{operatori_team}],
                "objective":{obiettivo_missione},
                "startTime":{timestamp_inizio_missione},
                "endTime":{timestamp_fine_missione},
                "successLevel":{livello_successo_missione}
            }
        },
        ...
    ]

---
### Opetazione 6 - Lista degli operatori attualmente liberi
**API URL**: _GET_ [BASE]/operators/free
**OUTPUT**: 200 - OK

    [
        {
            "id":{id_operatore},
            "firstName":{nome_operatore},
            "lastName":{cognome_operatore},
            "email":{email_operatore},
            "username":{username_operatore},
            "skills":[{abilità_operatore}],
            "licences":[{patenti_operatore}]
        },
        ...
    ]

---
### Operazione 7 - Creazione di una missione
**API URL**: _PATCH_ [BASE]/request/{id}
**INPUT**:

    {
        mission: {
            "teamLeader":{operatore_caposquadra},
            "team": [{operatori_team}],
            "objective":{obiettivo-missione},
            "startTime":{timestamp_inizio_missione},
            "endTime":null,
            "successLevel":null
        }
    }

**OUTPUT**: 204 - NO CONTENT
Nota come **_"endTime"_** e **_"successLevel"_** siano settati a **_"null"_** in quanto, appena aggiunta una missione, questa non ha una data di fine o un livello di successo.

---
### Operazione 8 - Chiusura di una missione in corso
**API URL**: _PATCH_ [BASE]/requests/{id}
**INPUT**: 

    {
        "status":"chiusa",
        "mission": {
            "successLevel":{livello_di_successo},
            "endTime":{timestamp_chiusura_missione}
        }
    }

**OUTPUT**: 204 - NO CONTENT

---
### Operazione 9 - Annullamento di una richiesta du soccorso (da parte dell'amministratore)
**API URL**: _DELETE_ [BASE]/requests/{id}
**OUTPUT**: 204 - NO CONTENT

---
### Operazione 10 - Dettagli di una missione
**API URL**: _GET_ [BASE]/requests/{id}/missionDetails
**OUTPUT**: 200

    {
        "status":{stato_richiesta},
        "mission": {
            "teamLeader":{operatore_caposquadra},
            "team":[{operatori_team}],
            "objective":{obiettivo_missione},
            "startTime":{timestamp_inizio_missione},
            "endTime":{timestamp_fine_missione},
            "successLevel":{livello_successo_missione}
        }
    }

---
### Operazione 11 - Dettagli di una richiesta di soccorso
**API URL**: _GET_ [BASE]/requests/{id}/requestDetails
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
**API URL**: _GET_ [BASE]/operators/{id}/details
**OUTPUT**: 200

    {
        "id":{id_operatore},
        "firstName":{nome_operatore},
        "lastName":{cognome_operatore},
        "email":{email_operatore},
        "username":{username_operatore},
        "licences":[{patenti-operatore}],
        "skills":[{abilità-operatore}],
        "status":{stato_operatore}
    }

---
### Operazione 13 - Lista delle missioni in cui un operatore è stato coinvolto
**API URL**: _GET_ [BASE]/operators/{id}/missions
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
                "successLevel":{livello_successo_missione}
            }
        },
        ...
    ]

---