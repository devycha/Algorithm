/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/10216
 *
 * ? 문제: Count Circle Groups
 * ? 시간 제한: 8초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 백준이는 국방의 의무를 수행하기 위해 떠났다. 혹독한 훈련을 무사히 마치고 나서, 정말 잘 생겼고 코딩도 잘하는 백준은 그 특기를 살려 적군의 진영을 수학적으로 분석하는 일을 맡게 되었다.
 * 2차원 평면 위의 N곳에 적군의 진영이 설치되어 있다. 각 적군의 진영들은 진영마다 하나의 통신탑을 설치해, i번째 적군의 통신탑은 설치 위치로부터 Ri 이내 거리에 포함되는 모든 지역을 자신의 통신영역 Ai로 가지게 된다. 만약 임의의 통신영역 Ai와 Aj가 닿거나 겹치는 부분이 있다면 진영 i와 진영 j는 직접적으로 통신이 가능하다. 물론 직접적으로 통신이 가능하지 않더라도, 임의의 지역 i와 j가 중간에 몇 개의 직접통신을 거쳐서 최종적으로 통신이 가능하다면 i와 j는 상호간에 통신이 가능한 것으로 본다.
 * 적들은 영리해서, 상호간에 통신이 가능한 부대끼리는 결집력있는 한 그룹처럼 행동한다. 백준은 이러한 그룹의 개수를 알아내 아군의 전략지침에 도움을 주고자 한다. 군대에 가서도 코딩하는 불쌍한 백준을 위해 적군의 통신망 분석을 도와주자!
 *
 * ? 입력 & 파싱
 * 입력 파일의 첫 번째 줄에 테스트 케이스의 수를 의미하는 자연수 T가 주어진다. 그 다음에는 T개의 테스트 케이스가 주어진다.
 * 각각의 테스트 케이스에 대해서 적군 진영의 숫자 N (1 ≤ N ≤ 3,000)이 주어진다. 이어서 N줄에 걸쳐 적군 진영의 좌표 x, y (0 ≤ x, y ≤ 5,000), 그리고 해당 진영의 R (0 ≤ R ≤ 5,000)이 주어진다. 주어지는 수는 모두 정수이다.
 *
 * 2        -> test
 * 2        -> n
 * 0 0 1    -> a b r
 * 1 0 1    -> a b r
 * 3        -> ...
 * 0 0 1
 * 2 0 1
 * 10 0 5
 *
 * ? 출력
 * 각 테스트 케이스에 대해서 한 줄에 걸쳐 적군 진영의 그룹 개수를 출력한다.
 *
 * 1
 * 2
 *
 * ? 채점 결과
 * * 시간: 7.152초
 * * 메모리: 139MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.V;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Camp {
    int num;
    int x;
    int y;
    int r;

    public Camp(int num, int x, int y, int r) {
        this.num = num;
        this.x = x;
        this.y = y;
        this.r = r;
    }
}

public class G5_10216 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static ArrayList<Camp> list;

    // * 초기 설정
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        int test = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        for (int t = 0; t < test; t++) {
            input(); // * 1. 입력 받기

            // * 2. DFS
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (!visit[list.get(i).num]) {
                    count++;
                    visit[list.get(i).num] = true;
                    dfs(list.get(i));
                }
            }

            sb.append(count + "\n");
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    // DFS
    public static void dfs(Camp cur) {
        for (int i = 0; i < n; i++) {
            Camp next = list.get(i);

            // 통신 범위 내의 있는 진영이면서 방문한 적이 없는 곳에 대하여 재귀 DFS 수행
            if (!visit[next.num] && isConnected(cur, next)) {
                visit[next.num] = true;
                dfs(next);
            }
        }
    }

    public static boolean isConnected(Camp camp1, Camp camp2) {
        return (camp1.r + camp2.r) * (camp1.r + camp2.r) >= (camp1.x - camp2.x) * (camp1.x - camp2.x) + (camp1.y - camp2.y) * (camp1.y - camp2.y);
    }

    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        visit = new boolean[n];
        list = new ArrayList<>();

        int x, y, r;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            list.add(new Camp(i, x, y, r));
        }
    }
}
