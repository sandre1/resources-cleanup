(ns resources-cleanup.core
  (:require [ring.adapter.jetty :as ring-jetty]
            [reitit.ring :as ring]
            [muuntaja.core :as m]
            [reitit.ring.middleware.muuntaja :as muuntaja])
  (:gen-class))

(defn string-handler [_]
  {:status 200
   :body "On the project"})

(defn ping-handler [_]
  {:status 200
   :body "you requested for PING"})

(defn pong-handler [_]
  {:status 200
   :body "you requested for PONG"})

(def app
  (ring/ring-handler
   (ring/router
    ["/"
     ["" string-handler]
     ["ping" {:get ping-handler}
      "pong" {:get pong-handler}]]
    {:data {:muuntaja m/instance
            :middleware [muuntaja/format-middleware]}})))



(defn start [] (ring-jetty/run-jetty app {:port 3000
                                          :join? false}))

(defn -main
  [& args]
  (println "calculating ...and calculating...")
  (println (+ 1 1)))

(defn print-args [args]
  (println "passed arguments: " args))

