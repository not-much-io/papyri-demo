(defproject papyri-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/clj" "src/cljs" "src/cljc"]

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring-server "0.4.0"]
                 [ring "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-json "0.4.0"]
                 [prone "0.8.2"]
                 [compojure "1.4.0"]
                 [hiccup "1.0.5"]
                 [environ "1.0.0"]
                 [org.clojure/java.jdbc "0.4.1"]
                 [postgresql "9.3-1102.jdbc41"]
                 [yesql "0.4.2"]
                 [org.clojure/clojurescript "1.7.48" :scope "provided"]
                 [cljsjs/react "0.13.3-1"]
                 [reagent "0.5.0"]
                 [reagent-forms "0.5.5"]
                 [reagent-utils "0.1.5"]
                 [cljsjs/material "1.0.4-0"]
                 [secretary "1.2.3"]
                 [cljs-ajax "0.3.14"]
                 [prismatic/schema "1.0.1"]]

  :plugins [[lein-environ "1.0.0"]
            [lein-asset-minifier "0.2.2"]]

  :ring {:handler papyri-demo.handler/app
         :uberwar-name "papyri-demo.war"}

  :min-lein-version "2.5.0"

  :uberjar-name "papyri-demo.jar"

  :main papyri-demo.server

  :clean-targets ^{:protect false} [:target-path
                                    [:cljsbuild :builds :app :compiler :output-dir]
                                    [:cljsbuild :builds :app :compiler :output-to]]

  :minify-assets
  {:assets
    {"resources/public/css/site.min.css" "resources/public/css/site.css"}}

  :cljsbuild {:builds {:app {:source-paths ["src/cljs"]
                             :compiler {:output-to     "resources/public/js/app.js"
                                        :output-dir    "resources/public/js/out"
                                        :asset-path   "js/out"
                                        :optimizations :none
                                        :pretty-print  true}}}}

  :profiles {:dev     {:repl-options {:init-ns          papyri-demo.repl
                                      :nrepl-middleware []}

                       :dependencies [[ring/ring-mock "0.2.0"]
                                      [ring/ring-devel "1.4.0"]
                                      [lein-figwheel "0.3.7"]
                                      [org.clojure/tools.nrepl "0.2.10"]
                                      [pjstadig/humane-test-output "0.7.0"]]

                       :source-paths ["env/dev/clj"]
                       :plugins      [[lein-figwheel "0.3.7"]
                                      [lein-cljsbuild "1.0.6"]
                                      [com.cemerick/clojurescript.test "0.3.3"]]

                       :injections   [(require 'pjstadig.humane-test-output)
                                      (pjstadig.humane-test-output/activate!)]

                       :figwheel     {:http-server-root "public"
                                      :server-port      3449
                                      :nrepl-port       7002
                                      :css-dirs         ["resources/public/css"]
                                      :ring-handler     papyri-demo.handler/app
                                      :websocket-host   "82.131.30.116"} ;;not working!

                       :env          {:dev true}

                       :cljsbuild    {:builds        {:app      {:source-paths ["env/dev/cljs"]
                                                                 :compiler     {:main       "papyri-demo.dev"
                                                                                :source-map true}
                                                                 :figwheel     {:websocket-host "82.131.30.116"}} ;;not working!
                                                      :test     {:source-paths ["src/cljs" "test/cljs" "src/cljc"]
                                                                 :compiler     {:output-to     "target/test.js"
                                                                                :optimizations :whitespace
                                                                                :pretty-print  true}
                                                                 :figwheel     {:websocket-host "82.131.30.116"}}} ;;not working!
                                      :test-commands {"unit" ["phantomjs" :runner
                                                              "test/vendor/es5-shim.js"
                                                              "test/vendor/es5-sham.js"
                                                              "test/vendor/console-polyfill.js"
                                                              "target/test.js"]}}}

             :uberjar {:hooks       [leiningen.cljsbuild minify-assets.plugin/hooks]
                       :env         {:production true}
                       :aot         :all
                       :omit-source true
                       :cljsbuild   {:jar    true
                                     :builds {:app
                                              {:source-paths ["env/prod/cljs"]
                                               :compiler
                                                             {:optimizations :advanced
                                                              :pretty-print  false}}}}}})
