(ns korhal.interop.interop
  (:require [clojure.set :refer [map-invert]]
            [korhal.interop.interop-types :refer [unit-types upgrade-types tech-types
                                                  unit-command-types race-types unit-size-types
                                                  weapon-types bullet-types damage-types
                                                  explosion-types order-types
                                                  unit-type-fn-maps unit-fn-maps
                                                  base-location-fn-maps player-fn-maps]])
  (:import (bwta BaseLocation BWTA Chokepoint Polygon Region)
           (bwapi.UnitType)
           (bwapi.UpgradeType)
           (bwapi.TechType)
           (bwapi.UnitCommandType)
           (bwapi.RaceType)
           (bwapi.UnitSizeType)
           (bwapi.WeaponType)
           (bwapi.BulletType)
           (bwapi.DamageType)
           (bwapi.ExplosionType)
           (bwapi.OrderType)
           (java.awt.Point)))

(declare get-unit-type pixel-x pixel-y tile-x tile-y start-location? can-build-here? get-type-id)

(def api nil)
(defn bind-api! [binding] (alter-var-root #'api #(identity %2) binding))

(defn dynamic-dot-form [instance method] `(. ~instance ~method))

;; type conversions

(defn java-point [obj grid-type]
  (case grid-type
    :pixel (java.awt.Point. (pixel-x obj) (pixel-y obj))
    :tile (java.awt.Point. (tile-x obj) (tile-y obj))))

;; type definitions

;; unit type kw lookup is a special case to add in the minerals and geysers
(def unit-type-kws
  (merge {:mineral bwapi.UnitType/Resource_Mineral_Field
          :geyser bwapi.UnitType/Resource_Vespene_Geyser}
         (zipmap (map keyword (take-nth 2 unit-types))
                 (map #(eval `(. bwapi.UnitType ~%)) (take-nth 2 (rest unit-types))))))

(defmacro gen-type-ids-map [inject-sym java-type coll]
  `(def ~inject-sym
     (->> (map #(vector (eval `(.getID ~(symbol (str ~java-type "/" %))))
                        (eval (symbol (str ~java-type "/" %))))
               (take-nth 2 (rest ~coll)))
          (flatten)
          (apply hash-map))))

(defmacro gen-type-kw-map [inject-sym java-type coll]
  `(def ~inject-sym
     (zipmap (map keyword (take-nth 2 ~coll))
             (map #(eval `(. ~~java-type ~%)) (take-nth 2 (rest ~coll))))))

(gen-type-ids-map unit-type-ids 'bwapi.UnitType unit-types)
(gen-type-ids-map upgrade-type-ids 'bwapi.UpgradeType upgrade-types)
(gen-type-ids-map tech-type-ids 'bwapi.TechType tech-types)
(gen-type-ids-map unit-command-type-ids 'bwapi.UnitCommandType unit-command-types)
(gen-type-ids-map race-type-ids 'bwapi.RaceType race-types)
(gen-type-ids-map unit-size-type-ids 'bwapi.UnitSizeType unit-size-types)
(gen-type-ids-map weapon-type-ids 'bwapi.WeaponType weapon-types)
(gen-type-ids-map bullet-type-ids 'bwapi.BulletType bullet-types)
(gen-type-ids-map damage-type-ids 'bwapi.DamageType damage-types)
(gen-type-ids-map explosion-type-ids 'bwapi.ExplosionType explosion-types)
(gen-type-ids-map order-type-ids 'bwapi.OrderType order-types)

(gen-type-kw-map upgrade-type-kws 'bwapi.UpgradeType upgrade-types)
(gen-type-kw-map tech-type-kws 'bwapi.TechType tech-types)
(gen-type-kw-map unit-command-type-kws 'bwapi.UnitCommandType unit-command-types)
(gen-type-kw-map race-type-kws 'bwapi.RaceType race-types)
(gen-type-kw-map unit-size-type-kws 'bwapi.UnitSizeType unit-size-types)
(gen-type-kw-map weapon-type-kws 'bwapi.WeaponType weapon-types)
(gen-type-kw-map bullet-type-kws 'bwapi.BulletType bullet-types)
(gen-type-kw-map damage-type-kws 'bwapi.DamageType damage-types)
(gen-type-kw-map explosion-type-kws 'bwapi.ExplosionType explosion-types)
(gen-type-kw-map order-type-kws 'bwapi.OrderType order-types)

;; common calls to get state vars and collections

(defn get-self [] (.getSelf api))

(defn my-minerals [] (.. api getSelf getMinerals))

(defn my-gas [] (.. api getSelf getGas))

(defn my-units [] (.getMyUnits api))

(defn enemy-units [] (.getEnemyUnits api))

(defn neutral-units [] (.getNeutralUnits api))

(defn bullets [] (.getAllBullets api))

(defn minerals []
  (filter #(= (.getTypeID %) (.getID bwapi.UnitType/Resource_Mineral_Field))
          (.getNeutralUnits api)))

(defn geysers []
  (filter #(= (.getTypeID %) (.getID bwapi.UnitType/Resource_Vespene_Geyser))
          (.getNeutralUnits api)))

;; map data

(defn get-map [] (.getMap api))

(defn load-map-data [boolean] (.loadMapData api boolean))

(defn map-name [] (.. api getMap getName))

(defn map-filename [] (.. api getMap getFileName))

(defn map-hash [] (.. api getMap getHash))

(defn map-width
  ([] (map-width :pixel))
  ([type]
     (case type
       :pixel (* 32 (.. api getMap getWidth))
       :tile (.. api getMap getWidth))))

(defn map-height
  ([] (map-height :pixel))
  ([type]
     (case type
       :pixel (* 32 (.. api getMap getHeight))
       :tile (.. api getMap getHeight))))

(defn map-tile-height [tx ty] (.getHeight (.getMap api) tx ty))

(defn map-walk-width [] (.. api getMap getWalkWidth))

(defn map-walk-height [] (.. api getMap getWalkHeight))

(defn region-at-tile
  ([point] (region-at-tile (.getX point) (.getY point)))
  ([tx ty] (.. api getMap getRegion tx ty)))

(defn walkable?
  ([point] (walkable? (.getX point) (.getY point)))
  ([wx wy] (.isWalkable (.getMap api) wx wy)))

(defn low-res-walkable?
  ([point] (low-res-walkable? (.getX point) (.getY point)))
  ([tx ty] (.isLowResWalkable (.getMap api) tx ty)))

(defn regions [] (.. api getMap getRegions))

(defn chokepoints [] (.. api getMap getChokePoints))

(defn base-locations [] (.. api getMap getBaseLocations))

(defn my-start-location [] (.. api getSelf getStartLocation))

(defn ground-distance
  ([p1 p2] (ground-distance (tile-x p1) (tile-y p1) (tile-x p2) (tile-y p2)))
  ([tx1 ty1 tx2 ty2] (.getGroundDistance (.getMap api) tx1 ty1 tx2 ty2)))

(defn connected?
  ([p1 p2] (connected? (.getX p1) (.getY p1) (.getX p2) (.getY p2)))
  ([tx1 ty1 tx2 ty2] (.. api getMap isConnected tx1 ty1 tx2 ty2)))

(defn enemy-start-locations []
  (let [bases (base-locations)
        enemy-base? (fn [base] (and (not= (java-point base :tile) (my-start-location))
                                    (start-location? base)))]
    (filter enemy-base? bases)))

;; chokepoint methods

(defn center-x [choke] (.getCenterX choke))

(defn center-y [choke] (.getCenterY choke))

;; generate player methods

(defmacro define-player-fns []
  (cons `do
        (for [[clj-name java-name] (partition 2 player-fn-maps)]
          `(defn ~clj-name [player#] (. player# ~java-name)))))

(define-player-fns)

(defn researched?
  ([tech-kw] (.hasResearched (.getSelf api) (.getID (tech-type-kws tech-kw))))
  ([player tech] (.hasResearched player (.getID tech))))

(defn researching?
  ([tech-kw] (.isResearching (.getSelf api) (.getID (tech-type-kws tech-kw))))
  ([player tech] (.isResearching player (.getID tech))))

(defn upgrade-level
  ([upgrade-kw] (.upgradeLevel (.getSelf api) (.getID (upgrade-type-kws upgrade-kw))))
  ([player upgrade] (.upgradeLevel player (.getID upgrade))))

(defn upgrading?
  ([upgrade-kw] (.isUpgrading (.getSelf api) (.getID (upgrade-type-kws upgrade-kw))))
  ([player upgrade] (.isUpgrading player (.getID upgrade))))

;; generate base location methods

(defmacro define-base-location-fns []
  (cons `do
        (for [[clj-name java-name] (partition 2 base-location-fn-maps)]
          `(defn ~clj-name [loc#] (. loc# ~java-name)))))

(define-base-location-fns)

;; generate single unit functions

(defmacro define-unit-type-fns []
  (cons `do
        (for [[clj-name java-name] (partition 2 unit-type-fn-maps)]
          `(defn ~clj-name [unit-or-unit-type#]
               (. (get-unit-type unit-or-unit-type#) ~java-name)))))

(define-unit-type-fns)

(defmacro define-unit-fns []
  (cons `do
        (for [[clj-name java-name] (partition 2 unit-fn-maps)]
          `(defn ~clj-name [unit#] (. unit# ~java-name)))))

(define-unit-fns)

;; supply functions are dumb and return double the supply to accommodate
;; 0.5 supply zerglings while still using an int type
;; Clojure isn't statically typed, so we don't have to put up with that shit

(defn my-supply-used [] (/ (.. api getSelf getSupplyUsed) 2))

(defn my-supply-total [] (/ (.. api getSelf getSupplyTotal) 2))

(defn supply-provided [unit]
  (/ (.getSupplyProvided (get-unit-type unit)) 2))

;; common API commands shared among multiple types

(defn get-id [obj] (.getID obj))

(defn get-unit-type [unit-or-unit-type]
  (if (instance? Unit unit-or-unit-type)
    (.getUnitType api (.getTypeID unit-or-unit-type))
    (.getUnitType api (.getID unit-or-unit-type))))

(defn get-unit-type-kw [unit-or-unit-type]
  (let [unit-type (get-unit-type unit-or-unit-type)
        static-type (unit-type-ids (get-id unit-type))]
    ((map-invert unit-type-kws) static-type)))

(defn get-tech-type [tech] (.getTechType api (.getID tech)))

(defn get-upgrade-type [upgrade] (.getUpgradeType api (.getID upgrade)))

(defn get-weapon-type [weapon-or-id]
  (.getWeaponType api (if (number? weapon-or-id)
                        weapon-or-id
                        (.getID weapon-or-id))))

(defn get-damage-type [damage-or-id]
  (.getDamageType api
                  (if (number? damage-or-id)
                    damage-or-id
                    (.getID damage-or-id))))

(defn get-explosion-type [explosion-or-id]
  (.getExplosionType api
                     (if (number? explosion-or-id)
                       explosion-or-id
                       (.getID explosion-or-id))))

(defn get-type-id [obj-or-unit-type]
  (if (or (instance? Unit obj-or-unit-type) (instance? Bullet obj-or-unit-type))
    (.getTypeID obj-or-unit-type)
    (.getID obj-or-unit-type)))

(defn pixel-x [obj] (.getX obj))

(defn pixel-y [obj] (.getY obj))

(defn tile-x [obj]
  (if (instance? BaseLocation obj)
    (.getTx obj)
    (.getTileX obj)))

(defn tile-y [obj]
  (if (instance? BaseLocation obj)
    (.getTy obj)
    (.getTileY obj)))

(defn walk-x [obj] (Math/floor (/ (pixel-x obj) 8)))

(defn walk-y [obj] (Math/floor (/ (pixel-y obj) 8)))

(defn mineral-price [obj]
  (cond
   (instance? bwapi.UnitType obj) (.getMineralPrice obj)
   (instance? bwapi.TechType obj) (.getMineralPrice obj)
   (instance? bwapi.UpgradeType obj) (+ (.getMineralPriceBase obj)
                                                 (* (upgrade-level (get-self) obj) (.getMineralPriceFactor obj)))))

(defn gas-price [obj]
  (cond
   (instance? bwapi.UnitType obj) (.getGasPrice obj)
   (instance? bwapi.TechType obj) (.getGasPrice obj)
   (instance? bwapi.UpgradeType obj) (+ (.getGasPriceBase obj)
                                                 (* (upgrade-level (get-self) obj) (.getGasPriceFactor obj)))))

(defn supply-required [obj]
  (cond
   (instance? bwapi.UnitType obj) (/ (.getSupplyRequired obj) 2)
   (instance? bwapi.TechType obj) 0
   (instance? bwapi.UpgradeType obj) 0))

(defn my-unit? [unit]
  (= (get-id (get-self)) (player-id unit)))

;; type predicates, e.g. is-drone?
(doseq [[n t] (partition 2 unit-types)]
  (let [class-type (eval `(.getID ~(symbol (str "bwapi.UnitType/" t))))]
    (intern *ns*
            (symbol (str "is-" n "?"))
            (fn [unit] (= (.getTypeID unit) class-type)))))

;; own unit type collections, e.g. my-drones
(defn plural [n]
  (let [n-str (str n)
        processed-str (cond (re-find #"[^a]y$" n-str) (str (clojure.string/join (butlast n-str)) "ies")
                            (re-find #"[s]$" n-str) n-str
                            (re-find #"larva$" n-str) (str n-str "e")
                            :else (str n-str "s"))]
    (if (symbol? n) (symbol processed-str) processed-str)))

(doseq [[n t] (partition 2 unit-types)]
  (when-not (re-seq #"^Critter" (str t))
    (let [type-predicate (eval (symbol (str *ns* "/is-" n "?")))]
      (intern *ns*
              (symbol (str "my-" (plural n)))
              (fn [] (filter type-predicate (.getMyUnits api)))))))

(def my-citadels-of-adun my-citadel-of-aduns)
(def my-nexuses my-nexus)

(defn worker? [unit]
  (or (is-scv? unit) (is-drone? unit) (is-probe? unit)))

(defn combat-unit? [unit]
  (and (not (worker? unit)) (not (building? unit))))

(defn health-perc [unit]
  (when (pos? (max-hit-points unit))
    (/ (hit-points unit) (max-hit-points unit))))

(defn get-unit-by-id [unit-id]
  (when (>= unit-id 0) (.getUnit api unit-id)))

(defn my-units-id [id] (filter #(= (.getTypeID %) id) (my-units)))

(defn my-units-kw [kw] (filter #(= (.getTypeID %) (.getID (kw unit-type-kws))) (my-units)))

(defn my-buildings [] (filter building? (my-units)))

(defn my-buildings-id [id] (filter #(= (.getTypeID %) id) (my-buildings)))

(defn my-buildings-kw [kw] (filter #(= (.getTypeID %) (.getID (kw unit-type-kws))) (my-buildings)))

;; API unit commands

(defn attack
  ([attacking-unit target-unit] (.attack api (.getID attacking-unit) (.getID target-unit)))
  ([attacking-unit px py] (.attack api (.getID attacking-unit) px py)))

(defn build
  ([builder point to-build] (build builder (.x point) (.y point) to-build))
  ([builder tx ty to-build] (.build api (.getID builder) tx ty
                                    (.getID (to-build unit-type-kws)))))

(defn build-addon [building to-build]
  (.buildAddon api (.getID building) (.getID (to-build unit-type-kws))))

(defn train [building to-train]
  (.train api (.getID building) (.getID (to-train unit-type-kws))))

(defn morph [unit morph-to]
  (.morph api (.getID unit) (.getID (morph-to unit-type-kws))))

(defn research [unit to-research]
  (.research api (.getID unit) (.getID (to-research tech-type-kws))))

(defn upgrade [unit to-upgrade]
  (.upgrade api (.getID unit) (.getID (to-upgrade upgrade-type-kws))))

(defn set-rally-point
  ([rally-unit target-unit-or-point]
     (cond
      (instance? java.awt.Point target-unit-or-point) (set-rally-point rally-unit
                                                                       (.x target-unit-or-point)
                                                                       (.y target-unit-or-point))
      :else (.setRallyPoint api (.getID rally-unit) (.getID target-unit-or-point))))
  ([rally-unit px py] (.setRallyPoint api (.getID rally-unit) px py)))

(defn move
  ([move-unit target-unit-or-point]
     (cond
      (instance? java.awt.Point target-unit-or-point) (move move-unit
                                                            (.x target-unit-or-point)
                                                            (.y target-unit-or-point))
      :else (.move api (.getID move-unit) (.getX target-unit-or-point) (.getY target-unit-or-point))))
  ([move-unit px py] (.move api (.getID move-unit) px py)))

(defn patrol
  ([patrol-unit target-unit-or-point]
     (cond
      (instance? java.awt.Point target-unit-or-point) (patrol patrol-unit
                                                              (.x target-unit-or-point)
                                                              (.y target-unit-or-point))
      :else (.patrol api (.getID patrol-unit) (.getX target-unit-or-point) (.getY target-unit-or-point))))
  ([patrol-unit px py] (.patrol api (.getID patrol-unit) px py)))

(defn hold-position [unit] (.holdPosition api (.getID unit)))

(defn stop [unit] (.stop api (.getID unit)))

(defn follow [follow-unit target-unit] (.follow api (.getID follow-unit) (.getID target-unit)))

(defn gather [gather-unit target-unit] (.gather api (.getID gather-unit) (.getID target-unit)))

(defn return-cargo [unit] (.returnCargo api unit))

(defn repair [repair-unit target-unit] (.repair api (.getID repair-unit) (.getID target-unit)))

(defn burrow [unit] (.burrow api (.getID unit)))

(defn unburrow [unit] (.unburrow api (.getID unit)))

(defn cloak [unit] (.cloak api (.getID unit)))

(defn decloak [unit] (.decloak api (.getID unit)))

(defn siege [unit] (.siege api (.getID unit)))

(defn unsiege [unit] (.unsiege api (.getID unit)))

(defn lift [unit] (.lift api (.getID unit)))

(defn land
  ([unit point] (land unit (.x point) (.y point)))
  ([unit tx ty] (.land api (.getID unit) tx ty)))

(defn load* [loading-unit target-unit] (.load api (.getID loading-unit) (.getID target-unit)))

(defn unload [unloading-unit target-unit] (.unload api (.getID unloading-unit) (.getID target-unit)))

(defn unload-all
  ([unit] (.unloadAll api (.getID unit)))
  ([unit point] (unload-all unit (.x point) (.y point)))
  ([unit tx ty] (.unloadAll api (.getID unit) tx ty)))

(defn right-click
  ([unit target-unit-or-point]
     (cond
      (instance? java.awt.Point target-unit-or-point) (right-click unit
                                                                   (.x target-unit-or-point)
                                                                   (.y target-unit-or-point))
      :else (.rightClick api (.getID unit) (.getID target-unit-or-point))))
  ([unit px py] (.rightClick api (.getID unit) px py)))

(defn halt-construction [unit] (.haltConstruction api (.getID unit)))

(defn cancel-construction [unit] (.cancelConstrution api (.getID unit)))

(defn cancel-addon [unit] (.cancelAddon api (.getID unit)))

(defn cancel-train
  ([unit] (.cancelTrain api (.getID unit) (dec (training-queue-size unit)))) ;; cancels last slot being used
  ([unit slot] (.cancelTrain api (.getID unit) slot)))

(defn cancel-morph [unit] (.cancelMorph api (.getID unit)))

(defn cancel-research [unit] (.cancelResearch api (.getID unit)))

(defn cancel-upgrade [unit] (.cancelUpgrade api (.getID unit)))

(defn use-tech
  ([unit tech] (.useTech api (.getID unit) (.getID tech)))
  ([unit tech target-unit-or-point]
     (cond
      (instance? java.awt.Point target-unit-or-point) (use-tech unit
                                                                tech
                                                                (.x target-unit-or-point)
                                                                (.y target-unit-or-point))
      :else (.useTech api (.getID unit) (.getID tech) (.getID target-unit-or-point))))
  ([unit tech px py] (.useTech api (.getID unit) (.getID tech) px py)))

(defn place-cop
  ([unit point] (place-cop unit (.x point) (.y point)))
  ([unit tx ty] (.placeCOP api (.getID unit) tx ty)))

;; API utility and drawing commands

(defn start [] (.start api))

(defn load-type-data [] (.loadTypeData api))

(defn replay-frame-total [] (.getReplayFrameTotal api))

(defn draw-health [boolean] (.drawHealth api boolean))

(defn draw-targets [boolean] (.drawTargets api boolean))

(defn draw-ids [boolean] (.drawIDs api boolean))

(defn enable-user-input [] (.enableUserInput api))

(defn enable-perfect-information [] (.enablePerfectInformation api))

(defn set-game-speed [speed] (.setGameSpeed api speed))

(defn frame-count [] (.getFrameCount api))

(defn set-frame-skip [frame-skip] (.setFrameSkip api frame-skip))

(defn leave-game [] (.leaveGame api))

(defn draw-box [left top right bottom color fill screen-coords]
  (.drawBox api left top right bottom color fill screen-coords))

(defn draw-circle [px py radius color fill screen-coords]
  (.drawCircle api px py radius color fill screen-coords))

(defn draw-line
  ([p1 p2 color screen-coords] (.drawLine api p1 p2 color screen-coords))
  ([x1 y1 x2 y2 color screen-coords] (.drawLine api x1 y1 x2 y2 color screen-coords)))

(defn draw-dot [px py color screen-coords]
  (.drawDot api px py color screen-coords))

(defn draw-text
  ([point msg screen-coords] (.drawText api point (str msg) screen-coords))
  ([px py msg screen-coords] (.drawText api px py (str msg) screen-coords)))

;; extended API commands

(defn supports-addon? [unit]
  (let [unit-type (get-unit-type unit)
        match-types (map (comp get-unit-type unit-type-kws)
                         [:command-center :factory :starport :science-facility])]
    (boolean (some #{unit-type} match-types))))

(defn has-addon? [unit]
  (and (building? unit) (not= -1 (addon-id unit))))

(defn tile-visible?
  ([point] (tile-visible? (.x point) (.y point)))
  ([tx ty] (.isVisible api tx ty)))

(defn tile-explored?
  ([point] (tile-explored? (.x point) (.y point)))
  ([tx ty] (.isExplored api tx ty)))

(defn tile-buildable?
  ([point include-buildings] (tile-buildable? (.x point) (.y point) include-buildings))
  ([tx ty include-buildings] (.isBuildable api tx ty include-buildings)))

(defn has-creep?
  ([point] (has-creep? (.x point) (.y point)))
  ([tx ty] (.hasCreep api tx ty)))

(defn- has-power?*
  ([tx ty] (.hasPower api tx ty))
  ([tx ty unit] (.hasPower api tx ty (get-type-id unit)))
  ([tx ty tile-width tile-height] (.hasPower api tx ty tile-width tile-height))
  ([tx ty tile-width tile-height unit] (.hasPower api tx ty tile-width tile-height (get-type-id unit))))

(defn has-power? [point-or-coord & rest-args]
  (cond
   (instance? java.awt.Point point-or-coord) (apply has-power?*
                                                    (.x point-or-coord)
                                                    (.y point-or-coord)
                                                    rest-args)
   :else (apply has-power?* point-or-coord rest)))

(defn has-power-precise?
  ([point] (has-power-precise? (.x point) (.y point)))
  ([px py] (.hasPowerPrecise api px py)))

(defn has-path?
  ([unit target-unit] (.hasPath api (.getID unit) (.getID target-unit)))
  ([unit to-x to-y] (.hasPath api (.getID unit) to-x to-y))
  ([from-x from-y to-x to-y] (.hasPath api from-x from-y to-x to-y)))

(defn has-loaded-unit? [unit maybe-loaded-unit]
  (.hasLoadedUnit api (.getID unit) (.getID maybe-loaded-unit)))

(defn can-build-here?
  ([tx ty unit-to-build check-explored] (.canBuildHere api tx ty (get-type-id unit-to-build) check-explored))
  ([unit tx ty unit-to-build check-explored] (.canBuildHere api (.getID unit) tx ty
                                                            (get-type-id unit-to-build) check-explored)))

(defn can-make?
  ([unit-to-make-kw] (.canMake api (get-type-id (unit-type-kws unit-to-make-kw))))
  ([unit unit-to-make-kw] (.canMake api (.getID unit) (get-type-id (unit-type-kws unit-to-make-kw)))))

(defn can-research?
  ([tech] (.canResearch api (get-type-id tech)))
  ([unit tech] (.canResearch api (.getID unit) (get-type-id tech))))

(defn can-upgrade?
  ([upgrade] (.canUpgrade api (get-type-id upgrade)))
  ([unit upgrade] (.canUpgrade api (.getID unit) (get-type-id upgrade))))

(defn can-upgrade-kw?
  ([upgrade-kw] (.canUpgrade api (get-type-id (upgrade-type-kws upgrade-kw))))
  ([unit upgrade-kw] (.canUpgrade api (.getID unit) (get-type-id (upgrade-type-kws upgrade-kw)))))

(defn under-aoe? [unit]
  (or (under-dark-swarm? unit) (under-disruption-web? unit) (under-storm? unit)))

(defn print-text [msg] (.printText api (str msg)))

(defn send-text [msg] (.sendText api (str msg)))

(defn set-command-optimization-level [level] (.setCommandOptimizationLevel api level))

(defn replay? [] (.isReplay api))

(defn visible-to-player? [unit player] (.isVisibleToPlayer api (.getID unit) (.getID player)))

(defn last-error [] (.getLastError api))

(defn remaining-latency-frames [] (.getRemainingLatencyFrames api))

(defn units-on-tile
  ([point] (units-on-tile (.x point) (.y point)))
  ([tx ty] (.getUnitsOnTile api tx ty)))

(defn what-upgrades [upgrade]
  (.getUnitType api (.getWhatUpgradesTypeID upgrade)))

(defn what-researches [tech]
  (.getUnitType api (.getWhatResearchesTypeID tech)))

;; weapon functions

(defn ground-weapon [unit] (get-weapon-type (.getGroundWeaponID (get-unit-type unit))))

(defn air-weapon [unit] (get-weapon-type (.getAirWeaponID (get-unit-type unit))))

(defn weapon? [weapon] (not= (get-id weapon) (get-id (weapon-type-kws :none))))

(defn damage-amount [weapon] (.getDamageAmount weapon))

(defn damage-bonus [weapon] (.getDamageBonus weapon))

(defn damage-cooldown [weapon] (.getDamageCooldown weapon))

(defn damage-factor [weapon] (.getDamageFactor weapon))

(defn weapon-upgrade-type [weapon]
  (get-upgrade-type (upgrade-type-ids (.getUpgradeTypeID weapon))))

(defn weapon-damage-type [weapon]
  (get-damage-type (damage-type-ids (.getDamageTypeID weapon))))

(defn weapon-explosion-type [weapon]
  (get-explosion-type (.getExplosionType weapon)))

(defn min-range [weapon] (.getMinRange weapon))

(defn max-range [weapon] (.getMaxRange weapon))

(defn inner-splash-radius [weapon] (.getInnerSplashRadius weapon))

(defn median-splash-radius [weapon] (.getMedianSplashRadius weapon))

(defn outer-splash-radius [weapon] (.getOuterSplashRadius weapon))

(defn targets-air? [weapon] (.isTargetsAir weapon))

(defn targets-ground? [weapon] (.isTargetsGround weapon))

(defn targets-mechanical? [weapon] (.isTargetsMechanical weapon))

(defn targets-organic? [weapon] (.isTargetsOrganic weapon))

(defn targets-non-building? [weapon] (.isTargetsNonBuilding weapon))

(defn targets-non-robotic? [weapon] (.isTargetsNonRobotic weapon))

(defn targets-terrain? [weapon] (.isTargetsTerrain weapon))

(defn targets-organic-or-mech? [weapon] (.isTargetsOrgOrMech weapon))

(defn targets-own? [weapon] (.isTargetsOwn weapon))

(defn ground-melee?
  "True for any unit with a ground attack with equal or shorter range
  to a firebat's (32)."
  [unit]
  (let [weapon (ground-weapon unit)]
    (and (not (building? unit)) (<= (max-range weapon) 32))))

;; additional bullet functions
;; fns that should also work: angle, velocity-x, velocity-y, remove-timer, exists?, visible?

(defn player-id [bullet] (.getPlayerID bullet))

(defn source-unit [bullet] (get-unit-by-id (.getSourceUnitID bullet)))

(defn position-x [bullet] (.getPositionX bullet))

(defn position-y [bullet] (.getPositionY bullet))

(defn position-valid? [bullet] (.getPositionValid bullet))

(defn position [bullet]
  (when (.getPositionValid bullet)
    (java.awt.Point. (.getPositionX bullet)
                     (.getPositionY bullet))))

(defn target-unit [bullet] (get-unit-by-id (.getTargetUnitID bullet)))

(defn target-position-x [bullet] (.getTargetPositionX bullet))

(defn target-position-y [bullet] (.getTargetPositionY bullet))

(defn target-position-valid [bullet] (.getTargetPositionValid bullet))

(defn target-position [bullet]
  (when (.getTargetPositionValid bullet)
    (java.awt.Point. (.getTargetPositionX bullet)
                     (.getTargetPositionY bullet))))

;; utility functions supplemental to JNIBWAPI

(defn dist [a b]
  (Math/sqrt (+ (Math/pow (- (pixel-x a) (pixel-x b)) 2)
                (Math/pow (- (pixel-y a) (pixel-y b)) 2))))

(defn dist-tile [a b]
  (Math/sqrt (+ (Math/pow (- (tile-x a) (tile-x b)) 2)
                (Math/pow (- (tile-y a) (tile-y b)) 2))))

(defn dist-choke [unit-or-building choke]
  (Math/sqrt (+ (Math/pow (- (pixel-x unit-or-building) (center-x choke)) 2)
                (Math/pow (- (pixel-y unit-or-building) (center-y choke)) 2))))

(defn deg->rad [deg] (* deg (/ Math/PI 180)))
(defn rad->deg [rad] (mod (* rad (/ 180 Math/PI)) 360))

(defn angle-to
  ([unit1 unit2] (angle-to (pixel-x unit1) (pixel-y unit1)
                           (pixel-x unit2) (pixel-y unit2)))
  ([x1 y1 x2 y2] (rad->deg (Math/atan2 (- y2 y1) (- x2 x1)))))

(defn angle-away
  ([unit1 unit2] (angle-away (pixel-x unit1) (pixel-y unit1)
                             (pixel-x unit2) (pixel-y unit2)))
  ([x1 y1 x2 y2] (mod (- (angle-to x1 y1 x2 y2) 180) 360)))

(defn point-angle
  "Return [px py] of the pixel location a given angle and distance
  from the unit."
  [unit angle distance]
  (let [rad (deg->rad angle)
        px (+ (pixel-x unit) (* distance (Math/cos rad)))
        py (+ (pixel-y unit) (* distance (Math/sin rad)))]
    [px py]))

(defn move-angle
  "Move a unit a specified distance at a specified angle."
  [unit angle distance]
  (apply move unit (point-angle unit angle distance)))

(defn mineral-walk-angle
  "Attempt to mineral walk in the general direction of the provided
  angle. If no minerals are in that direction, falls back to
  move-angle."
  [unit angle distance]
  (let [in-direction? (fn [mineral] (<= (Math/abs (- angle (angle-to unit mineral))) 135))
        minerals (filter in-direction? (minerals))
        current-frame (frame-count)]
    (if (seq minerals)
      (gather unit (first minerals))
      (move-angle unit angle distance))))

(defn closest [unit coll]
  (when (and unit (seq coll))
    (apply min-key (partial dist unit) coll)))

(defn closest-tile [unit coll]
  (when (and unit (seq coll))
    (apply min-key (partial dist-tile unit) coll)))

(defn closest-choke [unit coll]
  (when (and unit (seq coll))
    (apply min-key (partial dist-choke unit) coll)))

(defn closest-choke-start [start coll]
  (when (and start (seq coll))
    (let [tile-start (java.awt.Point. (* 32 (.x start)) (* 32 (.y start)))]
      (apply min-key (partial dist-choke tile-start) coll))))

(defn units-nearby [unit range coll]
  (let [nearby? (fn [target-unit] (<= (dist unit target-unit) range))
        nearby-units (remove #{unit} (filter nearby? coll))]
    (seq nearby-units)))

(defn walls-nearby
  "Return seq of angles to walls nearby the unit within
  distance. Defaults to 45 degree separation between angles."
  ;; TODO: clean up these pixel/walk-tile/build-tile conversions
  ([unit distance] (walls-nearby unit distance 45))
  ([unit distance sep]
     (let [angles (for [angle (range 0 360 sep)
                        :let [[px py] (point-angle unit angle distance)]]
                    (when (or (not (<= 0 px (map-width)))
                              (not (<= 0 py (map-height)))
                              (not= (map-tile-height (Math/floor (/ px 32))
                                                     (Math/floor (/ py 32)))
                                    (map-tile-height (tile-x unit)
                                                     (tile-y unit)))
                              (not (walkable? (/ px 8) (/ py 8))))
                      angle))]
       (remove nil? angles))))

(defn unit-max-range [unit]
  (max (max-range (ground-weapon unit)) (max-range (air-weapon unit))))

(defn attackable-by
  "Returns all units in coll that can currently attack unit OR are
  within buffer pixels. TODO: Filter by ground/air."
  ([unit coll] (attackable-by unit coll 128))
  ([unit coll buffer]
     (filter #(<= (dist unit %) (max buffer (unit-max-range %))) coll)))

(defn can-attack?
  "Returns true if unit has a weapon capable of hitting target."
  [unit target]
  (and (attack-capable? unit)
       (or (and (weapon? (ground-weapon unit))
                (not (flyer? target)))
           (and (weapon? (air-weapon unit))
                (flyer? target)))))

(defn enemies-in-range
  ([unit]
     (let [max-range (max (max-range (ground-weapon unit)) (max-range (air-weapon unit)))
           in-range? (fn [enemy] (<= (dist unit enemy) max-range))
           in-range-enemies (filter in-range? (enemy-units))]
       (seq in-range-enemies)))
  ([unit attack-type]
     (let [get-weapon (if (= attack-type :ground) ground-weapon air-weapon)
           in-range? (fn [enemy] (<= (dist unit enemy) (max-range (get-weapon unit))))
           in-range-enemies (filter in-range? (enemy-units))]
       (seq in-range-enemies))))

(defn being-targeted-by
  "Return units in coll that are currently targeting the specified
  unit."
  [unit coll]
  (filter #(= (get-id unit) (target-unit-id %)) coll))
