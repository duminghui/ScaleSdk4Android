package com.hcoor.sdk;

/**
 * 身体数据
 * Created by dumh on 14/11/8.
 */
public class BodyInfo {
    public int p_no;
    /**
     * 测量时间,从2000-1-1开始至测量时的秒数
     */
    public long time;
    /**
     * 体重
     */
    public float weight;

    /**
     * 脂肪百分比
     */
    public float bf;

    /**
     * 水分百分比
     */
    public float water;

    /**
     * 肌肉百分比
     */
    public float muscle;

    /**
     * 骨量
     */
    public float bone;

    /**
     * 基础代谢量
     */
    public int bmr;

//    /**
//     * 皮下脂肪百分比
//     */
//    public float sFat;

    /**
     * 内脏脂肪等级
     */
    public int inFat;

    /**
     * 体年龄
     */
    public int bodyAge;

    /**
     * 体重指数
     */
    public float bmi;

    /**
     * 去脂体重
     */
    public float noBfWeight;

    /**
     * 把所有数据设为默认值
     */
    public void emtpy() {
        p_no = 0;
        weight = 0;
        bf = 0;
        water = 0;
        muscle = 0;
        bone = 0;
        bmr = 0;
//        sFat = 0;
        inFat = 0;
        bodyAge = 0;
        bmi = 0;
    }

    public void setGroup1(int p_no, long time, float weight, float bf, float water) {
        this.p_no = p_no;
        this.time = time;
        this.weight = weight;
        this.bf = bf;
        this.water = water;
    }

    public void setGroup2(float muscle, float bone, int bmr, int inFat, int bodyAge) {
        this.muscle = muscle;
        this.bone = bone;
        this.bmr = bmr;
        this.inFat = inFat;
        this.bodyAge = bodyAge;
    }

    @Override
    public String toString() {
        return String.format("BodyInfo{p_no=%d, time=%d, weight=%s, bf=%s, water=%s, muscle=%s, bone=%s, bmr=%d, inFat=%d, bodyAge=%d}", p_no, time, weight, bf, water, muscle, bone, bmr, inFat, bodyAge);
    }
}
