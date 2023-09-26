import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public void solution() {
        int n = sc.nextInt();
        int d = sc.nextInt();
        int k = sc.nextInt();
        int c = sc.nextInt();

        int[] sushi = new int[n];
        int[] eat = new int[d+1];

        for (int i=0; i<n; i++) {
            sushi[i] = sc.nextInt();
        }

        int answer = 0;

        for (int i=0; i<k; i++) {
            if (eat[sushi[i]]==0) answer++;
            eat[sushi[i]]++;
        }
        if (eat[c]==0) answer++;
        eat[c]++;

        int temp = answer;

        for (int j=1; j<=n; j++) {
            int rm = (j-1)%n;
            int end = (j+k-1)%n;

            eat[sushi[rm]]--;
            if (eat[sushi[rm]]==0) temp--;

            if (eat[sushi[end]]==0) temp++;
            eat[sushi[end]]++;

            if (eat[c]==0) temp++;
            eat[c]++;

            answer = Math.max(temp, answer);
        }

        System.out.println(answer);
    }

    public static void main(String[] args) {
        new Main().solution();
    }

}