(ns parse-n-sort.utils
(:require 
[clojure.string :as str] )  )

;; TODO: testing and error handling
(defn determine-file-path
  "returns file path that string is using based on delimiter"
  [string]
  (cond
    (str/includes? string "|")
    "./resources/pipe.txt"
    (str/includes? string ",")
    "./resources/comma.csv"
    (str/includes? string " ")
    "./resources/space.txt"
    :else nil))

(defn parse-date
  [date]
       (.parse (java.text.SimpleDateFormat. "yyyy-MM-dd") date))

(defn format-date
  [date]
  (.format (java.text.SimpleDateFormat. "M/d/yyyy") date))
