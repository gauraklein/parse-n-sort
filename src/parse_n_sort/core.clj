(ns parse-n-sort.core
  (:require
   [parse-n-sort.cli-app :refer [format-file]]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [path (first args)
        output (second args)]
    (println "parsing and sorting")
    (format-file path output)))
