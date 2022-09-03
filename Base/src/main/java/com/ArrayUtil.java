package com;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sun.media.sound.SoftTuning;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

/**
 * @author xtc
 * @create 2022-09-03 14:31
 */
public class ArrayUtil {

    public static void main(String[] args) {
        generateArray();
    }

    public static void generateArray() {
        int [] array = new int[800000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * array.length);
        }
        File file = new File("Base/array.json");
        if(file.exists()){
            file.delete();
        }
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.append(JSON.toJSONString(array));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] readArray(){
        StringBuilder sb = new StringBuilder();
        try (FileReader fr = new FileReader("Base/array.json")) {
            char[] chars = new char[1024];
            int length;
            while((length = fr.read(chars, 0, chars.length)) != -1){
                sb.append(chars, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseArray(sb.toString(), Integer.class).stream().mapToInt(v -> v).toArray();
    }

}
