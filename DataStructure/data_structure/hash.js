function hash(str, length) {
    let total = 0;
    for (let i = 0; i < str.length; i++) {
        total = (total + str[i].charCodeAt() - 96) % length
    }
    return total;
}

class HashTable {
    constructor(size=53) {
        this.keyMap = new Array(size);
    }
    _hash(key) {
        let total = 0;
        let WEIRD_PRIME = 31;
        for (let i = 0; i < Math.min(key.length, 100); i++) {
            let char = key[i];
            let value = char.charCodeAt(0) - 96;
            total = (total + WEIRD_PRIME + value) & this.keyMap.length;
        }
        return total;
    }
    set(key, val) {
        let hash = this._hash(key);
        if (!this.keyMap[hash]) {
            this.keyMap[hash] = [];
        }
        this.keyMap[hash].push([key, val])
    }
    get(key) {
        let hash = this._hash(key);
        if (this.keyMap[hash]) {
            return this.keyMap[hash].filter(a => a[0] == key)
        }
        return undefined;
    }
    values() {
        let valuesArr = [];
        this.keyMap.forEach(arr => {
            if (arr) {
                arr.forEach(element => {
                    if (!valuesArr.includes(element[1])) {
                        valuesArr.push(element[1]);
                    }
                })
            }
        })
        return valuesArr;
    }
    keys() {
        let keysArr = [];
        this.keyMap.forEach(arr => {
            if (arr) {
                arr.forEach(element => {
                    if (!keysArr.includes(element[0])) {
                        keysArr.push(element[0]);
                    }
                })
            }
        })
        return keysArr;
    }
}

let ht = new HashTable();
ht.set('a', 1)
ht.set('b', 2)
ht.set('c', 3)
ht.set('d', 4)
ht.set('aa', 4)
ht.set('bb', 4)
ht.set('cc', 4)
console.log(ht)
console.log(ht.get('cc'))
console.log(ht.values())
console.log(ht.keys())

