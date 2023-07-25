package ru.codewars.impl;

import ru.codewars.CodeWarsTaskSolution;

import java.util.*;

public class SortBinaryTreeByLevels extends CodeWarsTaskSolution<List<Integer>, SortBinaryTreeByLevels.Node> {
    public static void main(String[] args) {
        (new SortBinaryTreeByLevels()).test();
    }

    @Override
    public List<Integer> solution(Node arg) {
        List<Integer> result = new ArrayList<>();
        Queue<Node> buffer = new LinkedList<>();
        if (arg == null) {
            return result;
        }
        buffer.offer(arg);
        while (!buffer.isEmpty()) {
            int size = buffer.size();
            for (int i = 0; i < size; i++) {
                Node currentNode = buffer.poll();
                result.add(currentNode.value);
                if (currentNode.left != null) {
                    buffer.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    buffer.add(currentNode.right);
                }
            }
        }
        return result;
    }

    @Override
    public Node getTestData() {
        return new Node(new Node(null, new Node(null, null, 4), 2), new Node(new Node(null, null, 5), new Node(null, null, 6), 3), 1);
    }

    @Override
    public List<Integer> getExpectedResult() {
        return Arrays.asList(1, 2, 3, 4, 5, 6);
    }

    @Override
    public int getTaskLvl() {
        return 4;
    }

    public static class Node {
        public Node left;
        public Node right;
        public int value;

        public Node(Node l, Node r, int v) {
            left = l;
            right = r;
            value = v;
        }
    }
}
