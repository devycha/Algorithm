/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/14867
 *
 * ? 제목: 물통
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 용량이 다른 두 개의 빈 물통 A, B가 있다. 이 물통들에 물을 채우고 비우는 일을 반복하여 두 물통을 원하는 상태(목표하는 양의 물을 담은 상태)가 되도록 만들고자 한다. 물통 이외에는 물의 양을 정확히 잴 수 있는 방법이 없으며, 가능한 작업은 다음과 같은 세 종류가 전부이다.
 * [F(x): Fill x]: 물통 x에 물을 가득 채운다. (물을 채우기 전에 물통 x가 비어있는지 여부는 관계없음. 다른 물통은 그대로 둠)
 * [E(x): Empty x]: 물통 x의 물을 모두 버린다. (다른 물통은 그대로 둠)
 * [M(x,y): Move water from x to y)]: 물통 x의 물을 물통 y에 붓는다. 이때 만약 물통 x에 남아 있는 물의 양이 물통 y에 남아 있는 빈 공간보다 적거나 같다면 물통 x의 물을 물통 y에 모두 붓는다. 만약 물통 x에 남아 있는 물의 양이 물통 y에 남아 있는 빈 공간보다 많다면 부을 수 있는 만큼 최대로 부어 물통 y를 꽉 채우고 나머지는 물통 x에 남긴다.
 * 예를 들어, 물통 A와 B의 용량이 각각 2리터와 5리터라고 하자. 두 물통 모두 빈 상태에서 시작하여 최종적으로 물통 A에는 2리터, 물통 B에는 4리터 물을 남기길 원할 경우, 다음과 같은 순서로 작업을 수행하면 총 8회의 작업으로 원하는 상태에 도달할 수 있다.
 * (0,0)→[F(B)]→(0,5)→[M(B,A)]→(2,3)→[E(A)]→(0,3)→[M(B,A)]→(2,1)→[E(A)]→(0,1)→[M(B,A)]→(1,0)→[F(B)]→(1,5)→[M(B,A)]→(2,4)
 * 하지만, 작업 순서를 아래와 같이 한다면 필요한 작업 총 수가 5회가 된다.
 * (0,0)→[F(A)]→(2,0)→[M(A,B)]→(0,2)→[F(A)]→(2,2)→[M(A,B)]→(0,4)→[F(A)]→(2,4)
 * 두 물통의 용량과 원하는 최종 상태를 입력으로 받은 후, 두 물통이 비어 있는 상태에서 시작하여 최종 상태에 도달하기 위한 최소 작업 수를 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 표준 입력으로 물통 A의 용량을 나타내는 정수 a(1 ≤ a < 100,000), 물통 B의 용량을 나타내는 정수 b(a < b ≤ 100,000), 최종 상태에서 물통 A에 남겨야 하는 물의 용량을 나타내는 정수 c(0 ≤ c ≤ a), 최종 상태에서 물통 B에 남겨야 하는 물의 용량을 나타내는 정수 d(0 ≤ d ≤ b)가 공백으로 분리되어 한 줄에 주어진다.
 *
 * 3 7 3 2  -> aMax bMax aEnd bEnd
 * 
 * ? 출력
 * 목표 상태에 도달하는 최소 작업 수를 나타내는 정수를 표준 출력으로 출력한다. 만약 목표 상태에 도달하는 방법이 없다면 –1을 출력한다.
 *
 * 9
 *
 * ? 서브태스크
 * 번호	배점	제한
 * 1	9   A 물통의 용량은 1리터
 * 2	14  B 물통의 용량은 A 물통의 용량의 배수
 * 3	34  1 ≤ a, b ≤ 1,000
 * 4	43  원래의 제약조건 이외에 아무 제약조건이 없다.
 *
 * ? 채점 결과
 * * 시간: 1140ms
 * * 메모리: 234MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.II;

import java.io.*;
import java.util.*;

public class G2_14867 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int aMax;
    static int bMax;
    static int aEnd;
    static int bEnd;

    // * 초기 설정
    static HashSet<String> visited = new HashSet<>(); // 방문 해쉬셋

    public static void main(String[] args) throws IOException {
        // * 1. 입력 받기
        st = new StringTokenizer(br.readLine());
        aMax = Integer.parseInt(st.nextToken());
        bMax = Integer.parseInt(st.nextToken());
        aEnd = Integer.parseInt(st.nextToken());
        bEnd = Integer.parseInt(st.nextToken());

        // * 2. BFS 수행
        int answer = bfs();

        // * 3. 정답 출력
        System.out.println(answer);
    }

    /*
    1. A 비우기
    2. B 비우기
    3. A 가득채우기
    4. B 가득채우기
    5. A -> B
    6. B -> A
     */

    // ! BFS
    public static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            // 정답
            if (cur[0] == aEnd && cur[1] == bEnd) {
                return cur[2];
            }

            // 1
            if (cur[0] != 0) {
                if (!isVisited(0, cur[1])) {
                    queue.add(new int[] {0, cur[1], cur[2]+1});
                }
            }

            // 2
            if (cur[1] != 0) {
                if (!isVisited(cur[0], 0)) {
                    queue.add(new int[] {cur[0], 0, cur[2]+1});
                }
            }

            // 3
            if (cur[0] != aMax) {
                if (!isVisited(aMax, cur[1])) {
                    queue.add(new int[] {aMax, cur[1], cur[2]+1});
                }
            }

            // 4
            if (cur[1] != bMax) {
                if (!isVisited(cur[0], bMax)) {
                    queue.add(new int[] {cur[0], bMax, cur[2]+1});
                }

            }

            // 5
            if (cur[0] != 0) {
                int[] next = moveAtoB(cur[0], cur[1], cur[2]);
                if (!isVisited(next[0], next[1])) {
                    queue.add(next);
                }
            }

            // 6
            if (cur[1] != 0) {
                int[] next = moveBtoA(cur[0], cur[1], cur[2]);
                if (!isVisited(next[0], next[1])) {
                    queue.add(next);
                }
            }
        }

        return -1;
    }

    // ! HashSet을 이용하여 이미 방문했는지 확인하고 방문하지 않았다면 방문표시하고 true를 리턴하는 함수
    public static boolean isVisited(int a, int b) {
        String v = a + "," + b;
        if (visited.contains(v)) {
            return true;
        }
        visited.add(v);
        return false;
    }

    // ! A에 있는 물을 B로 옮기는 함수
    public static int[] moveAtoB(int a, int b, int c) {
        if (a + b > bMax) {
            a -= (bMax - b);
            b = bMax;
        } else {
            b += a;
            a = 0;
        }

        return new int[] {a, b, c+1};
    }

    // ! B에 있는 물을 A로 옮기는 함수
    public static int[] moveBtoA(int a, int b, int c) {
        if (a + b > aMax) {
            b -= (aMax - a);
            a = aMax;
        } else {
            a += b;
            b = 0;
        }

        return new int[] {a, b, c+1};
    }
}
