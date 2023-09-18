package org.example._2023_09_18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        String text = "Despite crippling sanctions, " +
                "Pyongyang has conducted six nuclear tests between 2006 and 2017, and " +
                "is rumoured to be planning a seventh. It has continued to advance its military capability - " +
                "in breach of United Nations Security Council resolutions - to threaten its neighbours and potentially " +
                "even bring the US mainland within striking range.";

//        text = "MAMA MYLA RAMU";

        // вычисляем частоты символов в тексте
        TreeMap<Character, Integer> frequencies = countFrequency(text);

        // генерируем список листов дерева
        ArrayList<CodeTreeNode> codeTreeNodes = new ArrayList<>();
        for (Character c : frequencies.keySet()) {
            codeTreeNodes.add(new CodeTreeNode(c, frequencies.get(c)));
        }

        // строим кодовое дерево с помощью алгоритма Хаффмана
        CodeTreeNode tree = huffman(codeTreeNodes);

        // генерируем таблицу префиксных кодов для кодируемых символов с помощью кодового дерева
        //ключ это символ, а значение строка содержащая нули и единици которя является кодом этого символа
        TreeMap<Character, String> codes = new TreeMap<>();
        for (Character c : frequencies.keySet()) {
            //добавляем код для нашего символа из нашего дерева, в качестве
            // начального пути передаем пустую строку
            //в которую будут добавлять 0 1.txt
            codes.put(c, tree.getCodeForCharacter(c, ""));
        }

        System.out.println("Таблица префиксных кодов: " + codes.toString());

        // кодируем текст, заменяем символы соответствующими кодами
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            //бедем просто идти по строке и для каждого символа записывать код
            //этого символа который мы с генерировали при помощи кодового дерева
            encoded.append(codes.get(text.charAt(i)));
        }

        System.out.println("Размер исходной строки: " + text.getBytes().length * 8 + " бит");
        System.out.println("Размер сжатой строки: " + encoded.length() + " бит");
        System.out.println("Биты сжатой строки: " + encoded);
        //а как раскодировать?

        // декодируем сжатую информацию обратно
        String decoded = huffmanDecode(encoded.toString(), tree);

        System.out.println("Расшифровано: " + decoded);

        // тест сжатия файла
        //fileCompressTest();
    }

    //1.txt Считаем сколько раз каждый символ встречается в тексте
    private static TreeMap<Character, Integer> countFrequency(String text) {
        TreeMap<Character, Integer> freqMap = new TreeMap<>();
        for (int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            Integer count = freqMap.get(c);
            freqMap.put(c, count != null ? count + 1 : 1);
        }
        return freqMap;
    }

    //2 класс для представления кодового дерева
    private static class CodeTreeNode implements Comparable<CodeTreeNode> {

        //храним символ
        Character content;
        //частота или вес
        int weight;
        //левый потомок
        CodeTreeNode left;
        //правый потомок
        CodeTreeNode right;

        public CodeTreeNode(Character content, int weight) {
            this.content = content;
            this.weight = weight;
        }

        public CodeTreeNode(Character content, int weight, CodeTreeNode left, CodeTreeNode right) {
            this.content = content;
            this.weight = weight;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(CodeTreeNode o) {
            return o.weight - weight;
        }

        // функция которая делает проход по кодовому дереву
        //от корня до конкретного символа и при повороте
        // направо или на лево вычисляет последовательность 0 и 1.txt
        //которые будут кодом данного символа

        //в качестве параметра будет символ который мы ищем и путь в виде 0 и 1.txt
        //к которым мы будем дописывать 0 при сворачивании влево и 1.txt при сворачивании в право
        //по факту это обход дерева в глубину
        public String getCodeForCharacter(Character ch, String parentPath) {
            //наш символ это тот который мы ищем
            //то мы нашли то что искали
            if (content == ch) {
                //возвращаем путь
                return parentPath;
            } else {
                //если есть левый потомок
                if (left != null) {
                    //вызываем рекурсивно ту же функцию
                    //передаем символ который ищем и дописываем пути,те 0
                    String path = left.getCodeForCharacter(ch, parentPath + 0);
                    if (path != null) {
                        //значит что в ветке нашелся нужный символ
                        //значит надо вернуть путь который представляет из себя код
                        return path;
                    }
                }
                if (right != null) {
                    String path = right.getCodeForCharacter(ch, parentPath + 1);
                    if (path != null) {
                        return path;
                    }
                }
            }
            return null;
        }
    }

    //3. реализуем алгоритм
    //возвращает дерево, а принимает в качестве параметра список узлов

    private static CodeTreeNode huffman(ArrayList<CodeTreeNode> codeTreeNodes) {
        //пока список узлов больше чем один
        while (codeTreeNodes.size() > 1) {
            //упорядочиваем узлы по весам
            Collections.sort(codeTreeNodes);
            //берем два узла с самыми маленькими весами
            //получаю из списка узел и тут же его удалю
            //это будет два узла которые мы свяжем промежуточным узлом
            CodeTreeNode left = codeTreeNodes.remove(codeTreeNodes.size() - 1);
            CodeTreeNode right = codeTreeNodes.remove(codeTreeNodes.size() - 1);

            //теперь создаем промежуточный узел
            //вес равен сумме весов потомков
            CodeTreeNode parent = new CodeTreeNode(null, right.weight + left.weight, left, right);
            //теперь кладем его обратно в массив
            //прокручиваем этот алгоритм пока не останется всего один узел который и будет корнем нашего дерева
            codeTreeNodes.add(parent);
        }
        return codeTreeNodes.get(0);
    }

    //теперь декодируем
    //берем строку с битами и идем по дереву
    //пока не дойдем до листа и возвращаем этот символ

    //параметры дерево и строка с битами
    private static String huffmanDecode(String encoded, CodeTreeNode tree) {
        //тут будем хранить расшифрованные данные
        StringBuilder decoded = new StringBuilder();

        //переменная которая будет хранить узел когда будем спускаться по дереву
        //изначально он равен самому корневому узлу
        CodeTreeNode node = tree;
        //теперь идем по битам зашифрованной строки
        for (int i = 0; i < encoded.length(); i++) {
            //если текущий бит 0 тогда идем налево или направо
            node = encoded.charAt(i) == '0' ? node.left : node.right;
            //если мы дошли до какого-то листа и у него есть символ
            if (node.content != null) {
                //добавляем этот символ в последовательность
                decoded.append(node.content);
                //и возвращаем текущий узел на корень дерева
                node = tree;
            }
        }
        return decoded.toString();
    }
}