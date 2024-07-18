package com.ps.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sort {

    public static void main (String[] args) {
        Sort sort = new Sort();
        int[] arr = {8, 1, 4, 2, 7, 6, 3, 5};

        System.out.println("정렬 전: " + Arrays.toString(arr));

        /*
        // Bubble sort (가장 큰 값부터 정렬)
        System.out.println("\n *** Bubble Sort *** ");
        System.out.println("정렬 완료: " + Arrays.toString(sort.bubbleSort(arr)));

        // Bubble sort (가장 작은 값부터 정렬)
        System.out.println("\n *** Bubble Sort 2 ***");
        System.out.println("정렬 완료: " + Arrays.toString(sort.bubbleSort2(arr)));

        // Straight selection sort
        System.out.println("\n *** Straight Selection Sort ***");
        System.out.println("정렬 완료: " + Arrays.toString(sort.straightSelectionSort(arr)));

        // Straight insertion sort
        System.out.println("\n *** Straight Insertion Sort ***");
        System.out.println("정렬 완료 : " + Arrays.toString(sort.straightInsertionSort(arr)));

        // Shell sort
        System.out.println("\n *** Shell Sort ***");
        System.out.println("정렬 완료 : " + Arrays.toString(sort.shellSort(arr)));

        // Shell sort (Knuth 간격 수열 사용)
        System.out.println("\n *** Shell Sort (using Knuth sequences) ***");
        System.out.println("정렬 완료 : " + Arrays.toString(sort.shellSortKnuth(arr)));

        // Shell sort (Pratt 간격 수열 사용)
        System.out.println("\n *** Shell Sort (using Pratt sequences) ***");
        System.out.println(Arrays.toString(sort.shellSortPratt(arr)));
         */

        // Quick sort
        System.out.println("\n *** Quick Sort ***");
        sort.quickSort(arr);

        // Merge sort
        System.out.println("\n *** Merge Sort ***");
        sort.mergeSort(arr);

        // Counting sort
        System.out.println("\n *** Counting Sort ***");
        System.out.println(Arrays.toString(sort.countingSort(arr)));
    }

    public void printArr (int[] arr, int pointIdx) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            boolean point = pointIdx == i;
            System.out.print((point ? "(" : "") + arr[i] + (point ? ")" : ""));
            if (i + 1 < arr.length) System.out.print(", ");
        }
        System.out.println("]");
    }

    public void printArr (int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i + 1 < arr.length) System.out.print(", ");
        }
        System.out.println("]");
    }

    /**
     * 버블 정렬 (Bubble sort)
     * 배열에서 이웃한 두 요소의 대소관계를 비교하여 교환을 반복하는 정렬
     * 시간 복잡도 : O(n²)
     */
    public int[] bubbleSort (int[] unsortedArr) {
        // 정렬 전 배열 (unsortedArr) 을 바꾸지 않기 위해 Arrays.copyOf 사용
        int[] arr = Arrays.copyOf(unsortedArr, unsortedArr.length);
        int n = arr.length - 1;
        boolean swapped;

        for (int i = 0; i < n; i++) {
            swapped = false;
            // 한 사이클이 끝나면 맨 끝 요소가 정렬되므로 n - i 번 반복
            // (정렬되지 않은 부분만 반복)
            for (int j = 0; j < n - i; j++) {
                // 요소가 뒷 요소보다 크다면 두 요소를 교환
                if (arr[j] > arr[j + 1]) {
                    printArr(arr, j);
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // 만약 요소가 교환되지 않았다면, 배열이 이미 정렬된 상태이므로 return
            if (!swapped) break;
        }
        return arr;
    }

    /**
     * 가장 작은 값부터 정렬하는 버블 정렬
     */
    public int[] bubbleSort2 (int[] unsortedArr) {
        int[] arr = Arrays.copyOf(unsortedArr, unsortedArr.length);
        int n = arr.length - 1;
        boolean swapped;

        for (int i = 0; i < n; i++) {
            swapped = false;
            for (int j = n; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    printArr(arr, j);
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return arr;
    }

    /**
     * 단순 선택 정렬 (Straight selection sort)
     * 가장 작은 요소부터 선택해 정렬되지 않은 부분의 가장 앞쪽으로 옮겨서 순서대로 정렬하는 알고리즘
     * 시간 복잡도 : O(n²)
     */
    public int[] straightSelectionSort (int[] unsortedArr) {
        int[] arr = Arrays.copyOf(unsortedArr, unsortedArr.length);

        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[min]) min = j;
            printArr(arr, min);
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        return arr;
    }

    /**
     * 단순 삽입 정렬 (Straight insertion sort)
     * 정렬되지 않은 부분의 첫 번째 요소를 정렬된 부분의 '알맞은 위치' 로 옮기는 작업을 반복하며 정렬
     * 시간 복잡도 : O(n²)
     */
    public int[] straightInsertionSort (int[] unsortedArr) {
        int[] arr = Arrays.copyOf(unsortedArr, unsortedArr.length);
        int i, j;

        for (i = 0; i < arr.length; i++) {
            int temp = arr[i];
            // 정렬되지 않은 부분 (i ~ arr.length) 의 첫 번째 요소 (arr[i]) 보다 작거나 같은 값이 나올 때까지
            // 정렬된 부분 (0 ~ i) 의 요소를 한 칸씩 앞으로 배치
            for (j = i; j > 0 && arr[j - 1] > temp; j--)
                arr[j] = arr[j - 1];
            printArr(arr, j);
            // 알맞은 위치에 정렬되지 않은 부분의 첫번째 요소 배치
            arr[j] = temp;
        }
        return arr;
    }

    /**
     * 셸 정렬 (Shell sort)
     * 정렬할 배열의 요소를 그룹으로 나눠 각 그룹별로 단순 삽입 정렬을 수행하고 그룹을 다시 나누면서 정렬을 반복
     * (h개 요소에 대해 h-정렬을 반복)
     * e.g. 배열의 크기가 8일 때, 4-정렬, 2-정렬, 1-정렬을 반복하며 정렬함
     * 시간 복잡도 : O(n²)
     */
    public int[] shellSort (int[] unsortedArr) {
        int n = unsortedArr.length;

        // h-정렬을 수행하기 위한 간격 수열 생성
        // e.g. n = 8, [4, 2, 1]
        int h = n / 2;
        ArrayList<Integer> sequence = new ArrayList<>();
        while (h > 0) {
            sequence.add(h);
            h /= 2;
        }

        // 셸 정렬 수행
        return doShellSort(unsortedArr, sequence.stream().mapToInt(i -> i).toArray());
    }

    /**
     * 셸 정렬 수행하는 함수
     */
    public int[] doShellSort (int[] unsortedArr, int[] sequence) {
        int n = unsortedArr.length;
        int[] arr = Arrays.copyOf(unsortedArr, n);

        // 간격 수열에 따라 반복
        for (int h : sequence) {
            System.out.println(h + "-정렬");
            // 각 부분 배열에서 삽입 정렬 수행
            for (int i = h; i < n; i++) {
                int temp = arr[i];
                int j;
                System.out.println("삽입할 요소 (arr[" + i + "]) :" + temp);
                for (j = i; j >= h && arr[j - h] > temp; j -= h) {
                    // arr[i] 보다 큰 요소를 오른쪽으로 이동시킴
                    arr[j] = arr[j - h];
                    printArr(arr, j);
                }
                // arr[i] 를 부분 배열의 적절한 위치에 삽입함
                arr[j] = temp;
                printArr(arr, j);
            }
        }

        return arr;
    }


    /**
     * Knuth 간격 수열을 사용하는 셸 정렬
     *
     * 기존 셸 정렬의 경우, n 이 배열의 크기이고 h 가 간격이라고 한다면 [h = n / 2^k] 와 같은 수열을 이용하여 정렬한다. (k는 1부터 시작하는 정수)
     * 이 때 알고리즘의 평균 시간 복잡도는 O(n²) 이다.
     * 셸 정렬에서 어떤 간격 수열을 사용하는지에 따라 알고리즘의 성능을 향상시킬 수 있다.
     *
     * Knuth 간격 수열은 [h = 3k + 1] 와 같은 규칙을 따른다. (k는 0부터 시작하는 정수) h값이 서로 배수가 되지 않는 수열이다.
     * (h = ..., 121, 40, 13, 4, 1)
     *
     * 시간 복잡도 : O(n^3/2)
     */
    public int[] shellSortKnuth (int[] unsortedArr) {
        ArrayList<Integer> sequence = new ArrayList<>();
        int h = 1;

        // Knuth 간격 수열 생성 [h = 3k + 1]
        while (h < unsortedArr.length) {
            sequence.add(h);
            h = 3 * h + 1;
        }
        Collections.reverse(sequence);

        // 셸 정렬 수행
        return doShellSort(unsortedArr, sequence.stream().mapToInt(i -> i).toArray());
    }

    /**
     * Pratt 간격 수열을 사용하는 셸 정렬
     *
     * 셸 정렬의 간격 수열 중 평균 시간 복잡도가 가장 적은 간격 수열이다.
     * Pratt 간격 수열은 [h = 2^i * 3^j] 와 같은 규칙을 따른다. (i, j 는 0 이상의 정수)
     * 즉, Pratt 간격 수열은 2와 3의 거듭제곱의 곱으로 이루어진다.
     *
     * 시간 복잡도 : O(n \ log² n)
     */
    public int[] shellSortPratt (int[] unsortedArr) {
        ArrayList<Integer> sequence = new ArrayList<>();
        int n = unsortedArr.length;
        int h = 1;

        // Pratt 간격 수열 생성 [h = 2^i * 3^j]
        while (h <= n) {
            int q = h;
            while (q <= n) {
                sequence.add(q);
                q *= 3;
            }
            h *= 2;
        }
        sequence.sort(Collections.reverseOrder());

        // 셸 정렬 수행
        return doShellSort(unsortedArr, sequence.stream().mapToInt(Integer::intValue).toArray());
    }

    /**
     * 퀵 정렬 (Quick sort)
     * 분할 정복 (Divide and Conquer) 알고리즘의 일종으로, 배열을 피벗을 기준으로 두 부분으로 나누고 각각을 재귀적으로 정렬하는 알고리즘
     * 시간 복잡도 : O(n log n)
     */
    public void quickSort (int[] arr, int left, int right) {
        int pl = left;              // 왼쪽 인덱스
        int pr = right;             // 오른쪽 인덱스
        int x = arr[(pl + pr) / 2]; // 피벗

        // 배열을 피벗을 기준으로 두 그룹으로 나누기
        // 0 ~ pl - 1 : 피벗보다 값이 작은 그룹
        // pl + 1 ~ arr.length - 1 : 피벗보다 값이 큰 그룹
        do {
            // arr[pl] >= x, arr[pr] <= x 가 성립하는 pl, pr 인덱스 찾기
            while (arr[pl] < x) pl++;
            while (arr[pr] > x) pr--;
            // pl, pr 을 찾았다면 두 요소의 값을 교환
            if (pl <= pr) {
                int temp = arr[pl];
                arr[pl] = arr[pr];
                arr[pr] = temp;
                pl++;
                pr--;
            }
        } while (pl <= pr);

        printArr(arr);

        // pr 이 left 보다 오른쪽에 있다면 왼쪽 그룹을 다시 나누기
        if (left < pr) quickSort(arr, left, pr);
        // pl 이 right 보다 왼쪽에 있으면 오른쪽 그룹을 다시 나누기
        if (pl < right) quickSort(arr, pl, right);
    }

    public int[] quickSort (int[] unsortedArr) {
        int[] arr = Arrays.copyOf(unsortedArr, unsortedArr.length);
        quickSort(arr, 0, arr.length - 1);

        return arr;
    }

    /**
     * 병합 정렬 (Merge sort)
     * 분할 정복 (Divide and Conquer) 알고리즘의 일종으로, 배열을 작은 부분으로 나누고 각 부분을 정렬한 후 병합하여 전체를 정렬하는 알고리즘
     * 시간 복잡도 : O(n log n)
     */
    public void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            // 배열 앞부분을 분할
            mergeSort(arr, temp, left, center);
            // 배열 뒷부분을 분할
            mergeSort(arr, temp, center + 1, right);

            // 정렬된 두 개의 부분 배열을 병합
            int l = left;       // 왼쪽 부분 배열의 인덱스
            int r = center + 1; // 오른쪽 부분 배열의 인덱스
            int idx = left;     // temp 배열 인덱스

            // 왼쪽 부분 배열과 오른쪽 부분 배열의 현재 인덱스가 각각 그들의 끝을 넘지 않을 동안 루프 실행
            while (l <= center && r <= right) {
                // 왼쪽 부분 배열과 오른쪽 부분 배열 중 더 작은 값을 temp 배열에 추가
                if (arr[l] <= arr[r])
                    temp[idx++] = arr[l++];
                else
                    temp[idx++] = arr[r++];
            }

            // 병합을 완료하고 나면 왼쪽 혹은 오른쪽 배열 중 하나가 temp 에 합쳐지지 않고 남아있게 됨.
            // 남아있는 쪽의 배열을 temp 에 병합
            // 왼쪽 배열의 요소를 temp 배열에 병합
            if (l <= center)
                System.arraycopy(arr, l, temp, idx, center - l + 1);
            // 오른쪽 배열의 요소를 temp 배열에 병합
            if (r <= right)
                System.arraycopy(arr, r, temp, idx, right - r + 1);
            printArr(temp);

            // temp 배열에서 원래 배열 arr 로 복사
            System.arraycopy(temp, left, arr, left, right - left + 1);

        }
    }

    public int[] mergeSort (int[] unsortedArr) {
        int[] arr = Arrays.copyOf(unsortedArr, unsortedArr.length);
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);

        return arr;
    }

    /**
     * 도수 정렬 (Counting Sort)
     * 주어진 배열의 요소들의 빈도를 세어서 정렬하는 알고리즘. 특정 범위 내의 정수로 구성된 배열에 대해 매우 효율적임.
     *
     * 도수 정렬의 순서 :
     *  1. 배열을 도수분포표로 구현
     *  2. 도수분포표로 변환된 배열을 누적 도수분포표로 구현
     *  3. 배열을 순회하며 결과 배열에 값을 저장
     *  4. 결과 배열의 값을 기존 배열에 복사
     *
     * 시간 복잡도 : O(n + k), n = 배열의 크기, k = 배열 요소의 최댓값
     */
   public int[] countingSort (int[] unsortedArr) {
        int[] arr = Arrays.copyOf(unsortedArr, unsortedArr.length);

        // 배열의 최댓값 찾기
       int max = arr[0];
       for (int n : arr)
           if (n > max) max = n;

       int[] freq = new int[max + 1];      // 누적 도수를 담을 배열
       int[] result = new int[arr.length]; // 정렬된 결과를 저장할 배열
       int i;

       // 배열 arr 을 도수분포표로 만들기
       for (int n : arr) freq[n] ++;
       System.out.print("도수분포표 : ");
       printArr(freq);

       // 도수분포표를 누적 도수분포표로 만들기
       for (i = 1; i < freq.length; i++) freq[i] += freq[i - 1];
       System.out.print("누적 도수분포표 : ");
       printArr(freq);

       // 배열을 순회하면서 정렬된 결과를 결과 배열에 저장
       for (i = arr.length - 1; i >= 0; i--) result[--freq[arr[i]]] = arr[i];

       // 정렬된 결과를 원본 배열 arr 에 복사
       System.arraycopy(result, 0, arr, 0, arr.length);

       return arr;
   }
}
