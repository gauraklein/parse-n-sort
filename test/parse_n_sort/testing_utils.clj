(ns parse-n-sort.testing-utils
  (:require
   [clojure.string :as str]
   [clojure.data.json :as json]))

(defn format-response
  "formats json response to map for better comparison"
  [response]
  (json/read-str (slurp (:body response)) :key-fn keyword))

(defn sanitize-file
  "removes string from file"
  [filepath string]
  (spit filepath (str/replace (slurp filepath) (str "\n" string) "")))