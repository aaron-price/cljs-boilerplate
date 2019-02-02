# CLJS BOILERPLATE

This is a minimalistic template for getting up and running with the following:

- CLJS + Reagent
- TDD with green/red test results displaying in browser console
- Some simple functions I end up needing in **every** project, 
   e.g. includes?, obj->kmap, etc.
- Minimalistic anti-piracy

Dependencies:
React 16.7
reagent
clojure core.match
spectre


## Usage
```bash
git clone <url>
cd cljs-boilerplate
npm i
```

## repl
`shadow-cljs cljs-repl app`


## run dev mode
`shadow-cljs watch app`

Now open localhost:8080


## compile for production
`shadow-cljs release app`

Now open production/index.html

