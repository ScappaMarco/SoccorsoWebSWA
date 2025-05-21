# Funzionalità Realizzate
Segue la lista delle operazioni implementate.
URL di base: http;//swa.net/soccorsoweb/rest e ci riferimo ad essa tramite [BASE]
## Operazioni
### Operazione 1.1 - Login
**API URL**: _POST_ [BASE]/auth/login
**Output**: 204 - NO CONTENT

### Operazione 1.2 - Logout
**API URL**: _DELETE_ [BASE]/auth/logout
**Output**: 204 - NO CONTENT

### Operazione 2 - Inserimento di una richiesta di soccorso
**API URL**: _POST_ [BASE]/request
**Input**: {request}
**Output**: 201 - CREATED ([BASE]/requests/{id})

### Operazione 3 - Convalida di una richiesta di soccorso
**API URL**: 

### Operazione 4 - Lista (paginata) delle richieste di soccorso, filtrata in base alla tipologia (attive, in corso, chiuse, ignorate)
**API URL**: _GET_ [BASE]/requests/{status}?page={numero_pagina}&size={size_pagina} opzione 2
**OUTPUT**: 200

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
            }
        ],
        ...
    }

### Operazione 5 - Lista delle richieste di soccorso chiuse con risultato non totalmente positivo (livello di successo minore di 5)
**API URL**: _GET_ [BASE]/requests/mission/closed?max_success_level=4
**OUTPUT**: 200

    [
        {
            "id":{id_missione}
            "teamLeader":{team_leader_missione},
            "team":[{lista_operatori_team}],
            "obkective":{obiettivo_missione},
            "successLevel":{livello_successo_missione},
            "startTime":{data_e_ora_inizio_missione},
            "endTime":{data_e_ora_fine_missione}
        }
    ]
