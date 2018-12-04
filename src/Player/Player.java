package Player;

public class Player {
    PlayerData playerData;

    public Player(int playerId){
        playerData = new PlayerData(playerId);
    }
    public boolean getStatus(){
        return this.playerData.getStatus();
    }
    public int get_position(){
        return playerData.position;
    }
    public void set_positon(int position){
        this.playerData.position = position;
    }
}
