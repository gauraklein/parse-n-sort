(ns parse-n-sort.parse
  (:require
   [clojure.string :as str]))

;; TODO: Format Dates
(defn parse-file
  "Takes a string and splits based on delimiter returns a vector"
  [file]
  {:pre [(string? file)]}
  (let [delimiter  (some
                    #(when (re-find % file) %)
                    [#" \| " #", " #" "])]
    (if
     delimiter
      (->> file
           (str/split-lines)
           (map #(str/split % delimiter))
           (map #(zipmap [:last-name :first-name :email :color :birth-date] %))
           (vec))
      "Unable to find a valid delimiter, please check your file and try again")))

;; (parse-file (slurp "./resources/pipe.txt"))
