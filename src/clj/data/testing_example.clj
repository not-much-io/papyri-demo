(ns data.testing-example
  (:require [yesql.core :refer [defqueries]]))

(def spec "postgresql://localhost:5432/shouter")

; create-scroll-table insert-scroll
(defqueries "queries/testing-example.sql")



