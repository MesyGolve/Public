package experiments;

import java.awt.event.*;
import javax.swing.*;

public class illustrate implements ActionListener {

	public void actionPerformed(ActionEvent e) {

		JOptionPane.showMessageDialog(null,"1.使用本系统时，请先选择您要处理的对象类型，选择包括：研究生群体、本科生群体"
				+ "\n\n2.操作信息支持对信息进行增加、删除、修改、保存，"
						+ "能根据某项信息对学生进行排序" + "\n\n3.查询信息能够"
								+ "根据班级、姓名或者学号查询学生信息，以及查询该类学生总人数");		
	}

}
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                Object oa = tableModel.getValueAt(selectedRow, 0);
                Object ob = tableModel.getValueAt(selectedRow, 1);
                Object oc = tableModel.getValueAt(selectedRow, 2);
                Object od = tableModel.getValueAt(selectedRow, 3);
                Object oe = tableModel.getValueAt(selectedRow, 4);
                Object of = tableModel.getValueAt(selectedRow, 5);
                Object og = tableModel.getValueAt(selectedRow, 6);
                Object oh = tableModel.getValueAt(selectedRow, 7);
                Object oi = tableModel.getValueAt(selectedRow, 8);
                aTextField.setText(oa.toString());
                bTextField.setText(ob.toString());
                cTextField.setText(oc.toString());
                dTextField.setText(od.toString());
                eTextField.setText(oe.toString());
                fTextField.setText(of.toString());
                gTextField.setText(og.toString());
                hTextField.setText(oh.toString());
                iTextField.setText(oi.toString());
            }
        });
        scrollPane.setViewportView(table);
        JPanel panel = new JPanel();
        panel.setBounds(0,250,650,100);
        c.add(panel);      
        
        aTextField = new JTextField("", 10);
        bTextField = new JTextField("", 10);
        cTextField = new JTextField("", 10);
        dTextField = new JTextField("", 10);
        eTextField = new JTextField("", 10);
        fTextField = new JTextField("", 10);
        gTextField = new JTextField("", 10);
        hTextField = new JTextField("", 10);
        iTextField = new JTextField("", 10);
        
        panel.add(new JLabel("学号"));
        panel.add(aTextField);
        panel.add(new JLabel("姓名"));
        panel.add(bTextField);
        panel.add(new JLabel("年龄"));
        panel.add(cTextField);
        panel.add(new JLabel("班级"));
        panel.add(dTextField);
        panel.add(new JLabel("导师"));
        panel.add(eTextField);
        panel.add(new JLabel("地址"));
        panel.add(fTextField);
        panel.add(new JLabel(direction));
        panel.add(gTextField);
        panel.add(new JLabel(mastergrade));
        panel.add(hTextField);
        panel.add(new JLabel(minorgrades));
        panel.add(iTextField);
        
        JButton addButton = new JButton("增加信息");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] rowValues = {aTextField.getText(),
                        bTextField.getText(),cTextField.getText(),dTextField.getText(),eTextField.getText()
                        ,fTextField.getText(),gTextField.getText(),hTextField.getText(),iTextField.getText()};
                tableModel.addRow(rowValues);
                aTextField.setText("");
            	bTextField.setText("");
            	cTextField.setText("");
            	dTextField.setText("");
            	eTextField.setText("");
            	fTextField.setText("");
            	gTextField.setText("");
            	hTextField.setText("");
            	iTextField.setText("");
            }
        });
        panel.add(addButton);
        
        JButton updButton = new JButton("修改信息");
        updButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                	tableModel.setValueAt(aTextField.getText(), selectedRow, 0);
                	tableModel.setValueAt(bTextField.getText(), selectedRow, 1);
                	tableModel.setValueAt(cTextField.getText(), selectedRow, 2);
                	tableModel.setValueAt(dTextField.getText(), selectedRow, 3);
                	tableModel.setValueAt(eTextField.getText(), selectedRow, 4);
                	tableModel.setValueAt(fTextField.getText(), selectedRow, 5);
                	tableModel.setValueAt(gTextField.getText(), selectedRow, 6);
                	tableModel.setValueAt(hTextField.getText(), selectedRow, 7);
                	tableModel.setValueAt(iTextField.getText(), selectedRow, 8);
                }
            }
        });
        panel.add(updButton);
        
        JButton delButton = new JButton("删除信息");
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1)
                    tableModel.removeRow(selectedRow);
            } 
        });
        panel.add(delButton);
        
        JButton SaveButton = new JButton("保存信息");
        SaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	int count = 0;
            	String[] str = new String[1000];
                FileWriter fw = null;
                BufferedWriter bw = null;
                int row = table.getRowCount();
                int column = table.getColumnCount();
                try {
                    if (Operation.ungr == true && Operation.grst == false) {
                        fw = new FileWriter(file1);
                        bw = new BufferedWriter(fw);
                    } else if (Operation.ungr == false && Operation.grst == true) {
                        fw = new FileWriter(file2);
                        bw = new BufferedWriter(fw);
                    }
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < column; j++) {
                            Object obj = tableModel.getValueAt(i, j);
                            str[count] = obj.toString();
                            count++;
                        }
                        for(int k = 0;k < count;k++) {
                        	bw.write(str[k]);
                        	bw.newLine();
                        }
                        count = 0;
                    }
                    
                    bw.flush();
                    bw.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        });
        panel.add(SaveButton);
    }
}
                
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
package experiments;

import java.io.*;

public class Text {

	public static void main(String[] args) {
		
		File file1 = new File("D:\\ungr.txt");
		File file2 = new File("D:\\grst.txt");
		
		if(file1.exists() && file2.exists()) {
			
		}
		else if(file1.exists() && file2.exists() == false){
			try{
				file2.createNewFile();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else if(file1.exists() == false && file2.exists()) {
			try{
				file1.createNewFile();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				file1.createNewFile();
				file2.createNewFile();
			}
			catch(IOException e) {
				e.printStackTrace();
			}		
		}

		new JInformation().setVisible(true);
		
	}

}
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
