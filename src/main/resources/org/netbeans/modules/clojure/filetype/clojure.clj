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

(ns ${project.name}.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
