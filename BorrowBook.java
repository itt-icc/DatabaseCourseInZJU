import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BorrowBook extends JFrame implements ActionListener{
    private JFrame frame = new JFrame("图书借阅");
    private JPanel ManagerPane;
    private ImageIcon background;

    private JTextField bno;
    private JTextField cno;
    private JTextField mno;

    public String cur_mno;


//    public static void main(String[] args) throws SQLException {
//        new BorrowBook();
//    }



    public BorrowBook(String current) throws SQLException{

        //背景

        cur_mno=current;


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


        JLabel kahao = new JLabel("借书卡号:");
        ManagerPane.add(kahao);
        kahao.setBounds(20, 120, 54, 15);

        cno=new JTextField();
        ManagerPane.add(cno);
        cno.setBounds(20, 140, 50, 20);
        cno.setColumns(10);


        JLabel shuhao = new JLabel("书的编号:");
        ManagerPane.add(shuhao);
        shuhao.setBounds(120, 120, 54, 15);

        bno=new JTextField();
        ManagerPane.add(bno);
        bno.setBounds(120, 140, 50, 20);
        bno.setColumns(10);



        JLabel mid = new JLabel("你的管理员ID:");
        ManagerPane.add(mid);
        mid.setBounds(220, 120, 100, 15);

        mno=new JTextField();
        ManagerPane.add(mno);
        mno.setBounds(220, 140, 50, 20);
        mno.setColumns(10);


        JButton Search= new JButton("已借书籍");
        ManagerPane.add(Search);
        Search.setBounds(200, 400, 90, 30);
        Search.addActionListener(this);

        JButton borrow = new JButton("确定借阅");
        ManagerPane.add(borrow);
        borrow.setBounds(400, 400, 90, 30);
        borrow.addActionListener(this);


        JButton justexit= new JButton("退出");
        ManagerPane.add(justexit);
        justexit.setBounds(600, 400, 90, 30);
        justexit.addActionListener(this);
        justexit.setBackground(Color.YELLOW);


        JButton huanshu= new JButton("还书");
        ManagerPane.add(huanshu);
        huanshu.setBounds(600, 140, 90, 30);
        huanshu.addActionListener(this);
        huanshu.setBackground(Color.pink);


        JLabel cxjg = new JLabel("查询结果:");
        ManagerPane.add(cxjg);
        cxjg.setBounds(20,180,80,20);



        Object[][]data={{}};
        Object[] columnNames={"bno","category","title","press","year","author","price","total","stock"};
        DefaultTableModel tModel = new DefaultTableModel(data,columnNames );
        JTable table = new JTable(tModel);

        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色

        // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        // 设置行高
        table.setRowHeight(30);

        // 第一列列宽设置为40
        table.getColumnModel().getColumn(0).setPreferredWidth(40);


        JScrollPane jsp2=new JScrollPane(table);    //将文本域放入滚动窗口
        jsp2.setBounds(20,200,850,180);

        ManagerPane.add(jsp2);


        Search.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==Search) {

                    String _cno=cno.getText();
                    String result;
                    if(_cno.equals("")){
                        JOptionPane.showMessageDialog(null, "请输入借书证", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(!new connectZJU().testcno(_cno)) {
                        JOptionPane.showMessageDialog(null, "借书证不存在", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{

                        result=new connectZJU().borrow_Info2(_cno);
                        if(!result.equals("")){
                            JOptionPane.showMessageDialog(null, "查询完成", "温馨提示", JOptionPane.INFORMATION_MESSAGE);

                            String [] biaotou = {"bno","category","title","press","year","author","price","total","stock"};
                            String [][] ob=fix(result);
                            tModel.setDataVector(ob,biaotou);

                            for(int i=0;i<ob.length;i++){
                                for(int j=0;j<ob[i].length;j++){
                                    System.out.print(ob[i][j]+" ");
                                }
                                System.out.println();
                            }
                        }
                        else{JOptionPane.showMessageDialog(null, "没有借阅书籍", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                                tModel.setDataVector(data,columnNames);
                        }

                    }
                }
            }
        });



        borrow.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==borrow) {
                    String _bno=bno.getText();
                    String _cno=cno.getText();
                    String _mno=mno.getText();

                    if(_cno.equals("")) {
                        JOptionPane.showMessageDialog(null, "请输入借书证卡号", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(!new connectZJU().testcno(_cno)) {
                        JOptionPane.showMessageDialog(null, "借书证不存在", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(_bno.equals("")) {
                        JOptionPane.showMessageDialog(null, "请输入要借阅的书号", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(_mno.equals("")) {
                        JOptionPane.showMessageDialog(null, "请输入你的管理员ID", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                    }

                    else if(!_mno.equals(cur_mno)) {
                        JOptionPane.showMessageDialog(null, "管理员ID不正确", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                    }



                    else
                    {
                        String inf = new connectZJU().borrow_Book(_cno,_bno,_mno);
                        if(inf.equals(""))
                            JOptionPane.showMessageDialog(null,"今天已经借过这本书了", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(null, inf, "温馨提示", JOptionPane.INFORMATION_MESSAGE);
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

        huanshu.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==huanshu) {
                    String _bno=bno.getText();
                    String _cno=cno.getText();


                    if(_cno.equals("")) {
                        JOptionPane.showMessageDialog(null, "请输入借书证卡号", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(!new connectZJU().testcno(_cno)) {
                        JOptionPane.showMessageDialog(null, "借书证不存在", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(_bno.equals("")) {
                        JOptionPane.showMessageDialog(null, "请输入要归还的书号", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(!new connectZJU().testbno2(_cno,_bno)) {
                        JOptionPane.showMessageDialog(null, "没有借过这本书", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(new connectZJU().return_Book2(_cno,_bno))
                    {
                        JOptionPane.showMessageDialog(null, "所有相关书籍已经归还", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "归还失败", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    }


    public boolean checkresult(List<String> a)
    {
        for(String b:a)
            return true;
        return false;
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
