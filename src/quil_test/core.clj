(ns quil-test.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def grid-size 20)

(def state {:matrix (vec
                     (repeatedly (* grid-size grid-size) #(rand-int 2)))})

(defn setup []
  (q/frame-rate 10)
  (q/color-mode :hsb)
  (q/no-stroke)
  state)

(defn update-state [state]
  state)

(defn draw-state [state]
  (q/background 240)
  (let [cell-size (quot (q/width) grid-size)]
    (doseq [[i v] (map-indexed vector (:matrix state))]
      (let [multiplier (int (/ i grid-size))
            x (* cell-size (- i (* multiplier grid-size)))
            y (* cell-size multiplier)]
        (q/fill
         (if (= 1 v) 0 255))
        (q/rect x y cell-size cell-size)))))

(defn -main [& args]
  (q/defsketch game-of-life
    :host "host"
    :title "quil-test"
    :size [500 500]
    :setup setup
    :update update-state
    :draw draw-state
    :middleware [m/fun-mode]))
