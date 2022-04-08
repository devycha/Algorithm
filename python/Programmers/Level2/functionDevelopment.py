"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/42586

입력예시
progresses
[93, 30, 55]
[95, 90, 99, 99, 80, 99]

speeds
[1, 30, 5]
[1, 1, 1, 1, 1, 1]

결과예시
[2, 1]
[1, 3, 2]

"""

def solution(progresses, speeds):
    answer = []

    while (len(progresses) > 0):
        count = 0

        for i in range(len(progresses)):
            progresses[i] = progresses[i] + speeds[i]

        for i in range(len(progresses)):
            if progresses[i] >= 100:
                progresses[i] = 0
                count += 1
            else:
                break

        if count > 0:
            answer.append(count)

        tempPro = []
        tempSpe = []
        for i in range(len(progresses)):
            if progresses[i] != 0:
                tempPro.append(progresses[i])
                tempSpe.append(speeds[i])

        progresses = tempPro
        speeds = tempSpe

    return answer