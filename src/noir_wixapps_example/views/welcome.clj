(ns noir-wixapps-example.views.welcome
  (:require [noir-wixapps-example.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to noir-wixapps-example"]))
