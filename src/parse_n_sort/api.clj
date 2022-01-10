(ns parse-n-sort.api
(:require 
[parse-n-sort.parse :refer [parse-records]]
[parse-n-sort.sort :refer [sort-records]] )  )

;; Should take records from all 3 files merge them together then sort
;; going to have to add a case to the sort function that sorts by classname
;; Also an optional 3rd arg for direction if there's time
(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello world."})

;; TODO: TEST ME
(defn all-records [] 
  (let [pipes (parse-records (slurp "./resources/pipe.txt"))
        commas (parse-records (slurp "./resources/comma.csv"))
        spaces (parse-records (slurp "./resources/space.txt"))]
    (into [] (concat pipes commas spaces))))

;;TODO: refactor to take direction and add testing
(defn get-records [sort-val] 
  (sort-records (all-records) sort-val))