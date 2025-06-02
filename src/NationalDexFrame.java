import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class NationalDexFrame extends JFrame {

    private DefaultListModel<Pokemon> listModel = new DefaultListModel<>();
    private JList<Pokemon> pokemonList;
    private Pokemon[] allPokemon;
    private Pokemon[][] generations;

    // Fonts and colors for light theme to match MainMenuFrame
    private final Color windowBg = new Color(245, 245, 245);
    private final Color panelBg = new Color(255, 255, 255);
    private final Color listBg = new Color(250, 250, 250);
    private final Font headerFont = new Font("Segoe UI Semibold", Font.PLAIN, 18);
    private final Font bodyFont = new Font("Segoe UI", Font.PLAIN, 14);

    public NationalDexFrame(String title, Pokemon[] nationalDex,
                            Pokemon[] genOne, Pokemon[] genTwo, Pokemon[] genThree,
                            Pokemon[] genFour, Pokemon[] genFive, Pokemon[] genSix,
                            Pokemon[] genSeven, Pokemon[] genEight, Pokemon[] genNine) {
        super(title);
        this.allPokemon = nationalDex;
        this.generations = new Pokemon[][]{
                genOne, genTwo, genThree, genFour, genFive,
                genSix, genSeven, genEight, genNine
        };

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(580, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(windowBg);
        setResizable(false);

        initUI();
    }

    private void initUI() {
        // Left panel with pokemon list
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(300, 0));
        leftPanel.setBackground(panelBg);
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        listModel.clear();
        Arrays.stream(allPokemon).forEach(listModel::addElement);

        pokemonList = new JList<>(listModel);
        pokemonList.setCellRenderer(new PokemonCellRenderer());
        pokemonList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pokemonList.setFixedCellHeight(30);
        pokemonList.setVisibleRowCount(14);
        pokemonList.setBackground(listBg);

        JScrollPane scrollPane = new JScrollPane(pokemonList);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        leftPanel.add(scrollPane, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);

        // Right panel for generation filter and Show All button
        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
        rightPanel.setPreferredSize(new Dimension(360, 0));
        rightPanel.setBackground(panelBg);
        rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(rightPanel, BorderLayout.CENTER);

        // Title label
        JLabel filterTitle = new JLabel("Filter by Generation");
        filterTitle.setForeground(Color.DARK_GRAY);
        filterTitle.setFont(headerFont);
        filterTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
        rightPanel.add(filterTitle, BorderLayout.NORTH);

        // Generation toggle buttons grid (3x3)
        JPanel genButtonPanel = new JPanel(new GridLayout(3, 3, 8, 8));
        genButtonPanel.setBackground(panelBg);
        rightPanel.add(genButtonPanel, BorderLayout.CENTER);

        String[] genNames = {
                "Gen 1", "Gen 2", "Gen 3", "Gen 4", "Gen 5",
                "Gen 6", "Gen 7", "Gen 8", "Gen 9"
        };

        ButtonGroup toggleGroup = new ButtonGroup();

        for (int i = 0; i < generations.length; i++) {
            JToggleButton genBtn = new JToggleButton(genNames[i]);
            styleCompactToggleButton(genBtn);
            genBtn.setToolTipText("Show Pokémon from " + genNames[i]);
            final int idx = i;
            genBtn.addActionListener(e -> filterByGeneration(idx));

            toggleGroup.add(genBtn);
            genButtonPanel.add(genBtn);
        }

        // Show All button below generation buttons
        JButton showAllBtn = new JButton("Show All");
        styleCompactActionButton(showAllBtn);
        showAllBtn.setToolTipText("Show all Pokémon in the National Dex");
        showAllBtn.addActionListener(e -> {
            toggleGroup.clearSelection();
            showAllPokemon();
        });
        rightPanel.add(showAllBtn, BorderLayout.SOUTH);
        showAllBtn.setForeground(Color.black);

        // Double-click listener for Pokémon details popup
        pokemonList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Pokemon selected = pokemonList.getSelectedValue();
                    if (selected != null) {
                        showPokemonDetails(selected);
                    }
                }
            }
        });
    }

    private void styleCompactToggleButton(JToggleButton btn) {
        btn.setFocusPainted(false);
        btn.setBackground(new Color(230, 230, 230));
        btn.setForeground(Color.DARK_GRAY);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(190, 190, 190)),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (!btn.isSelected()) btn.setBackground(new Color(210, 210, 210));
            }
            public void mouseExited(MouseEvent e) {
                if (!btn.isSelected()) btn.setBackground(new Color(230, 230, 230));
            }
        });
    }

    private void styleCompactActionButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBackground(new Color(80, 130, 255));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(70, 110, 220));
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(80, 130, 255));
            }
        });
    }

    //takes the array created in Pokedex.java and passed by MainMenuFrame.java
    private void filterByGeneration(int genIndex) {
        listModel.clear();
        for (Pokemon p : generations[genIndex]) {
            if (p != null) listModel.addElement(p);
        }
    }
    //attached to show all button so we can go back after filtering
    private void showAllPokemon() {
        listModel.clear();
        Arrays.stream(allPokemon).forEach(listModel::addElement);
    }

    private void showPokemonDetails(Pokemon p) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(listBg);
        panel.setBorder(new EmptyBorder(15, 20, 15, 20));

        //Title: Name and Dex #
        JLabel nameLabel = new JLabel(p.getName() + "  (" + String.format("#%03d", p.getDexNum()) + ")");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setForeground(Color.DARK_GRAY);
        panel.add(nameLabel);
        panel.add(Box.createVerticalStrut(10));

        //Types
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        typePanel.setBackground(listBg);

        typePanel.add(createTypeLabel(p.getTypeOne()));
        if (p.getTypeTwo() != null && !p.getTypeTwo().isBlank()) {
            typePanel.add(createTypeLabel(p.getTypeTwo()));
        }
        panel.add(typePanel);
        panel.add(Box.createVerticalStrut(15));

        // Stats
        JPanel statsPanel = new JPanel(new GridLayout(0, 2, 15, 10));
        statsPanel.setBackground(listBg);
        statsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        addStatLabel(statsPanel, "HP", p.getHp());
        addStatLabel(statsPanel, "Attack", p.getAtk());
        addStatLabel(statsPanel, "Defense", p.getDef());
        addStatLabel(statsPanel, "Sp. Atk", p.getSpAtk());
        addStatLabel(statsPanel, "Sp. Def", p.getSpDef());
        addStatLabel(statsPanel, "Speed", p.getSpeed());

        panel.add(statsPanel);

        JOptionPane.showMessageDialog(this, panel, p.getName() + " Stats", JOptionPane.PLAIN_MESSAGE);
    }
    //sets up the rectangular box as a label to display pokemon's type in popup window
    private JLabel createTypeLabel(String type) {
        JLabel label = new JLabel(type);
        label.setFont(bodyFont);
        label.setForeground(Color.WHITE);
        label.setBackground(getTypeColor(type));
        label.setOpaque(true);
        label.setBorder(BorderFactory.createEmptyBorder(4, 14, 4, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(label.getPreferredSize().width + 10, 26));
        return label;
    }

    private void addStatLabel(JPanel panel, String statName, int statValue) {
        JLabel statLabel = new JLabel(statName + ":");
        statLabel.setFont(bodyFont);
        statLabel.setForeground(Color.DARK_GRAY);
        JLabel statValueLabel = new JLabel(String.valueOf(statValue));
        statValueLabel.setFont(bodyFont);
        statValueLabel.setForeground(Color.DARK_GRAY);
        panel.add(statLabel);
        panel.add(statValueLabel);
    }
//colors to use for each type like in the newer games
    private Color getTypeColor(String type) {
        return switch (type.toLowerCase()) {
            case "fire" -> new Color(255, 108, 48);
            case "water" -> new Color(88, 144, 255);
            case "grass" -> new Color(120, 200, 80);
            case "electric" -> new Color(255, 208, 48);
            case "psychic" -> new Color(255, 80, 120);
            case "ice" -> new Color(100, 200, 255);
            case "dragon" -> new Color(100, 50, 200);
            case "dark" -> new Color(90, 80, 100);
            case "fairy" -> new Color(240, 160, 250);
            case "fighting" -> new Color(180, 50, 50);
            case "flying" -> new Color(140, 180, 255);
            case "poison" -> new Color(160, 80, 200);
            case "ground" -> new Color(210, 180, 90);
            case "rock" -> new Color(200, 170, 90);
            case "bug" -> new Color(170, 190, 40);
            case "ghost" -> new Color(120, 100, 200);
            case "steel" -> new Color(180, 180, 200);
            case "normal" -> new Color(200, 200, 200);
            default -> new Color(180, 180, 180);
        };
    }


    private static class PokemonCellRenderer extends JLabel implements ListCellRenderer<Pokemon> {

        public PokemonCellRenderer() {
            setOpaque(true);
            setBorder(new EmptyBorder(5, 10, 5, 10));
            setFont(new Font("Segoe UI", Font.PLAIN, 14));
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Pokemon> list, Pokemon value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            setText(String.format("#%03d  %s", value.getDexNum(), value.getName()));

            if (isSelected) {
                setBackground(new Color(80, 130, 255));
                setForeground(Color.WHITE);
            } else {
                setBackground(new Color(250, 250, 250));
                setForeground(Color.DARK_GRAY);
            }
            return this;
        }
    }
}
