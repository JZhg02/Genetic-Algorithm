import java.util.Random;

public class Number {

    int geneticInformation;

    public Number(){
        Random rand = new Random();
        this.geneticInformation = rand.nextInt(255);
    }

    // Create a number with a specific geneticInformation
    public Number(int geneticInfo){
        this.geneticInformation = geneticInfo;
    }

    // Create a number with a specific binary geneticInformation
    public Number(int[] binaryGeneticInfo){
        this.geneticInformation = toDecimal(binaryGeneticInfo);
    }

    // Convert a decimal number to binary
    public int[] toBinary(int n){

        int[] binary = new int[8];
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
        return(binary);
    }

    public int power(int n, int p){
        int result = 1;
        for(int i=0; i<p; i++){
            result*=n;
        }
        return(result);
    }

    // Convert a binary number to decimal
    public int toDecimal(int[] n){
        int sum = 0;
        int cpt = 7;
        for(int i=0; i<8; i++){
            if(n[i]==1){
                sum += this.power(2, cpt);
            }
            cpt--;
        }
        return(sum);
    }

    // Calculate the fitness of the Number
    public int fitness_Score(){
        return((power((this.geneticInformation+3), 2) - 25));
    }

    @Override
    public String toString() {
        return(String.valueOf(this.geneticInformation));
    }
}
