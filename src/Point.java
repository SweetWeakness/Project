import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Point {

    //поля координат точки
    private int x;
    private int y;
    //поле flg
    boolean flg;

    //сетеры
    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    void setFlg(boolean flg) {
        this.flg = flg;
    }

    //гетеры
    int getXP() {
        return this.x;
    }

    int getYP() {
        return this.y;
    }

    boolean getFlg() {
        return this.flg;
    }

    //конструкторы
    Point() {
        this.x = 0;
        this.y = 0;
        flg = true;
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
        flg = true;
    }

    //формат вывода
    @Override
    public String toString() {
        return "(" + this.x + ";" + this.y + ")";
    }

    //убирает точку из доступных для рассмотрения
    void del() {
        this.flg = false;
    }

}


