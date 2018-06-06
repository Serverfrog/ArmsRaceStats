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

package de.serverfrog.ar.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Match implements Fetchable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date matchTime;
    private Outcome outcome;
    @ManyToOne
    private Clan ownClan;
    @ManyToOne
    private Clan enemyClan;
    @OneToMany(fetch = FetchType.LAZY)
    private List<PlayerStat> ownTeam;
    @OneToMany(fetch = FetchType.LAZY)
    private List<PlayerStat> enemyTeam;

    @Override
    public void fetch() {
        this.ownTeam.size();
        this.enemyTeam.size();
    }


    public enum Outcome {
        WIN, DEFEAT, DRAW;
    }

}
