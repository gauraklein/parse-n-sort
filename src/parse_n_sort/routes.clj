(ns parse-n-sort.routes
(:require [parse-n-sort.api :refer [get-records]])  )

(def routes
  [["/" {:get (fn [req]
                {:status 200
                 :body "Hello Universe"})}]
   ["/records/color" ;; returns records by color
    {:get (fn [req] {:status 200
                     :body (get-records :color)})}]

   ;; FIXME: broken currently birthdate is a string
   ["/records/birthdate" ;; returns records by birthdate
    {:get (fn [req] {:status 200 :body (get-records :birth-date)})}]


   ["/records/name" ;; returns records sorted by lastname
    {:get (fn [req] {:status 200 :body (get-records :last-name)})}]

   ["/records" {:post (fn [params] {:status 200 :body "adds a new record to appropriate file"})}]])