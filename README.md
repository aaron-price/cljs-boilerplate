# CLJS BOILERPLATE

This is a minimalistic template for getting up and running with the following:

- CLJS + Reagent
- TDD with red/green test results displaying in browser console
- Some simple functions I end up needing in **every** project like includes?, obj->kmap, etc.
- Minimalistic anti-piracy

## Setup
```bash
git clone https://github.com/aaron-price/cljs-boilerplate.git
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

Now open out-release/index.html

