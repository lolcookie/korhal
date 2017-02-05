(ns korhal.macro.command
  (:require [korhal.interop.interop :refer :all]
            [korhal.macro.state :refer [macro-state macro-tag-unit! get-macro-tag pop-build-order!]]
            [korhal.micro.state :refer [micro-tag-unit!]]
            [korhal.tools.contract :refer [contract-build contract-build-addon
                                           contract-train contract-upgrade
                                           contract-research clear-contracts
                                           cancel-contracts can-build?
                                           can-afford? contracted-kw?]]))

(defn- scv-available? [scv]
  (and (:available (get-macro-tag scv))
       (completed? scv)))

(defn assign-spare-scv!
  "Get an available SCV from a mineral line and assign it a macro
  tag. If a building is supplied, return the nearest available SCV to
  that building."
  ([tag]
     (let [scv (first (filter scv-available? (my-scvs)))]
       (when scv
         (macro-tag-unit! scv tag)
         scv)))
  ([building tag]
     (let [available-scvs (filter scv-available? (my-scvs))]
       (when (seq available-scvs)
         (let [scv (apply min-key (partial dist-tile building) available-scvs)]
           (macro-tag-unit! scv tag)
           scv)))))

(defn can-build-now? [b]
  (and (zero? (training-queue-size b)) (completed? b)))

(defn find-build-location [build-kw]
  (let [cc (first (my-command-centers))]
    (loop [attempt 0]
      (when-not (>= attempt 5)
        (let [tx (+ (tile-x cc) (* (Math/pow -1 (rand-int 2)) (rand-int 15)))
              ty (+ (tile-y cc) (* (Math/pow -1 (rand-int 2)) (rand-int 15)))]
          (if (can-build? tx ty build-kw true)
            [tx ty]
            (recur (inc attempt))))))))

(defn nearest-expansion []
  (let [not-my-base? (fn [base] (> (ground-distance (first (my-command-centers)) base) 5.0))
        bases (filter not-my-base? (base-locations))
        nearest-base (apply min-key (partial ground-distance (first (my-command-centers))) bases)]
    (when nearest-base nearest-base)))

(defn expand
  ([] (expand false))
  ([pop?]
     (let [expo (nearest-expansion)
           tx (tile-x expo)
           ty (tile-y expo)]
       (when (and tx ty (can-afford? :command-center))
         (let [builder (assign-spare-scv! expo {:role :build :retry 0 :jitter false :args [tx ty :command-center]})]
           (cancel-contracts builder)
           (micro-tag-unit! builder nil)
           (contract-build builder tx ty :command-center)
           (when pop? (pop-build-order!)))))))

(defn build-refinery
  ([] (build-refinery false))
  ([pop?]
     (let [cc (first (my-command-centers))
           closest-geyser (apply min-key (partial dist cc) (geysers))]
       (when (and closest-geyser (can-afford? :refinery))
         (let [tx (tile-x closest-geyser)
               ty (tile-y closest-geyser)
               builder (assign-spare-scv! {:role :build :retry 0 :jitter false :args [tx ty :refinery]})]
           (cancel-contracts builder)
           (micro-tag-unit! builder nil)
           (contract-build builder tx ty :refinery)
           (when pop? (pop-build-order!)))))))

(defn build-kw
  ([kw] (build-kw kw false))
  ([kw pop?]
     (when (can-afford? kw)
       (when-let [[tx ty] (find-build-location kw)]
         (let [builder (assign-spare-scv! {:role :build :retry 0 :jitter true :args [tx ty kw]})]
           (cancel-contracts builder)
           (micro-tag-unit! builder nil)
           (contract-build builder tx ty kw)
           (when pop? (pop-build-order!)))))))

(defn addon-kw [kw]
  (let [unit-type (get-unit-type (kw unit-type-kws))
        what-builds (what-build-id unit-type)
        my-builders (my-buildings-id what-builds)
        builder (first (filter (every-pred can-build-now? (complement has-addon?)) my-builders))]
    (when (and builder (can-afford? unit-type))
      (contract-build-addon builder kw)
      (pop-build-order!))))

(defn train-kw [kw]
  (let [unit-type (get-unit-type (kw unit-type-kws))
        what-builds (what-build-id unit-type)
        my-builders (my-buildings-id what-builds)
        builder (first (filter can-build-now? my-builders))]
    (when (and builder (can-afford? unit-type))
      (contract-train builder kw)
      (pop-build-order!))))

(defn research-kw [kw]
  (let [tech-type (get-tech-type (kw tech-type-kws))
        what-builds (what-researches tech-type)
        my-builders (my-buildings-id (get-id what-builds))
        builder (first (filter can-build-now? my-builders))]
    (when (and builder (can-afford? tech-type))
      (contract-research builder kw)
      (pop-build-order!))))

(defn upgrade-kw [kw]
  (let [upgrade-type (get-upgrade-type (kw upgrade-type-kws))
        what-builds (what-upgrades upgrade-type)
        my-builders (my-buildings-id (get-id what-builds))
        builder (first (filter can-build-now? my-builders))]
    (when (and builder (can-afford? upgrade-type))
      (contract-upgrade builder kw)
      (pop-build-order!))))

(defn num-buildings-of-kw
  ([kw] (num-buildings-of-kw kw false))
  ([kw include-contracted?]
   (let [sum (count (my-buildings-kw kw))]
     (if include-contracted?
       (+ (count (contracted-kw? kw)) sum)
       sum))))
