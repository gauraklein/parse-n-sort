(ns parse-n-sort.core
  (:require
   [parse-n-sort.cli-app :refer [print-output]]))

(defn -main
  "Application to parse and sort records"
  [& args]
  (let [path (first args)
        output (second args)]
    (println "Sorting " path)
    (print-output path output)))
