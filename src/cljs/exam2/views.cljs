(ns exam2.views
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com]
   [breaking-point.core :as bp]
   [exam2.subs :as subs]
   [exam2.events :as events]
   [iron.re-utils :as re-utils :refer [<sub >evt event->fn sub->fn]]
   ))


;; home

; (defn display-re-pressed-example []
;   (let [re-pressed-example (re-frame/subscribe [::subs/re-pressed-example])]
;     [:div

;      [:p
;       [:span "Re-pressed is listening for keydown events. A message will be displayed when you type "]
;       [:strong [:code "hello"]]
;       [:span ". So go ahead, try it out!"]]

;      (when-let [rpe @re-pressed-example]
;        [re-com/alert-box
;         :alert-type :info
;         :body rpe])]))

; (defn home-title []
;   (let [name (re-frame/subscribe [::subs/name])]
;     [re-com/title
;      :label (str "Hello from " @name ". This is the Home Page.")
;      :level :level1]))

; (defn link-to-about-page []
;   [re-com/hyperlink-href
;    :label "go to About Page"
;    :href "#/about"])

; (defn home-panel []
;   [re-com/v-box
;    :gap "1em"
;    :children [[home-title]
;               [link-to-about-page]
;               [display-re-pressed-example]
;               [:div
;                [:h3 (str "screen-width: " @(re-frame/subscribe [::bp/screen-width]))]
;                [:h3 (str "screen: " @(re-frame/subscribe [::bp/screen]))]]
;               ]])


; ;; about

; (defn about-title []
;   [re-com/title
;    :label "This is the About Page."
;    :level :level1])

; (defn link-to-home-page []
;   [re-com/hyperlink-href
;    :label "go to Home Page"
;    :href "#/"])

; (defn about-panel []
;   [re-com/v-box
;    :gap "1em"
;    :children [[about-title]
;               [link-to-home-page]]])


; ;; main

(defn home-panel [] 
 [:div {:class "row"}
  [:div {:class "col-2"}
   [:form {:class "form-signin"} ""
    [:img {:class "mb-4" :src "../images/Cow.jpg" :alt "" :width "72" :height "72"}]
    [:h1 {:class "h3 mb-3 font-weight-normal"} "Please sign in"]
    [:label {:for "inputEmail" :class "sr-only"} "Email address"]
    [:input {:type "email" 
             :id "inputEmail"
             :class "form-control"
             :placeholder "Email address"
             :required "" 
             :autoFocus ""
             :value (<sub [::subs/login-email])
             :onChange  #(>evt [::events/changeLoginEmail (.-value (.getElementById js/document "inputEmail"))])
             }]
    [:label {:for "inputPassword" :class "sr-only"} "Password"]
    [:input {:type "password" :id "inputPassword" :class "form-control" :placeholder "Password" :required ""}]
    [:div {:class "checkbox mb-3"}
     [:label {}
      [:input {:type "checkbox" :value "remember-me"} ]]]
    [:button {:class "btn btn-lg btn-primary btn-block"
              :onClick #(println "Submit")} "Sign in" ]
    [:p {:class "mt-5 mb-3 text-muted"} "Â© 2017-2019"]]]]
)

(defn about-panel [] 
[:div 
[:p "this is about"] ])
(defn- panels [panel-name]
  (println panel-name)
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (<sub [::subs/active-panel])]
    (show-panel active-panel)))
