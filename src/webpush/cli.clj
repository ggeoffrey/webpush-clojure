(ns webpush.cli
  (:require [webpush.core :refer [add-security-provider!]])
  (:import (nl.martijndwars.webpush.cli.handlers GenerateKeyHandler)
           (nl.martijndwars.webpush.cli.commands GenerateKeyCommand))
  (:gen-class))


(defn generate-keys!
  "Generate and print fresh public and private keys to stdout"
  []
  (-> (GenerateKeyCommand.)
      (GenerateKeyHandler.)
      (.run)))

(defn -main [& [command]]
  (add-security-provider!)
  (case command
    "generate-key" (generate-keys!)
    "send"
    nil))
