package com.ps.algorithm;

import java.util.Arrays;
import java.util.HashMap;

public class StringSearch {

    public static void main (String[] args) {
        StringSearch stringSearch = new StringSearch();
        String txt = "CDABABDABACDABABCABCB";
        String pattern = "ABAB";

        System.out.println("문자열: " + txt);
        System.out.println("패턴: " + pattern);

        // Brute-force
        System.out.println("\n*** Brute-Force ***");
        stringSearch.bruteForceSearch(txt, pattern);

        // KMP
        System.out.println("\n*** KMP ***");
        stringSearch.kmpSearch(txt, pattern);

        // Boyer-Moore
        System.out.println("\n*** Boyer-Moore ***");
        stringSearch.boyerMooreSearch(txt, pattern);
    }

    /**
     * 브루트-포스법 (Brute-Force)
     * 문자열에서 특정 문자를 검색하는 알고리즘
     * 패턴 (검색할 문자열) 이 텍스트 내에서 나타나는 모든 위치를 하나씩 비교하여 찾는 방식
     * 시간 복잡도: O(n * m) (n 은 문자열의 길이, m 은 패턴의 길이)
     */
    public void bruteForceSearch (String txt, String pattern) {
        int n = txt.length();
        int m = pattern.length();

        // 텍스트의 각 위치에서 패턴을 비교
        for (int i = 0; i <= n - m; i++) {
            int j;
            // 패턴과 텍스트를 비교
            for (j = 0; j < m; j++) {
                if (txt.charAt(i + j) != pattern.charAt(j))
                    break; // 불일치 시 루프 탈출
            }
            // 전체 패턴이 일치하면 인덱스 반환
            if (j == m)  System.out.println("패턴이 문자열의 " + i + "번째에 있습니다.");
        }
    }

    /**
     * KMP 법에 사용되는 LPS (Longest prefix suffix) 배열 생성하는 함수
     * LPS 배열이란 패턴의 각 접두사와 접미사가 일치하는 문자열 중 가장 긴 길이를 인덱스별로 담고 있는 배열.
     *
     * e.g. pattern = "ABABCA" 인 경우
     *      lps[1] = 0 (AB 의 접두사 - A, 접미사 - B. 인덱스가 일치하지 않는 접두사와 접미사 없음)
     *      lps[2] = 1 (ABA 의 접두사 - A, AB, 접미사 - A, BA. 접두사와 접미사가 일치하는 문자열 중 가장 긴 길이: "A")
     *      lps[3] = 2 (ABAB 의 접두사 - A, AB, ABA, 접미사 - B, AB, BAB. 가장 긴 길이: "AB")
     *      lps[4] = 0 (ABABC 의 접두사 - A, AB, ABA, ABAB, 접미사 - C, BC, ABC, BABC. 일치하는 접두사와 접미사 없음)
     *      lps[5] = 1 (ABABCA 의 접두사 - A, AB, ABA, ABAB, ABABC, 접미사 - A, CA, BCA, ABCA, BABCA. 가장 긴 길이: "A")
     *      lps 배열은 [0, 1, 2, 0, 1] 이 된다.
     */
    public int[] computeLPSArray (String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i++] = len;
            }
            else {
                if (len != 0) len = lps[len - 1];
                else lps[i++] = 0;
            }
        }
        System.out.println("LPS 배열: " + Arrays.toString(lps));

        return lps;
    }
    /**
     * KMP 법 (Knuth-Morris-Pratt)
     * 패턴 내의 접두사와 접미사를 활용하여 불필요한 비교를 줄인 문자열 검색 알고리즘
     * LPS 배열을 활용하여 패턴 내에서 이전 비교 결과를 활용함으로써 검색 속도가 향상됨.
     * 단, 패턴 안에 반복이 없으면 효율이 떨어짐.
     * 시간 복잡도: O(n + m) (n 은 문자열의 길이, m 은 패턴의 길이)
     */
    public void kmpSearch (String txt, String pattern) {
        int n = txt.length();
        int m = pattern.length();
        int[] lps = computeLPSArray(pattern); // 패턴의 LPS 배열

        int i = 0;  // 텍스트 인덱스
        int j = 0;  // 패턴 인덱스

        while (i < n) {
            if (pattern.charAt(j) == txt.charAt(i)) {
                i++;
                j++;
            }
            // 문자열에서 패턴 발견
            if (j == m) {
                System.out.println("패턴이 문자열의 " + (i - j) + "번째에 있습니다.");
                j = lps[j - 1]; // 다음 패턴 인덱스로 이동
            }
            else if (i < n && pattern.charAt(j) != txt.charAt(i)) {
                if (j != 0) j = lps[j - 1]; // LPS 배열을 사용하여 인덱스 이동
                else i++;
            }
        }
    }

    /**
     * Boyer-Moore 법에 사용되는 불일치 이동 규칙을 생성하는 함수
     * 패턴의 각 문자에 대한 불일치 이동 규칙 생성
     */
    public HashMap<Character, Integer> buildBadCharTable (String pattern) {
        HashMap<Character, Integer> badCharTable = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++)
            badCharTable.put(pattern.charAt(i), i);

        System.out.println(badCharTable);
        return badCharTable;
    }
    /**
     * 보이어-무어법 (Boyer-Moore)
     * 보이어-무어법은 '불일치 시 이동 규칙' 과 '호이징 규칙' 이 두가지 접근 방식을 사용하여 검색 속도를 향상시킨다.
     * 1. 불일치 시 이동 규칙: 패턴을 오른쪽에서 왼쪽으로 비교하며, 불일치가 발생하면 불일치한 문자의 위치와 패턴 내에서
     *    그 문자의 마지막 위치를 비교하여 이동한다.
     * 2. 호이징 규칙: 패턴이 텍스트에서 발견되었을 때, 다음 검색을 위해 텍스트의 현재 위치에서 패턴의 다음 위치로 이동한다.
     *    이 때, 텍스트의 특정 문자가 패턴 내에 존재하는 경우, 그 문자의 마지막 위치를 기준으로 이동한다.
     * 시간 복잡도: O(n / m) (n 은 문자열의 길이, m 은 패턴의 길이)
     */
    public void boyerMooreSearch (String txt, String pattern) {
        HashMap<Character, Integer> badCharTable = buildBadCharTable(pattern);
        int n = txt.length();
        int m = pattern.length();

        int i = 0;  // 텍스트에서 패턴을 비교할 시작 인덱스

        while (i <= n - m) {
            int j = m - 1;  // 패턴의 마지막 문자 인덱스

            // 패턴과 텍스트의 현재 위치에서 비교
            while (j >= 0 && pattern.charAt(j) == txt.charAt(i + j))
                j--;

            // j 가 -1 이면 패턴이 발견된 것
            if (j < 0) {
                System.out.println("패턴이 문자열의 " + i + "번째에 있습니다.");
                i += (i + m < n) ? m - badCharTable.getOrDefault(txt.charAt(i + m), -1) : 1;
            } else {
                // 불일치가 발생한 경우
                int badCharShift = badCharTable.getOrDefault(txt.charAt(i + j), -1);
                i += Math.max(1, j - badCharShift); // 이동 거리 계산
            }
        }
    }
}
