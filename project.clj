(defproject parse-n-sort "0.1.0-SNAPSHOT"
  :description "CLI app and REST api to parse and sort records"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [http-kit "2.5.3"] 
                 [metosin/reitit "0.5.15"]
                 [ring/ring-mock "0.4.0"]
                 [org.clojure/data.json "2.4.0"]]
  :main ^:skip-aot parse-n-sort.core
  :target-path "target/%s"
  :aliases {"server" ["run" "-m" "parse-n-sort.server/start-server"]}
  :profiles {:dev {:source-paths ["src" "dev"]}
             :uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
