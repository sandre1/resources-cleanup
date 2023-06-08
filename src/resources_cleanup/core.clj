(ns resources-cleanup.core
  (:require [ring.adapter.jetty :as ring-jetty]
            [reitit.ring :as ring]
            [muuntaja.core :as m]
            [reitit.ring.middleware.muuntaja :as muuntaja])
  (:gen-class))


(defn string-handler [request]
  (println (str request))
  {:status 200
   :body (str request)})

(defn ping-handler [_]
  {:status 200
   :body "PING PING PING"})

(defn pong-handler [_]
  {:status 200
   :body "PONG PONG PONG"})

(def app
  (ring/ring-handler
   (ring/router
    ["/"
     ["" string-handler]
     ["ping" {:get ping-handler}]
     ["pong" {:get pong-handler}]]
    {:data {:muuntaja m/instance
            :middleware [muuntaja/format-middleware]}})
   (ring/routes
    (ring/create-resource-handler {:path "/"})
    (ring/create-default-handler))))

(defn start [] (ring-jetty/run-jetty #'app {:port 3001
                                          :join? false}))

(defn -main
  [& args]
  (start))

(comment 
  (+ 1 1)
  (-main)
  0)