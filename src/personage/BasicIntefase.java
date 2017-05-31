package personage;

import java.util.ArrayList;

public interface BasicIntefase {

    void attack1(ArrayList attackingTeam, ArrayList modifyAttackingTeam,
                 ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam);

    void attack2(ArrayList<? extends BasicPersona> attackingTeam, ArrayList<? extends BasicPersona> modifyAttackingTeam,
                 ArrayList<? extends BasicPersona> defensibleTeam, ArrayList<? extends BasicPersona> modifyDefensibleTeam);
}
