(ns papyri-demo.prod
  (:require [papyri-demo.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
