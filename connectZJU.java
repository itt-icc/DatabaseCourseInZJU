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
    //�û���������
    static final String user = "root";
    static final String password = "108223";
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs;

    public static void main(String[] args){
        new connectZJU().testMno("123");
    }

    public connectZJU() {//���캯��
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

    //------------���³���Ϊ�û�/����Ա��¼-------------//
    /*
    function: �����û���֤
    return��boolean
    1���ɹ���¼
    0���û�������
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
    function: ����Ա�û���֤
    return int
    1: �ɹ���¼
    0���������
    -1���˻�������
    TODO����������
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
            System.out.println("�ɹ���¼");
        }
        else  if(b==0){
            System.out.println("�������");

        }
        else{
            System.out.println("�˻������ڣ�");
        }
        return b;
    }

    /*
    function: ע������û�
    return: boolean
    true:ע��ɹ�
    false��ע��ʧ��
    TODO��UI ������������Ϊ Teacher//Student
    TODO��ע����󿴿��ܲ�����catch����ķ�����Ӧ�Ĵ������ͣ�����������ͻ��
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
function: ע�����Ա
return: boolean
true:ע��ɹ�
false��ע��ʧ��

TODO��ע����󿴿��ܲ�����catch����ķ�����Ӧ�Ĵ������ͣ�����������ͻ��
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

    //����Ա�û���֤
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


    //ͼ����֤
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

    //���鿨��֤
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
    function: ɾ���û�
    return int
    1:ɾ���ɹ�
    ������ʧ��
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

    //------------����Ϊͼ��������-------------//
    /*
    function������ͼ�����
    return: int
        1:�ɹ����
        else:ʧ��
    TODO:�����ݿ�������ĸ�ʽ����Ϊvarchar ���߸�����UI��������floatֻ������λ
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
    function������������
    output��int ���ɹ���ͳ�Ƶ�����
    input: string �ļ���txt����·��
    ����������ָ��executeBatch������
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
            int[] result = ps.executeBatch();//���ذ���0��1�����飬�����1 ���ǲ���ɹ�������0����success_no_info��fail
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
            int[] result = ps.executeBatch();//���ذ���0��1�����飬�����1 ���ǲ���ɹ�������0����success_no_info��fail
            for (int value : result) {
                i = i + value;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }



    //-----------���¹������ڲ�ѯ-------------//
    /*
    function: ��ѯ�����������������磬��ݣ����ߣ��۸�
    input:hash-map��ʽ["category":"", "name" :"", "press" :"", "year" :"range","author" :"","price":"range"]
    year:"year between min and max"==>ͬ��for price������ͨ�����ù�ѡ���value�����
    output:List<String> ����ͼ����Ϣ
     */
    public List<String> search_Book(HashMap<String,String> info){
        int i = 0;
        String result = "";
        List<String> output = new ArrayList<>();
        boolean head = true;
        StringBuilder sql = new StringBuilder("SELECT * FROM book WHERE ");
        /*����ָ��*/
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
        /*�鿴��Ӧ��ָ��*/
        System.out.println(sql.toString());
        /*��ѯ����*/
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
        /*����ָ��*/
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
        /*�鿴��Ӧ��ָ��*/
        System.out.println(sql.toString());
        /*��ѯ����*/
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


    //------------���¹������ڽ���--------------//
    /*
    function:�� �鿴Ŀǰĳ�û���������
    input��string [card number]
    output: List<String> �ѽ�ͼ���� ����Ϊ��δ����
     */
    public List<String>borrow_Info(String cno){
        String result = "";
        List<String> output = new ArrayList<>(); //���ڴ�Ž�����Ϣ
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT bno,category,title,press,year,author,price,total,stock FROM borrow NATURAL JOIN book WHERE cno = ?");
            ps.setString(1,cno);//TODO �������ַ���format���������Ч�ʸ���㣬֮�������ٸ�
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
            ps.setString(1,cno);//TODO �������ַ���format���������Ч�ʸ���㣬֮�������ٸ�
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


    //����Ƿ��Ѿ������Ȿ��
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
    function�����Ҹ����Ƿ����
    input��String bno String table
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
    function: ��¼�����
    input: String cno--��֮ǰ�ĵ�¼�����ṩ, String bno;
    output: String [���н��]
     */
    public String borrow_Book(String cno, String bno,String mno){
        String result = "";
        int store = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            if (find_Book(bno,"book")){
                System.out.println("�����Ȿ��");
                //˵�����ڸñ���
                stmt = conn.createStatement();
                String stock = String.format("SELECT stock FROM book WHERE bno = '%s'",bno);
                rs = stmt.executeQuery(stock);
                while(rs.next()){
                    store = rs.getInt(1);//������Ҳֻ��һ�У�һ�����
                }
                if (store!=0){
                    System.out.println("���Ϊ"+store+"��");
                    //˵���п��
                    store = store-1;
                    String update = String.format("UPDATE book SET stock = %d WHERE bno = '%s'",store,bno);
                    //�洢������Ϣ
                    Calendar now = Calendar.getInstance();
                    String borrow_date = formatter.format(now.getTime());
                    now.add(Calendar.MONTH,3);//һ����������������
                    String return_date = formatter.format(now.getTime());

                    String insert = String.format("INSERT INTO borrow VALUES ('%s','%s','%s','%s','%s')",cno,bno,borrow_date,return_date,mno);
                    //����һ+��ӽ�����Ϣ
                    System.out.println(insert);
                    stmt.executeUpdate(insert);
                    result = "����ɹ�";
                    System.out.println("����ɹ���");
                    stmt.executeUpdate(update);

                }
                else{
                    //û�п�棬������е�ͼ��
                    String time="";
                    String latest = String.format("SELECT MIN(return_date) FROM borrow WHERE bno = '%s'",bno);
                    rs = stmt.executeQuery(latest);
                    while (rs.next()){
                        time = formatter.format(rs.getDate(1));
                    }
                    result="���޿�棬����黹ʱ�䣺"+time;
                    System.out.println(result);

                }

            }
            else {
                result = "�޸���!";
                System.out.println("û���Ȿ��");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /*------------�����ǻ��鹦��------------*/
    /*
    function: �����Ϊbno���鼮
    input: String bno;
    output: String ״̬��Ϣ
    TODO:������borrow table������һ��Ϊ �Ƿ��ѻ��飿
    TODO����Ҫȷ��sql datetime��set�Ƿ������string type ���ԡ�
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
                mess = "����ɹ�!";
                System.out.println(mess);
            }
            else {
                mess =  "δ����飬��Ϣ����";System.out.println(mess);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mess;
    }


    public boolean return_Book2(String cno,String bno){
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM borrow  WHERE cno = ? and bno=?");
            ps.setString(1,cno);//TODO �������ַ���format���������Ч�ʸ���㣬֮�������ٸ�
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
//     /*���Բ���һ���鱾*/
//     //cnnt.add_Book("01","CS","DS0","�������ipad",2021,"������",10.0f,10);
////     cnnt.add_Book("02","CS","DS0","�������ipad",2021,"������",10.0f,10);
////     cnnt.add_Book("03","CS","DS1","�������ipad",2021,"������",10.0f,10);
////     cnnt.add_Book("04","CS","DS2","�������ipad",2021,"������",10.0f,10);
////     cnnt.add_Book("05","CS","DS3","�������ipad",2021,"������",10.0f,10);
////     cnnt.add_Book("010","CS","DS4","�������ipad",2021,"������",10.0f,1);
////     boolean flag=cnnt.find_Book("01","book");
////     System.out.println(flag);
//     /*������������*/
////     cnnt.add_batch_Book("C:\\Users\\ZYC\\Desktop\\ZJU_DB\\src\\batch.txt");
//     /*ע�����Ա*/
////     cnnt.reg_manager("000001","108223","zmq","173");
////     cnnt.reg_manager("02","Zmq108223","zyc","188");
//     /*����Ա��¼*/
////     cnnt.log_Manager("01","001");
////     cnnt.log_Manager("02","Zmq108223");
////     cnnt.log_Manager("03","188");
//     /*����ע��*/
////     cnnt.reg_Reader("001","xyc","CS","S");
////     cnnt.reg_Reader("002","zyc","CS","S");
////     cnnt.reg_Reader("003","zmq","CS","S");
//     /*����*/
//     //�˴����ܻ���Ҫ�޸�һЩ��ѯ�ķ�ʽ
////     cnnt.borrow_Book("002","010","000001");
////     cnnt.borrow_Book("002","01","000001");
////     cnnt.borrow_Book("002","02","000001");
//     /*����*/
////     cnnt.return_Book("002","011","000001");
////     cnnt.return_Book("002","010","000001");
//     /*��ѯ������Ϣ*/
////     cnnt.borrow_Info("002");
//     /*��һ���������*/
// 	}
}
