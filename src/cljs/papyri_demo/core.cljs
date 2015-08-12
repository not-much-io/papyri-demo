(ns papyri-demo.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [secretary.core :as secretary :include-macros true]
              [goog.events :as events]
              [goog.history.EventType :as EventType])
    (:import goog.History))

;; -------------------------
;; Test data

(def test-content "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.")

;; -------------------------
;; Components

(defn card [title content]
  [:div {:class "square-card mdl-card mdl-shadow--2dp"}
   [:div {:class "mdl-card__title mdl-card--expand"}
    [:h2 {:class "mdl-card__title-text"}
     title]]
   [:div {:class "mdl-card__supporting-text"}
    content]])

(defn card-registry []
  (let [grid [:div {:class "card-registry mdl-grid"}]
        cards (repeat 20 [:div {:class "card-cell mdl-cell mdl-cell--4-col
        mdl-cell--6-col-tablet mdl-cell--12-col-phone"}
                          (card "Enuma Elish" test-content)])]
    (conj grid cards)))

(defn search-bar []
  [:form
   {:action "#"}
   [:div.mdl-textfield.mdl-js-textfield.mdl-textfield--expandable
    [:label.mdl-button.mdl-js-button.mdl-button--icon
     {:for "search"}
     [:i.material-icons
      "search"]]
    [:div.mdl-textfield__expandable-holder
     [:input#search.mdl-textfield__input
      {:type "text"}]
     [:label.mdl-textfield__label
      {:for "sample-expandable"}
      "Expandable Input"]]]])

;; -------------------------
;; Views

(defn home-page []
  [:div {:class "layout-transparent mdl-layout mdl-js-layout mdl-layout--fixed-header has-drawer"}
   [:header {:class "mdl-layout__header mdl-layout__header--transparent"}
    [:div {:class "mdl-layout__header-row"}
     [:span {:class "mdl-layout-title"} "Papyri"]
     [:div {:class "mdl-layout-spacer"}]
     (search-bar)]]
   [:div {:class "mdl-layout__drawer"}
    [:span {:class "mdl-layout-title"} "Papyri"]
    [:nav {:class "mdl-navigation"}
     [:a {:class "mdl-navigation__link"} "About"]
     [:a {:class "mdl-navigation__link"} "Settings"]]]
   [:main {:class "mdl-layout__content"}
    [:div
     ;questionable formatting..
     [:br]
     (card-registry)]]])

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
