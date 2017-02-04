(ns korhal.macro.build-order
  (:require [korhal.interop.interop :refer [print-text]]))

(def double-rax-mnm
  [9 :supply-depot
   11 :send-scout
   11 :barracks
   13 :barracks
   15 :supply-depot
   15 :refinery])

(def triple-rax-mnm
  [9 :supply-depot
   11 :send-scout
   11 :barracks
   13 :barracks
   15 :supply-depot
   15 :refinery
   15 :engineering-bay
   15 :academy
   :upgrade :infantry-weapons])

(def triple-factory-vulture
  [9 :supply-depot
   11 :barracks
   12 :refinery
   13 :send-scout
   15 :supply-depot
   :train :marine
   18 :factory
   :train :marine
   20 :factory
   :train :marine
   :addon :machine-shop
   22 :supply-depot
   :train :vulture
   25 :wait
   :upgrade :ion-thrusters
   :addon :machine-shop
   25 :supply-depot
   25 :factory
   :research :spider-mines])

(def one-rax-fast-expand-marine-defense
  [9 :supply-depot
   11 :barracks
   12 :refinery
   13 :send-scout
   15 :supply-depot
   16 :wait
   :train :marine
   18 :factory
   19 :wait
   :train :marine
   21 :wait
   :train :marine
   :addon :machine-shop
   :train :marine
   24 :supply-depot
   26 :wait
   :train :siege-tank-tank-mode
   28 :wait
   :research :tank-siege-mode
   28 :command-center])

(def one-rax-fast-expand-zerg
  [8 :supply-depot
   11 :barracks
   11 :send-scout
   12 :wait
   :train :marine
   14 :command-center
   16 :supply-depot
   :train :marine
   22 :supply-depot
   23 :refinery
   25 :engineering-bay
   30 :academy
   :upgrade :infantry-weapons
   32 :supply-depot
   :barracks
   35 :barracks
   35 :research :stim-packs
   40 :barracks
   :barracks
   :supply-depot
   43 :supply-depot
   47 :upgrade :u-238-shells
  ])

(def test-order
  [9 :supply-depot
   11 :barracks
   12 :refinery
   15 :supply-depot
   15 :factory
   15 :factory
   :addon :machine-shop
   :research :tank-siege-mode
   :addon :machine-shop])

   (def test-order
     [9 :supply-depot
      11 :barracks
      12 :refinery
      15 :supply-depot
      15 :factory
      15 :factory
      :addon :machine-shop
      :research :tank-siege-mode
      :addon :machine-shop])

(def build-orders
  {:test-order test-order
   :double-rax-mnm double-rax-mnm
   :triple-rax-mnm triple-rax-mnm
   :triple-factory-vulture triple-factory-vulture
   :one-rax-fast-expand-marine-defense one-rax-fast-expand-marine-defense
   :one-rax-fast-expand-zerg one-rax-fast-expand-zerg})

(defn get-random-build-order []
  (let [k (nth (keys build-orders) (rand-int (count build-orders)))]
    (print-text (str "Build order: " (name k)))
    (build-orders k)))
