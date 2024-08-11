package com.ps.data_structure.linked_list;

/**
 * 원형 이중 연결 리스트 (Circular Doubly Linked List)
 * 구성: 각 노드는 데이터, 다음 노드 포인터, 이전 노드 포인터를 가지고 있으며, 마지막 노드가 첫 번째 노드를, 첫 번째 노드가 마지막 노드를 가리킨다.
 * 특징: 양방향 탐색이 가능하며, 원형 구조로 인해 연속적인 탐색에 용이하다.
 * 사용: 복잡한 데이터 구조가 필요한 경우 (e.g. 음악 재생 목록, 이미지 슬라이드 쇼)
 */
public class CircularDoublyLinkedList<T> extends DoublyLinkedList<T> {
    public CircularDoublyLinkedList () {
        head = null;
        size = 0;
    }

    /**
     * 리스트의 맨 앞에 데이터 추가
     */
    public void addFirst (T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);

        if (head == null) {
            newNode.prev = newNode;
            newNode.next = newNode;
        } else {
            DoublyNode<T> tail = head.prev;

            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
            tail.next = newNode;
        }
        head = newNode;
        size++;
    }

    /**
     * 리스트의 맨 뒤에 데이터 추가
     */
    public void addLast (T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);

        if (head == null) {
            newNode.prev = newNode;
            newNode.next = newNode;
        } else {
            DoublyNode<T> tail = head.prev;

            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
            tail.next = newNode;
        }
        size++;
    }

    /**
     * 리스트의 첫번째 요소 삭제
     */
    public void removeFirst () {
        if (head == null) throw new NullPointerException("리스트가 비어 있습니다.");
        head.next.prev = head.prev;
        head = head.next;
        size--;
    }

    /**
     * 리스트의 마지막 요소 삭제
     */
    public void removeLast () {
        if (head == null) throw new NullPointerException("리스트가 비어 있습니다.");
        DoublyNode<T> tail = head.prev;
        tail.prev.next = head;
        head.prev = tail.prev;
        size--;
    }

    /**
     * 리스트에서 인덱스로 검색
     */
    public DoublyNode<T> search (int index) {
        DoublyNode<T> node = head;
        for (int i = 0; i < index; i++) node = node.next;
        return node;
    }

    /**
     * 리스트 비우기
     */
    public void clear () {
        head = null;
        size = 0;
    }

    /**
     * 현재 리스트 상태 출력
     */
    public void print () {
        DoublyNode<T> current = head;
        if (size > 1)
            for (int i = 0; i < size; i++) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }
        System.out.print(head == null ? "null" : head.data);
    }
}
