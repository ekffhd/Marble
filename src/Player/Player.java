package Player;
//주석 완료
import Property.Place;

public class Player {
    protected int           playerId; //플레이어 ID
    protected int           position; //현재 위치
    protected int           cash; //소지 자금
    protected int           ownedPlaceCount;
    protected int[]         ownedPlaceId;
    private boolean         isActive; //생존 여부

    protected int           islandCount; //무인도 탈출 카운트
    protected int           diceCount; //주사위 던진 횟수 카운트
    protected int           exemption; //면제권

    public Player(int playerId){
        this.playerId = playerId;
        this.position = 0;
        this.cash = 0;
        this.ownedPlaceCount = 0;
        this.ownedPlaceId = new int[24];

        this.isActive = true;

        this.islandCount = 0;
        this.diceCount = 0;
        this.exemption = 0;
    }

    public int get_player_id(){
        return playerId;
    }

    //생존여부 설정&반환
    public void set_status(boolean active){
        this.isActive = active;
    }
    public boolean get_status(){
        return isActive;
    }

    //부지를 구매/인수 하였을 때, ownedPlaceId 배열에 해당 부지의 id를 추가한다.
    public void add_owned_place(int placeId){
        ownedPlaceId[ownedPlaceCount] = placeId;
        ownedPlaceCount ++;
    } // add_owned_place()

    //인수 당햐였을 때, ownedPlaceId 배열에서 해당 부지의 id를 제거한다.
    public void sub_owned_place(int placeId){
        int flag = 0;

        for(int i=0;i<ownedPlaceCount;i++) {
            if (ownedPlaceId[i] == placeId) {
                flag = i;
                break;
            }
        }

        for(int i=flag;i<ownedPlaceCount-1;i++){
            ownedPlaceId[i] = ownedPlaceId[i+1];
        }
        ownedPlaceCount --;
    } // sub_owned_place()

    //소지자금 설정&반환
    public int add_cash(int cash){
        this.cash += cash;
        return this.cash;
    }
    public int sub_cash(int cash) {
        this.cash -= cash;
        return  this.cash;
    }
    public int get_cash(){
        return cash;
    }

    //현 위치 설정&반환
    public void set_position(int position){
        this.position = position;
    }
    public int get_position(){
        return position;
    }

    //지그재그 탈출 횟수 설정&반환
    public void add_island_count(){
        islandCount = 3;
    }
    public void sub_island_count() {
        islandCount --;
    }
    public void escape_island() {
        islandCount = 0;
    }
    public int get_island_count(){
        return islandCount;
    }

    //주사위 던진 횟수 설정&반환
    public void add_dice_count(){
        diceCount++;
    }
    public void init_dice_count(){
        diceCount = 0;
    }
    public int get_dice_count(){
        return diceCount;
    }

    //면제권 설정&반환
    public void set_exemption(){
        exemption = 1;
    }
    public void init_exemption(){
        exemption = 0;
    }
    public int get_exemption(){
        return exemption;
    }

    //플레이어가 파산하였을 경우, 소유한 부지 정보들을 모두 초기화한다.
    public void game_over(Place[] place){
        for(int i=0;i<ownedPlaceCount;i++){
            place[ownedPlaceId[i]].init_place_info(this);
        }
    }

    //플레이어의 정보를 초기화 한다.
    public void init_player(){
        position = 0;
        cash = 0;
        ownedPlaceCount = 0;
        for (int i=0; i<24; i++){
            ownedPlaceId[i] = 0;
        }
        isActive = true;
        islandCount = 0;
        diceCount = 0;
        exemption = 0;
    }

}
