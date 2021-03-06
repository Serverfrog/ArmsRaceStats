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

import lombok.AllArgsConstructor;

import java.net.URL;

@AllArgsConstructor
public enum JfxResources {

    MAIN("/de/serverfrog/ar/ui/main.fxml"),
    MATCH_INPUT("/de/serverfrog/ar/ui/tab/matchInput.fxml"),
    CREATE_CLAN("/de/serverfrog/ar/ui/dialog/createClan.fxml"),
    SEARCH_CLAN("/de/serverfrog/ar/ui/dialog/searchClan.fxml");

    private final String path;

    public URL getResource() {
        return getClass().getResource(path);
    }

}
