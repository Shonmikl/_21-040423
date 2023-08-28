function findOptimalBP() {
    const weights = [3,4,5,8,9];
    const prices  = [1,6,4,7,6];
    const maxWeight = 13;

    let maxPrice = 0;
    let optimalItems = [];

    function findCombination(currentIndex, currentWeight, currentPrice, selectedItems) {
        if(currentIndex === weights.length) {
            if(currentWeight <= maxWeight && currentPrice > maxPrice) {
                maxPrice = currentPrice;
                optimalItems = selectedItems.slice();
            }
            return;
        }

        if(currentWeight + weights[currentIndex] <= maxWeight) {
            selectedItems.push(currentIndex);
            findCombination(currentIndex + 1, currentWeight, currentPrice, selectedItems);
            selectedItems.pop();
        }

        findCombination(currentIndex + 1, currentWeight, currentPrice, selectedItems)
    }

    findCombination(0, 0, 0, []);

    console.log("OPT: ");
    for(let index of optimalItems) {
        console.log(index);
    }
}

findOptimalBP();