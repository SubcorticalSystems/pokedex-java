import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();

    JPanel subpanel1 = new JPanel();
    JPanel subpanel2 = new JPanel();
    JLabel pokedexLabel = new JLabel();
    JLabel pokemonNameLabel = new JLabel();
    //JLabel pokemonIdLabel = new JLabel();
    JLabel pokemonTupeLabel = new JLabel();
    MyFrame(Pokemon pokemon, String dexName) {

        this.setTitle("Pokedex");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 650);
        //this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10,10));
        this.setResizable(false);

        panel2.setLayout(new BorderLayout(5,5));
        pokedexLabel.setText(dexName);
        pokedexLabel.setBackground(Color.WHITE);
        pokedexLabel.setOpaque(true);
        pokemonNameLabel.setText(pokemon.getName() + "      " + pokemon.getDexNum() + "     "
        + pokemon.getTypeOne() + " " + pokemon.getTypeTwo());
        pokemonNameLabel.setBackground(Color.WHITE);
        pokemonNameLabel.setOpaque(true);


        panel1.setBackground(Color.red);
        panel2.setBackground(Color.lightGray);
        panel3.setBackground(Color.red);
        panel4.setBackground(Color.red);
        panel5.setBackground(Color.red);
        subpanel1.setBackground(Color.darkGray);
        subpanel2.setBackground(Color.darkGray);

        panel1.setPreferredSize(new Dimension(100, 100));
        panel2.setPreferredSize(new Dimension(100, 100));
        panel3.setPreferredSize(new Dimension(100, 100));
        panel4.setPreferredSize(new Dimension(100, 100));
        panel5.setPreferredSize(new Dimension(100, 100));
        subpanel1.setPreferredSize(new Dimension(50, 50));
        subpanel2.setPreferredSize(new Dimension(50, 50));

        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.SOUTH);
        this.add(panel4, BorderLayout.EAST);
        this.add(panel5, BorderLayout.WEST);
        panel2.add(subpanel1, BorderLayout.NORTH);
        panel2.add(subpanel2, BorderLayout.SOUTH);
        subpanel1.add(pokedexLabel, BorderLayout.WEST);
        panel2.add(pokemonNameLabel, BorderLayout.CENTER);

        this.setVisible(true);
    }

}
