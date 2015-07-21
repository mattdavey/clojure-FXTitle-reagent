(ns ^:figwheel-always clojurefxtitle.pricecomponent
    (:require
              [reagent.core :as reagent :refer [atom]]
              [goog.string :as gstring]
              [goog.string.format]
              [ajax.core :refer [GET POST]]
              ))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

(defn handler [response]
  (println "handler: " (str response))
)

(defn error-handler  [{:keys [status status-text]}]
  (println "error handler: " (str "something bad happened: " status " " status-text))
)

(defn my-click-handler [ccypair side price]
  (println (str "order " ccypair " " side " " price))
  (POST "/order"
          {:params {:price price
                    :side side
                    :ccypair ccypair}
           :handler handler
           :format :json
           :error-handler error-handler})    
  )
  
(defn rand-price [val]
  (rand-nth val))

(defn price-component [ccypair side]
  (let [seconds-elapsed (atom 0)
       whole_number (range 95000 100000)]
    (fn []
      (js/setTimeout #(reset! seconds-elapsed (rand-price whole_number)) 1000)
;;      (js/setTimeout #(swap! seconds-elapsed inc) 1000)
      (let [price (/ @seconds-elapsed 1000)]
        [:button {:style {:background "red" :width "150" :float "left" :text-align "center"}
           :on-click #(my-click-handler ccypair side price)}
         side (gstring/format " %0.3f" price )])))
       )