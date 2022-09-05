import java.util.Random;

public class Population {

    // Generate an initial group of 10 individual
    Number group_of_people[]  = new Number[10];
    Population(){
        for(int i=0; i<group_of_people.length; i++){
            group_of_people[i] = new Number();
        }
    }

    // Select fittest individual
    public int Selection1(){
        int fittest = 0 ;
        int min = group_of_people[0].geneticInformation ;
        for(int i=1; i<group_of_people.length; i++) {
            if (Math.abs(2-group_of_people[i].geneticInformation) <= min) {
                min = Math.abs(2-group_of_people[i].geneticInformation) ;
                fittest = group_of_people[i].geneticInformation ;
            }
        }
        return(fittest);
    }

    // Select second fittest individual
    public int Selection2() {

        int[] copy = new int[10] ;

        for (int i = 0; i < group_of_people.length; i++) {
            copy[i] = group_of_people[i].geneticInformation;
        }
        int n = copy.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (copy[j] > copy[j + 1]) {
                    int temp = copy[j];
                    copy[j] = copy[j + 1];
                    copy[j + 1] = temp;
                }
            }
        }
        return(copy[1]);
    }

    public int Selection3() {
        int leastFit = 0 ;
        int max = group_of_people[0].geneticInformation ;
        for(int i=1; i<group_of_people.length; i++) {
            if (Math.abs(2-group_of_people[i].geneticInformation) >= max) {
                max = Math.abs(2-group_of_people[i].geneticInformation) ;
                leastFit = group_of_people[i].geneticInformation ;
            }
        }
        return(leastFit);
    }

    // Form child by taking first part of parent 1 and mixing with second part of parent 2
    public Number reproduction(Number parent1, Number parent2){

        Random rand = new Random();
        int cut = rand.nextInt(1, 6);

        int[] childGenInfo = new int[8];

        // Start exchanging at the randomly designated cut Gene
        for(int i=0; i<cut; i++){
            childGenInfo[i] = parent1.toBinary(parent1.geneticInformation)[i]; // child copy the parent
        }
        for(int i=cut; i<parent2.toBinary(parent2.geneticInformation).length; i++){
            childGenInfo[i] = parent2.toBinary(parent2.geneticInformation)[i];
        }
/*
        for(int i=0; i<8; i++){
            System.out.print(childGenInfo[i]);
        }
*/
        return(new Number(childGenInfo)); // geneticInformation contains childGenInfo (decimal)
    }

    public Number Mutation(Number child){

        Random rand = new Random();
        int randomGene = rand.nextInt(0,7);
        int[] newGenInfo = child.toBinary(child.geneticInformation);

        if(newGenInfo[randomGene] == 1){
            newGenInfo[randomGene] = 0;
        } else {
            newGenInfo[randomGene] = 1;
        }
        child.geneticInformation = child.toDecimal(newGenInfo);

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

    public boolean repetition(Number[] population){
        int cpt = population[0].geneticInformation ;
        for (int i = 0 ; i < population.length-1 ; i++) {
            if (population[i+1].geneticInformation != population[i].geneticInformation) {
                cpt++ ;
            }
        }
        return cpt <= 5;
    }


}
