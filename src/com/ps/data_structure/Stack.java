package com.ps.data_structure;

import java.util.Scanner;

public class Stack {
    private int max;        // 스택 최대 사이즈
    private int top;        // 스택 꼭대기 인덱스
    private int[] stack;    // 스택을 저장할 배열

    /**
     * 스택 초기화
     * @param size 스택 max size
     */
    public Stack (int size) {
        max = size;
        stack = new int[size];
        top = -1;
    }

    /**
     * 스택에 데이터 추가
     * @param x 추가할 데이터
     */
    public void push (int x) {
        if (isFull()) throw new StackOverflowError("스택이 가득 찼습니다.");
        stack[++top] = x;
    }

    /**
     * 스택의 최상단 데이터 제거
     */
    public int pop () {
        if (isEmpty()) throw new RuntimeException("스택이 비어 있습니다.");
        return stack[top--];
    }

    /**
     * 스택의 최상단 데이터 반환
     */
    public int peek () {
        if (isEmpty()) throw new RuntimeException("스택이 비어 있습니다.");
        return stack[top];
    }

    /**
     * 스택이 비어있는지 확인
     */
    public boolean isEmpty () { return top == -1; }

    /**
     * 스택이 가득 찼는지 확인
     */
    public boolean isFull () { return top == max - 1; }

    /**
     * 스택 사이즈 반환
     */
    public int size () { return top + 1; }

    /**
     * 스택 용량 반환
     */
    public int capacity () { return max; }

    /**
     * 스택 비우기
     */
    public void clear () { top = -1; }

    /**
     * 현재 스택 상태 출력
     */
    public void print () {
        System.out.print("Stack : [ ");
        if (!isEmpty())
            for (int i = 0; i <= top; i++)
                System.out.print(stack[i] + " ");
        System.out.println("]");
    }

    /**
     * 스택에서 데이터 검색
     * @param x 검색할 데이터
     */
    public int search (int x) {
        if (isEmpty()) throw new RuntimeException("스택이 비어 있습니다.");
        for (int i = 0; i <= top; i++)
            if (stack[i] == x) return i;
        return -1;
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack stack = new Stack(10);

        while (true) {
            int menu = 0, x = 0;

            System.out.println("현재 데이터 수 : " + stack.size() + "/" + stack.capacity());
            System.out.print("(1)Push (2)Pop (3)Peek (4)Print (5)Search (6)Clear (0)Exit : ");

            menu = sc.nextInt();
            if (menu == 0) break;

            try {
                switch (menu) {
                    case 1:
                        System.out.print("푸시할 데이터를 입력하세요 : ");
                        x = sc.nextInt();
                        stack.push(x);
                        break;
                    case 2:
                        System.out.println("Pop 데이터 : " + stack.pop());
                        break;
                    case 3:
                        System.out.println("Peek 데이터 : " + stack.peek());
                        break;
                    case 4:
                        stack.print();
                        break;
                    case 5:
                        System.out.print("검색할 데이터를 입력하세요 : ");
                        x = sc.nextInt();
                        int result = stack.search(x);
                        if (result == -1) System.out.println("스택에 존재하지 않는 데이터입니다.");
                        else System.out.println("데이터는 스택의 " + (result + 1) + "번째에 있습니다.");
                        break;
                    case 6:
                        stack.clear();
                        System.out.println("스택이 청소되었습니다.");
                        break;
                }
            } catch (StackOverflowError | RuntimeException e) {
                System.out.println("Error : " + e.getMessage());
            }
        }

        System.out.println("Stack 을 종료합니다...");
    }
}
