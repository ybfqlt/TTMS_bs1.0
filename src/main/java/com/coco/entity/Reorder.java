package com.coco.entity;

/**
 * @Classname Reorder
 * @Description TODO
 * @Date 19-6-10 下午4:12
 * @Created by xns
 */
public class Reorder {

    /*private Integer scheduleId;*/

    private Integer seatRow;
    private Integer seatCol;

    public Reorder(Integer seatRow, Integer seatCol){
       /* this.scheduleId=scheduleId;*/
        this.seatRow=seatRow;
        this.seatCol=seatCol;

    }
    /*public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }
*/
    public Integer getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(Integer seatRow) {
        this.seatRow = seatRow;
    }

    public Integer getSeatCol() {
        return seatCol;
    }

    public void setSeatCol(Integer seatCol) {
        this.seatCol = seatCol;
    }

}
