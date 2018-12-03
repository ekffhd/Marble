public class City implements IPlace{
    private String name;
    private Building[] buildings;
    private int price;
    private Player owner;

    public City(){
        buildings = new Building[4];
        price = 0;
        for(int i=0; i<4; i++){
            buildings[i] = new Building();
        }
    }

    private class Building implements IBuilding{
        public void purchase(){

        }
        public void sell(){

        }
        public void acquire(){

        }
    }

    public void setName(String name){ //도시 이름 설정
        this.name = name;
    }

    public void onEnter(){
        if (owner == null){ // 주인이 없을 떄

        }
        else { // 주인이 있을 때

        }
    }
}
