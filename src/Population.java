import java.util.Random;

public class Population {

    // Generate an initial group of 10 individuals
    Number[] group_of_people = new Number[10];
    Population(){
        for(int i=0; i<group_of_people.length; i++){
            group_of_people[i] = new Number();
        }
    }

    // Select the fittest individual (closest to 2)
    public int selectionFittest(){
        int fittestScore = Math.abs(group_of_people[0].fitness_Score()) ;
        for(int i=1; i<group_of_people.length; i++) {
            if (Math.abs(group_of_people[i].fitness_Score()) < fittestScore) {
                fittestScore = Math.abs(group_of_people[i].fitness_Score()) ;
            }
        }
        return(fittestScore);
    }

    // Select the second fittest individual (Bubble-sorted an array and took the 2nd lowest number)
    public int selection2Fittest() {

        int[] copy = new int[10] ;

        for (int i = 0; i < group_of_people.length; i++) {
            copy[i] = Math.abs(group_of_people[i].fitness_Score());
        }
        for (int i = 0; i < copy.length - 1; i++) { // arrange from the smallest from the highest
            for (int j = 0; j < copy.length - i - 1; j++) {
                if (copy[j] > copy[j+1]) { // switch if it's higher than the next one
                    int temp = copy[j];
                    copy[j] = copy[j + 1];
                    copy[j + 1] = temp;
                }
            }
        }
        return(copy[1]); // take the second highest
    }

    // Select the least fit individual
    public int selectionLeastFit() {
        int leastFit = Math.abs(group_of_people[0].fitness_Score()) ;
        for(int i=1; i<group_of_people.length; i++) {
            if (Math.abs(group_of_people[i].fitness_Score()) > leastFit) { // farthest from 0
                leastFit = Math.abs(group_of_people[i].fitness_Score()) ;
            }
        }
        return(leastFit);
    }

    // Form a child (new Number) by taking first part of parent 1 and mixing it with second part of parent 2
    public Number reproduction(Number parent1, Number parent2){

        System.out.println("These are the parents: "+parent1+" & "+parent2);
        Random rand = new Random();
        int cut = rand.nextInt(1, 7); // can't be 0 or 8

        int[] childGenInfo = new int[8];

        // Start exchanging at the randomly designated cut Gene
        System.out.print("[");
        for(int i=0; i<cut; i++){
            childGenInfo[i] = parent1.toBinary(parent1.geneticInformation)[i]; // child copy half the parent 1
            System.out.print(" "+parent1.toBinary(parent1.geneticInformation)[i]+" ");
        }
        System.out.print(" + ");
        for(int i=cut; i<parent2.toBinary(parent2.geneticInformation).length; i++){ // and half the parent 2
            childGenInfo[i] = parent2.toBinary(parent2.geneticInformation)[i];
            System.out.print(" "+parent2.toBinary(parent2.geneticInformation)[i]+" ");
        }
        Number child  = new Number(childGenInfo); // transform in decimal
        System.out.println("] = "+child.geneticInformation);
        return(new Number(childGenInfo)); // geneticInformation contains childGenInfo (decimal)
    }

    // Randomly mutate 1 gene
    public Number Mutation(Number child){

        Random rand = new Random();
        int randomGene = rand.nextInt(0,8); // choose a random number from 0 to 7
        int[] newGenInfo = child.toBinary(child.geneticInformation); // decimal of the child to binary

        if(newGenInfo[randomGene] == 1){
            newGenInfo[randomGene] = 0;
        } else {
            newGenInfo[randomGene] = 1;
        }
        child.geneticInformation = child.toDecimal(newGenInfo); // binary to decimal

        System.out.print("Newly mutated gene: ");
        System.out.print("[");
        for(int i=0; i<newGenInfo.length; i++){
            System.out.print(" "+ newGenInfo[i] +" "); // binary
        }
        System.out.println("] = "+child.geneticInformation); // decimal
        return(child);
    }

    // Randomly mutate 2 (or more) genes
    // This creates a lot more variations, hence why it's much harder to find an individual to the solution
    public Number Mutation2(Number child){

        int[] newGenInfo = child.toBinary(child.geneticInformation); // decimal to binary

        Random rand = new Random();

        int randomGene = rand.nextInt(0,8);
        if(newGenInfo[randomGene] == 1){
            newGenInfo[randomGene] = 0;
        } else {
            newGenInfo[randomGene] = 1;
        }

        int randomGene2 = rand.nextInt(0,8);
        while (randomGene2 == randomGene)
            randomGene2 = rand.nextInt(0,8);
        if (newGenInfo[randomGene2] == 1) {
            newGenInfo[randomGene2] = 0 ;
        }
        else
            newGenInfo[randomGene2] = 1 ;


        /*
        int randomGene3 = rand.nextInt(0,8);
        while (randomGene3 == randomGene || randomGene3 == randomGene2)
            randomGene3 = rand.nextInt(0,8);
        if (newGenInfo[randomGene3] == 1) {
            newGenInfo[randomGene3] = 0 ;
        }
        else
            newGenInfo[randomGene3] = 1 ;

        */
        /*
        int randomGene4 = rand.nextInt(0,8);
        while (randomGene4 == randomGene || randomGene4 == randomGene2 || randomGene4 == randomGene3)
            randomGene4 = rand.nextInt(0,8);
        if (newGenInfo[randomGene4] == 1) {
            newGenInfo[randomGene4] = 0 ;
        }
        else
            newGenInfo[randomGene4] = 1 ;
        */

        child.geneticInformation = child.toDecimal((newGenInfo)) ; // binary to decimal

        return(child);
    }

    // Function that checks if there's an individual that is a perfect fit to the equation in the population
    public boolean checkIndividual(Number[] population){

        boolean foundIndividual = false;
        for(int i=0; i<population.length; i++){
            if (population[i].geneticInformation == 2){
                foundIndividual = true;
            }
        }
        return(foundIndividual);
    }

    // Terminate the process when we get to the 1000th iteration
    public boolean termination(int i){
        if(i>1000){
            return true;
        }
        return false;
    }

    // Taking in an argument, so I'm not overriding any method here, this is like a new method
    public String toString(int i) {
        StringBuilder newString = new StringBuilder();
        newString.append("Population ");
        newString.append(i);
        newString.append(" : [ ");
        for(int j=0; j<this.group_of_people.length; j++) {
            newString.append(this.group_of_people[j]).append(" ");
        }
        newString.append("]");
        return(newString.toString());
    }
}
