"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/42626?language=python3

입력에시
scoville
[1, 2, 3, 9, 10, 12]
K
7

결과예시
2
"""

import heapq

def solution(scoville, K):
    count = 0
    heapq.heapify(scoville)
    
    while scoville[0] < K:
        if len(scoville) > 1:
            heapq.heappush(scoville, heapq.heappop(scoville) + (heapq.heappop(scoville) * 2))
            count += 1
        else:
            return -1
        
    return count