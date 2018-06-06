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
import de.serverfrog.ar.ui.util.JfxResources;
import de.serverfrog.ar.ui.util.JfxUtil;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JfxMain {

    private final BeanFactory beanFactory;


    public void startUp(Runnable onClose) {
        JFrame frame = new JFrame("WoT Arms Race Tool");
        frame.setSize(500, 500);

        JFXPanel jfxPanel = new JFXPanel();
        frame.add(jfxPanel);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onClose.run();
            }
        });

        Platform.runLater(() -> start(jfxPanel));
    }

    private void start(JFXPanel panel) {
        ControllerViewTuple<MainView, GridPane> view = JfxUtil.createView(beanFactory, JfxResources.MAIN);
        Scene scene = new Scene(view.getView());
        panel.setScene(scene);
    }
}
