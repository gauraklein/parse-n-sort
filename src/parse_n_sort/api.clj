(ns parse-n-sort.api
(:require 
[parse-n-sort.parse :refer [parse-records]]
[parse-n-sort.sort :refer [sort-records]] 
[parse-n-sort.utils :refer [format-date determine-file-path]] )  )

;; Should take records from all 3 files merge them together then sort
;; going to have to add a case to the sort function that sorts by classname
;; Also an optional 3rd arg for direction if there's time

;; TODO: TEST ME
(defn all-records [] 
  (let [pipes (parse-records (slurp "./resources/pipe.txt"))
        commas (parse-records (slurp "./resources/comma.csv"))
        spaces (parse-records (slurp "./resources/space.txt"))]
    (into [] (concat pipes commas spaces))))

;;TODO: refactor to take direction and add testing
(defn get-records [sort-val]
  (let [sorted-records (sort-records (all-records) sort-val)
        formatted-records   (map #(assoc % :birth-date (format-date (:birth-date %))) sorted-records)]
    {:records (vec formatted-records)}))

(defn post-record [{:keys [parameters]}]
  (let [new-line (-> parameters :body :new-line)
        file-path (determine-file-path new-line)]
  (if file-path
    (do
      (spit file-path (apply str "\n" new-line) :append true)
      {:status 201
       :body "success"})
    {:status 422
     :body "no valid delimiter found"})))
