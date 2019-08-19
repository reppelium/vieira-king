(ns compiler.core
   (:require [compiler.config :refer :all])
  (:gen-class))

(defn -main
  [& args]
  (println "Hello, World!")
  (println (:special-words (get-possibles-token))))
