import jdk.swing.interop.SwingInterOpUtils;

import java.util.Arrays;

public class Main {
    public static void main(String []args){

        // MAIN CODE

        //Initiate first generation
        Population sample = new Population();
        // Iteration counter
        int genCpt = 1;
        System.out.println(sample.toString(genCpt));

        // While we haven't found our individual, redo the process
        while(!sample.checkIndividual(sample.group_of_people) && !sample.termination(genCpt)){
            //Selection of the 2 fittest
            int indexParent1 = 0;
            int indexParent2 = 0;
            for(int i=0; i<sample.group_of_people.length; i++){
                if(sample.selectionFittest() == sample.group_of_people[i].geneticInformation){
                    indexParent1 = i;
                }
                else if (sample.selection2Fittest() == sample.group_of_people[i].geneticInformation){
                    indexParent2 = i;
                }
            }
            Number newChild = sample.reproduction(sample.group_of_people[indexParent1], sample.group_of_people[indexParent2]);
            newChild = sample.Mutation(newChild);
            //Selection of the least fit
            int indexLeastFit = 0;
            for(int i=0; i<sample.group_of_people.length; i++){
                if(sample.selectionLeastFit() == sample.group_of_people[i].geneticInformation){
                    indexLeastFit = i;
                }
            }
            // Change Least fit with new Child
            for(int i=0; i<sample.group_of_people.length; i++){
                if (sample.group_of_people[i] == sample.group_of_people[indexLeastFit]){
                    System.out.println("Replacing "+sample.group_of_people[indexLeastFit]+" with "+newChild+"\n");
                    sample.group_of_people[i] = newChild;
                }
            }

            genCpt++;
            System.out.println(sample.toString(genCpt));
        }

        if (!sample.termination(genCpt)){
            System.out.println("Found him!");
        } else {
            System.out.println("Can't find him...");
        }

    }
}

