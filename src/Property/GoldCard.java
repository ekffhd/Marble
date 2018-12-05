package Property;


import Player.Player;

public class GoldCard {
    protected int cardId;

    public GoldCard(){
    }

    public int set_card_id(){
        cardId = (int)(Math.random() * 24);
        return cardId;
    }

    public int donate(int cash, Place place, Player player){
        place.city.price +=cash;
        return player.sub_cash(cash);
    }

    public int lotto(int cash, Player player){
        return player.add_cash(cash);
    }

    public void move_player(Place originPlace, Place nextPlace,int placeId, Player player){
        player.set_position(placeId);
        originPlace.hide_player(player.get_player_id());
        nextPlace.show_player(player.get_player_id());
    }

    public int pay_taxes(int cash, Player player){
        return player.sub_cash(cash);
    }


}
