(ns webpush.utils-test
  (:require [webpush.core :as push]
            [webpush.utils :as sut]
            [clojure.test :as t]))

(t/use-fixtures :once (fn [next]
                        (push/add-security-provider!)
                        (next)))

(t/deftest keys-generation
  (let [{:keys [public private]} (sut/generate-keys)]
    (t/is (string? public))
    (t/is (string? private))))
