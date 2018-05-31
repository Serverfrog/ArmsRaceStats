#!/usr/bin/python

import fame_calculator_functions as fun
from CalcValueHolder import CalcValueHolder as Holder

#
# Found at https://worldoftanks.eu/de/content/docs/arms-race-regulations/ Subject 7
#


holder = Holder(925)
holder.updateXp(ownXp=12367, enemyXp=1064 )
holder.team_size = 12


print(fun.fp(holder))
print(holder.__dict__)



x = []
y = []

for i in range(1,100):
    x.append(i)
    y.append(2*i)

    x2 = []
    y2 = []
for i in range(1,100):
        x2.append(100-i)
        y2.append(2*i)
