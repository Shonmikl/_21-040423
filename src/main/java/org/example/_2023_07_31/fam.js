function getFamous(persons) {
    let f = null;
    let first = 0;
    let last = persons.length - 1;

    while (first !== last) {
        if (persons[first].knows(persons[last])) {
            first++;
        } else {
            last--;
        }
    }

    for (let i = 0; i < persons.length; i++) {
        if (i !== first &&
            (persons[i].knows(persons[first]) ||
                persons[first].knows(persons[i]))) {
            f = null;
        }
        f = persons[first];
    }

    return f;
}
