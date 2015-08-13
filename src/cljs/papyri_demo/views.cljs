(ns papyri-demo.views
 (:require [papyri-demo.components :as compon]))

(defn page [content]
  [:div.layout-transparent.mdl-layout.mdl-js-layout.mdl-layout--fixed-header.has-drawer
   (compon/header)
   (compon/side-nav)
   [:main.mdl-layout__content
    content]])

(defn home-page []
  (let [content [:div
                 (compon/table-container (compon/table "Latin"))
                 (compon/table-container (compon/table "Ea"))]]
    (page content)))

(defn about-page []
  [:div [:h2 "About papyri-demo"]
   [:div [:a {:href "#/"} "go to the home page"]]])