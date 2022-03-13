const fs = require("fs");
const [N, ...input] = fs.readFileSync("input.txt").toString().split("\n");
for (let i = 0; i < input.length - 2; i += 3) {
  check(input[i], input[i + 1], input[i + 2]);
}

function check(commands, count, nums) {
  let arr = [];
  nums = nums.split(/[\[|\]|,]/);
  for (let i = 0; i < nums.length; i++) {
    if (nums[i] != "" && Number.isInteger(+nums[i])) {
      arr.push(+nums[i]);
    }
  }

  let left = 0,
    right = arr.length - 1;
  let reverseCount = 0;

  for (let i = 0; i < commands.length; i++) {
    if (commands[i] == "R") {
      reverseCount++;
    } else if (commands[i] == "D") {
      if (reverseCount % 2 == 0) {
        left++;
      } else {
        right--;
      }
    }
  }
  //   console.log(arr, left, right);
  if (left > right + 1) {
    console.log("error");
  } else {
    if (right - left == 1 && arr.length == 0) {
      console.log("error");
    } else {
      let result = [];
      if (reverseCount % 2 == 0) {
        for (let i = left; i <= right; i++) {
          result.push(arr[i]);
        }
      } else {
        for (let i = right; i >= left; i--) {
          result.push(arr[i]);
        }
      }
      result = "[" + result.join(",") + "]";
      console.log(result);
    }
  }
}
