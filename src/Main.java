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
/*
        Population pop = new Population();

        System.out.println("\n");
        Number n = pop.Selection1();
        Number n2 = pop.Selection2();
        Number n3 = pop.Selection3();
        System.out.println(n.geneticInformation);
        System.out.println(n2.geneticInformation);
        System.out.println(n3.geneticInformation);


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
            System.out.println(testChild.toBinary(testChild.geneticInformation)[i]);
        }
*/
        // MAIN CODE

        //Initiate first generation
        Population sample = new Population();
        int genCpt = 1;
        System.out.printf("Population %d:", genCpt);
        System.out.printf("[ ");
        for(int i=0; i<sample.group_of_people.length; i++){
            System.out.print(sample.group_of_people[i].geneticInformation+" ");
        }
        System.out.printf("]\n");

        // While we haven't found our individual, redo the process
        // while(!sample.checkIndividual(sample.group_of_people) && !sample.repetition(sample.group_of_people))
        while(!sample.checkIndividual(sample.group_of_people) && !sample.termination(genCpt)){
            //Selection of the 2 fittest
            int indexParent1 = 0;
            int indexParent2 = 0;
            for(int i=0; i<sample.group_of_people.length; i++){
                if(sample.Selection1() == sample.group_of_people[i].geneticInformation){
                    indexParent1 = i;
                }
                else if (sample.Selection2() == sample.group_of_people[i].geneticInformation){
                    indexParent2 = i;
                }
            }
            Number newChild = sample.reproduction(sample.group_of_people[indexParent1], sample.group_of_people[indexParent2]);
            newChild = sample.Mutation(newChild);
            //Selection of the least fit
            int indexLeastFit = 0;
            for(int i=0; i<sample.group_of_people.length; i++){
                if(sample.Selection3() == sample.group_of_people[i].geneticInformation){
                    indexLeastFit = i;
                }
            }
            // Change Least fit with new Child
            for(int i=0; i<sample.group_of_people.length; i++){
                if (sample.group_of_people[i] == sample.group_of_people[indexLeastFit]){
                    sample.group_of_people[i] = newChild;
                }
            }

            genCpt++;
            System.out.printf("Population %d: ", genCpt );
            System.out.printf("[ ");
            for(int i=0; i<sample.group_of_people.length; i++){
                System.out.print(sample.group_of_people[i].geneticInformation+" ");
            }
            System.out.printf("]\n");
        }

        /*
        if (!sample.repetition(sample.group_of_people)){
            System.out.println("Found him!");
        } else {
            System.out.println("Can't find him...");
        }*/

        if (!sample.termination(genCpt)){
            System.out.println("Found him!");
        } else {
            System.out.println("Can't find him...");
        }

    }
}

