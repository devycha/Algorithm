/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2098
 *
 * ? 제목: 외판원 순회
 * ? 시간 제한: 1초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 외판원 순회 문제는 영어로 Traveling Salesman problem (TSP) 라고 불리는 문제로 computer science 분야에서 가장 중요하게 취급되는 문제 중 하나이다. 여러 가지 변종 문제가 있으나, 여기서는 가장 일반적인 형태의 문제를 살펴보자.
 * 1번부터 N번까지 번호가 매겨져 있는 도시들이 있고, 도시들 사이에는 길이 있다. (길이 없을 수도 있다) 이제 한 외판원이 어느 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 돌아오는 순회 여행 경로를 계획하려고 한다. 단, 한 번 갔던 도시로는 다시 갈 수 없다. (맨 마지막에 여행을 출발했던 도시로 돌아오는 것은 예외) 이런 여행 경로는 여러 가지가 있을 수 있는데, 가장 적은 비용을 들이는 여행 계획을 세우고자 한다.
 * 각 도시간에 이동하는데 드는 비용은 행렬 W[i][j]형태로 주어진다. W[i][j]는 도시 i에서 도시 j로 가기 위한 비용을 나타낸다. 비용은 대칭적이지 않다. 즉, W[i][j] 는 W[j][i]와 다를 수 있다. 모든 도시간의 비용은 양의 정수이다. W[i][i]는 항상 0이다. 경우에 따라서 도시 i에서 도시 j로 갈 수 없는 경우도 있으며 이럴 경우 W[i][j]=0이라고 하자.
 * N과 비용 행렬이 주어졌을 때, 가장 적은 비용을 들이는 외판원의 순회 여행 경로를 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 도시의 수 N이 주어진다. (2 ≤ N ≤ 16) 다음 N개의 줄에는 비용 행렬이 주어진다. 각 행렬의 성분은 1,000,000 이하의 양의 정수이며, 갈 수 없는 경우는 0이 주어진다. W[i][j]는 도시 i에서 j로 가기 위한 비용을 나타낸다.
 * 항상 순회할 수 있는 경우만 입력으로 주어진다.
 *
 * 4            -> n
 * 0 10 15 20   -> arr[0][0] ~ arr[0][n-1]
 * 5 0 9 10
 * 6 13 0 12
 * 8 8 9 0      -> arr[n-1][0] ~ arr[n-1][n-1]
 *
 * ? 출력
 * 첫째 줄에 외판원의 순회에 필요한 최소 비용을 출력한다.
 *
 * 35
 *
 * ? 채점 결과
 * * 시간: 0.152초
 * * 메모리: 17MB
 * * 언어: JAVA8
 * * 시도: 10+
 */
package Gold.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G1_2098 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static int[][] arr;

    // * 초기 설정
    static int MAX = 987654321;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기.
        System.out.println(dfs(0, 1)); // * 2. TSP 알고리즘 + 정답 출력
    }

    // * TSP(외판원 순회 알고리즘) : Travelling Salesman Problem
    public static int dfs(int cur, int visit) {
        if (visit == (1 << n) - 1) {
            if (arr[cur][0] == 0) {
                return MAX;
            }
            return arr[cur][0];
        }

        if (dist[cur][visit] != -1) {
            return dist[cur][visit];
        }

        dist[cur][visit] = MAX;

        for (int i = 0; i < n; i++) {
            if ((visit & (1 << i)) != 0 || arr[cur][i] == 0) {
                continue;
            }
            dist[cur][visit] = Math.min(dist[cur][visit],
                    dfs(i, visit | (1 << i)) + arr[cur][i]);
        }

        return dist[cur][visit];
    }

    // * 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        dist = new int[n][(1 << n) - 1];
        for (int[] d : dist) {
            Arrays.fill(d, -1);
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
