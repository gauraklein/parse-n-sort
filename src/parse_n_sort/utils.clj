(ns parse-n-sort.utils
  (:require
   [clojure.string :as str]
   [parse-n-sort.parse :refer [parse-records]]))

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

(defn parse-birth-dates
  "takes a collection of records and parses the birth-dates for sorting"
  [records]
  (map #(assoc % :birth-date (parse-date (:birth-date %))) records))

(defn format-date
  [date]
  (.format (java.text.SimpleDateFormat. "M/d/yyyy") date))

(defn format-records
  "takes a collection of records and formats them for the user"
  [records]
  (vec (map #(assoc % :birth-date (format-date (:birth-date %))) records)))

(defn all-records []
  (let [pipes (parse-records (slurp "./resources/pipe.txt"))
        commas (parse-records (slurp "./resources/comma.csv"))
        spaces (parse-records (slurp "./resources/space.txt"))]
    (into [] (concat pipes commas spaces))))

(def sort-val-to-keyword
  {"birthdate" (keyword "birth-date")
   "name" (keyword "last-name")
   "color" (keyword "color")})
