(ns exam2.config)

(def debug?
  ^boolean goog.DEBUG)

(defonce firebase-app-info
 {:projectId ""
  :apiKey ""
  :authDomain ""
  :databaseURL ""
  :storageBucket ""})
