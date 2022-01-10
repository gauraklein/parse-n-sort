(ns parse-n-sort.utils)

(defn format-date
  [date]
  (->> date
       (.parse (java.text.SimpleDateFormat. "yyyy-MM-dd"))
       (.format (java.text.SimpleDateFormat. "M/d/yyyy"))))

