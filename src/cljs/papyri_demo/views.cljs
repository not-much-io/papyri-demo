(ns papyri-demo.views
 (:require [papyri-demo.components.about-cards :as about]
           [papyri-demo.components.navigation-bar :as nav]
           [papyri-demo.components.scrolls :as scrolls]
           [papyri-demo.components.fabs :as fabs]
           [papyri-demo.components.settings :as settings]))

(defn page [content]
  (nav/header-layout
    [:div.content-wrap content]))

(defn home-page []
  (let [content [:div
                 (scrolls/shelf)
                 (fabs/add-scroll-fab)]]
    (page content)))

(defn about-page []
  (let [content (about/about-card)]
    (page content)))

(defn settings-page []
  (let [content [:div
                 (settings/setting-table)]]
    (page content)))
