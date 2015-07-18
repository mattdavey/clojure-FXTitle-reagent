(ns ^:figwheel-always clojurefxtitle.core
    (:require
              [reagent.core :as reagent :refer [atom]]
              [clojurefxtitle.timer :as t]
    ))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "USDGBP"}))

(defn simple-component []
  [:div {:style {:background "green"} }
;;   [:p.someclass {:style {:text-align "center" :background "yellow"}}
;;    [:strong  "USDGBP"]
;;    ]
    ])


(defn fxtile []
  [:div {:style {:background "green" :width "300" :float "left" :border-style "solid"} }
    [:h1 {:style {:text-align "center" :margin "0" }} (:text @app-state) ]
      [simple-component]
        [:div
          [t/timer-component "Buy"]
          [t/timer-component "Sell"]
        ]
      ])

(defn dashboard []
  [:div {:style {:background "yellow" :width "100%"} }
    [fxtile]
    [fxtile]
  ]
)

(reagent/render-component [dashboard]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

