(ns parse-n-sort.utils-test
  (:require
   [clojure.test :refer :all]
   [parse-n-sort.utils :refer [format-date parse-date]]))

;; parse-date
(deftest parse-date-test
  (testing "throws an error if date is not formattable"
  (is (thrown? java.lang.IllegalArgumentException (format-date "Foo"))
  (is (= "1/9/2022" (format-date #inst "2022-01-09T06:00:00.000-00:00")))    )  ))

;; format-date
(deftest format-date-test
  (testing "throws an error if date is not parseable"
    (is (thrown? java.text.ParseException (parse-date "FOOO"))))
  (testing "returns desired date format"
    (is (= #inst "2022-01-09T06:00:00.000-00:00" (parse-date "2022-01-09")))))

(comment
  (run-tests))
