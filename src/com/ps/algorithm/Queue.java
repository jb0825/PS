package com.ps.algorithm;

import java.util.Scanner;

public class Queue {
    private int max;    // 큐의 최대 사이즈
    private int num;    // 큐의 현재 요소 수
    private int front;  // 큐의 맨 앞 요소의 인덱스
    private int rear;   // 큐의 맨 뒤 요소의 인덱스
    private int[] que;  // 큐를 저장할 배열

    /**
     * 큐 초기화
     * @param size 큐 max size
     */
    public Queue (int size) {
        max = size;
        num = 0;
        front = 0;
        rear = -1;
        que = new int[size];
    }

    /**
     * 큐에 데이터 추가
     * @param x 추가할 데이터
     */
    public void enque (int x) {
        if (isFull()) throw new RuntimeException("큐가 가득 찼습니다");
        if (rear == max - 1) rear = -1;
        que[++rear] = x;
        num++;
    }

    /**
     * 큐에서 맨 앞 데이터 제거
     */
    public int deque () {
        if (isEmpty()) throw new RuntimeException("큐가 비어 있습니다.");
        front++;
        num--;
        if (front == max) front = 0;
        return que[front];
    }

    /**
     * 큐의 맨 앞 요소 반환
     */
    public int peek () {
        if (isEmpty()) throw new RuntimeException("큐가 비어 있습니다.");
        return que[front];
    }

    /**
     * 큐가 비어있는지 확인
     */
    public boolean isEmpty () { return num == 0; }

    /**
     * 큐가 가득 찼는지 확인
     */
    public boolean isFull () { return num == max; }

    /**
     * 큐 사이즈 반환
     */
    public int size () { return num; }

    /**
     * 큐 용량 반환
     */
    public int capacity () { return max; }

    /**
     * 큐 비우기
     */
    public void clear () {
        num = 0;
        front = 0;
        rear = -1;
    }

    /**
     * 현재 큐 상태 출력
     */
    public void print () {
        System.out.print("Queue: [ ");
        if (!isEmpty())
            for (int i = front; i <= rear; i++)
                System.out.print(que[i] + " ");
        System.out.println("]");
    }

    /**
     * 큐에서 데이터 검색
     * @param x 검색할 데이터
     */
    public int search (int x) {
        if (isEmpty()) throw new RuntimeException("큐가 비어 있습니다.");
        for (int i = front; i <= rear; i++)
            if (que[i] == x) return i;
        return -1;
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue que = new Queue(10);

        while (true) {
            int menu = 0, x = 0;

            System.out.println("현재 데이터 수 : " + que.size() + "/" + que.capacity());
            System.out.print("(1)Enque (2)Deque (3)Peek (4)Print (5)Search (6)Clear (0)Exit : ");

            menu = sc.nextInt();
            if (menu == 0) break;

            try {
                switch (menu) {
                    case 1:
                        System.out.print("인큐할 데이터를 입력하세요 : ");
                        x = sc.nextInt();
                        que.enque(x);
                        break;
                    case 2:
                        System.out.println("Deque 데이터 : " + que.deque());
                        break;
                    case 3:
                        System.out.println("Peek 데이터 : " + que.peek());
                        break;
                    case 4:
                        que.print();
                        break;
                    case 5:
                        System.out.print("검색할 데이터를 입력하세요 : ");
                        x = sc.nextInt();
                        int result = que.search(x);
                        if (result == -1) System.out.println("큐에 존재하지 않는 데이터입니다.");
                        else System.out.println("데이터는 큐의 " + (result + 1) + "번째에 있습니다.");
                        break;
                    case 6:
                        que.clear();
                        System.out.println("큐가 청소되었습니다.");
                        break;
                }
            } catch (RuntimeException e) {
                System.out.println("Error : " + e.getMessage());
            }
        }

        System.out.println("Queue 를 종료합니다...");
    }
}
