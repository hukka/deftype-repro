REPL driven development bug repro
=================================
This is a minimalish test case of a [https://polylith.gitbook.io/polylith] style project setup. The bug is that changing and re-evaluating a function in a component does not update if that function has an identical name to a function in the core namespace, and the calling function is not evaluated again.

To reproduce
Launch a REPL with the `dev` alias and what you need to eval things in your editor, for example
```
$ clj -A:dev -Sdeps '{:deps {nrepl/nrepl {:mvn/version "0.9.0"} cider/cider-nrepl {:mvn/version "0.28.3"}}}'  -m nrepl.cmdline  --middleware '["cider.nrepl/cider-middleware"]' --interactive
```

Evaluate `bases/base1/src/repro.clj` in your REPL, and follow the commented instructions in that file.
