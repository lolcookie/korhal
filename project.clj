(defproject korhal "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src"]
  :resource-paths ["lib/bwmirror_v2_5.jar"]
  :main korhal.core
  :aot [korhal.core]
  :jvm-opts [""]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/core.async "0.2.395"]
                 [org.clojure/tools.nrepl "0.2.12"]
                 [org.clojure/core.logic "0.8.11"]])
