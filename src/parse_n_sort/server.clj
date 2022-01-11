(ns parse-n-sort.server
  (:require
   [muuntaja.core :as m]
   [reitit.ring :as ring]
   [reitit.ring.middleware.parameters :refer [parameters-middleware]]
   [reitit.ring.middleware.muuntaja :refer [format-negotiate-middleware
                                            format-request-middleware
                                            format-response-middleware]]
   [reitit.ring.coercion :refer [coerce-exceptions-middleware
                                 coerce-request-middleware
                                 coerce-response-middleware]]
   [reitit.coercion.schema]
   [schema.core :as s]
   [parse-n-sort.routes :refer [routes]]))

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
