package experiments;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;

public class Operation implements ActionListener{
	
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
			AddAndDeleteDemo aad = new AddAndDeleteDemo();
			aad.setVisible(true);
			JOptionPane.showMessageDialog(null,"操作前请仔细阅读本条信息"
					+ "\n\n1.增加信息时，请在操作面板下方输入框输入您所要增加的数据，"
					+ "全部输入完毕后点击\n增加按钮" + "\n\n2.修改信息时，请在表格中选中您要修改的信息所在行的任意一个元素，"
					+ "操作面板下方\n的输入框会显示您选中的该行信息，您只要对输入框中的信息进行修改，完成后点击修\n改信息按钮，"
					+ "就能完成信息的修改。\n\n注：请勿接在表格中修改信息,以免造成不必要的错误" + "\n\n3.删除信息时，"
					+ "请在表格中选中您要删除的信息所在行的任意一个元素，点击删除按钮" + "\n\n4.单击表格首栏，"
					+ "（即''学号……''栏）将依据您所单击的数据类型对学生信息进行排序"
					+ "\n\n5.完成所有操作后，请点击保存按钮对信息进行保存，否则您先前所作的一切操作无效");
		}
	}
}

class AddAndDeleteDemo extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	File file1 = new File("D:\\ungr.txt");
	File file2 = new File("D:\\grst.txt");
	
	private DefaultTableModel tableModel;
    private JTable table;
    private JTextField aTextField;
    private JTextField bTextField;
    private JTextField cTextField;
    private JTextField dTextField;
    private JTextField eTextField;
    private JTextField fTextField;
    private JTextField gTextField;
    private JTextField hTextField;
    private JTextField iTextField;
    
    String direction;
	String mastergrade;
	String minorgrades;
    
    public AddAndDeleteDemo() {
    	
    	int row = 0;
    	int column = 0;
    	
    	String tmp = null;
    	
    	FileReader fr = null;
    	BufferedReader br = null;
    	
        setTitle("操作窗口");
        setBounds(100, 100,660,450);
        
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0,0,650,250);
        Container c = getContentPane();
        c.setLayout(null);
        c.add(scrollPane);
        
        String[] columnNames1 = {"学号","姓名","年龄","班级","导师","地址","专业方向","主科成绩","通识成绩"};
        String[] columnNames2 = {"学号","姓名","年龄","班级","导师","地址","研究方向","主科成绩","辅修成绩"};
        String[][] tableV = new String[1000][9];
        
        try {
        	
        	if (Operation.ungr == true && Operation.grst == false) {
        		fr = new FileReader(file1);
        		br = new BufferedReader(fr);
        	}
        	else if (Operation.ungr == false && Operation.grst == true) {
        		fr = new FileReader(file2);
        		br = new BufferedReader(fr);
        	}
        	while((tmp = br.readLine()) != null) {
        		tableV[row][column] = tmp;
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
        
        String[][] tableValues = new String[row][9];
        
        for(int i = 0;i < row;i++) {
        	for(int j = 0;j < 9;j++) {
        		tableValues[i][j] = tableV[i][j];
        	}
        }
        
        if(Operation.ungr == true && Operation.grst == false) {
        	tableModel = new DefaultTableModel(tableValues, columnNames1);
            direction = "专业方向";
        	mastergrade = "主科成绩";
        	minorgrades = "通识成绩";
        }
        else if(Operation.ungr == false && Operation.grst == true) {
        	tableModel = new DefaultTableModel(tableValues, columnNames2);
        	direction = "研究方向";
            mastergrade = "主科成绩";
            minorgrades = "辅修成绩";
        }
        
        table = new JTable(tableModel);
        table.setRowSorter(new TableRowSorter<>(tableModel));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
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
                