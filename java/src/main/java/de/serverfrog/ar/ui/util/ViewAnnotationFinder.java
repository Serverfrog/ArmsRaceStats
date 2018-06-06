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

import javafx.scene.layout.GridPane;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ViewAnnotationFinder {

    private final ApplicationContext context;

    @SuppressWarnings("unchecked")
    public <T extends TabEmbeddable> List<ControllerViewTuple<T, GridPane>> produceViewsWith(Class<T> interFace) {
        String[] classNames = context.getBeanNamesForType(interFace);

        List<ControllerViewTuple<T, GridPane>> views = new ArrayList<>();

        for (String className : classNames) {
            Class<T> clazz = (Class<T>) context.getType(className);


            views.add(JfxUtil.createView(context, clazz));
        }

        return views;
    }

    public <T> Map<String, T> findViewsWith(Class<T> interFace) {
        return context.getBeansOfType(interFace);
    }
}
