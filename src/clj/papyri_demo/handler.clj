(ns papyri-demo.handler
  (:require [compojure.core :refer [GET POST defroutes]]
            [compojure.route :refer [not-found resources]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [include-js include-css]]
            [prone.middleware :refer [wrap-exceptions]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [environ.core :refer [env]]
            [ring.util.response :refer [response status]]
            [data-storage.queries :as queries]))

(def home-page
  (str "<!DOCTYPE html>"
  (html 
   [:html
    [:head
     [:meta {:charset "utf-8"}]
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1.0"}]
     (include-css "https://fonts.googleapis.com/css?family=Roboto:300,400,500,700"
                  "https://storage.googleapis.com/code.getmdl.io/1.0.4/material.brown-orange.min.css"
                  "https://fonts.googleapis.com/icon?family=Material+Icons"
                  (if (env :dev) "css/site.css" "css/site.min.css"))]
    [:body
     [:div#app
      [:div.mdl-progress.mdl-js-progress.mdl-progress__indeterminate.center-floating-content]]
     (include-js "js/app.js")]])))

(defn add-scroll-handler [{:keys [body]}]
  (println "Server recieved body: " body)
  (queries/add-scroll! body)
  (response {:status :success}))

(defroutes routes
  (GET "/" [] home-page)
  (POST "/add-scroll" request (add-scroll-handler request))
  (resources "/")
  (not-found "Not Found"))

(def app
  (let [site-conf (assoc-in site-defaults [:security :anti-forgery] false)
        handler (wrap-defaults #'routes site-conf)]
    (if (env :dev)
      (-> handler
          wrap-exceptions
          wrap-reload
          (wrap-json-body {:keywords? true})
          wrap-json-response
          wrap-keyword-params)
      handler)))
