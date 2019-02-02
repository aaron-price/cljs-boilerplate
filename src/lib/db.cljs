(ns lib.db
  (:require
    [reagent.core :as r]
  ))


(def initial-state {})

(def state (r/atom initial-state))

; Used for testing. Probably not helpful in production
(defn reset-state [] (reset! state initial-state))

