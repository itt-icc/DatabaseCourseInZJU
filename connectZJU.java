import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class connectZJU {
    static final String JDBC_DRIVE = "com.mysql.jdbc.Driver";
    static final String db_URL = "jdbc:mysql://localhost:3306/ZJU_library";
    //用户名和密码
    static final String user = "root";
    static final String password = "108223";
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs;

    public static void main(String[] args){
        new connectZJU().testMno("123");
    }

    public connectZJU() {//构造函数
        try {
            Class.forName(JDBC_DRIVE);
            System.out.println("Connecting to database......");
            conn = DriverManager.getConnection(db_URL, user, password);
            if (conn != null) {
                System.out.println("Database data connected");
            }
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    //------------以下程序为用户/管理员登录-------------//
    /*
    function: 读者用户验证
    return：boolean
    1：成功登录
    0：用户不存在
     */

    public boolean log_Reader(String cno) {
        boolean b = false;
        try {
            stmt = conn.createStatement();
            System.out.println("Logging......");
            rs = stmt.executeQuery("SELECT cno FROM card");
            while (rs.next() && !b) {
                if (cno.equals(rs.getString("cno"))) {
                    b = true;
                    System.out.println("Welcome!");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return b;
    }

    /*
    function: 管理员用户验证
    return int
    1: 成功登录
    0：密码错误
    -1：账户不存在
    TODO：忘记密码
     */
    public int log_Manager(String number, String code) {
        int b = -1;
        try {
            stmt = conn.createStatement();
            System.out.println("Logging......");
            rs = stmt.executeQuery("SELECT mno, pass FROM manager");
            while (rs.next() && b == -1) {
                if (number.equals(rs.getString("mno")) && code.equals(rs.getString("pass"))) {
                    b = 1;
                    System.out.println("Welcome!");
                } else if (number.equals(rs.getString("mno")) && !code.equals(rs.getString("pass"))) {
                    b = 0;
                    System.out.println("Password Incorrect!");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        if(b==1){
            System.out.println("成功登录");
        }
        else  if(b==0){
            System.out.println("密码错误！");

        }
        else{
            System.out.println("账户不存在！");
        }
        return b;
    }

    /*
    function: 注册读者用户
    return: boolean
    true:注册成功
    false：注册失败
    TODO：UI 控制输入类型为 Teacher//Student
    TODO：注册错误看看能不能用catch里面的返回相应的错误类型（比如主键冲突）
     */
    public boolean reg_Reader(String cno, String name, String department, String type) {
        boolean b = false;
        try {
            stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO card(cno, name, department, type) VALUES (?,?,?,?)");
            ps.setString(1, cno);
            ps.setString(2, name);
            ps.setString(3, department);
            ps.setString(4, type);
            int i = ps.executeUpdate();
            if (i == 1) {
                b = true;
                System.out.println("Succeed!");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return b;
    }


    /*
function: 注册管理员
return: boolean
true:注册成功
false：注册失败

TODO：注册错误看看能不能用catch里面的返回相应的错误类型（比如主键冲突）
 */
    public boolean reg_manager(String mno, String pass, String mname, String contact) {
        boolean b = false;
        try {
            stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO manager(mno, pass, mname, contact) VALUES (?,?,?,?)");
            ps.setString(1, mno);
            ps.setString(2, pass);
            ps.setString(3, mname);
            ps.setString(4, contact);
            int i = ps.executeUpdate();
            if (i == 1) {
                b = true;
                System.out.println("manager register Succeed!");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return b;
    }

    public int add_reader(String cno ,String name, String department, String type) {
        int b = 0;
        try {
            stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO card(cno, name, department, type) VALUES (?,?,?,?)");

            ps.setString(1, cno);
            ps.setString(2, name);
            ps.setString(3, department);
            ps.setString(4, type);
            System.out.println(ps);
            int i = ps.executeUpdate();
            if (i == 1) {
                b = 1;
                System.out.println("card register Succeed!");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return b;
    }

    //管理员用户验证
    public boolean testMno(String ID) {
        boolean b = false;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM manager");
            while (rs.next() && b == false) {
                String _id=rs.getString(1);
//                System.out.println(_id);
                if (ID.equals(_id))
                {
                    b = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }


    //图书验证
    public boolean testbno(String ID) {
        boolean b = false;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM book");
            while (rs.next() && b == false) {
                String _id=rs.getString(1);
//                System.out.println(_id);
                if (ID.equals(_id))
                {
                    b = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    //借书卡验证
    public boolean testcno(String ID) {
        boolean b = false;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM card");
            while (rs.next() && b == false) {
                String _id=rs.getString(1);
//                System.out.println(_id);
                if (ID.equals(_id))
                {
                    b = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }




    /*
    function: 删除用户
    return int
    1:删除成功
    其他：失败
     */

    public int delete_Reader(String cno) {
        int i = 0;
        try {
            stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM card WHERE cno = ?");
            ps.setString(1, cno);
            i = ps.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return i;
    }

    //------------以下为图书入库程序-------------//
    /*
    function：单本图书入库
    return: int
        1:成功入库
        else:失败
    TODO:把数据库里面书的格式设置为varchar 或者更长；UI控制数据float只能有两位
     */
    public int add_Book(String bno, String category, String name, String press, int year, String author, float price, int number) {
        int i = 0;
        try {
            stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO book(bno, category, title, press, year, author, price, total, stock) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, bno);
            ps.setString(2, category);
            ps.setString(3, name);
            ps.setString(4, press);
            ps.setInt(5, year);
            ps.setString(6, author);
            ps.setFloat(7, price);
            ps.setInt(8, number);
            ps.setInt(9, number);
            i = ps.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return i;
    }
    /*
    function：批量入库程序
    output：int 入库成功且统计的数量
    input: string 文件（txt）的路径
    用批量处理指令executeBatch来更新
     */
    public int add_batch_Book1(String address){
        int i = 0;
        try{
            BufferedReader in = new BufferedReader(new FileReader(address));
            System.out.println("file read success!");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO book VALUES (?,?,?,?,?,?,?,?,?)");
            String str;
            while ((str = in.readLine()) != null){
                String str1 = StringUtils.strip(str,"()");
                String[] order = str1.split(",");
                int num = order.length;
                if (num != 8){
                    System.out.println("Format Error: "+str);
                }
                else{
                    ps.setString(1,order[0]);
                    ps.setString(2, order[1]);
                    ps.setString(3, order[2]);
                    ps.setString(4, order[3]);
                    ps.setInt(5, Integer.parseInt(order[4]));
                    ps.setString(6, order[5]);
                    ps.setFloat(7, Float.parseFloat(order[6]));
                    ps.setInt(8, Integer.parseInt(order[7]));
                    ps.setInt(9, Integer.parseInt(order[7]));
                    ps.addBatch();
                }
            }
            int[] result = ps.executeBatch();//返回包含0，1的数组，如果是1 则是插入成功，若是0则是success_no_info或fail
            for (int value : result) {
                i = i + value;
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int add_batch_Book(String[] Batch){
        int i = 0;
        try{
            System.out.println("file read success!");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO book VALUES (?,?,?,?,?,?,?,?,?)");
            for (String str : Batch){
                System.out.println(str);
                String str1 = StringUtils.strip(str,"()");
                String[] order = str1.split(",");
                int num = order.length;
                if (num != 8){
                    System.out.println("Format Error: "+str);
                }
                else{
                    ps.setString(1,order[0]);
                    ps.setString(2, order[1]);
                    ps.setString(3, order[2]);
                    ps.setString(4, order[3]);
                    ps.setInt(5, Integer.parseInt(order[4]));
                    ps.setString(6, order[5]);
                    ps.setFloat(7, Float.parseFloat(order[6]));
                    ps.setInt(8, Integer.parseInt(order[7]));
                    ps.setInt(9, Integer.parseInt(order[7]));
                    ps.addBatch();
                }
            }
            int[] result = ps.executeBatch();//返回包含0，1的数组，如果是1 则是插入成功，若是0则是success_no_info或fail
            for (int value : result) {
                i = i + value;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }



    //-----------以下功能用于查询-------------//
    /*
    function: 查询书的类别，书名，出版社，年份，作者，价格
    input:hash-map格式["category":"", "name" :"", "press" :"", "year" :"range","author" :"","price":"range"]
    year:"year between min and max"==>同理for price，可以通过设置勾选项的value来解决
    output:List<String> 包含图书信息
     */
    public List<String> search_Book(HashMap<String,String> info){
        int i = 0;
        String result = "";
        List<String> output = new ArrayList<>();
        boolean head = true;
        StringBuilder sql = new StringBuilder("SELECT * FROM book WHERE ");
        /*分析指令*/
        Set<String> order = info.keySet();
        Iterator<String> itr = order.iterator();
        while (itr.hasNext()){
            String key = itr.next();//get key
            String value = info.get(key); //get the value
            if (!(value.equals("")) && !head){
                sql.append(" and ").append(key+"="+value);
            }
            else if (!value.equals("") && head){
                sql.append(key+"="+value);
                head = false;
            }
        }
        /*查看对应的指令*/
        System.out.println(sql.toString());
        /*查询操作*/
        try {
//            output.add();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());
            while (rs.next() && i<50){
                result = result+rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)
                        +"\t"+rs.getInt(5)+"\t"+rs.getString(6)+"\t"+rs.getFloat(7)
                        +"\t"+rs.getInt(8)+"\t"+rs.getInt(9);
                output.add(result);
                result = "";
                i = i+1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }



    public String search_Book2(HashMap<String,String> info){
        int i = 0;
        String result = "";
        boolean head = true;
        StringBuilder sql = new StringBuilder("SELECT * FROM book WHERE ");
        /*分析指令*/
        Set<String> order = info.keySet();
        Iterator<String> itr = order.iterator();
        while (itr.hasNext()){
            String key = itr.next();//get key
            String value = info.get(key); //get the value
            if (!(value.equals("")) && !head){
                if(key.equals("price")||key.equals("year"))
                {
                    sql.append(" and ").append(key+">="+value);
                }
                else if(key.equals("pricemax")||key.equals("yearmax"))
                {
                    if(key.equals("pricemax"))
                    sql.append(" and ").append("price"+"<="+value);
                    else
                        sql.append(" and ").append("year"+"<="+value);
                }
                else
                {
                    sql.append(" and ").append(key+"="+value);
                }
            }
            else if (!value.equals("") && head){
                if(key.equals("price")||key.equals("year"))
                {
                    sql.append(key+">="+value);
                }
                else if(key.equals("pricemax")||key.equals("yearmax"))
                {

                    if(key.equals("pricemax"))
                        sql.append("price"+"<="+value);
                    else
                        sql.append("year"+"<="+value);

                }else
                {sql.append(key+"="+value);}
                head = false;
            }
        }
        /*查看对应的指令*/
        System.out.println(sql.toString());
        /*查询操作*/
        try {
            String j="";
//            output.add();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());
            while (rs.next() && i<50){
                result = result+j+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)
                        +","+rs.getInt(5)+","+rs.getString(6)+","+rs.getFloat(7)
                        +","+rs.getInt(8)+","+rs.getInt(9);
                j=";";
                i = i+1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    //------------以下功能用于借书--------------//
    /*
    function:从 查看目前某用户借书的情况
    input：string [card number]
    output: List<String> 已借图书结果 空则为并未借书
     */
    public List<String>borrow_Info(String cno){
        String result = "";
        List<String> output = new ArrayList<>(); //用于存放借书信息
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT bno,category,title,press,year,author,price,total,stock FROM borrow NATURAL JOIN book WHERE cno = ?");
            ps.setString(1,cno);//TODO 可以用字符串format替代，运行效率更快点，之后提升再改
            rs = ps.executeQuery();
            while (rs.next()){


                result = result+rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)
                        +"\t"+rs.getInt(5)+"\t"+rs.getString(6)+"\t"+rs.getFloat(7)
                        +"\t"+rs.getInt(8)+"\t"+rs.getInt(9);

                output.add(result);
                result = "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }


    public String borrow_Info2(String cno){
        String result = "";

        try{
            PreparedStatement ps = conn.prepareStatement("SELECT bno,category,title,press,year,author,price,total,stock FROM borrow NATURAL JOIN book WHERE cno = ?");
            ps.setString(1,cno);//TODO 可以用字符串format替代，运行效率更快点，之后提升再改
            rs = ps.executeQuery();
            String j="";
            while (rs.next()){

                result = result+j+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)
                        +","+rs.getInt(5)+","+rs.getString(6)+","+rs.getFloat(7)
                        +","+rs.getInt(8)+","+rs.getInt(9);
                        j=";";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    //检查是否已经借了这本书
    public boolean testbno2(String cno,String bno) {
        boolean b = false;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT bno,category,title,press,year,author,price,total,stock FROM borrow NATURAL JOIN book WHERE cno = ? and bno=?");
            ps.setString(1,cno);
            ps.setString(2,bno);
            rs = ps.executeQuery();
            while (rs.next() && b == false) {
                String _b=rs.getString(1);
//                System.out.println(_id);
                if (bno.equals(_b))
                {
                    b = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }



    /*
    function：查找该书是否存在
    input：String bno String table
    output: boolean
     */
    public boolean find_Book(String bno,String table){
        try{
            stmt = conn.createStatement();
            String sql = String.format("SELECT bno FROM %s",table);
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                if (bno.equals(rs.getString("bno"))){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /*
    function: 登录后借书
    input: String cno--由之前的登录界面提供, String bno;
    output: String [运行结果]
     */
    public String borrow_Book(String cno, String bno,String mno){
        String result = "";
        int store = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            if (find_Book(bno,"book")){
                System.out.println("存在这本书");
                //说明存在该本书
                stmt = conn.createStatement();
                String stock = String.format("SELECT stock FROM book WHERE bno = '%s'",bno);
                rs = stmt.executeQuery(stock);
                while(rs.next()){
                    store = rs.getInt(1);//这里面也只有一行，一个结果
                }
                if (store!=0){
                    System.out.println("库存为"+store+"本");
                    //说明有库存
                    store = store-1;
                    String update = String.format("UPDATE book SET stock = %d WHERE bno = '%s'",store,bno);
                    //存储借书信息
                    Calendar now = Calendar.getInstance();
                    String borrow_date = formatter.format(now.getTime());
                    now.add(Calendar.MONTH,3);//一个人最多借书三个月
                    String return_date = formatter.format(now.getTime());

                    String insert = String.format("INSERT INTO borrow VALUES ('%s','%s','%s','%s','%s')",cno,bno,borrow_date,return_date,mno);
                    //库存减一+添加借书信息
                    System.out.println(insert);
                    stmt.executeUpdate(insert);
                    result = "借书成功";
                    System.out.println("借书成功！");
                    stmt.executeUpdate(update);

                }
                else{
                    //没有库存，找最近有的图书
                    String time="";
                    String latest = String.format("SELECT MIN(return_date) FROM borrow WHERE bno = '%s'",bno);
                    rs = stmt.executeQuery(latest);
                    while (rs.next()){
                        time = formatter.format(rs.getDate(1));
                    }
                    result="已无库存，最近归还时间："+time;
                    System.out.println(result);

                }

            }
            else {
                result = "无该书!";
                System.out.println("没有这本书");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /*------------以下是还书功能------------*/
    /*
    function: 还书号为bno的书籍
    input: String bno;
    output: String 状态信息
    TODO:可以在borrow table里面多加一项为 是否已还书？
    TODO：需要确定sql datetime的set是否可以用string type 可以√
     */
    public String return_Book(String cno,String bno,String borrow_date){
        String mess="";
        try{
            if (find_Book(bno,"borrow")){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Calendar now = Calendar.getInstance();
                String return_date = formatter.format(now.getTime());
                String return_b = String.format("UPDATE borrow SET return_date = '%s' WHERE bno = '%s' and cno = '%s' and borrow_date = '%s'",return_date,bno,cno,borrow_date);
                String update = String.format("UPDATE book SET stock = stock+1 WHERE bno = '%s'",bno);
                stmt = conn.createStatement();
                stmt.executeUpdate(return_b);
                stmt.executeUpdate(update);
                mess = "还书成功!";
                System.out.println(mess);
            }
            else {
                mess =  "未借该书，信息错误！";System.out.println(mess);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mess;
    }


    public boolean return_Book2(String cno,String bno){
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM borrow  WHERE cno = ? and bno=?");
            ps.setString(1,cno);//TODO 可以用字符串format替代，运行效率更快点，之后提升再改
            ps.setString(2,bno);
            rs = ps.executeQuery();
            while (rs.next()){
                String _bno = rs.getString(2);
                String _cno = rs.getString(1);
                String borrow_date=rs.getString(3);
                String return_b = String.format("delete from borrow  WHERE bno = '%s' and cno = '%s' and borrow_date = '%s'",_bno,_cno,borrow_date);
                String update = String.format("UPDATE book SET stock = stock+1 WHERE bno = '%s'",_bno);
                stmt = conn.createStatement();
                stmt.executeUpdate(return_b);
                stmt.executeUpdate(update);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

// {
//     connectZJU cnnt=new connectZJU();
//     /*测试插入一个书本*/
//     //cnnt.add_Book("01","CS","DS0","张孟麒的ipad",2021,"张孟麒",10.0f,10);
////     cnnt.add_Book("02","CS","DS0","张孟麒的ipad",2021,"张孟麒",10.0f,10);
////     cnnt.add_Book("03","CS","DS1","张孟麒的ipad",2021,"张孟麒",10.0f,10);
////     cnnt.add_Book("04","CS","DS2","张孟麒的ipad",2021,"张孟麒",10.0f,10);
////     cnnt.add_Book("05","CS","DS3","张孟麒的ipad",2021,"张孟麒",10.0f,10);
////     cnnt.add_Book("010","CS","DS4","张孟麒的ipad",2021,"张孟麒",10.0f,1);
////     boolean flag=cnnt.find_Book("01","book");
////     System.out.println(flag);
//     /*测试批量插入*/
////     cnnt.add_batch_Book("C:\\Users\\ZYC\\Desktop\\ZJU_DB\\src\\batch.txt");
//     /*注册管理员*/
////     cnnt.reg_manager("000001","108223","zmq","173");
////     cnnt.reg_manager("02","Zmq108223","zyc","188");
//     /*管理员登录*/
////     cnnt.log_Manager("01","001");
////     cnnt.log_Manager("02","Zmq108223");
////     cnnt.log_Manager("03","188");
//     /*读者注册*/
////     cnnt.reg_Reader("001","xyc","CS","S");
////     cnnt.reg_Reader("002","zyc","CS","S");
////     cnnt.reg_Reader("003","zmq","CS","S");
//     /*借书*/
//     //此处可能还需要修改一些查询的方式
////     cnnt.borrow_Book("002","010","000001");
////     cnnt.borrow_Book("002","01","000001");
////     cnnt.borrow_Book("002","02","000001");
//     /*还书*/
////     cnnt.return_Book("002","011","000001");
////     cnnt.return_Book("002","010","000001");
//     /*查询借书信息*/
////     cnnt.borrow_Info("002");
//     /*搜一本书待测试*/
// 	}
}
