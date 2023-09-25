import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public void solution() {
        int n = sc.nextInt();
        List<Pair> roomList = new ArrayList<>();
        for (int i=0; i<n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            Pair each = new Pair(start, end);
            roomList.add(each);
        }
        Collections.sort(roomList);

        int answer = 0;
        int curTime = 0;

        for (Pair each : roomList) {
            if (each.start >= curTime) {
                curTime = each.end;
                answer++;
            }
        }

        System.out.println(answer);
    }


    public static void main(String[] args) {
        new Main().solution();
    }

    public class Pair implements Comparable<Pair> {
        public int start;
        public int end;

        public Pair (int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(final Pair other) {
            if (this.end == other.end) {
                return this.start - other.start;
            }
            return this.end - other.end;
        }
    }
}