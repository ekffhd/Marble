package Property;

import Player.Player;

public class GoldCard {
    protected int cardId;

    public GoldCard(){
    }

    // ATM 기부 카드를 뽑았을 경우
    public int donate(int cash, Place place, Player player){
        place.city.price +=cash;
        return player.sub_cash(cash);
    }

    // 복권 카드를 뽑았을 경우
    public int lotto(int cash, Player player){
        return player.add_cash(cash);
    }

    // 이동 카드를 뽑았을 경우
    public void move_player(Place originPlace, Place nextPlace,int cityNumber, Player player){
        player.set_position(cityNumber);
        originPlace.hide_player(player.get_player_id());
        nextPlace.show_player(player.get_player_id());
    }

    //세금 지불 카드를 뽑았을 경우
    public int pay_taxes(int cash, Player player){
        return player.sub_cash(cash);
    }

}
