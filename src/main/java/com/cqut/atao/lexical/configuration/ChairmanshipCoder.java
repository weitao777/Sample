package com.cqut.atao.lexical.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author atao
 * @version 1.0.0
 * @ClassName chairmanshipCoder.java
 * @Description 读取种别码
 * @createTime 2022年04月20日 08:54:00
 */
@Configuration
public class ChairmanshipCoder {


    @Bean
    public Map<String,Integer> getCodeMap(){
        return ChairmanshipCoder.readCoder();
    }

    /**
     * 读取种别码
     * @return
     */
    public static Map<String,Integer> readCoder(){
        Map<String,Integer> map = new HashMap<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("target/classes/chairmanship_code.txt"));
            String s = null;
            // 一次读入一行，直到读入null为文件结束
            while ((s = reader.readLine()) != null) {
                String[] temp = s.split(" ");
                map.put(temp[0],Integer.valueOf(temp[1]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return map;
    }

    /**
     * 读取代码
     * @return
     */
    public static String readFile(String filePath){
        String text = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String s = "";
            // 一次读入一行，直到读入null为文件结束
            while ((s = reader.readLine()) != null) {
                text += (s+'\n');
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return text;
    }

}
