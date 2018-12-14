package Util;
//주석 완료
public class Dice {
    protected int dice_num;

    //생성자
    public Dice(){
        dice_num = (int)(Math.random() * 6) + 1;
    }

    //주사위 굴리기
    public void roll_dice(){
        dice_num = (int)(Math.random() * 6) + 1;
    }

    //주사위 값 반환
    public int get_dice(){
        return dice_num;
    }

}
