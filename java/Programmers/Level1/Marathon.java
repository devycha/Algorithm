/**
 * 문제URL
 * https://programmers.co.kr/learn/courses/30/lessons/42576?language=java
 */

class Marathon {
    public String solution(String[] participant, String[] completion) {
	    String answer = "";
	    
	    HashMap<String, Integer> map = new HashMap<String, Integer>();
	    
	    for (String player : participant) {
	    	map.put(player, map.getOrDefault(player, 0) + 1);
	    }
	    
	    for (String player : completion) {
	    	map.put(player, map.get(player) - 1);
	    }
	    
	    Set<String> keySet = map.keySet();
	    Iterator<String> iter = keySet.iterator();
	    
	    while (iter.hasNext()) {
	    	String player = iter.next();
	    	
	    	if (map.get(player) != 0) {
	    		answer = player;
	    		break;
	    	}
	    }
	    
	    return answer;
	  }
}