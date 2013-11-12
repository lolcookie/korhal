(ns korhal.interop-types)

(def unit-types
  ['marine 'Terran_Marine
   'ghost 'Terran_Ghost
   'vulture 'Terran_Vulture
   'goliath-turret 'Undefined4
   'siege-tank-tank-mode 'Terran_Siege_Tank_Tank_Mode
   'siege-tank-turret-tank-mode 'Undefined6
   'scv 'Terran_SCV
   'wraith 'Terran_Wraith
   'science-vessel 'Terran_Science_Vessel
   'dropship 'Terran_Dropship
   'battlecruiser 'Terran_Battlecruiser
   'spider-mine 'Terran_Vulture_Spider_Mine
   'nuclear-missile 'Terran_Nuclear_Missile
   'siege-tank-siege-mode 'Terran_Siege_Tank_Siege_Mode
   'siege-tank-turret-siege-mode 'Undefined31
   'firebat 'Terran_Firebat
   'scanner-sweep 'Spell_Scanner_Sweep
   'medic 'Terran_Medic
   'larva 'Zerg_Larva
   'egg 'Zerg_Egg
   'zergling 'Zerg_Zergling
   'hydralisk 'Zerg_Hydralisk
   'ultralisk 'Zerg_Ultralisk
   'broodling 'Zerg_Broodling
   'drone 'Zerg_Drone
   'overlord 'Zerg_Overlord
   'mutalisk 'Zerg_Mutalisk
   'guardian 'Zerg_Guardian
   'queen 'Zerg_Queen
   'defiler 'Zerg_Defiler
   'scourge 'Zerg_Scourge
   'infested-terran 'Zerg_Infested_Terran
   'valkyrie 'Terran_Valkyrie
   'cocoon 'Zerg_Cocoon
   'corsair 'Protoss_Corsair
   'dark-templar 'Protoss_Dark_Templar
   'devourer 'Zerg_Devourer
   'dark-archon 'Protoss_Dark_Archon
   'probe 'Protoss_Probe
   'zealot 'Protoss_Zealot
   'dragoon 'Protoss_Dragoon
   'high-templar 'Protoss_High_Templar
   'archon 'Protoss_Archon
   'shuttle 'Protoss_Shuttle
   'scout 'Protoss_Scout
   'arbiter 'Protoss_Arbiter
   'carrier 'Protoss_Carrier
   'interceptor 'Protoss_Interceptor
   'reaver 'Protoss_Reaver
   'observer 'Protoss_Observer
   'scarab 'Protoss_Scarab
   'rhynadon 'Critter_Rhynadon
   'bengalaas 'Critter_Bengalaas
   'scantid 'Critter_Scantid
   'kakaru 'Critter_Kakaru
   'ragnasaur 'Critter_Ragnasaur
   'ursadon 'Critter_Ursadon
   'lurker-egg 'Zerg_Lurker_Egg
   'lurker 'Zerg_Lurker
   'disruption-web 'Spell_Disruption_Web
   'command-center 'Terran_Command_Center
   'comsat-station 'Terran_Comsat_Station
   'nuclear-silo 'Terran_Nuclear_Silo
   'supply-depot 'Terran_Supply_Depot
   'refinery 'Terran_Refinery
   'barracks 'Terran_Barracks
   'academy 'Terran_Academy
   'factory 'Terran_Factory
   'starport 'Terran_Starport
   'control-tower 'Terran_Control_Tower
   'science-facility 'Terran_Science_Facility
   'covert-ops 'Terran_Covert_Ops
   'physics-lab 'Terran_Physics_Lab
   'machine-shop 'Terran_Machine_Shop
   'engineering-bay 'Terran_Engineering_Bay
   'armory 'Terran_Armory
   'missile-turret 'Terran_Missile_Turret
   'bunker 'Terran_Bunker
   'infested-command-center 'Zerg_Infested_Command_Center
   'hatchery 'Zerg_Hatchery
   'lair 'Zerg_Lair
   'hive 'Zerg_Hive
   'nydus-canal 'Zerg_Nydus_Canal
   'hydralisk-den 'Zerg_Hydralisk_Den
   'defiler-mound 'Zerg_Defiler_Mound
   'greater-spire 'Zerg_Greater_Spire
   'queens-nest 'Zerg_Queens_Nest
   'evolution-chamber 'Zerg_Evolution_Chamber
   'ultralisk-cavern 'Zerg_Ultralisk_Cavern
   'spire 'Zerg_Spire
   'spawning-pool 'Zerg_Spawning_Pool
   'creep-colony 'Zerg_Creep_Colony
   'spore-colony 'Zerg_Spore_Colony
   'sunken-colony 'Zerg_Sunken_Colony
   'extractor 'Zerg_Extractor
   'nexus 'Protoss_Nexus
   'robotics-facility 'Protoss_Robotics_Facility
   'pylon 'Protoss_Pylon
   'assimilator 'Protoss_Assimilator
   'observatory 'Protoss_Observatory
   'gateway 'Protoss_Gateway
   'photon-cannon 'Protoss_Photon_Cannon
   'citadel-of-adun 'Protoss_Citadel_of_Adun
   'cybernetics-core 'Protoss_Cybernetics_Core
   'templar-archives 'Protoss_Templar_Archives
   'forge 'Protoss_Forge
   'stargate 'Protoss_Stargate
   'fleet-beacon 'Protoss_Fleet_Beacon
   'arbiter-tribunal 'Protoss_Arbiter_Tribunal
   'robotics-support-bay 'Protoss_Robotics_Support_Bay
   'shield-battery 'Protoss_Shield_Battery])

(def unit-command-types
  ['attack-move 'Attack_Move
   'attack-unit 'Attack_Unit
   'build 'Build
   'build-addon 'Build_Addon
   'train 'Train
   'morph 'Morph
   'research 'Research
   'upgrade 'Upgrade
   'set-rally-position 'Set_Rally_Position
   'set-rally-unit 'Set_Rally_Unit
   'move 'Move
   'patrol 'Patrol
   'hold-position 'Hold_Position
   'stop 'Stop
   'follow 'Follow
   'gather 'Gather
   'return-cargo 'Return_Cargo
   'repair 'Repair
   'burrow 'Burrow
   'unburrow 'Unburrow
   'cloak 'Cloak
   'decloak 'Decloak
   'siege 'Siege
   'unsiege 'Unsiege
   'lift 'Lift
   'land 'Land
   'load 'Load
   'unload 'Unload
   'unload-all 'Unload_All
   'unload-all-position 'Unload_All_Position
   'right-click-position 'Right_Click_Position
   'right-click-unit 'Right_Click_Unit
   'halt-construction 'Halt_Construction
   'cancel-construction 'Cancel_Construction
   'cancel-addon 'Cancel_Addon
   'cancel-train 'Cancel_Train
   'cancel-train-slot 'Cancel_Train_Slot
   'cancel-morph 'Cancel_Morph
   'cancel-research 'Cancel_Research
   'cancel-upgrade 'Cancel_Upgrade
   'use-tech 'Use_Tech
   'use-tech-position 'Use_Tech_Position
   'use-tech-unit 'Use_Tech_Unit
   'place-cop 'Place_COP
   'none 'None])

(def upgrade-types
  ['infantry-armor 'Terran_Infantry_Armor
   'vehicle-plating 'Terran_Vehicle_Plating
   'ship-plating 'Terran_Ship_Plating
   'carapace 'Zerg_Carapace
   'flyer-carapace 'Zerg_Flyer_Carapace
   'ground-armor 'Protoss_Ground_Armor
   'air-armor 'Protoss_Air_Armor
   'infantry-weapons 'Terran_Infantry_Weapons
   'vehicle-weapons 'Terran_Vehicle_Weapons
   'ship-weapons 'Terran_Ship_Weapons
   'melee-attacks 'Zerg_Melee_Attacks
   'missile-attacks 'Zerg_Missile_Attacks
   'flyer-attacks 'Zerg_Flyer_Attacks
   'ground-weapons 'Protoss_Ground_Weapons
   'air-weapons 'Protoss_Air_Weapons
   'plasma-shields 'Protoss_Plasma_Shields
   'u-238-shells 'U_238_Shells
   'ion-thrusters 'Ion_Thrusters
   'titan-reactor 'Titan_Reactor
   'ocular-implants 'Ocular_Implants
   'moebius-reactor 'Moebius_Reactor
   'apollo-reactor 'Apollo_Reactor
   'colossus-reactor 'Colossus_Reactor
   'ventral-sacs 'Ventral_Sacs
   'antennae 'Antennae
   'pneumatized-carapace 'Pneumatized_Carapace
   'metabolic-boost 'Metabolic_Boost
   'adrenal-glands 'Adrenal_Glands
   'muscular-augments 'Muscular_Augments
   'grooved-spines 'Grooved_Spines
   'gamete-meiosis 'Gamete_Meiosis
   'metasynaptic-node 'Metasynaptic_Node
   'singularity-charge 'Singularity_Charge
   'leg-enhancements 'Leg_Enhancements
   'scarab-damage 'Scarab_Damage
   'reaver-capacity 'Reaver_Capacity
   'gravitic-drive 'Gravitic_Drive
   'sensor-array 'Sensor_Array
   'gravitic-boosters 'Gravitic_Boosters
   'khaydarin-amulet 'Khaydarin_Amulet
   'apial-sensors 'Apial_Sensors
   'gravitic-thrusters 'Gravitic_Thrusters
   'carrier-capacity 'Carrier_Capacity
   'khaydarin-core 'Khaydarin_Core
   'argus-jewel 'Argus_Jewel
   'argus-talisman 'Argus_Talisman
   'caduceus-reactor 'Caduceus_Reactor
   'chitinous-plating 'Chitinous_Plating
   'anabolic-synthesis 'Anabolic_Synthesis
   'charon-boosters 'Charon_Boosters])

(def tech-types
  ['stim-packs 'Stim_Packs
   'lockdown 'Lockdown
   'emp-shockwave 'EMP_Shockwave
   'spider-mines 'Spider_Mines
   'scanner-sweep 'Scanner_Sweep
   'tank-siege-mode 'Tank_Siege_Mode
   'defensive-matrix 'Defensive_Matrix
   'irradiate 'Irradiate
   'yamato-gun 'Yamato_Gun
   'cloaking-field 'Cloaking_Field
   'personnel-cloaking 'Personnel_Cloaking
   'burrowing 'Burrowing
   'infestation 'Infestation
   'spawn-broodlings 'Spawn_Broodlings
   'dark-swarm 'Dark_Swarm
   'plague 'Plague
   'consume 'Consume
   'ensnare 'Ensnare
   'parasite 'Parasite
   'psionic-storm 'Psionic_Storm
   'hallucination 'Hallucination
   'recall 'Recall
   'statis-field 'Stasis_Field
   'archon-warp 'Archon_Warp
   'restoration 'Restoration
   'disruption-web 'Disruption_Web
   'mind-control 'Mind_Control
   'dark-archon-meld 'Dark_Archon_Meld
   'feedback 'Feedback
   'optical-flare 'Optical_Flare
   'maelstrom 'Maelstrom
   'lurker-aspect 'Lurker_Aspect
   'healing 'Healing
   'nuclear-strike 'Nuclear_Strike])

(def race-types
  ['zerg 'Zerg
   'terran 'Terran
   'protoss 'Protoss
   'random 'Random])

(def unit-size-types
  ['independent 'Independent
   'small 'Small
   'medium 'Medium
   'large 'Large])

(def weapon-types
  ['gauss-rifle 'Gauss_Rifle
   'c-10-canister-rifle 'C_10_Canister_Rifle
   'fragmentation-grenade 'Fragmentation_Grenade
   'spider-mines 'Spider_Mines
   'twin-autocannons 'Twin_Autocannons
   'hellfire-missile-pack 'Hellfire_Missile_Pack
   'arclite-cannon 'Arclite_Cannon
   'fusion-cutter 'Fusion_Cutter
   'gemini-missiles 'Gemini_Missiles
   'burst-lasers 'Burst_Lasers
   'ats-laser-battery 'ATS_Laser_Battery
   'ata-laser-battery 'ATA_Laser_Battery
   'flamethrower 'Flame_Thrower
   'arclite-shock-cannon 'Arclite_Shock_Cannon
   'longbolt-missile 'Longbolt_Missile
   'yamato-gun 'Yamato_Gun
   'nuclear-strike 'Nuclear_Strike
   'lockdown 'Lockdown
   'emp-shockwave 'EMP_Shockwave
   'irradiate 'Irradiate
   'claws 'Claws
   'needle-spines 'Needle_Spines
   'kaiser-blades 'Kaiser_Blades
   'toxic-spores 'Toxic_Spores
   'spines 'Spines
   'acid-spore 'Acid_Spore
   'glave-wurm 'Glave_Wurm
   'seeker-spores 'Seeker_Spores
   'subterranean-tentacle 'Subterranean_Tentacle
   'suicide-infested-terran 'Suicide_Infested_Terran
   'suicide-scourge 'Suicide_Scourge
   'parasite 'Parasite
   'spawn-broodlings 'Spawn_Broodlings
   'ensnare 'Ensnare
   'dark-swarm 'Dark_Swarm
   'plague 'Plague
   'consume 'Consume
   'particle-beam 'Particle_Beam
   'psi-blades 'Psi_Blades
   'phase-disruptor 'Phase_Disruptor
   'psi-assault 'Psi_Assault
   'psionic-shockwave 'Psionic_Shockwave
   'dual-photon-blasters 'Dual_Photon_Blasters
   'anti-matter-missiles 'Anti_Matter_Missiles
   'phase-disruptor-cannon 'Phase_Disruptor_Cannon
   'pulse-cannon 'Pulse_Cannon
   'sts-photon-cannon 'STS_Photon_Cannon
   'sta-photon-cannon 'STA_Photon_Cannon
   'scarab 'Scarab
   'statis-field 'Stasis_Field
   'psionic-storm 'Psionic_Storm
   'neutron-flare 'Neutron_Flare
   'disruption-web 'Disruption_Web
   'restoration 'Restoration
   'halo-rockets 'Halo_Rockets
   'corrosive-acid 'Corrosive_Acid
   'mind-control 'Mind_Control
   'feedback 'Feedback
   'optical-flare 'Optical_Flare
   'maelstrom 'Maelstrom
   'subterranean-spines 'Subterranean_Spines
   'warp-blades 'Warp_Blades
   'none 'None])

(def bullet-types
  ['melee 'Melee
   'fusion-cutter-hit 'Fusion_Cutter_Hit
   'gauss-rifle-hit 'Gauss_Rifle_Hit
   'c-10-canister-rifle-hit 'C_10_Canister_Rifle_Hit
   'gemini-missiles 'Gemini_Missiles
   'fragmentation-grenade 'Fragmentation_Grenade
   'longbolt-missile 'Longbolt_Missile
   'ats-ata-laser-battery 'ATS_ATA_Laser_Battery
   'burst-lasers 'Burst_Lasers
   'arclite-shock-cannot-hit 'Arclite_Shock_Cannon_Hit
   'emp-missile 'EMP_Missile
   'dual-photon-blasters-hit 'Dual_Photon_Blasters_Hit
   'particle-beam-hit 'Particle_Beam_Hit
   'anti-matter-missile 'Anti_Matter_Missile
   'pulse-cannon 'Pulse_Cannon
   'psionic-shockwave-hit 'Psionic_Shockwave_Hit
   'psionic-storm 'Psionic_Storm
   'yamato-gun 'Yamato_Gun
   'phase-disruptor 'Phase_Disruptor
   'sta-sts-cannon-overlay 'STA_STS_Cannon_Overlay
   'sunken-colony-tentacle 'Sunken_Colony_Tentacle
   'acid-spore 'Acid_Spore
   'glave-wurm 'Glave_Wurm
   'seeker-spores 'Seeker_Spores
   'queen-spell-carrier 'Queen_Spell_Carrier
   'plague-cloud 'Plague_Cloud
   'consume 'Consume
   'needle-spine-hit 'Needle_Spine_Hit
   'invisible 'Invisible
   'optical-flare-grenade 'Optical_Flare_Grenade
   'halo-rockets 'Halo_Rockets
   'subterranean-spines 'Subterranean_Spines
   'corrosive-acid-shot 'Corrosive_Acid_Shot
   'neutron-flare 'Neutron_Flare])

(def damage-types
  ['independent 'Independent
   'explosive 'Explosive
   'concussive 'Concussive
   'normal 'Normal
   'ignore-armor 'Ignore_Armor
   'none 'None
   'unknown 'Unknown])

(def explosion-types
  ['none 'None
   'normal 'Normal
   'radial-splash 'Radial_Splash
   'enemy-splash 'Enemy_Splash
   'lockdown 'Lockdown
   'nuclear-missile 'Nuclear_Missile
   'parasite 'Parasite
   'broodlings 'Broodlings
   'emp-shockwave 'EMP_Shockwave
   'irradiate 'Irradiate
   'ensnare 'Ensnare
   'plague 'Plague
   'statis-field 'Stasis_Field
   'dark-swarm 'Dark_Swarm
   'consume 'Consume
   'yamato-gun 'Yamato_Gun
   'restoration 'Restoration
   'disruption-web 'Disruption_Web
   'corrosive-acid 'Corrosive_Acid
   'mind-control 'Mind_Control
   'feedback 'Feedback
   'optical-flare 'Optical_Flare
   'maelstrom 'Maelstrom
   'air-splash 'Air_Splash])

(def order-types
  ['die 'Die
   'stop 'Stop
   'guard 'Guard
   'player-guard 'PlayerGuard
   'turret-guard 'TurretGuard
   'bunker-guard 'BunkerGuard
   'move 'Move
   'attack-unit 'AttackUnit
   'attack-tile 'AttackTile
   'hover 'Hover
   'attack-move 'AttackMove
   'infested-command-center 'InfestedCommandCenter
   'unused-nothing 'UnusedNothing
   'unused-powerup 'UnusedPowerup
   'tower-guard 'TowerGuard
   'vulture-mine 'VultureMine
   'nothing 'Nothing
   'nothing-3 'Nothing3
   'cast-infestation 'CastInfestation
   'infesting-command-center 'InfestingCommandCenter
   'place-building 'PlaceBuilding
   'build-protoss-2 'BuildProtoss2
   'constructing-building 'ConstructingBuilding
   'repair 'Repair
   'place-addon 'PlaceAddon
   'build-addon 'BuildAddon
   'train 'Train
   'rally-point-unit 'RallyPointUnit
   'rally-point-tile 'RallyPointTile
   'zerg-birth 'ZergBirth
   'zerg-unit-morph 'ZergUnitMorph
   'zerg-building-morph 'ZergBuildingMorph
   'incomplete-building 'IncompleteBuilding
   'build-nydus-exit 'BuildNydusExit
   'enter-nydus-canal 'EnterNydusCanal
   'follow 'Follow
   'carrier 'Carrier
   'reaver-carrier-move 'ReaverCarrierMove
   'carrier-ignore-2 'CarrierIgnore2
   'reaver 'Reaver
   'train-fighter 'TrainFighter
   'interceptor-attack 'InterceptorAttack
   'scarab-attack 'ScarabAttack
   'recharge-shields-unit 'RechargeShieldsUnit
   'recharge-shields-battery 'RechargeShieldsBattery
   'shield-battery 'ShieldBattery
   'interceptor-return 'InterceptorReturn
   'building-land 'BuildingLand
   'building-lift-off 'BuildingLiftOff
   'drone-lift-off 'DroneLiftOff
   'lifting-off 'LiftingOff
   'research-tech 'ResearchTech
   'upgrade 'Upgrade
   'larva 'Larva
   'spawning-larva 'SpawningLarva
   'harvest-1 'Harvest1
   'harvest-2 'Harvest2
   'move-to-gas 'MoveToGas
   'wait-for-gas 'WaitForGas
   'harvest-gas 'HarvestGas
   'return-gas 'ReturnGas
   'move-to-minerals 'MoveToMinerals
   'wait-for-minerals 'WaitForMinerals
   'mining-minerals 'MiningMinerals
   'harvest-3 'Harvest3
   'harvest-4 'Harvest4
   'return-minerals 'ReturnMinerals
   'interrupted 'Interrupted
   'enter-transport 'EnterTransport
   'pickup-idle 'PickupIdle
   'pickup-transport 'PickupTransport
   'pickup-bunker 'PickupBunker
   'pickup-4 'Pickup4
   'powerup-idle 'PowerupIdle
   'sieging 'Sieging
   'unsieging 'Unsieging
   'init-creep-growth 'InitCreepGrowth
   'spread-creep 'SpreadCreep
   'stopping-creep-growth 'StoppingCreepGrowth
   'guardian-aspect 'GuardianAspect
   'archon-warp 'ArchonWarp
   'completing-archon-summon 'CompletingArchonsummon
   'hold-position 'HoldPosition
   'cloak 'Cloak
   'decloak 'Decloak
   'unload 'Unload
   'move-unload 'MoveUnload
   'fire-yamato-gun 'FireYamatoGun
   'cast-lockdown 'CastLockdown
   'burrowing 'Burrowing
   'burrowed 'Burrowed
   'unburrowing 'Unburrowing
   'cast-dark-swarm 'CastDarkSwarm
   'cast-parasite 'CastParasite
   'cast-spawn-broodlings 'CastSpawnBroodlings
   'cast-emp-shockwave 'CastEMPShockwave
   'nuke-wait 'NukeWait
   'nuke-train 'NukeTrain
   'nuke-launch 'NukeLaunch
   'nuke-paint 'NukePaint
   'nuke-unit 'NukeUnit
   'cast-nuclear-strike 'CastNuclearStrike
   'nuke-track 'NukeTrack
   'cloak-nearby-units 'CloakNearbyUnits
   'place-mine 'PlaceMine
   'right-click-action 'RightClickAction
   'cast-recall 'CastRecall
   'teleport-to-location 'TeleporttoLocation
   'cast-scanner-sweep 'CastScannerSweep
   'scanner 'Scanner
   'cast-defensive-matrix 'CastDefensiveMatrix
   'cast-psionic-storm 'CastPsionicStorm
   'cast-irradiate 'CastIrradiate
   'cast-plague 'CastPlague
   'cast-consume 'CastConsume
   'cast-ensnare 'CastEnsnare
   'cast-stasis-field 'CastStasisField
   'cast-hallucination 'CastHallucination
   'hallucination-2 'Hallucination2
   'reset-collision 'ResetCollision
   'patrol 'Patrol
   'ctf-cop-init 'CTFCOPInit
   'ctf-cop-1 'CTFCOP1
   'ctf-cop-2 'CTFCOP2
   'computer-ai 'ComputerAI
   'atk-move-ep 'AtkMoveEP
   'harass-move 'HarassMove
   'ai-patrol 'AIPatrol
   'guard-post 'GuardPost
   'rescue-passive 'RescuePassive
   'neutral 'Neutral
   'computer-return 'ComputerReturn
   'self-destructing 'SelfDestrucing
   'critter 'Critter
   'hidden-gun 'HiddenGun
   'open-door 'OpenDoor
   'close-door 'CloseDoor
   'hide-trap 'HideTrap
   'reveal-trap 'RevealTrap
   'enable-doodad 'Enabledoodad
   'disable-doodad 'Disabledoodad
   'warpin 'Warpin
   'medic 'Medic
   'medic-heal-1 'MedicHeal1
   'heal-move 'HealMove
   'medic-heal-2 'MedicHeal2
   'cast-restoration 'CastRestoration
   'cast-disruption-web 'CastDisruptionWeb
   'cast-mind-control 'CastMindControl
   'dark-archon-meld 'DarkArchonMeld
   'cast-feedback 'CastFeedback
   'cast-optical-flare 'CastOpticalFlare
   'cast-maelstrom 'CastMaelstrom
   'junk-yard-dog 'JunkYardDog
   'fatal 'Fatal
   'none 'None
   'unknown 'Unknown])

(def unit-type-fn-maps
  ['get-name 'getName
   'race-id 'getRaceID
   'what-build-id 'getWhatBuildID
   'armor-upgrade-id 'getArmorUpgradeID
   'max-hit-points 'getMaxHitPoints
   'max-shields 'getMaxShields
   'max-energy 'getMaxEnergy
   'armor 'getArmor
   'mineral-price 'getMineralPrice
   'gas-price 'getGasPrice
   'build-time 'getBuildTime
   'supply-required 'getSupplyRequired
   'supply-provided 'getSupplyProvided
   'space-required 'getSpaceRequired
   'space-provided 'getSpaceProvided
   'build-score 'getBuildScore
   'destroy-score 'getDestroyScore
   'size-id 'getSizeID
   'tile-width 'getTileWidth
   'tile-height 'getTileHeight
   'dimension-left 'getDimensionLeft
   'dimension-up 'getDimensionUp
   'dimension-right 'getDimensionRight
   'dimension-down 'getDimensionDown
   'seek-range 'getSeekRange
   'sight-range 'getSightRange
   'ground-weapon-id 'getGroundWeaponID
   'max-ground-hits 'getMaxGroundHits
   'air-weapon-id 'getAirWeaponID
   'max-air-hits 'getMaxAirHits
   'top-speed 'getTopSpeed
   'acceleration 'getAcceleration
   'halt-distance 'getHaltDistance
   'turn-radius 'getTurnRadius
   'produce-capable? 'isProduceCapable
   'attack-capable? 'isAttackCapable
   'can-move? 'isCanMove
   'flyer? 'isFlyer
   'regenerates? 'isRegenerates
   'spellcaster? 'isSpellcaster
   'invincible? 'isInvincible
   'organic? 'isOrganic
   'mechanical? 'isMechanical
   'robotic? 'isRobotic
   'detector? 'isDetector
   'resource-container? 'isResourceContainer
   'refinery? 'isRefinery
   'worker? 'isWorker
   'requires-psi? 'isRequiresPsi
   'requires-creep? 'isRequiresCreep
   'burrowable? 'isBurrowable
   'cloakable? 'isCloakable
   'building? 'isBuilding
   'addon? 'isAddon
   'flying-building? 'isFlyingBuilding
   'spell? 'isSpell])

(def unit-fn-maps
  ['replay-id 'getReplayID
   'player-id 'getPlayerID
   'type-id 'getTypeID
   'angle 'getAngle
   'velocity-x 'getVelocityX
   'velocity-y 'getVelocityY
   'hit-points 'getHitPoints
   'shields 'getShields
   'energy 'getEnergy
   'resources 'getResources
   'resource-group 'getResourceGroup
   'last-command-frame 'getLastCommandFrame
   'last-command-id 'getLastCommandID
   'initial-x 'getInitialX
   'initial-y 'getInitialY
   'initial-tile-x 'getInitialTileX
   'initial-tile-y 'getInitialTileY
   'initial-hit-points 'getInitialHitPoints
   'initial-resources 'getInitialResources
   'kill-count 'getKillCount
   'acid-spore-count 'getAcidSporeCount
   'interceptor-count 'getInterceptorCount
   'scarab-count 'getScarabCount
   'spider-mine-count 'getSpiderMineCount
   'ground-weapon-cooldown 'getGroundWeaponCooldown
   'air-weapon-cooldown 'getAirWeaponCooldown
   'spell-cooldown 'getSpellCooldown
   'defense-matrix-points 'getDefenseMatrixPoints
   'defense-matrix-timer 'getDefenseMatrixTimer
   'ensnare-timer 'getEnsnareTimer
   'irradiate-timer 'getIrradiateTimer
   'lockdown-timer 'getLockdownTimer
   'maelstrom-timer 'getMaelstromTimer
   'order-timer 'getOrderTimer
   'plague-timer 'getPlagueTimer
   'remove-timer 'getRemoveTimer
   'statis-timer 'getStasisTimer
   'stim-timer 'getStimTimer
   'build-type-id 'getBuildTypeID
   'training-queue-size 'getTrainingQueueSize
   'researching-tech-id 'getResearchingTechID
   'upgrading-upgrade-id 'getUpgradingUpgradeID
   'remaining-build-timer 'getRemainingBuildTimer
   'remaining-train-time 'getRemainingTrainTime
   'remaining-research-time 'getRemainingResearchTime
   'remaining-upgrade-time 'getRemainingUpgradeTime
   'build-unit-id 'getBuildUnitID
   'target-unit-id 'getTargetUnitID
   'target-x 'getTargetX
   'target-y 'getTargetY
   'order-id 'getOrderID
   'order-target-id 'getOrderTargetID
   'secondary-order-id 'getSecondaryOrderID
   'rally-x 'getRallyX
   'rally-y 'getRallyY
   'rally-unit-id 'getRallyUnitID
   'addon-id 'getAddOnID
   'transport-id 'getTransportID
   'num-loaded-units 'getNumLoadedUnits
   'num-larva 'getNumLarva
   'is-exists? 'isExists
   'nuke-ready? 'isNukeReady
   'accelerating? 'isAccelerating
   'attacking? 'isAttacking
   'attack-frame? 'isAttackFrame
   'being-constructed? 'isBeingConstructed
   'being-gathered? 'isBeingGathered
   'being-healed? 'isBeingHealed
   'blind? 'isBlind
   'braking? 'isBraking
   'burrowed? 'isBurrowed
   'carrying-gas? 'isCarryingGas
   'carrying-minerals? 'isCarryingMinerals
   'cloaked? 'isCloaked
   'completed? 'isCompleted
   'constructing? 'isConstructing
   'defense-matrixed? 'isDefenseMatrixed
   'detected? 'isDetected
   'ensnared? 'isEnsnared
   'following? 'isFollowing
   'gathering-gas? 'isGatheringGas
   'gathering-minerals? 'isGatheringMinerals
   'hallucination? 'isHallucination
   'holding-position? 'isHoldingPosition
   'idle? 'isIdle
   'interruptible? 'isInterruptable
   'invincible? 'isInvincible
   'irradiated? 'isIrradiated
   'lifted? 'isLifted
   'loaded? 'isLoaded
   'locked-down? 'isLockedDown
   'maelstrommed? 'isMaelstrommed
   'morphing? 'isMorphing
   'moving? 'isMoving
   'parasited? 'isParasited
   'patrolling? 'isPatrolling
   'plagued? 'isPlagued
   'repairing? 'isRepairing
   'selected? 'isSelected
   'sieged? 'isSieged
   'starting-attack? 'isStartingAttack
   'statised? 'isStasised
   'stimmed? 'isStimmed
   'stuck? 'isStuck
   'training? 'isTraining
   'under-attack? 'isUnderAttack
   'under-dark-swarm? 'isUnderDarkSwarm
   'under-disruption-web? 'isUnderDisruptionWeb
   'under-storm? 'isUnderStorm
   'unpowered? 'isUnpowered
   'upgrading? 'isUpgrading
   'visible? 'isVisible])

(def player-fn-maps
  ['start-location 'getStartLocation
   'self? 'isSelf
   'ally? 'isAlly
   'enemy? 'isEnemy
   'neutral? 'isNeutral
   'observer? 'isObserver
   'player-color 'getColor
   'player-name 'getName])

(def base-location-fn-maps
  ['region-id 'getRegionID
   'get-base-minerals 'getMinerals
   'get-base-gas 'getGas
   'island? 'isIsland
   'mineral-only? 'isMineralOnly
   'start-location? 'isStartLocation])
