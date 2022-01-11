(defproject parse-n-sort "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [http-kit "2.5.3"] 
                 [metosin/reitit "0.5.15"]]
  :main ^:skip-aot parse-n-sort.core
  :target-path "target/%s"
  :aliases {"server" ["run" "-m" "parse-n-sort.server/start-server"]}
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
             :dev {:source-paths ["src" "dev"]}})
