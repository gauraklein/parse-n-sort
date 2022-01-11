(ns parse-n-sort.api-test
  (:require
   [clojure.test :refer :all]
   [ring.mock.request :as mock]
   [clojure.string :as str]
   [parse-n-sort.server :refer [server]]
   [parse-n-sort.utils :refer [all-records]]
   [parse-n-sort.sort :refer [sort-records]]
   [parse-n-sort.testing-utils :refer [format-response sanitize-file]]))

;; GET
(defn sorted-by-last-name []
  (sort-records (all-records) :last-name))
(defn sorted-by-birth-date []
  (sort-records (all-records) :birth-date))
(defn sorted-by-color []
  (sort-records (all-records) :color))

(deftest get-request-test
  (testing "records/name"
    (let [response (server (mock/request :get "/records/name"))]
      (is (= (:status response) 200))
      (is (= (format-response response)
             {:records (sorted-by-last-name)}))))

  (testing "records/birthdate"
    (let [response (server (mock/request :get "/records/birthdate"))]
      (is (= (:status response) 200))
      (is (= (format-response response)
             {:records (sorted-by-birth-date)}))))

  (testing "records/color"
    (let [response (server (mock/request :get "/records/color"))]
      (is (= (:status response) 200))
      (is (= (format-response response)
             {:records (sorted-by-color)}))))

  (testing "404"
    (let [response (server (mock/request :get "/"))]
      (is (= (:status response) 404))
      (is (= (:body response) "not found")))))

;; POST
(def pipe-new-line "you | should | delete | this | line")
(def pipe-path "./resources/pipe.txt")
(def comma-new-line "you, should, delete, this, line")
(def comma-path "./resources/comma.csv")
(def space-new-line "you should delete this line")
(def space-path "./resources/space.txt")

(deftest post-request-test
  (testing "POST to /records pipe.txt"
    (let [response (server (-> (mock/request :post "/records")
                               (mock/json-body {:new-line pipe-new-line})))]
      (is (= (:status response) 201))
      (is (str/includes? (slurp pipe-path) pipe-new-line))
      (sanitize-file pipe-path pipe-new-line)))
  (testing "POST to /records comma.csv"
    (let [response (server (-> (mock/request :post "/records")
                               (mock/json-body {:new-line comma-new-line})))]
      (is (= (:status response) 201))
      (is (str/includes? (slurp comma-path) comma-new-line))
      (sanitize-file comma-path comma-new-line)))
  (testing "POST to /records space.txt"
    (let [response (server (-> (mock/request :post "/records")
                               (mock/json-body {:new-line space-new-line})))]
      (is (= (:status response) 201))
      (is (str/includes? (slurp space-path) space-new-line))
      (sanitize-file space-path space-new-line)))
  (testing "422"
    (let [response (server (-> (mock/request :post "/records")
                               (mock/json-body {:new-line "this-should-fail"})))]
      (is (= (:status response) 422))
      (is (= (:body response) "no valid delimiter found")))))

(comment
  (run-tests))

