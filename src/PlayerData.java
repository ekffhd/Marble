public class PlayerData {
    private int characterId;
    private int position;
    private int cash;
    private boolean is_active;
    //건물 클래스


    public PlayerData(int characterId){
        this.characterId = characterId;
        this.position = 0;
        this.cash = 0;
        this.is_active = true;
    }
    public boolean getStatus(){
        return this.is_active;
    }
    public int getCharacterIdI(){
        return this.characterId;
    }
    public int getPosition(){
        return this.position;
    }
    public  int getCash(){
        return this.cash;
    }
}
