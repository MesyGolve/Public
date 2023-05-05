package experiments;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JInformation extends JFrame{
	
	static boolean ungr = false;
	static boolean grst = false;

	private static final long serialVersionUID = 1L;

	public JInformation() {
		
		setTitle("学生信息系统");
		setBounds(550,350,500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null);
		
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		
		jp1.setBounds(0,0,500,100);
		jp2.setBounds(0,100,150,200);
		jp3.setBounds(150,100,350,200);
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		
		JLabel jl1 = new JLabel("欢迎来到学生信息系统");
		jl1.setFont(new Font("宋体",Font.PLAIN,20));
		jp1.add(jl1);
		
		JLabel jl2 = new JLabel("学生类型");
		jl2.setFont(new Font("华文行楷",Font.PLAIN,16));
		jp2.add(jl2);
		
		JRadioButton jr1 = new JRadioButton("本科生");
		JRadioButton jr2 = new JRadioButton("研究生");
		
		jr1.addActionListener(new jbAction1());
		jr2.addActionListener(new jbAction2());
		
		jp2.add(jr1);
		jp2.add(jr2);
		
		ButtonGroup group = new ButtonGroup();
		group.add(jr1);
		group.add(jr2);
		
		JButton jb1 = new JButton("操作信息");
		JButton jb2 = new JButton("查询信息");
		JButton jb3 = new JButton("使用说明");

		jb1.addActionListener(new Operation());
		jb2.addActionListener(new Search());
		jb3.addActionListener(new illustrate());

		jp3.add(jb1);
		jp3.add(jb2);
		jp3.add(jb3);

	}
	
	private class jbAction1 implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			ungr = true;
			grst = false;
		}
	
	}
	
	private class jbAction2 implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			ungr = false;
			grst = true;
		}
	
	}
	
	public boolean getungr() {
		
		return ungr;
	}
	
	public boolean getgrst() {
		
		return grst;
	}
	
}
