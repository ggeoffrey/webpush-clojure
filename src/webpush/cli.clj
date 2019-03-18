(ns webpush.cli
  (:gen-class)
  (:require [webpush.utils :as utils]
            [webpush.core :as push]))

(defn init! []
  (push/add-security-provider!))


(defn -main [& args]
  (init!)
  (case (first args)
    "generate-key"      (let [{:keys [public private]} (utils/generate-keys)]
                          (println "Public:")
                          (println public)
                          (println "Private:")
                          (println private))
    nil))
