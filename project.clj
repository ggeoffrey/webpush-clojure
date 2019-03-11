(defproject webpush "0.1.0-SNAPSHOT"
  :description "Web Push library for Clojure"
  :url "https://github.com/ggeoffrey/webpush-clojure"
  :license {:name "MIT"
            :url  "https://github.com/ggeoffrey/webpush-clojure/blob/master/LICENSE"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [nl.martijndwars/web-push "5.0.1"]
                 [org.bouncycastle/bcprov-jdk15on "1.61"]]
  :repl-options {:init-ns webpush.core})
