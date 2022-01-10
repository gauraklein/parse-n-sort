(ns parse-n-sort.sort)

;; TODO: add direction in here - separate out format code 
(defn sort-records
  "takes a set of records and a desired output style then prints the output"
  [records format]
  {:pre [(vector? records) (or (int? format) (keyword? format))]}
  (cond
    (= format  1)
    (do
      (println "Sorting by 'color' and 'last name' ascending")
      (sort-by (juxt :color :last-name) records))
    (= format 2)
    (do
      (println "Sorting by 'birth date' ascending")
      (sort-by :birth-date records))
    (= format 3)
    (do
      (println "Sorting by 'last name' descending")
      (sort-by :last-name #(compare %2 %1) records))
    (keyword? format)
    (do
      (println (str "Sorting by " format " ascending"))
    ;; could add (when "descedning" #(compare %2 %1)) 
      (sort-by format records))))
