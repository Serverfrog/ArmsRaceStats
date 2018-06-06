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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@JfxView(resource = JfxResources.SEARCH_CLAN)
@Component
@Scope(SCOPE_PROTOTYPE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchClan extends StageContainer<Clan> implements Initializable {


    private final BeanFactory beanFactory;
    private final ClanRepository clanRepository;
    @FXML
    private TableView<Clan> clans;
    private Clan foundClan;

    @Override
    protected Clan getValue() {
        return foundClan;
    }

    @Override
    protected String getTitle() {
        return "Search Clan";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        refreshClans();

    }


    public void createClan() {
        JfxUtil.openDialog(beanFactory, CreateClan.class, this::addClan);
    }


    public void chooseClan() {
        closeStage();
    }

    private void addClan(Clan c) {
        System.out.println("Created Clan = " + c);
        refreshClans();
    }

    private void refreshClans() {
        ObservableList<Clan> items = clans.getItems();
        items.clear();
        clanRepository.findAll().forEach(items::add);
    }

    @SuppressWarnings("unchecked")
    private void initTable() {

        ObservableList<TableColumn<Clan, ?>> columns = clans.getColumns();

        TableColumn<Clan, String> nameColummn = (TableColumn<Clan, String>) columns.get(0);
        TableColumn<Clan, String> eloColummn = (TableColumn<Clan, String>) columns.get(1);

        nameColummn.setCellValueFactory(new PropertyValueFactory<>("name"));
        eloColummn.setCellValueFactory(new PropertyValueFactory<>("lastElo"));

        clans.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> this.foundClan = newValue);
    }
}
