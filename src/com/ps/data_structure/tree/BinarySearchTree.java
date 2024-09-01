package com.ps.data_structure.tree;

/**
 * 이진 탐색 트리 (Binary Search Tree)
 * 각 노드가 최대 2개의 자식 노드를 가지며,
 * 노드의 왼쪽 서브트리에는 현재 노드보다 작은 값을 가지고, 노드의 오른쪽 서브트리에는 노드보다 큰 값을 가지는 트리
 * 노드가 삽입되는 순서에 따라 트리가 불균형해질 수 있음.
 * 시간 복잡도: 탐색, 삽입, 삭제 연산은 평균적으로 O(log n) 의 시간 복잡도를 가지며, 최악의 경우 O(n) 까지 증가할 수 있음.
*/
public class BinarySearchTree<T extends Comparable<T>>  {
    TreeNode<T> root;
    int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * 트리에 노드 삽입
     */
    public void insert (T value) {
        root = insertRec(root, value);
    }

    /**
     * 트리에 노드 삽입을 수행하는 함수.
     * 트리에 이미 같은 값이 존재하면 삽입하지 않음
     */
    public TreeNode<T> insertRec (TreeNode<T> node, T value) {
        // 비어있는 서브트리에 신규 노드 삽입
        if (node == null) {
            size++;
            return new TreeNode<>(value);
        }

        // 삽입할 값이 존재하는 경우 삽입 X
        if (node.data == value) return node;

        int compareResult = value.compareTo(node.data);
        // 삽입할 값이 노드보다 작은 경우 왼쪽 서브트리 탐색
        if (compareResult < 0) node.left = insertRec(node.left, value);
        // 삽입할 값이 노드보다 큰 경우 오른쪽 서브트리 탐색
        else if (compareResult > 0) node.right = insertRec(node.right, value);

        return node;
    }

    /**
     * 노드 삭제
     */
    public void delete (T value) {
        root = deleteRec(root, value);
    }

    /**
     * 트리에서 노드 삭제를 수행하는 함수
     */
    public TreeNode<T> deleteRec (TreeNode<T> node, T value) {
        if (root == null) return null;

        int compareResult = value.compareTo(node.data);
        // 왼쪽 서브트리 탐색
        if (compareResult < 0) node.left = deleteRec(node.left, value);
        // 오른쪽 서브트리 탐색
        else if (compareResult > 0) node.right = deleteRec(node.right, value);
        // 삭제할 노드를 찾은 경우
        else {
            size--;
            // 왼쪽 자식이 없는 경우 현재 노드를 오른쪽 자식으로 교체
            if (node.left == null) return node.right;
            // 오른쪽 자식이 없는 경우 현재 노드를 왼쪽 자식으로 교체
            else if (node.right == null) return node.left;

            // 모든 자식이 존재하는 경우
            // 삭제할 노드를 오른쪽 서브트리의 최솟값으로 대체
            // (노드의 오른쪽 값은 항상 현재 노드보다 큰 값이므로 그 특성을 유지하기 위해 오른쪽 서브트리의 최솟값으로 대체)
            node.data = min(node.right);
            // 현재 노드의 삭제
            node.right = deleteRec(node.right, node.data);
        }
        return node;
    }

    /**
     * 트리의 최솟값 조회
     */
    public T min (TreeNode<T> node) {
        while (node.left != null) node = node.left;
        return node.data;
    }

    /**
     * 트리의 최댓값 조회
     */
    public T max (TreeNode<T> node) {
        while (node.right != null) node = node.right;
        return node.data;
    }

    /**
     * 전위 탐색
     * 루트 노드를 먼저 탐색하고 왼쪽 서브트리를 탐색한 후 오른쪽 서브트리를 탐색한다.
     * e.g.         5
     *          3      7
     *       2   4   6   8
     * 해당 트리를 전위 탐색으로 조회하면 5 -> 3 -> 2 -> 4 -> 7 -> 6 -> 8 순서로 조회된다.
     */
    public void preOrder () {
        System.out.print("전위 탐색: ");
        preOrderRec(root);
    }
    public void preOrderRec (TreeNode<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderRec(node.left);
            preOrderRec(node.right);
        }
    }

    /**
     * 중위 탐색
     * 왼쪽 서브트리를 먼저 탐색하고, 그 다음에 루트 노드를 탐색한 후 오른쪽 서브트리를 탐색한다.
     * ※ 이진 탐색 트리의 경우 중위 탐색하면 오름차순으로 조회된다.
     * e.g.         5
     *          3      7
     *       2   4   6   8
     * 해당 트리를 중위 탐색으로 조회하면 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 순서로 조회된다.
     */
    public void inOrder () {
        System.out.print("중위 탐색: ");
        inOrderRec(root);
    }
    public void inOrderRec (TreeNode<T> node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.print(node.data + " ");
            inOrderRec(node.right);
        }
    }

    /**
     * 후위 탐색
     * 왼쪽 서브트리를 먼저 탐색하고 오른쪽 서브트리를 탐색한 후 루트 노드를 탐색한다.
     * e.g.         5
     *          3      7
     *       2   4   6   8
     * 해당 트리를 후위 탐색으로 조회하면 2 -> 4 -> 3 -> 6 -> 8 -> 7 -> 5 순서로 조회된다.
     */
    public void postOrder () {
        System.out.print("후위 탐색: ");
        postOrderRec(root);
    }
    public void postOrderRec (TreeNode<T> node) {
        if (node != null) {
            postOrderRec(node.left);
            postOrderRec(node.right);
            System.out.print(node.data + " ");
        }
    }

    /**
     * 값이 트리에 포함되었는지 확인하는 함수
     */
    public boolean contains (T value) {
        return containsRec(root, value);
    }
    public boolean containsRec (TreeNode<T> node, T value) {
        if (node == null) return false;

        int compareResult = value.compareTo(node.data);
        if (compareResult < 0) return containsRec(node.left, value);
        else if (compareResult > 0) return containsRec(node.right, value);
        else return true;
    }

    /**
     * 트리의 높이를 반환하는 함수
     */
    public int getHeight (TreeNode<T> node) {
        if (node == null) return 0;
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 트리를 트리 모양대로 출력하는 함수
     */
    public void print () {
        if (root == null) {
            System.out.println("트리가 비어 있습니다.");
            return;
        }

        int height = getHeight(root);
        int[] space = getSpaces(height);

        for (int i = 1; i <= height; i++) {
            // 각 레벨의 공백 출력
            for (int j = 0; j < space[i - 1]; j++) System.out.print(" ");
            // 레벨별 노드 출력
            printRec(root, i, i - 2 < 0 ? 1 : space[i - 2]);
            System.out.println();
        }
    }

    /**
     * 레벨별 노드 출력 함수
     */
    public void printRec (TreeNode<T> node, int level, int space) {
        if (level == 1) {
            // 노드 출력
            System.out.print(node == null ? "-" : node.data);
            // 노드간의 공백 출력
            for (int i = 0; i < space; i++) System.out.print(" ");
        }
        else if (level > 1) {
            // node == null ? null : node.left => 노드가 비어있는 경우에도 "-" 을 출력하기 위함
            printRec(node == null ? null : node.left, level - 1, space);
            printRec(node == null ? null : node.right, level - 1, space);
        }
    }

    /**
     * 트리 모양대로 출력하기 위한 공백 계산 함수
     * 각 레벨의 왼쪽 공백은 n * 2 + 1 개수대로 출력한다. (n 은 이전 공백 수, 0레벨인 경우 n = 1)
     * 각 레벨의 형제 노드간의 공백은 부모 레벨의 공백 개수대로 출력한다.
     * e.g. 다음과 같은 트리가 있을 때
     *                A                 => 왼쪽 공백 개수: 15
     *        B               C         => 왼쪽 공백 개수: 7, 형제 노드간의 공백 개수: 15
     *    D       E       F       G     => 왼쪽 공백 개수: 3, 형제 노드간의 공백 개수: 7
     * [15, 7, 3, 1] 공백 배열을 반환한다.
     */
    public int[] getSpaces (int height) {
        int[] space = new int[height];
        int[] reverse = new int[height];

        // 공백 개수 계산 배열 space
        space[0] = 1;
        for (int i = 1; i < height; i++)
            space[i] = space[i - 1] * 2 + 1;

        // space 배열 역순 정렬
        for (int i = 0; i < height; i++)
            reverse[i] = space[height - i - 1];

        return reverse;
    }
}
