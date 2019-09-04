package com.wyk;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        System.out.println('"' == '\"');
        Map<String, List<User>> map = new HashMap<>();
        List<User> list = new ArrayList<>();

        User user1 = new User(1, "alibaba");
        User user2 = new User(2,"zhifubao");
        list.add(user1);
        list.add(user2);
        map.put("\"666{77}",list);

        String s = JSON.toJSONString(map);

        JSONObject parseObject = JSON.parseObject(s);

        System.out.println(s);
        String a = a("((ur)oi)");
    }


    public String a(String s){
        char[] array = s.toCharArray();
        int left = 0;
        int right = 0;
        for (char c : array){
            if (c == '('){
                left ++;
            }else if(c == ')'){
                right ++;
            }
            if (right > left){
                return null;
            }
        }

        this.r(array, 0);
        StringBuilder target = new StringBuilder();
        for (char c : array){
            if (c != '(' && c != ')'){
                target.append(c);
            }
        }

        System.out.println(target.toString());
        return target.toString();
    }

    public void r(char[] chars, int start){
        int index = start;

        for (int i=index; i<chars.length; i++){
            char c = chars[index];
            if (c == '('){
                this.r(chars, index+1);
            }else if (c == ')'){
                reverse(chars, start, index-1);
                start = index+1;
            }
            index++;
        }

    }

    public void reverse(char[] chars, int left, int right){
        char[] arr = new char[right-left+1];
        int index = right;
        for (int i=0;i<arr.length;i++){
            arr[i] = chars[index];
            index--;
        }
        for (int i=0;i<arr.length;i++){
            chars[left++] = arr[i];
        }
    }
}
