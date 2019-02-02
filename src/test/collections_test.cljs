(ns test.collections-test
  (:require
    [cljs.test :refer [deftest is]]
    [lib.helpers.collections :refer [includes? find-item includes-item? dissoc-in do-in drop-nth]]
  ))

(deftest test-vectors
  (is (=
    true
    (includes? 2 [1 2 3])))

  (is (=
    false
    (includes? 5 [1 2 3])))

  (is (=
    (find-item 
      (fn [x] (= "Aaron" (x "name")))
      [{"name" "Aaron"} {"name" "Price"}])
    {"name" "Aaron"}))  

  (is (=
    (find-item
      (fn [x] (= "Price" (x "name")))
      [{"name" "Aaron"}])
    nil))

  (is (not
    (includes-item?
      (fn [x] (= "Price" (x "name")))
      [{"name" "Aaron"}])))
  
  (is 
    (includes-item?
      (fn [x] (= "Aaron" (x "name")))
      [{"name" "Aaron"}]))
  (is (=
    (dissoc-in {:config {:x "X"} :elements {:a "A" :b "B"}} [:elements :b])
    {:config {:x "X"} :elements {:a "A"}}))
  (is (=
    (do-in merge {:config {:a "A"}} [:config] {:b "B"})
    {:config {:a "A" :b "B"}}))
  (is (=
    (do-in conj {:stuff '(1 2)} [:stuff] 3)
    {:stuff '(3 1 2)}))
  
  (is (=
    (drop-nth ["A" "B" "C"] 0)
    ["B" "C"]))
  (is (=
    (drop-nth ["A" "B" "C"] 1)
    ["A" "C"]))
  (is (=
    (drop-nth ["A" "B" "C"] 2)
    ["A" "B"]))

  (is (=
    (do-in drop-nth {:tweens [1 2 3]} [:tweens] 1)
    {:tweens [1 3]}
    ))
)


