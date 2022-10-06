/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2166
 *
 * ? 제목: 다각형의 면적
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 2차원 평면상에 N(3 ≤ N ≤ 10,000)개의 점으로 이루어진 다각형이 있다. 이 다각형의 면적을 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 N이 주어진다. 다음 N개의 줄에는 다각형을 이루는 순서대로 N개의 점의 x, y좌표가 주어진다. 좌표값은 절댓값이 100,000을 넘지 않는 정수이다.
 *
 * 4        -> n
 * 0 0      -> points.get(0)
 * 0 10
 * 10 10
 * 10 0     -> points.get(n-1)
 *
 * ? 출력
 * 첫째 줄에 면적을 출력한다. 면적을 출력할 때에는 소수점 아래 둘째 자리에서 반올림하여 첫째 자리까지 출력한다.
 *
 * 100.0
 *
 * ? 채점 결과
 * * 시간: 0.152초
 * * 메모리: 17MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.V;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Point {
    long x;
    long y;

    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}

public class G5_2166 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static ArrayList<Point> points = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. 신발끈 정리
        long val1 = 0;
        long val2 = 0;
        for (int i = 0; i < n; i++) {
            val1 += (points.get(i).x * points.get(i+1).y);
            val2 += (points.get(i+1).x * points.get(i).y);
        }

        // * 3. 출력: 출력값 형변환
        double area = Math.abs(val1 - val2) * 10.0 / 20;
        String str = String.valueOf((long) (area * 10));
        String answer = str.substring(0, str.length()-1) + "." + str.substring(str.length()-1);
        bw.write(answer);

        br.close();
        bw.flush();
        bw.close();
    }

    // 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());

            points.add(new Point(x, y));
        }

        points.add(points.get(0));
    }

}
