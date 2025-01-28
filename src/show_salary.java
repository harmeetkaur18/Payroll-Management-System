import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class show_salary extends JFrame implements ActionListener {
    Choice searchEmpCho,searchMonthCho;
    JTable table;
    JButton search,print,close;
    show_salary(){
        super("Salary Information:");
        getContentPane().setBackground(new Color(192,186,254));
        setSize(700,500);
        setLocation(400,200);
        setLayout(null);

        JLabel searchEmpID = new JLabel("Search By Employer ID");
        searchEmpID.setBounds(20,20,150,20);
        add(searchEmpID);

        searchEmpCho = new Choice();
        searchEmpCho.setBounds(180,20,150,20);
        add(searchEmpCho);

        try{
            Conn c= new Conn();
            ResultSet resultSet = c.s.executeQuery("select * from salary_info");
            while (resultSet.next()){
                searchEmpCho.add(resultSet.getString("empID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

     /*   JLabel searchMonth = new JLabel("Search By Month");
        searchMonth.setBounds(400,20,100,20);
        add(searchMonth);

        searchMonthCho = new Choice();
        searchMonthCho.add("January");
        searchMonthCho.add("February");
        searchMonthCho.add("March");
        searchMonthCho.add("April");
        searchMonthCho.add("May");
        searchMonthCho.add("June");
        searchMonthCho.add("July");
        searchMonthCho.add("August");
        searchMonthCho.add("September");
        searchMonthCho.add("October");
        searchMonthCho.add("November");
        searchMonthCho.add("December");
        searchMonthCho.setBounds(520,20,150,20);
        add(searchMonthCho); */



        table = new JTable();
        try{
            Conn c =new Conn();
            ResultSet resultSet = c.s.executeQuery("select * from salary_info");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,700,500);
        scrollPane.setBackground(Color.white);
        add(scrollPane);


        search = new JButton("Search");
        search.setBackground(Color.white);
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(Color.white);
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBackground(Color.white);
        close.setBounds(600,70,80,20);
        close.addActionListener(this);
        add(close);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==search){
            String query_search = "select * from salary_info where empID = '"+searchEmpCho.getSelectedItem()+"' " ;
            try{
                Conn c = new Conn();
                ResultSet resultSet = c.s.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==print)
        {
            try {
                table.print();
            } catch (Exception E) {
                E.printStackTrace();
            }

        }
        else if(e.getSource()==close){
            setVisible(false);
            new Home();
        }
        else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new show_salary();
    }
}
