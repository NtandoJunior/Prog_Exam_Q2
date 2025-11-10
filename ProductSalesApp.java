import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;

public class ProductSalesApp {
    private static final int SALES_LIMIT = 500;
    private JFrame frame;
    private JTextArea textArea;
    private JLabel yearsLabel;

    private ProductSales currentProductSales;

    public ProductSalesApp() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Product Sales Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 360);
        frame.setLayout(new BorderLayout());

        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        exitItem.addActionListener(e -> exitApplication());
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem loadMenuItem = new JMenuItem("Load Product Data");
        loadMenuItem.addActionListener(e -> loadProductData());
        JMenuItem saveMenuItem = new JMenuItem("Save Product Data");
        saveMenuItem.addActionListener(e -> saveProductData());
        JMenuItem clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(e -> clearData());
        toolsMenu.add(loadMenuItem);
        toolsMenu.add(saveMenuItem);
        toolsMenu.add(clearMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        frame.setJMenuBar(menuBar);

        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1, 6, 6));
        JButton loadButton = new JButton("Load Product Data");
        loadButton.addActionListener(e -> loadProductData());
        JButton saveButton = new JButton("Save Product Data");
        saveButton.addActionListener(e -> saveProductData());
        topPanel.add(loadButton);
        topPanel.add(saveButton);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(topPanel, BorderLayout.NORTH);

        
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scroller = new JScrollPane(textArea);
        scroller.setPreferredSize(new Dimension(380, 180));
        frame.add(scroller, BorderLayout.CENTER);

        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        yearsLabel = new JLabel("Years Processed: 0");
        bottomPanel.add(yearsLabel);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 12, 12, 12));
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
    }

    private void loadProductData() {
        int[][] data = {
                {300, 150, 700},
                {250, 200, 600}
        };

        currentProductSales = new ProductSales(data, SALES_LIMIT);
        textArea.setText(currentProductSales.getSummaryText());
        yearsLabel.setText("Years Processed: " + currentProductSales.getYearsProcessed());
    }

    private void saveProductData() {
        if (currentProductSales == null) {
            JOptionPane.showMessageDialog(frame, "No data to save. Please load product data first.", "No Data", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String text = textArea.getText();
        
        try (FileWriter writer = new FileWriter("data.txt")) {
            writer.write(text);
            writer.flush();
            JOptionPane.showMessageDialog(frame, "Product data saved to data.txt", "Saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearData() {
        textArea.setText("");
        yearsLabel.setText("Years Processed: 0");
        currentProductSales = null;
    }

    private void exitApplication() {
        frame.dispose();
    }

    private void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductSalesApp app = new ProductSalesApp();
            app.show();
        });
    }
}

