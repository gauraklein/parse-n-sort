(ns parse-n-sort.core-test
  (:require [clojure.test :refer :all]
            [parse-n-sort.parse :refer [parse-file]]))

;; parse-file

(def pipe-str "last-name-1 | first-name-1 | email-1 | color-1 | birth-date-1\nlast-name-2 | first-name-2 | email-2 | color-2 | birth-date-2")
(def comma-str "last-name-1, first-name-1, email-1, color-1, birth-date-1\nlast-name-2, first-name-2, email-2, color-2, birth-date-2")
(def space-str "last-name-1 first-name-1 email-1 color-1 birth-date-1\nlast-name-2 first-name-2 email-2 color-2 birth-date-2")

(def correct-output [{:last-name "last-name-1", :first-name "first-name-1", :email "email-1", :color "color-1", :birth-date "birth-date-1"}
                     {:last-name "last-name-2", :first-name "first-name-2", :email "email-2", :color "color-2", :birth-date "birth-date-2"}])

(deftest parse-file-test

  (testing "throws an exception when arg is not a string"
    (is (thrown? java.lang.AssertionError (parse-file 1)))
    (is (thrown? java.lang.AssertionError (parse-file nil)))
    (is (thrown? java.lang.AssertionError (parse-file correct-output))))

  (testing "returns a message when no valid delimiter is found"
    (is (= "Unable to find a valid delimiter, please check your file and try again" (parse-file ""))))

  (testing "returns the correct output for properly formatted files"
    (is (= correct-output (parse-file pipe-str)))
    (is (= correct-output (parse-file comma-str)))
    (is (= correct-output (parse-file space-str)))))

  (run-tests)
