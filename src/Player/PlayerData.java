package Player;

public class PlayerData {
    protected int characterId;
    protected int position;
    protected int cash;
    private boolean isActive;

    protected int islandCount;
    protected int diceCount;
    protected int exemption;

    public PlayerData(int characterId){
        this.characterId = characterId;
        this.position = 0;
        this.cash = 0;
        this.isActive = true;
        this.islandCount = 0;
        this.diceCount = 0;
        this.exemption = 0;
    }

    public boolean getStatus(){
        return this.isActive;
    }
    public int getCharacterId(){
        return this.characterId;
    }
    public int getPosition(){
        return this.position;
    }
    public  int getCash(){
        return this.cash;
    }

}
