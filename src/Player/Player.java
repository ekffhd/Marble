package Player;

public class Player {
    PlayerData playerData;

    public Player(int playerId){
        playerData = new PlayerData(playerId);
    }

    public int get_player_id(){
        return playerData.characterId;
    }
    public boolean get_status(){
        return this.playerData.getStatus();
    }
    public int get_position(){
        return playerData.position;
    }
    public int get_cash(){
        return playerData.cash;
    }

    public int add_cash(int cash){
        playerData.cash += cash;
        return playerData.cash;
    }
    public int sub_cash(int cash) {
        if (playerData.cash - cash > 0) {
            playerData.cash -= cash;
        }
        else{
            playerData.cash = 0;
        }
        return  playerData.cash;
    }
    public void set_position(int position){
        this.playerData.position = position;
    }
    public void add_island_count(){
        playerData.islandCount = 3;
    }
    public void sub_island_count() {
        playerData.islandCount --;
    }
    public void escape_island() {
        playerData.islandCount = 0;
    }
    public void add_dice_count(){
        playerData.diceCount++;
    }
    public void init_dice_count(){
        playerData.diceCount = 0;
    }
    public void set_exemption(){
        playerData.exemption = 1;
    }
    public void init_exemption(){
        playerData.exemption = 0;
    }

    public int get_island_count(){
        return playerData.islandCount;
    }
    public int get_dice_count(){
        return playerData.diceCount;
    }
    public int get_exemption(){
        return playerData.exemption;
    }
}
