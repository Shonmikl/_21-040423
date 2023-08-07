function fibLoops(fibIndex) {
    if (fibIndex < 0) {
        throw new Error("Invalid argument!");
    }
    var fib1 = -1; // number -2
    var fib2 = 1;  // number -1
    for (var n = 0; n <= fibIndex; n++) {
        var fibCurrent = fib1 + fib2;
        fib1 = fib2;
        fib2 = fibCurrent;
    }
    return fib2;
}


function fibRecursive(fibIndex) {
    if (fibIndex < 0) {
        throw new Error("Invalid argument");
    }
    if (fibIndex === 0) {
        return 0;
    }
    if (fibIndex === 1 || fibIndex === 2) {
        return 1;
    }
    return fibRecursive(fibIndex - 2) + fibRecursive(fibIndex - 1);
}

// использование функции:
console.log(fibRecursive(10)); // выводит 55


function fibStreamsApi(fibIndex) {
    let arr = [0, 1];
    for(let i = 2; i <= fibIndex; i++){
        arr.push(arr[i - 1] + arr[i - 2]);
    }
    return arr[fibIndex];
}

console.log(fibStreamsApi(10)); // Выводит 55