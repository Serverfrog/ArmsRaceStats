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

package de.serverfrog.ar.ui.dialog;

import de.serverfrog.ar.eao.ClanRepository;
import de.serverfrog.ar.entity.Clan;
import de.serverfrog.ar.ui.util.JfxResources;
import de.serverfrog.ar.ui.util.JfxUtil;
import de.serverfrog.ar.ui.util.JfxView;
import de.serverfrog.ar.ui.util.StageContainer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@JfxView(resource = JfxResources.CREATE_CLAN)
@Component
@Scope(SCOPE_PROTOTYPE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateClan extends StageContainer<Clan> implements Initializable {

    private final ClanRepository clanRepository;
    @FXML
    private Spinner<Integer> elo;
    @FXML
    private TextField name;
    @FXML
    private CheckBox own;
    private Clan createdClan;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        elo.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5000));
        JfxUtil.fixSpinner(elo);
    }

    @Override
    protected Clan getValue() {
        return createdClan;
    }

    @Override
    protected String getTitle() {
        return "Create Clan";
    }


    public void savePressed() {
        Clan clan = new Clan();
        clan.setLastElo(elo.getValue());
        clan.setName(name.getText());
        clan.setOwnClan(own.isSelected());
        clanRepository.save(clan);
        createdClan = clan;

        this.closeStage();
    }

}
