[![Clojars Project](https://img.shields.io/clojars/v/webpush.svg)](https://clojars.org/webpush)

# webpush-clojure

Wrapping [Webpush-java](https://github.com/web-push-libs/webpush-java). Only provide a convenient clojure api.

**NOT PRODUCTION READY** Webpush-java is production ready but this clojure
wrapper has not been tested yet. It will be in a few days/weeks.

Supports:
- payloads,
- VAPID,
- asynchrounous sending.

Do not support:
- [urgency](https://github.com/web-push-libs/webpush-java/issues/29)

## Usage

### Data

Your backend receive subscriptions shaped like this:
```clojure
{:endpoint "https://random-push-service.com/some-kind-of-unique-id-1234/v2/"
 :keys     {:p256dh "BNcRdreALRFXTkOOUHK1EtK2wtaz5Ry4YfYCA_0QTpQtUbVlUls0VJXg7A8u-Ts1XbjhazAkj7I99e8QcYP7DkM="
            :auth   "tBHItJI5svbpez7KI4CCXg=="}}
```

### How to send a notification:
```clojure
(require '[webpush.core :refer [subscription push-service notification send!]])

(let [;; Create a subscription
      sub (subscription <endpoint> <p256dh> <auth>)
      ;; Create a push-service instance by passing your `public-key`,
      ;; `private-key` (as strings), and the webpush `subject` (most of the time
      ;; your email)
      svc (service <public-key> <private-key> <subject>)]
  (send! svc (notification sub "Hello World!")))

;; The webpush protocol force payloads encryption using public/private keypairs.
;; On the JVM it uses some algorithms provided by BouncyCastle. If you get any
;; error relative to crypto, you can enable them by calling:
(webpush.core/add-security-provider!)
```

### Generating keypairs

```clojure
(webpush.utils/generate-keys)
;; => {:public "BEWi1tvrrkZhe_FJjhGbVvLDmXrQMhMr27aJ9UGJdCSAdRp8tVciDbNqBMLlmb2ZctcZvfY2tEvQIlSRo7QqVkw=",
;;     :private "AMbTUIvdMwwboLSxWMNoRS6XaJuXmX1reHrkNBdiJBwq"}
```

### CLI

`Webpush-java` allow users to generate keys or send a notification as a shell
command (using gradle). If you have any use case where shell commands might be a
better fit then a REPL please open an issue.

## Contributing

This library should stay so small that all contributions should go to [Webpush-java](https://github.com/web-push-libs/webpush-java) IMHO, but all PRs are welcome.

## TODO

- Add cli commands
  - add `generate-key`
  - add `send-notification`
- add missing constructor arities (when it make sense in Clojure)
- add documentation
- add better examples

