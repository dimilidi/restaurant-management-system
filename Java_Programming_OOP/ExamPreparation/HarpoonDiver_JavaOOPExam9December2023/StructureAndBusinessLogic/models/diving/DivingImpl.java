package ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.diving;


import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.diver.Diver;
import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.divingSite.DivingSite;

import java.util.Collection;
import java.util.Iterator;
public class DivingImpl implements Diving{
    @Override
    public void searching(DivingSite divingSite, Collection<Diver> divers) {
        // Iterate over each diver
        for (Diver diver : divers) {
            // Check if the diver can dive
            if (diver.canDive()) {
                // Get sea creatures from the diving site
                Collection<String> seaCreatures = divingSite.getSeaCreatures();
                Iterator<String> iterator = seaCreatures.iterator();

                // Start searching sea creatures
                while (iterator.hasNext() && diver.getOxygen() > 0) {
                    String seaCreature = iterator.next();
                    // Shoot the sea creature
                    diver.shoot();
                    // Add the sea creature to the diver's catch
                   diver.getSeaCatch().getSeaCreatures().add(seaCreature);
                    // Remove the sea creature from the diving site
                    iterator.remove();
                }
            }
        }
    }
}
