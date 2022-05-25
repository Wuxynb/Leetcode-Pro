package bbc.others;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileFormatConversion {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt"), "GB2312"));
        Map<String, String> map = new HashMap<>();
        map.put("xijing", "西京");
        map.put("lushan", "鲁山");
        map.put("pingyang", "平阳");
        map.put("danling", "丹凌");
        map.put("xinyuan", "新元");
        String line, schoolMsg = "";
        // na005 aaa 工程技术学院 010-21345673 西京 张小林 java 84 13553001115
        while ((line = bf.readLine()) != null) {
            if (line.indexOf(" ") != -1) {
                StringBuilder sb = new StringBuilder();
                String[] arr1 = line.split(" ");
                String[] arr2 = arr1[1].split(":");
                schoolMsg = sb.append(arr2[2])
                        .append(" ")
                        .append(arr1[0])
                        .append(" ")
                        .append(arr2[0])
                        .append(" ")
                        .append(arr2[1])
                        .append(" ")
                        .append(map.getOrDefault(arr2[3], arr2[3]))
                        .append(" ").toString();
            } else {
                String[] arr = line.split(":");
                StringBuilder sb = new StringBuilder(schoolMsg);
                if (arr.length == 4) {
                    sb.append(arr[3])
                            .append(" ")
                            .append(arr[1])
                            .append(" ")
                            .append(arr[0])
                            .append(" ")
                            .append(arr[2]);
                } else {
                    sb.append(arr[2])
                            .append(" ")
                            .append(arr[1])
                            .append(" ")
                            .append(arr[0]);
                }
                System.out.println(sb.toString());
            }
        }
        bf.close();
        scan.close();
    }
}
