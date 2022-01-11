(ns parse-n-sort.core
  (:require
   [parse-n-sort.cli-app :refer [print-output]]))

(defn -main
  "CLI entrypoint to parse and sort records takes a path and a desired format"
  [& args]
  (let [path (first args)
        output (second args)]
    (print-output path output)))
