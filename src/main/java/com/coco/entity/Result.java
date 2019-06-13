package com.coco.entity;

/**
 * @Classname Result
 * @Description TODO
 * @Date 19-5-29 下午2:22
 * @Created by xns
 */
public class Result {
    private boolean judge;
    private Object mes;

    public Result(){
    }
    public Result(boolean judge,Object mes){
        this.judge=judge;
        this.mes = mes;
    }

    public boolean getJudge() {
        return judge;
    }

    public void setJudge(boolean judge) {
        this.judge = judge;
    }

    public Object getMes() {
        return mes;
    }

    public void setMes(Object mes) {
        this.mes = mes;
    }
}
