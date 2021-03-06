;; Freecoin - digital social currency toolkit

;; part of Decentralized Citizen Engagement Technologies (D-CENT)
;; R&D funded by the European Commission (FP7/CAPS 610349)

;; Copyright (C) 2015 Dyne.org foundation
;; Copyright (C) 2015 Thoughtworks, Inc.

;; Sourcecode designed, written and maintained by
;; Denis Roio <jaromil@dyne.org>

;; With contributions by
;; Duncan Mortimer <dmortime@thoughtworks.com>

;; This program is free software: you can redistribute it and/or modify
;; it under the terms of the GNU Affero General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU Affero General Public License for more details.

;; You should have received a copy of the GNU Affero General Public License
;; along with this program.  If not, see <http://www.gnu.org/licenses/>.

(ns freecoin.views.participants-list
  (:require [freecoin.routes :as routes]
            [clavatar.core :as clavatar]))

(defn render-participant [wallet]
  [:li {:style "margin: 1em" :class "clj--participant__item"}
   [:div {:class "card pull-left" }
    [:span (str "name: " (:name wallet))]
    [:br]
    [:span (str "email: " (:email wallet))]
    [:br]
    [:span {:class "qrcode pull-left"}
     [:img {:src (routes/path :qrcode :uid (:uid wallet))} ]]
    [:span {:class "gravatar pull-right"}
     [:img {:src (clavatar/gravatar (:email wallet) :size 87 :default :mm)}]]]])

(defn participants-list [content]
  (let [wallets (:wallets content)]
    {:body [:div
            (if (empty? wallets)
              [:span (str "No participant found")]
              [:ul {:style "list-style-type: none;"}
               (for [wallet wallets]
                 (render-participant wallet))])]
     :title "Participants"}))
