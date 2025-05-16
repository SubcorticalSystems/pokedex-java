import javax.swing.*;
import java.awt.*;
import javax.swing.border.AbstractBorder;

public class MyFrame extends JFrame {

    public MyFrame() {
        setTitle("Pokédex");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel basePanel = new JPanel() {
            // For anti-aliased rendering of circles
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            }
        };
        basePanel.setBackground(new Color(200, 0, 0));
        basePanel.setLayout(null);
        setContentPane(basePanel);

        class CirclePanel extends JPanel {
            private final Color color;
            private final int diameter;

            public CirclePanel(Color color, int diameter) {
                this.color = color;
                this.diameter = diameter;
                setPreferredSize(new Dimension(diameter, diameter));
                setOpaque(false); // Important to make the background transparent
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillOval(0, 0, diameter, diameter);
                g2.setColor(Color.BLACK);
                g2.drawOval(0, 0, diameter, diameter);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(diameter, diameter);
            }
        }


        class LensPanel extends JPanel {
            private final Color baseColor;
            private final int diameter;

            public LensPanel(Color baseColor, int diameter) {
                this.baseColor = baseColor;
                this.diameter = diameter;
                setOpaque(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                // Enable anti-aliasing for smooth circles
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw white border circle (outer ring)
                g2.setColor(Color.WHITE);
                g2.fillOval(0, 0, diameter, diameter);

                // Draw blue inner circle (lens)
                int inset = 4;
                g2.setColor(baseColor);
                g2.fillOval(inset, inset, diameter - inset * 2, diameter - inset * 2);

                // small reflection highlight
                g2.setColor(new Color(255, 255, 255, 100)); // semi-transparent white
                g2.fillOval(diameter / 4, diameter / 4, diameter / 5, diameter / 5);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(diameter, diameter);
            }
        }

        LensPanel bigBlueLens = new LensPanel(new Color(0, 102, 204), 50);
        bigBlueLens.setBounds(20, 20, 50, 50);
        basePanel.add(bigBlueLens);

        //  Smaller indicator lights (15px, with more spacing from big circle)
        int[] lightX = {85, 105, 125};  // Moved right to create more space
        Color[] lightColors = {
                new Color(0, 153, 255),  // Small blue
                new Color(255, 204, 0),  // Yellow
                new Color(0, 204, 0)     // Green
        };

        for (int i = 0; i < 3; i++) {
            CirclePanel light = new CirclePanel(lightColors[i], 15);
            light.setBounds(lightX[i], 38, 15, 15); // Slightly lower to match big circle
            basePanel.add(light);
        }

        // "Pokédex" label above screen--will be replaced depending on which dex is open
        JLabel titleLabel = new JLabel("Pokédex");
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(190, 60, 200, 30);
        basePanel.add(titleLabel);


        DiagonalScreenPanel screenPanel = new DiagonalScreenPanel();
        screenPanel.setBounds(100, 120, 300, 200);
        screenPanel.setLayout(null);
        basePanel.add(screenPanel);

        class DiagonalScreenPanel extends JPanel {
            public DiagonalScreenPanel() {
                setOpaque(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();

                Polygon shape = new Polygon();
                shape.addPoint(0, 0);        // top-left
                shape.addPoint(w, 0);        // top-right
                shape.addPoint(w, h);        // bottom-right
                shape.addPoint(20, h);       // bottom-left diagonal start
                shape.addPoint(0, h - 20);   // bottom-left diagonal end

                g2.setColor(Color.BLACK);
                g2.fillPolygon(shape);
            }
        }


        // Green display inside
        JPanel displayPanel = new JPanel();
        displayPanel.setBackground(new Color(34, 139, 34));
        displayPanel.setBounds(20, 20, 260, 160);
        screenPanel.add(displayPanel);


        // D-pad
        Color dpadColor = new Color(60, 60, 60);
        int dpadBaseX = 50;
        int dpadBaseY = 400;

        JButton upButton = createDpadButton("↑", dpadColor);
        upButton.setBounds(dpadBaseX + 45, dpadBaseY, 40, 40);
        basePanel.add(upButton);

        JButton downButton = createDpadButton("↓", dpadColor);
        downButton.setBounds(dpadBaseX + 45, dpadBaseY + 90, 40, 40);
        basePanel.add(downButton);

        JButton leftButton = createDpadButton("←", dpadColor);
        leftButton.setBounds(dpadBaseX, dpadBaseY + 45, 40, 40);
        basePanel.add(leftButton);

        JButton rightButton = createDpadButton("→", dpadColor);
        rightButton.setBounds(dpadBaseX + 90, dpadBaseY + 45, 40, 40);
        basePanel.add(rightButton);

        JPanel center = new JPanel();
        center.setBounds(dpadBaseX + 45, dpadBaseY + 45, 40, 40);
        center.setBackground(dpadColor.darker());
        center.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        basePanel.add(center);

        // Circular A and B buttons
        /*CircleButton buttonA = new CircleButton("B", new Color(255, 80, 80));
        buttonA.setBounds(380, 420, 40, 40); // Lowered to y=400
        basePanel.add(buttonA);

        CircleButton buttonB = new CircleButton("A", new Color(100, 100, 100));
        buttonB.setBounds(340, 390, 40, 40); // Lowered to y=370
        basePanel.add(buttonB);
         */

        CircleButton buttonA = new CircleButton("A", new Color(255, 80, 80));
        buttonA.setBounds(340, 390, 40, 40); // Lowered to y=400
        basePanel.add(buttonA);

        CircleButton buttonB = new CircleButton("B", new Color(100, 100, 100));
        buttonB.setBounds(380, 420, 40, 40); // Lowered to y=370
        basePanel.add(buttonB);

        // Speaker grill panel
        SpeakerGrillPanel speakerGrill = new SpeakerGrillPanel();
        speakerGrill.setBounds(310, 490, 130, 50); // Wider by 30px
        basePanel.add(speakerGrill);

/*
        // Main action button ("GO")
        JButton actionButton = new JButton("GO");
        actionButton.setBounds(370, 460, 60, 60);
        actionButton.setFont(new Font("Arial", Font.BOLD, 14));
        actionButton.setForeground(Color.WHITE);
        actionButton.setBackground(new Color(220, 0, 0));
        actionButton.setFocusPainted(false);
        actionButton.setBorderPainted(false);
        actionButton.setOpaque(true);
        actionButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));
        actionButton.setEnabled(false);
        basePanel.add(actionButton);

 */
}

        private JButton createDpadButton (String text, Color bgColor){
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setForeground(Color.WHITE);
            button.setBackground(bgColor);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setOpaque(true);
            button.setEnabled(false);
            return button;
        }


        // circular border for indicator lights
        static class RoundBorder extends AbstractBorder {
            private final int diameter;

            public RoundBorder(int diameter) {
                this.diameter = diameter;
            }

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(Color.BLACK);
                g.drawOval(x, y, diameter, diameter);
            }
        }

        public static void main (String[]args){
            SwingUtilities.invokeLater(() -> new MyFrame().setVisible(true));
        }
    }

    class CircleButton extends JButton {
        private final Color fillColor;
        private final String label;

        public CircleButton(String label, Color fillColor) {
            super("");
            this.fillColor = fillColor;
            this.label = label;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setFont(new Font("Arial", Font.BOLD, 16));
            setForeground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw circle
            g2.setColor(fillColor);
            g2.fillOval(0, 0, getWidth(), getHeight());

            // Border
            g2.setColor(Color.DARK_GRAY);
            g2.setStroke(new BasicStroke(2));
            g2.drawOval(0, 0, getWidth() - 1, getHeight() - 1);

            // centered label
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(label);
            int textHeight = fm.getAscent();
            g2.setColor(Color.WHITE);
            g2.drawString(label, (getWidth() - textWidth) / 2, (getHeight() + textHeight / 2) / 2);

            g2.dispose();
        }

        @Override
        public boolean contains(int x, int y) {
            int radius = getWidth() / 2;
            return Math.pow(x - radius, 2) + Math.pow(y - radius, 2) <= radius * radius;
        }
    }


    class DiagonalScreenPanel extends JPanel {
        public DiagonalScreenPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            Polygon shape = new Polygon();
            shape.addPoint(0, 0);        // top-left
            shape.addPoint(w, 0);        // top-right
            shape.addPoint(w, h);        // bottom-right
            shape.addPoint(20, h);       // bottom-left diagonal start
            shape.addPoint(0, h - 20);   // bottom-left diagonal end

            g2.setColor(Color.BLACK);
            g2.fillPolygon(shape);
        }
    }


//
class SpeakerGrillPanel extends JPanel {
    public SpeakerGrillPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // Background
        g2.setColor(new Color(200, 0, 0));
        g2.fillRoundRect(0, 0, w, h, 10, 10);

        // Diagonal slits
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));

        // Rotate around center for diagonal lines
        g2.rotate(Math.toRadians(-45), w / 2.0, h / 2.0);

        int slitCount = 6;
        int spacing = 10;
        int length = w + 30; // Longer than width so they span cleanly

        for (int i = -2; i < slitCount; i++) {
            int y = 10 + i * spacing;
            g2.drawLine(-10, y, length, y);
        }

        g2.dispose();
    }


}


