(ns webpush.utils
  (:import java.security.KeyPairGenerator
           [nl.martijndwars.webpush Base64Encoder Utils]
           org.bouncycastle.jce.ECNamedCurveTable
           org.bouncycastle.jce.provider.BouncyCastleProvider))

(defn generate-key-pair []
  (-> (KeyPairGenerator/getInstance Utils/ALGORITHM BouncyCastleProvider/PROVIDER_NAME)
      (doto (.initialize (ECNamedCurveTable/getParameterSpec Utils/CURVE)))
      (.generateKeyPair)))

(defn encode [key]
  (Base64Encoder/encodeUrl (Utils/encode key)))

(defn generate-keys
  "Generate public and private keys"
  []
  (let [key-pair (generate-key-pair)]
    {:public  (encode (.getPublic key-pair))
     :private (encode (.getPrivate key-pair))}))
