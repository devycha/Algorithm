/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/1613
 *
 * ! 시간 제한: 2초
 * ! 메모리 제한: 128MB
 *
 * ! 채점 결과
 * 메모리: 36356KB
 * 시간: 476ms
 * 언어: JAVA
 *
 * ! 문제
 * 역사, 그 중에서도 한국사에 해박한 세준이는 많은 역사적 사건들의 전후 관계를 잘 알고 있다. 즉, 임진왜란이 병자호란보다 먼저 일어났으며, 무오사화가 기묘사화보다 먼저 일어났다는 등의 지식을 알고 있는 것이다.
 * 세준이가 알고 있는 일부 사건들의 전후 관계들이 주어질 때, 주어진 사건들의 전후 관계도 알 수 있을까? 이를 해결하는 프로그램을 작성해 보도록 하자.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 첫 줄에 사건의 개수 n(400 이하의 자연수)과 알고 있는 사건의 전후 관계의 개수 k(50,000 이하의 자연수)가 주어진다. 다음 k줄에는 전후 관계를 알고 있는 두 사건의 번호가 주어진다. 이는 앞에 있는 번호의 사건이 뒤에 있는 번호의 사건보다 먼저 일어났음을 의미한다. 물론 사건의 전후 관계가 모순인 경우는 없다. 다음에는 사건의 전후 관계를 알고 싶은 사건 쌍의 수 s(50,000 이하의 자연수)이 주어진다. 다음 s줄에는 각각 서로 다른 두 사건의 번호가 주어진다. 사건의 번호는 1보다 크거나 같고, N보다 작거나 같은 자연수이다.
 *
 * 5 5  -> n k
 * 1 2
 * 1 3
 * 2 3
 * 3 4
 * 2 4
 * 3    -> testCase
 * 1 5
 * 2 4
 * 3 1
 *
 * 출력
 * s줄에 걸쳐 물음에 답한다. 각 줄에 만일 앞에 있는 번호의 사건이 먼저 일어났으면 -1, 뒤에 있는 번호의 사건이 먼저 일어났으면 1, 어떤지 모르면(유추할 수 없으면) 0을 출력한다.
 */
package Gold.III;

import java.io.*;
import java.util.*;

public class G3_1613 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));
    static StringTokenizer st;
    static int n;
    static int[][] floyd; // 플로이드 와샬 배열

    public static void main(String[] args) throws IOException {
        // 파싱
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 플로이드 와샬 배열 초기화
        floyd = new int[n+1][n+1];
        for (int[] f: floyd) {
            Arrays.fill(f, 1000);
        }

        // 플로이드 와샬 배열에 경로 입력
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int post = Integer.parseInt(st.nextToken());

            floyd[pre][post] = 1;
        }

        // 플로이드 와샬(모든 정점에서 모든 정점으로 경로가 있는지 판단)
        for (int t = 1; t <= n; t++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (floyd[i][j] > floyd[i][t] + floyd[t][j]) {
                        floyd[i][j] = floyd[i][t] + floyd[t][j];
                    }
                }
            }
        }

        // 테스트케이스 수행
        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine());
            int case1 = Integer.parseInt(st.nextToken());
            int case2 = Integer.parseInt(st.nextToken());

            boolean pre = preCheck(case1, case2); // case1이 case2 이전에 일어났는지
            boolean post = postCheck(case1, case2); // case1이 case2 이후에 일어났는지

            if (pre) bw.write(-1 + "\n"); // 이전에 일어났다면 -1
            else if (post) bw.write(1 + "\n"); // 이후에 일어났다면 1
            else bw.write(0 + "\n"); // 둘다 아니면 0

        }
        bw.flush(); // 출력
    }

    // 플로이드 와샬 배열의 floyd[case1][case2] 값이 1이상 1000미만이면 case1이 case2보다 먼저 일어났음.
    public static boolean preCheck(int case1, int case2) {
        return 1 <= floyd[case1][case2] && floyd[case1][case2] < 1000;
    }

    // 반대로 플로이드 와샬 배열의 floyd[case2][case1] 값이 1이상 1000미만이면 case2이 case1보다 먼저 일어났음.
    // 이므로 case1이 case2 이후에 일어났음.
    public static boolean postCheck(int case1, int case2) {
        return 1 <= floyd[case2][case1] && floyd[case2][case1] < 1000;
    }

}
