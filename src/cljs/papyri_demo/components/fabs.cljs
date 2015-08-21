(ns papyri-demo.components.fabs)

(defn add-scroll-fab []
  [:form {:action "#/new-scroll"}
   [:button.fixed-bottom-fab.mdl-button.mdl-js-button.mdl-button--fab.mdl-js-ripple-effect.mdl-button--colored
    [:i.material-icons "add"]]])