(ns parse-n-sort.server
  (:require
   [muuntaja.core :as m]
   [org.httpkit.server :refer [run-server]]
   [reitit.ring :as ring]
   [reitit.ring.middleware.parameters :refer [parameters-middleware]]
   [reitit.ring.middleware.muuntaja :refer [format-negotiate-middleware
                                            format-request-middleware
                                            format-response-middleware]]
   [reitit.ring.coercion :refer [coerce-exceptions-middleware
                                 coerce-request-middleware
                                 coerce-response-middleware]]
   [reitit.coercion.schema]
   [parse-n-sort.routes :refer [routes]])
  (:gen-class))

(def server
  (ring/ring-handler
   (ring/router
    routes
    {:data {:coercion reitit.coercion.schema/coercion
            :muuntaja m/instance
            :middleware [parameters-middleware
                         format-negotiate-middleware
                         format-response-middleware
                         format-request-middleware
                         coerce-exceptions-middleware
                         coerce-request-middleware
                         coerce-response-middleware]}})
   (ring/routes
    (ring/redirect-trailing-slash-handler)
    (ring/create-default-handler
     {:not-found (constantly {:status 404
                              :body "Route not found"})}))))

(defn start-server []
  (println "Starting API server on port 4000...")
  (run-server server {:port 4000})
  (println "Server successfully started!"))
