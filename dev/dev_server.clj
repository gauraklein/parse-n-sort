(ns dev-server
  (:require
   [org.httpkit.server :refer [run-server]]
   [parse-n-sort.server :refer [server]]))

(defonce dev-server (atom nil))

(defn stop-server []  
  (when-not (nil? @dev-server)
    (println "Stopping server")
    (@dev-server :timeout 100)
    (reset! dev-server nil)))

(defn start-server []
  (println "starting server...")
  (reset! dev-server (run-server server {:port 4000})))

(defn restart-server []
  (stop-server)
  (start-server))

(comment
  (stop-server)
  (restart-server)
  (start-server)
  )