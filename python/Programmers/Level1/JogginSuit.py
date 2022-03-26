# 문제URL
# https://programmers.co.kr/learn/courses/30/lessons/42862?language=python3

def solution(n, lost, reserve):
    answer = 0

    lost.sort()
    reserve.sort()
    lostStudent=[]

    # 1. 체육복을 가지고 있는 사람을 찾음(여분의 체육복이 있는 사람이 도난 당한 경우를 계산)
    for lo in lost:
        if lo in reserve:
            reserve.remove(lo)
        else:
            lostStudent.append(lo)


    # 2. 체육복을 가지고 있는 사람의 앞뒤로 잃어버린 사람 한명에게 에게 체육복 나눠줌
    for res in reserve:
        if (res-1) in lostStudent:
            lostStudent.remove(res-1)
        elif (res+1) in lostStudent:
            lostStudent.remove(res+1)

    # 3. 체육복을 다 나눠주고도 잃어버린 사람이 몇 명인지 확인
    totalLost = len(lostStudent)

    # 4. 전체 학생에서 잃어버린 사람의 수를 빼면 수업을 들을 수 있는 학생의 수가 나옴
    answer = n - totalLost

    return answer