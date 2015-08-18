(ns papyri-demo.util)

(defn start-upgrading []
  (js/setInterval (fn []
                    (.upgradeDom js/componentHandler)) 10))