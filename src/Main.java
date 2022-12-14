public class Main {
    public static void main(String []args){

        // MAIN CODE

        //Initiate first generation
        Population sample = new Population();

        // Iteration counter
        int genCpt = 1;
        System.out.println(sample.toString(genCpt));

        int previousParent1 = 0;
        int previousParent2 = 0;

        // While we haven't found our individual, redo the process
        while(!sample.checkIndividual(sample.group_of_people) && !sample.termination(genCpt)){

            //Selection of the 2 fittest
            int indexParent1 = 0;
            int indexParent2 = 0;
            for(int i=0; i<sample.group_of_people.length; i++){ // take the index of the fittest parent
                if(sample.selectionFittest() == Math.abs(sample.group_of_people[i].fitness_Score())){
                    indexParent1 = i;
                }
            }
            for(int i=0; i<sample.group_of_people.length; i++){ // the index of the 2nd fittest parent
                if (sample.selection2Fittest() == Math.abs(sample.group_of_people[i].fitness_Score())){
                    indexParent2 = i;
                }
            }

            // Creation of child
            Number newChild = new Number();
            // Mutate
            if (sample.group_of_people[indexParent1].geneticInformation == previousParent1 &&
                    sample.group_of_people[indexParent2].geneticInformation == previousParent2) { // switch the 2 parents if they are the same
                newChild = sample.reproduction(sample.group_of_people[indexParent2], sample.group_of_people[indexParent1]);
                previousParent1 = sample.group_of_people[indexParent2].geneticInformation;
                previousParent2 = sample.group_of_people[indexParent1].geneticInformation;
            } else {
                newChild = sample.reproduction(sample.group_of_people[indexParent1], sample.group_of_people[indexParent2]);
                previousParent1 = sample.group_of_people[indexParent1].geneticInformation;
                previousParent2 = sample.group_of_people[indexParent2].geneticInformation;
            }

            // Mutate child
            newChild = sample.Mutation(newChild);

            //Selection of the least fit
            int indexLeastFit = 0;
            for(int i=0; i<sample.group_of_people.length; i++){
                if(sample.selectionLeastFit() == Math.abs(sample.group_of_people[i].fitness_Score())){
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

