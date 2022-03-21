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
  BFS() {
    let queue = [];
    let data = [];
    queue.push(this.root)
    while (queue.length) {
      let node = queue.shift()
      data.push(node)
      if (node.left) queue.push(node.left)
      if (node.right) queue.push(node.right)
    }
    return data;
  }
  DFS_preorder() {
    let data = [];
    function traverse(node) {
      data.push(node);
      if (node.left) traverse(node.left);
      if (node.right) traverse(node.right);
    }
    traverse(this.root);
    return data;
  }
  DFS_postorder() {
    let data = [];
    function traverse(node) {
      if (node.left) traverse(node.left);
      if (node.right) traverse(node.right);
      data.push(node);
    }
    traverse(this.root);
    return data;
  }
  DFS_inorder() {
    let data = [];
    function traverse(node) {
      if (node.left) traverse(node.left);
      data.push(node);
      if (node.right) traverse(node.right);
    }
    traverse(this.root);
    return data;
  }
  
}

let bst = new BinarySearchTree();
bst.insert(10)
bst.insert(9)
bst.insert(8)
bst.insert(7)
bst.insert(11)
bst.insert(12)
bst.insert(13)
bst.insert(14)
bst.insert(15)
console.log(bst.DFS_inorder())
