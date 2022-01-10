(ns parse-n-sort.utils)

(defn parse-date
  [date]
       (.parse (java.text.SimpleDateFormat. "yyyy-MM-dd") date))

(defn format-date
  [date]
  (.format (java.text.SimpleDateFormat. "M/d/yyyy") date))
