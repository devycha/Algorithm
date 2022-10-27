/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/14395
 *
 * ? 제목: 4연산
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 정수 s가 주어진다. 정수 s의 값을 t로 바꾸는 최소 연산 횟수를 구하는 프로그램을 작성하시오.
 * 사용할 수 있는 연산은 아래와 같다.
 *
 * s = s + s; (출력: +)
 * s = s - s; (출력: -)
 * s = s * s; (출력: *)
 * s = s / s; (출력: /) (s가 0이 아닐때만 사용 가능)
 *
 * ? 입력 & 파싱
 * 첫째 줄에 s와 t가 주어진다. (1 ≤ s, t ≤ 109)
 *
 * 7 392 -> s t
 *
 * ? 출력
 * 첫째 줄에 정수 s를 t로 바꾸는 방법을 출력한다. s와 t가 같은 경우에는 0을, 바꿀 수 없는 경우에는 -1을 출력한다. 가능한 방법이 여러 가지라면, 사전 순으로 앞서는 것을 출력한다.
 * 연산의 아스키 코드 순서는 '*', '+', '-', '/' 이다.
 *
 * +*+
 *
 * ? 채점 결과
 * * 시간: 0.124초
 * * 메모리: 15MB
 * * 언어: JAVA8
 * * 시도: 9
 */
package Gold.V;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class NumberFour {
    long num;
    String op;

    public NumberFour(long num, String op) {
        this.num = num;
        this.op = op;
    }
}

public class G5_14395 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        long s = Long.parseLong(st.nextToken());
        long t = Long.parseLong(st.nextToken());

        HashSet<Long> set = new HashSet<>();

        if (s == t) {
            System.out.println(0);
        } else if (t == 1) {
            System.out.println("/");
        } else {
            Queue<NumberFour> queue = new LinkedList<>();
            queue.add(new NumberFour(s, ""));
            boolean find = false;

            while (!queue.isEmpty()) {
                NumberFour nf = queue.poll();

                if (nf.num == t) {
                    System.out.println(nf.op);
                    find = true;
                    break;
                }

                long prod = nf.num * nf.num;
                String prodOp = nf.op + "*";

                long sum = 2 * nf.num;
                String sumOp = nf.op + "+";

                long div = 1;
                String divOp = nf.op + "/";


                if (!set.contains(prod)) {
                    set.add(prod);
                    queue.add(new NumberFour(prod, prodOp));
                }


                if (!set.contains(sum)) {
                    set.add(sum);
                    queue.add(new NumberFour(sum, sumOp));
                }


                if (!set.contains(div)) {
                    set.add(div);
                    queue.add(new NumberFour(div, divOp));
                }
            }

            if (!find) {
                System.out.println(-1);
            }
        }
    }
}
