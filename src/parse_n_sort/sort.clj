(ns parse-n-sort.sort
  (:require
   [parse-n-sort.utils :refer [parse-birth-dates format-records]]))

;; TODO: add direction in here - separate out format code 
(defn sort-records
  "takes a set of records and a desired output style then prints the output"
  [records format]
  {:pre [(vector? records) (or (int? format) (keyword? format))]}
  (cond
    (= format  1)
    (do
      (println "Sorting by 'color' and 'last name' ascending")
      (->> records
           (parse-birth-dates)
           (sort-by (juxt :color :last-name))
           (format-records)))
    (= format 2)
    (do
      (println "Sorting by 'birth date' ascending")
      (->> records
           (parse-birth-dates)
           (sort-by :birth-date)
           (format-records)))
    (= format 3)
    (do
      (println "Sorting by 'last name' descending")
      (->> records
           (parse-birth-dates)
           (sort-by :last-name #(compare %2 %1))
           (format-records)))
    (keyword? format)
    (do
      (println (str "Sorting by " format " ascending"))
      (->> records
           (parse-birth-dates)
           (sort-by format)
           (format-records)))))
