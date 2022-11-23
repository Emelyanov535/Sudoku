package SudokuGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;

import static SudokuGame.Map.GenerateMap;
import static SudokuGame.Map.map;

public class SudokuGame{

    public static int N = 20;
    public static int[][] DeleteMap = new int[9][9];

    public static void DrawMap(JTextField[][] field,JFrame frame){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(map[i][j]!=0){
                    field[i][j] = new JTextField(String.valueOf(map[i][j]));
                    field[i][j].setEditable(false);
                }else{
                    field[i][j] = new JTextField();
                }
                Font BifFont = new Font("TimesRoman", Font.BOLD,30);
                field[i][j].setBounds(i * 53 + 20,j * 53 + 20,50,50);
                field[i][j].setFont(BifFont);
                field[i][j].setHorizontalAlignment(JTextField.CENTER);
                frame.add(field[i][j]);
            }
        }
    }

    public static void CreateDeleteMap(int N){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                DeleteMap[i][j] = map[i][j];
            }
        }
        while(N > 0){
            int x = (int)(Math.random() * 9);
            int y = (int)(Math.random() * 9);
            if(map[x][y]!=0){
                map[x][y] = 0;
                N--;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SudokuGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        GenerateMap();
        CreateDeleteMap(N);

        JTextField[][] field = new JTextField[9][9];
        DrawMap(field,frame);

        JButton NewGame = new JButton("Новая игра");
        NewGame.setBounds(500,20,100,50);

        JButton Check = new JButton("Проверить");
        Check.setBounds(500,74,100,50);

        JButton Hint = new JButton("Подсказка");
        Hint.setBounds(500,128,100,50);

        frame.add(Check);
        frame.add(NewGame);
        frame.add(Hint);
        Canvas canvas = new Canvas();
        frame.add(canvas);

        frame.setSize(630,555);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        NewGame.addActionListener(e -> {
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    frame.remove(field[i][j]);
                }
            }
            int L = N;
            GenerateMap();
            CreateDeleteMap(L);

            frame.repaint();
            DrawMap(field,frame);

        });

        Check.addActionListener(e ->  {
            boolean Flag = false;
                for(int i = 0; i < 9; i++){
                    for(int j = 0; j < 9; j++){
                        if(Objects.equals(field[j][i].getText(), Integer.toString(DeleteMap[j][i]))){
                            Flag = false;
                        }else{
                            Flag = true;
                            break;
                        }
                    }
                    if(Flag){
                        JOptionPane.showMessageDialog(null,"Решено неверно");
                        break;
                    }
                }
                if(!Flag){
                    JOptionPane.showMessageDialog(null,"Решено верно");
                }
        });

        Hint.addActionListener(e -> {
            int K = 1;
            while(K > 0){
                int x = (int)(Math.random() * 9);
                int y = (int)(Math.random() * 9);
                if(field[x][y].getText().isEmpty()){
                    field[x][y].setText(Integer.toString(DeleteMap[x][y]));
                    K--;
                }
            }
        });
    }

    static class Canvas extends JComponent implements ActionListener{

        public Canvas(){super();}

        @Override
        public void actionPerformed(ActionEvent e) {}

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setStroke(new BasicStroke(2));
            for (int y = 18; y < 600; y = y + 159){
                g2d.drawLine(18, y, 495, y);
            }
            for (int x = 18; x < 600; x = x + 159){
                g2d.drawLine(x, 18, x, 495);
            }
        }
    }
}