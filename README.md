# Înainte de instalare

Docker va trebui sa fie instalat local - aplicația va rula dintr-un container docker (prin docker compose)

În fișierul config.edn se găsește configurația aplicației și a web serverului jetty: 
 [ring-jetty9-adapter](https://github.com/sunng87/ring-jetty9-adapter);
  astfel, se poate modifica locația sursa si target



# Ghid de instalare

Pentru a porni aplicatia, se va rula comanda:
`docker compose up`

Vor porni 2 containere:
* #app - serviciul care se ocupa de mutat/copiat fisierele; Acesta este scris in [Clojure](https://clojuredocs.org/)
* #nginx - serviciu care este configurat sa trimita o copie a cererii catre #app;


```shell

# start REPL (or use Calva Jack-in)
clojure -Sdeps '{:deps {nrepl/nrepl {:mvn/version,"1.0.0"},cider/cider-nrepl {:mvn/version,"0.28.5"}}}' -M -m nrepl.cmdline --middleware "[cider.nrepl/cider-middleware]"

# start app outside REPL
clojure -M:start

# build image and start docker containers
docker compose up
```