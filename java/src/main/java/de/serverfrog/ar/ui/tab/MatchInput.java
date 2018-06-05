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

import de.serverfrog.ar.entity.Match;
import de.serverfrog.ar.ui.util.JfxResources;
import de.serverfrog.ar.ui.util.JfxView;
import de.serverfrog.ar.ui.util.TabEmbeddable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@JfxView(resource = JfxResources.MATCH_INPUT)
@Component
@Scope(SCOPE_PROTOTYPE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MatchInput implements TabEmbeddable, Initializable {

    @FXML
    private DatePicker matchDate;

    @FXML
    private ComboBox outcome;

    @FXML
    private GridPane main;

    @Override
    public String getName() {
        return "Match Input";
    }

    @Override
    public GridPane getContent() {
        return main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        outcome.getItems().addAll(Match.Outcome.values());
        outcome.getSelectionModel().clearAndSelect(0);
    }
}
