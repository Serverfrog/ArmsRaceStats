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

import de.serverfrog.ar.business.BMatch;
import de.serverfrog.ar.entity.Match;
import de.serverfrog.ar.entity.PlayerStat;
import de.serverfrog.ar.ui.components.PlayerTableHelper;
import de.serverfrog.ar.ui.dialog.SearchClan;
import de.serverfrog.ar.ui.util.JfxResources;
import de.serverfrog.ar.ui.util.JfxUtil;
import de.serverfrog.ar.ui.util.JfxView;
import de.serverfrog.ar.ui.util.TabEmbeddable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@JfxView(resource = JfxResources.MATCH_INPUT)
@Component
@Scope(SCOPE_PROTOTYPE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MatchInput implements TabEmbeddable, Initializable {


    private final BMatch matchRepository;

    private final BeanFactory beanFactory;

    @FXML
    private TableView<PlayerStat> ownPlayers;

    @FXML
    private TableView<PlayerStat> enemyPlayers;

    @FXML
    private Label ownClanName;

    @FXML
    private DatePicker matchDate;

    @FXML
    private ComboBox<Match.Outcome> outcome;

    @FXML
    private ScrollPane main;
    @FXML
    private Label enemyClanName;

    private MatchModel model;

    @Override
    public String getName() {
        return "Match Input";
    }

    @Override
    public ScrollPane getContent() {
        return main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PlayerTableHelper.prepareTable(ownPlayers);
        PlayerTableHelper.prepareTable(enemyPlayers);

        prepareModel();
    }

    private void prepareModel() {

        this.model = new MatchModel();
        matchDate.setValue(LocalDate.now());

        outcome.getItems().addAll(Match.Outcome.values());
        outcome.getSelectionModel().clearAndSelect(0);

        ownClanName.textProperty().bindBidirectional(model.getOwnClanName());
        enemyClanName.textProperty().bindBidirectional(model.getEnemyClanName());

        model.getOutcome().bindBidirectional(outcome.valueProperty());
        model.getMatchDate().bindBidirectional(matchDate.valueProperty());
        model.getOwnPlayers().bindBidirectional(ownPlayers.itemsProperty());
        model.getEnemyPlayers().bindBidirectional(enemyPlayers.itemsProperty());
    }

    public void clearModel() {
        model.getOwnPlayers().clear();
        model.getEnemyPlayers().clear();
        model.updateOwnClan(null);
        model.updateEnemyClan(null);

        ownClanName.textProperty().unbindBidirectional(model.getOwnClanName());
        enemyClanName.textProperty().unbindBidirectional(model.getEnemyClanName());

        model.getOutcome().unbindBidirectional(outcome.valueProperty());
        model.getMatchDate().unbindBidirectional(matchDate.valueProperty());
        model.getOwnPlayers().unbindBidirectional(ownPlayers.itemsProperty());
        model.getEnemyPlayers().unbindBidirectional(enemyPlayers.itemsProperty());
    }

    public void chooseOwnClan() {
        JfxUtil.openDialog(beanFactory, SearchClan.class, (c) -> this.model.updateOwnClan(c));
    }

    public void chooseEnemyClan() {
        JfxUtil.openDialog(beanFactory, SearchClan.class, (c) -> this.model.updateEnemyClan(c));
    }

    public void newEnemyPlayer() {
        enemyPlayers.getItems().add(new PlayerStat());
    }

    public void newOwnPlayer() {
        ownPlayers.getItems().add(new PlayerStat());
    }

    public void saveMatch() {
        matchRepository.save(model.buildMatch());
        clearModel();
        prepareModel();
    }
}
