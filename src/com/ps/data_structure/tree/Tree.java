package com.ps.data_structure.tree;

import java.util.InputMismatchException;
import java.util.Scanner;

class TreeNode<T> {
    T data;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode (T data) {
        this.data = data;
        this.left = this.right = null;
    }
}

/**
 * Tree (트리) 는 자계층적인 구조를 표현하는 데 사용하는 자료구조이다.
 *
 * 트리의 기본 구조:
 *      1. 루트 노드 (Root node): 트리의 최상위 노드로, 트리에서 유일한 시작점
 *      2. 노드 (Node): 트리의 각 데이터 요소를 나타내는 기본 단위. 각 노드는 0개 이상의 자식을 가질 수 있음
 *      3. 간선 (Edge): 노드 간의 연결을 나타냄
 *      4. 리프 노드 (Leaf node): 자식이 없는 노드를 의미함
 *      5. 높이 (Height): 트리의 루트 노드에서 가장 깊은 리프 노드까지의 간선 수
 *      6. 깊이 (Depth): 특정 노드가 루트 노드에서 얼마나 떨어져 있는지를 나타내는 값
 */
public class Tree {
    public static void main (String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            int menu, x;

            System.out.print(
                "—————————————————*** Binary Search Tree ***————————————————\n" +
                "| (1)Insert (2)Delete (3)Preorder (4)Inorder (5)Postorder |\n" +
                "| (0)Exit                                                 |\n" +
                "‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n" +
                "insert: "
            );

            try {
                menu = sc.nextInt();
                if (menu == 0) break;

                switch (menu) {
                    case 1:
                        System.out.print("추가할 데이터를 입력하세요: ");
                        x = sc.nextInt();
                        tree.insert(x);
                        break;
                    case 2:
                        System.out.print("삭제할 데이터를 입력하세요: ");
                        x = sc.nextInt();
                        tree.delete(x);
                        break;
                    case 3:
                        tree.preOrder();
                        break;
                    case 4:
                        tree.inOrder();
                        break;
                    case 5:
                        tree.postOrder();
                        break;
                }

                System.out.println();
                tree.print();
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("Error: 잘못된 값을 입력했습니다.\n");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + "\n");
            }
        }

        System.out.println("Binary Search Tree 를 종료합니다 ...");
    }
}
