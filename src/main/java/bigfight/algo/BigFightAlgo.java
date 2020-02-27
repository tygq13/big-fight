package bigfight.algo;

import java.util.ArrayList;

public class BigFightAlgo {

    public static int[] uniformRandomDistribute(int size, int total) {
        int[] result = new int[size];
        int remainder = total % size;
        int share = total - remainder;
        int personLeft = size;
        double valueLeft = share;
        if (share != 0) {
            for (int i = 0; i < size - 1; i++) {
                // uniform distribution around the average value left
                result[i] = (int) Math.round( Math.random() * (valueLeft / personLeft) * 2 );
                valueLeft -= result[i];
                personLeft -= 1;
            }
            // last person
            result[size - 1] = (int) valueLeft;
        }

        // share the remainder
        for (int i = 0; i < remainder; i++) {
            int index = (int) (Math.random() * size);
            result[index] += 1;
        }
        return result;
    }
}
