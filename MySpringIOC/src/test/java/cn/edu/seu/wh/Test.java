/*
 * @Author: 王欢(HeartWh)
 * @Github:https://github.com/njustwh2014
 */

package cn.edu.seu.wh;

import cn.edu.seu.wh.core.JsonApplicationContext;
import cn.edu.seu.wh.entity.Robot;

public class Test {
    public static void main(String[] args) throws Exception{
        JsonApplicationContext jsonApplicationContext=new JsonApplicationContext("application.json");
        jsonApplicationContext.init();
        Robot robot=(Robot)jsonApplicationContext.getBean("robot");
        robot.show();

    }
}
