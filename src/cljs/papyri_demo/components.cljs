(ns papyri-demo.components)

(def test-data {:thought "Lorem ipsum dolor sit amet"
                :category "Latin"
                :date "Aug. 13"})

(defn side-nav []
  [:div.mdl-layout__drawer
   [:span.mdl-layout-title "Papyri"]
   [:nav.mdl-navigation
    [:a.mdl-navigation__link "About"]
    [:a.mdl-navigation__link "Settings"]
    [:a.mdl-navigation__link "Latin"]
    [:a.mdl-navigation__link "Ea"]]])

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
  [:header.mdl-layout__header.mdl-layout__header--transparent
   [:div.mdl-layout__header-row
    [:span.mdl-layout-title "Papyri"]
    [:div.mdl-layout-spacer]
    (search-bar)]])

(defn table-row [data]
  [:tr
   [:td.mdl-data-table__cell--non-numeric
    (:thought data)]])

(defn table [name]
  [:table.mdl-data-table.mdl-js-data-table.mdl-shadow--2dp.papyri-table
   [:caption.table-caption [:h6 name]]
   [:tbody
    (repeat 10 (table-row test-data))]])

(defn table-container [table]
  [:div.table-container.mdl-grid
   [:div.mdl-cell.mdl-cell--12-col
    [:div.paper-backdrop.mdl-card.mdl-shadow--2dp
     table]]])