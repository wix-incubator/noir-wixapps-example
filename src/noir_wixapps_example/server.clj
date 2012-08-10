(ns noir-wixapps-example.server
  (:import [org.apache.commons.codec.binary Base64])
  (:require [noir.server :as server]
             [clj-json.core :as json])
  (:use noir.core
        ring.middleware.params
        ring.middleware.wixapps-middleware
        hiccup.core
        hiccup.form-helpers
        hiccup.page-helpers))

(defpartial main-layout [& content]
  (html5
    [:head
     [:title "WixApps example"]]
    [:body
     content]))

(defn parse-instance [instance]
  (let [[given-hmac signed-instance] (clojure.string/split  instance #"\.")]
    (json/parse-string (String. (Base64/decodeBase64 (.getBytes signed-instance))))))

(defn check-owner [instance]
  (let [parsed-instance (parse-instance instance)]
  (get parsed-instance "permissions")))

(defpage "/test" {:keys [instance]}
  (main-layout
    [:p (parse-instance instance)]))

(defpage "/settings" {:keys [instance]}
  ()

  )

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode mode
                        :ns 'noir-wixapps-example})))

(server/add-middleware wrap-params)
(server/add-middleware wrap-wixapps-middleware {:algorithm "HmacSHA256" :secret-key "d245bbf8-57eb-49d6-aeff-beff6d82cd39"})
