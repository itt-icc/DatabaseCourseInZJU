import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchBook extends JFrame implements ActionListener{
    private JFrame frame = new JFrame("图书查询");
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
        new SearchBook();
    }


    public SearchBook() throws SQLException{
        //背景
        background = new ImageIcon(this.getClass().getResource("back1.jpg"));
        JLabel label = new JLabel(background);
        label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());


        ManagerPane = (JPanel) frame.getContentPane();
        ManagerPane.setOpaque(false);//非透明
        ManagerPane.setLayout(null);//无布局
        frame.getLayeredPane().setLayout(null);
        frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setBounds(200, 200, background.getIconWidth(),background.getIconHeight());
        frame.setResizable(true);
        frame.setVisible(true);


        JLabel shuhao = new JLabel("书号:");
        ManagerPane.add(shuhao);
        shuhao.setBounds(20, 120, 54, 15);

        bno=new JTextField();
        ManagerPane.add(bno);
        bno.setBounds(20, 140, 50, 20);
        bno.setColumns(10);


        JLabel leibie = new JLabel("类别:");
        ManagerPane.add(leibie);
        leibie.setBounds(120, 120, 54, 15);

        category=new JTextField();
        ManagerPane.add(category);
        category.setBounds(120, 140, 50, 20);
        category.setColumns(10);


        JLabel shuming = new JLabel("书名:");
        ManagerPane.add(shuming);
        shuming.setBounds(220, 120, 54, 15);

        btitie=new JTextField();
        ManagerPane.add(btitie);
        btitie.setBounds(220, 140, 50, 20);
        btitie.setColumns(10);


        JLabel chubanshe = new JLabel("出版社:");
        ManagerPane.add(chubanshe);
        chubanshe.setBounds(320, 120, 54, 15);

        press=new JTextField();
        ManagerPane.add(press);
        press.setBounds(320, 140, 50, 20);
        press.setColumns(10);


        JLabel nianfen = new JLabel("年份:");
        ManagerPane.add(nianfen);
        nianfen.setBounds(420, 120, 54, 15);

        year=new JTextField();
        ManagerPane.add(year);
        year.setBounds(420, 140, 50, 20);
        year.setColumns(10);


        JLabel zuozhe = new JLabel("作者:");
        ManagerPane.add(zuozhe);
        zuozhe.setBounds(520, 120, 54, 15);

        author=new JTextField();
        ManagerPane.add(author);
        author.setBounds(520, 140, 50, 20);
        author.setColumns(10);



        JLabel jiage = new JLabel("价格:");
        ManagerPane.add(jiage);
        jiage.setBounds(620, 120, 54, 15);

        price=new JTextField();
        ManagerPane.add(price);
        price.setBounds(620, 140, 50, 20);
        price.setColumns(10);



        JLabel shuliang = new JLabel("总藏书量:");
        ManagerPane.add(shuliang);
        shuliang.setBounds(720, 120, 54, 15);

        total=new JTextField();
        ManagerPane.add(total);
        total.setBounds(720, 140, 50, 20);
        total.setColumns(10);




        JButton Search= new JButton("开始查询");
        ManagerPane.add(Search);
        Search.setBounds(300, 400, 90, 30);
        Search.addActionListener(this);


        JButton justexit= new JButton("退出");
        ManagerPane.add(justexit);
        justexit.setBounds(500, 400, 90, 30);
        justexit.addActionListener(this);
        justexit.setBackground(Color.YELLOW);



        JTextArea jta=new JTextArea("查询结果",7,30);
        jta.setLineWrap(true);    //设置文本域中的文本为自动换行
        jta.setForeground(Color.BLACK);    //设置组件的背景色

        jta.setFont(new Font("",Font.PLAIN,10));    //修改字体样式
//        jta.setBackground(Color.YELLOW);    //设置按钮背景色
        JScrollPane jsp=new JScrollPane(jta);    //将文本域放入滚动窗口
        jsp.setBounds(20,200,850,180);

        ManagerPane.add(jsp);

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
                String _total=total.getText();

                HashMap< String, String> hMap = new HashMap< String, String>();
                hMap.put("bno",_bno);
                hMap.put("category",_catagory);
                hMap.put("title",_btitle);
                hMap.put("press",_press);
                hMap.put("year",_year);
                hMap.put("author",_author);
                hMap.put("price",_price);
                hMap.put("total",_total);


                for (String key : hMap.keySet()) {
                    String value=hMap.get(key);
                    if(!value.equals("")&&!key.equals("year")&&!key.equals("price")&&!key.equals("total"))
                       {
                           hMap.put(key,"'"+hMap.get(key)+"'");
                       }
//                    System.out.println(key+":"+value);
                }
                List<String> result= new ArrayList<>();
                if(e.getSource()==Search) {
                    boolean b=true;
                    if (_bno.equals("") && _catagory.equals("") && _btitle.equals("") && _press.equals("") && _year.equals("") && _author.equals("") && _price.equals("") && _total.equals("")) {
                        JOptionPane.showMessageDialog(null, "请至少输入一项信息", "错误", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        try {
                                result=new connectZJU().search_Book(hMap);
                                if(!checkresult(result))
                                {
                                    b=false;
                                    JOptionPane.showMessageDialog(null, "未找到书籍", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                                }
                        } catch (Exception sqlException) {
                            sqlException.printStackTrace();
                            b=false;
                        }
                        if(b)
                        {
                            JOptionPane.showMessageDialog(null, "查询完成", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                            String books="bno\tcategory\ttitle\tpress\tyear\tauthor\tprice\ttotal\tstock\n";
                            for (String str : result) {
                                System.out.println(str);
                                books=books+str+"\n";
                            }
                            jta.setText(books);
//                            System.out.println(books);
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
}
