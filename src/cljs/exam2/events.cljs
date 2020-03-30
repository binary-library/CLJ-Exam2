(ns exam2.events
  (:require
   [re-frame.core :as re-frame]
   [re-frame.core :refer [reg-event-db reg-event-fx]]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [iron.re-utils :as re-utils :refer [<sub >evt event->fn sub->fn]]
   [exam2.config :as config]
   [exam2.db :as db]
   ))

; *** helper start ***
; (defonce secondary-app (js/firebase.initializeApp (clj->js config/firebase-app-info) "Secondary"))

; (defn email-create-user [{:keys [email password]}]
;   (-> secondary-app
;       (.auth)
;       (.createUserWithEmailAndPassword email password)
;       (.then #(js/alert (str "Created User!\n" %))
;       (.catch #(js/alert (str "Failed to create user: " %))))))

; *** helper end ***

; App Start
(reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(reg-event-db
 ::changeLoginEmail
 (fn-traced [db [_ new-email]]
  (println new-email)
  (assoc db :login-email new-email)))

(reg-event-db
 ::set-active-panel
 (fn-traced [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(reg-event-db
 ::set-re-pressed-example
 (fn [db [_ value]]
   (assoc db :re-pressed-example value)))

; App end

; *** Firebase Start ***
; Firebase firestore
; (reg-event-fx
;  ::fetch-user-data
;  (fn-traced [coeffects [_ user-id]]
;   (println "Fetching user-data: " user-id)  
;   (if (nil? user-id)
;    {}
;    ; {:firestore/get
;    ;  {:path-document [:users (str user-id)]
;    ;   :on-success [::succeeded-fetch-user-data]
;    ;   :on-failure [::failed-fetch-user-data]}}
;    {}
;    )))
; (reg-event-fx
;  ::succeeded-fetch-user-data
;  (fn-traced [coeffects [_ db-user-data]]
;   (println "Succeeded fetch user-data" db-user-data)
;   (let [user-data (:data db-user-data)]
;    (if (nil? user-data)
;     (do (>evt [::failed-user-data db-user-data]) {})
;     {:db (assoc (:db coeffects) :user-data user-data)}))))
; (reg-event-fx
;  ::failed-fetch-user-data
;  (fn-traced [coeffects [_ value]]
;   (println (str "Failed fetch user data: " value))
;   (js/setTimeout #(>evt [::fetch-user-data]) 1000)
;   {}))


; ; Firebase auth login event
; (reg-event-db
;  ::set-auth-user
;  (fn-traced [coeffects [_ user-data]]
;   (println "Setting user data" user-data)
;   ; (>evt [::fetch-user-data (:uid user-data)])
;   ; (>evt [::set-active-panel :home])
;   {:db (assoc (:db coeffects) :user user-data)}))

; (reg-event-fx
;  ::firebase-error
;  (fn-traced [_ [_ firebase-error]]
;   (println "firebase error: " firebase-error)
;   (js/alert (str "Failed to login!\n" firebase-error))
;   {}))
