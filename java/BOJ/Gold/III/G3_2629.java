/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2629
 *
 * ? 제목: 양팔저울
 * ? 시간 제한: 1초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 양팔 저울과 몇 개의 추가 주어졌을 때, 이를 이용하여 입력으로 주어진 구슬의 무게를 확인할 수 있는지를 결정하려고 한다.
 * 무게가 각각 1g과 4g인 두 개의 추가 있을 경우, 주어진 구슬과 1g 추 하나를 양팔 저울의 양쪽에 각각 올려놓아 수평을 이루면 구슬의 무게는 1g이다. 또 다른 구슬이 4g인지를 확인하려면 1g 추 대신 4g 추를 올려놓으면 된다.
 * 구슬이 3g인 경우 아래 <그림 1>과 같이 구슬과 추를 올려놓으면 양팔 저울이 수평을 이루게 된다. 따라서 각각 1g과 4g인 추가 하나씩 있을 경우 주어진 구슬이 3g인지도 확인해 볼 수 있다.
 * <그림 1> 구슬이 3g인지 확인하는 방법 ($\boxed{1}$은 1g인 추, $\boxed{4}$는 4g인 추, ●은 무게를 확인할 구슬)
 * <그림 2>와 같은 방법을 사용하면 구슬이 5g인지도 확인할 수 있다. 구슬이 2g이면 주어진 추를 가지고는 확인할 수 없다.
 * 추들의 무게와 확인할 구슬들의 무게가 입력되었을 때, 주어진 추만을 사용하여 구슬의 무게를 확인 할 수 있는지를 결정하는 프로그램을 작성하시오.
 * <그림 2> 구슬이 5g인지 확인하는 방법
 *
 * ? 입력 & 파싱
 * 첫째 줄에는 추의 개수가 자연수로 주어진다. 추의 개수는 30 이하이다. 둘째 줄에는 추의 무게들이 자연수로 가벼운 것부터 차례로 주어진다. 같은 무게의 추가 여러 개 있을 수도 있다. 추의 무게는 500g이하이며, 입력되는 무게들 사이에는 빈칸이 하나씩 있 다. 세 번째 줄에는 무게를 확인하고자 하는 구슬들의 개수가 주어진다. 확인할 구슬의 개수는 7이하이다. 네 번째 줄에는 확인하고자 하는 구슬들의 무게가 자연수로 주어지며, 입력되는 무게들 사이에는 빈 칸이 하나씩 있다. 확인하고자 하는 구슬의 무게는 40,000보다 작거나 같은 자연수이다.
 *
 * 2    -> n
 * 1 4  -> chu[0] ~ chu[n-1]
 * 2    -> m
 * 3 2  -> beads.get(0) ~ beads.get(m-1)
 *
 * ? 출력
 * 주어진 각 구슬의 무게에 대하여 확인이 가능하면 Y, 아니면 N 을 차례로 출력한다. 출력은 한 개의 줄로 이루어지며, 각 구슬에 대한 답 사이에는 빈칸을 하나씩 둔다.
 *
 * Y N
 *
 * ? 채점 결과
 * * 시간: 0.152초
 * * 메모리: 32MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.III;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G3_2629 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static int[] chu;
    static ArrayList<Integer> beads;

    // * 초기 설정
    static boolean[][] visit;
    static ArrayList<Integer>[][] dp;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. DP(Tabulation) - Start Case 설정
        dp[0][0].add(chu[0]);
        visit[0][chu[0]] = true;

        for (int i = 1; i < n; i++) {
            if (!visit[0][chu[i]]) {
                visit[0][chu[i]] = true;
                dp[0][i].add(chu[i]);
            }

            int next1 = chu[0] + chu[i];
            int next2 = Math.abs(chu[0] - chu[i]);

            if (!visit[i][next1]) {
                visit[i][next1] = true;
                dp[0][i].add(next1);
            }

            if (!visit[i][next2]) {
                visit[i][next2] = true;
                dp[0][i].add(next2);
            }
        }

        // * 3. DP(Tabulation: Bottom-Up)
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dp[i][j] = dp[i-1][j];
                    continue;
                }

                for (int bead : dp[i-1][j]) {
                    dp[i][j].add(bead);

                    int next1 = chu[i] + bead;
                    if (!visit[j][next1]) {
                        visit[j][next1] = true;
                        dp[i][j].add(next1);
                    }

                    int next2 = Math.abs(chu[i] - bead);
                    if (!visit[j][next2]) {
                        visit[j][next2] = true;
                        dp[i][j].add(next2);
                    }
                }
            }
        }

        // * 4. 출력
        StringBuffer sb = new StringBuffer();
        for (int bead : beads) {
            boolean able = false;
            for (int i = 0; i < n; i++) {
                if (visit[i][bead]) {
                    able = true;
                    break;
                }
            }

            if (able) {
                sb.append("Y ");
            } else {
                sb.append("N ");
            }
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    // 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        chu = new int[n];
        visit = new boolean[n][400001];
        dp = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new ArrayList<>();
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            chu[i] = Integer.parseInt(st.nextToken());
        }

        beads = new ArrayList<>();
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            beads.add(Integer.parseInt(st.nextToken()));
        }
    }
}
