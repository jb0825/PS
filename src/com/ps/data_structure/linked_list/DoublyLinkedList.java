package com.ps.data_structure.linked_list;

class DoublyNode<T> {
    T data;
    DoublyNode<T> next;
    DoublyNode<T> prev;

    public DoublyNode (T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    public DoublyNode (T data, DoublyNode<T> prev, DoublyNode<T> next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}
/**
 * 이중 연결 리스트 (Doubly Linked List)
 * 구성: 각 노드는 데이터, 다음 노드 포인터, 이전 노드 포인터를 가지고 있다.
 * 특징: 양방향 탐색이 가능하며, 삽입과 삭제가 용이하다. 특정 노드를 찾는 성능도 단일 연결 리스트보다 개선되었다.
 * 사용: 양방향 탐색이 필요한 경우 (e.g. 브라우저의 뒤로가기/앞으로 가기 기능)
 */
public class DoublyLinkedList<T> {
    DoublyNode<T> head; // 리스트의 첫번째 노드
    DoublyNode<T> tail; // 리스트의 마지막 노드
    int size;           // 리스트 크기

    public DoublyLinkedList () {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * 리스트의 맨 앞에 데이터 추가
     * @param data 추가할 데이터
     */
    public void addFirst (T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);

        if (head == null) tail = newNode;
        else {
            head.prev = newNode;
            newNode.next = head;
        }
        head = newNode;
        size++;
    }

    /**
     * 리스트의 맨 뒤에 데이터 추가
     * @param data 추가할 데이터
     */
    public void addLast (T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);

        if (head == null) head = newNode;
        else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        size++;
    }

    /**
     * 리스트의 특정 위치에 데이터 추가
     * @param index 데이터를 삽입할 위치
     * @param data 추가할 데이터
     */
    public void add (int index, T data) {
        if (index <= 0) {
            addFirst(data);
            return;
        }
        if (index >= size) {
            addLast(data);
            return;
        }

        DoublyNode<T> current = search(index);
        if (current != null) {
            DoublyNode<T> newNode = new DoublyNode<>(data, current.prev, current);
            if (current.prev != null) current.prev.next = newNode;  // index 이전 노드의 다음으로 새로운 노드 연결
            current.prev = newNode; // index 이전 노드로 새로운 노드 연결
            size++;
        }
    }

    /**
     * 리스트의 첫번째 요소 삭제
     */
    public void removeFirst () {
        if (head == null) throw new NullPointerException("리스트가 비어 있습니다.");
        head = head.next;
        head.prev = null;
        size--;
    }

    /**
     * 리스트의 마지막 요소 삭제
     */
    public void removeLast () {
        if (head == null) throw new NullPointerException("리스트가 비어 있습니다.");
        tail = tail.prev;
        tail.next = null;
        size--;
    }

    /**
     * 리스트의 특정 위치의 요소 삭제
     * @param index 삭제할 노드의 위치
     */
    public void remove (int index) {
        if (head == null) throw new NullPointerException("리스트가 비어 있습니다.");
        if (index <= 0) {
            removeFirst();
            return;
        }
        if (index >= size) {
            removeLast();
            return;
        }

        DoublyNode<T> current = search(index);
        if (current != null) {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
        }
    }

    /**
     * 리스트의 특정 데이터 삭제
     * @param value 삭제할 노드의 데이터
     */
    public void removeByValue (T value) {
        if (head == null) throw new NullPointerException("리스트가 비어 있습니다.");

        DoublyNode<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.data == value) break;
            current = current.next;
        }
        if (current == null) throw new NullPointerException("존재하지 않는 데이터입니다.");
        if (current == head) {
            removeFirst();
            return;
        }
        if (current == tail) {
            removeLast();
            return;
        }

        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
    }

    /**
     * 리스트에서 인덱스로 검색
     * @param index 검색할 노드의 인덱스
     */
    public DoublyNode<T> search (int index) {
        DoublyNode<T> node;
        if ((size / 2) > index) {
            // 검색할 인덱스가 시작에 가까우면 순차 탐색
            node = head;
            for (int i = 0; i < index; i++) node = node.next;
        } else {
            // 검색할 인덱스가 끝에 가까우면 역순 탐색
            node = tail;
            for (int i = size; i > index; i--) node = node.prev;
        }

        return node;
    }

    /**
     * 리스트 비우기
     */
    public void clear () {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * 현재 리스트 상태 출력
     */
    public void print () {
        DoublyNode<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.print("null");
    }
}
