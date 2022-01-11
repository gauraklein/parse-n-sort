(ns parse-n-sort.routes
  (:require
   [schema.core :as s]
   [parse-n-sort.api :refer [get-records post-record]]))


(def routes
  [["/" {:get (fn [req]
                {:status 200
                 :body "Hello Universe"})}]
   ["/records"
    {:post {:parameters {:body {:new-line s/Str}}
          		:handler post-record}}]
	
   ["/records/color" ;; returns records by color
    {:get (fn [req] {:status 200
                     :body (get-records :color)})}]

   ;; FIXME: broken currently birthdate is a string
   ["/records/birthdate" ;; returns records by birthdate
    {:get (fn [req] {:status 200 :body (get-records :birth-date)})}]


   ["/records/name" ;; returns records sorted by lastname
    {:get (fn [req] {:status 200 :body (get-records :last-name)})}]])