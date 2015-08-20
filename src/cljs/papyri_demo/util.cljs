(ns papyri-demo.util)

(defn start-upgrading []
  (js/setInterval #(.upgradeDom js/componentHandler) 100))
