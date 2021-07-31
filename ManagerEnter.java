import java.awt.event.*;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;


public class ManagerEnter extends JFrame implements ActionListener
{
    private JFrame frame = new JFrame("图书馆管理系统 ");
    private ImageIcon background;
    private JPanel ManagerEnterPane;
    private JTextField ReaderNametextField;
    private JTextField ReaderPasswordtextField;
    public static String mno_flag;

    public static void main(String[] args)
    {
        new ManagerEnter();
    }

    public ManagerEnter()
    {
        background = new ImageIcon(this.getClass().getResource("back.jpg"));// 背景图片
        JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
        label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());// 把标签的大小位置设置为图片刚好填充整个面板

        ManagerEnterPane = (JPanel) frame.getContentPane(); // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明

        ManagerEnterPane.setOpaque(false);
        ManagerEnterPane.setLayout(null);//无布局
        frame.getLayeredPane().setLayout(null);

        frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // 把背景图片添加到分层窗格的最底层作为背景

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setBounds(500, 200, 450, 300);//窗体大小
        frame.setResizable(false);//不可更改尺寸
        frame.setVisible(true);//窗体可见



        JLabel ReaderNameLabel = new JLabel("工号:");
        ManagerEnterPane.add(ReaderNameLabel);
        ReaderNameLabel.setBounds(70, 37, 61, 37);
        ReaderNameLabel.setFont(new Font("",Font.BOLD,15));


        JLabel ReaderPasswordLabel = new JLabel("密码:");
        ManagerEnterPane.add(ReaderPasswordLabel);
        ReaderPasswordLabel.setBounds(70, 121, 61, 24);
        ReaderPasswordLabel.setFont(new Font("",Font.BOLD,15));


        //创建文本输入框
        ReaderNametextField = new JTextField();
        ManagerEnterPane.add(ReaderNametextField);
        ReaderNametextField.setBounds(156, 39, 178, 37);
        ReaderNametextField.setColumns(10);

        ReaderPasswordtextField = new JPasswordField();
        ManagerEnterPane.add(ReaderPasswordtextField);
        ReaderPasswordtextField.setForeground(Color.BLACK);
        ReaderPasswordtextField.setBounds(156, 117, 178, 37);
        ReaderPasswordtextField.setColumns(10);


        //再创建两个按钮
        JButton RegisterButton = new JButton("注册");
        ManagerEnterPane.add(RegisterButton);
        RegisterButton.setBounds(60, 187, 88, 37);
        RegisterButton.setFont(new Font("",Font.BOLD,15));
        RegisterButton.addActionListener(this);


        JLabel ForgetLabel = new JLabel("忘记密码？");
        ManagerEnterPane.add(ForgetLabel);
        ForgetLabel.setBounds(358, 123, 66, 40);
        ForgetLabel.setForeground(Color.blue);


        JButton LandButton = new JButton("登陆");
        ManagerEnterPane.add(LandButton);
        LandButton.setFont(new Font("", Font.BOLD, 15));
        LandButton.setBounds(180, 187, 88, 37);



        JButton justexit = new JButton("退出系统");
        justexit.setBackground(Color.gray);    //设置按钮背景色
        ManagerEnterPane.add(justexit);
        justexit.setBounds(300, 187, 88, 37);
        justexit.addActionListener(this);



        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        RegisterButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                if(e.getSource()==RegisterButton)
                {
                    new ManagerRegister();
                }
            }
        });

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

        LandButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                int b=-10;
                if(e.getSource()==LandButton)
                {
                    //得到用户名和密码
                    String id=ReaderNametextField.getText();
                    String password=ReaderPasswordtextField.getText();
                    if(id.equals("")||password.equals(""))
                    {
                        JOptionPane.showMessageDialog(null,"请输入完整信息","通知",JOptionPane.INFORMATION_MESSAGE);
                        ReaderNametextField.setText(id);
                        ReaderPasswordtextField.setText(password);
                    }
                    else {
                        try
                        {
                             b = new connectZJU().log_Manager(id, password);
                        }

                        catch (Exception e1) {
                            e1.printStackTrace();
                            System.out.println("失败");
                        }
//                        1: 成功登录
//                        0：密码错误 -1：账户不存在
                        if (b==1)
                        {
                            mno_flag=id;
                            frame.dispose();
                            try {
                                new ManagerFrame(mno_flag);
                            } catch (Exception e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                        if(b==-1){
                            JOptionPane.showMessageDialog(null,"账户不存在，请注册","通知",JOptionPane.INFORMATION_MESSAGE);
                            ReaderNametextField.setText(id);
                            ReaderPasswordtextField.setText(password);
                        }
                        if(b==0){
                            JOptionPane.showMessageDialog(null,"用户名或密码错误","通知",JOptionPane.INFORMATION_MESSAGE);
                            ReaderNametextField.setText(id);
                            ReaderPasswordtextField.setText(password);
                        }
                    }
                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}


