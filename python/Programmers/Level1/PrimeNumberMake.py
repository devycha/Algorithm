"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/12977

입력값 예시
[1,2,3,4]
[1,2,7,6,4]

출력값 예시
1
4
"""
def solution(nums):
    answer = 0
    
    for i in range(len(nums)):
        for j in range(i+1,len(nums)):
            for k in range(j+1, len(nums)):
                sum = nums[i] + nums[j] + nums[k]
                if check(sum):
                    answer += 1
    return answer

def check(sum):
    for i in range(2,sum):
        if sum % i == 0:
            return False
    return True