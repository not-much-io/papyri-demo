(ns papyri-demo.views
 (:require [papyri-demo.components.about-cards :as about]
           [papyri-demo.components.navigation-bar :as nav]
           [papyri-demo.components.papyri :as papyri]))

(defn page [content]
  (nav/header-layout content))

(defn home-page []
  (let [content [:div
                 (papyri/table-container (papyri/table "Latin"))
                 (papyri/table-container (papyri/table "Ea"))]]
    (page content)))

(defn about-page []
  (let [content (about/about-card)]
    (page content)))