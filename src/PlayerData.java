

public class PlayerData {
    public int characterId;
    public int pointX, pointY;
    public int cash;
    //건물 클래스

    public PlayerData(int characterId){
        this.characterId = characterId;
        this.pointX = 0;
        this.pointY = 0;
        this.cash = 0;
    }
}
