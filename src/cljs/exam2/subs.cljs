(ns exam2.subs
  (:require
   [re-frame.core :as re-frame]
   [re-frame.core :refer [reg-sub subscribe]]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::active-panel
 (fn [db [myfirstvar]]
   (println "ACTIVE-PANEL: " db)
   (:active-panel db)))

; ; (re-frame/reg-sub
;  ::re-pressed-example
;  (fn [db _]
;    (:re-pressed-example db)))

; Logged in Firebase auth user
(reg-sub
 ::user
 (fn [db _]
  (:user db)))

(reg-sub
 ::login-email
 (fn [db _]
  (:login-email db)))
