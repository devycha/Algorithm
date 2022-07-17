/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ! address: https://www.acmicpc.net/problem/1941
 *
 * ! 문제
 * 총 25명의 여학생들로 이루어진 여학생반은 5×5의 정사각형 격자 형태로 자리가 배치되었고, 얼마 지나지 않아 이다솜과 임도연이라는 두 학생이 두각을 나타내며 다른 학생들을 휘어잡기 시작했다. 곧 모든 여학생이 ‘이다솜파’와 ‘임도연파’의 두 파로 갈라지게 되었으며, 얼마 지나지 않아 ‘임도연파’가 세력을 확장시키며 ‘이다솜파’를 위협하기 시작했다.
 * 위기의식을 느낀 ‘이다솜파’의 학생들은 과감히 현재의 체제를 포기하고, ‘소문난 칠공주’를 결성하는 것이 유일한 생존 수단임을 깨달았다. ‘소문난 칠공주’는 다음과 같은 규칙을 만족해야 한다.
 * 이름이 이름인 만큼, 7명의 여학생들로 구성되어야 한다.
 * 강한 결속력을 위해, 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
 * 화합과 번영을 위해, 반드시 ‘이다솜파’의 학생들로만 구성될 필요는 없다.
 * 그러나 생존을 위해, ‘이다솜파’가 반드시 우위를 점해야 한다. 따라서 7명의 학생 중 ‘이다솜파’의 학생이 적어도 4명 이상은 반드시 포함되어 있어야 한다.
 * 여학생반의 자리 배치도가 주어졌을 때, ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수를 구하는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 'S'(이다‘솜’파의 학생을 나타냄) 또는 'Y'(임도‘연’파의 학생을 나타냄)을 값으로 갖는 5*5 행렬이 공백 없이 첫째 줄부터 다섯 줄에 걸쳐 주어진다.
 *
 * YYYYY    -> arr[0]
 * SYSYS
 * YYYYY
 * YSYYS
 * YYYYY    -> arr[4]
 *
 * ! 출력
 * 첫째 줄에 ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수를 출력한다.
 *
 * 2
 *
 * ! 채점 결과
 * 메모리: 298708KB
 * 시간: 2604ms
 * 언어: JAVA
 */
package Gold.III;

import java.io.*;
import java.util.ArrayList;

public class G3_1941 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static char[][] arr;
    static int origin = (int) Math.pow(2, 25) - 1;
    static int answer = 0;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        input(); // 입력 받기
        // 브루트포스
        for (int i = 1; i < (1 << 25); i++) {
            String s = Integer.toBinaryString(origin & i);
            if (!lengthCheck(s)) continue; // 길이 체크

            int[] loc = new int[7];
            int count = 0;
            for (int j = s.length()-1; j >= 0; j--) {

                if (s.charAt(j) == '1') {
                    loc[count++] = s.length() - j - 1;
                }
            }
            if (check(loc)) answer++; // 인접해있는지 + S가 4 이상인지

        }
        bw.write(String.valueOf(answer)); // 정답 출력
        bw.flush();
    }

    // 길이 체크
    public static boolean lengthCheck(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') count++;
            if (count > 7) return false;
        }
        return count == 7;
    }

    // 인접해있는지 + S가 4 이상인지
    public static boolean check(int[] loc) {
        int sCount = 0;
        int yCount = 0;

        ArrayList<Integer> list = new ArrayList<>();
        list.add(loc[0]);
        loc[0] = -1;
        int pt = 0;

        while (pt < list.size()) {
            int num = list.get(pt++);
            int x = num / 5;
            int y = num % 5;
            if (arr[x][y] == 'S') sCount++;
            else yCount++;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nextNum = 5 * nx + ny;

                if (0 <= nx && nx < 5 && 0 <= ny && ny < 5) {
                    for (int j = 0; j < 7; j++) {
                        if (loc[j] == nextNum) {
                            list.add(nextNum);
                            loc[j] = -1;
                        }
                    }
                }
            }
        }
        if (sCount < 4 || list.size() < 7) return false;
        return true;
    }

    // 입력 받기
    public static void input() throws IOException {
        arr = new char[5][5];
        for (int i = 0; i < 5; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }
}
