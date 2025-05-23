import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {
        setTitle("Pokédex Launcher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        setContentPane(mainPanel);

        // Header
        JLabel titleLabel = new JLabel("Select a Pokédex", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Dex Grid (2 rows of 5)
        JPanel buttonGrid = new JPanel(new GridLayout(2, 5, 15, 15));
        buttonGrid.setBackground(new Color(245, 245, 245));
        buttonGrid.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Add Dex Panels
        addDexOption(buttonGrid, "National", Pokedex.nationalDex.toArray(new Pokemon[0]), "resources/icons/books.png", true);
        addDexOption(buttonGrid, "Kanto", Pokedex.kantoDex, "resources/icons/Kanto_Map.png", false);
        addDexOption(buttonGrid, "Johto", Pokedex.johtoDex, "resources/icons/Johto_Map.png", false);
        addDexOption(buttonGrid, "Hoenn", Pokedex.hoennDex, "resources/icons/Hoenn_Map.png", false);
        addDexOption(buttonGrid, "Sinnoh", Pokedex.sinnohDex, "resources/icons/Sinnoh_Map.png", false);
        addDexOption(buttonGrid, "Unova", Pokedex.unovaDex, "resources/icons/Unova_Map.png", false);
        addDexOption(buttonGrid, "Kalos", Pokedex.hoennDex, "resources/icons/Kalos_Map.png", false);
        addDexOption(buttonGrid, "Alola", Pokedex.kantoDex, "resources/icons/Alola_Map.png", false);
        addDexOption(buttonGrid, "Galar", Pokedex.johtoDex, "resources/icons/Grookey_Map.png", false);
        addDexOption(buttonGrid, "Paldea", Pokedex.hoennDex, "resources/icons/Paldea_Map.png", false);
        mainPanel.add(buttonGrid, BorderLayout.CENTER);

        // Footer
        JLabel footer = new JLabel("Pokédex Viewer v1.0", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footer.setForeground(Color.GRAY);
        footer.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.add(footer, BorderLayout.SOUTH);
    }


    private void addDexOption(JPanel parent, String name, Pokemon[] pokedex, String iconPath, boolean isNational) {
        JPanel dexPanel = new JPanel(new BorderLayout());
        dexPanel.setBackground(Color.WHITE);
        dexPanel.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210), 1));

        // Icon
        ImageIcon icon = loadScaledIcon(iconPath, 110, 110);
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        dexPanel.add(iconLabel, BorderLayout.CENTER);

        // Button
        JButton button = new JButton(name);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setFocusPainted(false);
        button.setBackground(new Color(230, 230, 230));
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        button.addActionListener(e -> new RegionalDexFrame(name + " Pokédex", pokedex).setVisible(true));


        dexPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isNational) {
                    new NationalDexFrame(name, pokedex).setVisible(true);
                } else {
                    new RegionalDexFrame(name + " Pokédex", pokedex).setVisible(true);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                dexPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                dexPanel.setBackground(new Color(245, 245, 245)); // subtle hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                dexPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                dexPanel.setBackground(Color.WHITE);
            }
        });


        dexPanel.add(button, BorderLayout.SOUTH);
        parent.add(dexPanel);
    }

    private ImageIcon loadScaledIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}

