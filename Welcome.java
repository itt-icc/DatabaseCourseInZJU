import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Welcome implements ActionListener{
    private JFrame frame = new JFrame("图书馆管理系统 ");
    private JPanel imagePanel;
    private ImageIcon background;

    public Welcome() {
        background = new ImageIcon(this.getClass().getResource("back.jpg"));// 背景图片
        JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
        label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());// 把标签的大小位置设置为图片刚好填充整个面板
        imagePanel = (JPanel) frame.getContentPane(); // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
        frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // 把背景图片添加到分层窗格的最底层作为背景

        imagePanel.setOpaque(false);
        imagePanel.setLayout(null);//无布局
        frame.getLayeredPane().setLayout(null);

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setBounds(500, 200, 450, 300);//窗体大小
        frame.setResizable(false);//不可更改尺寸
        frame.setVisible(true);//窗体可见

        JLabel title = new JLabel("ZJU图书馆管理系统");
        title.setBounds(120, 20, 300, 20);//调整title的大小和位置
        title.setFont(new Font("黑体",Font.ITALIC+Font.BOLD,20));//倾斜+加粗
        imagePanel.add(title);

        JButton ManagerEnterButton = new JButton("管理员入口");
        ManagerEnterButton.setBackground(Color.YELLOW);    //设置按钮背景色
        imagePanel.add(ManagerEnterButton);
        ManagerEnterButton.setBounds(124, 66, 184, 40);
        ManagerEnterButton.addActionListener(this);


//        JButton ReaderEnterButton = new JButton("读者入口");
//        ReaderEnterButton.setBackground(Color.pink);    //设置按钮背景色
//        imagePanel.add(ReaderEnterButton);
//        ReaderEnterButton.setBounds(124, 130, 184, 40);
//        ReaderEnterButton.addActionListener(this);


        JButton justexit = new JButton("退出系统");
        justexit.setBackground(Color.gray);    //设置按钮背景色
        imagePanel.add(justexit);
        justexit.setBounds(124, 180, 184, 40);
        justexit.addActionListener(this);


        ManagerEnterButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                if(e.getSource()==ManagerEnterButton)
                {
                    frame.dispose();
                    new ManagerEnter();
                }
            }
        });



//        ReaderEnterButton.addActionListener(new ActionListener()
//        {
//
//            @Override
//            public void actionPerformed(ActionEvent e)
//            {
//                // TODO Auto-generated method stub
//                boolean b=false;
//                if(e.getSource()==ReaderEnterButton)
//                {
//                    JOptionPane.showMessageDialog(null,"请返回管理员入口","警示",JOptionPane.INFORMATION_MESSAGE);
//                }
//            }
//        });



        justexit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                if(e.getSource()==justexit)
                {
                    frame.dispose();
                }
            }
        });
    }




    public static void main(String[] args)
    {
        new Welcome();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}



