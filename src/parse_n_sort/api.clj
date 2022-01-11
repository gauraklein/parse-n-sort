(ns parse-n-sort.api
  (:require
   [parse-n-sort.sort :refer [sort-records]]
   [parse-n-sort.utils :refer [determine-file-path
                               all-records
                               sort-val-to-keyword]]))

(defn get-records [{:keys [parameters]}]
  (let [raw-sort-val (-> parameters :path :sort-val)
        sort-key (get sort-val-to-keyword raw-sort-val)
        sorted-records (sort-records (all-records) sort-key)]
    {:status 200
     :body {:records sorted-records}}))

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
