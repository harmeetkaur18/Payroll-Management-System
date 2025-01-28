

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class calculate_salary extends JFrame implements ActionListener{
    JLabel nameText, addressText;
    TextField unitText;
    Choice empCho,monthCho;
    JButton submit, cancel;
    calculate_salary(){

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214,195,247));
        add(panel);

        JLabel heading = new JLabel("Calculate Salary ");
        heading.setBounds(70,10,300,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(heading);

        JLabel emp = new JLabel("Employer ID");
        emp.setBounds(50,80,100,20);
        panel.add(emp);

        empCho = new Choice();
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.s.executeQuery("select * from employee");
            while (resultSet.next()){
                empCho.add(resultSet.getString("empID"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }
        empCho.setBounds(180,80,100,20);
        panel.add(empCho);

        JLabel name = new JLabel("Name");
        name.setBounds(50,120,100,20);
        panel.add(name);

        nameText = new JLabel("");
        nameText.setBounds(180,120,150,20);
        panel.add(nameText);

        JLabel basic_sal = new JLabel("Basic Salary");
        basic_sal.setBounds(50,160,100,20);
        panel.add(basic_sal);

        addressText = new JLabel("");
        addressText.setBounds(180,160,150,20);
        panel.add(addressText);

        try {
            Conn c= new Conn();
            ResultSet resultSet = c.s.executeQuery("select * from employee where empID = '"+empCho.getSelectedItem()+"' ");
            while (resultSet.next()){
                nameText.setText(resultSet.getString("name"));
                addressText.setText(resultSet.getString("salary"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }
        empCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                     Conn c= new Conn();
                    ResultSet resultSet = c.s.executeQuery("select * from employee where empID = '"+empCho.getSelectedItem()+"' ");
                    while (resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                        addressText.setText(resultSet.getString("salary"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JLabel unitconsumed = new JLabel("No. of Leaves");
        unitconsumed.setBounds(50,200,100,20);
        panel.add(unitconsumed);

        unitText = new TextField();
        unitText.setBounds(180,200,150,20);
        panel.add(unitText);

        JLabel month = new JLabel("Month");
        month.setBounds(50,240,100,20);
        panel.add(month);

        monthCho = new Choice();
        monthCho.add("January");
        monthCho.add("February");
        monthCho.add("March");
        monthCho.add("April");
        monthCho.add("May");
        monthCho.add("June");
        monthCho.add("July");
        monthCho.add("August");
        monthCho.add("September");
        monthCho.add("October");
        monthCho.add("November");
        monthCho.add("December");
        monthCho.setBounds(180,240,150,20);
        panel.add(monthCho);


        submit = new JButton("Submit");
        submit.setBounds(80,300,100,25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(220,300,100,25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel,"Center");
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/budget.png"));
        Image image = imageIcon.getImage().getScaledInstance(250,200,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon1);
        add(imageLabel,"East");

        setSize(650,400);
        setLocation(400,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit){
            String sempID = empCho.getSelectedItem();
            String sempname= nameText.getText();
           // String basic= addressText.getText();
            int sbasic= Integer.parseInt(addressText.getText());
            String sLeaves = unitText.getText();
            String smonth = monthCho.getSelectedItem();
            
        //    int tax;
            int totalsalary =0;
            int units= Integer.parseInt(sLeaves);
            String query_tax = "select * from salary_details";
            try{
                Conn c = new Conn();
                ResultSet resultSet = c.s.executeQuery(query_tax);
                while (resultSet.next()){
                    totalsalary+= sbasic;
                    totalsalary -= units * Integer.parseInt(resultSet.getString("deduct"));
                    totalsalary += Integer.parseInt(resultSet.getString("hra"));
                    totalsalary += Integer.parseInt(resultSet.getString("da"));
                  /*  totalsalary += Integer.parseInt(resultSet.getString("swacch_bharat"));
                    totalsalary += Integer.parseInt(resultSet.getString("fixed_tax"));*/
                    JOptionPane.showMessageDialog(null, "salary Calculated");
                    setVisible(false);
                    new Home();
                }
            }catch (Exception E){
                E.printStackTrace();
            }
        
            double tax= 0.05* totalsalary;
            double netsalary= totalsalary- tax;
            

            String query_total_salary = "insert into salary_info values('"+sempID+"', '"+sempname+"', '"+smonth+"','"+sLeaves+"', '"+totalsalary+"', '"+tax+"', '"+netsalary+"')";
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query_total_salary);
              
                setVisible(false);
            }catch (Exception E){
                E.printStackTrace();
            }

        }
        else if(e.getSource()==cancel){
            setVisible(false);
            new Home();
        }
        else {
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new calculate_salary();
    }

}
