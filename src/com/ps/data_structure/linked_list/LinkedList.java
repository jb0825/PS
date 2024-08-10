package com.ps.data_structure.linked_list;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * LinkedList (연결 리스트) 는 데이터를 저장하는 자료구조 중 하나로, 각 데이터 요소가 노드로 구성되어 있으며
 * 노드는 데이터와 다음 요소에 대한 참조를 가지고 있다.
 * 배열과 달리 링크드 리스트는 동적으로 크기를 조절할 수 있는 장점이 있다.
 *
 * 링크드 리스트의 기본 구조:
 *      1. 노드 (Node): 데이터와 다음 노드에 대한 참조를 포함하는 구조
 *      2. 헤드 (Head): 리스트의 첫번째 노드를 가리키는 포인터
 */
public class LinkedList {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        // 단일 연결 리스트
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();

        while (true) {
            int menu, x;

            System.out.print(
                "———————————————————————————*** Linked List ***————————————————————————\n" +
                "| (1)Add to front (2)Add to last (3)Add                              |\n" +
                "| (4)Remove to front (5)Remove to last (6)Remove (7)Remove by value  |\n" +
                "| (8)Clear (0)Exit                                                   |\n" +
                "‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n" +
                "insert: "
            );

            try {
                menu = sc.nextInt();
                if (menu == 0) break;

                switch (menu) {
                    case 1:
                        System.out.print("추가할 데이터를 입력하세요: ");
                        x = sc.nextInt();
                        linkedList.addFirst(x);
                        break;
                    case 2:
                        System.out.print("추가할 데이터를 입력하세요: ");
                        x = sc.nextInt();
                        linkedList.addLast(x);
                        break;
                    case 3:
                        System.out.print("추가할 위치를 입력하세요: ");
                        int index = sc.nextInt();
                        System.out.print("추가할 데이터를 입력하세요: ");
                        x = sc.nextInt();
                        linkedList.add(index, x);
                        break;
                    case 4:
                        linkedList.removeFirst();
                        break;
                    case 5:
                        linkedList.removeLast();
                        break;
                    case 6:
                        System.out.print("삭제할 노드의 위치를 입력하세요: ");
                        x = sc.nextInt();
                        if (x > linkedList.size) throw new NullPointerException("삭제할 노드의 위치를 잘못 입력했습니다.");
                        linkedList.remove(x);
                        break;
                    case 7:
                        System.out.print("삭제할 노드의 데이터를 입력하세요: ");
                        x = sc.nextInt();
                        linkedList.removeByValue(x);
                        break;
                    case 8:
                        linkedList.clear();
                        System.out.println("리스트를 청소했습니다.");
                        break;
                }
                linkedList.print();
                System.out.println("\n");

            } catch (InputMismatchException e) {
                System.out.println("Error: 잘못된 값을 입력했습니다. 다시 입력해 주세요.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("LinkedList 를 종료합니다 ...");
    }
}
