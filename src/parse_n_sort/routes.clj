(ns parse-n-sort.routes
  (:require
   [schema.core :as s]
   [parse-n-sort.api :refer [get-records post-record]]))

(def routes
  [;; receives a string and attempts to parse record values from it
   ["/records"
    {:post {:parameters {:body {:new-line s/Str}}
            :handler post-record}}]
   ;; returns records sorted by sort-val
   ["/records/:sort-val" {:parameters {:path {:sort-val s/Str}}
                          :get get-records}]])
