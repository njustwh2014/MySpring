/*
 * @Author: 王欢(HeartWh)
 * @Github:https://github.com/njustwh2014
 */

package cn.edu.seu.wh.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ConstructorArg {
    private int index;
    private String ref;
    private String name;
    private Object value;
}
