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
        playerData.cash -= cash;
        return playerData.cash;
    }
    public void set_positon(int position){
        this.playerData.position = position;
    }
    public void set_island_count(){
        this.playerData.island_count = 2;
    }
}
