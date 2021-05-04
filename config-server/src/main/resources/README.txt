    search-paths:
          - '*service' 
    # questo sopra indica un array di paths che si trovano su git, in parole povere ho creato 
    # delle sottocartelle per ordinare i files .yml di configurazione su git quindi visto che 
    # hanno tutti la parola service al loro interno (es. mem-service..) con questa wildecard
    # li prendo tutti e li posso raggiungere con le chimate che ci sono sotto

localhost:8191/membership/default

localhost:8191/membership/dev
localhost:8191/membership/prod

localhost:8191/reporting-dev.yml  <--- direct call of config properties. It'll return the exact 
										content of the specific .yml
										
localhost:8191/reporting-dev.properties   \    |Cambiando estensione anche se su git sono salvati 
										   \___|come .yml ci tornerà come risposta l'estensione 
										   /   |richiesta!Fortissimo! ;) 
localhost:8191/reporting-dev.json         /    |

localhost:8191/membership/prod/hash ---> puoi aggiungere anche l'hash di un commit passato volendo
											questo caricherà le configurazioni di quella versione