function solution(n, edge) {
  let answer = 0;
  let obj = {};
  edge.forEach(node => {
      if (!obj[node[0]]) obj[node[0]] = [];
      if (!obj[node[1]]) obj[node[1]] = [];
      obj[node[0]].push(node[1])
      obj[node[1]].push(node[0])
  })
  let distance = {};
  let queue = [1];
  let visited = {};
  
  distance[1] = 0;
  visited[1] = 1;
  
  let max = 0;
  while (queue.length) {
      let current = queue.shift();
      obj[current].forEach(node => {
          if (!visited[node]) {
              distance[node] = distance[current] + 1;
              max = Math.max(distance[current]+1, max)
              queue.push(node)
              visited[node] = 1;
          }
      })
  }
  for (let key in distance) {
      if (distance[key] == max) answer++;
  }
  
  return answer;
}

// 다른사람풀이
function solution(n, edges) {
  // make adjacent list
  const adjList = edges.reduce((G, [from, to]) => {
      G[from] = (G[from] || []).concat(to);
      G[to] = (G[to] || []).concat(from);
      return G;
  }, {});

  // do BFS
  const queue = [1];
  const visited = {1: true};
  const dist = {1: 0};
  while(queue.length > 0) {
      const node = queue.shift();

      if (adjList[node]) {
          adjList[node].forEach(n => {
              if (!visited[n]) {
                  queue.push(n);
                  visited[n] = true;
                  const d = dist[node] + 1;
                  if (dist[n] == undefined || d < dist[n]) {
                      dist[n] = d;
                  }
              }
          });
      }
  }

  const dists = Object.values(dist);
  const maxDist = Math.max(...dists);
  return dists.filter(d => d == maxDist).length;
}
