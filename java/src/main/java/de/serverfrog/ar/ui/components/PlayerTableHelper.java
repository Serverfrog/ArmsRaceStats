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

package de.serverfrog.ar.ui.components;

import de.serverfrog.ar.entity.Player;
import de.serverfrog.ar.entity.PlayerStat;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.NonNull;

import java.util.function.Function;

public class PlayerTableHelper {


    public static void prepareTable(TableView<PlayerStat> stats) {
        stats.setEditable(true);

        ObservableList<TableColumn<PlayerStat, ?>> columns = stats.getColumns();
        createNameColumn(columns);

        TableColumn<PlayerStat, Integer> damageColumn = createColumn("Damage", "damage",
                event -> event.getRowValue().setDamage(event.getNewValue()), Integer::valueOf);
        columns.add(damageColumn);

        TableColumn<PlayerStat, Integer> teamDamageColumn = createColumn("Team Damage", "teamDamage",
                event -> event.getRowValue().setTeamDamage(event.getNewValue()), Integer::valueOf);
        columns.add(teamDamageColumn);

        TableColumn<PlayerStat, Integer> supportColumn = createColumn("Support", "support",
                event -> event.getRowValue().setSupport(event.getNewValue()), Integer::valueOf);
        columns.add(supportColumn);

        TableColumn<PlayerStat, Integer> blockedColumn = createColumn("Blocked", "blocked",
                event -> event.getRowValue().setBlocked(event.getNewValue()), Integer::valueOf);
        columns.add(blockedColumn);

//        SPECIAL CASE

        createSurvivedColumn(columns);

//        SPECIAL CASE


        TableColumn<PlayerStat, Integer> xpColumn = createColumn("XP", "xp",
                event -> event.getRowValue().setXp(event.getNewValue()), Integer::valueOf);
        columns.add(xpColumn);

        TableColumn<PlayerStat, Integer> shootsColumn = createColumn("Shoots", "shoots",
                event -> event.getRowValue().setShoots(event.getNewValue()), Integer::valueOf);
        columns.add(shootsColumn);

        TableColumn<PlayerStat, Integer> shootsHitColumn = createColumn("Shoots Hits", "shootsHits",
                event -> event.getRowValue().setShootsHits(event.getNewValue()), Integer::valueOf);
        columns.add(shootsHitColumn);

        TableColumn<PlayerStat, Integer> shootsPenColumn = createColumn("Shoots Pen", "shootsPen",
                event -> event.getRowValue().setShootsPen(event.getNewValue()), Integer::valueOf);
        columns.add(shootsPenColumn);

        TableColumn<PlayerStat, Integer> shootsSplashedColumn = createColumn("Shoots Splashed", "splashed",
                event -> event.getRowValue().setSplashed(event.getNewValue()), Integer::valueOf);
        columns.add(shootsSplashedColumn);

        TableColumn<PlayerStat, Integer> hitsColumn = createColumn("Hits", "hits",
                event -> event.getRowValue().setHits(event.getNewValue()), Integer::valueOf);
        columns.add(hitsColumn);

        TableColumn<PlayerStat, Integer> hitsPenColumn = createColumn("Hits Pen", "hitsPen",
                event -> event.getRowValue().setHitsPen(event.getNewValue()), Integer::valueOf);
        columns.add(hitsPenColumn);

    }


    private static void createSurvivedColumn(ObservableList<TableColumn<PlayerStat, ?>> columns) {
        TableColumn<PlayerStat, Boolean> survivedColumn = createColumn("Survived", "survived",
                event -> event.getRowValue().setSurvived(event.getNewValue()), Boolean::valueOf);
        columns.add(survivedColumn);
    }

    public static <T> TableColumn<PlayerStat, T> createColumn(@NonNull String label, @NonNull String property,
                                                              @NonNull EventHandler<TableColumn.CellEditEvent<PlayerStat, T>> update,
                                                              @NonNull Function<String, T> converter) {
        TableColumn<PlayerStat, T> damageColumn = new TableColumn<>(label);
        damageColumn.setCellValueFactory(new PropertyValueFactory<>(property));
        damageColumn.setOnEditCommit(update);
        damageColumn.setCellFactory(param -> new TextFieldEditingCell<>(converter));
        return damageColumn;
    }


    private static void createNameColumn(ObservableList<TableColumn<PlayerStat, ?>> columns) {
        TableColumn<PlayerStat, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(param -> {

            PlayerStat value = param.getValue();
            if (value.getPlayer() == null) {
                Player player = new Player();
                player.setName("Name = " + Math.random());
                value.setPlayer(player);
            }
            return new SimpleStringProperty(value.getPlayer().getName());
        });

        nameColumn.setOnEditCommit(event -> event.getRowValue().getPlayer().setName(event.getNewValue()));
        nameColumn.setCellFactory(param -> new TextFieldEditingCell<>((s) -> s));
        columns.add(nameColumn);
    }


}
