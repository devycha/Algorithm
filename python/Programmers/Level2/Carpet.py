"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/42842

입력예시
brown
10
8
24
yellow
2
1
24

출력예시
[4, 3]
[3, 3]
[8, 6]
"""

def solution(brown, yellow):
    area = brown + yellow
    
    # 가로 길이가 세로 길이보다 크기 때문에 큰 수부터 반복
    for width in range(area, 1, -1):
        # 가로 길이 찾기
        if area % width == 0:
            # 세로 길이
            height = area / width
            
            # yellow 면적을 구하는 공식에 대입해서 값 확인
            if yellow == (width - 2) * (height - 2):
                return [width, height]