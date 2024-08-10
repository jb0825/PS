package com.ps.data_structure.linked_list;

class SinglyNode<T> {
    T data;
    SinglyNode<T> next;

    public SinglyNode (T data) {
        this.data = data;
        this.next = null;
    }
}

/**
 * 단일 연결 리스트 (Singly Linked List)
 * 구성: 각 노드는 데이터와 다음 노드를 가리키는 포인터를 가지고 있다.
 * 특징: 노드를 한 방향으로만 탐색할 수 있으며, 특정 노드를 찾기 위해서는 노드의 처음부터 끝까지 탐색해야 한다.
 * 사용: 간단한 리스트 구조가 필요할 때 사용
 */
public class SinglyLinkedList<T> {
    SinglyNode<T> head; // 리스트의 첫번째 노드
    SinglyNode<T> tail; // 리스트의 마지막 노드
    int size;           // 리스트 크기

    public SinglyLinkedList () {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * 리스트의 맨 앞에 데이터 추가
     * @param data 추가할 데이터
     */
    public void addFirst (T data) {
        SinglyNode<T> newNode = new SinglyNode<>(data);

        if (head == null) tail = newNode;
        else newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * 리스트의 맨 뒤에 데이터 추가
     * @param data 추가할 데이터
     */
    public void addLast (T data) {
        SinglyNode<T> newNode = new SinglyNode<>(data);

        if (head == null) head = newNode;
        else tail.next = newNode;
        tail = newNode;
        size++;
    }

    /**
     * 리스트의 특정 위치에 데이터 추가
     * @param index 데이터를 삽입할 위치
     * @param data 추가할 데이터
     */
    public void add (int index, T data) {
        if (index == 0) addFirst(data);
        else if (index >= size) addLast(data);
        else {
            SinglyNode<T> newNode = new SinglyNode<>(data);
            SinglyNode<T> current = search(index - 1); // index 위치의 노드 찾기
            newNode.next = current.next;    // 새로운 노드의 다음 노드를 index 노드의 다음으로 설정
            current.next = newNode;         // index 노드의 다음 노드를 새로운 노드로 설정
        }
        size++;
    }

    /**
     * 리스트의 첫번째 요소 삭제
     */
    public void removeFirst () {
        if (head == null) throw new NullPointerException("리스트가 비어 있습니다.");
        head = head.next;
        size--;
    }

    /**
     * 리스트의 마지막 요소 삭제
     */
    public void removeLast () {
        if (head == null) throw new NullPointerException("리스트가 비어 있습니다.");
        // 데이터가 1개인 경우 리스트 비우기
        if (size == 1) {
            clear();
            return;
        }

        SinglyNode<T> current = head;
        while (current.next != tail) current = current.next;    // 꼬리 노드의 이전 노드 찾기
        current.next = null;    // 꼬리 노드의 이전 노드를 꼬리 노드로 설정
        tail = current;
        size--;
    }

    /**
     * 리스트의 특정 위치의 요소 삭제
     * @param index 삭제할 노드의 위치
     */
    public void remove (int index) {
        if (head == null) throw new NullPointerException("리스트가 비어 있습니다.");
        // 데이터가 1개인 경우 리스트 비우기
        if (size == 1) {
            clear();
            return;
        }

        if (index == 0) removeFirst();
        else {
            SinglyNode<T> current = search(index - 1);
            // 위치의 노드를 삭제
            if (current.next != null) {
                if (current.next.next == null) tail = current; // 삭제한 노드의 다음 노드가 없다면 꼬리 노드 업데이트
                current.next = current.next.next;
            }
        }
    }

    /**
     * 리스트의 특정 데이터 삭제
     * @param value 삭제할 노드의 데이터
     */
    public void removeByValue (T value) {
        if (head == null) return;

        SinglyNode<T> current = head;
        // 데이터가 일치하는 노드 찾고 삭제
        while (current.next != null) {
            if (current.next.data == value) {
                current.next = current.next.next;
                if (current.next == null) tail = current; // 삭제한 노드의 다음 노드가 없다면 꼬리 노드 업데이트
            }
            current = current.next;
        }
        throw new NullPointerException("존재하지 않는 데이터입니다.");
    }

    public SinglyNode<T> search (int index) {
        SinglyNode<T> node = head;
        for (int i = 0; i < index; i++) node = node.next;
        return node;
    }

    public void clear () {
        head = null;
        tail = null;
        size = 0;
    }

    public void print () {
        SinglyNode<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.print("null");
    }
}
