(ns resources-cleanup.core
  (:require [ring.adapter.jetty :as ring-jetty]
            [reitit.ring :as ring]
            [muuntaja.core :as m]
            [reitit.ring.middleware.muuntaja :as muuntaja])
  (:gen-class))

(defn string-handler [_]
  {:status 200
   :body "On the project"})

(def app
  (ring/ring-handler
   (ring/router
    ["/"
     ["" string-handler]
     ["math" {:post string-handler}]]
    {:data {:muuntaja m/instance
            :middleware [muuntaja/format-middleware]}})))



(defn start [] (ring-jetty/run-jetty app {:port 3000
                                          :join? false}))

(defn -main
  [& args]
  (println "starting server ....")
  (start))

