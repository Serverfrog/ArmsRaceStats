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

package de.serverfrog.ar;

import de.serverfrog.ar.business.BMatch;
import de.serverfrog.ar.eao.ClanRepository;
import de.serverfrog.ar.eao.MatchRepository;
import de.serverfrog.ar.entity.Clan;
import de.serverfrog.ar.entity.Match;
import de.serverfrog.ar.ui.JfxMain;
import javafx.application.Platform;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws InterruptedException {

        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class)
                .headless(false);

        ConfigurableApplicationContext context = builder
                .run(args);
        context.registerShutdownHook();

        JfxMain jfx = context.getBean(JfxMain.class);
        SwingUtilities.invokeLater(() -> jfx.startUp(() -> shutdown(context)));


        MatchRepository bean = context.getBean(MatchRepository.class);
        ClanRepository bean2 = context.getBean(ClanRepository.class);
        BMatch bMatch = context.getBean(BMatch.class);
        jpaTest(bean, bean2, bMatch);
    }

    private static void jpaTest(MatchRepository repository, ClanRepository bean2, BMatch bMatch) {
        Clan enemy = new Clan();
        enemy.setName("LastEnemy");
        Clan own = new Clan();
        own.setName("GR-W2");
        bean2.save(enemy);
        bean2.save(own);

        Match match = new Match();
        match.setOutcome(Match.Outcome.DEFEAT);
        match.setOwnClan(own);
        match.setEnemyClan(enemy);

        Match match1 = new Match();
        match1.setOutcome(Match.Outcome.WIN);
        match1.setOwnClan(own);
        match1.setEnemyClan(enemy);

        Match match2 = new Match();
        match2.setOutcome(Match.Outcome.DRAW);
        match2.setOwnClan(own);
        match2.setEnemyClan(enemy);

        Match match3 = new Match();
        match3.setOutcome(Match.Outcome.WIN);
        match3.setOwnClan(own);
        match3.setEnemyClan(enemy);


        repository.save(match);
        repository.save(match1);
        repository.save(match2);
        repository.save(match3);

        for (Match m : bMatch.findAllMatchesEager()) {
            System.out.println("Findall->" + m);
        }
        System.out.println("------------------------");

        for (Match m : bMatch.findByOutcomeEager(Match.Outcome.WIN)) {
            System.out.println("Findall->" + m);
        }


    }


    private static void shutdown(ConfigurableApplicationContext context) {
        context.stop();
        context.close();

        Platform.exit();
        System.exit(0);
    }
}
