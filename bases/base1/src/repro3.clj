(ns repro3
  (:require
   [clojure.tools.logging :as log]))

(defn read []
  ; Increment this log message and re-evaluate to keep track of versions
   (log/info "Called repro3/read 1"))

