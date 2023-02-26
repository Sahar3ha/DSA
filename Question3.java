package DSA.Assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Question3 {
    public static   void check( int[] array){
        int l= array.length;
        int mid=l/2;
        int[] subarray=new int[mid];
        int[] subarray1=new int[mid];
        for (int i=0; i<mid;i++){
            subarray[i]=array[i];
            subarray1[i]=array[mid+i];
        }
        ArrayList<Integer> difference=new ArrayList<>();
        int[] temp = subarray.clone();
        int[] temp1 = subarray1.clone();
        difference.add(multiply(temp,temp1,mid));
        for (int j=0;j<mid;j++) {
            for (int z = 0; z < mid; z++) {
                temp[j] = subarray1[z];
                temp1[z] = subarray[j];
                difference.add(multiply(temp,temp1,mid));
                System.out.println(Arrays.toString(temp));
                System.out.println(Arrays.toString(temp1));
                temp = subarray.clone();
                temp1 = subarray1.clone();
            }
        }
        System.out.println("Final output:"+ Collections.min(difference));
    }
    public static int multiply(int[] temp,int[] temp1,int mid){
        int keep=1;
        int keep1=1;
        for (int x=0;x<mid;x++) {
            keep = keep * temp[x];
            keep1 = keep1 * temp1[x];
        }
        return Math.abs(keep-keep1);
    }

    public static void main(String[] args) {
        int[] array={1,2,3,5};
        check(array);
    }


    //Question 3 (b)
    public static boolean matchesPattern(String str, String pattern) {
        int n = str.length();
        int m = pattern.length();
        int i = 0, j = 0;

        while (i < n && j < m) {
            if (pattern.charAt(j) == '@') {
                j++;
                // Match the rest of the string
                while (i < n && j < m && str.charAt(i) == pattern.charAt(j)) {
                    i++;
                    j++;
                }
                // If we have reached the end of the pattern, the pattern matches the string
                if (j == m) {
                    return true;
                }
                // If not, move the pointer in the string back to the previous match position
                while (i > 0 && str.charAt(i - 1) != pattern.charAt(j - 1)) {
                    i--;
                }
            } else if (pattern.charAt(j) == '#') {
                // If pattern contains '#', match any single character
                i++;
                j++;
            } else {
                // If pattern contains a character, it should match the corresponding character in the string
                if (pattern.charAt(j) != str.charAt(i)) {
                    return false;
                }
                i++;
                j++;
            }
        }

        // If we have reached the end of both the string and the pattern, the pattern matches the string
        return i == n && j == m;
    }
}
