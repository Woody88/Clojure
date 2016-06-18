(ns clojure-spellchecker.core
  (:gen-class)
  (:require [clojure.string :as str])
  (:import (org.apache.commons.lang3 StringUtils)))

(def words 
  (set (map str/trim
        (str/split-lines (slurp "resources/wordsEn.txt")))))

(defn correct? [word]
  (contains? words word))

(defn distance [word1 word2]
  (StringUtils/getLevenshteinDistance word1 word2))

(defn min-distance [word]
  (apply min-key (partial distance word) words))

(defn -main
  "Receive a word and check if its an english word."
  [& args]
  (let [word (first args)]
    (if (correct? word)
      (println "correct")
      (println (str "Did you mean " (min-distance word) " ?")))))


