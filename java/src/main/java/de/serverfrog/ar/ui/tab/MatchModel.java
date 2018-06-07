/*
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.serverfrog.ar.ui.tab;

import de.serverfrog.ar.entity.Clan;
import de.serverfrog.ar.entity.Match;
import de.serverfrog.ar.entity.PlayerStat;
import javafx.beans.property.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
public class MatchModel {

    private StringProperty ownClanName = new SimpleStringProperty();


    private StringProperty enemyClanName = new SimpleStringProperty();


    private ObjectProperty<Match.Outcome> outcome = new SimpleObjectProperty<>(Match.Outcome.WIN);

    private ObjectProperty<LocalDate> matchDate = new SimpleObjectProperty<>(LocalDate.now());

    private ListProperty<PlayerStat> ownPlayers = new SimpleListProperty<>();
    private ListProperty<PlayerStat> enemyPlayers = new SimpleListProperty<>();

    private Clan ownClan;
    private Clan enemyClan;


    public void updateOwnClan(Clan ownClan) {
        this.ownClan = ownClan;
        if (ownClan != null) {
            ownClanName.set(ownClan.getName());
        } else {
            ownClanName.set("<choose>");
        }
    }

    public void updateEnemyClan(Clan enemyClan) {
        this.enemyClan = enemyClan;
        if (enemyClan != null) {
            enemyClanName.set(enemyClan.getName());
        } else {
            enemyClanName.set("<choose>");
        }
    }

    public Match buildMatch() {
        Match match = new Match();
        match.setOutcome(outcome.get());
        ZoneId zoneId = ZoneId.systemDefault();


        match.setMatchTime(Date.from(matchDate.get().atStartOfDay(zoneId).toInstant()));

        match.setOwnClan(ownClan);
        match.setOwnTeam(ownPlayers.get());
        match.setEnemyClan(enemyClan);
        match.setEnemyTeam(enemyPlayers.get());
        return match;
    }
}
