(ns resources-cleanup.core
  (:require [babashka.fs :as fs]
            [reitit.ring :as ring]
            [ring.adapter.jetty :as ring-jetty]
            [resources-cleanup.config :as cfg])
  (:gen-class))

(defn check-and-move-resource! [src dest]
  (when-not (fs/directory? src)
    (if (fs/exists? src)
      (fs/copy src dest)
      (println "no files were found!!"))))

(defn manage-resource [path]
  (let [cfg (deref cfg/config)
        app-cfg (:app cfg)
        host-source-path (:source-dir app-cfg)
        resource-path (str host-source-path path)
        resource-dest-path (:target-dir app-cfg)
        dest-path (str host-source-path resource-dest-path)]
    (check-and-move-resource! resource-path dest-path)))

(defn resource-handler [req]
  (let [path (:uri req)]
    (println "calea este: " path)
    (manage-resource path)
    {:status 200
     :body path}))

(def app
  (ring/ring-handler
   (ring/router
    [["/*" resource-handler]])))

(defn start []
  (let [cfg @cfg/config
        jett-opts (:ring-jetty cfg)]
    (ring-jetty/run-jetty #'app {:port (or (:port jett-opts) 3000)
                                 :join? false})))

(defn -main
  [& args]
  (cfg/load-config)
  (start))

(comment
  (fs/exists? "/home/nas/proiecte/resources-cleanup/data/source")
  (fs/file "/home/nas/proiecte/resources-cleanup/data/source")
  (-main)
  (:app @cfg/config)
  0)