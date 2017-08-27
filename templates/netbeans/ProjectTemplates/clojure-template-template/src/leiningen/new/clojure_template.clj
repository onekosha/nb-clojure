(ns leiningen.new.clojure-template
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "clojure-template"))

(defn clojure-template
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' clojure-template project.")
    (->files data
             ["src/{{sanitized}}/foo.clj" (render "foo.clj" data)])))
