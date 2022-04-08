REPL driven development bug repro
=================================
This is a minimal test case of a bug where changing and re-evaluating a function in another namespace does not update if that function has an identical name to a function in the core namespace, and the calling function's definition hasn't been separately evaluated (i.e. only the whole buffer has been evaluated, once or more).

To reproduce
Launch a REPL with the `dev` alias and what you need to eval things in your editor, for example
```
$ clj -A:dev -Sdeps '{:deps {nrepl/nrepl {:mvn/version "0.9.0"} cider/cider-nrepl {:mvn/version "0.28.3"}}}'  -m nrepl.cmdline  --middleware '["cider.nrepl/cider-middleware"]' --interactive
```

Evaluate `src/repro.clj` in your REPL, and follow the commented instructions in that file.
