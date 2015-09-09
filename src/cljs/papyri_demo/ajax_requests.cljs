(ns papyri-demo.ajax-requests
  (:require [ajax.core :refer [POST]]
            [schema.core :as s :include-macros true]
            [schemas.database-in :as db-in]))

(defn handler [response]
  (.log js/console (str "Response: " response)))

(defn error-handler [response]
  (.error js/console (str "Error: " response)))

(defn add-scroll [data]
  (s/validate db-in/new-scroll data)
  (POST "/add-scroll"
        {:params data
         :handler handler
         :error-handler error-handler
         :format :json
         :response-format :json
         :keywords? true}))
