import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Welcome implements ActionListener{
    private JFrame frame = new JFrame("ͼ��ݹ���ϵͳ ");
    private JPanel imagePanel;
    private ImageIcon background;

    public Welcome() {
        background = new ImageIcon(this.getClass().getResource("back.jpg"));// ����ͼƬ
        JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
        label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
        imagePanel = (JPanel) frame.getContentPane(); // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
        frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����

        imagePanel.setOpaque(false);
        imagePanel.setLayout(null);//�޲���
        frame.getLayeredPane().setLayout(null);

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setBounds(500, 200, 450, 300);//�����С
        frame.setResizable(false);//���ɸ��ĳߴ�
        frame.setVisible(true);//����ɼ�

        JLabel title = new JLabel("ZJUͼ��ݹ���ϵͳ");
        title.setBounds(120, 20, 300, 20);//����title�Ĵ�С��λ��
        title.setFont(new Font("����",Font.ITALIC+Font.BOLD,20));//��б+�Ӵ�
        imagePanel.add(title);

        JButton ManagerEnterButton = new JButton("����Ա���");
        ManagerEnterButton.setBackground(Color.YELLOW);    //���ð�ť����ɫ
        imagePanel.add(ManagerEnterButton);
        ManagerEnterButton.setBounds(124, 66, 184, 40);
        ManagerEnterButton.addActionListener(this);


//        JButton ReaderEnterButton = new JButton("�������");
//        ReaderEnterButton.setBackground(Color.pink);    //���ð�ť����ɫ
//        imagePanel.add(ReaderEnterButton);
//        ReaderEnterButton.setBounds(124, 130, 184, 40);
//        ReaderEnterButton.addActionListener(this);


        JButton justexit = new JButton("�˳�ϵͳ");
        justexit.setBackground(Color.gray);    //���ð�ť����ɫ
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
//                    JOptionPane.showMessageDialog(null,"�뷵�ع���Ա���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
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



