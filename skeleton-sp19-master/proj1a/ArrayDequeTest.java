import static org.junit.Assert.*;
import org.junit.Test;

public class ArrayDequeTest {
    @Test
    public void testAll() {
        // test minusOne
        ArrayDeque<Integer> myAD = new ArrayDeque<>();
        int cur_1 = 0;
        int exp_1 = 7;
        assertEquals(exp_1, myAD.minusOne(cur_1));
        int cur_2 = 7;
        int exp_2 = 6;
        assertEquals(exp_2, myAD.minusOne(cur_2));
        // test addFirst
        for(int i = 0; i < 8; i++) {
            myAD.addFirst(i);
        }
        int actual = myAD.get(1);
        assertEquals(2, actual);
        // test addLast
        myAD.addLast(67);
        actual = myAD.get(8);
        assertEquals(67,actual);
    }
    public static void main(String[] args) {
        ArrayDeque<Integer> myAD = new ArrayDeque<>();
        for (int i = 0; i < 9; i++) {
            myAD.addFirst(i);
        }
        for (int i = 0; i < 6; i++) {
            int last = myAD.removeLast();
        }
        for (int i = 0; i < 30; i++){
            myAD.addFirst(i+3);
        }
        System.out.println(myAD.size());
    }

}
