(ns parse-n-sort.sort
  (:require
   [parse-n-sort.parse :refer [parse-file]]))

(defn sort-by-1-key
  "wrapper around sort-by for one val"
  [coll k direction]
  (if (= direction "descending")
    (sort-by k #(compare %2 %1) coll)
    (sort-by k coll)))

(defn sort-by-2-keys
  "wrapper around sort-by for two vals"
  [coll k1 k2 direction]
  (if (= direction "descending")
    (sort-by (juxt k1 k2) #(compare %2 %1) coll)
    (sort-by (juxt k1 k2) coll)))

(sort-by-2-keys (parse-file (slurp "./resources/pipe.txt")) :color :email "ascending")
