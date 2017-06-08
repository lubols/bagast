
import static java.lang.Math.abs;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lenovo
 */
public class RandomNumbers {

  // private int[] numbers;

    public RandomNumbers() {
       // numbers = new int[10];;
    }

//    public static void main(String[] args) throws Exception {
//    RandomNumbers numbers = new RandomNumbers();
//    int[] someArr = {1,2,3,4,5,6,7,8,9,10};
//        for (int i = 0; i <= 10; i++){
//         for(int j = 0; j<=someArr.length-1; j++){
//             System.out.print(someArr[j]+" ");
//            
//         }
//         someArr= numbers.getRandNumbers(someArr);
//            System.out.println();
//     }
//    }
/**
 * Funkcia generujúca náhodné čísla
 * do pola vloží na prvé miesto náhodné čísla, ostané posunie
 * @param arr pole hodnôt  
 * @param min dolná hranica generovaných hodnôt
 * @param max horná hranica generovaných hodnôt
 * @return pole s novou náhodnou hodnotou na prvom mieste
 */
    public int[] getRandNumbers(int[] arr, int min, int max) {
       Random rnd = new Random();
        for(int i = arr.length -1; i>0 ; i--){
          arr[i]=arr[i-1];
       }
        int num = abs(min)+abs(max);
        
        arr[0]=min + rnd.nextInt(num);
        return arr;
    }
}
