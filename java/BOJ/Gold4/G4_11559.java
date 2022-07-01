/**
 * ! 문제 출처: 백준 온라인 져지
 * address: https://www.acmicpc.net/problem/11559
 *
 * ! 시간제한: 1초
 * ! 메모리제한: 256MB
 *
 * ! 문제
 * 뿌요뿌요의 룰은 다음과 같다.
 * 필드에 여러 가지 색깔의 뿌요를 놓는다. 뿌요는 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.
 * 뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다. 이때 1연쇄가 시작된다.
 * 뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 역시 중력의 영향을 받아 차례대로 아래로 떨어지게 된다.
 * 아래로 떨어지고 나서 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터지게 되는데, 터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때마다 1연쇄씩 늘어난다.
 * 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.
 * 남규는 최근 뿌요뿌요 게임에 푹 빠졌다. 이 게임은 1:1로 붙는 대전게임이라 잘 쌓는 것도 중요하지만, 상대방이 터뜨린다면 연쇄가 몇 번이 될지 바로 파악할 수 있는 능력도 필요하다. 하지만 아직 실력이 부족하여 남규는 자기 필드에만 신경 쓰기 바쁘다. 상대방의 필드가 주어졌을 때, 연쇄가 몇 번 연속으로 일어날지 계산하여 남규를 도와주자!
 *
 * ! 입력 & 파싱
 * 총 12개의 줄에 필드의 정보가 주어지며, 각 줄에는 6개의 문자가 있다.
 * 이때 .은 빈공간이고 .이 아닌것은 각각의 색깔의 뿌요를 나타낸다.
 * R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑이다.
 * 입력으로 주어지는 필드는 뿌요들이 전부 아래로 떨어진 뒤의 상태이다. 즉, 뿌요 아래에 빈 칸이 있는 경우는 없다.
 *
 * ......   -> arr[0][0] ~ arr[0][5]
 * ......
 * ......
 * ......
 * ......
 * ......
 * ......
 * ......
 * .Y....
 * .YG...
 * RRYG..
 * RRYGG.   -> arr[11][0] ~ arr[11][5]
 *
 * !출력
 * 현재 주어진 상황에서 몇연쇄가 되는지 출력한다. 하나도 터지지 않는다면 0을 출력한다.
 */
package Gold4;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class G4_11559 {
    // 초기 설정
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static char[][] field; // 게임 필드
    static boolean[][] visited = new boolean[12][6]; // 방문 리스트
    static boolean isChanged = true; // 폭발이 일어났는지 여부
    static int count = 0; // 연쇄의 갯수
    static int[] dx = {1, -1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, 1, -1}; // 상하좌우
    static ArrayList<ArrayList<int[]>> groupList; // 한 타임동안 DFS로 연결된 위치들을 모은 리스트 [ [{x, y}...], ... ]
    public static void main(String[] args) throws IOException {
        // 필드 초기화
        field = new char[12][6];
        for (int i = 0; i < 12; i++) {
            field[i] = br.readLine().toCharArray();
        }

        // 연쇄가 일어날때까지 반복
        while (isChanged) {
            isChanged = false;

            clearVisited(); // 방문리스트 초기화
            bomb(); // 연쇄발생
            down(); // 연쇄발생 후 남은 뿌요들을 아래로 낙하

            // 연쇄가 한번이라도 일어났다면 count 증가
            if (isChanged) count++;
        }

        bw.write(String.valueOf(count)); // 정답 출력
        br.close();
        bw.close();
    }

    // BFS를 이용한 연쇄폭발
    public static void bomb() {
        groupList = new ArrayList<>(); // 매 연쇄마다 리스트 초기화

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (field[i][j] != '.') { // 뿌요들이 있는 위치에서
                    // 방문 표시
                    visited[i][j] = true;

                    // groupList의 하나의 요소인 새로운 그룹 생성: 같은 색의 인접한 뿌요들의 위치 배열을 담은 리스트
                    ArrayList<int[]> newGroup = new ArrayList<>();
                    newGroup.add(new int[] {i, j});
                    int pt = 0;

                    // DFS 수행
                    while (pt < newGroup.size()) {
                        int x = newGroup.get(pt)[0];
                        int y = newGroup.get(pt++)[1];

                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];

                            if (0 <= nx && nx < 12 && 0 <= ny && ny < 6 && !visited[nx][ny] && field[nx][ny] == field[x][y]) {
                                visited[nx][ny] = true;
                                newGroup.add(new int[] {nx, ny});
                            }
                        }
                    }
                    // 새로운 뿌요그룹을 groupList에 저장
                    groupList.add(newGroup);
                }
            }
        }

        for (ArrayList<int[]> group : groupList) {
            // 인접한 뿌요가 4개이상일 때만
            if (group.size() >= 4) {
                isChanged = true; // 연쇄가 발생했다고 표시
                for (int[] loc : group) {
                    field[loc[0]][loc[1]] = '.'; // 해당 뿌요들의 위치의 값을 "."으로 바꿔서 폭발시킴
                }
            }
        }
    }

    // 남은 뿌요들을 아래로 낙하시키는 함수
    public static void down() {
        // 열 기준으로
        for (int j = 0; j < 6; j++) {
            // 맨 아래에서부터
            for (int i = 11; i >= 0; i--) {
                // 뿌요가 폭파되고 남은 부분이 있을 때 해당 위치보다 위에있는 가장 가까운 뿌요를 가지고 옴
                if (field[i][j] == '.') {
                    for (int k = i-1; k >= 0; k--) {
                        if (field[k][j] != '.') {
                            field[i][j] = field[k][j];
                            field[k][j] = '.';
                            break; // 뿌요를 가지고 왔으면 break
                        }
                    }
                }
            }
        }
    }

    // 방문리스트 초기화
    public static void clearVisited() {
        for (boolean[] b: visited) {
            Arrays.fill(b, false);
        }
    }
}
/**
 * ? 채점 결과
 * 메모리: 11620KB
 * 시간: 80ms
 * 언어: JAVA
 */
