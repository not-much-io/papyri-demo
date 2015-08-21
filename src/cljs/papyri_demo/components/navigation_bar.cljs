(ns papyri-demo.components.navigation-bar
 (:require [papyri-demo.components.settings :as settings]))

(defn side-nav []
  [:div.mdl-layout__drawer
   [:span.mdl-layout-title "Papyri"]
   [:nav.mdl-navigation
    [:a.mdl-navigation__link {:href "#/"} "Main page"]
    [:a.mdl-navigation__link {:href "#/about"} "About"]
    [:a.mdl-navigation__link {:href "#/settings"} "Settings"]]])

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

(defn header []
 (let [header-keyword (keyword (str "header.mdl-layout__header"
                                    (if @settings/transparent-theme
                                     ".mdl-layout__header--transparent"
                                     "")))]
  [header-keyword
   [:div.mdl-layout__header-row
    [:span.mdl-layout-title "Papyri"]
    [:div.mdl-layout-spacer]
    (search-bar)]]))

(defn header-layout [content]
 (let [div-keyword (keyword (str "div.mdl-layout.mdl-js-layout.mdl-layout--fixed-header.has-drawer"
                                 (if @settings/transparent-theme
                                  ".layout-transparent"
                                  "")))]
  [div-keyword
   (header)
   (side-nav)
   [:main.mdl-layout__content content]]))