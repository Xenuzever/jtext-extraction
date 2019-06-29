package xyz.javamon.te.component;

import javax.swing.*;

public class TEFrame extends JFrame {
    
    private JTextArea textArea;

    public TEFrame() {
        initialize();
    }

    private void initialize() {
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(getTextArea());
        getContentPane().add(scrollPane);
        setVisible(true);
    }

    public JTextArea getTextArea() {
        if (textArea == null) {
            textArea = new JTextArea();
            textArea.setTransferHandler(new TEDropFileHandler());
            textArea.setText("ファイルをドラッグ&ドロップしてください。");
            textArea.setEnabled(false);
        }
        return textArea;
    }

}
