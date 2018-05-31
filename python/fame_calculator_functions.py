#!/usr/bin/python

def calcEloC(elo):
    if (elo <= 1000): return 1.0
    if (elo <= 1050): return 1.1
    if (elo <= 1100): return 1.2
    if (elo <= 1150): return 1.3
    if (elo <= 1200): return 1.4
    if (elo <= 1250): return 1.5
    if (elo <= 1300): return 1.6
    if (elo <= 1350): return 1.7
    if (elo <= 1400): return 1.8
    if (elo <= 1450): return 1.9
    return 2.0

def fp(holder):
    return fame_points(fame_points_base=holder.fame_points_base, battle_type_c=holder.battle_type_c, elo_c=holder.elo_c,
                          event_value_c=holder.event_value_c, team_xp=holder.team_xp, battle_xp=holder.battle_xp, team_size=holder.team_size,
                          bonus_p=holder.bonus_p, technology_p=holder.technology_p )



# calculate the fame_points
def fame_points( fame_points_base, battle_type_c, elo_c, event_value_c, team_xp, battle_xp,team_size, bonus_p, technology_p ):
    numerator = fame_points_base * battle_type_c * elo_c * event_value_c * team_xp
    divisor  = battle_xp * team_size
    bonus = 1 + (bonus_p + technology_p)/100

    return (numerator / divisor) * bonus
