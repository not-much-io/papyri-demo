(ns papyri-demo.components.scrolls)

(def test-data {:thought "Lorem ipsum dolor sit amet"
                :category "Latin"
                :date "Aug. 13"})

(defn table-row [data]
  [:tr
   [:td.mdl-data-table__cell--non-numeric
    (:thought data)]])

(defn table [name]
  [:table.mdl-data-table.mdl-js-data-table.mdl-shadow--2dp.papyri-table
   [:caption.table-caption [:h6 name]]
   [:tbody
    (repeat 6 (table-row test-data))]])

(defn table-container [table]
  [:div.table-container.mdl-grid
   [:div.mdl-cell.mdl-cell--12-col
    [:div.paper-backdrop.mdl-card.mdl-shadow--2dp
     table]]])