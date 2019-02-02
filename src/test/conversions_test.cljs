(ns test.conversions-test
  (:require
    [lib.helpers.conversions :refer [obj->kmap obj->smap array->kvector array->svector]]
    [cljs.test :refer [is deftest]]))

(def sample_obj #js {
  "foo" "bar"
  "num" 42
  "child" #js {
    "name" "my name"
  }
})

(def sample_kmap {
  :foo "bar"
  :num 42
  :child {
    :name "my name"
  }
})


(deftest obj->kmap_test
  (is (= sample_kmap (obj->kmap sample_obj))))


(def sample_smap {
  "foo" "bar"
  "num" 42
  "child" {
    "name" "my name"
  }
})


(deftest obj->smap_test
  (is (= sample_smap (obj->smap sample_obj))))


(def sample_arr #js [
  #js { "id" 1 "m" #js { "name" "a" } }
  #js { "id" 2 "m" #js { "name" "b" } }
])

(def sample_kvect [
  { :id 1 :m { :name "a" } }
  { :id 2 :m { :name "b" } }
])

(deftest arr->kvector
  (is (= sample_kvect (array->kvector sample_arr) )))

(def sample_svect [
  { "id" 1 "m" { "name" "a" } }
  { "id" 2 "m" { "name" "b" } }
])

(deftest arr->svector
  (is (= sample_svect (array->svector sample_arr) )))


