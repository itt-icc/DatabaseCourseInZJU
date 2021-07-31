import java.awt.event.*;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;


public class ManagerEnter extends JFrame implements ActionListener
{
    private JFrame frame = new JFrame("ͼ��ݹ���ϵͳ ");
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
        background = new ImageIcon(this.getClass().getResource("back.jpg"));// ����ͼƬ
        JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
        label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������

        ManagerEnterPane = (JPanel) frame.getContentPane(); // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��

        ManagerEnterPane.setOpaque(false);
        ManagerEnterPane.setLayout(null);//�޲���
        frame.getLayeredPane().setLayout(null);

        frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setBounds(500, 200, 450, 300);//�����С
        frame.setResizable(false);//���ɸ��ĳߴ�
        frame.setVisible(true);//����ɼ�



        JLabel ReaderNameLabel = new JLabel("����:");
        ManagerEnterPane.add(ReaderNameLabel);
        ReaderNameLabel.setBounds(70, 37, 61, 37);
        ReaderNameLabel.setFont(new Font("",Font.BOLD,15));


        JLabel ReaderPasswordLabel = new JLabel("����:");
        ManagerEnterPane.add(ReaderPasswordLabel);
        ReaderPasswordLabel.setBounds(70, 121, 61, 24);
        ReaderPasswordLabel.setFont(new Font("",Font.BOLD,15));


        //�����ı������
        ReaderNametextField = new JTextField();
        ManagerEnterPane.add(ReaderNametextField);
        ReaderNametextField.setBounds(156, 39, 178, 37);
        ReaderNametextField.setColumns(10);

        ReaderPasswordtextField = new JPasswordField();
        ManagerEnterPane.add(ReaderPasswordtextField);
        ReaderPasswordtextField.setForeground(Color.BLACK);
        ReaderPasswordtextField.setBounds(156, 117, 178, 37);
        ReaderPasswordtextField.setColumns(10);


        //�ٴ���������ť
        JButton RegisterButton = new JButton("ע��");
        ManagerEnterPane.add(RegisterButton);
        RegisterButton.setBounds(60, 187, 88, 37);
        RegisterButton.setFont(new Font("",Font.BOLD,15));
        RegisterButton.addActionListener(this);


        JLabel ForgetLabel = new JLabel("�������룿");
        ManagerEnterPane.add(ForgetLabel);
        ForgetLabel.setBounds(358, 123, 66, 40);
        ForgetLabel.setForeground(Color.blue);


        JButton LandButton = new JButton("��½");
        ManagerEnterPane.add(LandButton);
        LandButton.setFont(new Font("", Font.BOLD, 15));
        LandButton.setBounds(180, 187, 88, 37);



        JButton justexit = new JButton("�˳�ϵͳ");
        justexit.setBackground(Color.gray);    //���ð�ť����ɫ
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
                    //�õ��û���������
                    String id=ReaderNametextField.getText();
                    String password=ReaderPasswordtextField.getText();
                    if(id.equals("")||password.equals(""))
                    {
                        JOptionPane.showMessageDialog(null,"������������Ϣ","֪ͨ",JOptionPane.INFORMATION_MESSAGE);
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
                            System.out.println("ʧ��");
                        }
//                        1: �ɹ���¼
//                        0��������� -1���˻�������
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
                            JOptionPane.showMessageDialog(null,"�˻������ڣ���ע��","֪ͨ",JOptionPane.INFORMATION_MESSAGE);
                            ReaderNametextField.setText(id);
                            ReaderPasswordtextField.setText(password);
                        }
                        if(b==0){
                            JOptionPane.showMessageDialog(null,"�û������������","֪ͨ",JOptionPane.INFORMATION_MESSAGE);
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


