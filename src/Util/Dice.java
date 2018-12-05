package Util;

public class Dice {
    protected int dice_num;

    public Dice(){
        dice_num = (int)(Math.random() * 6) + 1;
    }

    public int roll_dice(){
        //dice_num = 3;
        dice_num = (int)(Math.random() * 6) + 1;
        return dice_num;
    }

    public int get_dice(){
        return dice_num;
    }
}
