(ns parse-n-sort.api-test
  (:require
   [parse-n-sort.server :refer [server]]
   [clojure.test :refer :all]
   [ring.mock.request :as mock]
   [parse-n-sort.utils :refer [all-records]]
   [parse-n-sort.sort :refer [sort-records]]))

(defn sorted-by-last-name []
  (sort-records (all-records) :last-name))

(deftest test-api
  (testing "get-records"
    (let [response (server (mock/request :get "/records/name"))]
      (println (:body response))
      (is (= (:status response) 200))
      (is (= (slurp (:body response)) (sorted-by-last-name)))))
  (testing "404"
    (let [response (server (mock/request :get "/"))]
      (is (= (:status response) 404))
      (is (= (:body response) "not found")))))

(run-tests)

