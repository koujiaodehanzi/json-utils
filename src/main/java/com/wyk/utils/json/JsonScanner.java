package com.wyk.utils.json;

import static com.wyk.utils.json.JsonSign.*;

/**
 * @author wuyankun
 * @title: JsonScanner
 * @projectName json-util
 * @description: TODO
 * @date 2019/9/217:25
 */
public class JsonScanner {

    public static final char EOI = 0x1A;

    private String jsonStr;

    private int targetLevel;

    private int currentLevel = 0;

    private String targetStr;

    private char ch;

    private int currentIndex = 0;

    private int len;

    public JsonScanner(String jsonStr, int targetLevel) {
        this.jsonStr = jsonStr;
        this.targetLevel = targetLevel;

        len = jsonStr.length();
        this.ch = jsonStr.charAt(0);
    }

    public final void next(){
        int index = currentIndex++;
        ch = (index >= this.len ? EOI : jsonStr.charAt(index));
    }


    public String getTargetStr(){
        return this.targetStr;
    }

    public final void skipWhitespace() {
        for (;;) {
            if (ch <= '/') {
                if (ch == ' ' || ch == '\r' || ch == '\n' || ch == '\t' || ch == '\f' || ch == '\b') {
                    next();
                    continue;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
    }

    public void parse(){
        for (;;){
            if (ch == EOI){
                return;
            }else if (ch == LBRACE || ch == LBRACKET){
                currentLevel++;
            }else if (ch == RBRACE || ch == RBRACKET){
                currentLevel--;
            }
            if (currentLevel == targetLevel){
                // 当level相同时，查询该level是否有对应field
                for (;;){
                    String nextKey = getNextKey();
                    if ((nextKey == null || nextKey.equals("")) && (ch == RBRACE || ch == RBRACKET)){
                        next();
                        currentLevel--;
                        break;
                    }
                }
            }
        }
    }

    public String getNextKey(){
        if (ch == RBRACE || ch == RBRACKET){
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        int keyStatus = 0;
        for (;;){
            if (ch == QUOT){
                keyStatus ++;
            }
            if (keyStatus == 2){
                next();
                break;
            }else if (keyStatus == 1){
                stringBuilder.append(ch);
            }
            next();
        }
        return stringBuilder.toString().trim();
    }

    public String getNextValue(){
        skipWhitespace();
        if (ch != COLON){
            throw new RuntimeException("key和value之间必须有符号\":\"");
        }
        skipWhitespace();

        return "";
    }

}
