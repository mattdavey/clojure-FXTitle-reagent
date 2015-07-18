(ns ^:figwheel-always clojurefxtitle.timer
    (:require
              [reagent.core :as reagent :refer [atom]]
              [goog.string :as gstring]
              [goog.string.format]))

(enable-console-print!)

(defn rand-price [val]
  (rand-nth val))
                
(defn timer-component [label]
  (let [seconds-elapsed (atom 0)
       whole_number (range 95000 100000)]
    (fn []
      (js/setTimeout #(reset! seconds-elapsed (rand-price whole_number)) 1000)
;;      (js/setTimeout #(swap! seconds-elapsed inc) 1000)
      [:div {:style {:background "red" :width "150" :float "left"} }
       label (gstring/format " %0.3f" (/ @seconds-elapsed 1000) )])))