(ns test.runner
  (:require
    [shadow.test :as tests]
    [lib.db :refer [reset-state]]
    
    ; Manually require every test module here
    [test.conversions-test]
    [test.collections-test]
  ))


(def size "margin: 1rem; font-size: 1.5rem;")
(def fail (str "background: #F44336; color: #FFF;" size))
(def success (str "background: #4CAF50; color: #FFF;" size))

(defn logger [t & args]
  (.log js/console (str "%c" args) t))

(defn run-tests []
  (let [
         test_str  (with-out-str (tests/run-all-tests))
         ;passes    (not (clojure.string/includes? test_str "FAIL"))
         test_stats (re-find #"Ran\s+.*\stests\scontaining\s+.*\sassertions.\n+.*\sfailures,\s+.*errors." test_str)
         [test_count failures] (clojure.string/split-lines test_stats)
         fail_count (first (clojure.string/split (re-find #"\d+.*failures" failures) " "))
         error_count (second (clojure.string/split (re-find #"\s\d+.*errors" failures) " "))
         passes (and (= fail_count "0") (= error_count "0"))
       ]
    (prn "==~=~=~=~=~=~=~=~=~=~=~=~~=~=~=~=~=~")
    (reset-state)
    (if passes
      ((fn []
         (logger success test_count)
         (logger success failures)
      ))
      ((fn []
         (logger fail failures)
         (tests/run-all-tests)
      )))
  ))

