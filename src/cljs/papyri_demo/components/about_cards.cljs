(ns papyri-demo.components.about-cards)

(defn about-card []
  [:div.mdl-card.mdl-shadow--2dp.about-card.center-floating-content
   [:div.mdl-card__title [:h2.mdl-card__title-text "Papyri"]]
   [:div.mdl-card__supporting-text
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.
    Mauris sagittis pellentesque lacus eleifend lacinia..."]
   [:div.mdl-card__actions.mdl-card--border
    [:a.mdl-button.mdl-button--colored.mdl-js-button.mdl-js-ripple-effect {:href "#/"}
     "Back to main page"]]
   [:div.mdl-card__menu
    [:button.mdl-button.mdl-button--icon.mdl-js-button.mdl-js-ripple-effect
     [:i.material-icons "share"]]]])
