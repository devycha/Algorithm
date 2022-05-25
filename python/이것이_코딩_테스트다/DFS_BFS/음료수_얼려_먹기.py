"""
N X M 크기의 얼음 틀이 있다. 구멍이 뚫려 있는 부분은 0, 칸막이 존재하는 부분은 1로 표시된다.
구멍이 뚫려 있는 부분끼리 상, 하, 좌, 우로 붙어 있는 경우 서로 연결되어 있는 것을 간주한다.
이때 얼음 틀의 모양이 주어졌을 때 생성되는 총 아이스크림의 개수를 구하라.

입력예시
4 5
00110
00011
11111
00000

결과
3
"""

n, m = map(int, input().split())

graph = []
for i in range(n):
    graph.append(list(map(int, input())))

def dfs(x, y):
    # 주어진 범위를 벗어나는 경우 바로 종료
    if x <= -1 or x >= n or y <= -1 or y >= m:
        return False
    
    # 방문한 노드가 0이면
    if graph[x][y] == 0:
        # 1로 바꾸면서 방문처리
        graph[x][y] = 1

        # 상, 하, 좌, 우 위치도 모두 방문
        dfs(x-1, y)
        dfs(x+1, y)
        dfs(x, y-1)
        dfs(x, y+1)
        return True
    return False

# 모든 노드 방문
result = 0
for i in range(n):
    for j in range(m):
        if dfs(i,j) == True:
            result += 1

# 정답
print(result)