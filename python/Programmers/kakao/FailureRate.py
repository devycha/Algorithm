"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/42889

입력예시
N
5
4

stages
[2, 1, 2, 6, 2, 4, 3, 3]
[4,4,4,4,4]

결과예시
[3,4,2,1,5]
[4,1,2,3]
"""
def solution(N, stages):
    answer = []
    
    fail_stage = {}
    
    for n in range(1, N+1):
        challenging_player = stages.count(n)
        
        reached_player = 0
        for stage in stages:
            if stage >= n:
                reached_player += 1
                
        if challenging_player != 0:
            fail_stage[n] = challenging_player / reached_player
        else:
            fail_stage[n] = 0
    
    answer = sorted(fail_stage, key=lambda x : fail_stage[x], reverse=True)
    
    return answer