import javax.swing.*;//for swing utilities
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Pokedex {
    final String pokeFile = "resources/pokemon.csv";
    static ArrayList<Pokemon> nationalDex = new ArrayList<>();//Use an ArrayList for the National Pokédex since it can continue to grow
    static ArrayList<Pokemon> placeholderDex = new ArrayList<>();//Used to store Pokémon if adjustments need to be made from National Dex compared to a Regional dex
    //Use Arrays for Regional Pokémon and Regional Pokédex since they are finalized with fixed quantities
    //the "gen___" arrays consist of the Pokémon introduced in that region,
    //and may differ from the total Pokémon in that region's Pokédex.
    static Pokemon[] genOne = new Pokemon[151];
    static Pokemon[] kantoDex = new Pokemon[151];
    static Pokemon[] genTwo = new Pokemon[100];
    static Pokemon[] johtoDex = new Pokemon[256];
    static Pokemon[] genThree = new Pokemon[135];
    static Pokemon[] hoennDex = new Pokemon[211];
    static Pokemon[] genFour = new Pokemon[107];
    static Pokemon[] sinnohDex = new Pokemon[210];
    static Pokemon[] genFive = new Pokemon[156];
    static Pokemon[] unovaDex = new Pokemon[156];
    static Pokemon[] genSix = new Pokemon[72];
    static Pokemon[] centralKalosDex = new Pokemon[157];
    static Pokemon[] coastalKalosDex = new Pokemon[153];
    static Pokemon[] mountainKalosDex = new Pokemon[151];
    static Pokemon[] genSeven = new Pokemon[88];
    static Pokemon[] alolaDex = new Pokemon[403];
    static Pokemon[] genEight = new Pokemon[96];
    static Pokemon[] galarDex = new Pokemon[400];
    static Pokemon[] genNine = new Pokemon[120];
    static Pokemon[] paldeaDex = new Pokemon[400];


    //main method
    public static void main(String[] args) throws IOException {
        //Creates a new pokedex Object to store all the pokemon into
        Pokedex pokedex = new Pokedex();
        //reads from csv to populate national dex
        pokedex.readCSV();

        //Creates Region's Pokémon
        pokedex.createGenOne();
        pokedex.createGenTwo();
        pokedex.createGenThree();
        pokedex.createGenFour();
        pokedex.createGenFive();
        pokedex.createGenSix();
        pokedex.createGenSeven();
        pokedex.createGenEight();
        pokedex.createGenNine();

        //Creates Regional Dex
        pokedex.createKantoDex();
        pokedex.createJohtoDex();
        pokedex.createHoennDex();
        pokedex.createSinnohDex();
        pokedex.createUnovaDex();
        pokedex.createKalosDex();
        pokedex.createAlolaDex();
        pokedex.createGalarDex();
        pokedex.createPaldeaDex();

        //makes sure that GUI interface launches
        SwingUtilities.invokeLater(() -> {
            new MainMenuFrame().setVisible(true);
        });


    }

    //Reads from .csv to populate National Dex ArrayList
    private void readCSV() throws IOException {
        BufferedReader reader = null;
        String line;
        try {
            reader = new BufferedReader(new FileReader(pokeFile));
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                for (int i = 0; i < data.length; i += 10) {
                    Pokemon pokemon = getPokemon(i, data);
                    nationalDex.add(pokemon);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            reader.close();
            //System.out.println(nationalDex);
        }
    }

    //prints dex for bug testing
    private static <T> void printPokedex(T[] pokedex) {
        System.out.println(Arrays.toString(pokedex)
                .replace("[", "")
                .replace("]", "")
                .replace(", ", ""));
    }

    //recieves input from readCSV in order to create a Pokemon Object
    private Pokemon getPokemon(int i, String[] data) {
        //uses i plus a number to adjust what order the data is being put into pokemon object
        Pokemon pokemon = new Pokemon();
        pokemon.setName(data[i]);
        pokemon.setDexNum(Integer.parseInt(data[i + 1]));
        pokemon.setTypeOne(data[i + 2]);
        pokemon.setTypeTwo(data[i + 3]);
        pokemon.setHp(Integer.parseInt((data[i + 4])));
        pokemon.setAtk(Integer.parseInt(data[i + 5]));
        pokemon.setDef(Integer.parseInt(data[i + 6]));
        pokemon.setSpAtk(Integer.parseInt(data[i + 7]));
        pokemon.setSpDef(Integer.parseInt(data[i + 8]));
        pokemon.setSpeed(Integer.parseInt(data[i + 9]));
        return pokemon;
    }

    private void populateRegionalDex(int[] dexNumsRequired, Pokemon[] dex) {
        Pokemon target;//creates a new object to temporarily store a national pokemon info
        try {
            for (int i = 0; i < dexNumsRequired.length; i++) {
                target = nationalDex.get(dexNumsRequired[i] - 1);//fills
                new Pokemon(target);//sends that targeted pokemon to be copied and put in placeholder array
                dex[i] = placeholderDex.get(i);//fills regional dex array
                if(dex == unovaDex){
                    dex[i].setDexNum(i);//this one uses #000 for victini
                } else {
                    dex[i].setDexNum(i + 1); //account for zero-index of array
                }

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(dexNumsRequired.length);
        }
        //makes sure that the dex is cleared for when it is reused by next Generation's Pokedex
        placeholderDex.clear();
    }

    //Kanto Region #1-151
    private void createGenOne() {
        for (int i = 0; i <= 150; i++) {
            genOne[i] = nationalDex.get(i);
        }

    }

    private void createKantoDex() {
        for (int i = 0; i < kantoDex.length; i++) {
            kantoDex[i] = nationalDex.get(i);
        }

    }

    //Johto Region #152-251 but +5 in Johto dex for evolutions added in generation 4
    private void createGenTwo() {
        int counter = 0;
        for (int i = 151; i < 251; i++) {
            genTwo[counter] = nationalDex.get(i);
            counter++;
        }
    }

    private void createJohtoDex() throws IOException {
        //all required dex numbers using national dex numbers but in order of regional dex placements
        int[] johtoDexNumsRequired = {152, 153, 154, 155, 156, 157, 158, 159, 160, 16, 17, 18, 21, 22, 163, 164, 19, 20, 161, 162,
                172, 25, 26, 10, 11, 12, 13, 14, 15, 165, 166, 167, 168, 74, 75, 76, 41, 42, 169, 173, 35, 36, 174, 39, 40, 175, 176, 27, 28, 23, 24, 206, 179, 180, 181,
                194, 195, 92, 93, 94, 201, 95, 208, 69, 70, 71, 187, 188, 189, 46, 47, 60, 61, 62, 186, 129, 130, 118, 119, 79, 80, 199, 43, 44, 45, 182, 96, 97, 63, 64, 65,
                132, 204, 205, 29, 30, 31, 32, 33, 34, 193, 469, 191, 192, 102, 103, 185, 202, 48, 49, 123, 212, 127, 214, 109, 110, 88, 89, 81, 82, 100, 101, 190, 424, 209,
                210, 37, 38, 58, 59, 234, 183, 184, 50, 51, 56, 57, 52, 53, 54, 55, 66, 67, 68, 236, 106, 107, 237, 203, 128, 241, 240, 126, 238, 124, 239, 125, 122, 235, 83, 177, 178, 211,
                72, 73, 98, 99, 213, 120, 121, 90, 91, 222, 223, 224, 170, 171, 86, 87, 108, 463, 114, 465, 133, 134, 135, 136, 196, 197, 116, 117, 230, 207, 225, 220, 221, 473, 216,
                217, 231, 232, 226, 227, 84, 85, 77, 78, 104, 105, 115, 111, 112, 198, 228, 229, 218, 219, 215, 200, 137, 233, 113, 242, 131, 138, 139, 140, 141, 142, 143, 1, 2, 3, 4, 5, 6,
                7, 8, 9, 144, 145, 146, 243, 244, 245, 147, 148, 149, 246, 247, 248, 249, 250, 150, 151, 251
        };

        populateRegionalDex(johtoDexNumsRequired, johtoDex);
    }

    //Hoenn Region #252-386
    private void createGenThree() {
        int counter = 0;
        for (int i = 251; i < 386; i++) {
            genThree[counter] = nationalDex.get(i);
            counter++;
        }
    }

    private void createHoennDex() {
        int[] hoennDexNumsRequired = {
                252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270,
                271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 475, 283, 284, 285, 286, 287, 288, 289,
                63, 64, 65, 290, 291, 292, 293, 294, 295, 296, 297, 118, 119, 129, 130, 298, 183, 184,
                74, 75, 76, 299, 476, 300, 301, 41, 42, 169, 72, 73, 302, 303, 304, 305, 306, 66, 67, 68, 307,
                308, 309, 310, 311, 312, 81, 82, 462, 100, 101, 313, 314, 43, 44, 45, 182, 84, 85, 406,
                315, 407, 316, 317, 318, 319, 320, 321, 322, 323, 218, 219, 324, 88, 89, 109, 110, 325,
                326, 27, 28, 327, 227, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342,
                343, 344, 345, 346, 347, 348, 174, 39, 40, 349, 350, 351, 120, 121, 352,
                353, 354, 355, 356, 477, 357, 433, 358, 359, 37, 38, 172, 25, 26, 54, 55, 360, 202,
                177, 178, 203, 231, 232, 127, 214, 111, 112, 464, 361, 362, 478, 363, 364, 365, 366, 367,
                368, 369, 222, 170, 171, 370, 116, 117, 230, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380,
                381, 382, 383, 384, 385, 386
        };
        populateRegionalDex(hoennDexNumsRequired, hoennDex);
    }

    //#387-493
    private void createGenFour() {
        int counter = 0;
        for (int i = 386; i < 493; i++) {
            genFour[counter] = nationalDex.get(i);
            counter++;
        }
    }

    private void createSinnohDex() {

        int[] sinnohDexNumsRequired = {
                387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 63, 64, 65, 129, 130,
                406, 315, 407, 41, 42, 169, 74, 75, 76, 95, 208, 408, 409, 410, 411, 66, 67, 68, 54, 55, 412, 413, 414, 265, 266, 267, 268, 269,
                415, 416, 417, 418, 419, 420, 421, 422, 423, 214, 190, 424, 425, 426, 427, 428, 92, 93, 94, 200, 429, 198, 430, 431, 432,
                118, 119, 339, 340, 433, 358, 434, 435, 307, 308, 436, 437, 77, 78, 438, 185, 439, 122, 440, 113, 242, 173, 35, 36,
                441, 172, 25, 26, 163, 164, 442, 443, 444, 445, 446, 143, 201, 447, 448, 194, 195, 278, 279, 203, 449, 450, 298,
                183, 184, 451, 452, 453, 454, 455, 223, 224, 456, 457, 72, 73, 349, 350, 458, 226, 459, 460, 215, 461, 480, 481,
                482, 483, 484, 490, 479, 207, 472, 299, 476, 280, 281, 282, 475, 108, 463, 133, 134, 135, 136, 196, 197, 470,
                471, 333, 334, 175, 176, 468, 228, 229, 81, 82, 462, 114, 465, 193, 469, 357, 111, 112, 464, 355, 356, 477, 137, 233,
                474, 123, 212, 239, 125, 466, 240, 126, 467, 220, 221, 473, 361, 362, 478, 359, 487
        };

        populateRegionalDex(sinnohDexNumsRequired, sinnohDex);
    }


    //#494-649
private void createGenFive(){
        int counter = 0;
        for (int i = 493; i < 649; i++) {
            genFive[counter] = nationalDex.get(i);
            counter++;
        }

}

private void createUnovaDex(){
    int[] unovaDexNumsRequired = {
            494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510,
            511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527,
            528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544,
            545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561,
            562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578,
            579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595,
            596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612,
            613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629,
            630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649
    };
        populateRegionalDex(unovaDexNumsRequired, unovaDex);
}


    //#650-721
    private void createGenSix(){
        int counter = 0;
        for (int i = 649; i < 721; i++) {
            genSix[counter] = nationalDex.get(i);
            counter++;
        }
    }

    private void createKalosDex(){
        //central
        int[] kalosCentralDexNumsRequired = {
                650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 263, 264, 661, 662, 663,
                16, 17, 18, 664, 665, 666, 10, 11, 12, 13, 14, 15, 511, 512, 513, 514, 515, 516,
                172, 25, 26, 399, 400, 206, 298, 183, 184, 412, 412, 412, 413, 413, 413, 414,
                283, 284, 129, 130, 341, 342, 118, 119, 318, 319, 667, 668, 54, 55, 83, 447, 448,
                280, 281, 282, 475, 669, 670, 671, 406, 315, 407, 165, 166, 415, 416, 300, 301,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 672, 673, 674, 675, 676, 84, 85, 311, 312, 316, 317,
                559, 560, 63, 64, 65, 43, 44, 45, 182, 161, 162, 290, 291, 292, 677, 678, 352,
                679, 680, 681, 543, 544, 545, 531, 235, 453, 454, 580, 581, 682, 683, 684, 685,
                313, 314, 187, 188, 189, 446, 143, 293, 294, 295, 307, 308, 41, 42, 169, 610,
                611, 612, 719, 720, 721
        };
        populateRegionalDex(kalosCentralDexNumsRequired, centralKalosDex);

        int[] kalosCoastalDexNumsRequired = {
                425, 426, 619, 620, 335, 336, 325, 326, 359, 686, 687, 337, 338, 371, 372, 373,
                278, 279, 276, 277, 688, 689, 557, 558, 72, 73, 320, 321, 370, 690, 691, 692, 693,
                120, 121, 90, 91, 211, 116, 117, 230, 369, 551, 552, 553, 694, 695, 449, 450,
                111, 112, 464, 95, 208, 527, 528, 66, 67, 68, 104, 105, 115, 303, 696, 697, 698,
                699, 142, 597, 598, 209, 210, 309, 310, 228, 229, 133, 134, 135, 136, 196, 197,
                470, 471, 700, 587, 193, 469, 701, 561, 622, 623, 299, 476, 296, 297, 538, 539,
                396, 397, 398, 434, 435, 29, 30, 31, 32, 33, 34, 702, 433, 358, 439, 122, 577,
                578, 579, 360, 202, 524, 525, 526, 302, 703, 128, 241, 179, 180, 181, 127, 214,
                417, 79, 80, 199, 102, 103, 441, 458, 226, 366, 367, 368, 223, 224, 222, 170,
                171, 594, 131, 144, 145, 146
        };
        populateRegionalDex(kalosCoastalDexNumsRequired, coastalKalosDex);

        int[] kalosMountainDexNumsRequired = {
                50, 51, 328, 329, 330, 443, 444, 445, 74, 75, 76, 218, 219, 213, 451, 452,
                194, 195, 704, 705, 706, 588, 589, 616, 617, 69, 70, 71, 455, 92, 93, 94,
                60, 61, 62, 186, 23, 24, 618, 339, 340, 509, 510, 261, 262, 504, 505, 624,
                625, 707, 198, 430, 590, 591, 270, 271, 272, 418, 419, 550, 708, 709, 710,
                711, 607, 608, 609, 479, 81, 82, 462, 100, 101, 568, 569, 220, 221, 473,
                712, 713, 613, 614, 238, 124, 582, 583, 584, 459, 460, 225, 215, 461, 532,
                533, 534, 324, 27, 28, 304, 305, 306, 246, 247, 248, 631, 632, 167, 168,
                21, 22, 615, 227, 714, 715, 207, 472, 163, 164, 174, 39, 40, 353, 354,
                570, 571, 574, 575, 576, 438, 185, 327, 216, 217, 108, 463, 123, 212, 132,
                333, 334, 621, 633, 634, 635, 147, 148, 149, 716, 717, 718, 150
        };
        populateRegionalDex(kalosMountainDexNumsRequired, mountainKalosDex);
    }
    //#722-809
    private void createGenSeven(){
        int counter = 0;
        for (int i = 721; i < 809; i++) {
            genSeven[counter] = nationalDex.get(i);
            counter++;
        }
    }

    private void createAlolaDex(){

        int[] alolaDexNumsRequired = {
                722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734, 735, 19,
                20, 10, 11, 12, 165, 166, 167, 168, 427, 428, 686, 687, 570, 571, 676, 172,
                25, 26, 737, 737, 738, 438, 185, 440, 113, 242, 446, 143, 79, 80, 199, 278,
                279, 63, 64, 65, 52, 53, 81, 82, 462, 88, 89, 439, 122, 23, 24, 206, 58,
                59, 96, 97, 296, 297, 235, 739, 740, 92, 93, 94, 425, 426, 198, 430, 41,
                42, 169, 714, 715, 50, 51,21,22,627,628,629, 630, 56,57,225,701,741,742,743,669,670,671,
                548,549,546,547,54,55,238,124,129,130,339,340,86,87,66,67,68,524,525,526,703,302,303,744,745,
                327,72,73,456,457,746,370,222,747,748,90,91,366,367,368,223,224,458,
                226,371,372,373,506,507,508,133,134,135,136,196,197,470,471,700,179,180,181,
                749,750,174,39,40,128,241,283,284,751,752,753,754,755,756,46,47,60,61,62,186,118,119,550,349,
                350,594,661,662,663,757,758,104,105,115,240, 126, 467, 636, 637, 759, 760, 761, 762, 763, 764, 127,
                163, 164, 352, 765, 766, 704, 705, 706, 351,
                767, 768, 120, 121, 769, 770, 138, 139, 140, 141, 345, 346, 347, 348, 408, 409, 410, 411, 566, 567,
                564, 565, 696, 697, 698, 699, 246, 247, 248, 708, 709, 177, 178, 299, 476, 771, 170, 171, 772, 773,
                803, 804, 718, 568, 569, 572, 573, 204, 205, 227, 132, 173, 35, 36, 605, 606, 774, 374, 375, 376,
                137, 233, 474, 674, 675, 775, 324, 776, 228, 229, 702, 777, 309, 310, 239, 125, 466, 74, 75, 76,
                551, 552, 553, 328, 329, 330, 443, 444, 445, 343, 344, 622, 623, 707, 778, 353, 354, 592, 593, 779,
                780, 359, 361,362, 478, 215, 461, 27, 28, 37, 38, 582, 583, 584, 559, 560, 624, 625, 209, 210, 422,
                423, 369, 781, 318, 319, 690, 691, 692, 693, 320, 321, 131, 357, 102, 103, 341, 342, 619, 620, 782,
                783, 784, 587, 123, 212, 214, 190, 424, 667, 668, 200, 429, 621, 108, 463, 447, 448, 147, 148, 149,
                142, 785, 786, 787, 788, 789, 790, 791, 792, 793, 805, 806, 794, 795, 796, 797, 798, 799, 800, 801,
                802, 807
        };
        populateRegionalDex(alolaDexNumsRequired, alolaDex);

    }
    //#810-905
    private void createGenEight(){
        int counter = 0;
        for(int i = 809; i < 905; i++ ){
            genEight[counter] = nationalDex.get(i);
            counter ++;
        }
    }

    private void createGalarDex(){

        int[] galarDexNumsRequired = {
                810, 811, 812, 813, 814, 815, 816, 817, 818,
                824, 825, 826, 10, 11, 12, 736, 737, 738,
                163, 164, 821, 822, 823, 819, 820, 519, 520,
                521, 827, 828, 263, 264, 862, 831,
                832, 270, 271, 272, 273, 274, 275, 833, 834,
                509, 510, 835, 836, 659, 660, 572, 573,
                761, 762, 763, 43, 44, 45, 182, 406, 315,
                407, 278, 279, 595, 596, 309, 310, 37, 38,
                58, 59, 582, 583, 584, 220, 221,
                473, 225, 361, 362, 478, 343, 344, 749, 750,
                557, 558, 622, 623, 517, 518, 177, 178, 759,
                760, 459, 460, 98, 99, 194, 195,
                341, 342, 290, 291, 292, 236, 106, 107, 237,
                674, 675, 599, 600, 601, 415, 416, 436, 437,
                280, 281, 282, 475, 425, 426, 829, 830, 420,
                421, 434, 435, 535, 536, 537, 355, 356, 477,
                66, 67, 68, 92, 93, 94, 129, 130, 118, 119,
                223, 224, 90, 91,
                349, 350, 550, 746, 771, 568, 569, 850, 851,
                837, 838, 839, 50, 51, 529, 530, 524,
                525, 526, 532, 533, 534, 527, 528, 714, 715,
                95, 208, 846, 847, 52, 863, 53,
                868, 869, 742, 743, 597, 598, 710, 711, 172,
                25, 26, 133, 134, 135, 136, 196, 197,
                470, 471, 700,
                840, 841, 842, 677, 678, 684, 685, 682, 683,
                751, 752, 360, 202, 83, 865, 170, 171, 453,
                454, 559, 560, 618, 213, 339, 340, 422,
                423, 767, 768, 688, 689, 222, 864, 859,
                860, 861, 856, 857, 858, 757, 758, 624, 625, 538,
                539, 109,110, 438, 185, 173, 35, 36, 175, 176, 468, 446, 143,
                546, 547, 111, 112, 464, 574, 575, 576, 577, 578,
                579, 588, 589, 616, 617, 605, 606, 613, 614, 627,
                628, 629, 630, 451, 452, 607, 608, 609, 686, 687,
                215, 461, 302, 303, 556, 561, 447, 448, 324,
                778, 878, 879, 211, 592, 593, 747, 748,
                845, 848, 849, 843, 844, 449, 450, 632, 631,
                694, 695, 701, 328, 329, 330, 610, 611, 612, 562,
                867, 563, 679, 680, 681, 77, 78,
                854, 855, 876, 708, 709, 755, 756, 765, 766,
                877, 870, 780, 776, 777, 872, 873,
                852, 853, 871, 458, 226, 320, 321, 712, 713, 781,
                131, 337, 338, 439, 122, 866, 554, 555, 874, 875, 884, 479,
                132, 880, 881, 882, 883, 4, 5, 6,
                772, 773, 246, 247, 248, 633, 634, 635, 704, 705,
                706, 782, 783, 784, 885, 886, 887, 888, 889,
                890
        };
        populateRegionalDex(galarDexNumsRequired, galarDex);

    }
    //#906-1025
    private void createGenNine(){
        int counter = 0;
        for(int i = 905; i < 1025; i++){
            genNine[counter] = nationalDex.get(i);
            counter ++;
        }
    }
    private void createPaldeaDex(){
        int paldeaDexNumsRequired[] = {
                906, 907, 908, 909, 910, 911, 912, 913, 914, 915, 916,
                917, 918, 919, 920, 187, 188, 189, 661, 662, 663, 921, 922, 923,
                228, 229, 734, 735, 819, 820, 191, 192, 401, 402, 664, 665, 666,
                415, 416, 821, 822, 823, 440, 113, 242, 298, 183, 184, 283, 284,
                418, 419, 194, 980, 54, 55, 833, 834, 174, 39, 40, 280, 281, 282, 475,
                96, 97, 92, 93, 94, 924, 925, 172, 25, 26, 926, 927, 287, 288, 289,
                761, 762, 763, 928, 929, 930, 438, 185, 744, 745,
                837, 838, 839, 403, 404, 405, 396, 397, 398, 741,
                179, 180, 181, 548, 549, 285, 286, 840, 841, 842,
                325, 326, 931, 200, 429, 296, 297, 739, 740, 757, 758,
                231, 232, 878, 879, 443, 444, 445, 932, 933, 934,
                278, 279, 129, 130, 846, 847, 550, 316, 317, 52, 53,
                425, 426, 669, 670, 671, 50, 51, 324,
                322, 323, 436, 437, 610, 611, 612, 56, 57, 979,
                307, 308, 447, 448, 935, 936, 937, 339, 340, 938, 939,
                704, 705, 706, 453, 454, 940, 941, 133, 134, 135, 136,
                196, 197, 470, 471, 700, 206, 982, 585, 586,
                203, 981, 88, 89, 942, 943, 848, 849, 702,
                417, 944, 945, 234, 590, 591, 100, 101, 81, 82, 462,
                132, 58, 59, 216, 217, 335, 336, 333, 334, 672, 673, 128,
                667, 668, 434, 435, 570, 571, 215, 461, 198, 430, 574, 575, 576,
                854, 855, 778, 707, 876, 946, 947, 948, 949, 357, 753, 754, 950, 951, 952,
                331, 332, 953, 954, 48, 49, 204, 205, 123, 212, 214, 955, 956,
                449, 450, 551, 552, 553, 843, 844, 749, 750, 636, 637,
                371, 372, 373, 957, 958, 959, 856, 857, 858, 859, 860, 861,
                960, 961, 962, 963, 964, 965, 966, 967, 968,
                302, 353, 354, 870,
                701, 442, 714, 715, 885, 886, 887, 969, 970, 479,
                971, 972, 765, 766, 775, 246, 247, 248, 874, 875, 871, 769, 770,
                79, 80, 199, 422, 423, 90, 91, 211, 370, 456, 457, 779,
                594, 690, 691, 692, 693, 602, 603, 604, 747, 748,
                973, 147, 148, 149, 872, 873,
                459, 460, 225, 613, 614, 361, 362, 478, 615, 974, 975, 712, 713,
                627, 628, 624, 625, 983, 633, 634, 635, 976, 977, 978,
                984, 985, 986, 987, 988, 989, 990, 991, 992, 993, 994, 995, 996, 997, 998, 999,
                1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008
        };
        populateRegionalDex(paldeaDexNumsRequired,paldeaDex);

    }

}


