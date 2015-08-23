(ns papyri-demo.ajax.testing-example
  (:require [ajax.core :refer [POST]]))

(defn handler [response]
  (.log js/console (str "handler says:" response)))

(defn error-handler [{:keys [status status-text]}]
  (.log js/console (str "error handler says: " status " " status-text)))

(defn send-msg []
  (.log js/console "POSTing!")
  (POST "/adding-scroll"
        {:params {:message "Test Message"
                  :user    "Bob"}
         :handler handler
         :error-handler error-handler}))