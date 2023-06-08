(ns resources-cleanup.core
  (:require [babashka.fs :as fs]
            [reitit.ring :as ring]
            [ring.adapter.jetty :as ring-jetty])
  (:gen-class))

(defn check-and-move-resource! [src dest]
  (if-not (fs/directory? src)
    (if (fs/exists? src)
      (fs/copy src dest)
      (println "no files were found!!"))))

(defn manage-resource [path]
  (let [user-dir-path (System/getProperty "user.dir")
        src-path (str user-dir-path path)
        dest-path (str user-dir-path "/data/target")]
    (println src-path)
    (check-and-move-resource! src-path dest-path) ))

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

(defn start [] (ring-jetty/run-jetty #'app {:port 3001
                                          :join? false}))

(defn -main
  [& args]
  (start))

(comment 
  (System/getProperty "user.dir")
  (if (fs/exists? "/home/nas/proiecte/resources-cleanup/data/source/some-doc.doc")
    (fs/copy "/home/nas/proiecte/resources-cleanup/data/source/some-doc.doc" "/home/nas/proiecte/resources-cleanup/data/target/"))
  (fs/exists? "/home/nas/proiecte/resources-cleanup/data/source")
  (fs/file "/home/nas/proiecte/resources-cleanup/data/source")
  
  (-main)

  0)