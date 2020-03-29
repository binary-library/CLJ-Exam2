(ns exam2.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::active-panel
 (fn [db [myfirstvar]]
    (println myfirstvar)
   (:active-panel db)))

; ; (re-frame/reg-sub
;  ::re-pressed-example
;  (fn [db _]
;    (:re-pressed-example db)))
