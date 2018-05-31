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

package de.serverfrog.ar.ui.util;

import de.serverfrog.ar.util.SilentThrow;
import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.BeanFactory;

import java.io.IOException;

public class JfxUtil {

    private JfxUtil() {
        throw new UnsupportedOperationException("Privat Constructor");
    }


    public static <C, V> ControllerViewTuple<C, V> createView(BeanFactory beanFactory, JfxResources resources) {
        FXMLLoader fxmlLoader = new FXMLLoader(resources.getResource());
        fxmlLoader.setControllerFactory(beanFactory::getBean);
        try {
            V view;
            view = fxmlLoader.load();
            C controller = fxmlLoader.getController();
            return new ControllerViewTuple<>(controller, view);
        } catch (IOException e) {
            e.printStackTrace();
            SilentThrow.throwing(e);
            return null;
        }
    }
}
