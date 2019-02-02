(ns lib.core
  (:require 
    [test.runner :refer [run-tests]]
    [lib.components.root :refer [hello]]
    [lib.helpers.collections :refer [includes?]]
    [reagent.core :as r]))

(def host (.-hostname js/window.location))
(def allowed-hosts ["localhost" "aaroncoding.com" ""])
(def allowed-host? (includes? host allowed-hosts))


(defn ^:dev/after-load dev-runner []
  ; Re-run tests with every reload (:dev only)
  (when ^boolean js/goog.DEBUG
    (run-tests)))


(defn ^:export init []
  (if allowed-host? 
    (do 
      (dev-runner)
      
      ; render root component
      (r/render [hello] 
                (js/document.getElementById "aaron")))
    
    ; Not allowed
    (r/render [:div "Sorry, this domain hasn't been whitelisted. :-("] 
              (js/document.getElementById "aaron"))))

(init)

