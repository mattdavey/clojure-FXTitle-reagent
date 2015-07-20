(ns ^:figwheel-always clojurefxtitle.core
    (:require
              [reagent.core :as reagent :refer [atom]]
              [clojurefxtitle.timer :as t]
    ))

(enable-console-print!)

;;(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "FX Dashboard"
                        :counters {"USDGBP" {:id 1
                                             :name "USDGBP"}
                                   "USDEUR" {:id 2
                                             :name "USDEUR"}
                                  }
                          }))

(defn pricetitle-component []
  [:div {:style {:background "green"} }
;;   [:p.someclass {:style {:text-align "center" :background "yellow"}}
;;    [:strong  "USDGBP"]
;;    ]
    ])


(defn fxtile [c]
  [:div {:style {:background "green" :width "300" :float "left" :border-style "solid"} }
    [:h1 {:style {:text-align "center" :margin "0" }} (:name c) ]
      [pricetitle-component]
        [:div
          [t/price-component "Buy"]
          [t/price-component "Sell"]
        ]
      ])

(defn dashboard []
  [:div {:style {:width "100%"} }
    (for [counter (vals (:counters @app-state))]
      ^{:key (:name counter)} [fxtile counter]
    )
  ]
)

(reagent/render-component [dashboard]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

