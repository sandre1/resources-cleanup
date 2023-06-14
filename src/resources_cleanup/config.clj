(ns resources-cleanup.config
  (:require [aero.core :refer [read-config]]
            [clojure.tools.logging :as log]))

(def config (atom nil))

(defn load-config
  ([] (load-config (or (System/getenv "X_CONFIG_PATH") "config.edn")))
  ([src]
   (log/info "Loading config" src)
   (reset! config (read-config src))))


(comment
  (load-config)
  @config
  (or (System/getenv "X_CONFIG_PATH") "config.edn")
  (swap! config (read-config "config.edn"))
  (reset! config {:app {:source-dir "/home/nas/proiecte/resources-cleanup", :target-dir "/data/target"}})
  0)