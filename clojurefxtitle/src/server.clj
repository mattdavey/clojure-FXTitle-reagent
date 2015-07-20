(ns server
  (:require
   [ring.middleware.resource :refer [wrap-resource]]
   [ring.middleware.file-info :refer [wrap-file-info]]
   [ring.util.response :refer [redirect]]
   [compojure.core :refer :all]
   [compojure.route :as route]
   ))

(defn handler [request]
  (case (request :uri)
    "/" (redirect "index.html")
  )
)

(defroutes app
  (GET "/" [] (redirect "index.html"))
  (POST "/test1" req (println "Output: " (:body req)) 
     {:status 200 :body {:name "bob"} })
  (route/not-found "<h1>Custom - Page not found</h1>"))
  
(def static-server 
  (-> handler
      (wrap-resource "public")
      (wrap-file-info)))