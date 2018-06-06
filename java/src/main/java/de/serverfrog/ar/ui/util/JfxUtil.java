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
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.BeanFactory;

import java.io.IOException;
import java.util.function.Consumer;

public class JfxUtil {

    private JfxUtil() {
        throw new UnsupportedOperationException("Privat Constructor");
    }


    public static <C extends StageContainer<E>, E>
    void openDialog(BeanFactory beanFactory, Class<C> dialogClass, Consumer<E> consumer) {
        ControllerViewTuple<C, GridPane> view = createView(beanFactory, dialogClass);

        Stage stage = new Stage();

        C controller = view.getController();
        controller.setStageSupplier(() -> stage);
        controller.setReturnConsumer(consumer);

        GridPane view1 = view.getView();
        Scene scene = new Scene(view1);

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(controller.getTitle());
        stage.show();
    }

    public static <C, V> ControllerViewTuple<C, V> createView(BeanFactory beanFactory, Class<C> viewClass) {
        JfxView annotation = viewClass.getAnnotation(JfxView.class);

        if (annotation == null) {
            throw new UnsupportedOperationException("View has no JfxView Annotation. Class=" + viewClass);
        }

        JfxResources resource = annotation.resource();
        return createView(beanFactory, resource);
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


    public static <V> void fixSpinner(Spinner<V> spinner) {
        SpinnerValueFactory<V> factory = spinner.getValueFactory();
        TextFormatter<V> formatter = new TextFormatter<>(factory.getConverter(), factory.getValue());
        spinner.getEditor().setTextFormatter(formatter);
        // bidi-bind the values
        factory.valueProperty().bindBidirectional(formatter.valueProperty());
    }
}
