(ns parse-n-sort.cli-app
  (:require
   [clojure.pprint :refer [print-table]]
   [parse-n-sort.parse :refer [parse-args]]
   [parse-n-sort.sort :refer [sort-records]]))



(defn print-output
  "pprints user desired output"
  [path output]
  (let [{:keys [parsed-file output-format]} (parse-args path output)
        sorted-records  (sort-records parsed-file output-format)]
    (print-table sorted-records)))
