(ns webpush.utils
  (:require [clojure.string :as str])
  (:import nl.martijndwars.webpush.cli.commands.GenerateKeyCommand
           nl.martijndwars.webpush.cli.handlers.GenerateKeyHandler))

(defn parse-output [string]
  (let [[_ public _ private] (str/split-lines string)]
    {:public  public
     :private private}))

(defn generate-keys
  "Generate and print fresh public and private keys to stdout"
  []
  (parse-output
   (with-out-str
     (-> (GenerateKeyCommand.)
         (GenerateKeyHandler.)
         (.run)))))
