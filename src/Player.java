import java.awt.*;

public class Player {
    PlayerData playerData;

    public Player(int playerId){
        playerData = new PlayerData(playerId);
    }
    public boolean getStatus(){
        return this.playerData.getStatus();
    }
}
