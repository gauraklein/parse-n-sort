(ns parse-n-sort.sort-test
  (:require
   [clojure.test :refer :all]
   [parse-n-sort.sort :refer [sort-records]]))

(def sort-input [{:last-name "last-name-3", :first-name "first-name-3", :email "email-3", :color "color-2", :birth-date "2013-05-11"}
                 {:last-name "last-name-1", :first-name "first-name-1", :email "email-1", :color "color-1", :birth-date "2000-01-01"}
                 {:last-name "last-name-2", :first-name "first-name-2", :email "email-2", :color "color-2", :birth-date "2002-10-20"}])
(def ascending-sort-output '({:last-name "last-name-1", :first-name "first-name-1", :email "email-1", :color "color-1", :birth-date "1/1/2000"}
                             {:last-name "last-name-2", :first-name "first-name-2", :email "email-2", :color "color-2", :birth-date "10/20/2002"}
                             {:last-name "last-name-3", :first-name "first-name-3", :email "email-3", :color "color-2", :birth-date "5/11/2013"}))
(def descending-sort-output '({:last-name "last-name-3", :first-name "first-name-3", :email "email-3", :color "color-2", :birth-date "5/11/2013"}
                              {:last-name "last-name-2", :first-name "first-name-2", :email "email-2", :color "color-2", :birth-date "10/20/2002"}
                              {:last-name "last-name-1", :first-name "first-name-1", :email "email-1", :color "color-1", :birth-date "1/1/2000"}))
(deftest sort-records-test

  (testing "throws exception when first arg is not a vector"
    (is (thrown? java.lang.AssertionError (sort-records {} 1)))
    (is (thrown? java.lang.AssertionError (sort-records nil 1))))

  (testing "throw expection when second arg is not an int"
    (is (thrown? java.lang.AssertionError (sort-records sort-input "1")))
    (is (thrown? java.lang.AssertionError (sort-records sort-input nil))))

  (testing "returns the correct sorted output"
    (is (= ascending-sort-output (sort-records sort-input 1)))
    (is (= ascending-sort-output (sort-records sort-input 2)))
    (is (= descending-sort-output (sort-records sort-input 3)))))

(comment
  (run-tests)
  )