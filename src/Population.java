import java.util.Random;

public class Population {

    // Generate an initial group of 10 individual
    Number group_of_people[]  = new Number[10];
    Population(){
        for(int i=0; i<group_of_people.length; i++){
            group_of_people[i] = new Number();
            System.out.println(group_of_people[i].geneticInformation);
        }
    }

    // Select fittest individual
    public int Selection1(){
        int mostFit = 0;
        for(int i=0; i<group_of_people.length; i++){
            if(group_of_people[i].geneticInformation > mostFit){
                mostFit = group_of_people[i].geneticInformation;
            }
        }
        return(mostFit);
    }

    // Select second fittest individual
    public int Selection2(){
        int mostFit = 0;
        int secondMostFit = 0;
        for(int i=0; i<group_of_people.length; i++){
            if(group_of_people[i].geneticInformation > mostFit){
                secondMostFit = mostFit;
                mostFit = group_of_people[i].geneticInformation;
            }
        }
        return(secondMostFit);
    }

    // Form child by taking first part of parent 1 and mixing with second part of parent 2
    public Number reproduction(Number parent1, Number parent2){

        Random rand = new Random();
        int cut = rand.nextInt(1, 6);

        int[] childGenInfo = new int[8];

        // Start exchanging at the randomly designated cut Gene
        for(int i=0; i<cut; i++){
            childGenInfo[i] = parent1.toBinary(parent1.geneticInformation)[i];
            System.out.println("Adding "+parent1.toBinary(parent1.geneticInformation)[i]+" from parent 1");
        }
        for(int i=cut; i<parent2.toBinary(parent2.geneticInformation).length; i++){
            childGenInfo[i] = parent2.toBinary(parent2.geneticInformation)[i];
            System.out.println("Adding "+parent2.toBinary(parent2.geneticInformation)[i]+" from parent 2");
        }

        for(int i=0; i<8; i++){
            System.out.print(childGenInfo[i]);
        }

        return(new Number(childGenInfo));
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
            if (population[i].geneticInformation == population[i].fitness_Score(population[i].geneticInformation)){
                foundIndividual = true;
            }
        }

        return(foundIndividual);
    }

}
