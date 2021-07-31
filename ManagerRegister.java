import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ManagerRegister extends JFrame implements ActionListener
{
    private JFrame frame = new JFrame("ZJU图书馆-管理员注册界面");
    private ImageIcon background;
    private JPanel ManagerRegisterPane;

    private JTextField Name;
    private JTextField Number;
    private JTextField Password;
    private JTextField PasswordAgain;
    private JTextField Telephone;




    public ManagerRegister() {
        background = new ImageIcon(this.getClass().getResource("back.jpg"));// 背景图片
        JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
        label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());// 把标签的大小位置设置为图片刚好填充整个面板
        ManagerRegisterPane = (JPanel) frame.getContentPane(); // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
        frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // 把背景图片添加到分层窗格的最底层作为背景

        ManagerRegisterPane.setOpaque(false);
        ManagerRegisterPane.setLayout(null);//无布局
        frame.getLayeredPane().setLayout(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭操作
        frame.setBounds(450, 200, 450, 300);//窗体大小
        frame.setResizable(false);//不可更改尺寸
        frame.setVisible(true);//窗体可见


        JLabel name = new JLabel("姓名:");
        ManagerRegisterPane.add(name);
        name.setBounds(120, 26, 54, 15);

        Name = new JTextField();
        ManagerRegisterPane.add(Name);
        Name.setBounds(193, 26, 155, 21);
        Name.setColumns(10);

        JLabel number = new JLabel("工号:");
        ManagerRegisterPane.add(number);
        number.setBounds(120, 56, 54, 15);


        Number = new JTextField();
        ManagerRegisterPane.add(Number);
        Number.setColumns(10);
        Number.setBounds(193, 56, 155, 21);



        JLabel password = new JLabel("密码:");
        ManagerRegisterPane.add(password);
        password.setBounds(120, 86, 54, 15);

        Password = new JPasswordField();
        ManagerRegisterPane.add(Password);
        Password.setColumns(10);
        Password.setBounds(193, 86, 155, 21);


        JLabel passwordagain = new JLabel("确认密码:");
        ManagerRegisterPane.add(passwordagain);
        passwordagain.setBounds(120, 116, 80, 15);

        PasswordAgain = new JPasswordField();
        ManagerRegisterPane.add(PasswordAgain);
        PasswordAgain.setColumns(10);
        PasswordAgain.setBounds(193, 116, 155, 21);


        JLabel telephone = new JLabel("手机号:");
        ManagerRegisterPane.add(telephone);
        telephone.setBounds(120, 146, 54, 15);

//        frame.getContentPane().add(telephone);


        Telephone = new JTextField();
        ManagerRegisterPane.add(Telephone);
        Telephone.setColumns(11);
        Telephone.setBounds(193, 146, 155, 21);
//        frame.getContentPane().add(Telephone);



        JButton register = new JButton("注册");
        frame.getContentPane().add(register);
        register.setBounds(120, 200, 80, 20);
        register.setBackground(Color.YELLOW);    //设置按钮背景色


        JButton cancle = new JButton("取消");
        frame.getContentPane().add(cancle);
        cancle.setBounds(236, 200, 80, 20);


        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        register.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                boolean b=false;
                if(e.getSource()==register){
                    String name0=Name.getText();
                    String telephone0=Telephone.getText();
                    String ID=Number.getText();
                    String password0=Password.getText();
                    String checkpassword0=PasswordAgain.getText();

                    /*输入完整信息*/
                    if(ID.equals("")||telephone0.equals("")||ID.equals("")||password0.equals("")||checkpassword0.equals(""))
                    {
                        JOptionPane.showMessageDialog(null,"请输入完整信息","错误",JOptionPane.INFORMATION_MESSAGE);
                        Password.setText(password0);
                        PasswordAgain.setText(checkpassword0);
                        Name.setText(name0);
                        Telephone.setText(telephone0);
                        Number.setText(ID);
                    }

                    else
                        try
                        {
                            /*不能重复注册*/
                            System.out.println(new connectZJU().testMno(ID));
                            if(new connectZJU().testMno(ID))
                            {
                                JOptionPane.showMessageDialog(null,"此用户已经注册","错误",JOptionPane.INFORMATION_MESSAGE);
                                Number.setText("");
                            }

                            /*建立连接之前先检擦密码一致*/
                            else if(!password0.equals(checkpassword0))
                            {
                                //您的密码不一致
                                JOptionPane.showMessageDialog(null,"您的密码不一致，请重新输入","错误",JOptionPane.INFORMATION_MESSAGE);
                                Password.setText("");
                                PasswordAgain.setText("");
                            }
                            else {
                                b = new connectZJU().reg_manager(ID, password0, name0, telephone0);
                                if (b) {
                                    System.out.print("注册成功");
                                    JOptionPane.showMessageDialog(null, "注册成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        }
                        catch(Exception sqlException)
                        {
                            sqlException.printStackTrace();
                        }

                }
            }
        });
        cancle.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==cancle) {
                    frame.dispose();
                }
            }
        });
    }

    public static void main(String[] args)
    {
        new ManagerRegister();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
