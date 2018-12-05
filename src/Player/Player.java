package Player;

public class Player {
    PlayerData playerData;

    public Player(int playerId){
        playerData = new PlayerData(playerId);
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
    public void add_cash(int cash){
        this.playerData.cash += cash;
    }
    public void sub_cash(int cash) { this.playerData.cash -= cash;}
    public void set_positon(int position){
        this.playerData.position = position;
    }
}
