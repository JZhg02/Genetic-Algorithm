import java.util.Random;

public class Number {

    // int_random == geneticInformation
    int geneticInformation;

    public Number(){
        // The Number(person)'s genetic information/chromosome
        // int_random = chromosome
        Random rand = new Random();
        this.geneticInformation = rand.nextInt(255);
    }

    public Number(int geneticInfo){
        this.geneticInformation = geneticInfo;
    }

    public Number(int[] binaryGeneticInfo){
        this.geneticInformation = toDecimal(binaryGeneticInfo);
    }

    public int[] toBinary(int n){

        int binary[] = new int[8];
        int cpt = 0;

        while(n>0){
            binary[cpt++] = n%2;
            n=n/2;
        }

        for(int i=0; i<4; i++){
            int temp = binary[i];
            binary[i] = binary[binary.length-1-i];
            binary[binary.length-1-i] = temp;
        }
/*
        for(int i=0; i< binary.length; i++){
            System.out.print(binary[i]);
        }
 */
        return(binary);
    }

    public int power(int n, int p){
        int result = 1;
        for(int i=0; i<p; i++){
            result*=n;
        }
        return(result);
    }

    public int toDecimal(int[] n){
        int sum = 0;
        int cpt = 7;
        for(int i=0; i<8; i++){
            if(n[i]==1){
                sum += this.power(2, cpt);
            }
            cpt--;
        }
        System.out.println("This is genetic sum "+sum);
        return(sum);
    }

    public int fitness_Score(int x){
        return((power((x+3), 2) - 25));
    }
}
