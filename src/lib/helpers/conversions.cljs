(ns lib.helpers.conversions)

; Convert js object into { :keyworded map }
(defn obj->kmap
  [obj]
  (if (goog.isObject obj)
    (-> (fn [result key]
          (let [v (goog.object/get obj key)]
            (if (= "function" (goog/typeOf v))
              result
              (assoc result (keyword key) (obj->kmap v)))))
        (reduce {} (.getKeys goog/object obj)))
    obj))

; Convert js object into { "stringed": map }
(defn obj->smap
  [obj]
  (if (goog.isObject obj)
    (-> (fn [result key]
          (let [v (goog.object/get obj key)]
            (if (= "function" (goog/typeOf v))
              result
              (assoc result key (obj->smap v)))))
        (reduce {} (.getKeys goog/object obj)))
    obj))


; #js [{"a" 1}] -> [{"a" 1}]
(defn array->kvector
  [arr]
  (map obj->kmap arr))


; #js [{"a" 1}] -> [{:a 1}]
(defn array->svector
  [arr]
  (map obj->smap arr))


