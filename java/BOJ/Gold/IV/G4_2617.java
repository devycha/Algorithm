/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2617
 *
 * ? 제목: 구슬 찾기
 * ? 시간 제한: 1초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 모양은 같으나, 무게가 모두 다른 N개의 구슬이 있다. N은 홀수이며, 구슬에는 번호가 1,2,...,N으로 붙어 있다. 이 구슬 중에서 무게가 전체의 중간인 (무게 순서로 (N+1)/2번째) 구슬을 찾기 위해서 아래와 같은 일을 하려 한다.
 * 우리에게 주어진 것은 양팔 저울이다. 한 쌍의 구슬을 골라서 양팔 저울의 양쪽에 하나씩 올려 보면 어느 쪽이 무거운가를 알 수 있다. 이렇게 M개의 쌍을 골라서 각각 양팔 저울에 올려서 어느 것이 무거운가를 모두 알아냈다. 이 결과를 이용하여 무게가 중간이 될 가능성이 전혀 없는 구슬들은 먼저 제외한다.
 * 예를 들어, N=5이고, M=4 쌍의 구슬에 대해서 어느 쪽이 무거운가를 알아낸 결과가 아래에 있다.
 *
 * 구슬 2번이 구슬 1번보다 무겁다.
 * 구슬 4번이 구슬 3번보다 무겁다.
 * 구슬 5번이 구슬 1번보다 무겁다.
 * 구슬 4번이 구슬 2번보다 무겁다.
 *
 * 위와 같이 네 개의 결과만을 알고 있으면, 무게가 중간인 구슬을 정확하게 찾을 수는 없지만, 1번 구슬과 4번 구슬은 무게가 중간인 구슬이 절대 될 수 없다는 것은 확실히 알 수 있다. 1번 구슬보다 무거운 것이 2, 4, 5번 구슬이고, 4번 보다 가벼운 것이 1, 2, 3번이다. 따라서 답은 2개이다.
 * M 개의 쌍에 대한 결과를 보고 무게가 중간인 구슬이 될 수 없는 구슬의 개수를 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫 줄은 구슬의 개수를 나타내는 정수 N(1 ≤ N ≤ 99)과 저울에 올려 본 쌍의 개수 M(1 ≤ M ≤ N(N-1)/2)이 주어진다. 그 다음 M 개의 줄은 각 줄마다 두 개의 구슬 번호가 주어지는데, 앞 번호의 구슬이 뒤 번호의 구슬보다 무겁다는 것을 뜻한다.
 *
 * 5 4  -> n m
 * 2 1  -> a b
 * 4 3  -> a b
 * 5 1  -> a b
 * 4 2  -> a b
 *
 * ? 출력
 * 첫 줄에 무게가 중간이 절대로 될 수 없는 구슬의 수를 출력 한다.
 *
 * 2
 *
 * ? 채점 결과
 * * 시간: 0.148초
 * * 메모리: 13MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.IV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_2617 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int answer = 0;

        // * 1. 입력 받기
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n+1][n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = 1;
            arr[b][a] = 2;
        }

        // * 2. 플로이드-와샬
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (k == i || i == j) {
                        continue;
                    }

                    if (arr[i][k] == 1 && arr[k][j] == 1) {
                        arr[i][j] = 1;
                    } else if (arr[i][k] == 2 && arr[k][j] == 2) {
                        arr[i][j] = 2;
                    }
                }
            }
        }

        // * 3. 본인보다 무겁거나 가벼운것들의 갯수가 반을 넘어갈 경우 카운트+1
        for (int i = 1; i <= n; i++) {
            int c1 = 0;
            int c2 = 0;

            for (int j = 1; j <= n; j++) {
                if (arr[i][j] == 1) {
                    c1++;
                } else if (arr[i][j] == 2) {
                    c2++;
                }
            }

            if (c1 >= (n/2) + 1 || c2 >= (n/2) + 1) {
                answer++;
            }
        }

        // * 4. 출력
        System.out.println(answer);
    }
}
