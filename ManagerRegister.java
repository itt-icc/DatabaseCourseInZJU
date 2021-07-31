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
    private JFrame frame = new JFrame("ZJUͼ���-����Աע�����");
    private ImageIcon background;
    private JPanel ManagerRegisterPane;

    private JTextField Name;
    private JTextField Number;
    private JTextField Password;
    private JTextField PasswordAgain;
    private JTextField Telephone;




    public ManagerRegister() {
        background = new ImageIcon(this.getClass().getResource("back.jpg"));// ����ͼƬ
        JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
        label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
        ManagerRegisterPane = (JPanel) frame.getContentPane(); // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
        frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����

        ManagerRegisterPane.setOpaque(false);
        ManagerRegisterPane.setLayout(null);//�޲���
        frame.getLayeredPane().setLayout(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رղ���
        frame.setBounds(450, 200, 450, 300);//�����С
        frame.setResizable(false);//���ɸ��ĳߴ�
        frame.setVisible(true);//����ɼ�


        JLabel name = new JLabel("����:");
        ManagerRegisterPane.add(name);
        name.setBounds(120, 26, 54, 15);

        Name = new JTextField();
        ManagerRegisterPane.add(Name);
        Name.setBounds(193, 26, 155, 21);
        Name.setColumns(10);

        JLabel number = new JLabel("����:");
        ManagerRegisterPane.add(number);
        number.setBounds(120, 56, 54, 15);


        Number = new JTextField();
        ManagerRegisterPane.add(Number);
        Number.setColumns(10);
        Number.setBounds(193, 56, 155, 21);



        JLabel password = new JLabel("����:");
        ManagerRegisterPane.add(password);
        password.setBounds(120, 86, 54, 15);

        Password = new JPasswordField();
        ManagerRegisterPane.add(Password);
        Password.setColumns(10);
        Password.setBounds(193, 86, 155, 21);


        JLabel passwordagain = new JLabel("ȷ������:");
        ManagerRegisterPane.add(passwordagain);
        passwordagain.setBounds(120, 116, 80, 15);

        PasswordAgain = new JPasswordField();
        ManagerRegisterPane.add(PasswordAgain);
        PasswordAgain.setColumns(10);
        PasswordAgain.setBounds(193, 116, 155, 21);


        JLabel telephone = new JLabel("�ֻ���:");
        ManagerRegisterPane.add(telephone);
        telephone.setBounds(120, 146, 54, 15);

//        frame.getContentPane().add(telephone);


        Telephone = new JTextField();
        ManagerRegisterPane.add(Telephone);
        Telephone.setColumns(11);
        Telephone.setBounds(193, 146, 155, 21);
//        frame.getContentPane().add(Telephone);



        JButton register = new JButton("ע��");
        frame.getContentPane().add(register);
        register.setBounds(120, 200, 80, 20);
        register.setBackground(Color.YELLOW);    //���ð�ť����ɫ


        JButton cancle = new JButton("ȡ��");
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

                    /*����������Ϣ*/
                    if(ID.equals("")||telephone0.equals("")||ID.equals("")||password0.equals("")||checkpassword0.equals(""))
                    {
                        JOptionPane.showMessageDialog(null,"������������Ϣ","����",JOptionPane.INFORMATION_MESSAGE);
                        Password.setText(password0);
                        PasswordAgain.setText(checkpassword0);
                        Name.setText(name0);
                        Telephone.setText(telephone0);
                        Number.setText(ID);
                    }

                    else
                        try
                        {
                            /*�����ظ�ע��*/
                            System.out.println(new connectZJU().testMno(ID));
                            if(new connectZJU().testMno(ID))
                            {
                                JOptionPane.showMessageDialog(null,"���û��Ѿ�ע��","����",JOptionPane.INFORMATION_MESSAGE);
                                Number.setText("");
                            }

                            /*��������֮ǰ�ȼ������һ��*/
                            else if(!password0.equals(checkpassword0))
                            {
                                //�������벻һ��
                                JOptionPane.showMessageDialog(null,"�������벻һ�£�����������","����",JOptionPane.INFORMATION_MESSAGE);
                                Password.setText("");
                                PasswordAgain.setText("");
                            }
                            else {
                                b = new connectZJU().reg_manager(ID, password0, name0, telephone0);
                                if (b) {
                                    System.out.print("ע��ɹ�");
                                    JOptionPane.showMessageDialog(null, "ע��ɹ�", "�ɹ�", JOptionPane.INFORMATION_MESSAGE);
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
