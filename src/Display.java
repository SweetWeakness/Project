import javax.naming.CompositeName;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Display {

    private static ArrayList<Point> pointList = new ArrayList<Point>();
    private JFrame window;
    public Triangle[] triangles;
    public Point[] points;
    public JPanel content;
    boolean flg=false;


    public void create(final int width, int height, String title){//метод для создания окна




        //окно

        window=new JFrame(title);
        JFrame.setDefaultLookAndFeelDecorated(true);
        window.setLayout(null);
        window.setPreferredSize(new Dimension(width,height));//передаем ее для нашей бумаги
        window.setResizable(false);//запрещаем изменять размер рамкипользователю
        window.pack();//чтобы рамка прогнулась под размеры бумажки
        window.setVisible(true);//банальщина - окно должно быть видимым
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//чтоб нажимая на крестик прога стопилась
        window.setLocationRelativeTo(null);// окно будет создано по центру



        //панель
        JPanel butpan=new JPanel();
        butpan.setBounds(768,0,1024/4,800);
        butpan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        butpan.setLayout(null);
        window.getContentPane().add(butpan);


        //рисовач
        content=new JPanel();
        content.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        content.setLayout(null);
        window.getContentPane().add(content);
        content.setBounds(0,0,767,800);






        //все кнопки и надписи
        final JButton ButAdd=new JButton("Добавить точку");
        butpan.add(ButAdd);
        ButAdd.setBounds(30,80,192,50);

        final JButton ButWrite=new JButton("Записать в файл");
        butpan.add(ButWrite);
        ButWrite.setVisible(false);
        ButWrite.setBounds(5,410,242,50);

        final JButton ButAgain=new JButton("Начать заново");
        butpan.add(ButAgain);
        ButAgain.setBounds(30,600,192,50);
        ButAgain.setVisible(false);

        final JLabel LaX=new JLabel("X :");
        butpan.add(LaX);
        LaX.setBounds(50,30,30,30);
        final JTextField TextX = new JTextField();
        butpan.add(TextX);
        TextX.setBounds(70,25,30,40);

        final JLabel LaY=new JLabel("Y :");
        butpan.add(LaY);
        LaY.setBounds(140,30,30,30);
        final JTextField TextY = new JTextField();
        butpan.add(TextY);
        TextY.setBounds(160,25,30,40);

        final JButton ButRanAdd=new JButton("Добавить случайное кол-во точек");
        butpan.add(ButRanAdd);
        ButRanAdd.setBounds(5,245,242,50);

        final JLabel LaOr=new JLabel("<<ИЛИ>>");
        butpan.add(LaOr);
        LaOr.setBounds(100,170,60,30);

        final JButton ButOK=new JButton("Начать построение треугольников");
        ButOK.setVisible(false);
        butpan.add(ButOK);
        ButOK.setBounds(5,245,242,50);

        final JLabel LaOr2=new JLabel("<<ИЛИ>>");
        butpan.add(LaOr2);
        LaOr2.setBounds(100,335,60,30);

        final JButton ButFile=new JButton("Считать точки из файла");
        butpan.add(ButFile);
        ButFile.setBounds(5,410,242,50);





        //слушатель кнопки рандом
        ButRanAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ButAgain.setVisible(true);
                clrscr();
                points=AddRan();
                for(int i=0;i<points.length;i++){
                    System.out.println(i+1+" "+points[i]);
                }
                writing(points);
                triangles=mainfunc(points);

                ButAdd.setVisible(false);
                LaX.setVisible(false);
                LaY.setVisible(false);
                TextX.setVisible(false);
                TextY.setVisible(false);
                ButOK.setVisible(true);
                ButFile.setVisible(false);
                LaOr2.setVisible(false);
                ButRanAdd.setBounds(5,80,242,50);
                ButRanAdd.setText("Изменить случайные точки");
            }
        });


        ButOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ButAgain.setVisible(true);
                ButWrite.setVisible(true);
                try {
                    if (flg) {
                        points = new Point[pointList.size()];
                        for (int i = 0; i < pointList.size(); i++) {
                            points[i] = pointList.get(i);
                        }
                        triangles = mainfunc(points);
                    }
                    docreate();
                }catch (Exception d){
                    System.out.println("Ошибка, нет точек");
                }
            }
        });


        //слушатель кнопки добавить
        ButAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ButAgain.setVisible(true);
                ButRanAdd.setVisible(false);
                LaOr.setVisible(false);
                flg=true;
                int X = (!TextX.getText().equals("")?Integer.parseInt(TextX.getText()):0);
                int Y= (!TextY.getText().equals("")?Integer.parseInt(TextY.getText()):0);
                if (X > 0 && Y > 0) {
                    pointList.add(new Point(X, Y));
                }
                if (pointList.size()%3==0){
                    ButOK.setVisible(true);
                    LaOr.setVisible(true);

                }else{
                    ButOK.setVisible(false);
                    LaOr.setVisible(false);
                }
                Point tmp=pointList.get(pointList.size()-1);
                Graphics g=content.getGraphics();
                g.setColor(Color.black);
                g.fillOval(tmp.getXP(),tmp.getYP(),4,4);
            }
        });





        ButAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clrscr();
                ButAgain.setVisible(false);
                ButAdd.setVisible(true);
                LaX.setVisible(true);
                LaY.setVisible(true);
                TextX.setVisible(true);
                TextY.setVisible(true);
                ButRanAdd.setBounds(5,245,242,50);
                LaOr.setVisible(true);
                LaOr2.setVisible(true);
                ButFile.setVisible(true);
                ButOK.setVisible(false);
                ButRanAdd.setVisible(true);
                ButWrite.setVisible(false);
                pointList=new ArrayList<Point>();
            }
        });




        ButFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ButAdd.setVisible(false);
                LaX.setVisible(false);
                LaY.setVisible(false);
                TextX.setVisible(false);
                TextY.setVisible(false);
                ButOK.setVisible(true);
                ButAgain.setVisible(true);
                LaOr.setVisible(false);
                LaOr2.setVisible(false);
                ButRanAdd.setVisible(false);
                ButOK.setVisible(true);
                ButFile.setVisible(false);
                flg=true;
                try(Scanner in=new Scanner(new File("in.txt"))){
                    while(in.hasNextInt()){
                        Point tmp=new Point(in.nextInt(),in.nextInt());
                        pointList.add(tmp);
                    }
                    for (int i=0;i<pointList.size();i++){
                        System.out.println(pointList.get(i));
                    }
                    writing(pointList);
                }catch (FileNotFoundException d){
                    System.out.println("Файл не найден" + d);
                }
            }
        });

        ButWrite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (PrintStream out=new PrintStream(new File("out.txt"))){
                    for(int i=0;i<triangles.length;i++){
                        out.println(triangles[i]);
                    }
                }catch (Exception d){
                    System.out.println("Невозможно создать файл");
                }
            }
        });

        clrscr();
    }










    //все функции для решения задач
    private Point[] AddRan(){
        Random random=new Random();
        int NG=random.nextInt(10)+1;

        Point[] ArrG=new Point[3*NG];

        for(int i=0;i<3*NG;i++){
            ArrG[i]=new Point(random.nextInt(760),random.nextInt(780));
        }

        return ArrG;


    }//случайное кол-ов точек

    private Triangle[] mainfunc(Point[] points){



        int n=points.length;//кол-во точек
        int max;//для посика точки с наибольшей координатой по Оу
        int k=0;//для запоминания индесов
        int min;//для поиска минимума
        Point[] arr=points;//наши объекты


        //поиск макса
        max=arr[0].getYP();
        for(int i=1;i<n;i++){
            if(max<arr[i].getYP()){
                max=arr[i].getYP();
            }
        }



        min=max;

        int[] x=new int[n];//цикл по заполнению массива "х" индесами из массива объектов
        for(int i=0;i<n;i++){//в итоге он будет заполнен индексами по порядку, то есть если a1(индекс)>a2, то у точки
            for(int j=0;j<n;j++){//в массиве с индексом x[а1] координата по Оy> чем у точки в массиве с индексом x[а2]
                if (arr[j].getYP()<=min&&arr[j].getFlg()){//поиск минимума по Оу (точка должна быть еще не рассмотрена)
                    min=arr[j].getYP();
                    k=j;
                }
            }
            min=max;//нашли, обнулили счетчик
            arr[k].del();//закрыли точка для дальнейшего просмотра
            x[i]=k;//заполнили массив х
        }

        Triangle[] tri=new Triangle[points.length/3];//создали дефолтный треугольник
        for(int i=0;i<n;){//вывод наших искомых треугольников
            tri[i/3]=new Triangle(arr[x[i]],arr[x[i+1]],arr[x[i+2]]);//I-тый треугольник, состоит из точек, у которых координаты по Оу>=
            System.out.println(i/3+1+" "+tri[i/3]);//больше чем у точки с индексом x[i-1] и <= x[i+4]
            i=i+3;
        }

        return tri;
    }// вернет массив из готовых треугольников


    private void writing(Point[] points){
        Graphics g=content.getGraphics();
        g.setColor(Color.black);
        for(int i=0;i<points.length;i++){
            g.fillOval(points[i].getXP(),points[i].getYP(),4,4);
        }
    }

    private void writing(ArrayList<Point> tmp){
        Graphics g=content.getGraphics();
        g.setColor(Color.black);
        for(int i=0;i<pointList.size();i++){
            g.fillOval(tmp.get(i).getXP(),tmp.get(i).getYP(),4,4);
        }
    }


    private void clrscr(){
        Graphics g=content.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(1,1,content.getWidth()-1,content.getHeight()-1);
    }

    private void docreate(){
        for (int i=0;i<triangles.length;i++){
            Graphics g=content.getGraphics();
            g.setColor(Color.GREEN);
            g.drawLine(triangles[i].ax+1,triangles[i].ay+1,triangles[i].bx+1,triangles[i].by+1);
            g.drawLine(triangles[i].cx+1,triangles[i].cy+1,triangles[i].bx+1,triangles[i].by+1);
            g.drawLine(triangles[i].ax+1,triangles[i].ay+1,triangles[i].cx+1,triangles[i].cy+1);
        }
    }
}
