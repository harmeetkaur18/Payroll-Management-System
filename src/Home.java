

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener{
    
    JButton add,view,update,remove,calculatesalary, showSal;
    
    Home(){
        
        setLayout(null);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image i2=i1.getImage().getScaledInstance(1120,630,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,1120,630);
        add(image);
        
        JLabel heading= new JLabel("Payroll Management System");
        heading.setBounds(650,20,400,40);
        heading.setFont(new Font("serif",Font.BOLD,25));
        image.add(heading);
        
        add=new JButton("Add Employee");
        add.setBounds(650,80,150,40);
        add.addActionListener(this);
        image.add(add);
        
        view=new JButton("View Employees");
        view.setBounds(820,80,150,40);
        view.addActionListener(this);
        image.add(view);
        
        update=new JButton("Update Employee");
        update.setBounds(650,140,150,40);
        update.addActionListener(this);
        image.add(update);
        
        remove=new JButton("Remove Employee");
        remove.setBounds(820,140,150,40);
        remove.addActionListener(this);
        image.add(remove);

        showSal=new JButton("Show Salary");
        showSal.setBounds(650,200,150,40);
        showSal.addActionListener(this);
        image.add(showSal);
       
        calculatesalary=new JButton("Calculate salary");
        calculatesalary.setBounds(820,200,150,40);
        calculatesalary.addActionListener(this);
        image.add(calculatesalary);

        setSize(1120,630);
        setLocation(250,100);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==add){  
            setVisible(false);
            new AddEmployee();
        }else if(ae.getSource()==view){
            setVisible(false);
            new ViewEmployee();
        }else if(ae.getSource()==update){
            setVisible(false);
            new UpdateEmployee("");

        }else if(ae.getSource()==remove){
            setVisible(false);
            new RemoveEmployee();
        }
        else if(ae.getSource()==calculatesalary){
            setVisible(false);
            new calculate_salary();
        }
        else if(ae.getSource()==showSal){
            setVisible(false);
            new show_salary();
        }
        else{
            setVisible(false);
        }
    }    
    public static void main(String[] args){
        new Home();
    }
}
