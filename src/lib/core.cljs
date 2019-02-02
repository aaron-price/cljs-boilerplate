(ns lib.core
  (:require 
    [test.runner :refer [run-tests]]
    [lib.components.root :refer [hello]]
    [lib.helpers.collections :refer [includes?]]
    [reagent.core :as r]))

(def host (.-hostname js/window.location))
(def allowed-hosts ["localhost" "aaroncoding.com" ""])
(def allowed-host? (includes? host allowed-hosts))

(defn ^:export init []
  (if allowed-host? 
    (do 
      ; Run tests only in dev mode
      (when ^boolean js/goog.DEBUG
        (run-tests))
      
      ; render root component
      (r/render [hello] 
                (js/document.getElementById "aaron")))
    
    ; Not allowed
    (r/render [:div "Sorry, you do not have permission yet."] 
              (js/document.getElementById "aaron"))))

(init)
