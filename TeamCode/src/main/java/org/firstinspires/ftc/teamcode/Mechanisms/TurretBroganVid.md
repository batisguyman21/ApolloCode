# Turret tutorial

## Important:
S.V.P assurer vous que de n'importe quell manière que vous faites le turret les files ne peuvent pas se dechiree.

# Qu'est ce qu'un P.I.D?

* **P**roportional:
Elle sert à augmenter ta vitesse le plus que tu es loin et te ralentire quand tu es plus proche.

* **I**ntegral :
Cela n'est pas utiliser ici, mais de ce que j'ai compris, elle est la somme des erreurs

* **D**erivative : 
Elle sert à amortir le Proportional quand on ralentit.

# Comment fonction notre logique?

Voici la logique que je vais utiliser dans le code.

(delta Time)
dT = elapsed time
reset time

est-ce que le id tag qu'on voit est valide?
Non?:
    motor = 0
    dernière erreur = 0
    sortir ici.
Oui?:
    erreur = Goal - tx (LLResults.GetTx)
    pTerm = erreur * kP
    si dt > 0
    non?
        dt = 0
    oui?
    dTerm = ((erreur - dernière erreur)/dt)*kd
    si abs(erreur) < tolerance
    oui?:
        motor = 0
        Non?:
        Clip(pTerm-dTerm) entre le plus vite que l'on veut que s'aille et son negatif.
        motor = power
        dernière erreur = erreur
        sortir ici.