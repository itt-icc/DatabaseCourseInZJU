import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddDelreader extends JFrame implements ActionListener{
    private JFrame frame = new JFrame("图书馆管理系统 ");
    private JPanel ManagerPane;
    private ImageIcon background;

    private JTextField ID;
    private JTextField Name;
    private JTextField Dept;
    private JTextField Type;



    public static void main(String[] args) throws SQLException {
        new AddDelreader();
    }

//    public boolean CheckInfo(){
//        boolean b = true;
//
//
//        return b;
//    }

    public AddDelreader() throws SQLException{
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


        JLabel title = new JLabel("读者信息管理:");
        title.setBounds(150, 150, 250, 30);//调整title的大小和位置
        title.setFont(new Font("",Font.BOLD,30));//倾斜+加粗
        ManagerPane.add(title);

        JLabel number = new JLabel("工号:");
        ManagerPane.add(number);
        number.setBounds(160, 230, 54, 15);

        ID=new JTextField();
        ManagerPane.add(ID);
        ID.setBounds(160, 250, 100, 20);
        ID.setColumns(10);

        JLabel xingming = new JLabel("姓名:");
        ManagerPane.add(xingming);
        xingming.setBounds(300, 230, 54, 15);

        Name=new JTextField();
        ManagerPane.add(Name);
        Name.setBounds(300, 250, 100, 20);
        Name.setColumns(10);

        JLabel xueyuan = new JLabel("学院:");
        ManagerPane.add(xueyuan);
        xueyuan.setBounds(450, 230, 54, 15);


        Dept=new JTextField();
        ManagerPane.add(Dept);
        Dept.setBounds(450, 250, 100, 20);
        Dept.setColumns(10);

        JLabel leibie = new JLabel("类别(T/S):");
        ManagerPane.add(leibie);
        leibie.setBounds(600, 230, 54, 15);

        Type=new JTextField();
        ManagerPane.add(Type);
        Type.setBounds(600, 250, 100, 20);
        Type.setColumns(10);



        JButton Addreader= new JButton("确认增加");
        ManagerPane.add(Addreader);
        Addreader.setBounds(300, 350, 90, 30);
        Addreader.addActionListener(this);

        JButton Delreader = new JButton("确认删除");
        ManagerPane.add(Delreader);
        Delreader.setBounds(500, 350, 90, 30);
        Delreader.addActionListener(this);

        JButton justexit= new JButton("退出");
        ManagerPane.add(justexit);
        justexit.setBounds(400, 400, 90, 30);
        justexit.addActionListener(this);




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


        Delreader.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String id = ID.getText();
                String name=Name.getText();
                String dept=Dept.getText();
                String type=Type.getText();

                boolean b = true;
                int result=0;

                // TODO Auto-generated method stub
                if(e.getSource()==Delreader)
                {
                    if(id.equals(""))
                    {
                        JOptionPane.showMessageDialog(null,"请输入完整信息","错误",JOptionPane.INFORMATION_MESSAGE);
                        ID.setText(id);
                        Name.setText(name);
                        Dept.setText(dept);
                        Type.setText(type);
                        b=false;
                    }



                    if((!type.equals("T"))&&(!type.equals("S"))&&b)
                    {
                        JOptionPane.showMessageDialog(null,"请输入正确的类别(T/S)","错误",JOptionPane.INFORMATION_MESSAGE);
                        Type.setText("");
                        b=false;
                    }


                    if(!(new connectZJU().testcno(id))&&b) {
                        b=false;
                        JOptionPane.showMessageDialog(null,"卡号中没有此信息","错误",JOptionPane.INFORMATION_MESSAGE);
                    }


                    if(b)
                    {
                        try{
                            result=new connectZJU().delete_Reader(id);

                            }
                        catch(Exception sqlException)
                        {
                            sqlException.printStackTrace();
                        }
                        if(result==1)
                        {
                            JOptionPane.showMessageDialog(null,"删除成功","提示",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                }
            }
        });


        Addreader.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String id = ID.getText();
                String name=Name.getText();
                String dept=Dept.getText();
                String type=Type.getText();

                boolean b = true;
                int result=0;

                // TODO Auto-generated method stub
                if(e.getSource()==Addreader)
                {
                    if(id.equals("")||name.equals("")||dept.equals("")||type.equals(""))
                    {
                        JOptionPane.showMessageDialog(null,"请输入完整信息","错误",JOptionPane.INFORMATION_MESSAGE);
                        ID.setText(id);
                        Name.setText(name);
                        Dept.setText(dept);
                        Type.setText(type);
                        b=false;

                    }

                    if((!type.equals("T"))&&(!type.equals("S"))&&b)
                    {
                        JOptionPane.showMessageDialog(null,"请输入正确的类别(T/S)","错误",JOptionPane.INFORMATION_MESSAGE);
                        Type.setText("");
                        b=false;
                    }

                    if(new connectZJU().testcno(id)&&b) {
                        b=false;
                        JOptionPane.showMessageDialog(null,"请不要重复添加","错误",JOptionPane.INFORMATION_MESSAGE);
                    }
                    if(b)
                    {
                        try{
                            result=new connectZJU().add_reader(id,name,dept,type);
                        }
                        catch(Exception sqlException)
                        {
                            sqlException.printStackTrace();
                        }
                        if(result==1)
                        {
                            JOptionPane.showMessageDialog(null,"信息添加成功","提示",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
