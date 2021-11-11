function solution(line) {
  var answer = [];
  let line1, line2, numeratorX, numeratorY, denominator, X, Y, minX, minY, maxX, maxY
  let coordinate = [];
  minX = 1001, minY = 1001, maxX = -1001, maxY = -1001;
  for (let i = 0; i < line.length-1; i++) {
      line1 = line[i];
      for (let j = i; j < line.length; j++) {
          line2 = line[j]
          denominator = line1[0]*line2[1] - line1[1]*line2[0]
          numeratorX = line1[1]*line2[2] - line1[2]*line2[1]
          numeratorY = line1[2]*line2[0] - line1[0]*line2[2]
          if (denominator) {
              X = numeratorX / denominator;
              Y = numeratorY / denominator;
              if (X%1 == 0 && Y%1 == 0) {
                  minX = Math.min(minX, X)
                  minY = Math.min(minY, Y)
                  maxX = Math.max(maxX, X)
                  maxY = Math.max(maxY, Y)
                  coordinate.push([X, Y])    
              }
          }
      }
  }
  console.log(coordinate)
  coordinate.map(o => [,o[0]-minX])
  
  return answer;
}