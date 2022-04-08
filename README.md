REPL driven development bug repro
=================================
This is a minimal test case of a bug where changing and re-evaluating a function in another namespace does not update if that function has an identical name to a function in the core namespace, and the calling function's definition hasn't been separately evaluated (i.e. only the whole buffer has been evaluated, once or more).

Reproduce with REPL connected editor
------------------------------------
Launch a REPL with what you need to eval things in your editor, for example
```
$ clj -Sdeps '{:deps {nrepl/nrepl {:mvn/version "0.9.0"} cider/cider-nrepl {:mvn/version "0.28.3"}}}'  -m nrepl.cmdline  --middleware '["cider.nrepl/cider-middleware"]' --interactive
```

Evaluate `src/repro.clj` in your REPL, and follow the commented instructions in that file.

Reproduce with non REPL editor
------------------------------
Paste to `clj` prompt

```
(require 'repro)
(repro/func-b)
```

 edit prints in repro2.clj and paste again:

```
(require 'repro2 :reload)
(repro/func-b)
(repro2/read)
(require 'repro :reload)
(repro/func-b)
```

Reproduce with just clj
-----------------------
```
(ns repro2)

(defn read []
   (prn "Called repro2/read 1"))

(defn read2 []
   (prn "Called repro2/read2 1"))

(ns repro
  (:require [repro2]))

(defn func-b []
  (prn "Called func-b: 1")
  (repro2/read)
  (repro2/read2))

(func-b)

(ns repro2)

(defn read []
   (prn "Called repro2/read 2"))

(defn read2 []
   (prn "Called repro2/read2 2"))

(ns repro)

(func-b)

(defn func-b []
  (prn "Called func-b: 2")
  (repro2/read)
  (repro2/read2))

(func-b)
```

Notes
-----

```
(require '[clojure.tools.namespace.repl :refer [refresh]])
(refresh)
```
does work, but has to be used after every change (i.e. it does not have a similar effect to just re-evaling the `func-b` definition).
