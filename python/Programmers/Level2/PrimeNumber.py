"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/42839?language=python3

입력예시
"17"
"011"

출력예시
3
2
"""

# 다른 사람의 풀이 과정을 보고 푼 문제이다.
from itertools import permutations

def solution(numbers):
    prime_set = set()
    
    for i in range(len(numbers)):
        # permutations를 이용해 1, 7, 17, 71을 만든다.
        numbers_permutation = permutations(list(numbers), i + 1)
        number_perm_list = map(int, map("".join, numbers_permutation))

        # 같은 숫자가 중복적으로 나올 수 있기 때문에 set을 이용해 중복 제거
        prime_set |= set(number_perm_list)
    
    # 0, 1 은 미리 제거
    prime_set -= set(range(0,2))

    # 에라토스테네스의 체를 이용한 풀이로 가장 큰 숫자의 제곱근을 구한다
    lim = int(max(prime_set) ** 0.5) + 1
    
    # 제곱근 이하의 숫자들의 배수를 제거
    for i in range(2, lim):
        prime_set -= set(range(i*2, max(prime_set) + 1, i))
    
    # 남아 있는 숫자들이 소수가 된다
    return len(prime_set)