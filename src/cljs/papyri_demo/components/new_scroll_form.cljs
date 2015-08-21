(ns papyri-demo.components.new-scroll-form)

(defn text-input-single-line [action id]
  [:form {:action action}
   [:div.mdl-textfield.mdl-js-textfield
    [:input.mdl-textfield__input {:type "text"
                                  :id id}]
    [:label.mdl-textfield__label {:for id} id]]])

(defn text-input-multy-line [action id]
  [:form {:action action}
   [:div.mdl-textfield.mdl-js-textfield
    [:textarea.mdl-textfield__input {:type "text"
                                     :rows 10
                                     :id id}]
    [:label.mdl-textfield__label {:for id} id]]])

(defn new-scroll-form []
  [:div.mdl-card.mdl-shadow--2dp.center-floating-content
   [:div.mdl-card__title.mdl-card--expand
    [:h2.mdl-card__title-text "New Scroll"]]
   [:div.mdl-card__supporting-text
    (text-input-single-line "#" "Title..")
    (text-input-multy-line "#" "Content..")]
   [:div.mdl-card__actions.mdl-card--border
    [:a.mdl-button.mdl-button--colored.mdl-js-button.mdl-js-ripple-effect {:href "#/"}
     "Submit"]]])