(ns compiler.logic.lexicon
   (:require [compiler.config :refer :as config]))


(defn create-token
   [index name line-number]
   {:index index
   :name name
   :line line})

; @param list of lines
; @return a map with :tokens :errors
(defn execute-lexicon-verifier
   [list-of-lines]
   ; TODO pre-process the lines to create [{:contetn "asdasd" :line-number 1}]
   ; TODO maybe some day We will have a problem with something that start in one line and end in other
   (let [possibles-tokens config/get-possibles-token]
   (loop [index 0

         ]))
