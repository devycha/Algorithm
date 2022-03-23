# 문제URL
# https://programmers.co.kr/learn/courses/30/lessons/42576?language=python3

# 내가 푼 방법
def solution(participant, completion):
    answer = ''

    participant.sort()
    completion.sort()
    
    for i in range(len(completion)):
        if (participant[i] != completion[i]):
            return participant[i]
    
    return participant[i+1]

# 다른 사람들이 푼 방법
def solution2(participant, completion):
    answer = ''
    dic = {}
    sumHash = 0

    # 1. participant list의 hash를 구하고, hash 값을 더함
    for part in participant:
        dic[hash(part)] = part
        sumHash += hash(part)

    # 2. completion list의 hash를 빼줌
    for comp in completion:
        sumHash -= hash(comp)

    # 3. 남은 값이 완주하지 못한 선수의 hash 값이 됨
    answer = dic[sumHash]

    return answer

from collection import Counter

def solution3(participant, completion):
    # 1. participant의 counter를 구함
    part_counter = Counter(participant)

    # 2. completion의 counter를 구함
    comp_counter = Counter(completion)

    # 3. 둘의 차를 구하고, key를 찾는다
    answer = part_counter - comp_counter

    # answer = Counter(participant) - Counter(completion) -> 한 줄 요약

    return list(answer.keys())[0]