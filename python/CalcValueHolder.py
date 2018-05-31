import fame_calculator_functions as fun

class CalcValueHolder:
    ##enemy Elo, must be changed if updated
    elo = 1

    #  die Basiszahl der Ruhmespunkte im Wettrüsten gleich 1500.
    fame_points_base = 1500

    # 1 = Herausfordererturnier in einer Landungsprovinz der Basisfront
    # 5 = Herausfordererturnier in einer Standardprovinz beliebiger Front und Herausfordererturnier in einer Landungsauktion der Fortgeschritten- und Elite-Front.
    # 5 = Gefecht gegen den Besitzer auf allen Fronten
    battle_type_c = 1

    # 1 = Landungsprovinz der Basisfront
    # 1 = Provinz der Basisfront
    # 1.1 = Provinz der Fortgeschrittenen Front (inklusive Landungsprovinzen)
    # 1.2 = Provinz der Elite-Front (inklusive Landungsprovinzen)
    event_value_c = 1

    #  Elo-Wertungsfaktor, entspricht der Front-Elo-Wertung für Stufe-X-Fahrzeuge.
    elo_c = fun.calcEloC(elo=elo);

    # vom Team im Gefecht verdiente Erfahrung. (own team)
    # maybe must be bruteforced/ calculated for needed points
    team_xp = 1

    # for faster calculation
    enemy_team_xp = 1

    #von den Teams im Gefecht insgesamt verdiente Erfahrung. (both)
    battle_xp = team_xp + enemy_team_xp

    # Teamgröße
    team_size = 15

    # Faktor aus aktiven Boni für Ruhmespunkte je Gefecht (für Clans und Spieler unterschiedlich).
    bonus_p = 0

    #  Faktor aus aktiven Technologien für Ruhmespunkte je Gefecht (für Clans und Spieler unterschiedlich).
    technology_p = 1

    def __init__(self,elo):
        self.elo = elo
        self.elo_c = fun.calcEloC(elo=elo)
        self.fame_points_base = 1500
        self.battle_type_c = 1
        self.event_value_c = 1
        self.team_xp = 1
        self.enemy_team_xp = 1
        self.battle_xp = 1
        self.team_size = 15
        self.bonus_p = 0
        self.technology_p = 1

    def updateXp(self,ownXp, enemyXp):
        self.team_xp = ownXp
        self.enemy_team_xp = enemyXp
        self.battle_xp = ownXp + enemyXp
