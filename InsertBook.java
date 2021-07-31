import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class InsertBook extends JFrame implements ActionListener{
    private JFrame frame = new JFrame("ͼ��¼��");
    private JPanel ManagerPane;
    private ImageIcon background;

    private JTextField bno;
    private JTextField category;
    private JTextField btitie;
    private JTextField press;
    private JTextField year;
    private JTextField author;
    private JTextField price;
    private JTextField total;
    private JTextField stock;



    public static void main(String[] args) throws SQLException {
        new InsertBook();
    }

    public InsertBook() throws SQLException{
        //����
        background = new ImageIcon(this.getClass().getResource("back1.jpg"));
        JLabel label = new JLabel(background);
        label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());


        ManagerPane = (JPanel) frame.getContentPane();
        ManagerPane.setOpaque(false);//��͸��
        ManagerPane.setLayout(null);//�޲���
        frame.getLayeredPane().setLayout(null);
        frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setBounds(200, 200, background.getIconWidth(),background.getIconHeight());
        frame.setResizable(true);
        frame.setVisible(true);


        JLabel shuhao = new JLabel("���:");
        ManagerPane.add(shuhao);
        shuhao.setBounds(20, 120, 54, 15);

        bno=new JTextField();
        ManagerPane.add(bno);
        bno.setBounds(20, 140, 50, 20);
        bno.setColumns(10);


        JLabel leibie = new JLabel("���:");
        ManagerPane.add(leibie);
        leibie.setBounds(120, 120, 54, 15);

        category=new JTextField();
        ManagerPane.add(category);
        category.setBounds(120, 140, 50, 20);
        category.setColumns(10);


        JLabel shuming = new JLabel("����:");
        ManagerPane.add(shuming);
        shuming.setBounds(220, 120, 54, 15);

        btitie=new JTextField();
        ManagerPane.add(btitie);
        btitie.setBounds(220, 140, 50, 20);
        btitie.setColumns(10);


        JLabel chubanshe = new JLabel("������:");
        ManagerPane.add(chubanshe);
        chubanshe.setBounds(320, 120, 54, 15);

        press=new JTextField();
        ManagerPane.add(press);
        press.setBounds(320, 140, 50, 20);
        press.setColumns(10);


        JLabel nianfen = new JLabel("���:");
        ManagerPane.add(nianfen);
        nianfen.setBounds(420, 120, 54, 15);

        year=new JTextField();
        ManagerPane.add(year);
        year.setBounds(420, 140, 50, 20);
        year.setColumns(10);




        JLabel zuozhe = new JLabel("����:");
        ManagerPane.add(zuozhe);
        zuozhe.setBounds(520, 120, 54, 15);

        author=new JTextField();
        ManagerPane.add(author);
        author.setBounds(520, 140, 50, 20);
        author.setColumns(10);



        JLabel jiage = new JLabel("�۸�:");
        ManagerPane.add(jiage);
        jiage.setBounds(620, 120, 54, 15);

        price=new JTextField();
        ManagerPane.add(price);
        price.setBounds(620, 140, 50, 20);
        price.setColumns(10);




        JLabel shuliang = new JLabel("�ܲ�����:");
        ManagerPane.add(shuliang);
        shuliang.setBounds(720, 120, 54, 15);

        total=new JTextField();
        ManagerPane.add(total);
        total.setBounds(720, 140, 50, 20);
        total.setColumns(10);




//        JLabel kucun = new JLabel("���:");
//        ManagerPane.add(kucun);
//        kucun.setBounds(820, 120, 54, 15);
//
//        stock=new JTextField();
//        ManagerPane.add(stock);
//        stock.setBounds(820, 140, 50, 20);
//        stock.setColumns(10);






        JButton AddOne= new JButton("��������");
        ManagerPane.add(AddOne);
        AddOne.setBounds(300, 350, 90, 30);
        AddOne.addActionListener(this);



        JButton justexit= new JButton("�˳�");
        ManagerPane.add(justexit);
        justexit.setBounds(400, 400, 90, 30);
        justexit.addActionListener(this);

        JButton AddMul = new JButton("��������");
        ManagerPane.add(AddMul);
        AddMul.setBounds(500, 350, 90, 30);
        AddMul.addActionListener(this);

        JTextArea jta=new JTextArea("����������",7,30);
        jta.setLineWrap(true);    //�����ı����е��ı�Ϊ�Զ�����
        jta.setForeground(Color.BLACK);    //��������ı���ɫ

        jta.setFont(new Font("",Font.BOLD,16));    //�޸�������ʽ
//        jta.setBackground(Color.YELLOW);    //���ð�ť����ɫ
        JScrollPane jsp=new JScrollPane(jta);    //���ı�������������
        Dimension size=jta.getPreferredSize();    //����ı������ѡ��С
        jsp.setBounds(20,200,800,100);

        ManagerPane.add(jsp);


        AddOne.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                String _bno=bno.getText();
                String _catagory=category.getText();
                String _btitle=btitie.getText();
                String _press=press.getText();
                String _year=year.getText();
                String _author=author.getText();
                String _price=price.getText();
                String _total=total.getText();


                    if(e.getSource()==AddOne) {
                        int result = 0;
                        if (_bno.equals("") || _catagory.equals("") || _btitle.equals("") || _press.equals("") || _year.equals("") || _author.equals("") || _price.equals("") || _total.equals("")) {
                            JOptionPane.showMessageDialog(null, "������������Ϣ", "����", JOptionPane.INFORMATION_MESSAGE);
                        } else if (new connectZJU().testbno(_bno)) {
                            JOptionPane.showMessageDialog(null, "�Ȿ���Ѿ�����", "����", JOptionPane.INFORMATION_MESSAGE);
                            bno.setText("");
                        } else {
                            try {
                                int int_year=Integer.parseInt(_year);
                                float f_price=Float.parseFloat(_price);
                                int int_total = Integer.parseInt(_total);
                                result = new connectZJU().add_Book(_bno, _catagory, _btitle, _press, int_year, _author, f_price, int_total);
                            } catch (Exception sqlException) {
                                sqlException.printStackTrace();
                            }
                            if (result == 1) {
                                JOptionPane.showMessageDialog(null, "�������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
            }
        });




        justexit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==justexit) {
                    frame.dispose();
                }
            }
        });

        AddMul.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==AddMul) {
                    String batchbook = jta.getText();
                    String [] Batch=batchbook.split("\n");
                    new connectZJU().add_batch_Book(Batch);
                }
            }
        });




    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
