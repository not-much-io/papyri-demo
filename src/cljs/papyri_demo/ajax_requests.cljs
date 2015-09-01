(ns papyri-demo.ajax.ajax-requests
  (:require [ajax.core :refer [POST]]))

(defn handler [response]
  (.log js/console (str "Response: " response)))

(defn error-handler [response]
  (.error js/console (str "Error: " response)))

(defn add-scroll [data]
  (POST "/add-scroll"
        {:params {:content (:content data)
                  :title (:title data)}
         :handler handler
         :error-handler error-handler
         :format :json
         :response-format :json
         :keywords? true}))
