(comment
;*******************************************************************************
<#assign licenseFirst = ";*">
<#assign licensePrefix = ";* ">
<#assign licenseLast = ";*">
<#include "../Licenses/license-${project.license}.txt">

;*******************************************************************************
;* author ${user}
;*******************************************************************************
)

(defproject ${project.name} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot my-stuff.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
