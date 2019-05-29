package com.coco.entity;

/**
 * @Classname Result
 * @Description TODO
 * @Date 19-5-29 下午2:22
 * @Created by ltt
 */
public class Result {
    private boolean judge;
    private Object message;

    public Result(){
    }
    public Result(boolean judge,Object type){
        this.judge=judge;
        this.message = message;
    }

    public boolean getJudge() {
        return judge;
    }

    public void setJudge(boolean judge) {
        this.judge = judge;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
