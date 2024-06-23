import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
public class Main {
    static long[] cow;
    static int MIN = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        cow = new long[101];
        for (int i = 0; i < N; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int amt = sc.nextInt();
            for (int j = s; j <= e; j++) {
                cow[j] += amt;
            }
        }
        Conditioning[] cond = new Conditioning[M];
        for (int i = 0; i < M; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int amt = sc.nextInt();
            int cost = sc.nextInt();
            cond[i] = new Conditioning(s, e, amt, cost);
        }

        recReduction(cow, cond, 0, 0);
        System.out.println(MIN);

    }

    static void recReduction(long[] cow, Conditioning[] cond, int ind, int cost) {
        boolean clear = true;

        for (long l : cow) {
            if (l > 0) {
                clear = false;
                break;
            }
        }

        if (clear) {
            MIN = Math.min(MIN, cost);
        } else {
            if (ind < cond.length) {
                long[] copy = cow.clone();
                recReduction(copy, cond, ind +1, cost);

                Conditioning c = cond[ind];
                for (int i = c.beg; i <= c.end; i++) {
                    copy[i] -= c.amt;
                }
                recReduction(copy, cond, ind + 1, cost + c.cost);
            }
        }
    }
}

class Conditioning {
    int beg, end, amt, cost;
    public Conditioning(int b, int e, int a, int c) {
        beg = b;
        end = e;
        amt = a;
        cost = c;
    }


}
