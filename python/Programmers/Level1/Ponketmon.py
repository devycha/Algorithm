"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/1845?language=python3

입출력 예
nums	      result
[3,1,2,3]	    2
[3,3,3,2,2,4]	3
[3,3,3,2,2,2]	2
"""

def solution(nums):
    answer = []
    selectNum = len(nums) / 2
    checkNum = 0
    
    nums.sort()
    
    for num in nums:
        if checkNum != num:
            answer.append(num)
            checkNum = num
            
    if len(answer) > selectNum:
        return selectNum
    else:
        return len(answer)