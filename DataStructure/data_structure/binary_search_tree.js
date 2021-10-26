class Node {
  constructor(val) {
    this.val = val;
    this.left = null;
    this.right = null;
  }
}

class BinarySearchTree {
  constructor() {
    this.root = null;
  }
  insert(val) {
    let newNode = new Node(val)
    if (!this.root) {
      this.root = newNode;
    } else {
      let current = this.root;
      while (true) {
        if (val == current.val) return undefined;
        if (current.val > newNode.val) {
          if (current.left) {
            current = current.left;
          } else {
            current.left = newNode;
            break;
          }
        } else {
          if (current.right) {
            current = current.right;
          } else {
            current.right = newNode;
            break;
          }
        }
      }
      return true;
    }
  }
  search(val) {
    if (!this.root) return undefined;
    let current = this.root;
    while(true) {
      if (current.val == val) return true;
      if (current.val > val) {
        if (current.left) {
          current = current.left;
        } else {
          return false;
        }
      } else {
        if (current.right) {
          current = current.right;
        } else {
          return false;
        }
      } 
    }
  }
}

let bst = new BinarySearchTree();
bst.insert(10)
bst.insert(9)
bst.insert(11)
bst.insert(12)
bst.insert(13)
console.log(bst.search(12))
