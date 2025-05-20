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
**Output**: 201 - CREATED ([BASE]/request/{id})