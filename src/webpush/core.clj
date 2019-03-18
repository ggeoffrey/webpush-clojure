(ns webpush.core
  (:import java.security.Security
           (org.bouncycastle.jce.provider BouncyCastleProvider)
           (nl.martijndwars.webpush Notification PushService
                                    Subscription Subscription$Keys)))

(defn add-security-provider! []
  (Security/addProvider (BouncyCastleProvider.)))

(defn subscription
  ([subscription-map]
   (let [{:keys [endpoint keys]} subscription-map
         {:keys [p256dh auth]}   keys]
     (subscription endpoint p256dh auth)))
  ([endpoint p256dh auth]
   (Subscription. endpoint (Subscription$Keys. p256dh auth))))

(defn service
  ([gcm-api-key]
   (PushService. gcm-api-key))
  ([key-pair subject]
   (PushService. key-pair subject))
  ([public-key private-key subject]
   (PushService. public-key private-key subject)))

(defn notification [subscription payload]
  (Notification. subscription payload))

(defn send [push-service notification]
  (.send push-service notification))

(defn send-async [push-service notification]
  (.sendAsync push-service notification))

(comment
  (add-security-provider!)
  (let [sub   (subscription {:endpoint "https://random-push-service.com/some-kind-of-unique-id-1234/v2/"
                             :keys     {:key  "BNcRdreALRFXTkOOUHK1EtK2wtaz5Ry4YfYCA_0QTpQtUbVlUls0VJXg7A8u-Ts1XbjhazAkj7I99e8QcYP7DkM="
                                        :auth "tBHItJI5svbpez7KI4CCXg=="}})
        svc   (service)
        notif (notification sub "hello")]
    (send push-service notif)))
