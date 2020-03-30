(ns exam2.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [re-pressed.core :as rp]
   [breaking-point.core :as bp]
   [exam2.events :as events]
   [exam2.subs :as subs]
   [exam2.routes :as routes]
   [exam2.views :as views]
   [exam2.config :as config]
   [com.degel.re-frame-firebase :as firebase]
   ))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  ; (re-frame/dispatch-sync [::rp/add-keyboard-event-listener "keydown"])
  ; (re-frame/dispatch-sync [::bp/set-breakpoints
  ;                          {:breakpoints [:mobile
  ;                                         768
  ;                                         :tablet
  ;                                         992
  ;                                         :small-monitor
  ;                                         1200
  ;                                         :large-monitor]
  ;                           :debounce-ms 166}])
  (dev-setup)
  ; (firebase/init :firebase-app-info      config/firebase-app-info
  ;               ; See: https://firebase.google.com/docs/reference/js/firebase.firestore.Settings
  ;               ; :firestore-settings     {:timestampsInSnapshots true}
  ;               :get-user-sub           [::subs/user]
  ;               :set-user-event         [::events/set-auth-user]
  ;               :default-error-handler  [::events/firebase-error])
  (mount-root))
