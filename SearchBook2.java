import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchBook2 extends JFrame implements ActionListener{
    private JFrame frame = new JFrame("ͼ���ѯ");
    private JPanel ManagerPane;
    private ImageIcon background;

    private JTextField bno;
    private JTextField category;
    private JTextField btitie;
    private JTextField press;
    private JTextField year;
    private JTextField yearmax;
    private JTextField author;
    private JTextField price;
    private JTextField pricemax;
    private JTextField total;
    private JTextField stock;



    public static void main(String[] args) throws SQLException {
        new SearchBook2();
    }


    public SearchBook2() throws SQLException{
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


        JLabel nianfen = new JLabel("���min:");
        ManagerPane.add(nianfen);
        nianfen.setBounds(420, 120, 54, 15);

        year=new JTextField();
        ManagerPane.add(year);
        year.setBounds(420, 140, 50, 20);
        year.setColumns(10);



        JLabel nianfenmax = new JLabel("���max:");
        ManagerPane.add(nianfenmax);
        nianfenmax.setBounds(480, 120, 54, 15);

        yearmax=new JTextField();
        ManagerPane.add(yearmax);
        yearmax.setBounds(480, 140, 50, 20);
        yearmax.setColumns(10);


        JLabel zuozhe = new JLabel("����:");
        ManagerPane.add(zuozhe);
        zuozhe.setBounds(580, 120, 54, 15);

        author=new JTextField();
        ManagerPane.add(author);
        author.setBounds(580, 140, 50, 20);
        author.setColumns(10);


        JLabel jiagemax = new JLabel("�۸�max:");
        ManagerPane.add(jiagemax);
        jiagemax.setBounds(750, 120, 54, 15);

        pricemax=new JTextField();
        ManagerPane.add(pricemax);
        pricemax.setBounds(750, 140, 50, 20);
        pricemax.setColumns(10);



        JLabel jiage = new JLabel("�۸�min:");
        ManagerPane.add(jiage);
        jiage.setBounds(690, 120, 54, 15);

        price=new JTextField();
        ManagerPane.add(price);
        price.setBounds(690, 140, 50, 20);
        price.setColumns(10);



        JLabel shuliang = new JLabel("�ܲ�����:");
        ManagerPane.add(shuliang);
        shuliang.setBounds(820, 120, 54, 15);

        total=new JTextField();
        ManagerPane.add(total);
        total.setBounds(820, 140, 50, 20);
        total.setColumns(10);

        JLabel cxjg = new JLabel("��ѯ���:");
        ManagerPane.add(cxjg);
        cxjg.setBounds(20,180,80,20);


        JButton Search= new JButton("��ʼ��ѯ");
        ManagerPane.add(Search);
        Search.setBounds(300, 400, 90, 30);
        Search.addActionListener(this);


        JButton justexit= new JButton("�˳�");
        ManagerPane.add(justexit);
        justexit.setBounds(500, 400, 90, 30);
        justexit.addActionListener(this);
        justexit.setBackground(Color.YELLOW);


        Object[][]data={{}};
        Object[] columnNames={"bno","category","title","press","year","author","price","total","stock"};
        DefaultTableModel tModel = new DefaultTableModel(data,columnNames );
        RowSorter sorter = new TableRowSorter(tModel);

        JTable table = new JTable(tModel);
        table.setRowSorter(sorter);

        // ���ñ��������ɫ
        table.setForeground(Color.BLACK);                   // ������ɫ
        table.setFont(new Font(null, Font.PLAIN, 14));      // ������ʽ
        table.setSelectionForeground(Color.DARK_GRAY);      // ѡ�к�������ɫ
        table.setSelectionBackground(Color.LIGHT_GRAY);     // ѡ�к����屳��
        table.setGridColor(Color.GRAY);                     // ������ɫ

        // ���ñ�ͷ441825636@qq.com
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // ���ñ�ͷ����������ʽ
        table.getTableHeader().setForeground(Color.RED);                // ���ñ�ͷ����������ɫ
        table.getTableHeader().setResizingAllowed(false);               // ���ò������ֶ��ı��п�
        table.getTableHeader().setReorderingAllowed(false);             // ���ò������϶������������
        // �����и�
        table.setRowHeight(30);

        // ��һ���п�����Ϊ40
        table.getColumnModel().getColumn(0).setPreferredWidth(40);


        JScrollPane jsp2=new JScrollPane(table);    //���ı�������������
        jsp2.setBounds(20,200,850,180);

        ManagerPane.add(jsp2);



        Search.addActionListener(new ActionListener()
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
                String _yearmax=yearmax.getText();
                String _pricemax=pricemax.getText();
                String _total=total.getText();


                HashMap< String, String> hMap = new HashMap< String, String>();

                hMap.put("bno",_bno);
                hMap.put("category",_catagory);
                hMap.put("title",_btitle);
                hMap.put("press",_press);
                hMap.put("year",_year);
                hMap.put("author",_author);
                hMap.put("price",_price);
                hMap.put("pricemax",_pricemax);
                hMap.put("yearmax",_yearmax);
                hMap.put("total",_total);


                for (String key : hMap.keySet()) {
                    String value=hMap.get(key);
                    if(!value.equals("")&&!key.equals("year")&&!key.equals("price")&&!key.equals("total")&&!key.equals("yearmax")&&!key.equals("pricemax"))
                       {
                           hMap.put(key,"'"+hMap.get(key)+"'");
                       }
                }

                if(e.getSource()==Search) {
                    if (_bno.equals("") && _catagory.equals("") && _btitle.equals("") && _press.equals("") && _year.equals("") && _author.equals("") && _price.equals("") && _total.equals("")&&_pricemax.equals("")&&_yearmax.equals("")) {
                        JOptionPane.showMessageDialog(null, "����������һ����Ϣ", "����", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        try {
                             String result2=new connectZJU().search_Book2(hMap);
                             if(result2.equals(""))
                             {
                                 JOptionPane.showMessageDialog(null, "δ�ҵ��鼮", "��ܰ��ʾ", JOptionPane.INFORMATION_MESSAGE);
                                 tModel.setDataVector(data,columnNames);
                             }
                             else{
                                 JOptionPane.showMessageDialog(null, "��ѯ���", "��ܰ��ʾ", JOptionPane.INFORMATION_MESSAGE);
                                 String [] biaotou = {"bno","category","title","press","year","author","price","total","stock"};
                                 String [][] ob=fix(result2);
                                 tModel.setDataVector(ob,biaotou);
                                 for(int i=0;i<ob.length;i++){
                                     for(int j=0;j<ob[i].length;j++){
                                         System.out.print(ob[i][j]+" ");
                                     }
                                     System.out.println();
                                 }
                             }
                        } catch (Exception sqlException) {
                              sqlException.printStackTrace();
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
    }

    public boolean checkresult(List<String> a)
    {
        for(String b:a)
                return true;
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    }

    public String[][] fix(String former)
    {
        String[] result=former.split(";");
        String[][] d=new String[result.length][];
        for(int i=0;i<result.length;i++){
            String[] second=result[i].split(",");
            d[i]=new String[second.length];
            for(int j=0;j<second.length;j++){
                d[i][j]=second[j];
            }
        }
        return d;
    }
}
