(ns ^:figwheel-always clojurefxtitle.pricecomponent
    (:require
              [reagent.core :as reagent :refer [atom]]
              [goog.string :as gstring]
              [goog.string.format]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

(defn my-click-handler [side price]
    (println "order " side price )
  )
  
(defn rand-price [val]
  (rand-nth val))

(defn price-component [label]
  (let [seconds-elapsed (atom 0)
       whole_number (range 95000 100000)]
    (fn []
      (js/setTimeout #(reset! seconds-elapsed (rand-price whole_number)) 1000)
;;      (js/setTimeout #(swap! seconds-elapsed inc) 1000)
      (let [price (/ @seconds-elapsed 1000)]
        [:button {:style {:background "red" :width "150" :float "left" :text-align "center"}
           :on-click #(my-click-handler label price)}
         label (gstring/format " %0.3f" price )])))
       )