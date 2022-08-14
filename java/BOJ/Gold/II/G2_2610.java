/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2610
 *
 * ? 제목: 회의 준비
 * ? 시간 제한: 1초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * KOI 준비를 위해 회의를 개최하려 한다. 주최측에서는 회의에 참석하는 사람의 수와 참석자들 사이의 관계를 따져 하나 이상의 위원회를 구성하려고 한다. 위원회를 구성하는 방식은 다음과 같다.
 * 서로 알고 있는 사람은 반드시 같은 위원회에 속해야 한다.
 * 효율적인 회의 진행을 위해 위원회의 수는 최대가 되어야 한다.
 * 이런 방식으로 위원회를 구성한 후에 각 위원회의 대표를 한 명씩 뽑아야 한다. 각 위원회의 대표만이 회의 시간 중 발언권을 가지며, 따라서 회의 참석자들이 자신의 의견을 말하기 위해서는 자신이 속한 위원회의 대표에게 자신의 의견을 전달해야 한다. 그런데 각 참석자는 자신이 알고 있는 사람에게만 의견을 전달할 수 있어 대표에게 의견을 전달하기 위해서는 때로 여러 사람을 거쳐야 한다. 대표에게 의견을 전달하는 경로가 여러 개 있을 경우에는 가장 적은 사람을 거치는 경로로 의견을 전달하며 이때 거치는 사람의 수를 참석자의 의사전달시간이라고 한다.
 * 위원회에서 모든 참석자들의 의사전달시간 중 최댓값이 최소가 되도록 대표를 정하는 프로그램을 작성하시오.
 * 예를 들어 1번, 2번, 3번 세 사람으로 구성되어 있는 위원회에서 1번과 2번, 2번과 3번이 서로 알고 있다고 하자. 1번이 대표가 되면 3번이 대표인 1번에게 의견을 전달하기 위해서 2번을 거쳐야만 한다. 반대로 3번이 대표가 되어도 1번이 대표인 3번에게 의견을 전달하려면 2번을 거쳐야만 한다. 하지만 2번이 대표가 되면 1번과 3번 둘 다 아무도 거치지 않고 대표에게 직접 의견을 전달 할 수 있다. 따라서 이와 같은 경우 2번이 대표가 되어야 한다.
 *
 * ? 입력 & 파싱
 * 첫째 중에 회의에 참석하는 사람의 수 N이 주어진다. 참석자들은 1부터 N까지의 자연수로 표현되며 회의에 참석하는 인원은 100 이하이다. 둘째 줄에는 서로 알고 있는 관계의 수 M이 주어진다. 이어 M개의 각 줄에는 서로 아는 사이인 참석자를 나타내는 두개의 자연수가 주어진다.
 *
 * 8    -> n
 * 7    -> m
 * 1 2  -> a b
 * 2 3  -> a b
 * 4 5  -> a b
 * 5 6  -> a b
 * 4 6  -> a b
 * 6 7  -> a b
 * 7 4  -> a b
 *
 * ? 출력
 * 첫째 줄에는 구성되는 위원회의 수 K를 출력한다. 다음 K줄에는 각 위원회의 대표 번호를 작은 수부터 차례로 한 줄에 하나씩 출력한다. 한 위원회의 대표가 될 수 있는 사람이 둘 이상일 경우 그중 한 명만 출력하면 된다.
 *
 * 3
 * 2
 * 4
 * 8
 *
 * ? 채점 결과
 * * 시간: 300ms
 * * 메모리: 20MB
 * * 언어: JAVA8
 *
*/
package Gold.II;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class G2_2610 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m; // 사람수, 서로 알고있는 라인 수
    static int[][] arr; // 연결된 사람까지의 거리

    // * 초기 설정
    static int[] parent; // 합집합
    static int[] result; // 결과
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>(); // 위원회번호(키) : 위원회에 속한 사람 리스트(값)

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        floyd(); // * 2. 플로이드-와샬

        // * 3. 경로가 있는 사람들끼리 UNION_FIND(합집합 찾기)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i][j] != 200 && parent[i] != parent[j]) {
                    union(i, j);
                }
            }
        }

        // * 4. 대표 찾기
        find();

        // * 5. 대표 번호 정렬하기
        Arrays.sort(result);

        // * 6. 정답 출력
        StringBuffer sb = new StringBuffer();
        sb.append(result.length + "\n");

        for (int r : result) {
            sb.append(r + "\n");
        }

        bw.write(sb.toString());

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! 대표 찾기
    public static void find() {
        // * 모든 사람에 대해
        for (int i = 1; i <= n; i++) {
            // * 해당 그룹 번호에 맞는 위원회가 map에 등록안되어있으면 등록
            if (!map.containsKey(parent[i])) {
                map.put(parent[i], new ArrayList<Integer>());
            }
            // * 해당 그룹 번호에 맞는 위원회에 현재 인원의 번호 추가
            map.get(parent[i]).add(i);
        }

        // * 각 위원회마다 대표를 result에 저장
        int p = 0;
        result = new int[map.size()];

        for (int key : map.keySet()) {
            int min = 1000;
            int min_node = 0;

            for (int node : map.get(key)) {
                int len = findMaxLenth(node);
                if (len < min) {
                    min = len;
                    min_node = node;
                }
            }

            result[p++] = min_node;
        }
    }

    // ! 현재 인원과 연결된 사람들의 거리 중 최댓값 찾기 함수
    public static int findMaxLenth(int node) {
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (arr[node][i] != 200) {
                max = Math.max(max, arr[node][i]);
            }
        }
        return max;
    }

    // ! 플로이드 와샬 함수
    public static void floyd() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (arr[i][j] >= arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            arr[i][i] = 0;
        }
    }

    // ! UNION_FIND
    public static int findParent(int node) {
        if (parent[node] != node) {
            parent[node] = findParent(parent[node]);
        }
        return parent[node];
    }

    // ! UNION_FIND
    public static boolean union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a == b) {
            return false;
        }

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }

        return true;
    }

    // ! 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        arr = new int[n+1][n+1];
        parent = IntStream.range(0, n+1).toArray();

        for (int[] a : arr) {
            Arrays.fill(a, 200);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = 1;
            arr[b][a] = 1;
        }
    }
}