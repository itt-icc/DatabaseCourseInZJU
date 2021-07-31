import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ManagerFrame extends JFrame implements ActionListener{
	private JFrame frame = new JFrame("图书馆管理系统 ");
	private JPanel ManagerPane;
	private ImageIcon background;

	public String load_mno;

/*	public static void main(String[] args) throws SQLException {
		new ManagerFrame();
	}*/

	public ManagerFrame(String currentname) throws SQLException{
		//背景

		load_mno=currentname;

		background = new ImageIcon(this.getClass().getResource("back1.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());


		ManagerPane = (JPanel) frame.getContentPane();
		ManagerPane.setOpaque(false);//非透明
		ManagerPane.setLayout(null);//无布局
		 frame.getLayeredPane().setLayout(null);
		 frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
//		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		frame.setBounds(200, 200, background.getIconWidth(),background.getIconHeight());
		 frame.setResizable(true);
		 frame.setVisible(true);



		JLabel title = new JLabel("你好，管理员！");
		title.setBounds(340, 170, 300, 30);//调整title的大小和位置
		title.setFont(new Font("宋体",Font.BOLD,30));//倾斜+加粗
		ManagerPane.add(title);


		JButton BookSeeking= new JButton("图书查询");
		ManagerPane.add(BookSeeking);
		BookSeeking.setBounds(60, 250, 90, 30);
		BookSeeking.addActionListener(this);

		JButton AddBook = new JButton("图书入库");
		ManagerPane.add(AddBook);
		AddBook.setBounds(200, 250, 90, 30);
		AddBook.addActionListener(this);


		JButton BorrowBook= new JButton("借还书");
		ManagerPane.add(BorrowBook);
		BorrowBook.setBounds(340, 250, 112, 30);
		BorrowBook.addActionListener(this);

		JButton AddReader = new JButton("增删借书卡");
		ManagerPane.add(AddReader);
		AddReader.setBounds(500, 250, 112, 30);
		AddReader.addActionListener(this);

		JButton Message = new JButton("退出系统");
		frame.getContentPane().add(Message);
		Message.setBounds(700, 250, 112, 30);
		Message.addActionListener(this);
		Message.setBackground(Color.YELLOW);    //设置按钮背景色


		BookSeeking.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				if(e.getSource()==BookSeeking)
				{
					try {
//						frame.dispose();
						new SearchBook2();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});


		AddReader.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				if(e.getSource()==AddReader)
				{
					try {
//						frame.dispose();
						new AddDelreader();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		BorrowBook.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				if(e.getSource()==BorrowBook)
				{
					try {
//						frame.dispose();
						new BorrowBook(load_mno);
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});


		Message.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				if(e.getSource()==Message)
				{
						frame.dispose();
				}
			}
		});


		AddBook.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				if(e.getSource()==AddBook)
				{
					try {
//						frame.dispose();
						new InsertBook();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});


	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

	}
}
