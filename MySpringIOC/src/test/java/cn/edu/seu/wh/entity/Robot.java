/*
 * @Author: 王欢(HeartWh)
 * @Github:https://github.com/njustwh2014
 */

package cn.edu.seu.wh.entity;

public class Robot {
    private Hand hand;
    private Mouth mouth;
    public void show(){
        hand.waveHand();
        mouth.eatThing();
    }
}
