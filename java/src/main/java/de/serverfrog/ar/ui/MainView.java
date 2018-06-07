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

package de.serverfrog.ar.ui;

import de.serverfrog.ar.ui.util.ControllerViewTuple;
import de.serverfrog.ar.ui.util.TabEmbeddable;
import de.serverfrog.ar.ui.util.ViewAnnotationFinder;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainView implements Initializable {

    private final ViewAnnotationFinder viewAnnotationFinder;
    @FXML
    private TabPane tabPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<ControllerViewTuple<TabEmbeddable, ScrollPane>> views = viewAnnotationFinder
                .produceViewsWith(TabEmbeddable.class);

        ObservableList<Tab> tabs = tabPane.getTabs();

        for (ControllerViewTuple<TabEmbeddable, ScrollPane> view : views) {
            TabEmbeddable controller = view.getController();

            Tab tab = new Tab(controller.getName());
            tab.setContent(controller.getContent());
            tabs.add(tab);
        }

    }

}
