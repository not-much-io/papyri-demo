(ns data-storage.migration
  (:require [data-storage.queries :refer [spec]]
            [yesql.core :refer [defquery]]))

(defquery is-migrated "queries/is-migrated.sql")
(defquery create-scroll-table "queries/create-scroll-table.sql")

(defn migrated? []
  (-> (is-migrated spec)
      first
      count
      pos?))

(defn migrate []
  (when (not (migrated?))
    (print "Creating table...")
    (flush)
    (create-scroll-table spec)
    (println "Done")))

