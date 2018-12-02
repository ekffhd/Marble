public class Dice {
    private int dice_num;

    public Dice(){
        dice_num = (int)(Math.random() * 6) + 1;
    }

    public int roll_dice(){
        dice_num = (int)(Math.random() * 6) + 1;
        return dice_num;
    }
}
