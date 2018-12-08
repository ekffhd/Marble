package Util;

public class Dice {
    protected int dice_num;

    public Dice(){
        dice_num = (int)(Math.random() * 6) + 1;
    }

    public float roll_dice(){
        //dice_num = ;
        dice_num = (int)(Math.random() * 6) + 1;
        return dice_num;
    }

    public int get_dice(){
        return dice_num;
    }
}
