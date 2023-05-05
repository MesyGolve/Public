package experiments;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;

public class Search implements ActionListener{
	
	static boolean ungr;
	static boolean grst;

	public void actionPerformed(ActionEvent e) {
		
		JInformation a = new JInformation();
		
		ungr = a.getungr();
		grst = a.getgrst();
		
		a.dispose();
		
		if(ungr == false && grst == false) {
			
			JOptionPane.showMessageDialog(null, "您未选择学生类型","发生错误",JOptionPane.ERROR_MESSAGE);
		}
		else {
			SearchPanel sp = new SearchPanel();
			sp.setVisible(true);
		}
	}
}

class SearchPanel extends JDialog {

	private static final long serialVersionUID = 1L;
	
	int row = 0;
	int column = 0;
	
	static int mark;
	
	String[][] tableValues = new String[1000][9];
	
	private JTable jt1;
	private JTextField jt2;
	private DefaultTableModel tableModel;
	
	File file1 = new File("D:\\ungr.txt");
	File file2 = new File("D:\\grst.txt");
	
	public SearchPanel() {
		
		mark = 0;
		
		String tmp = null;	
		FileReader fr = null;
    	BufferedReader br = null;
    	
    	setTitle("查询窗口");
        setBounds(100, 100,660,450);
        
        Container c = getContentPane();
        c.setLayout(null);
        
        JScrollPane jp1 = new JScrollPane();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        
        jp1.setBounds(0,0,650,250);
        jp2.setBounds(10,250,220,100);
        jp3.setBounds(230,250,440,100);
        
        tableModel = new DefaultTableModel();
        
        String[] columnNames1 = {"学号","姓名","年龄","班级","导师","地址","专业方向","主科成绩","通识成绩"};
        String[] columnNames2 = {"学号","姓名","年龄","班级","导师","地址","研究方向","主科成绩","辅修成绩"};
        String[][] table = null;
        
        if(Search.ungr == true && Search.grst == false) {
        	tableModel = new DefaultTableModel(table, columnNames1);
        }
        else if(Search.ungr == false && Search.grst == true) {
        	tableModel = new DefaultTableModel(table, columnNames2);
        }
        
        jt1 = new JTable(tableModel);
        jt1.setRowSorter(new TableRowSorter<>(tableModel));
        jt1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        jp1.setViewportView(jt1);
        
        JRadioButton jr1 = new JRadioButton("通过班级查询");
		JRadioButton jr2 = new JRadioButton("通过姓名查询");
		JRadioButton jr3 = new JRadioButton("通过学号查询");
		
		jr1.addActionListener(new jrAction1());
		jr2.addActionListener(new jrAction2());
		jr3.addActionListener(new jrAction3());
		
		jp2.add(jr1);
		jp2.add(jr2);
		jp2.add(jr3);
		
		ButtonGroup group = new ButtonGroup();
		
		group.add(jr1);
		group.add(jr2);
		group.add(jr3);
		
		JLabel jl = new JLabel("请输入关键词 ");
		jt2 = new JTextField("",10);
		JButton jb = new JButton("确定");
		JButton jb1 = new JButton("查询总人数");
		
		jb.addActionListener(new jbAction());
		jb1.addActionListener(new jb1Action());
		
		jp3.add(jl);
		jp3.add(jt2);
		jp3.add(jb);
		jp3.add(jb1);
		
        c.add(jp1);
        c.add(jp2);
        c.add(jp3);
   
    	try {
        	
        	if (Search.ungr == true && Search.grst == false) {
        		fr = new FileReader(file1);
        		br = new BufferedReader(fr);
        	}
        	else if (Search.ungr == false && Search.grst == true) {
        		fr = new FileReader(file2);
        		br = new BufferedReader(fr);
        	}
        	while((tmp = br.readLine()) != null) {
        		tableValues[row][column] = tmp;
        		column++;
        		if(column == 9) {
        			column = 0;
        			row++;
        		}
        	}
        }
        catch(IOException e) {
        	e.printStackTrace();
        }
    		
	}
	
	private class jrAction1 implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			mark = 1;
		}
	
	}
	
	private class jrAction2 implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			mark = 2;
		}
	
	}
	
	private class jrAction3 implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			mark = 3;
		}
	
	}
	
	private class jbAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			boolean mark1 = true;
			String str = null;
			
			str = jt2.getText();
			
			int col = jt1.getRowCount();
			
			for(int i = col - 1; i >= 0; i--) {
			    tableModel.removeRow(i);
			}
			
			if(mark == 1) {
				for(int i = 0;i < row;i++) {
					if(tableValues[i][3].equals(str)) {
						tableModel.addRow(tableValues[i]);
						mark1 = false;
					}
				}
			}
			else if(mark == 2) {
				for(int i = 0;i < row;i++) {
					if(tableValues[i][1].equals(str)) {
						tableModel.addRow(tableValues[i]);
						mark1 = false;
					}
				}
			}
			else if(mark == 3) {
				for(int i = 0;i < row;i++) {
					if(tableValues[i][0].equals(str)) {
						tableModel.addRow(tableValues[i]);
						mark1 = false;
					}
				}
			}
			else if(mark == 0) {
				JOptionPane.showMessageDialog(null, "您未选择关键词类型","发生错误",JOptionPane.ERROR_MESSAGE);
			}
			
			if(mark1 && mark != 0) {
				JOptionPane.showMessageDialog(null, "未找到符合条件的学生","发生错误",JOptionPane.ERROR_MESSAGE);
			}
			
		}
	
	}
	
	private class jb1Action implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			String str = "";
			
			if(Search.ungr == true && Search.grst == false) {
				str = "本科生：";
			}
			else if(Search.ungr == false && Search.grst == true) {	
				str = "研究生：";
			}
			else {
				JOptionPane.showMessageDialog(null, "未知的错误","发生错误",JOptionPane.ERROR_MESSAGE);
			}
			
			JOptionPane.showMessageDialog(null,str + row + " 人");
			
		}
		
	}
	
}
