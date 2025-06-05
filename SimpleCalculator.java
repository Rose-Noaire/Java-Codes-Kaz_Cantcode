import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class calculadorapp extends JFrame implements ActionListener {

    private JTextField display;
    private double num1, num2, resultado;
    private String operador;

    public calculadorapp() {
        setTitle("Calculadora Científica");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4, 5, 5));

        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "√", "x²", "sin", "cos",
            "tan", "log", "C", "DEL"
        };

        for (String texto : botoes) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            switch (comando) {
                case "C":
                    display.setText("");
                    break;
                case "DEL":
                    String texto = display.getText();
                    if (!texto.isEmpty()) {
                        display.setText(texto.substring(0, texto.length() - 1));
                    }
                    break;
                case "=":
                    calcular();
                    break;
                case "+": case "-": case "*": case "/":
                    num1 = Double.parseDouble(display.getText());
                    operador = comando;
                    display.setText("");
                    break;
                case "√":
                    num1 = Double.parseDouble(display.getText());
                    display.setText(String.valueOf(Math.sqrt(num1)));
                    break;
                case "x²":
                    num1 = Double.parseDouble(display.getText());
                    display.setText(String.valueOf(Math.pow(num1, 2)));
                    break;
                case "sin":
                    num1 = Math.toRadians(Double.parseDouble(display.getText()));
                    display.setText(String.valueOf(Math.sin(num1)));
                    break;
                case "cos":
                    num1 = Math.toRadians(Double.parseDouble(display.getText()));
                    display.setText(String.valueOf(Math.cos(num1)));
                    break;
                case "tan":
                    num1 = Math.toRadians(Double.parseDouble(display.getText()));
                    display.setText(String.valueOf(Math.tan(num1)));
                    break;
                case "log":
                    num1 = Double.parseDouble(display.getText());
                    display.setText(String.valueOf(Math.log10(num1)));
                    break;
                default: // números e ponto
                    display.setText(display.getText() + comando);
                    break;
            }
        } catch (Exception ex) {
            display.setText("Erro");
        }
    }

    private void calcular() {
        try {
            num2 = Double.parseDouble(display.getText());
            switch (operador) {
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "*":
                    resultado = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        display.setText("Divisão por zero");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(resultado));
        } catch (Exception e) {
            display.setText("Erro");
        }
    }

    public static void main(String[] args) {
        new calculadorapp();
    }
}
