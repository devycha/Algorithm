"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/42579?language=python3#

입력예시
genres
["classic", "pop", "classic", "classic", "pop"]
plays
[500, 600, 150, 800, 2500]

결과예시
	[4, 1, 3, 0]
"""

def solution(genres, plays):
    answer = []
    genres_dict = {}
    genres_total_plays = {}
    
    for i, genre in enumerate(genres):
        if genre in genres_dict:
            genres_dict[genre].append((plays[i],i))
            genres_total_plays[genre] += plays[i]
        else:
            genres_dict[genre] = [(plays[i], i)]
            genres_total_plays[genre] = plays[i]
    
    genres_list = sorted(genres_total_plays.items(), key=lambda x: x[1], reverse=True)
    
    for genre in genres_list:
        music_list = sorted(genres_dict[genre[0]], key=lambda x :(-x[0], x[1]))
        
        if len(music_list) > 1:
            for i in range(2):
                answer.append(music_list[i][1])
        else:
            answer.append(music_list[0][1])
    
    return answer