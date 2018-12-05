package Property;


import Player.Player;

public class GoldCard {
    protected int cardId;

    public GoldCard(){

    }
    public GoldCard(int cardId){
        this.cardId = cardId;

    }

    public int set_card_id(){
        cardId = (int)(Math.random() * 9);
        return cardId;
    }

    public int donate(int cash, Place place, Player player){
        place.city.price +=cash;
        return player.sub_cash(cash);
    }

    public int lotto(int cash, Player player){
        return player.add_cash(cash);
    }


}
