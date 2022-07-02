/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/1194
 *
 * ! 시간제한: 2초
 * ! 메모리제한: 128MB
 *
 * !문제
 * 지금 민식이가 계획한 여행은 달이 맨 처음 뜨기 시작할 때 부터, 준비했던 여행길이다. 하지만, 매번 달이 차오를 때마다 민식이는 어쩔 수 없는 현실의 벽 앞에서 다짐을 포기하고 말았다.
 * 민식이는 매번 자신의 다짐을 말하려고 노력했지만, 말을 하면 아무도 못 알아들을 것만 같아서, 지레 겁먹고 벙어리가 되어버렸다. 결국 민식이는 모두 잠든 새벽 네시 반쯤 홀로 일어나, 창 밖에 떠있는 달을 보았다.
 * 하루밖에 남지 않았다. 달은 내일이면 다 차오른다. 이번이 마지막기회다. 이걸 놓치면 영영 못간다.
 * 영식이는 민식이가 오늘도 여태것처럼 그냥 잠 들어버려서 못 갈지도 모른다고 생각했다. 하지만 그러기엔 민식이의 눈에는 저기 뜬 달이 너무나 떨렸다.
 * 민식이는 지금 미로 속에 있다. 미로는 직사각형 모양이고, 여행길을 떠나기 위해 미로를 탈출하려고 한다. 미로는 다음과 같이 구성되어져있다.
 *
 * ! 빈 칸: 언제나 이동할 수 있다. ('.')
 * ! 벽: 절대 이동할 수 없다. ('#')
 * ! 열쇠: 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 집는다. ('a', 'b', 'c', 'd', 'e', 'f')
 * ! 문: 대응하는 열쇠가 있을 때만 이동할 수 있다. ('A', 'B', 'C', 'D', 'E', 'F')
 * ! 민식이의 현재 위치: 빈 곳이고, 민식이가 현재 서 있는 곳이다. ('0')
 * ! 출구: 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를 탈출한다. ('1')
 *
 * 달이 차오르는 기회를 놓치지 않기 위해서, 미로를 탈출하려고 한다. 한 번의 움직임은 현재 위치에서 수평이나 수직으로 한 칸 이동하는 것이다.
 * 민식이가 미로를 탈출하는데 걸리는 이동 횟수의 최솟값을 구하는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 미로의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 50) 둘째 줄부터 N개의 줄에 미로의 모양이 주어진다. 같은 타입의 열쇠가 여러 개 있을 수 있고, 문도 마찬가지이다. 그리고, 문에 대응하는 열쇠가 없을 수도 있다. '0'은 한 개, '1'은 적어도 한 개 있다. 열쇠는 여러 번 사용할 수 있다.
 *
 * 5 5      -> n m
 * ....1    -> arr[0][0] ~ arr[0][m-1]
 * #1###
 * .1.#0
 * ....A
 * .1.#.    -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ! 출력
 * 첫째 줄에 민식이가 미로를 탈출하는데 드는 이동 횟수의 최솟값을 출력한다. 만약 민식이가 미로를 탈출 할 수 없으면, -1을 출력한다.
 *
 * -1
 *
 * ? 채점 결과
 * 메모리: 15800KB
 * 시간: 144ms
 * 언어: JAVA
 */
package Gold.I;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G1_1194 {
    // 초기 설정
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int n; // N(행갯수)
    static int m; // M(열갯수)
    static char[][] arr; // 미로 정보
    static int[] dx = {1, -1, 0, 0}; // 상하
    static int[] dy = {0, 0, 1, -1}; // 좌우
    static int answer = -1; // 정답
    static ArrayList<Integer>[][] visited; // 방문배열(배열의 한 요소에는 key 정보에 대한 정수가 리스트 형태로 들어있음)

    public static void main(String[] args) throws IOException {
        // 파싱
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        // 방문 배열 채우기
        visited = new ArrayList[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = new ArrayList<Integer>();
            }
        }

        // 민식이 위치 찾기
        int[] minSik = findMinSik();

        // 민식이 위치를 큐에 넣기
        LinkedList<int[]> queue = new LinkedList<>();
        visited[minSik[0]][minSik[1]].add(0); // 민식이 위치에 방문배열 안에 키가 하나도 없는 뜻의 0을 추가
        queue.add(new int[] {minSik[0], minSik[1], 0, 0}); // {민식이위치x, 민식이위치y, 키정보, 이동횟수} 추가

        // BFS 시작
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int keys = cur[2];
            int count = cur[3];

            // 출구에 도착하면 answer에 이동횟수를 저장하고 종료
            if (arr[x][y] == '1') {
                answer = count;
                break;
            }

            // 상하좌우에 대하여
            for (int i = 0 ; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (arr[nx][ny] == '#') continue; // * 벽일 때 패스
                    if (isDoor(arr[nx][ny]) && !hasKey(arr[nx][ny], keys)) continue; // * 문일 때(키가 없을 경우) 패스
                    if (isKey(arr[nx][ny])) { // 키가 있는 곳일 때
                        int newKey = addKey(keys, keyBit(arr[nx][ny])); // 자신이 갖고있는 키와 새롭게 받은 키를 합친 키정보
                        if (visited[nx][ny].contains(newKey)) continue; // * 업데이트된 키정보로 이미 해당 위치를 방문했을 때 패스
                        visited[nx][ny].add(newKey); // 업데이트된 키정보를 방문배열의 리스트 안에 추가
                        queue.add(new int[] {nx, ny, newKey, count+1}); // 큐에 삽입
                    } else { // 지나갈 수 있는 경우일 때
                        if (visited[nx][ny].contains(keys)) continue; // (방문한곳에 이미 같은 키를 가진 방문이 존재할 때)
                        visited[nx][ny].add(keys); // 방문배열에 현재 키정보를 리스트 안에 추가
                        queue.add(new int[] {nx, ny, keys, count+1}); // 큐에 삽입
                    }
                }
            }
        }

        System.out.println(answer); // 정답 출력
    }

    /*
    문 정보와, 현재 키 정보(정수값)을 가지고 해당 문에 대한 키가 있는지 판단하는 함수
    문 : A(A -> A - 'A' + 'a' 로 변환 -> 'a'로 변환됨 -> keyBit 함수를 이용해서 'a' -> 1로 변환(비트마스크)
    현재 갖고 있는 키 : bcdef (2(b) + 4(c) + 8(d) + 16(e) 32(f) = 62) => 비트마스크 "111110"
    1 vs 111110 을 &연산 했을 때 0이 나오면 해당 문에 대한 키가 없는 것임
    000001
    111110
    ------
    000000 => 0
     */
    public static boolean hasKey(char door, int keys) {
        int needKey = keyBit((char) (door - 'A' + 'a'));
        return (needKey & keys) != 0;
    }

    /*
    현재 갖고있는 키와 새롭게 추가할 키를 합쳐서( | 연산 ) 새로운 키조합의 정수를 만들어 냄
    32  -> 100000
    1   -> 000001
    -------------
    33  -> 100001
     */
    public static int addKey(int keys, int addKey) {
        return (keys | addKey);
    }

    /*
    해당 값이 A부터 F, 즉 문인지 판단하는 함수
     */
    public static boolean isDoor(char val) {
        return 0 <= val - 'A' && val - 'A' < 6;
    }

    /*
    해당 값이 a부터 f, 즉 키인지 판단하는 함수
     */
    public static boolean isKey(char val) {
        return 0 <= val - 'a' && val - 'a' < 6;
    }

    /*
    민식이의 위치를 찾는 함수
     */
    public static int[] findMinSik() {
        int[] loc = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '0') return new int[] {i, j};
            }
        }
        return loc;
    }

    /*
    키값을 정수형태로 바꾸는 함수
    'a' 부터 'f'까지
    'a': 000001
    'b': 000010
    'c': 000100
    'd': 001000
    'e': 010000
    'f': 100000 
     */
    public static int keyBit(char key) {
        return 1 << (key - 'a');
    }
}
