(ns play.2048)

(def transpose (partial apply mapv vector))

(def move-left (comp
                 (partial conj (repeat 0))
                 (partial map (partial apply +))
                 (partial mapcat (partial partition-all 2))
                 (partial partition-by identity)
                 (partial remove zero?)))

(def make-left-move (comp
                      (partial into [])
                      (partial take 2)
                      (partial flatten)
                      (partial move-left)))

(def make-right-move (comp reverse make-left-move reverse))

(def play-right (partial map make-right-move))
(def play-left (partial map make-left-move))
(def play-top (comp
                transpose
                (partial map make-left-move)
                transpose))
(def play-bottom (comp
                   transpose
                   (partial map make-right-move)
                   transpose))

(println (play-top (play-left [[2 2] [2 2]])))
