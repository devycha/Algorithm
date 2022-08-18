/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2473
 *
 * ? 제목: 세 용액
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * KOI 부설 과학연구소에서는 많은 종류의 산성 용액과 알칼리성 용액을 보유하고 있다. 각 용액에는 그 용액의 특성을 나타내는 하나의 정수가 주어져있다.  산성 용액의 특성값은 1부터 1,000,000,000까지의 양의 정수로 나타내고, 알칼리성 용액의 특성값은 -1부터 -1,000,000,000까지의 음의 정수로 나타낸다.
 * 같은 양의 세 가지 용액을 혼합한 용액의 특성값은 혼합에 사용된 각 용액의 특성값의 합으로 정의한다. 이 연구소에서는 같은 양의 세 가지 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다.
 * 예를 들어, 주어진 용액들의 특성값이 [-2, 6, -97, -6, 98]인 경우에는 특성값이 -97와 -2인 용액과 특성값이 98인 용액을 혼합하면 특성값이 -1인 용액을 만들 수 있고, 이 용액이 특성값이 0에 가장 가까운 용액이다. 참고로, 세 종류의 알칼리성 용액만으로나 혹은 세 종류의 산성 용액만으로 특성값이 0에 가장 가까운 혼합 용액을 만드는 경우도 존재할 수 있다.
 * 산성 용액과 알칼리성 용액이 주어졌을 때, 이 중 같은 양의 세 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 세 용액을 찾는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에는 전체 용액의 수 N이 입력된다. N은 3 이상 5,000 이하의 정수이다. 둘째 줄에는 용액의 특성값을 나타내는 N개의 정수가 빈칸을 사이에 두고 주어진다. 이 수들은 모두 -1,000,000,000 이상 1,000,000,000 이하이다. N개의 용액들의 특성값은 모두 다르고, 산성 용액만으로나 알칼리성 용액만으로 입력이 주어지는 경우도 있을 수 있다.
 *
 * 5                -> n
 * -2 6 -97 -6 98   -> arr[0] ~ arr[n-1]
 *
 * ? 출력
 * 첫째 줄에 특성값이 0에 가장 가까운 용액을 만들어내는 세 용액의 특성값을 출력한다. 출력해야하는 세 용액은 특성값의 오름차순으로 출력한다. 특성값이 0에 가장 가까운 용액을 만들어내는 경우가 두 개 이상일 경우에는 그 중 아무것이나 하나를 출력한다.
 *
 * -6 -3 -2
 *
 * ? 채점 결과
 * * 시간: 168ms
 * * 메모리: 14MB
 * * 언어: JAVA8
 * * 시도: 4
 */
package Gold.III;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G3_2473 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static int[] arr;

    // * 초기 설정
    static int[] result = new int[3]; // * 0에 가장 가까운 세 용액의 특성값
    static long max = Long.MAX_VALUE; // * 세 용액의 특성값의 합의 절댓값의 최댓값

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. 투포인터를 이용하여 세 용액의 특성값의 합을 비교하여 합이 0에 가장 가까운 세 용액 찾기
        for (int i = 0; i < arr.length-2; i++) {
            int base = arr[i]; // * 기준이 되는 용액
            int left = i+1; // * 기준 용액보다 특성값이 큰 용액 중 가장 특성값이 작은 용액
            int right = arr.length-1; // * 특성값이 가장 큰 용액

            // * 투포인터
            while (left < right) {
                long sum = (long) base + arr[left] + arr[right];

                // * 0에 더 가까운 세 용액을 찾았을 때, 결과와 max값 업데이트
                if (Math.abs(sum) <= max) {
                    max = Math.abs(sum);
                    result[0] = base;
                    result[1] = arr[left];
                    result[2] = arr[right];
                }

                // * 특성값의 합이 음수일 때 left 포인터 1증가
                if (sum < 0) {
                    left++;
                } else { // * 특성값의 합이 양수일 때 right 포인터 1 감소
                    right--;
                }

            }
        }

        // * 3. 결과 출력
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }

    // ! 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        int p = 0;
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[p++] = Integer.parseInt(st.nextToken());
        }

        // * 용액들을 특성값을 기준으로 오름차순 정렬
        Arrays.sort(arr);
    }
}