# noir-wixapps-example

A example application using the ring-wixapps-middleware.

The app shows how to integrate the middleware and two functions:

parse-instance: get the SecureIntance from the instance parameter

check-owner: check if the current user is the instance owner, useful to authenticate the settings endpoint.

## Usage

```bash
lein deps
lein run
```

## License

Copyright (C) 2012 Dimitri Krassovski, Wix.

Distributed under the Eclipse Public License, the same as Clojure.

