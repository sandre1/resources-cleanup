(ns resources-cleanup.config
  (:require [aero.core :refer [read-config]]))

(defn config []
  (read-config "config.edn"))


