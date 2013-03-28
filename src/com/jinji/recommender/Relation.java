package com.jinji.recommender;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 27/3/13
 * Time: 12:02 PM
 */
public class Relation {

    private String name;
    private Direction direction;


    public Relation(String name, Direction direction) {
        this.name = name;
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getString() {

        if(getDirection()== Direction.INCOMING)
            return "<-[:"+getName()+"]-";
        else
            return "-[:"+getName()+"]->";

    }

}

