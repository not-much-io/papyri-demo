(ns papyri-demo.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [secretary.core :as secretary :include-macros true]
              [goog.events :as events]
              [goog.history.EventType :as EventType])
    (:import goog.History))

;; -------------------------
;; Components

(defn card [title content]
  [:div {:class "mdl-card mdl-shadow--2dp"}
   [:div {:class "mdl-card__title mdl-card--expand"}
    [:h2 {:class "mdl-card__title-text"}
     title]]
   [:div {:class "mdl-card__supporting-text"}
    content]])

;; -------------------------
;; Views

(defn home-page []
  [:div {:class "layout-transparent mdl-layout mdl-js-layout"}
   [:header {:class "mdl-layout__header mdl-layout__header--transparent"}
    [:div {:class "mdl-layout__header-row"}
     [:span {:class "mdl-layout-title"} "Title"]
     [:div {:class "mdl-layout-spacer"}]
     [:nav {:class "mdl-navigation"}
      [:a {:class "mdl-navigation__link"} "Link"]
      [:a {:class "mdl-navigation__link"} "Link"]
      [:a {:class "mdl-navigation__link"} "Link"]
      [:a {:class "mdl-navigation__link"} "Link"]]]]
   [:div {:class "mdl-layout__drawer"}
    [:span {:class "mdl-layout-title"} "Title"]
    [:nav {:class "mdl-navigation"}
     [:a {:class "mdl-navigation__link"} "Link"]
     [:a {:class "mdl-navigation__link"} "Link"]
     [:a {:class "mdl-navigation__link"} "Link"]
     [:a {:class "mdl-navigation__link"} "Link"]]]
   [:main {:class "mdl-layout__content"}]])

(defn about-page []
  [:div [:h2 "About papyri-demo"]
   [:div [:a {:href "#/"} "go to the home page"]]])

(defn current-page []
  [:div [(session/get :current-page)]])

;; -------------------------
;; Routes
(secretary/set-config! :prefix "#")

(secretary/defroute "/" []
  (session/put! :current-page #'home-page))

(secretary/defroute "/about" []
  (session/put! :current-page #'about-page))

;; -------------------------
;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

;; -------------------------
;; Initialize app
(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (hook-browser-navigation!)
  (mount-root))
