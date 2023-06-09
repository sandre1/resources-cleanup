(ns resources-cleanup.core
  (:require [babashka.fs :as fs]
            [reitit.ring :as ring]
            [ring.adapter.jetty9 :refer [run-jetty]]
            [resources-cleanup.config :as cfg]
            [clojure.tools.logging :as log])
  (:gen-class))

(defn resource-handler
  "Checks and moves resource to a specified location"
  [req]
  (let [uri (:uri req)
        app-cfg (:app @cfg/config)
        source-dir (:source-dir app-cfg)
        target-dir (:target-dir app-cfg)
        target-path (str target-dir uri)
        source-path (str source-dir uri)]
    (log/info "Serve uri" uri ":" source-path "->" target-path)
    (if (fs/directory? source-path)
      {:status 200
       :headers {"content-type" "text/plain; charset=utf-8"}
       :body "Hello from directory"}
      (try (fs/move source-path target-path)
          {:status 200
           :headers {"content-type" "text/plain; charset=utf-8"}
           :body (str "Moved a resource to " target-path)}
           (catch java.nio.file.NoSuchFileException _nsfe
             {:status 404
              :headers {"content-type" "text/plain; charset=utf-8"}
              :body (str "Not found " source-path)})))))

(def app
  (ring/ring-handler
   (ring/router
    [["/*" resource-handler]])))

(defn start []
  (let [cfg @cfg/config
        jetty-config (:jetty cfg)]
    (log/info "configul dat este:" jetty-config)
    (run-jetty app jetty-config)))

(defn -main
  [& args]
  (cfg/load-config)
  (start))

(comment
  (fs/exists? "/home/nas/proiecte/resources-cleanup/data/source")
  (fs/file "/home/nas/proiecte/resources-cleanup/data/source")
  (fs/regular-file? "/home/nas/proiecte/resources-cleanup/data/source/some-doc.do")
  (-main)
  @cfg/config
  (:app @cfg/config)
  (start)
  (let [cfg @cfg/config
        jetty (:jetty cfg)]
    jetty)
  0
  )