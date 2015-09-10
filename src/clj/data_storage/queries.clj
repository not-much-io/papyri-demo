(ns data-storage.queries
  (:require [schemas.database-in :as db-in]
            [schema.core :as s]
            [yesql.core :refer [defquery]]))

(def spec (or (System/getenv "DATABASE URL")
              "postgresql://localhost:5432/papyridb"))

(defquery create-table "queries/create-scroll-table.sql")
(defquery insert-scroll! "queries/insert-scroll.sql")

(defn add-scroll! [scroll_data]
  (s/validate db-in/new-scroll scroll_data)
  (insert-scroll! spec (:title scroll_data) (:content scroll_data)))

