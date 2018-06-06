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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;

@Data
public class MatchModel {

    private StringProperty ownClanName = new SimpleStringProperty();


    private StringProperty enemyClanName = new SimpleStringProperty();

    private Clan ownClan;
    private Clan enemyClan;


    public void updateOwnClan(Clan ownClan) {
        this.ownClan = ownClan;
        ownClanName.set(ownClan.getName());
    }

    public void updateEnemyClan(Clan enemyClan) {
        this.enemyClan = enemyClan;
        enemyClanName.set(enemyClan.getName());
    }
}
