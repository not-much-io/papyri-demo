(ns data-storage.queries
  (:require [schemas.database-in :as db-in]
            [schema.core :as s]))

(defn add-scroll! [data]
  (s/validate db-in/new-scroll data)
  (println "Executing query!"))

