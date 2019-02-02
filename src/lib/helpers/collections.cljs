(ns lib.helpers.collections
  (:require
    [com.rpl.specter :refer-macros [select select-one] :refer [ALL FIRST LAST pred pred=]]
  ))


; (find-item (fn [x] (= (x "name") "AA") [{"name" "AA"} {"name" "BB"}]
; => {"name" "AA"}
(defn find-item [cb vect]
  (select-one [ALL (pred cb)] vect))


; (includes? 2 [1 2 3 4])
; => true
(defn includes? [itm vect]
  (some?
    (select-one [ALL (pred= itm)] vect)))


; (includes-item? (fn [x] (= (x "name") "AA") [{"name" "AA" "name" "BB"}])
; => true
(defn includes-item? [cb vect]
  (some? (find-item cb vect)))


; (remove-i 1 [ "a" "b" "c" ])
; => ["a" "c"]
(defn remove-i [i vect]
  (vec 
    (concat 
      (subvec vect 0 i)
      (subvec vect (inc i)))))

; (def obj { :a {:b "B" :c "C"} })
; (dissoc-in obj [:a :b]) => { :a { :c "C" } }
(defn dissoc-in
  [m [k & ks :as keys]]
  (if ks
    (if-let [nextmap (get m k)]
      (let [newmap (dissoc-in nextmap ks)]
        (if (seq newmap)
          (assoc m k newmap)
          (dissoc m k)))
      m)
    (dissoc m k)))


(defn do-in [fun m path payload]
  "Apply function at specific field inside map
  merge-in: (do-in merge {:stuff {:a 'A'}} [:stuff] {:b 'B'})
  conj-in: (do-in conj {:stuff '(:a :b)} [:stuff] :c)
  "
  (assoc-in m path 
    (fun 
      (get-in m path) 
      payload)))

; (itr (fn [x] [:div x]) ["a" "b" "c"])
(defn itr
  "Similar to map, but automatically handles :key for react components"
  [cb coll]
  [:<>
    (doall
      (map-indexed
        (fn [idx itm]
          [:<> {:key idx} [cb itm]])
        coll))])

; (itri (fn [i x] [:div "index: " i " | " x]) ["a" "b" "c"])
(defn itri
  "Similar to map-indexed, but automatically handles :key for react components"
  [cb coll]
  [:<>
    (doall
      (map-indexed
        (fn [idx itm]
          [:<> {:key idx} [cb idx itm]])
        coll))])

(defn drop-nth [coll n]
  (let [
    hd (subvec coll 0 n)
    tl (subvec coll (inc n))
    new-vec (into hd tl)    
  ] new-vec))

