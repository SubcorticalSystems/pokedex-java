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
        addDexOption(buttonGrid, "Kalos", Pokedex.coastalKalosDex, "resources/icons/Kalos_Map.png", false);
        addDexOption(buttonGrid, "Alola", Pokedex.alolaDex, "resources/icons/Alola_Map.png", false);
        addDexOption(buttonGrid, "Galar", Pokedex.galarDex, "resources/icons/Grookey_Map.png", false);
        addDexOption(buttonGrid, "Paldea", Pokedex.paldeaDex, "resources/icons/Paldea_Map.png", false);
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

                //Kalos has 3 mini dex comprising its regional dex so we need to give an option
                //which to pass to Regional dex frame
                if (name.equals("Kalos")) {
                    String[] kalosOptions = {"Central Kalos Dex", "Coastal Kalos Dex", "Mountain Kalos Dex"};
                    String selected = (String) JOptionPane.showInputDialog(//option pane that will be clickable before launch
                            null,
                            "Choose a Kalos Dex:",
                            "Kalos Regional Dex",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            kalosOptions,
                            kalosOptions[0]
                    );

                    if (selected != null) {
                        Pokemon[] selectedDex = switch (selected) {
                            case "Central Kalos Dex" -> Pokedex.centralKalosDex;
                            case "Coastal Kalos Dex" -> Pokedex.coastalKalosDex;
                            case "Mountain Kalos Dex" -> Pokedex.mountainKalosDex;
                            default -> null;
                        };

                        if (selectedDex != null) {//once an option is selecting and okay is pressed it will pass like normal and open regional dex
                            new RegionalDexFrame(selected, selectedDex).setVisible(true);
                        }
                    }
                } else if (isNational) {
                    new NationalDexFrame("National Dex",
                            Pokedex.nationalDex.toArray(new Pokemon[0]),
                            Pokedex.genOne,
                            Pokedex.genTwo,
                            Pokedex.genThree,
                            Pokedex.genFour,
                            Pokedex.genFive,
                            Pokedex.genSix,
                            Pokedex.genSeven,
                            Pokedex.genEight,
                            Pokedex.genNine
                    ).setVisible(true);

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

