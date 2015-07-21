(ns server
  (:require
   [ring.middleware.resource :refer [wrap-resource]]
   [ring.middleware.json :as middleware]
   [ring.middleware.file-info :refer [wrap-file-info]]
   [ring.util.response :refer [redirect]]
   [compojure.handler :as handler]
   [compojure.core :refer :all]
   [compojure.route :as route]
   ))

(defn handler [request]
  (case (request :uri)
    "/" (redirect "index.html")
  )
)

(defn order-handler [req]
;;  (let [body (-> req :body)]
;;    (println "Request: " req " Body: " body)
;;  )
  (let [price (-> req :params :price)
    ccypair (-> req :params :ccypair)
    side (-> req :params :side)]
      (println (str "Order: " price side ccypair)) 
  )
  
  {:status 200 }
)  
     
(defroutes app-routes
  (GET "/" [] (redirect "index.html"))
  (POST "/order" [] order-handler) 
  (route/not-found "<h1>Custom - Page not found</h1>"))
  
(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-params)
      (middleware/wrap-json-response)))