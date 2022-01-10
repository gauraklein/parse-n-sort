(ns parse-n-sort.cli-app
  (:require
   [clojure.pprint :refer [print-table]]
   [parse-n-sort.parse :refer [parse-args]]
   [parse-n-sort.sort :refer [sort-records]]
   [parse-n-sort.utils :refer [format-date]]))



(defn print-output
  "pprints user desired output"
  [path output]
  (let [{:keys [parsed-file output-format]} (parse-args path output)
        sorted-records  (sort-records parsed-file output-format)
        formatted-records (map #(assoc % :birth-date (format-date (:birth-date %))) sorted-records)]
    (print-table formatted-records)))