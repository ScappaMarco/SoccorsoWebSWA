# SoccorsoWeb - Sviluppo Web Avanzato

## Il Servizio

A partire dalle strutture dati del progetto SoccorsoWeb (che non dovete necessariamente implementare: siete liberi di usare il codice e il database realizzato da qualche vostro collega, oppure di abbozzare della logica con i soli elementi necessari a supportare le operazioni specificate), si costruisca il servizio RESTful specificato di seguito.

    1. Login/logout con username e password.
    2. Inserimento di una richiesta di soccorso.
    3. Convalida di una richiesta di soccorso (questo endpoint deve ricevere le richieste provenienti dai click sui link di convalida descritti nella specifica).
    4. Lista (paginata) delle richieste di soccorso, filtrata in base alla tipologia (attive, in corso, chiuse, ignorate).
    5. Lista delle richieste di soccorso chiuse con risultato non totalmente positivo (livello di successo minore di 5).
    6. Lista degli operatori attualmente liberi.
    7. Creazione di una missione.
    8. Chiusura di una missione in corso.
    9. Annullamento di una richiesta di soccorso (da parte dell'amministratore).
    10. Dettagli di una missione.
    11. Dettagli una richiesta di soccorso.
    12. Dettagli di un operatore.
    13. Lista delle missioni in cui un operatore è stato coinvolto.

Prestate attenzione alla struttura delle URL, che deve seguire per quanto possibile il paradigma collection-item, evidenziando le relazioni tra gli oggetti tramite nidificazione.

Se alcune operazioni necessitano di accesso con autenticazione, potrete usare uno qualsiasi degli approcci visti a lezione per trasmettere il token di sessione/autenticazione, precedentemente ottenuto tramite la procedura (API) di login: ad esempio passare tale token con l'header Authentication.

Questa è una specifica di base. Potete aggiungere altre funzionalità a vostra scelta che sfruttino le altre API del servizio, se volete, aumentando così il valore del progetto.

## Il Client

Si implementi un semplice client di test (preferibilmente Javascript), che interagisce con le API principali del servizio appena descritto.

In particolare, il client dovrà permettere di eseguire opportunamente le operazioni 1, 2, 4 e 6.

Nota bene: il test client general-purpose inserito all'interno degli esempi del corso non è valido in quest'ambito, in quanto non richiede alcuna programmazione per poter essere adattato ad altri servizi!

## Documentazione
La documentazione OpenAPI è disponibile [qui](https://github.com/ScappaMarco/SoccorsoWebSWA/blob/main/SoccorsoWebREST/documentation/opeanAPIspecification.yaml).