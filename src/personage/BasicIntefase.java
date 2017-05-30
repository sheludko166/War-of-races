package personage;

import java.util.ArrayList;

/**
 * Created by dos on 26.05.2017.
 */
public interface BasicIntefase {


    void attack1(ArrayList attackingTeam, ArrayList modifyAttackingTeam,
                 ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam);

    void attack2(ArrayList<? extends BasicPersona> attackingTeam, ArrayList<? extends BasicPersona> modifyAttackingTeam,
                 ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam);
}
