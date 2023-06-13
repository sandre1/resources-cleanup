# How to build and run the app
- to run this app you must have docker and docker compose installed on your computer
- to build the image and start the container, run: 
$ docker compose up
- the app will run on localhost:3000

## about the app
 - se va primi requestul
 - daca raspuns 200 -> copiaza resursa 
 ;; va cauta in 2 directoare : [1]source si [2]target
 - resursa va fi copiata unde ?!
 - daca raspuns 404 -> nu face nimic

 ## to do
 - configuratia pentru jetty va trebui sa fie optional specificata in config [done]
 - trebuie specificat content-type [done]
 - de modificat response body ( daca intorc header, standardizat sau nu )[done]
 - de implementat logging (clojure.tools logging + dialog) []
 - de pus dostrings la metode [done]
 - de revizuit numele leturilor din manage-resource [done]
 - instructiuni de build si de rulat app []