/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/1058
 *
 * ? 제목: 친구
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 지민이는 세계에서 가장 유명한 사람이 누구인지 궁금해졌다. 가장 유명한 사람을 구하는 방법은 각 사람의 2-친구를 구하면 된다. 어떤 사람 A가 또다른 사람 B의 2-친구가 되기 위해선, 두 사람이 친구이거나, A와 친구이고, B와 친구인 C가 존재해야 된다. 여기서 가장 유명한 사람은 2-친구의 수가 가장 많은 사람이다. 가장 유명한 사람의 2-친구의 수를 출력하는 프로그램을 작성하시오.
 * A와 B가 친구면, B와 A도 친구이고, A와 A는 친구가 아니다.
 *
 * ? 입력
 * 첫째 줄에 사람의 수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에 각 사람이 친구이면 Y, 아니면 N이 주어진다.
 *
 * 3    -> n
 * NYY  -> N : 0, Y : 1 로 치환 대입, arr[0][0] ~ arr[0][n-1]
 * YNY
 * YYN  -> arr[n-1][0] ~ arr[n-1][n-1]
 *
 * ? 출력
 * 첫째 줄에 가장 유명한 사람의 2-친구의 수를 출력한다.
 *
 * 2
 *
 * ? 채점 결과
 * * 시간: 0.084초
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Silver.II;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class S2_1058 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // * 파싱
    static int n;
    static int[][] arr;

    // * 초기 설정
    static int max = 0;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. BFS
        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[] {i, 0});
            visited[i] = true;
            int count = 0;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();

                if (cur[1] == 2) {
                    break;
                }

                for (int j = 0; j < n; j++) {
                    if (arr[cur[0]][j] == 1 && !visited[j]) {
                        visited[j] = true;
                        queue.add(new int[] {j, cur[1] + 1});
                        count++;
                    }
                }
            }

            if (count > max) {
                max = count;
            }
        }

        // * 3. 출력
        System.out.println(max);
    }

    // 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                arr[i][j] = (c[j] == 'Y') ? 1 : 0;
            }
        }
    }
}
