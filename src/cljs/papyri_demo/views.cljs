(ns papyri-demo.views
 (:require [papyri-demo.components.about-cards :as about]
           [papyri-demo.components.navigation-bar :as nav]
           [papyri-demo.components.scrolls :as scrolls]
           [papyri-demo.components.fabs :as fabs]))

(defn page [content]
  (nav/header-layout content))

(defn home-page []
  (let [content [:div
                 (scrolls/shelf)
                 (fabs/add-scroll-fab)]]
    (page content)))

(defn about-page []
  (let [content (about/about-card)]
    (page content)))
