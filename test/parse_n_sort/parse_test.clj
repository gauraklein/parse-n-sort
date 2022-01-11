(ns parse-n-sort.parse-test
  (:require
   [clojure.test :refer :all]
   [parse-n-sort.parse :refer [parse-records parse-args]]))

;; parse-records
(def pipe-str "last-name-1 | first-name-1 | email-1 | color-1 | 2000-01-01\nlast-name-2 | first-name-2 | email-2 | color-2 | 2002-10-20")
(def comma-str "last-name-1, first-name-1, email-1, color-1, 2000-01-01\nlast-name-2, first-name-2, email-2, color-2, 2002-10-20")
(def space-str "last-name-1 first-name-1 email-1 color-1 2000-01-01\nlast-name-2 first-name-2 email-2 color-2 2002-10-20")

(def correct-output [{:last-name "last-name-1", :first-name "first-name-1", :email "email-1", :color "color-1", :birth-date "2000-01-01"}
                     {:last-name "last-name-2", :first-name "first-name-2", :email "email-2", :color "color-2", :birth-date "2002-10-20"}])

(deftest parse-records-test

  (testing "throws an exception when arg is not a string"
    (is (thrown? java.lang.AssertionError (parse-records 1)))
    (is (thrown? java.lang.AssertionError (parse-records nil)))
    (is (thrown? java.lang.AssertionError (parse-records correct-output))))

  (testing "returns a message when no valid delimiter is found"
    (is (= "Unable to find a valid delimiter, please check your file and try again"
           (parse-records ""))))

  (testing "returns the correct output for properly formatted files"
    (is (= correct-output (parse-records pipe-str)))
    (is (= correct-output (parse-records comma-str)))
    (is (= correct-output (parse-records space-str)))))

;; parse-args
(def test-path "./test/parse_n_sort/test_input.txt")
(def parsed-file [{:last-name "last-name-3", :first-name "first-name-3", :email "email-3", :color "color-2", :birth-date "2013-05-11"}
                  {:last-name "last-name-1", :first-name "first-name-1", :email "email-1", :color "color-1", :birth-date "2002-10-20"}
                  {:last-name "last-name-2", :first-name "first-name-2", :email "email-2", :color "color-2", :birth-date "2000-01-01"}])

(deftest parse-args-test

  (testing "throws an error if path is not valid"
    (is (thrown? java.io.FileNotFoundException (parse-args "foo" "1"))))

  (testing "throws an error if output is not parseable"
    (is (thrown? java.lang.NumberFormatException (parse-args test-path "foo"))))

  (testing "returns the correct output"
    (is (= {:parsed-file parsed-file :output-format 1} (parse-args test-path "1")))
    (is (= {:parsed-file parsed-file :output-format 2} (parse-args test-path "2")))
    (is (= {:parsed-file parsed-file :output-format 3} (parse-args test-path "3")))))

(comment
  (run-tests))
