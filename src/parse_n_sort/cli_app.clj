(ns parse-n-sort.cli-app
  (:require
   [clojure.pprint :refer [pprint]]
   [parse-n-sort.parse :refer [parse-file]]
   [parse-n-sort.sort :refer [sort-by-1-key sort-by-2-keys]]))

(defn format-file
  "takes a file and a desired output style then prints the output"
  [path output]
  (let [parsed-file (-> path (slurp) (parse-file))
        format (Integer/parseInt output)]
    (cond
      (= format  1)
      (do
        (println "Sorting by 'color' and 'last name' ascending")
        (pprint (sort-by-2-keys parsed-file :color :last-name "ascending")))
      (= format 2)
      (do
        (println "Sorting by 'birth date' ascending")
        (pprint (sort-by-1-key parsed-file :birth-date "ascending")))
      (= format 3)
      (do
        (println "Sorting by 'last name' descending")
        (pprint (sort-by-1-key parsed-file :last-name "descending"))))))

  
  