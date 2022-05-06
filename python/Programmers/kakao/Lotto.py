"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/77484

입력예시
lottos		
[44, 1, 0, 0, 31, 25]
[0, 0, 0, 0, 0, 0]
[45, 4, 35, 20, 3, 9]

win_nums
[31, 10, 45, 1, 6, 19]
[38, 19, 20, 40, 15, 25]
[20, 9, 3, 45, 4, 35]

결과예시
result
[3, 5]
[1, 6]
[1, 1]
"""

def solution(lottos, win_nums):
    answer = []
    
    rank = [6,6,5,4,3,2,1]
    
    min_answer = 0
    for win_num in win_nums:
        if win_num in lottos:
            min_answer += 1
    
    zero_count = 0
    for lotto in lottos:
        if lotto == 0:
            zero_count += 1
    
    max_answer = min_answer + zero_count
    
    answer.append(rank[max_answer])
    answer.append(rank[min_answer])

    return answer