function countFrequency(text) {
  const freqMap = new Map();
  for (let i = 0; i < text.length; i++) {
    const char = text.charAt(i);
    const count = freqMap.get(char);
    freqMap.set(char, count !== undefined ? count + 1 : 1);
  }
  return freqMap;
}

class CodeTreeNode {
  constructor(content, weight, left, right) {
    this.content = content;
    this.weight = weight;
    this.left = left;
    this.right = right;
  }

  getCodeForCharacter(ch, parentPath) {
    if (this.content === ch) {
      return parentPath;
    } else {
      if (this.left) {
        const path = this.left.getCodeForCharacter(ch, parentPath + '0');
        if (path !== null) {
          return path;
        }
      }
      if (this.right) {
        const path = this.right.getCodeForCharacter(ch, parentPath + '1');
        if (path !== null) {
          return path;
        }
      }
    }
    return null;
  }
}

function huffman(codeTreeNodes) {
  while (codeTreeNodes.length > 1) {
    codeTreeNodes.sort((a, b) => b.weight - a.weight);
    const left = codeTreeNodes.pop();
    const right = codeTreeNodes.pop();
    const parent = new CodeTreeNode(null, right.weight + left.weight, left, right);
    codeTreeNodes.push(parent);
  }
  return codeTreeNodes[0];
}

function huffmanDecode(encoded, tree) {
  let decoded = '';
  let node = tree;
  for (let i = 0; i < encoded.length; i++) {
    node = encoded.charAt(i) === '0' ? node.left : node.right;
    if (node.content !== null) {
      decoded += node.content;
      node = tree;
    }
  }
  return decoded;
}

function main() {
  const text = "Despite crippling sanctions, " +
    "Pyongyang has conducted six nuclear tests between 2006 and 2017, and " +
    "is rumoured to be planning a seventh. It has continued to advance its military capability - " +
    "in breach of United Nations Security Council resolutions - to threaten its neighbours and potentially " +
    "even bring the US mainland within striking range.";

  const frequencies = countFrequency(text);

  const codeTreeNodes = [];
  for (const c of frequencies.keys()) {
    codeTreeNodes.push(new CodeTreeNode(c, frequencies.get(c)));
  }

  const tree = huffman(codeTreeNodes);

  const codes = new Map();
  for (const c of frequencies.keys()) {
    codes.set(c, tree.getCodeForCharacter(c, ''));
  }

  console.log('Таблица префиксных кодов:', codes);

  let encoded = '';
  for (let i = 0; i < text.length; i++) {
    encoded += codes.get(text.charAt(i));
  }

  console.log('Размер исходной строки:', text.length * 8, 'бит');
  console.log('Размер сжатой строки:', encoded.length, 'бит');
  console.log('Биты сжатой строки:', encoded);

  const decoded = huffmanDecode(encoded, tree);
  console.log('Расшифровано:', decoded);
}

main();