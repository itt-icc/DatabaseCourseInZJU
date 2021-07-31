import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ManagerFrame extends JFrame implements ActionListener{
	private JFrame frame = new JFrame("ͼ��ݹ���ϵͳ ");
	private JPanel ManagerPane;
	private ImageIcon background;

	public String load_mno;

/*	public static void main(String[] args) throws SQLException {
		new ManagerFrame();
	}*/

	public ManagerFrame(String currentname) throws SQLException{
		//����

		load_mno=currentname;

		background = new ImageIcon(this.getClass().getResource("back1.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());


		ManagerPane = (JPanel) frame.getContentPane();
		ManagerPane.setOpaque(false);//��͸��
		ManagerPane.setLayout(null);//�޲���
		 frame.getLayeredPane().setLayout(null);
		 frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
//		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		frame.setBounds(200, 200, background.getIconWidth(),background.getIconHeight());
		 frame.setResizable(true);
		 frame.setVisible(true);



		JLabel title = new JLabel("��ã�����Ա��");
		title.setBounds(340, 170, 300, 30);//����title�Ĵ�С��λ��
		title.setFont(new Font("����",Font.BOLD,30));//��б+�Ӵ�
		ManagerPane.add(title);


		JButton BookSeeking= new JButton("ͼ���ѯ");
		ManagerPane.add(BookSeeking);
		BookSeeking.setBounds(60, 250, 90, 30);
		BookSeeking.addActionListener(this);

		JButton AddBook = new JButton("ͼ�����");
		ManagerPane.add(AddBook);
		AddBook.setBounds(200, 250, 90, 30);
		AddBook.addActionListener(this);


		JButton BorrowBook= new JButton("�軹��");
		ManagerPane.add(BorrowBook);
		BorrowBook.setBounds(340, 250, 112, 30);
		BorrowBook.addActionListener(this);

		JButton AddReader = new JButton("��ɾ���鿨");
		ManagerPane.add(AddReader);
		AddReader.setBounds(500, 250, 112, 30);
		AddReader.addActionListener(this);

		JButton Message = new JButton("�˳�ϵͳ");
		frame.getContentPane().add(Message);
		Message.setBounds(700, 250, 112, 30);
		Message.addActionListener(this);
		Message.setBackground(Color.YELLOW);    //���ð�ť����ɫ


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
