import javax.swing.*;
// import javafx.scene.control.RadioButton; 

public class RadioButtonExample {
    public RadioButtonExample() {
        JFrame f = new JFrame("Radio button");
        JRadioButton r1 = new JRadioButton("A) Male");
        r1.setBounds(75, 50, 100, 30);
        JRadioButton r2 = new JRadioButton("B) Male");
        r2.setBounds(100, 50, 100, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        f.add(r1);
        f.add(r2);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);

    }
    public static void main(String[] args) {
        new RadioButtonExample();
    }

}
