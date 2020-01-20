import java.util.Scanner;

public class Solution {
  private static final int valueForModulo = (int) (Math.pow(10, 9) + 7);

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int numberOfTestCases = scanner.nextInt();

    while (numberOfTestCases-- > 0) {
      int numberOfElements = scanner.nextInt();
      long result = bitwiseOR_allElements_modValueForModulo(numberOfElements, scanner);
      System.out.println(result);
    }
    scanner.close();
  }
  
/**
  * The method calculates the total result.
  *
  * The challenge requires to calculate the sum of all possible XOR combinations 
  * of the input element, modulo "Math.pow(10, 9)+7". 
  *
  * The total number of XOR combinations (order of selection does not matter)
  * is Math.pow(2, numberOfElements). If there is a bit at index 'i' with 
  * value '1', then the bit at this index will occur in half the combinations, 
  * i.e. Math.pow(2, numberOfElements - 1).
  *
  * Doing all the possible XOR combinations, and then summing these up, will result 
  * in time out for this challenge. Thus, a streamlined alternative is to do 
  * bitwise OR on all the elements and then shift them to the left by 
  * Math.pow(2, numberOfElements - 1).
  *
  * @return A long value, representing the bitwise OR an all elements, 
  *         modulo "Math.pow(9, 10)+7".
  */
  private static long bitwiseOR_allElements_modValueForModulo(int numberOfElements, Scanner scanner) {
    int exponent = calculate_valueOfExponent(numberOfElements);
    int bitwiseOR = 0;

    while (numberOfElements-- > 0) {
      bitwiseOR = (bitwiseOR | scanner.nextInt());
    }
    return ((long) (bitwiseOR % valueForModulo) * exponent) % valueForModulo;
  }
  
/**
  * A helper method for method "bitwiseOR_allElements_modValueForModulo". 
  *
  * The method calculates the value of the exponent, by which the result of 
  * the bitwise OR on all input elements has to be shifted to the left.
  *
  * The conditions of the challenge stipulate that the total result be modulo of
  * "Math.pow(10,9)+7". Applying the in-built Java libraries first to calulate 
  * value of the exponent, and only then to apply the modulo, will result in 
  * huge values. Thus, the current method is created in order to streamline
  * the application and handle integers with maximum value up to that of 
  * the modulo.
  *
  * @return An integer, representing the number of necessary left shifts, 
  *         modulo "Math.pow(9, 10)+7".
  */       
  private static int calculate_valueOfExponent(int numberOfElements) {
    int exponent = 1;
    for (int i = 0; i < numberOfElements - 1; i++) {
      exponent = (exponent << 1) % valueForModulo;
    }
    return exponent;
  }
}
