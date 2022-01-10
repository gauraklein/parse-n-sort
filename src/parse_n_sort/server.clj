(ns parse-n-sort.server
  (:require
   [muuntaja.core :as m]
   [org.httpkit.server :refer [run-server]]
   [reitit.ring :as ring]
   [reitit.ring.middleware.muuntaja :refer [format-negotiate-middleware
                                            format-request-middleware
                                            format-response-middleware]]
   [parse-n-sort.routes :refer [routes]]))

(defonce dev-server (atom nil))

(defn stop-server []  
  (when-not (nil? @dev-server)
    (println "Stopping server")
    (@dev-server :timeout 100)
    (reset! dev-server nil)))

(def server
  (ring/ring-handler
   (ring/router 
    routes
 {:data {:muuntaja m/instance
         :middleware [format-negotiate-middleware
                      format-response-middleware
                      format-request-middleware]}}  	)
   (ring/routes
    (ring/redirect-trailing-slash-handler)
    (ring/create-default-handler
     {:not-found (constantly {:status 404
                              :body "Route not found"})}))))

(defn start-server []
  (println "THE SERVER HAS STARTED")
  (reset! dev-server (run-server server {:port 4000})))

(defn restart-server []
  (stop-server)
  (start-server))

(comment
  (stop-server)
  (restart-server)
  (start-server)
  )