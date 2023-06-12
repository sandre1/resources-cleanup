(ns resources-cleanup.config
  (:require [aero.core :refer [read-config]]))

(def config (atom nil))

(defn load-config
  ([] (load-config (or (System/getenv "X_CONFIG_PATH") "config.edn")))
  ([src] (reset! config (read-config src))))


(comment
  (load-config)
  @config
  (or (System/getenv "X_CONFIG_PATH") "config.edn")
  (swap! config (read-config "config.edn"))
  (reset! config {:app {:source-dir "/home/nas/proiecte/resources-cleanup", :target-dir "/data/target"}})
  0)