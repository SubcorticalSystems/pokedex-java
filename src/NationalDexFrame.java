import javax.swing.*;

public class NationalDexFrame extends JFrame {
    public NationalDexFrame(String title, Pokemon[] pokedex) {
        super(title);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // You could call the same UI builder as RegionalDexFrame
        add(new JLabel("National Pokédex UI goes here")); // Replace with your actual UI
    }
}
