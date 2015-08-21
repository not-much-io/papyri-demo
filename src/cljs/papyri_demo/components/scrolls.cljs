(ns papyri-demo.components.scrolls)

(def test-data {:content "Lorem ipsum dolor sit amet "
                :category "Latin"
                :date "Aug. 13"})

(defn scroll [data]
  [:tr.scroll-row
   [:td.scroll-content.mdl-data-table__cell--non-numeric {:style {:paddingBottom "10px"}} ;;hack
    (apply str (repeat (inc (rand-int 10)) (:content data)))]
   [:td.icon-td
    [:a [:i.scroll-icon.material-icons "edit"]]]])

(defn shelf []
  [:table.mdl-data-table.mdl-js-data-table.mdl-shadow--2dp
   [:tbody
    (for [i (range 20)]
      (scroll test-data))]])