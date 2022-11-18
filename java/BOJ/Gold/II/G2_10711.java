/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/10711
 *
 * ? 제목: 모래성
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 명우와 친구들은 여름방학을 맞이하여 해변가에 놀러가기로 했다. 이번에 여행을 떠난 해수욕장의 이름은 ALPS(Awsome Land & Poor Sea)이다.
 * 해변가에서 수영복을 입은 미녀들에게 관심이 많은 원철이와는 달리 명우는 해변가의 모래에 더 관심이 많다. 해변가의 모래는 무한한 것들을 만들 수 있는 가능성을 내포하고 있다. 또한 이렇게 만들어진 작품이 파도에 의해 사라지는 모습은, 마치 자신이 가장 빛날 수 있는 시간을 알고 스스로 아름답게 산화하려는 것으로 보인다. 이런 완벽에 가까운 물품인 모래를 두고서 해수욕이나 헤엄을 치는 것은 인생을 낭비하는 것과 같다고 생각한다. 하지만 아무도 명우의 말에 공감해주지 못했고, 결국 명우는 혼자서 모래성을 만들었다.
 *
 * 다른 친구들이 혼신의 힘을 다해 놀고있을 때 명우는 혼신의 힘을 다해 모래성을 쌓았다. 이 모래성은 언젠간 파도에 의해서 무너질 터... 명우는 이런 무너짐도 예술의 일환으로 이해한 사람이므로 무너지는 것도 고려해서 모래성을 만들었다.
 *
 * 그가 만든 모래성을 2차원 격자단위로 만들었으며, 각 격자마다 튼튼함의 정도를 다르게 해서 성을 만들었다. 이 튼튼함은 1부터 9 사이의 숫자로 표현될 수 있다. 이 튼튼함은, 자기 격자 주변의 8방향 (위 아래 왼쪽 오른쪽, 그리고 대각선) 을 봐서 모래성이 쌓여있지 않은 부분의 개수가 자기 모래성의 튼튼함보다 많거나 같은 경우 파도에 의해서 무너질 수 있음을 의미한다. 그 이외의 경우는 파도가 쳐도 무너지지 않는다. 모래성이 무너진 경우, 그 격자는 모래성이 쌓여있지 않은 것으로 취급한다.
 *
 * 이 모래성은 언젠가는 파도에 의해서 깎이고 깎여서, 결국 한가지 형태로 수렴할 것이다. 모래성을 완성한 명우는 문득 자신이 만든 예술품의 수명이 궁금해졌다. 모래성은 위에 서술한 바와 같이 파도가 한번 칠 때마다 특정 부분이 무너저내리는 방식으로 모양이 변화된다. 모래성이 더이상 모양이 변하지 않게 되려면 (모양이 수렴되려면) 파도가 몇번 쳐야하는지 구해보자.
 *
 * ? 입력 & 파싱
 * 첫째 줄에는 모래성의 가로세로 격자 크기 H, W가 주어진다. (1 ≤ H, W ≤ 1,000)
 * 그 다음 H줄에 걸쳐 W개의 문자로 모래성의 상태를 나타내는 문자가 들어온다.
 * 각 문자는 1~9 사이의 숫자, 또는 '.' 이다. 1~9는 그 격자의 모래의 강도를 나타내며, '.'는 모래가 없다는 뜻이다.
 * 모래성은 격자의 가장자리와 접해 있지 않다.
 *
 * 5 6      -> h w
 * ......   -> arr[0][0] ~ arr[0][w-1]
 * .939..
 * .3428.
 * .9393.
 * ......   -> arr[h-1][0] ~ arr[h-1][w-1]
 *
 * ? 출력
 * 몇번의 파도가 몰려오고나서야 모래성의 상태가 수렴하는지를 구한다.
 *
 * 3
 *
 * ? 채점 결과
 * * 시간: 0.388초
 * * 메모리: 70MB
 * * 언어: JAVA8
 * * 시도: 3
 */
package Gold.II;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G2_10711 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int h, w;
    static char[][] arr;
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        input();

        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();

                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if (dx == 0 && dy == 0) {
                            continue;
                        }

                        int nx = cur[0] + dx;
                        int ny = cur[1] +dy;

                        if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
                            continue;
                        }

                        if (arr[nx][ny] == '.') {
                            continue;
                        }

                        if (--arr[nx][ny] == '0') {
                            arr[nx][ny] = '.';
                            queue.add(new int[] {nx, ny});
                        }
                    }
                }
            }

            if (!queue.isEmpty()) {
                time++;
            }
        }

        System.out.println(time);
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        queue = new LinkedList<>();
        arr = new char[h][w];

        for (int i = 0; i < h; i++) {
            char[] sands = br.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                arr[i][j] = sands[j];
                if (arr[i][j] == '.') {
                    queue.add(new int[] {i, j});
                }
            }
        }
    }
}
