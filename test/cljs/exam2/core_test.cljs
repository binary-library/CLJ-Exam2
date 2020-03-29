(ns exam2.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [exam2.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
