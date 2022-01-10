(ns parse-n-sort.parse
  (:require
   [clojure.string :as str]
   [parse-n-sort.utils :refer [format-date]]))

(defn parse-records
  "Takes a string of records and splits based on delimiter returns a vector"
  [records]
  {:pre [(string? records)]}
  (let [delimiter  (some
                    #(when (re-find % records) %)
                    [#" \| " #", " #" "])]
    (if
     delimiter
      (->> records
           (str/split-lines)
           (map #(str/split % delimiter))
           (map #(zipmap [:last-name :first-name :email :color :birth-date] %))
           (map #(assoc % :birth-date (format-date (:birth-date %))))
           (vec))
      "Unable to find a valid delimiter, please check your file and try again")))

(defn parse-args
  "parses args passed by user"
  [path output]
  {:parsed-file (-> path (slurp) (parse-records))
   :output-format  (Integer/parseInt output)})
