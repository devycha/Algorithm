/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/3665
 *
 * ! 문제: 최종 순위
 * ! 시간 제한: 1초
 * ! 메모리 제한: 256MB
 *
 * ! 문제
 * 올해 ACM-ICPC 대전 인터넷 예선에는 총 n개의 팀이 참가했다. 팀은 1번부터 n번까지 번호가 매겨져 있다. 놀랍게도 올해 참가하는 팀은 작년에 참가했던 팀과 동일하다.
 * 올해는 인터넷 예선 본부에서는 최종 순위를 발표하지 않기로 했다. 그 대신에 작년에 비해서 상대적인 순위가 바뀐 팀의 목록만 발표하려고 한다. (작년에는 순위를 발표했다) 예를 들어, 작년에 팀 13이 팀 6 보다 순위가 높았는데, 올해 팀 6이 팀 13보다 순위가 높다면, (6, 13)을 발표할 것이다.
 * 창영이는 이 정보만을 가지고 올해 최종 순위를 만들어보려고 한다. 작년 순위와 상대적인 순위가 바뀐 모든 팀의 목록이 주어졌을 때, 올해 순위를 만드는 프로그램을 작성하시오. 하지만, 본부에서 발표한 정보를 가지고 확실한 올해 순위를 만들 수 없는 경우가 있을 수도 있고, 일관성이 없는 잘못된 정보일 수도 있다. 이 두 경우도 모두 찾아내야 한다.
 *
 * ! 입력 & 파싱
 * 첫째 줄에는 테스트 케이스의 개수가 주어진다. 테스트 케이스는 100개를 넘지 않는다. 각 테스트 케이스는 다음과 같이 이루어져 있다.
 * 팀의 수 n을 포함하고 있는 한 줄. (2 ≤ n ≤ 500)
 * n개의 정수 ti를 포함하고 있는 한 줄. (1 ≤ ti ≤ n) ti는 작년에 i등을 한 팀의 번호이다. 1등이 가장 성적이 높은 팀이다. 모든 ti는 서로 다르다.
 * 상대적인 등수가 바뀐 쌍의 수 m (0 ≤ m ≤ 25000)
 * 두 정수 ai와 bi를 포함하고 있는 m줄. (1 ≤ ai < bi ≤ n) 상대적인 등수가 바뀐 두 팀이 주어진다. 같은 쌍이 여러 번 발표되는 경우는 없다.
 *
 * 1    -> t
 * 2    -> n
 * 1 2  -> preRank
 * 2    -> change
 * 1 2  -> a b
 *
 * 출력
 * 각 테스트 케이스에 대해서 다음을 출력한다.
 * n개의 정수를 한 줄에 출력한다. 출력하는 숫자는 올해 순위이며, 1등팀부터 순서대로 출력한다. 만약, 확실한 순위를 찾을 수 없다면 "?"를 출력한다. 데이터에 일관성이 없어서 순위를 정할 수 없는 경우에는 "IMPOSSIBLE"을 출력한다.
 *
 * 2 1
 *
 * ? 채점 결과
 * 메모리: 93140KB
 * 시간: 872ms
 * 언어: JAVA8
 */
package Gold.I;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G1_3665 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    
    // 파싱
    static int n;
    static int[] preRank;
    
    // 초기 설정
    static int[] degree; // 위상배열
    static ArrayList<Integer> result; // 순위 결과 리스트
    static ArrayList<Integer>[] map; // 후순위 리스트 1: [2, 3, 4] => 1이 2, 3, 4보다 앞서있음.

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 갯수 t
        for (int i = 0; i < t; i++) { // 총 t만큼 반복
            input(); // 입력 받기
            if (degreeSort()) { // 위상 정렬 후 모든 순서를 나타낼 수 있으면
                // 출력
                for (int j = 0; j < result.size(); j++) {
                    bw.write(result.get(j) + " ");
                }
                bw.write("\n");
            } else {
                // 그렇지 않으면 IMPOSSIBLE 출력
                bw.write("IMPOSSIBLE\n");
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }

    // 위상 정렬
    public static boolean degreeSort() {
        LinkedList<Integer> queue = new LinkedList<>();

        // 위상이 0인 팀 queue에 삽입
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        // 시작부터 위상이 0인 팀이 2개가 생겨
        // 2개의 순서를 알 수 없는 경우
        if (queue.size() > 1) return false;

        // 위상정렬 수행
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result.add(cur);
            int count = 0;

            for (int next: map[cur]) {
                if (--degree[next] == 0) {
                    count++;
                    queue.add(next);
                }
            }

            if (count > 1) return false;
        }

        return result.size() == n;
    }

    // 입력 받기(각 테스트 케이스마다)
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine()); // 팀 개수
        degree = new int[n+1]; // 위상 배열 초기화
        result = new ArrayList<>(); // 결과 리스트 초기화

        // 후순위 리스트 초기화
        map = new ArrayList[n+1]; 
        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<Integer>();
        }

        // 이전 순위 배열 초기화
        preRank = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            preRank[i] = Integer.parseInt(st.nextToken());
        }

        // 이전 순위를 가지고 위상배열 및 후순위리스트 채우기
        for (int i = 1; i < preRank.length-1; i++) {
            for (int j = i+1; j < preRank.length; j++) {
                map[preRank[i]].add(preRank[j]);
                degree[preRank[j]]++;
            }
        }

        // 순위가 바뀐 팀에 대하여
        int change = Integer.parseInt(br.readLine());
        for (int i = 0; i < change; i++) {
            st = new StringTokenizer(br.readLine());
            
            // 기존 a팀과 b팀의 우열관계가 바뀌었다는 것임
            // a b : a가 b를 이기는게 아니라 a와 b의 우열관계가 바뀐것
            // b a : a가 b를 이기는게 아니라 a와 b의 우열관계가 바뀐것
            // 둘 다 같은 의미임
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 기존 우열관계 및 위상배열 변경
            if (map[a].contains(Integer.valueOf(b))) {
                map[a].remove(Integer.valueOf(b));
                degree[b]--;
                map[b].add(a);
                degree[a]++;
            } else if (map[b].contains(Integer.valueOf(a))) {
                map[b].remove(Integer.valueOf(a));
                degree[a]--;
                map[a].add(b);
                degree[b]++;
            }
        }

    }
}
