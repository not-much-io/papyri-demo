(ns papyri-demo.components.settings
 (:require [reagent.core :refer [atom]]))

(defonce transparent-theme (atom true))

(defn setting-switch [setting]
 (let [id (str setting "-switch")]
  [:label.mdl-switch.mdl-js-switch.mdl-js-ripple-effect
   {:for id}
   [:input.mdl-switch__input
    (let [args {:type    "checkbox"
                :id      id
                :on-click #(reset! transparent-theme (not @transparent-theme))}]
     (if @transparent-theme
      (assoc args :checked "true")
      args))]
   [:span.mdl-switch__label]]))

(defn setting-table []
 [:table.mdl-data-table.mdl-js-data-table.mdl-shadow-2dp.center-floating-content
  [:thead
   [:tr
    [:th.mdl-data-table__cell--non-numeric "Setting"]
    [:th.mdl-data-table__cell--non-numeric "Value"]]]
  [:tbody
   [:tr
    [:td "Transparent Theme"]
    [:td (setting-switch "transparency")]]]])