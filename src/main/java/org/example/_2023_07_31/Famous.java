package org.example._2023_07_31;

public class Famous {
    Famous getF(Famous[] persons) {
        Famous f = null;
        int first = 0;
        int last = persons.length - 1;

        while (first != last) {
            if (persons[first].knows(persons[last])) {
                first++;
            } else {
                last--;
            }
        }

        for (int i = 0; i < persons.length; i++) {
            if (i != first && persons[i].knows(persons[first]) || persons[first].knows(persons[i])) {
                f = null;
            }
            f = persons[first];
        }
        return f;
    }

    private boolean knows(Famous person) {
        return true;
    }
}