import javax.swing.*;
import java.awt.*;

public class Point{

    //поля координат точки
    private double x;
    private double y;
    //поле flg
    boolean flg;

    //сетеры
    void setX(double x) {
        this.x = x;
    }

    void setY(double y) {
        this.y = y;
    }

    void setFlg(boolean flg) {
        this.flg = flg;
    }

    //гетеры
    double getX() {
        return this.x;
    }

    double getY() {
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

    Point(double x, double y) {
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


    public void writing(Graphics g){
        g.fillOval(0,0,3,3);
    }

}


