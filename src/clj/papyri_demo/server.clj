(ns papyri-demo.server
  (:require [papyri-demo.handler :refer [app]]
            [data-storage.migration :refer [migrate]]
            [environ.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

 (defn -main [& args]
   (migrate)
   (let [port (Integer/parseInt (or (env :port) "3000"))]
     (run-jetty app {:port port :join? false})))
