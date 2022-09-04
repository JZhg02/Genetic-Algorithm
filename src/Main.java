import java.util.Arrays;

public class Main {
    public static void main(String []args){

/*
        //Test Binary and Decimal functions
        Number number = new Number();

        number.toBinary(number.geneticInformation);
        System.out.println(number.geneticInformation);
        for(int i=0; i<8; i++){
            System.out.print(number.toBinary(number.geneticInformation)[i]);
        }
        System.out.println(number.toDecimal(number.toBinary(number.geneticInformation)));
*/

        // Test population

        Population pop = new Population();
        System.out.println("This is res:"+pop.group_of_people[2].geneticInformation);

        // Test reproduction function
        System.out.println("These are the 2 individuals' genetic information: "
                + Arrays.toString(pop.group_of_people[0].toBinary(pop.group_of_people[0].geneticInformation)) +" & "
                + Arrays.toString(pop.group_of_people[1].toBinary(pop.group_of_people[1].geneticInformation)));

        Number testChild = new Number();
        testChild = pop.reproduction(pop.group_of_people[0], pop.group_of_people[1]);
        for(int i=0; i<8; i++){
            System.out.print(testChild.toBinary(testChild.geneticInformation)[i]);
        }


        System.out.println("\n");
        // Test mutation function

        testChild = pop.Mutation(testChild);
        for(int i=0; i<8; i++){
            System.out.print(testChild.toBinary(testChild.geneticInformation)[i]);
        }

        // MAIN CODE

        //Initiate first generation
        Population sample = new Population();
        System.out.println("Population: ");
        System.out.printf("[ ");
        for(int i=0; i<sample.group_of_people.length; i++){
            System.out.print(sample.group_of_people[i].geneticInformation+" ");
        }
        System.out.printf("]");

        // While we haven't found our individual, redo the process
        while(!sample.checkIndividual(sample.group_of_people)){
            //Selection of the 2 fittest
            //Number newChild = sample.reproduction(fittest1, fittest2);
            //newChild = mutation(newChild);
            //Selection of the least fit
            //Replace least fit with child in sample[]
        }

        System.out.println("Found him!");
        System.out.println("SJHDKQSJBDQ.BD.Q");

    }
}

