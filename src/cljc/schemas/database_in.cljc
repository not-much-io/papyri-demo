(ns schemas.database-in
  (:require [schema.core :as s
             :include-macros true]))

(def new-scroll
  "Schema for a new scroll"
  {:title s/Str
   :content s/Str})